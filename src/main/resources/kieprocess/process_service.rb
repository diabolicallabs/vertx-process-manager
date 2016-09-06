require 'vertx/vertx'
require 'kieprocess/process_instance_service'
require 'vertx/util/utils.rb'
# Generated from com.diabolicallabs.process.manager.service.ProcessService
module Kieprocess
  class ProcessService
    # @private
    # @param j_del [::Kieprocess::ProcessService] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::Kieprocess::ProcessService] the underlying java delegate
    def j_del
      @j_del
    end
    # @param [::Vertx::Vertx] vertx 
    # @param [String] address 
    # @return [::Kieprocess::ProcessService]
    def self.create_proxy(vertx=nil,address=nil)
      if vertx.class.method_defined?(:j_del) && address.class == String && !block_given?
        return ::Vertx::Util::Utils.safe_create(Java::ComDiabolicallabsProcessManagerService::ProcessService.java_method(:createProxy, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class]).call(vertx.j_del,address),::Kieprocess::ProcessService)
      end
      raise ArgumentError, "Invalid arguments when calling create_proxy(vertx,address)"
    end
    # @param [Fixnum] processInstanceId 
    # @yield 
    # @return [self]
    def abort(processInstanceId=nil)
      if processInstanceId.class == Fixnum && block_given?
        @j_del.java_method(:abort, [Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(processInstanceId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling abort(processInstanceId)"
    end
    # @param [String] processId 
    # @yield 
    # @return [self]
    def create(processId=nil)
      if processId.class == String && block_given?
        @j_del.java_method(:create, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(processId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ::Vertx::Util::Utils.safe_create(ar.result,::Kieprocess::ProcessInstanceService) : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling create(processId)"
    end
    # @param [String] processId 
    # @param [Hash{String => Object}] variables 
    # @yield 
    # @return [self]
    def create_with_variables(processId=nil,variables=nil)
      if processId.class == String && variables.class == Hash && block_given?
        @j_del.java_method(:createWithVariables, [Java::java.lang.String.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(processId,::Vertx::Util::Utils.to_json_object(variables),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ::Vertx::Util::Utils.safe_create(ar.result,::Kieprocess::ProcessInstanceService) : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling create_with_variables(processId,variables)"
    end
    # @param [String] eventName 
    # @param [Hash{String => Object}] data 
    # @yield 
    # @return [self]
    def signal_event(eventName=nil,data=nil)
      if eventName.class == String && data.class == Hash && block_given?
        @j_del.java_method(:signalEvent, [Java::java.lang.String.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(eventName,::Vertx::Util::Utils.to_json_object(data),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling signal_event(eventName,data)"
    end
    # @param [String] eventName 
    # @param [Fixnum] processInstanceId 
    # @param [Hash{String => Object}] data 
    # @yield 
    # @return [self]
    def signal_event_for_process(eventName=nil,processInstanceId=nil,data=nil)
      if eventName.class == String && processInstanceId.class == Fixnum && data.class == Hash && block_given?
        @j_del.java_method(:signalEventForProcess, [Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(eventName,processInstanceId,::Vertx::Util::Utils.to_json_object(data),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling signal_event_for_process(eventName,processInstanceId,data)"
    end
    # @param [String] processId 
    # @yield 
    # @return [self]
    def start_process(processId=nil)
      if processId.class == String && block_given?
        @j_del.java_method(:startProcess, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(processId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ::Vertx::Util::Utils.safe_create(ar.result,::Kieprocess::ProcessInstanceService) : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling start_process(processId)"
    end
    # @param [String] processId 
    # @param [Hash{String => Object}] jsonObject 
    # @yield 
    # @return [self]
    def start_process_with_variables(processId=nil,jsonObject=nil)
      if processId.class == String && jsonObject.class == Hash && block_given?
        @j_del.java_method(:startProcessWithVariables, [Java::java.lang.String.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(processId,::Vertx::Util::Utils.to_json_object(jsonObject),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ::Vertx::Util::Utils.safe_create(ar.result,::Kieprocess::ProcessInstanceService) : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling start_process_with_variables(processId,jsonObject)"
    end
  end
end
