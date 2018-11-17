package com.diabolicallabs.process.manager.service;

import bitronix.tm.TransactionManagerServices;
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
<<<<<<< HEAD
import org.drools.core.impl.KnowledgeBaseFactory;
import org.jbpm.runtime.manager.impl.jpa.EntityManagerFactoryManager;
=======
import io.vertx.serviceproxy.ServiceBinder;
>>>>>>> 577d43a2903b2b678aa55cedbe3d84be347f0a82
import org.kie.api.KieBase;
import org.kie.api.definition.process.Node;
import org.kie.api.definition.process.Process;
import org.kie.api.definition.process.WorkflowProcess;
import org.kie.api.io.Resource;
import org.kie.api.runtime.Environment;
import org.kie.api.runtime.EnvironmentName;
import org.kie.api.runtime.manager.*;
import org.kie.api.runtime.process.ProcessRuntime;
import org.kie.api.task.TaskLifeCycleEventListener;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.kie.internal.runtime.manager.context.ProcessInstanceIdContext;
import org.kie.internal.task.api.EventService;
import org.kie.internal.utils.KieHelper;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
<<<<<<< HEAD
import javax.transaction.TransactionManager;
import java.util.HashSet;
import java.util.Set;
=======
import java.lang.reflect.Proxy;
>>>>>>> 577d43a2903b2b678aa55cedbe3d84be347f0a82

public class KnowledgeServiceImpl implements KnowledgeService {

  private Logger logger = LoggerFactory.getLogger(KnowledgeServiceImpl.class);

  private Boolean built = false;
  private Vertx vertx;
  private JsonObject config;
  private String address;
  private String taskAddress;

  private KieBase kieBase;
  private RuntimeEngine runtime;
  private RuntimeEnvironment environment;
  private RuntimeManager manager;
  private Set<Resource> resources = new HashSet<>();

  TaskService taskService;
  MessageConsumer<JsonObject> taskServiceConsumer;

  public KnowledgeServiceImpl(Vertx vertx, JsonObject config, String address) {
    this.vertx = vertx;
    this.config = config;
    this.address = address;
    taskAddress = address + ".user.task";
  }

  @Override
  public KnowledgeService addClassPathResource(String resourceName, Handler<AsyncResult<Void>> handler) {

    Resource resource = ResourceFactory.newClassPathResource(resourceName);
    resources.add(resource);
    //builder.addAsset(resource, resource.getResourceType());
    handler.handle(Future.succeededFuture());

    return this;
  }

  @Override
  public KnowledgeService addFileResource(String fileName, Handler<AsyncResult<Void>> handler) {

    Resource resource = ResourceFactory.newFileResource(fileName);
    resources.add(resource);
    handler.handle(Future.succeededFuture());

    return this;
  }

  @Override
  public KnowledgeService processDefinitions(Handler<AsyncResult<JsonArray>> handler) {

    if (!built) {
      handler.handle(Future.failedFuture("KnowledgeService is not built yet"));
      return this;
    }

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
  public KnowledgeService getSessionService(Handler<AsyncResult<SessionService>> handler) {

    if (!built) {
      handler.handle(Future.failedFuture("KnowledgeService is not built yet"));
      return this;
    }

    String serviceAddress = address + ".SessionService";
    SessionServiceImpl serviceImpl = new SessionServiceImpl(vertx, config, serviceAddress, runtime.getKieSession());

    new ServiceBinder(vertx)
        .setAddress(serviceAddress)
        .register(SessionService.class, serviceImpl);

    handler.handle(Future.succeededFuture(SessionService.createProxy(vertx, serviceAddress)));
    return this;
  }

  @Override
  public KnowledgeService getTaskService(Handler<AsyncResult<TaskService>> handler) {

    if (!built) {
      handler.handle(Future.failedFuture("KnowledgeService is not built yet"));
      return this;
    }

    if (taskService == null) {
      String serviceAddress = address + ".TaskService";
      TaskServiceImpl serviceImpl = new TaskServiceImpl(vertx, serviceAddress, runtime.getTaskService());
      taskServiceConsumer = new ServiceBinder(vertx)
          .setAddress(serviceAddress)
          .register(TaskService.class, serviceImpl);
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
  public KnowledgeService build(Handler<AsyncResult<Void>> handler) {
    EntityManagerFactory entityManagerFactory =
      Persistence.createEntityManagerFactory( "org.jbpm.persistence.jpa" );
    //EntityManagerFactory entityManagerFactory = EntityManagerFactoryManager.get().getOrCreate("org.jbpm.persistence.jpa");
    TransactionManager transactionManager = TransactionManagerServices.getTransactionManager();

    RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder();
    resources.forEach(resource -> builder.addAsset(resource, resource.getResourceType()));
    builder.addEnvironmentEntry(EnvironmentName.TRANSACTION_MANAGER, transactionManager);
    builder.entityManagerFactory(entityManagerFactory);
    environment = builder.get();

    kieBase = environment.getKieBase();
    manager = RuntimeManagerFactory.Factory.get().newPerProcessInstanceRuntimeManager(environment);
    //manager = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(environment);
    runtime = manager.getRuntimeEngine(ProcessInstanceIdContext.get());
    //runtime = manager.getRuntimeEngine(EmptyContext.get());
    built = true;

    handler.handle(Future.succeededFuture());

    return this;
  }

  @Override
  public void close() {
<<<<<<< HEAD
    logger.info("Closing KnowledgeService");
    if (taskServiceConsumer != null) ProxyHelper.unregisterService(taskServiceConsumer);
    if (manager != null) {
      manager.close();
      manager.disposeRuntimeEngine(runtime);
    }
    if (environment != null) environment.close();
=======
    if (taskServiceConsumer != null) new ServiceBinder(vertx).unregister(taskServiceConsumer);
>>>>>>> 577d43a2903b2b678aa55cedbe3d84be347f0a82
  }
}
