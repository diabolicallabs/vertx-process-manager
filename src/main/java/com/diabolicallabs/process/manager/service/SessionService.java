package com.diabolicallabs.process.manager.service;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyClose;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.serviceproxy.ProxyHelper;

@ProxyGen
@VertxGen
public interface SessionService {

  static final String DEFAULT_ADDRESS = "kie.session.service";

  static SessionService createProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(SessionService.class, vertx, address);
  }

  @Fluent
  SessionService getProcessService(Handler<AsyncResult<ProcessService>> handler);

  @Fluent
  SessionService getRuleService(Handler<AsyncResult<RuleService>> handler);

  @ProxyClose
  void close();
}
