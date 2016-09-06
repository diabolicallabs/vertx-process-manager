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
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;


public class KnowledgeServiceFactory {

  final com.diabolicallabs.process.manager.service.KnowledgeServiceFactory delegate;

  public KnowledgeServiceFactory(com.diabolicallabs.process.manager.service.KnowledgeServiceFactory delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  public static KnowledgeServiceFactory createProxy(Vertx vertx, String address) { 
    KnowledgeServiceFactory ret = KnowledgeServiceFactory.newInstance(com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.createProxy((io.vertx.core.Vertx)vertx.getDelegate(), address));
    return ret;
  }

  public KnowledgeServiceFactory getKnowledgeService(Handler<AsyncResult<KnowledgeService>> handler) { 
    delegate.getKnowledgeService(new Handler<AsyncResult<com.diabolicallabs.process.manager.service.KnowledgeService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.KnowledgeService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(KnowledgeService.newInstance(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    });
    return this;
  }

  public Observable<KnowledgeService> getKnowledgeServiceObservable() { 
    io.vertx.rx.java.ObservableFuture<KnowledgeService> handler = io.vertx.rx.java.RxHelper.observableFuture();
    getKnowledgeService(handler.toHandler());
    return handler;
  }


  public static KnowledgeServiceFactory newInstance(com.diabolicallabs.process.manager.service.KnowledgeServiceFactory arg) {
    return arg != null ? new KnowledgeServiceFactory(arg) : null;
  }
}
