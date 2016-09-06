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
public class ProcessService implements ReifiedType {

  @Ignore
  public static final io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.process.manager.service.ProcessService, ProcessService> TO_CEYLON = new io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.process.manager.service.ProcessService, ProcessService>() {
    public io.vertx.lang.ceylon.Converter<com.diabolicallabs.process.manager.service.ProcessService, ProcessService> converter(final TypeDescriptor... descriptors) {
      return new io.vertx.lang.ceylon.Converter<com.diabolicallabs.process.manager.service.ProcessService, ProcessService>() {
        public ProcessService convert(com.diabolicallabs.process.manager.service.ProcessService src) {
          return new ProcessService(src);
        }
      };
    }
  };

  @Ignore
  public static final io.vertx.lang.ceylon.Converter<ProcessService, com.diabolicallabs.process.manager.service.ProcessService> TO_JAVA = new io.vertx.lang.ceylon.Converter<ProcessService, com.diabolicallabs.process.manager.service.ProcessService>() {
    public com.diabolicallabs.process.manager.service.ProcessService convert(ProcessService src) {
      return src.delegate;
    }
  };

  @Ignore public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(ProcessService.class);
  @Ignore private final com.diabolicallabs.process.manager.service.ProcessService delegate;

  public ProcessService(com.diabolicallabs.process.manager.service.ProcessService delegate) {
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

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessService")
  public ProcessService abort(
    final @TypeInfo("ceylon.language::Integer") @Name("processInstanceId")  ceylon.language.Integer processInstanceId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.Long arg_0 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(processInstanceId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    ProcessService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessService.TO_CEYLON.converter().safeConvert(delegate.abort(arg_0, arg_1));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessService")
  public ProcessService create(
    final @TypeInfo("ceylon.language::String") @Name("processId")  ceylon.language.String processId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessInstanceService)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(processId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<com.diabolicallabs.process.manager.service.ProcessInstanceService>(handler) {
      public Object toCeylon(com.diabolicallabs.process.manager.service.ProcessInstanceService event) {
        return com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessInstanceService.TO_CEYLON.converter().safeConvert(event);
      }
    };
    ProcessService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessService.TO_CEYLON.converter().safeConvert(delegate.create(arg_0, arg_1));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessService")
  public ProcessService createWithVariables(
    final @TypeInfo("ceylon.language::String") @Name("processId")  ceylon.language.String processId, 
    final @TypeInfo("ceylon.json::Object") @Name("variables")  ceylon.json.Object variables, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessInstanceService)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(processId);
    io.vertx.core.json.JsonObject arg_1 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(variables);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<com.diabolicallabs.process.manager.service.ProcessInstanceService>(handler) {
      public Object toCeylon(com.diabolicallabs.process.manager.service.ProcessInstanceService event) {
        return com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessInstanceService.TO_CEYLON.converter().safeConvert(event);
      }
    };
    ProcessService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessService.TO_CEYLON.converter().safeConvert(delegate.createWithVariables(arg_0, arg_1, arg_2));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessService")
  public ProcessService signalEvent(
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
    ProcessService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessService.TO_CEYLON.converter().safeConvert(delegate.signalEvent(arg_0, arg_1, arg_2));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessService")
  public ProcessService signalEventForProcess(
    final @TypeInfo("ceylon.language::String") @Name("eventName")  ceylon.language.String eventName, 
    final @TypeInfo("ceylon.language::Integer") @Name("processInstanceId")  ceylon.language.Integer processInstanceId, 
    final @TypeInfo("ceylon.json::Object") @Name("data")  ceylon.json.Object data, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(eventName);
    java.lang.Long arg_1 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(processInstanceId);
    io.vertx.core.json.JsonObject arg_2 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(data);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    ProcessService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessService.TO_CEYLON.converter().safeConvert(delegate.signalEventForProcess(arg_0, arg_1, arg_2, arg_3));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessService")
  public ProcessService startProcess(
    final @TypeInfo("ceylon.language::String") @Name("processId")  ceylon.language.String processId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessInstanceService)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(processId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<com.diabolicallabs.process.manager.service.ProcessInstanceService>(handler) {
      public Object toCeylon(com.diabolicallabs.process.manager.service.ProcessInstanceService event) {
        return com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessInstanceService.TO_CEYLON.converter().safeConvert(event);
      }
    };
    ProcessService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessService.TO_CEYLON.converter().safeConvert(delegate.startProcess(arg_0, arg_1));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessService")
  public ProcessService startProcessWithVariables(
    final @TypeInfo("ceylon.language::String") @Name("processId")  ceylon.language.String processId, 
    final @TypeInfo("ceylon.json::Object") @Name("jsonObject")  ceylon.json.Object jsonObject, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessInstanceService)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(processId);
    io.vertx.core.json.JsonObject arg_1 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(jsonObject);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<com.diabolicallabs.process.manager.service.ProcessInstanceService>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<com.diabolicallabs.process.manager.service.ProcessInstanceService>(handler) {
      public Object toCeylon(com.diabolicallabs.process.manager.service.ProcessInstanceService event) {
        return com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessInstanceService.TO_CEYLON.converter().safeConvert(event);
      }
    };
    ProcessService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessService.TO_CEYLON.converter().safeConvert(delegate.startProcessWithVariables(arg_0, arg_1, arg_2));
    return this;
  }

}
