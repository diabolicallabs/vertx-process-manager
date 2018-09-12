package com.diabolicallabs.process.manager.service;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

@ProxyGen
@VertxGen
public interface ProcessService {

  static final String DEFAULT_ADDRESS = "kie.process.service";

  static ProcessService createProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(ProcessService.class, vertx, address);
  }

  @Fluent
  ProcessService abort(Long processInstanceId, Handler<AsyncResult<Void>> handler);

  @Fluent
  ProcessService create(String processId, Handler<AsyncResult<ProcessInstanceService>> handler);

  @Fluent
  ProcessService createWithVariables(String processId, JsonObject variables, Handler<AsyncResult<ProcessInstanceService>> handler);

  @Fluent
  ProcessService signalEvent(String eventName, JsonObject data, Handler<AsyncResult<Void>> handler);

  @Fluent
  ProcessService signalEventForProcess(String eventName, Long processInstanceId, JsonObject data, Handler<AsyncResult<Void>> handler);

  @Fluent
  ProcessService startProcess(String processId, Handler<AsyncResult<ProcessInstanceService>> handler);

  @Fluent
  ProcessService startProcessWithVariables(String processId, JsonObject jsonObject, Handler<AsyncResult<ProcessInstanceService>> handler);
}
