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

package com.diabolicallabs.process.manager.rxjava.service;

import java.util.Map;
import rx.Observable;
import io.vertx.core.json.JsonArray;
import io.vertx.rxjava.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;


public class RuleService {

  final com.diabolicallabs.process.manager.service.RuleService delegate;

  public RuleService(com.diabolicallabs.process.manager.service.RuleService delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  public static RuleService createProxy(Vertx vertx, String address) { 
    RuleService ret = RuleService.newInstance(com.diabolicallabs.process.manager.service.RuleService.createProxy((io.vertx.core.Vertx)vertx.getDelegate(), address));
    return ret;
  }

  public RuleService delete(String factHandle, Handler<AsyncResult<Void>> handler) { 
    delegate.delete(factHandle, handler);
    return this;
  }

  public Observable<Void> deleteObservable(String factHandle) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    delete(factHandle, handler.toHandler());
    return handler;
  }

  public RuleService fireAllRules(Handler<AsyncResult<Integer>> handler) { 
    delegate.fireAllRules(handler);
    return this;
  }

  public Observable<Integer> fireAllRulesObservable() { 
    io.vertx.rx.java.ObservableFuture<Integer> handler = io.vertx.rx.java.RxHelper.observableFuture();
    fireAllRules(handler.toHandler());
    return handler;
  }

  public RuleService getQueryResults(String queryName, String resultName, Handler<AsyncResult<JsonArray>> handler) { 
    delegate.getQueryResults(queryName, resultName, handler);
    return this;
  }

  public Observable<JsonArray> getQueryResultsObservable(String queryName, String resultName) { 
    io.vertx.rx.java.ObservableFuture<JsonArray> handler = io.vertx.rx.java.RxHelper.observableFuture();
    getQueryResults(queryName, resultName, handler.toHandler());
    return handler;
  }

  public RuleService insert(String packageName, String typeName, JsonObject attributes, Handler<AsyncResult<String>> handler) { 
    delegate.insert(packageName, typeName, attributes, handler);
    return this;
  }

  public Observable<String> insertObservable(String packageName, String typeName, JsonObject attributes) { 
    io.vertx.rx.java.ObservableFuture<String> handler = io.vertx.rx.java.RxHelper.observableFuture();
    insert(packageName, typeName, attributes, handler.toHandler());
    return handler;
  }

  public RuleService update(String factHandle, String factType, JsonObject attributes, Handler<AsyncResult<Void>> handler) { 
    delegate.update(factHandle, factType, attributes, handler);
    return this;
  }

  public Observable<Void> updateObservable(String factHandle, String factType, JsonObject attributes) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    update(factHandle, factType, attributes, handler.toHandler());
    return handler;
  }


  public static RuleService newInstance(com.diabolicallabs.process.manager.service.RuleService arg) {
    return arg != null ? new RuleService(arg) : null;
  }
}
