require 'vertx/vertx'
require 'vertx/util/utils.rb'
# Generated from com.diabolicallabs.process.manager.service.ProcessInstanceService
module Kieprocess
  class ProcessInstanceService
    # @private
    # @param j_del [::Kieprocess::ProcessInstanceService] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::Kieprocess::ProcessInstanceService] the underlying java delegate
    def j_del
      @j_del
    end
    # @param [::Vertx::Vertx] vertx 
    # @param [String] address 
    # @return [::Kieprocess::ProcessInstanceService]
    def self.create_proxy(vertx=nil,address=nil)
      if vertx.class.method_defined?(:j_del) && address.class == String && !block_given?
        return ::Vertx::Util::Utils.safe_create(Java::ComDiabolicallabsProcessManagerService::ProcessInstanceService.java_method(:createProxy, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class]).call(vertx.j_del,address),::Kieprocess::ProcessInstanceService)
      end
      raise ArgumentError, "Invalid arguments when calling create_proxy(vertx,address)"
    end
    # @yield 
    # @return [self]
    def abort
      if block_given?
        @j_del.java_method(:abort, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling abort()"
    end
    # @yield 
    # @return [self]
    def start
      if block_given?
        @j_del.java_method(:start, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling start()"
    end
    # @yield 
    # @return [self]
    def get_instance_id
      if block_given?
        @j_del.java_method(:getInstanceId, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_instance_id()"
    end
    # @yield 
    # @return [self]
    def get_name
      if block_given?
        @j_del.java_method(:getName, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_name()"
    end
    # @yield 
    # @return [self]
    def get_parent_instance_id
      if block_given?
        @j_del.java_method(:getParentInstanceId, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_parent_instance_id()"
    end
    # @yield 
    # @return [self]
    def get_state
      if block_given?
        @j_del.java_method(:getState, [Java::IoVertxCore::Handler.java_class]).call(nil)
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_state()"
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
    # @return [void]
    def close
      if !block_given?
        return @j_del.java_method(:close, []).call()
      end
      raise ArgumentError, "Invalid arguments when calling close()"
    end
  end
end
