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


public class TaskService {

  final com.diabolicallabs.process.manager.service.TaskService delegate;

  public TaskService(com.diabolicallabs.process.manager.service.TaskService delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  public static TaskService createProxy(Vertx vertx, String address) { 
    TaskService ret = TaskService.newInstance(com.diabolicallabs.process.manager.service.TaskService.createProxy((io.vertx.core.Vertx)vertx.getDelegate(), address));
    return ret;
  }

  public TaskService addComment(Long taskId, String userId, String comment, Handler<AsyncResult<Void>> handler) { 
    delegate.addComment(taskId, userId, comment, handler);
    return this;
  }

  public Observable<Void> addCommentObservable(Long taskId, String userId, String comment) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    addComment(taskId, userId, comment, handler.toHandler());
    return handler;
  }

  public TaskService claim(Long taskId, String userId, Handler<AsyncResult<Void>> handler) { 
    delegate.claim(taskId, userId, handler);
    return this;
  }

  public Observable<Void> claimObservable(Long taskId, String userId) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    claim(taskId, userId, handler.toHandler());
    return handler;
  }

  public TaskService complete(Long taskId, String userId, JsonObject data, Handler<AsyncResult<Void>> handler) { 
    delegate.complete(taskId, userId, data, handler);
    return this;
  }

  public Observable<Void> completeObservable(Long taskId, String userId, JsonObject data) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    complete(taskId, userId, data, handler.toHandler());
    return handler;
  }

  public TaskService delegate(Long taskId, String userId, String newUserId, Handler<AsyncResult<Void>> handler) { 
    delegate.delegate(taskId, userId, newUserId, handler);
    return this;
  }

  public Observable<Void> delegateObservable(Long taskId, String userId, String newUserId) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    delegate(taskId, userId, newUserId, handler.toHandler());
    return handler;
  }

  public TaskService exit(Long taskId, String userId, Handler<AsyncResult<Void>> handler) { 
    delegate.exit(taskId, userId, handler);
    return this;
  }

  public Observable<Void> exitObservable(Long taskId, String userId) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    exit(taskId, userId, handler.toHandler());
    return handler;
  }

  public TaskService fail(Long taskId, String userId, JsonObject data, Handler<AsyncResult<Void>> handler) { 
    delegate.fail(taskId, userId, data, handler);
    return this;
  }

  public Observable<Void> failObservable(Long taskId, String userId, JsonObject data) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    fail(taskId, userId, data, handler.toHandler());
    return handler;
  }

  public TaskService forward(Long taskId, String userId, String newUserId, Handler<AsyncResult<Void>> handler) { 
    delegate.forward(taskId, userId, newUserId, handler);
    return this;
  }

  public Observable<Void> forwardObservable(Long taskId, String userId, String newUserId) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    forward(taskId, userId, newUserId, handler.toHandler());
    return handler;
  }

  public TaskService getContent(Long taskId, Handler<AsyncResult<JsonObject>> handler) { 
    delegate.getContent(taskId, handler);
    return this;
  }

  public Observable<JsonObject> getContentObservable(Long taskId) { 
    io.vertx.rx.java.ObservableFuture<JsonObject> handler = io.vertx.rx.java.RxHelper.observableFuture();
    getContent(taskId, handler.toHandler());
    return handler;
  }

  public TaskService release(Long taskId, String userId, Handler<AsyncResult<Void>> handler) { 
    delegate.release(taskId, userId, handler);
    return this;
  }

  public Observable<Void> releaseObservable(Long taskId, String userId) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    release(taskId, userId, handler.toHandler());
    return handler;
  }

  public TaskService resume(Long taskId, String userId, Handler<AsyncResult<Void>> handler) { 
    delegate.resume(taskId, userId, handler);
    return this;
  }

  public Observable<Void> resumeObservable(Long taskId, String userId) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    resume(taskId, userId, handler.toHandler());
    return handler;
  }

  public TaskService skip(Long taskId, String userId, Handler<AsyncResult<Void>> handler) { 
    delegate.skip(taskId, userId, handler);
    return this;
  }

  public Observable<Void> skipObservable(Long taskId, String userId) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    skip(taskId, userId, handler.toHandler());
    return handler;
  }

  public TaskService start(Long taskId, String userId, Handler<AsyncResult<Void>> handler) { 
    delegate.start(taskId, userId, handler);
    return this;
  }

  public Observable<Void> startObservable(Long taskId, String userId) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    start(taskId, userId, handler.toHandler());
    return handler;
  }

  public TaskService stop(Long taskId, String userId, Handler<AsyncResult<Void>> handler) { 
    delegate.stop(taskId, userId, handler);
    return this;
  }

  public Observable<Void> stopObservable(Long taskId, String userId) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    stop(taskId, userId, handler.toHandler());
    return handler;
  }

  public TaskService suspend(Long taskId, String userId, Handler<AsyncResult<Void>> handler) { 
    delegate.suspend(taskId, userId, handler);
    return this;
  }

  public Observable<Void> suspendObservable(Long taskId, String userId) { 
    io.vertx.rx.java.ObservableFuture<Void> handler = io.vertx.rx.java.RxHelper.observableFuture();
    suspend(taskId, userId, handler.toHandler());
    return handler;
  }


  public static TaskService newInstance(com.diabolicallabs.process.manager.service.TaskService arg) {
    return arg != null ? new TaskService(arg) : null;
  }
}
