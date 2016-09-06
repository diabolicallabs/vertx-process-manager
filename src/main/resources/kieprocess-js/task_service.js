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

/** @module kieprocess-js/task_service */
var utils = require('vertx-js/util/utils');
var Vertx = require('vertx-js/vertx');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JTaskService = com.diabolicallabs.process.manager.service.TaskService;

/**
 @class
*/
var TaskService = function(j_val) {

  var j_taskService = j_val;
  var that = this;

  /**

   @public
   @param taskId {number} 
   @param userId {string} 
   @param comment {string} 
   @param handler {function} 
   @return {TaskService}
   */
  this.addComment = function(taskId, userId, comment, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] ==='number' && typeof __args[1] === 'string' && typeof __args[2] === 'string' && typeof __args[3] === 'function') {
      j_taskService["addComment(java.lang.Long,java.lang.String,java.lang.String,io.vertx.core.Handler)"](utils.convParamLong(taskId), userId, comment, function(ar) {
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
   @param taskId {number} 
   @param userId {string} 
   @param handler {function} 
   @return {TaskService}
   */
  this.claim = function(taskId, userId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] ==='number' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_taskService["claim(java.lang.Long,java.lang.String,io.vertx.core.Handler)"](utils.convParamLong(taskId), userId, function(ar) {
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
   @param taskId {number} 
   @param userId {string} 
   @param data {Object} 
   @param handler {function} 
   @return {TaskService}
   */
  this.complete = function(taskId, userId, data, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] ==='number' && typeof __args[1] === 'string' && (typeof __args[2] === 'object' && __args[2] != null) && typeof __args[3] === 'function') {
      j_taskService["complete(java.lang.Long,java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](utils.convParamLong(taskId), userId, utils.convParamJsonObject(data), function(ar) {
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
   @param taskId {number} 
   @param userId {string} 
   @param newUserId {string} 
   @param handler {function} 
   @return {TaskService}
   */
  this.delegate = function(taskId, userId, newUserId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] ==='number' && typeof __args[1] === 'string' && typeof __args[2] === 'string' && typeof __args[3] === 'function') {
      j_taskService["delegate(java.lang.Long,java.lang.String,java.lang.String,io.vertx.core.Handler)"](utils.convParamLong(taskId), userId, newUserId, function(ar) {
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
   @param taskId {number} 
   @param userId {string} 
   @param handler {function} 
   @return {TaskService}
   */
  this.exit = function(taskId, userId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] ==='number' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_taskService["exit(java.lang.Long,java.lang.String,io.vertx.core.Handler)"](utils.convParamLong(taskId), userId, function(ar) {
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
   @param taskId {number} 
   @param userId {string} 
   @param data {Object} 
   @param handler {function} 
   @return {TaskService}
   */
  this.fail = function(taskId, userId, data, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] ==='number' && typeof __args[1] === 'string' && (typeof __args[2] === 'object' && __args[2] != null) && typeof __args[3] === 'function') {
      j_taskService["fail(java.lang.Long,java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](utils.convParamLong(taskId), userId, utils.convParamJsonObject(data), function(ar) {
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
   @param taskId {number} 
   @param userId {string} 
   @param newUserId {string} 
   @param handler {function} 
   @return {TaskService}
   */
  this.forward = function(taskId, userId, newUserId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] ==='number' && typeof __args[1] === 'string' && typeof __args[2] === 'string' && typeof __args[3] === 'function') {
      j_taskService["forward(java.lang.Long,java.lang.String,java.lang.String,io.vertx.core.Handler)"](utils.convParamLong(taskId), userId, newUserId, function(ar) {
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
   @param taskId {number} 
   @param handler {function} 
   @return {TaskService}
   */
  this.getContent = function(taskId, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] ==='number' && typeof __args[1] === 'function') {
      j_taskService["getContent(java.lang.Long,io.vertx.core.Handler)"](utils.convParamLong(taskId), function(ar) {
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
   @param taskId {number} 
   @param userId {string} 
   @param handler {function} 
   @return {TaskService}
   */
  this.release = function(taskId, userId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] ==='number' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_taskService["release(java.lang.Long,java.lang.String,io.vertx.core.Handler)"](utils.convParamLong(taskId), userId, function(ar) {
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
   @param taskId {number} 
   @param userId {string} 
   @param handler {function} 
   @return {TaskService}
   */
  this.resume = function(taskId, userId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] ==='number' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_taskService["resume(java.lang.Long,java.lang.String,io.vertx.core.Handler)"](utils.convParamLong(taskId), userId, function(ar) {
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
   @param taskId {number} 
   @param userId {string} 
   @param handler {function} 
   @return {TaskService}
   */
  this.skip = function(taskId, userId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] ==='number' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_taskService["skip(java.lang.Long,java.lang.String,io.vertx.core.Handler)"](utils.convParamLong(taskId), userId, function(ar) {
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
   @param taskId {number} 
   @param userId {string} 
   @param handler {function} 
   @return {TaskService}
   */
  this.start = function(taskId, userId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] ==='number' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_taskService["start(java.lang.Long,java.lang.String,io.vertx.core.Handler)"](utils.convParamLong(taskId), userId, function(ar) {
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
   @param taskId {number} 
   @param userId {string} 
   @param handler {function} 
   @return {TaskService}
   */
  this.stop = function(taskId, userId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] ==='number' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_taskService["stop(java.lang.Long,java.lang.String,io.vertx.core.Handler)"](utils.convParamLong(taskId), userId, function(ar) {
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
   @param taskId {number} 
   @param userId {string} 
   @param handler {function} 
   @return {TaskService}
   */
  this.suspend = function(taskId, userId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] ==='number' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_taskService["suspend(java.lang.Long,java.lang.String,io.vertx.core.Handler)"](utils.convParamLong(taskId), userId, function(ar) {
      if (ar.succeeded()) {
        handler(null, null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_taskService;
};

/**

 @memberof module:kieprocess-js/task_service
 @param vertx {Vertx} 
 @param address {string} 
 @return {TaskService}
 */
TaskService.createProxy = function(vertx, address) {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string') {
    return utils.convReturnVertxGen(JTaskService["createProxy(io.vertx.core.Vertx,java.lang.String)"](vertx._jdel, address), TaskService);
  } else throw new TypeError('function invoked with invalid arguments');
};

// We export the Constructor function
module.exports = TaskService;