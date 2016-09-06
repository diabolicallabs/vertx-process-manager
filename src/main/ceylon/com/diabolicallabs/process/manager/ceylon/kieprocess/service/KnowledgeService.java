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
import io.vertx.core.json.JsonArray;
import io.vertx.ceylon.core.Vertx;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Ceylon(major = 8)
public class KnowledgeService implements ReifiedType {

  @Ignore
  public static final io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.process.manager.service.KnowledgeService, KnowledgeService> TO_CEYLON = new io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.process.manager.service.KnowledgeService, KnowledgeService>() {
    public io.vertx.lang.ceylon.Converter<com.diabolicallabs.process.manager.service.KnowledgeService, KnowledgeService> converter(final TypeDescriptor... descriptors) {
      return new io.vertx.lang.ceylon.Converter<com.diabolicallabs.process.manager.service.KnowledgeService, KnowledgeService>() {
        public KnowledgeService convert(com.diabolicallabs.process.manager.service.KnowledgeService src) {
          return new KnowledgeService(src);
        }
      };
    }
  };

  @Ignore
  public static final io.vertx.lang.ceylon.Converter<KnowledgeService, com.diabolicallabs.process.manager.service.KnowledgeService> TO_JAVA = new io.vertx.lang.ceylon.Converter<KnowledgeService, com.diabolicallabs.process.manager.service.KnowledgeService>() {
    public com.diabolicallabs.process.manager.service.KnowledgeService convert(KnowledgeService src) {
      return src.delegate;
    }
  };

  @Ignore public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(KnowledgeService.class);
  @Ignore private final com.diabolicallabs.process.manager.service.KnowledgeService delegate;

  public KnowledgeService(com.diabolicallabs.process.manager.service.KnowledgeService delegate) {
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

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::KnowledgeService")
  public KnowledgeService addClassPathResource(
    final @TypeInfo("ceylon.language::String") @Name("resourceName")  ceylon.language.String resourceName, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(resourceName);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    KnowledgeService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.KnowledgeService.TO_CEYLON.converter().safeConvert(delegate.addClassPathResource(arg_0, arg_1));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::KnowledgeService")
  public KnowledgeService addFileResource(
    final @TypeInfo("ceylon.language::String") @Name("fileName")  ceylon.language.String fileName, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(fileName);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    KnowledgeService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.KnowledgeService.TO_CEYLON.converter().safeConvert(delegate.addFileResource(arg_0, arg_1));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::KnowledgeService")
  public KnowledgeService processDefinitions(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.json::Array)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.json.JsonArray>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.json.JsonArray>(handler) {
      public Object toCeylon(io.vertx.core.json.JsonArray event) {
        return io.vertx.lang.ceylon.ToCeylon.JsonArray.safeConvert(event);
      }
    };
    KnowledgeService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.KnowledgeService.TO_CEYLON.converter().safeConvert(delegate.processDefinitions(arg_0));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::KnowledgeService")
  public KnowledgeService getProcessService(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|com.diabolicallabs.process.manager.ceylon.kieprocess.service::ProcessService)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<com.diabolicallabs.process.manager.service.ProcessService>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<com.diabolicallabs.process.manager.service.ProcessService>(handler) {
      public Object toCeylon(com.diabolicallabs.process.manager.service.ProcessService event) {
        return com.diabolicallabs.process.manager.ceylon.kieprocess.service.ProcessService.TO_CEYLON.converter().safeConvert(event);
      }
    };
    KnowledgeService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.KnowledgeService.TO_CEYLON.converter().safeConvert(delegate.getProcessService(arg_0));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::KnowledgeService")
  public KnowledgeService getRuleService(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|com.diabolicallabs.process.manager.ceylon.kieprocess.service::RuleService)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<com.diabolicallabs.process.manager.service.RuleService>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<com.diabolicallabs.process.manager.service.RuleService>(handler) {
      public Object toCeylon(com.diabolicallabs.process.manager.service.RuleService event) {
        return com.diabolicallabs.process.manager.ceylon.kieprocess.service.RuleService.TO_CEYLON.converter().safeConvert(event);
      }
    };
    KnowledgeService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.KnowledgeService.TO_CEYLON.converter().safeConvert(delegate.getRuleService(arg_0));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::KnowledgeService")
  public KnowledgeService getTaskService(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|com.diabolicallabs.process.manager.ceylon.kieprocess.service::TaskService)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<com.diabolicallabs.process.manager.service.TaskService>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<com.diabolicallabs.process.manager.service.TaskService>(handler) {
      public Object toCeylon(com.diabolicallabs.process.manager.service.TaskService event) {
        return com.diabolicallabs.process.manager.ceylon.kieprocess.service.TaskService.TO_CEYLON.converter().safeConvert(event);
      }
    };
    KnowledgeService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.KnowledgeService.TO_CEYLON.converter().safeConvert(delegate.getTaskService(arg_0));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::KnowledgeService")
  public KnowledgeService getTaskServiceAddress(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::String)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.String>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.String>(handler) {
      public Object toCeylon(java.lang.String event) {
        return io.vertx.lang.ceylon.ToCeylon.String.safeConvert(event);
      }
    };
    KnowledgeService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.KnowledgeService.TO_CEYLON.converter().safeConvert(delegate.getTaskServiceAddress(arg_0));
    return this;
  }

  @TypeInfo("ceylon.language::Anything")
  public void close() {
    delegate.close();
  }

}
