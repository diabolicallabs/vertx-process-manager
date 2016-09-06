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

/** @module kieprocess-js/knowledge_service */
var utils = require('vertx-js/util/utils');
var RuleService = require('kieprocess-js/rule_service');
var ProcessService = require('kieprocess-js/process_service');
var Vertx = require('vertx-js/vertx');
var TaskService = require('kieprocess-js/task_service');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JKnowledgeService = com.diabolicallabs.process.manager.service.KnowledgeService;

/**
 @class
*/
var KnowledgeService = function(j_val) {

  var j_knowledgeService = j_val;
  var that = this;

  /**

   @public
   @param resourceName {string} 
   @param handler {function} 
   @return {KnowledgeService}
   */
  this.addClassPathResource = function(resourceName, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_knowledgeService["addClassPathResource(java.lang.String,io.vertx.core.Handler)"](resourceName, function(ar) {
      if (ar.succeeded()) {
        handler(null, null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param fileName {string} 
   @param handler {function} 
   @return {KnowledgeService}
   */
  this.addFileResource = function(fileName, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_knowledgeService["addFileResource(java.lang.String,io.vertx.core.Handler)"](fileName, function(ar) {
      if (ar.succeeded()) {
        handler(null, null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param handler {function} 
   @return {KnowledgeService}
   */
  this.processDefinitions = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_knowledgeService["processDefinitions(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnJson(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param handler {function} 
   @return {KnowledgeService}
   */
  this.getProcessService = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_knowledgeService["getProcessService(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnVertxGen(ar.result(), ProcessService), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param handler {function} 
   @return {KnowledgeService}
   */
  this.getRuleService = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_knowledgeService["getRuleService(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnVertxGen(ar.result(), RuleService), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param handler {function} 
   @return {KnowledgeService}
   */
  this.getTaskService = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_knowledgeService["getTaskService(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnVertxGen(ar.result(), TaskService), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param handler {function} 
   @return {KnowledgeService}
   */
  this.getTaskServiceAddress = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_knowledgeService["getTaskServiceAddress(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        handler(ar.result(), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   */
  this.close = function() {
    var __args = arguments;
    if (__args.length === 0) {
      j_knowledgeService["close()"]();
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_knowledgeService;
};

/**

 @memberof module:kieprocess-js/knowledge_service
 @param vertx {Vertx} 
 @param address {string} 
 @return {KnowledgeService}
 */
KnowledgeService.createProxy = function(vertx, address) {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string') {
    return utils.convReturnVertxGen(JKnowledgeService["createProxy(io.vertx.core.Vertx,java.lang.String)"](vertx._jdel, address), KnowledgeService);
  } else throw new TypeError('function invoked with invalid arguments');
};

// We export the Constructor function
module.exports = KnowledgeService;