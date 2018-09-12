package com.diabolicallabs.process.manager.service;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyClose;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.serviceproxy.ProxyHelper;

@ProxyGen
@VertxGen
public interface KnowledgeService {

  static KnowledgeService createProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(KnowledgeService.class, vertx, address);
  }

  @Fluent
  KnowledgeService addClassPathResource(String resourceName, Handler<AsyncResult<Void>> handler);

  @Fluent
  KnowledgeService addFileResource(String fileName, Handler<AsyncResult<Void>> handler);

  @Fluent
  KnowledgeService processDefinitions(Handler<AsyncResult<JsonArray>> handler);

  @Fluent
  KnowledgeService getSessionService(Handler<AsyncResult<SessionService>> handler);

  @Fluent
  KnowledgeService getTaskService(Handler<AsyncResult<TaskService>> handler);

  @Fluent
  KnowledgeService getTaskServiceAddress(Handler<AsyncResult<String>> handler);

  @Fluent
  KnowledgeService build(Handler<AsyncResult<Void>> handler);

  @ProxyClose
  void close();
}
