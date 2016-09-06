package com.diabolicallabs.process.manager.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;

import java.util.UUID;

public class ProcessServiceImpl implements ProcessService {

  private Vertx vertx;
  private String address;
  private KieSession kieSession;
  private ProcessInstance instance;

  public ProcessServiceImpl(Vertx vertx, String address, KieSession kieSession) {
    this.vertx = vertx;
    this.address = address;
    this.kieSession = kieSession;
  }

  @Override
  public ProcessService abort(Long processInstanceId, Handler<AsyncResult<Void>> handler) {

    kieSession.abortProcessInstance(processInstanceId);
    handler.handle(Future.succeededFuture());

    return this;
  }

  @Override
  public ProcessService create(String processId, Handler<AsyncResult<ProcessInstanceService>> handler) {

    this.createWithVariables(processId, new JsonObject(), handler);

    return this;
  }

  @Override
  public ProcessService createWithVariables(String processId, JsonObject variables, Handler<AsyncResult<ProcessInstanceService>> handler) {

    String id = UUID.randomUUID().toString();
    String serviceAddress = "ProcessInstanceService-" + processId + "-" + id;

    vertx.executeBlocking(future -> {
      kieSession.addEventListener(new VertxProcessEventListener(vertx));

      instance = kieSession.createProcessInstance(processId, variables.getMap());
      future.complete(kieSession);
    }, result -> {
      if (result.succeeded()) {
        ProcessInstanceServiceImpl serviceImpl = new ProcessInstanceServiceImpl(vertx, serviceAddress, instance, kieSession);
        ProxyHelper.registerService(ProcessInstanceService.class, vertx, serviceImpl, serviceAddress);

        ProcessInstanceService service = ProcessInstanceService.createProxy(vertx, serviceAddress);
        handler.handle(Future.succeededFuture(service));
      }
      if (result.failed()) handler.handle(Future.failedFuture(result.cause()));
    });

    return this;
  }

  @Override
  public ProcessService signalEvent(String eventName, JsonObject data, Handler<AsyncResult<Void>> handler) {

    kieSession.signalEvent(eventName, data);
    handler.handle(Future.succeededFuture());

    return this;
  }

  @Override
  public ProcessService signalEventForProcess(String eventName, Long processInstanceId, JsonObject data, Handler<AsyncResult<Void>> handler) {

    kieSession.signalEvent(eventName, data, processInstanceId);
    handler.handle(Future.succeededFuture());

    return this;
  }

  @Override
  public ProcessService startProcess(String processId, Handler<AsyncResult<ProcessInstanceService>> handler) {

    String id = UUID.randomUUID().toString();
    String serviceAddress = "ProcessInstanceService-" + processId + "-" + id;

    vertx.executeBlocking(future -> {
      kieSession.addEventListener(new VertxProcessEventListener(vertx));

      instance = kieSession.startProcess(processId);
      future.complete(kieSession);
    }, result -> {
      if (result.succeeded()) {
        ProcessInstanceServiceImpl serviceImpl = new ProcessInstanceServiceImpl(vertx, serviceAddress, instance, kieSession);
        ProxyHelper.registerService(ProcessInstanceService.class, vertx, serviceImpl, serviceAddress);

        ProcessInstanceService service = ProcessInstanceService.createProxy(vertx, serviceAddress);
        handler.handle(Future.succeededFuture(service));
      }
      if (result.failed()) handler.handle(Future.failedFuture(result.cause()));
    });

    return this;
  }

  @Override
  public ProcessService startProcessWithVariables(String processId, JsonObject jsonObject, Handler<AsyncResult<ProcessInstanceService>> handler) {

    String id = UUID.randomUUID().toString();
    String serviceAddress = "ProcessInstanceService-" + processId + "-" + id;

    vertx.executeBlocking(future -> {
      kieSession.addEventListener(new VertxProcessEventListener(vertx));

      instance = kieSession.startProcess(processId, jsonObject.getMap());
      future.complete(kieSession);
    }, result -> {
      if (result.succeeded()) {
        ProcessInstanceServiceImpl serviceImpl = new ProcessInstanceServiceImpl(vertx, serviceAddress, instance, kieSession);
        ProxyHelper.registerService(ProcessInstanceService.class, vertx, serviceImpl, serviceAddress);

        ProcessInstanceService service = ProcessInstanceService.createProxy(vertx, serviceAddress);
        handler.handle(Future.succeededFuture(service));
      }
      if (result.failed()) handler.handle(Future.failedFuture(result.cause()));
    });

    return this;
  }

}
