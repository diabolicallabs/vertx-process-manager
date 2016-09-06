require 'kieprocess/knowledge_service'
require 'vertx/vertx'
require 'vertx/util/utils.rb'
# Generated from com.diabolicallabs.process.manager.service.KnowledgeServiceFactory
module Kieprocess
  class KnowledgeServiceFactory
    # @private
    # @param j_del [::Kieprocess::KnowledgeServiceFactory] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::Kieprocess::KnowledgeServiceFactory] the underlying java delegate
    def j_del
      @j_del
    end
    # @param [::Vertx::Vertx] vertx 
    # @param [String] address 
    # @return [::Kieprocess::KnowledgeServiceFactory]
    def self.create_proxy(vertx=nil,address=nil)
      if vertx.class.method_defined?(:j_del) && address.class == String && !block_given?
        return ::Vertx::Util::Utils.safe_create(Java::ComDiabolicallabsProcessManagerService::KnowledgeServiceFactory.java_method(:createProxy, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class]).call(vertx.j_del,address),::Kieprocess::KnowledgeServiceFactory)
      end
      raise ArgumentError, "Invalid arguments when calling create_proxy(vertx,address)"
    end
    # @yield 
    # @return [self]
    def get_knowledge_service
      if block_given?
        @j_del.java_method(:getKnowledgeService, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ::Vertx::Util::Utils.safe_create(ar.result,::Kieprocess::KnowledgeService) : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_knowledge_service()"
    end
  end
end
