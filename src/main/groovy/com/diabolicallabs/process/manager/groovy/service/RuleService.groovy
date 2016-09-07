/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.diabolicallabs.process.manager.groovy.service;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.core.json.JsonObject
import io.vertx.core.json.JsonArray
import io.vertx.groovy.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
@CompileStatic
public class RuleService {
  private final def com.diabolicallabs.process.manager.service.RuleService delegate;
  public RuleService(Object delegate) {
    this.delegate = (com.diabolicallabs.process.manager.service.RuleService) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  public static RuleService createProxy(Vertx vertx, String address) {
    def ret = InternalHelper.safeCreate(com.diabolicallabs.process.manager.service.RuleService.createProxy(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, address), com.diabolicallabs.process.manager.groovy.service.RuleService.class);
    return ret;
  }
  public RuleService delete(String factHandle, Handler<AsyncResult<Void>> handler) {
    delegate.delete(factHandle, handler);
    return this;
  }
  public RuleService fireAllRules(Handler<AsyncResult<Integer>> handler) {
    delegate.fireAllRules(handler);
    return this;
  }
  public RuleService getQueryResults(String queryName, String resultName, Handler<AsyncResult<List<Object>>> handler) {
    delegate.getQueryResults(queryName, resultName, handler != null ? new Handler<AsyncResult<io.vertx.core.json.JsonArray>>() {
      public void handle(AsyncResult<io.vertx.core.json.JsonArray> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((List<Object>)InternalHelper.wrapObject(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
    return this;
  }
  public RuleService insert(String packageName, String typeName, Map<String, Object> attributes, Handler<AsyncResult<String>> handler) {
    delegate.insert(packageName, typeName, attributes != null ? new io.vertx.core.json.JsonObject(attributes) : null, handler);
    return this;
  }
  public RuleService update(String factHandle, String factType, Map<String, Object> attributes, Handler<AsyncResult<Void>> handler) {
    delegate.update(factHandle, factType, attributes != null ? new io.vertx.core.json.JsonObject(attributes) : null, handler);
    return this;
  }
}
