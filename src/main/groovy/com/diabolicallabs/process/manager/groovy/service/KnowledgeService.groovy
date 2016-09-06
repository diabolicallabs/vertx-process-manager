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
import io.vertx.core.json.JsonArray
import io.vertx.groovy.core.Vertx
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
@CompileStatic
public class KnowledgeService {
  private final def com.diabolicallabs.process.manager.service.KnowledgeService delegate;
  public KnowledgeService(Object delegate) {
    this.delegate = (com.diabolicallabs.process.manager.service.KnowledgeService) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  public static KnowledgeService createProxy(Vertx vertx, String address) {
    def ret = InternalHelper.safeCreate(com.diabolicallabs.process.manager.service.KnowledgeService.createProxy(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, address), com.diabolicallabs.process.manager.groovy.service.KnowledgeService.class);
    return ret;
  }
  public KnowledgeService addClassPathResource(String resourceName, Handler<AsyncResult<Void>> handler) {
    delegate.addClassPathResource(resourceName, handler);
    return this;
  }
  public KnowledgeService addFileResource(String fileName, Handler<AsyncResult<Void>> handler) {
    delegate.addFileResource(fileName, handler);
    return this;
  }
  public KnowledgeService processDefinitions(Handler<AsyncResult<List<Object>>> handler) {
    delegate.processDefinitions(handler != null ? new Handler<AsyncResult<io.vertx.core.json.JsonArray>>() {
      public void handle(AsyncResult<io.vertx.core.json.JsonArray> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((List<Object>)InternalHelper.wrapObject(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
    return this;
  }
  public KnowledgeService getProcessService(Handler<AsyncResult<ProcessService>> handler) {
    delegate.getProcessService(handler != null ? new Handler<AsyncResult<com.diabolicallabs.process.manager.service.ProcessService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.ProcessService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(InternalHelper.safeCreate(ar.result(), com.diabolicallabs.process.manager.groovy.service.ProcessService.class)));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
    return this;
  }
  public KnowledgeService getRuleService(Handler<AsyncResult<RuleService>> handler) {
    delegate.getRuleService(handler != null ? new Handler<AsyncResult<com.diabolicallabs.process.manager.service.RuleService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.RuleService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(InternalHelper.safeCreate(ar.result(), com.diabolicallabs.process.manager.groovy.service.RuleService.class)));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
    return this;
  }
  public KnowledgeService getTaskService(Handler<AsyncResult<TaskService>> handler) {
    delegate.getTaskService(handler != null ? new Handler<AsyncResult<com.diabolicallabs.process.manager.service.TaskService>>() {
      public void handle(AsyncResult<com.diabolicallabs.process.manager.service.TaskService> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(InternalHelper.safeCreate(ar.result(), com.diabolicallabs.process.manager.groovy.service.TaskService.class)));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
    return this;
  }
  public KnowledgeService getTaskServiceAddress(Handler<AsyncResult<String>> handler) {
    delegate.getTaskServiceAddress(handler);
    return this;
  }
  public void close() {
    delegate.close();
  }
}
