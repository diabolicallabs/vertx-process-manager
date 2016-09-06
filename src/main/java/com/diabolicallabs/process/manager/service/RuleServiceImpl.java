package com.diabolicallabs.process.manager.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.drools.core.marshalling.impl.ProtobufMessages;
import org.drools.core.spi.FactHandleFactory;
import org.kie.api.runtime.KieSession;
import org.kie.internal.runtime.helper.BatchExecutionHelper;

public class RuleServiceImpl implements RuleService {

  private Vertx vertx;
  private String address;
  private KieSession kieSession;

  public RuleServiceImpl(Vertx vertx, String address, KieSession kieSession) {
    this.vertx = vertx;
    this.address = address;
    this.kieSession = kieSession;
  }

  @Override
  public RuleService delete(String factHandle, Handler<AsyncResult<Void>> handler) {

    JsonObject json = new JsonObject().put("fact-handle", false);
    return this;
  }

  @Override
  public RuleService fireAllRules(Handler<AsyncResult<Integer>> handler) {

    Integer rulesFired = kieSession.fireAllRules();
    handler.handle(Future.succeededFuture(rulesFired));

    return this;
  }

  @Override
  public RuleService insert(String factType, JsonObject attributes, Handler<AsyncResult<String>> handler) {

    /*

  {"object":{"org.drools.compiler.test.Person":{"name":"john","age":25}}}
     */
    JsonObject fact = new JsonObject().put("object", new JsonObject().put(factType, attributes));

    String factHandle = kieSession.insert(fact).toExternalForm();
    handler.handle(Future.succeededFuture(factHandle));
    return this;
  }

  @Override
  public RuleService update(String factHandle, String factType, JsonObject attributes, Handler<AsyncResult<Void>> handler) {
    return this;
  }
}
