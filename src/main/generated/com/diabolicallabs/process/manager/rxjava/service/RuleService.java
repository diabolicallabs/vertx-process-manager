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

  public RuleService fireAllRules(Handler<AsyncResult<Integer>> handler) { 
    delegate.fireAllRules(handler);
    return this;
  }

  public Observable<Integer> fireAllRulesObservable() { 
    io.vertx.rx.java.ObservableFuture<Integer> handler = io.vertx.rx.java.RxHelper.observableFuture();
    fireAllRules(handler.toHandler());
    return handler;
  }

  public RuleService insert(JsonObject fact, Handler<AsyncResult<Void>> handler) { 
    delegate.insert(fact, handler);
    return this;
  }

  public Observable<Void> insertObservable(JsonObject fact) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    insert(fact, handler.toHandler());
    return handler;
  }


  public static RuleService newInstance(com.diabolicallabs.process.manager.service.RuleService arg) {
    return arg != null ? new RuleService(arg) : null;
  }
}
