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
import com.diabolicallabs.process.manager.service.ProcessState
import io.vertx.core.json.JsonObject
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
@CompileStatic
public class ProcessInstanceService {
  private final def com.diabolicallabs.process.manager.service.ProcessInstanceService delegate;
  public ProcessInstanceService(Object delegate) {
    this.delegate = (com.diabolicallabs.process.manager.service.ProcessInstanceService) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  public static ProcessInstanceService createProxy(Vertx vertx, String address) {
    def ret = InternalHelper.safeCreate(com.diabolicallabs.process.manager.service.ProcessInstanceService.createProxy(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, address), com.diabolicallabs.process.manager.groovy.service.ProcessInstanceService.class);
    return ret;
  }
  public ProcessInstanceService abort(Handler<AsyncResult<Void>> handler) {
    delegate.abort(handler);
    return this;
  }
  public ProcessInstanceService start(Handler<AsyncResult<Long>> handler) {
    delegate.start(handler);
    return this;
  }
  public ProcessInstanceService getInstanceId(Handler<AsyncResult<Long>> handler) {
    delegate.getInstanceId(handler);
    return this;
  }
  public ProcessInstanceService getName(Handler<AsyncResult<String>> handler) {
    delegate.getName(handler);
    return this;
  }
  public ProcessInstanceService getParentInstanceId(Handler<AsyncResult<Long>> handler) {
    delegate.getParentInstanceId(handler);
    return this;
  }
  public ProcessInstanceService getState(Handler<AsyncResult<ProcessState>> handler) {
    delegate.getState(handler);
    return this;
  }
  public ProcessInstanceService signalEvent(String eventName, Map<String, Object> data, Handler<AsyncResult<Void>> handler) {
    delegate.signalEvent(eventName, data != null ? new io.vertx.core.json.JsonObject(data) : null, handler);
    return this;
  }
  public void close() {
    delegate.close();
  }
}
