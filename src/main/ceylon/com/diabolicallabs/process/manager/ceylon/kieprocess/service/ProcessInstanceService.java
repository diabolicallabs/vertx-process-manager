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
import com.diabolicallabs.process.manager.service.ProcessState;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Ceylon(major = 8)
public class ProcessInstanceService implements ReifiedType {

  @Ignore
  public static final io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.process.manager.service.ProcessInstanceService, ProcessInstanceService> TO_CEYLON = new io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.process.manager.service.ProcessInstanceService, ProcessInstanceService>() {
    public io.vertx.lang.ceylon.Converter<com.diabolicallabs.process.manager.service.ProcessInstanceService, ProcessInstanceService> converter(final TypeDescriptor... descriptors) {
      return new io.vertx.lang.ceylon.Converter<com.diabolicallabs.process.manager.service.ProcessInstanceService, ProcessInstanceService>() {
        public ProcessInstanceService convert(com.diabolicallabs.process.manager.service.ProcessInstanceService src) {
          return new ProcessInstanceService(src);
        }
      };
    }
  };

  @Ignore
  public static final io.vertx.lang.ceylon.Converter<ProcessInstanceService, com.diabolicallabs.process.manager.service.ProcessInstanceService> TO_JAVA = new io.vertx.lang.ceylon.Converter<ProcessInstanceService, com.diabolicallabs.process.manager.service.ProcessInstanceService>() {
    public com.diabolicallabs.process.manager.service.ProcessInstanceService convert(ProcessInstanceService src) {
      return src.delegate;
    }
  };

  @Ignore public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(ProcessInstanceService.class);
  @Ignore private final com.diabolicallabs.process.manager.service.ProcessInstanceService delegate;

  public ProcessInstanceService(com.diabolicallabs.process.manager.service.ProcessInstanceService delegate) {
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

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessInstanceService")
  public ProcessInstanceService abort(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    ProcessInstanceService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessInstanceService.TO_CEYLON.converter().safeConvert(delegate.abort(arg_0));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessInstanceService")
  public ProcessInstanceService start(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Integer)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Long>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Long>(handler) {
      public Object toCeylon(java.lang.Long event) {
        return io.vertx.lang.ceylon.ToCeylon.Long.safeConvert(event);
      }
    };
    ProcessInstanceService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessInstanceService.TO_CEYLON.converter().safeConvert(delegate.start(arg_0));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessInstanceService")
  public ProcessInstanceService getInstanceId(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Integer)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Long>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Long>(handler) {
      public Object toCeylon(java.lang.Long event) {
        return io.vertx.lang.ceylon.ToCeylon.Long.safeConvert(event);
      }
    };
    ProcessInstanceService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessInstanceService.TO_CEYLON.converter().safeConvert(delegate.getInstanceId(arg_0));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessInstanceService")
  public ProcessInstanceService getName(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::String)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.String>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.String>(handler) {
      public Object toCeylon(java.lang.String event) {
        return io.vertx.lang.ceylon.ToCeylon.String.safeConvert(event);
      }
    };
    ProcessInstanceService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessInstanceService.TO_CEYLON.converter().safeConvert(delegate.getName(arg_0));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessInstanceService")
  public ProcessInstanceService getParentInstanceId(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Integer)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Long>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Long>(handler) {
      public Object toCeylon(java.lang.Long event) {
        return io.vertx.lang.ceylon.ToCeylon.Long.safeConvert(event);
      }
    };
    ProcessInstanceService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessInstanceService.TO_CEYLON.converter().safeConvert(delegate.getParentInstanceId(arg_0));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessInstanceService")
  public ProcessInstanceService getState(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::String)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<com.diabolicallabs.process.manager.service.ProcessState>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<com.diabolicallabs.process.manager.service.ProcessState>(handler) {
      public Object toCeylon(com.diabolicallabs.process.manager.service.ProcessState event) {
        return io.vertx.lang.ceylon.ToCeylon.<com.diabolicallabs.process.manager.service.ProcessState>enumeration().safeConvert(event);
      }
    };
    ProcessInstanceService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessInstanceService.TO_CEYLON.converter().safeConvert(delegate.getState(arg_0));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessInstanceService")
  public ProcessInstanceService signalEvent(
    final @TypeInfo("ceylon.language::String") @Name("eventName")  ceylon.language.String eventName, 
    final @TypeInfo("ceylon.json::Object") @Name("data")  ceylon.json.Object data, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(eventName);
    io.vertx.core.json.JsonObject arg_1 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(data);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    ProcessInstanceService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessInstanceService.TO_CEYLON.converter().safeConvert(delegate.signalEvent(arg_0, arg_1, arg_2));
    return this;
  }

  @TypeInfo("ceylon.language::Anything")
  public void close() {
    delegate.close();
  }

}
