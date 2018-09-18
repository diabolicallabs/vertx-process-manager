package com.diabolicallabs.process.manager.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;
import io.vertx.serviceproxy.ServiceBinder;

import java.util.UUID;

public class KnowledgeServiceFactoryImpl implements KnowledgeServiceFactory {

  private Vertx vertx;
  private JsonObject config;

  public KnowledgeServiceFactoryImpl(Vertx vertx, JsonObject config) {
    this.vertx = vertx;
    this.config = config;
  }

  @Override
  public KnowledgeServiceFactory getKnowledgeService(Handler<AsyncResult<KnowledgeService>> handler) {

    String id = UUID.randomUUID().toString();
    String address = "KnowledgeService." + id;

    KnowledgeServiceImpl serviceImpl = new KnowledgeServiceImpl(vertx, config, address);

    new ServiceBinder(vertx)
        .setAddress(address)
        .register(KnowledgeService.class, serviceImpl);

    KnowledgeService service = KnowledgeService.createProxy(vertx, address);
    handler.handle(Future.succeededFuture(service));

    return this;
  }

}
