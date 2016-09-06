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
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;


public class KnowledgeService {

  final com.diabolicallabs.process.manager.service.KnowledgeService delegate;

  public KnowledgeService(com.diabolicallabs.process.manager.service.KnowledgeService delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  public static KnowledgeService createProxy(Vertx vertx, String address) { 
    KnowledgeService ret = KnowledgeService.newInstance(com.diabolicallabs.process.manager.service.KnowledgeService.createProxy((io.vertx.core.Vertx)vertx.getDelegate(), address));
    return ret;
  }

  public KnowledgeService addClassPathResource(String resourceName, Handler<AsyncResult<Void>> handler) { 
    delegate.addClassPathResource(resourceName, handler);
    return this;
  }

  public Observable<Void> addClassPathResourceObservable(String resourceName) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    addClassPathResource(resourceName, handler.toHandler());
    return handler;
  }

  public KnowledgeService addFileResource(String fileName, Handler<AsyncResult<Void>> handler) { 
    delegate.addFileResource(fileName, handler);
    return this;
  }

  public Observable<Void> addFileResourceObservable(String fileName) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    addFileResource(fileName, handler.toHandler());
    return handler;
  }

  public KnowledgeService processDefinitions(Handler<AsyncResult<JsonArray>> handler) { 
    delegate.processDefinitions(handler);
    return this;
  }

  public Observable<JsonArray> processDefinitionsObservable() { 
    io.vertx.rx.java.ObservableFuture<JsonArray> handler = io.vertx.rx.java.RxHelper.observableFuture();
    processDefinitions(handler.toHandler());
    return handler;
  }

  public KnowledgeService getProcessService(Handler<AsyncResult<ProcessService>> handler) { 
    delegate.getProcessService(new Handler<AsyncResult<com.diabolicallabs.process.manager.service.ProcessService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.ProcessService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(ProcessService.newInstance(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    });
    return this;
  }

  public Observable<ProcessService> getProcessServiceObservable() { 
    io.vertx.rx.java.ObservableFuture<ProcessService> handler = io.vertx.rx.java.RxHelper.observableFuture();
    getProcessService(handler.toHandler());
    return handler;
  }

  public KnowledgeService getRuleService(Handler<AsyncResult<RuleService>> handler) { 
    delegate.getRuleService(new Handler<AsyncResult<com.diabolicallabs.process.manager.service.RuleService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.RuleService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(RuleService.newInstance(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    });
    return this;
  }

  public Observable<RuleService> getRuleServiceObservable() { 
    io.vertx.rx.java.ObservableFuture<RuleService> handler = io.vertx.rx.java.RxHelper.observableFuture();
    getRuleService(handler.toHandler());
    return handler;
  }

  public KnowledgeService getTaskService(Handler<AsyncResult<TaskService>> handler) { 
    delegate.getTaskService(new Handler<AsyncResult<com.diabolicallabs.process.manager.service.TaskService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.TaskService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(TaskService.newInstance(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    });
    return this;
  }

  public Observable<TaskService> getTaskServiceObservable() { 
    io.vertx.rx.java.ObservableFuture<TaskService> handler = io.vertx.rx.java.RxHelper.observableFuture();
    getTaskService(handler.toHandler());
    return handler;
  }

  public KnowledgeService getTaskServiceAddress(Handler<AsyncResult<String>> handler) { 
    delegate.getTaskServiceAddress(handler);
    return this;
  }

  public Observable<String> getTaskServiceAddressObservable() { 
    io.vertx.rx.java.ObservableFuture<String> handler = io.vertx.rx.java.RxHelper.observableFuture();
    getTaskServiceAddress(handler.toHandler());
    return handler;
  }

  public void close() { 
    delegate.close();
  }


  public static KnowledgeService newInstance(com.diabolicallabs.process.manager.service.KnowledgeService arg) {
    return arg != null ? new KnowledgeService(arg) : null;
  }
}
