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
public interface RuleService {

  static final String DEFAULT_ADDRESS = "kie.rule.service";

  static RuleService createProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(RuleService.class, vertx, address);
  }

  @Fluent RuleService delete(String factHandle, Handler<AsyncResult<Void>> handler);
  @Fluent RuleService fireAllRules(Handler<AsyncResult<Integer>> handler);
  @Fluent RuleService insert(String factType, JsonObject attributes, Handler<AsyncResult<String>> handler);
  @Fluent RuleService update(String factHandle, String factType, JsonObject attributes, Handler<AsyncResult<Void>> handler);

}
