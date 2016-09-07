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

/** @module kieprocess-js/rule_service */
var utils = require('vertx-js/util/utils');
var Vertx = require('vertx-js/vertx');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JRuleService = com.diabolicallabs.process.manager.service.RuleService;

/**
 @class
*/
var RuleService = function(j_val) {

  var j_ruleService = j_val;
  var that = this;

  /**

   @public
   @param factHandle {string} 
   @param handler {function} 
   @return {RuleService}
   */
  this.delete = function(factHandle, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_ruleService["delete(java.lang.String,io.vertx.core.Handler)"](factHandle, function(ar) {
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
   @return {RuleService}
   */
  this.fireAllRules = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_ruleService["fireAllRules(io.vertx.core.Handler)"](function(ar) {
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
   @param queryName {string} 
   @param resultName {string} 
   @param handler {function} 
   @return {RuleService}
   */
  this.getQueryResults = function(queryName, resultName, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_ruleService["getQueryResults(java.lang.String,java.lang.String,io.vertx.core.Handler)"](queryName, resultName, function(ar) {
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
   @param packageName {string} 
   @param typeName {string} 
   @param attributes {Object} 
   @param handler {function} 
   @return {RuleService}
   */
  this.insert = function(packageName, typeName, attributes, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && (typeof __args[2] === 'object' && __args[2] != null) && typeof __args[3] === 'function') {
      j_ruleService["insert(java.lang.String,java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](packageName, typeName, utils.convParamJsonObject(attributes), function(ar) {
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
   @param factHandle {string} 
   @param factType {string} 
   @param attributes {Object} 
   @param handler {function} 
   @return {RuleService}
   */
  this.update = function(factHandle, factType, attributes, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && (typeof __args[2] === 'object' && __args[2] != null) && typeof __args[3] === 'function') {
      j_ruleService["update(java.lang.String,java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](factHandle, factType, utils.convParamJsonObject(attributes), function(ar) {
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
  this._jdel = j_ruleService;
};

/**

 @memberof module:kieprocess-js/rule_service
 @param vertx {Vertx} 
 @param address {string} 
 @return {RuleService}
 */
RuleService.createProxy = function(vertx, address) {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string') {
    return utils.convReturnVertxGen(JRuleService["createProxy(io.vertx.core.Vertx,java.lang.String)"](vertx._jdel, address), RuleService);
  } else throw new TypeError('function invoked with invalid arguments');
};

// We export the Constructor function
module.exports = RuleService;