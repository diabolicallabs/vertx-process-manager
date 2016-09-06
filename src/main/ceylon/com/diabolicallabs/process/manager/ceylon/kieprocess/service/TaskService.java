package com.diabolicallabs.process.manager.ceylon.kieprocess.service;

import com.redhat.ceylon.compiler.java.metadata.Ceylon;
import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.metadata.TypeParameter;
import com.redhat.ceylon.compiler.java.metadata.TypeParameters;
import com.redhat.ceylon.compiler.java.metadata.Variance;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.metadata.Name;
import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;
import com.redhat.ceylon.compiler.java.runtime.model.ReifiedType;
import ceylon.language.Callable;
import ceylon.language.DocAnnotation$annotation$;
import io.vertx.ceylon.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Ceylon(major = 8)
public class TaskService implements ReifiedType {

  @Ignore
  public static final io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.process.manager.service.TaskService, TaskService> TO_CEYLON = new io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.process.manager.service.TaskService, TaskService>() {
    public io.vertx.lang.ceylon.Converter<com.diabolicallabs.process.manager.service.TaskService, TaskService> converter(final TypeDescriptor... descriptors) {
      return new io.vertx.lang.ceylon.Converter<com.diabolicallabs.process.manager.service.TaskService, TaskService>() {
        public TaskService convert(com.diabolicallabs.process.manager.service.TaskService src) {
          return new TaskService(src);
        }
      };
    }
  };

  @Ignore
  public static final io.vertx.lang.ceylon.Converter<TaskService, com.diabolicallabs.process.manager.service.TaskService> TO_JAVA = new io.vertx.lang.ceylon.Converter<TaskService, com.diabolicallabs.process.manager.service.TaskService>() {
    public com.diabolicallabs.process.manager.service.TaskService convert(TaskService src) {
      return src.delegate;
    }
  };

  @Ignore public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(TaskService.class);
  @Ignore private final com.diabolicallabs.process.manager.service.TaskService delegate;

  public TaskService(com.diabolicallabs.process.manager.service.TaskService delegate) {
    this.delegate = delegate;
  }

  @Ignore 
  public TypeDescriptor $getType$() {
    return $TypeDescriptor$;
  }

  @Ignore
  public Object getDelegate() {
    return delegate;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService")
  public TaskService addComment(
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("userId")  ceylon.language.String userId, 
    final @TypeInfo("ceylon.language::String") @Name("comment")  ceylon.language.String comment, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(userId);
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(comment);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    TaskService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(delegate.addComment(arg_0, arg_1, arg_2, arg_3));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService")
  public TaskService claim(
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("userId")  ceylon.language.String userId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(userId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    TaskService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(delegate.claim(arg_0, arg_1, arg_2));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService")
  public TaskService complete(
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("userId")  ceylon.language.String userId, 
    final @TypeInfo("ceylon.json::Object") @Name("data")  ceylon.json.Object data, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(userId);
    io.vertx.core.json.JsonObject arg_2 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(data);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    TaskService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(delegate.complete(arg_0, arg_1, arg_2, arg_3));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService")
  public TaskService delegate(
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("userId")  ceylon.language.String userId, 
    final @TypeInfo("ceylon.language::String") @Name("newUserId")  ceylon.language.String newUserId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(userId);
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(newUserId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    TaskService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(delegate.delegate(arg_0, arg_1, arg_2, arg_3));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService")
  public TaskService exit(
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("userId")  ceylon.language.String userId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(userId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    TaskService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(delegate.exit(arg_0, arg_1, arg_2));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService")
  public TaskService fail(
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("userId")  ceylon.language.String userId, 
    final @TypeInfo("ceylon.json::Object") @Name("data")  ceylon.json.Object data, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(userId);
    io.vertx.core.json.JsonObject arg_2 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(data);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    TaskService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(delegate.fail(arg_0, arg_1, arg_2, arg_3));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService")
  public TaskService forward(
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("userId")  ceylon.language.String userId, 
    final @TypeInfo("ceylon.language::String") @Name("newUserId")  ceylon.language.String newUserId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(userId);
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(newUserId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    TaskService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(delegate.forward(arg_0, arg_1, arg_2, arg_3));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService")
  public TaskService getContent(
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.json::Object)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.json.JsonObject>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.json.JsonObject>(handler) {
      public Object toCeylon(io.vertx.core.json.JsonObject event) {
        return io.vertx.lang.ceylon.ToCeylon.JsonObject.safeConvert(event);
      }
    };
    TaskService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(delegate.getContent(arg_0, arg_1));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService")
  public TaskService release(
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("userId")  ceylon.language.String userId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(userId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    TaskService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(delegate.release(arg_0, arg_1, arg_2));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService")
  public TaskService resume(
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("userId")  ceylon.language.String userId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(userId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    TaskService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(delegate.resume(arg_0, arg_1, arg_2));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService")
  public TaskService skip(
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("userId")  ceylon.language.String userId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(userId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    TaskService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(delegate.skip(arg_0, arg_1, arg_2));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService")
  public TaskService start(
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("userId")  ceylon.language.String userId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(userId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    TaskService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(delegate.start(arg_0, arg_1, arg_2));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService")
  public TaskService stop(
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("userId")  ceylon.language.String userId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(userId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    TaskService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(delegate.stop(arg_0, arg_1, arg_2));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService")
  public TaskService suspend(
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("userId")  ceylon.language.String userId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(userId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    TaskService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(delegate.suspend(arg_0, arg_1, arg_2));
    return this;
  }

}
