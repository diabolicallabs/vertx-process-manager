require 'vertx/vertx'
require 'vertx/util/utils.rb'
# Generated from com.diabolicallabs.process.manager.service.RuleService
module Kieprocess
  class RuleService
    # @private
    # @param j_del [::Kieprocess::RuleService] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::Kieprocess::RuleService] the underlying java delegate
    def j_del
      @j_del
    end
    # @param [::Vertx::Vertx] vertx 
    # @param [String] address 
    # @return [::Kieprocess::RuleService]
    def self.create_proxy(vertx=nil,address=nil)
      if vertx.class.method_defined?(:j_del) && address.class == String && !block_given?
        return ::Vertx::Util::Utils.safe_create(Java::ComDiabolicallabsProcessManagerService::RuleService.java_method(:createProxy, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class]).call(vertx.j_del,address),::Kieprocess::RuleService)
      end
      raise ArgumentError, "Invalid arguments when calling create_proxy(vertx,address)"
    end
    # @param [String] factHandle 
    # @yield 
    # @return [self]
    def delete(factHandle=nil)
      if factHandle.class == String && block_given?
        @j_del.java_method(:delete, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(factHandle,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling delete(factHandle)"
    end
    # @yield 
    # @return [self]
    def fire_all_rules
      if block_given?
        @j_del.java_method(:fireAllRules, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling fire_all_rules()"
    end
    # @param [String] queryName 
    # @param [String] resultName 
    # @yield 
    # @return [self]
    def get_query_results(queryName=nil,resultName=nil)
      if queryName.class == String && resultName.class == String && block_given?
        @j_del.java_method(:getQueryResults, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(queryName,resultName,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.encode) : nil : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_query_results(queryName,resultName)"
    end
    # @param [String] packageName 
    # @param [String] typeName 
    # @param [Hash{String => Object}] attributes 
    # @yield 
    # @return [self]
    def insert(packageName=nil,typeName=nil,attributes=nil)
      if packageName.class == String && typeName.class == String && attributes.class == Hash && block_given?
        @j_del.java_method(:insert, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(packageName,typeName,::Vertx::Util::Utils.to_json_object(attributes),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling insert(packageName,typeName,attributes)"
    end
    # @param [String] factHandle 
    # @param [String] factType 
    # @param [Hash{String => Object}] attributes 
    # @yield 
    # @return [self]
    def update(factHandle=nil,factType=nil,attributes=nil)
      if factHandle.class == String && factType.class == String && attributes.class == Hash && block_given?
        @j_del.java_method(:update, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(factHandle,factType,::Vertx::Util::Utils.to_json_object(attributes),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling update(factHandle,factType,attributes)"
    end
  end
end
