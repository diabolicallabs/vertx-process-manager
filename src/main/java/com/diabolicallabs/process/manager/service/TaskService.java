package com.diabolicallabs.process.manager.service;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

import java.util.List;

@ProxyGen
@VertxGen
public interface TaskService {

  static final String DEFAULT_ADDRESS = "kie.task.service";

  static TaskService createProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(TaskService.class, vertx, address);
  }

  @Fluent TaskService addComment(Long taskId, String userId, String comment, Handler<AsyncResult<Void>> handler);
  @Fluent TaskService claim(Long taskId, String userId, Handler<AsyncResult<Void>> handler);
  @Fluent TaskService complete(Long taskId, String userId, JsonObject data, Handler<AsyncResult<Void>> handler);
  @Fluent TaskService delegate(Long taskId, String userId, String newUserId, Handler<AsyncResult<Void>> handler);
  @Fluent TaskService exit(Long taskId, String userId, Handler<AsyncResult<Void>> handler);
  @Fluent TaskService fail(Long taskId, String userId, JsonObject data, Handler<AsyncResult<Void>> handler);
  @Fluent TaskService forward(Long taskId, String userId, String newUserId, Handler<AsyncResult<Void>> handler);
  @Fluent TaskService getContent(Long taskId, Handler<AsyncResult<JsonObject>> handler);
  @Fluent TaskService release(Long taskId, String userId, Handler<AsyncResult<Void>> handler);
  @Fluent TaskService resume(Long taskId, String userId, Handler<AsyncResult<Void>> handler);
  @Fluent TaskService skip(Long taskId, String userId, Handler<AsyncResult<Void>> handler);
  @Fluent TaskService start(Long taskId, String userId, Handler<AsyncResult<Void>> handler);
  @Fluent TaskService stop(Long taskId, String userId, Handler<AsyncResult<Void>> handler);
  @Fluent TaskService suspend(Long taskId, String userId, Handler<AsyncResult<Void>> handler);
}
