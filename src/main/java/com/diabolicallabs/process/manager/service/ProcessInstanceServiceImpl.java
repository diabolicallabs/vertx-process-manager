package com.diabolicallabs.process.manager.service;


import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;

public class ProcessInstanceServiceImpl implements ProcessInstanceService {

  private Vertx vertx;
  private String address;
  private KieSession kieSession;
  private ProcessInstance instance;
  private TaskService taskService;

  public ProcessInstanceServiceImpl(Vertx vertx, String address, ProcessInstance instance, KieSession kieSession) {
    this.vertx = vertx;
    this.address = address;
    this.instance = instance;
    this.kieSession = kieSession;
  }

  @Override
  public ProcessInstanceService start(Handler<AsyncResult<Long>> handler) {
    
    kieSession.startProcessInstance(instance.getId());
    handler.handle(Future.succeededFuture(instance.getId()));

    return this;
  }

  @Override
  public ProcessInstanceService getInstanceId(Handler<AsyncResult<Long>> handler) {

    handler.handle(Future.succeededFuture(instance.getId()));

    return this;
  }

  @Override
  public ProcessInstanceService getName(Handler<AsyncResult<String>> handler) {

    handler.handle(Future.succeededFuture(instance.getProcessName()));

    return this;
  }

  @Override
  public ProcessInstanceService getParentInstanceId(Handler<AsyncResult<Long>> handler) {

    handler.handle(Future.succeededFuture(instance.getParentProcessInstanceId()));

    return this;
  }

  @Override
  public ProcessInstanceService getState(Handler<AsyncResult<ProcessState>> handler) {

    int state = instance.getState();

    switch (state) {
      case ProcessInstance.STATE_ABORTED:
        handler.handle(Future.succeededFuture(ProcessState.ABORTED));
        break;
      case ProcessInstance.STATE_ACTIVE:
        handler.handle(Future.succeededFuture(ProcessState.ACTIVE));
        break;
      case ProcessInstance.STATE_COMPLETED:
        handler.handle(Future.succeededFuture(ProcessState.COMPLETED));
        break;
      case ProcessInstance.STATE_PENDING:
        handler.handle(Future.succeededFuture(ProcessState.PENDING));
        break;
      case ProcessInstance.STATE_SUSPENDED:
        handler.handle(Future.succeededFuture(ProcessState.SUSPENDED));
        break;
    }

    return this;
  }

  @Override
  public ProcessInstanceService signalEvent(String eventName, JsonObject data, Handler<AsyncResult<Void>> handler) {

    kieSession.signalEvent(eventName, data, instance.getId());
    handler.handle(Future.succeededFuture());

    return this;
  }

  @Override
  public ProcessInstanceService abort(Handler<AsyncResult<Void>> handler) {

    kieSession.abortProcessInstance(instance.getId());
    handler.handle(Future.succeededFuture());

    return this;
  }

  @Override
  public void close() {
    kieSession.dispose();
  }
}
