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
import com.diabolicallabs.process.manager.service.ProcessState;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;


public class ProcessInstanceService {

  final com.diabolicallabs.process.manager.service.ProcessInstanceService delegate;

  public ProcessInstanceService(com.diabolicallabs.process.manager.service.ProcessInstanceService delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  public static ProcessInstanceService createProxy(Vertx vertx, String address) { 
    ProcessInstanceService ret = ProcessInstanceService.newInstance(com.diabolicallabs.process.manager.service.ProcessInstanceService.createProxy((io.vertx.core.Vertx)vertx.getDelegate(), address));
    return ret;
  }

  public ProcessInstanceService abort(Handler<AsyncResult<Void>> handler) { 
    delegate.abort(handler);
    return this;
  }

  public Observable<Void> abortObservable() { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    abort(handler.toHandler());
    return handler;
  }

  public ProcessInstanceService start(Handler<AsyncResult<Long>> handler) { 
    delegate.start(handler);
    return this;
  }

  public Observable<Long> startObservable() { 
    io.vertx.rx.java.ObservableFuture<Long> handler = io.vertx.rx.java.RxHelper.observableFuture();
    start(handler.toHandler());
    return handler;
  }

  public ProcessInstanceService getInstanceId(Handler<AsyncResult<Long>> handler) { 
    delegate.getInstanceId(handler);
    return this;
  }

  public Observable<Long> getInstanceIdObservable() { 
    io.vertx.rx.java.ObservableFuture<Long> handler = io.vertx.rx.java.RxHelper.observableFuture();
    getInstanceId(handler.toHandler());
    return handler;
  }

  public ProcessInstanceService getName(Handler<AsyncResult<String>> handler) { 
    delegate.getName(handler);
    return this;
  }

  public Observable<String> getNameObservable() { 
    io.vertx.rx.java.ObservableFuture<String> handler = io.vertx.rx.java.RxHelper.observableFuture();
    getName(handler.toHandler());
    return handler;
  }

  public ProcessInstanceService getParentInstanceId(Handler<AsyncResult<Long>> handler) { 
    delegate.getParentInstanceId(handler);
    return this;
  }

  public Observable<Long> getParentInstanceIdObservable() { 
    io.vertx.rx.java.ObservableFuture<Long> handler = io.vertx.rx.java.RxHelper.observableFuture();
    getParentInstanceId(handler.toHandler());
    return handler;
  }

  public ProcessInstanceService getState(Handler<AsyncResult<ProcessState>> handler) { 
    delegate.getState(handler);
    return this;
  }

  public Observable<ProcessState> getStateObservable() { 
    io.vertx.rx.java.ObservableFuture<ProcessState> handler = io.vertx.rx.java.RxHelper.observableFuture();
    getState(handler.toHandler());
    return handler;
  }

  public ProcessInstanceService signalEvent(String eventName, JsonObject data, Handler<AsyncResult<Void>> handler) { 
    delegate.signalEvent(eventName, data, handler);
    return this;
  }

  public Observable<Void> signalEventObservable(String eventName, JsonObject data) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    signalEvent(eventName, data, handler.toHandler());
    return handler;
  }

  public void close() { 
    delegate.close();
  }


  public static ProcessInstanceService newInstance(com.diabolicallabs.process.manager.service.ProcessInstanceService arg) {
    return arg != null ? new ProcessInstanceService(arg) : null;
  }
}
