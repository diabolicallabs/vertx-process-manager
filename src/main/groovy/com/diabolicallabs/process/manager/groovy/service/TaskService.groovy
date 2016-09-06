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
import io.vertx.groovy.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
@CompileStatic
public class TaskService {
  private final def com.diabolicallabs.process.manager.service.TaskService delegate;
  public TaskService(Object delegate) {
    this.delegate = (com.diabolicallabs.process.manager.service.TaskService) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  public static TaskService createProxy(Vertx vertx, String address) {
    def ret = InternalHelper.safeCreate(com.diabolicallabs.process.manager.service.TaskService.createProxy(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, address), com.diabolicallabs.process.manager.groovy.service.TaskService.class);
    return ret;
  }
  public TaskService addComment(Long taskId, String userId, String comment, Handler<AsyncResult<Void>> handler) {
    delegate.addComment(taskId, userId, comment, handler);
    return this;
  }
  public TaskService claim(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    delegate.claim(taskId, userId, handler);
    return this;
  }
  public TaskService complete(Long taskId, String userId, Map<String, Object> data, Handler<AsyncResult<Void>> handler) {
    delegate.complete(taskId, userId, data != null ? new io.vertx.core.json.JsonObject(data) : null, handler);
    return this;
  }
  public TaskService delegate(Long taskId, String userId, String newUserId, Handler<AsyncResult<Void>> handler) {
    delegate.delegate(taskId, userId, newUserId, handler);
    return this;
  }
  public TaskService exit(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    delegate.exit(taskId, userId, handler);
    return this;
  }
  public TaskService fail(Long taskId, String userId, Map<String, Object> data, Handler<AsyncResult<Void>> handler) {
    delegate.fail(taskId, userId, data != null ? new io.vertx.core.json.JsonObject(data) : null, handler);
    return this;
  }
  public TaskService forward(Long taskId, String userId, String newUserId, Handler<AsyncResult<Void>> handler) {
    delegate.forward(taskId, userId, newUserId, handler);
    return this;
  }
  public TaskService getContent(Long taskId, Handler<AsyncResult<Map<String, Object>>> handler) {
    delegate.getContent(taskId, handler != null ? new Handler<AsyncResult<io.vertx.core.json.JsonObject>>() {
      public void handle(AsyncResult<io.vertx.core.json.JsonObject> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((Map<String, Object>)InternalHelper.wrapObject(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
    return this;
  }
  public TaskService release(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    delegate.release(taskId, userId, handler);
    return this;
  }
  public TaskService resume(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    delegate.resume(taskId, userId, handler);
    return this;
  }
  public TaskService skip(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    delegate.skip(taskId, userId, handler);
    return this;
  }
  public TaskService start(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    delegate.start(taskId, userId, handler);
    return this;
  }
  public TaskService stop(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    delegate.stop(taskId, userId, handler);
    return this;
  }
  public TaskService suspend(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    delegate.suspend(taskId, userId, handler);
    return this;
  }
}
