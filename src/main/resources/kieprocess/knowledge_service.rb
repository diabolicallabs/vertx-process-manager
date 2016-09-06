require 'kieprocess/rule_service'
require 'kieprocess/process_service'
require 'vertx/vertx'
require 'kieprocess/task_service'
require 'vertx/util/utils.rb'
# Generated from com.diabolicallabs.process.manager.service.KnowledgeService
module Kieprocess
  class KnowledgeService
    # @private
    # @param j_del [::Kieprocess::KnowledgeService] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::Kieprocess::KnowledgeService] the underlying java delegate
    def j_del
      @j_del
    end
    # @param [::Vertx::Vertx] vertx 
    # @param [String] address 
    # @return [::Kieprocess::KnowledgeService]
    def self.create_proxy(vertx=nil,address=nil)
      if vertx.class.method_defined?(:j_del) && address.class == String && !block_given?
        return ::Vertx::Util::Utils.safe_create(Java::ComDiabolicallabsProcessManagerService::KnowledgeService.java_method(:createProxy, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class]).call(vertx.j_del,address),::Kieprocess::KnowledgeService)
      end
      raise ArgumentError, "Invalid arguments when calling create_proxy(vertx,address)"
    end
    # @param [String] resourceName 
    # @yield 
    # @return [self]
    def add_class_path_resource(resourceName=nil)
      if resourceName.class == String && block_given?
        @j_del.java_method(:addClassPathResource, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(resourceName,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling add_class_path_resource(resourceName)"
    end
    # @param [String] fileName 
    # @yield 
    # @return [self]
    def add_file_resource(fileName=nil)
      if fileName.class == String && block_given?
        @j_del.java_method(:addFileResource, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(fileName,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling add_file_resource(fileName)"
    end
    # @yield 
    # @return [self]
    def process_definitions
      if block_given?
        @j_del.java_method(:processDefinitions, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.encode) : nil : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling process_definitions()"
    end
    # @yield 
    # @return [self]
    def get_process_service
      if block_given?
        @j_del.java_method(:getProcessService, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ::Vertx::Util::Utils.safe_create(ar.result,::Kieprocess::ProcessService) : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_process_service()"
    end
    # @yield 
    # @return [self]
    def get_rule_service
      if block_given?
        @j_del.java_method(:getRuleService, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ::Vertx::Util::Utils.safe_create(ar.result,::Kieprocess::RuleService) : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_rule_service()"
    end
    # @yield 
    # @return [self]
    def get_task_service
      if block_given?
        @j_del.java_method(:getTaskService, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ::Vertx::Util::Utils.safe_create(ar.result,::Kieprocess::TaskService) : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_task_service()"
    end
    # @yield 
    # @return [self]
    def get_task_service_address
      if block_given?
        @j_del.java_method(:getTaskServiceAddress, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_task_service_address()"
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
