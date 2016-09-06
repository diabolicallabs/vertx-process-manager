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


public class ProcessService {

  final com.diabolicallabs.process.manager.service.ProcessService delegate;

  public ProcessService(com.diabolicallabs.process.manager.service.ProcessService delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  public static ProcessService createProxy(Vertx vertx, String address) { 
    ProcessService ret = ProcessService.newInstance(com.diabolicallabs.process.manager.service.ProcessService.createProxy((io.vertx.core.Vertx)vertx.getDelegate(), address));
    return ret;
  }

  public ProcessService abort(Long processInstanceId, Handler<AsyncResult<Void>> handler) { 
    delegate.abort(processInstanceId, handler);
    return this;
  }

  public Observable<Void> abortObservable(Long processInstanceId) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    abort(processInstanceId, handler.toHandler());
    return handler;
  }

  public ProcessService create(String processId, Handler<AsyncResult<ProcessInstanceService>> handler) { 
    delegate.create(processId, new Handler<AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(ProcessInstanceService.newInstance(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    });
    return this;
  }

  public Observable<ProcessInstanceService> createObservable(String processId) { 
    io.vertx.rx.java.ObservableFuture<ProcessInstanceService> handler = io.vertx.rx.java.RxHelper.observableFuture();
    create(processId, handler.toHandler());
    return handler;
  }

  public ProcessService createWithVariables(String processId, JsonObject variables, Handler<AsyncResult<ProcessInstanceService>> handler) { 
    delegate.createWithVariables(processId, variables, new Handler<AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(ProcessInstanceService.newInstance(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    });
    return this;
  }

  public Observable<ProcessInstanceService> createWithVariablesObservable(String processId, JsonObject variables) { 
    io.vertx.rx.java.ObservableFuture<ProcessInstanceService> handler = io.vertx.rx.java.RxHelper.observableFuture();
    createWithVariables(processId, variables, handler.toHandler());
    return handler;
  }

  public ProcessService signalEvent(String eventName, JsonObject data, Handler<AsyncResult<Void>> handler) { 
    delegate.signalEvent(eventName, data, handler);
    return this;
  }

  public Observable<Void> signalEventObservable(String eventName, JsonObject data) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    signalEvent(eventName, data, handler.toHandler());
    return handler;
  }

  public ProcessService signalEventForProcess(String eventName, Long processInstanceId, JsonObject data, Handler<AsyncResult<Void>> handler) { 
    delegate.signalEventForProcess(eventName, processInstanceId, data, handler);
    return this;
  }

  public Observable<Void> signalEventForProcessObservable(String eventName, Long processInstanceId, JsonObject data) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    signalEventForProcess(eventName, processInstanceId, data, handler.toHandler());
    return handler;
  }

  public ProcessService startProcess(String processId, Handler<AsyncResult<ProcessInstanceService>> handler) { 
    delegate.startProcess(processId, new Handler<AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(ProcessInstanceService.newInstance(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    });
    return this;
  }

  public Observable<ProcessInstanceService> startProcessObservable(String processId) { 
    io.vertx.rx.java.ObservableFuture<ProcessInstanceService> handler = io.vertx.rx.java.RxHelper.observableFuture();
    startProcess(processId, handler.toHandler());
    return handler;
  }

  public ProcessService startProcessWithVariables(String processId, JsonObject jsonObject, Handler<AsyncResult<ProcessInstanceService>> handler) { 
    delegate.startProcessWithVariables(processId, jsonObject, new Handler<AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(ProcessInstanceService.newInstance(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    });
    return this;
  }

  public Observable<ProcessInstanceService> startProcessWithVariablesObservable(String processId, JsonObject jsonObject) { 
    io.vertx.rx.java.ObservableFuture<ProcessInstanceService> handler = io.vertx.rx.java.RxHelper.observableFuture();
    startProcessWithVariables(processId, jsonObject, handler.toHandler());
    return handler;
  }


  public static ProcessService newInstance(com.diabolicallabs.process.manager.service.ProcessService arg) {
    return arg != null ? new ProcessService(arg) : null;
  }
}
