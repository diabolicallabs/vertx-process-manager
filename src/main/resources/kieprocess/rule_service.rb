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
    # @yield 
    # @return [self]
    def fire_all_rules
      if block_given?
        @j_del.java_method(:fireAllRules, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling fire_all_rules()"
    end
    # @param [Hash{String => Object}] fact 
    # @yield 
    # @return [self]
    def insert(fact=nil)
      if fact.class == Hash && block_given?
        @j_del.java_method(:insert, [Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(::Vertx::Util::Utils.to_json_object(fact),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling insert(fact)"
    end
  end
end
