package com.diabolicallabs.process.manager.service;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.serviceproxy.ProxyHelper;

@ProxyGen
@VertxGen
public interface KnowledgeServiceFactory {

  static final String DEFAULT_ADDRESS = "kie.knowledge.service.factory";

  static KnowledgeServiceFactory createProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(KnowledgeServiceFactory.class, vertx, address);
  }

  @Fluent KnowledgeServiceFactory getKnowledgeService(Handler<AsyncResult<KnowledgeService>> handler);

}
