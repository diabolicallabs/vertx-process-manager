require 'vertx/vertx'
require 'vertx/util/utils.rb'
# Generated from com.diabolicallabs.process.manager.service.TaskService
module Kieprocess
  class TaskService
    # @private
    # @param j_del [::Kieprocess::TaskService] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::Kieprocess::TaskService] the underlying java delegate
    def j_del
      @j_del
    end
    # @param [::Vertx::Vertx] vertx 
    # @param [String] address 
    # @return [::Kieprocess::TaskService]
    def self.create_proxy(vertx=nil,address=nil)
      if vertx.class.method_defined?(:j_del) && address.class == String && !block_given?
        return ::Vertx::Util::Utils.safe_create(Java::ComDiabolicallabsProcessManagerService::TaskService.java_method(:createProxy, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class]).call(vertx.j_del,address),::Kieprocess::TaskService)
      end
      raise ArgumentError, "Invalid arguments when calling create_proxy(vertx,address)"
    end
    # @param [Fixnum] taskId 
    # @param [String] userId 
    # @param [String] comment 
    # @yield 
    # @return [self]
    def add_comment(taskId=nil,userId=nil,comment=nil)
      if taskId.class == Fixnum && userId.class == String && comment.class == String && block_given?
        @j_del.java_method(:addComment, [Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(taskId,userId,comment,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling add_comment(taskId,userId,comment)"
    end
    # @param [Fixnum] taskId 
    # @param [String] userId 
    # @yield 
    # @return [self]
    def claim(taskId=nil,userId=nil)
      if taskId.class == Fixnum && userId.class == String && block_given?
        @j_del.java_method(:claim, [Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(taskId,userId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling claim(taskId,userId)"
    end
    # @param [Fixnum] taskId 
    # @param [String] userId 
    # @param [Hash{String => Object}] data 
    # @yield 
    # @return [self]
    def complete(taskId=nil,userId=nil,data=nil)
      if taskId.class == Fixnum && userId.class == String && data.class == Hash && block_given?
        @j_del.java_method(:complete, [Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(taskId,userId,::Vertx::Util::Utils.to_json_object(data),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling complete(taskId,userId,data)"
    end
    # @param [Fixnum] taskId 
    # @param [String] userId 
    # @param [String] newUserId 
    # @yield 
    # @return [self]
    def delegate(taskId=nil,userId=nil,newUserId=nil)
      if taskId.class == Fixnum && userId.class == String && newUserId.class == String && block_given?
        @j_del.java_method(:delegate, [Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(taskId,userId,newUserId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling delegate(taskId,userId,newUserId)"
    end
    # @param [Fixnum] taskId 
    # @param [String] userId 
    # @yield 
    # @return [self]
    def exit(taskId=nil,userId=nil)
      if taskId.class == Fixnum && userId.class == String && block_given?
        @j_del.java_method(:exit, [Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(taskId,userId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling exit(taskId,userId)"
    end
    # @param [Fixnum] taskId 
    # @param [String] userId 
    # @param [Hash{String => Object}] data 
    # @yield 
    # @return [self]
    def fail(taskId=nil,userId=nil,data=nil)
      if taskId.class == Fixnum && userId.class == String && data.class == Hash && block_given?
        @j_del.java_method(:fail, [Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(taskId,userId,::Vertx::Util::Utils.to_json_object(data),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling fail(taskId,userId,data)"
    end
    # @param [Fixnum] taskId 
    # @param [String] userId 
    # @param [String] newUserId 
    # @yield 
    # @return [self]
    def forward(taskId=nil,userId=nil,newUserId=nil)
      if taskId.class == Fixnum && userId.class == String && newUserId.class == String && block_given?
        @j_del.java_method(:forward, [Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(taskId,userId,newUserId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling forward(taskId,userId,newUserId)"
    end
    # @param [Fixnum] taskId 
    # @yield 
    # @return [self]
    def get_content(taskId=nil)
      if taskId.class == Fixnum && block_given?
        @j_del.java_method(:getContent, [Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(taskId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.encode) : nil : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_content(taskId)"
    end
    # @param [Fixnum] taskId 
    # @param [String] userId 
    # @yield 
    # @return [self]
    def release(taskId=nil,userId=nil)
      if taskId.class == Fixnum && userId.class == String && block_given?
        @j_del.java_method(:release, [Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(taskId,userId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling release(taskId,userId)"
    end
    # @param [Fixnum] taskId 
    # @param [String] userId 
    # @yield 
    # @return [self]
    def resume(taskId=nil,userId=nil)
      if taskId.class == Fixnum && userId.class == String && block_given?
        @j_del.java_method(:resume, [Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(taskId,userId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling resume(taskId,userId)"
    end
    # @param [Fixnum] taskId 
    # @param [String] userId 
    # @yield 
    # @return [self]
    def skip(taskId=nil,userId=nil)
      if taskId.class == Fixnum && userId.class == String && block_given?
        @j_del.java_method(:skip, [Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(taskId,userId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling skip(taskId,userId)"
    end
    # @param [Fixnum] taskId 
    # @param [String] userId 
    # @yield 
    # @return [self]
    def start(taskId=nil,userId=nil)
      if taskId.class == Fixnum && userId.class == String && block_given?
        @j_del.java_method(:start, [Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(taskId,userId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling start(taskId,userId)"
    end
    # @param [Fixnum] taskId 
    # @param [String] userId 
    # @yield 
    # @return [self]
    def stop(taskId=nil,userId=nil)
      if taskId.class == Fixnum && userId.class == String && block_given?
        @j_del.java_method(:stop, [Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(taskId,userId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling stop(taskId,userId)"
    end
    # @param [Fixnum] taskId 
    # @param [String] userId 
    # @yield 
    # @return [self]
    def suspend(taskId=nil,userId=nil)
      if taskId.class == Fixnum && userId.class == String && block_given?
        @j_del.java_method(:suspend, [Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(taskId,userId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling suspend(taskId,userId)"
    end
  end
end
