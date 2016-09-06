package com.diabolicallabs.process.manager.service;

import bitronix.tm.resource.jdbc.PoolingDataSource;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.serviceproxy.ProxyHelper;
import org.kie.api.KieBase;
import org.kie.api.definition.process.Node;
import org.kie.api.definition.process.Process;
import org.kie.api.definition.process.WorkflowProcess;
import org.kie.api.io.Resource;
import org.kie.api.runtime.manager.*;
import org.kie.api.task.TaskLifeCycleEventListener;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.kie.internal.task.api.EventService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class KnowledgeServiceImpl implements KnowledgeService {

  private Logger logger = LoggerFactory.getLogger(KnowledgeServiceImpl.class);

  private Vertx vertx;
  private String address;
  private String taskAddress;

  private KieBase kieBase;
  private RuntimeEngine runtime;
  private RuntimeEnvironmentBuilder environmentBuilder;

  ProcessService processService;
  RuleService ruleService;
  TaskService taskService;

  MessageConsumer<JsonObject> processServiceConsumer;
  MessageConsumer<JsonObject> ruleServiceConsumer;
  MessageConsumer<JsonObject> taskServiceConsumer;

  public KnowledgeServiceImpl(Vertx vertx, String address) {
    this.vertx = vertx;
    this.address = address;
    taskAddress = address + ".user.task";
    environmentBuilder = RuntimeEnvironmentBuilder.Factory.get().newDefaultInMemoryBuilder();
  }

  private void generateRuntime() {

    if (runtime != null) return;

    PoolingDataSource ds = new PoolingDataSource();
    ds.setUniqueName("jdbc/jbpm");
    ds.setClassName("org.h2.jdbcx.JdbcDataSource");
    ds.setMaxPoolSize(3);
    ds.setAllowLocalTransactions(true);
    ds.getDriverProperties().put("user", "sa");
    ds.getDriverProperties().put("password", "sasa");
    ds.getDriverProperties().put("URL", "jdbc:h2:file:/tmp/data/process-instance-db");
    ds.init();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
    environmentBuilder.entityManagerFactory(emf);
    environmentBuilder.userGroupCallback(new VertxUserGroupCallback());

    RuntimeEnvironment environment = environmentBuilder.get();
    kieBase = environment.getKieBase();

    RuntimeManager manager = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(environment);
    runtime = manager.getRuntimeEngine(EmptyContext.get());

    EventService<TaskLifeCycleEventListener> taskService = (EventService<TaskLifeCycleEventListener>) runtime.getTaskService();
    taskService.registerTaskEventListener(new VertxTaskEventListener(vertx, taskAddress));

  }

  @Override
  public KnowledgeService addClassPathResource(String resourceName, Handler<AsyncResult<Void>> handler) {

    Resource resource = ResourceFactory.newClassPathResource(resourceName);
    environmentBuilder.addAsset(resource, resource.getResourceType());

    handler.handle(Future.succeededFuture());
    return this;
  }

  @Override
  public KnowledgeService addFileResource(String fileName, Handler<AsyncResult<Void>> handler) {

    Resource resource = ResourceFactory.newFileResource(fileName);
    environmentBuilder.addAsset(resource, resource.getResourceType());

    handler.handle(Future.succeededFuture());
    return this;
  }

  @Override
  public KnowledgeService processDefinitions(Handler<AsyncResult<JsonArray>> handler) {

    generateRuntime();

    JsonArray processDefinitions = new JsonArray();
    for (Process process : kieBase.getProcesses()) {
      WorkflowProcess workflowProcess = (WorkflowProcess) process;
      for (Node node : workflowProcess.getNodes()) {
        String type = (String) node.getMetaData().get("NodeType");
        logger.info("node: " + node.getName());
        logger.info("meta: " + node.getMetaData());
      }
      JsonObject json = new JsonObject()
        .put("id", process.getId())
        .put("name", process.getName())
        .put("packageName", process.getPackageName())
        .put("version", process.getVersion());

      processDefinitions.add(json);
    }
    handler.handle(Future.succeededFuture(processDefinitions));

    return this;
  }

  @Override
  public KnowledgeService getProcessService(Handler<AsyncResult<ProcessService>> handler) {

    if (processService == null) {

      String serviceAddress = address + ".ProcessService";

      generateRuntime();

      ProcessServiceImpl serviceImpl = new ProcessServiceImpl(vertx, serviceAddress, runtime.getKieSession());
      processServiceConsumer = ProxyHelper.registerService(ProcessService.class, vertx, serviceImpl, serviceAddress);

      processService = ProcessService.createProxy(vertx, serviceAddress);
    }
    handler.handle(Future.succeededFuture(processService));

    return this;
  }

  @Override
  public KnowledgeService getRuleService(Handler<AsyncResult<RuleService>> handler) {

    if (ruleService == null) {

      String serviceAddress = address + ".RuleService";

      generateRuntime();

      RuleServiceImpl serviceImpl = new RuleServiceImpl(vertx, serviceAddress, runtime.getKieSession());
      ruleServiceConsumer = ProxyHelper.registerService(RuleService.class, vertx, serviceImpl, serviceAddress);

      ruleService = RuleService.createProxy(vertx, serviceAddress);
    }
    handler.handle(Future.succeededFuture(ruleService));

    return this;
  }

  @Override
  public KnowledgeService getTaskService(Handler<AsyncResult<TaskService>> handler) {

    if (taskService == null) {

      String serviceAddress = address + ".TaskService";

      generateRuntime();

      TaskServiceImpl serviceImpl = new TaskServiceImpl(vertx, serviceAddress, runtime.getTaskService());
      taskServiceConsumer = ProxyHelper.registerService(TaskService.class, vertx, serviceImpl, serviceAddress);

      taskService = TaskService.createProxy(vertx, serviceAddress);
    }
    handler.handle(Future.succeededFuture(taskService));

    return this;
  }

  @Override
  public KnowledgeService getTaskServiceAddress(Handler<AsyncResult<String>> handler) {
    handler.handle(Future.succeededFuture(taskAddress));
    return this;
  }

  @Override
  public void close() {

    if (processServiceConsumer != null) ProxyHelper.unregisterService(processServiceConsumer);
    if (ruleServiceConsumer != null) ProxyHelper.unregisterService(ruleServiceConsumer);
    if (taskServiceConsumer != null) ProxyHelper.unregisterService(taskServiceConsumer);
    runtime.getKieSession().dispose();
    runtime.getAuditService().dispose();
  }

}
