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

/** @module kieprocess-js/process_service */
var utils = require('vertx-js/util/utils');
var Vertx = require('vertx-js/vertx');
var ProcessInstanceService = require('kieprocess-js/process_instance_service');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JProcessService = com.diabolicallabs.process.manager.service.ProcessService;

/**
 @class
*/
var ProcessService = function(j_val) {

  var j_processService = j_val;
  var that = this;

  /**

   @public
   @param processInstanceId {number} 
   @param handler {function} 
   @return {ProcessService}
   */
  this.abort = function(processInstanceId, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] ==='number' && typeof __args[1] === 'function') {
      j_processService["abort(java.lang.Long,io.vertx.core.Handler)"](utils.convParamLong(processInstanceId), function(ar) {
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
   @param processId {string} 
   @param handler {function} 
   @return {ProcessService}
   */
  this.create = function(processId, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_processService["create(java.lang.String,io.vertx.core.Handler)"](processId, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnVertxGen(ar.result(), ProcessInstanceService), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param processId {string} 
   @param variables {Object} 
   @param handler {function} 
   @return {ProcessService}
   */
  this.createWithVariables = function(processId, variables, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && (typeof __args[1] === 'object' && __args[1] != null) && typeof __args[2] === 'function') {
      j_processService["createWithVariables(java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](processId, utils.convParamJsonObject(variables), function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnVertxGen(ar.result(), ProcessInstanceService), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param eventName {string} 
   @param data {Object} 
   @param handler {function} 
   @return {ProcessService}
   */
  this.signalEvent = function(eventName, data, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && (typeof __args[1] === 'object' && __args[1] != null) && typeof __args[2] === 'function') {
      j_processService["signalEvent(java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](eventName, utils.convParamJsonObject(data), function(ar) {
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
   @param eventName {string} 
   @param processInstanceId {number} 
   @param data {Object} 
   @param handler {function} 
   @return {ProcessService}
   */
  this.signalEventForProcess = function(eventName, processInstanceId, data, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] ==='number' && (typeof __args[2] === 'object' && __args[2] != null) && typeof __args[3] === 'function') {
      j_processService["signalEventForProcess(java.lang.String,java.lang.Long,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](eventName, utils.convParamLong(processInstanceId), utils.convParamJsonObject(data), function(ar) {
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
   @param processId {string} 
   @param handler {function} 
   @return {ProcessService}
   */
  this.startProcess = function(processId, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_processService["startProcess(java.lang.String,io.vertx.core.Handler)"](processId, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnVertxGen(ar.result(), ProcessInstanceService), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param processId {string} 
   @param jsonObject {Object} 
   @param handler {function} 
   @return {ProcessService}
   */
  this.startProcessWithVariables = function(processId, jsonObject, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && (typeof __args[1] === 'object' && __args[1] != null) && typeof __args[2] === 'function') {
      j_processService["startProcessWithVariables(java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](processId, utils.convParamJsonObject(jsonObject), function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnVertxGen(ar.result(), ProcessInstanceService), null);
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
  this._jdel = j_processService;
};

/**

 @memberof module:kieprocess-js/process_service
 @param vertx {Vertx} 
 @param address {string} 
 @return {ProcessService}
 */
ProcessService.createProxy = function(vertx, address) {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string') {
    return utils.convReturnVertxGen(JProcessService["createProxy(io.vertx.core.Vertx,java.lang.String)"](vertx._jdel, address), ProcessService);
  } else throw new TypeError('function invoked with invalid arguments');
};

// We export the Constructor function
module.exports = ProcessService;