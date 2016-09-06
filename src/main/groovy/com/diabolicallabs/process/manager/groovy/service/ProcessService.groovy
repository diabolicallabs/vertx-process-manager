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
public class ProcessService {
  private final def com.diabolicallabs.process.manager.service.ProcessService delegate;
  public ProcessService(Object delegate) {
    this.delegate = (com.diabolicallabs.process.manager.service.ProcessService) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  public static ProcessService createProxy(Vertx vertx, String address) {
    def ret = InternalHelper.safeCreate(com.diabolicallabs.process.manager.service.ProcessService.createProxy(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, address), com.diabolicallabs.process.manager.groovy.service.ProcessService.class);
    return ret;
  }
  public ProcessService abort(Long processInstanceId, Handler<AsyncResult<Void>> handler) {
    delegate.abort(processInstanceId, handler);
    return this;
  }
  public ProcessService create(String processId, Handler<AsyncResult<ProcessInstanceService>> handler) {
    delegate.create(processId, handler != null ? new Handler<AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(InternalHelper.safeCreate(ar.result(), com.diabolicallabs.process.manager.groovy.service.ProcessInstanceService.class)));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
    return this;
  }
  public ProcessService createWithVariables(String processId, Map<String, Object> variables, Handler<AsyncResult<ProcessInstanceService>> handler) {
    delegate.createWithVariables(processId, variables != null ? new io.vertx.core.json.JsonObject(variables) : null, handler != null ? new Handler<AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(InternalHelper.safeCreate(ar.result(), com.diabolicallabs.process.manager.groovy.service.ProcessInstanceService.class)));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
    return this;
  }
  public ProcessService signalEvent(String eventName, Map<String, Object> data, Handler<AsyncResult<Void>> handler) {
    delegate.signalEvent(eventName, data != null ? new io.vertx.core.json.JsonObject(data) : null, handler);
    return this;
  }
  public ProcessService signalEventForProcess(String eventName, Long processInstanceId, Map<String, Object> data, Handler<AsyncResult<Void>> handler) {
    delegate.signalEventForProcess(eventName, processInstanceId, data != null ? new io.vertx.core.json.JsonObject(data) : null, handler);
    return this;
  }
  public ProcessService startProcess(String processId, Handler<AsyncResult<ProcessInstanceService>> handler) {
    delegate.startProcess(processId, handler != null ? new Handler<AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(InternalHelper.safeCreate(ar.result(), com.diabolicallabs.process.manager.groovy.service.ProcessInstanceService.class)));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
    return this;
  }
  public ProcessService startProcessWithVariables(String processId, Map<String, Object> jsonObject, Handler<AsyncResult<ProcessInstanceService>> handler) {
    delegate.startProcessWithVariables(processId, jsonObject != null ? new io.vertx.core.json.JsonObject(jsonObject) : null, handler != null ? new Handler<AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(InternalHelper.safeCreate(ar.result(), com.diabolicallabs.process.manager.groovy.service.ProcessInstanceService.class)));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
    return this;
  }
}
