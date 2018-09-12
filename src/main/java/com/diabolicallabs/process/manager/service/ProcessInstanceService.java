package com.diabolicallabs.process.manager.service;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyClose;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

@ProxyGen
@VertxGen
public interface ProcessInstanceService {

  static ProcessInstanceService createProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(ProcessInstanceService.class, vertx, address);
  }

  @Fluent
  ProcessInstanceService abort(Handler<AsyncResult<Void>> handler);

  @Fluent
  ProcessInstanceService start(Handler<AsyncResult<Long>> handler);

  @Fluent
  ProcessInstanceService getInstanceId(Handler<AsyncResult<Long>> handler);

  @Fluent
  ProcessInstanceService getName(Handler<AsyncResult<String>> handler);

  @Fluent
  ProcessInstanceService getParentInstanceId(Handler<AsyncResult<Long>> handler);

  @Fluent
  ProcessInstanceService getState(Handler<AsyncResult<ProcessState>> handler);

  @Fluent
  ProcessInstanceService signalEvent(String eventName, JsonObject data, Handler<AsyncResult<Void>> handler);

  @ProxyClose
  void close();
}
