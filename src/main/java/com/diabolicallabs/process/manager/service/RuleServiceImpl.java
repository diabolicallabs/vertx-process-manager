package com.diabolicallabs.process.manager.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.drools.core.marshalling.impl.ProtobufMessages;
import org.drools.core.spi.FactHandleFactory;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.kie.internal.runtime.helper.BatchExecutionHelper;

import java.util.List;
import java.util.Map;

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
  public RuleService getQueryResults(String queryName, String resultName, Handler<AsyncResult<JsonArray>> handler) {

    JsonArray results = new JsonArray();

    QueryResults queryResults = kieSession.getQueryResults(queryName);
    for (QueryResultsRow row : queryResults) {
      Object object = row.get(resultName);
      String packageName = object.getClass().getPackage().getName();
      String typeName = object.getClass().getSimpleName();

      FactHandle handle = row.getFactHandle(resultName);
      FactType factType = kieSession.getKieBase().getFactType(packageName, typeName);
      Map<String, Object> attributes = factType.getAsMap(object);
      JsonObject json = new JsonObject(attributes);
      results.add(json);
    }
    handler.handle(Future.succeededFuture(results));

    return this;
  }

  @Override
  public RuleService insert(String packageName, String typeName, JsonObject attributes, Handler<AsyncResult<String>> handler) {

    FactType factType = kieSession.getKieBase().getFactType(packageName, typeName);

    Object fact;
    try {
      fact = factType.newInstance();
      factType.setFromMap(fact, attributes.getMap());
      String factHandle = kieSession.insert(fact).toExternalForm();
      handler.handle(Future.succeededFuture(factHandle));
    } catch (Exception e) {
      handler.handle(Future.failedFuture(e));
    }

    return this;
  }

  @Override
  public RuleService update(String factHandle, String factType, JsonObject attributes, Handler<AsyncResult<Void>> handler) {
    return this;
  }
}
