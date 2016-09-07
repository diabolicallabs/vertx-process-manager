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
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Ceylon(major = 8)
public class RuleService implements ReifiedType {

  @Ignore
  public static final io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.process.manager.service.RuleService, RuleService> TO_CEYLON = new io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.process.manager.service.RuleService, RuleService>() {
    public io.vertx.lang.ceylon.Converter<com.diabolicallabs.process.manager.service.RuleService, RuleService> converter(final TypeDescriptor... descriptors) {
      return new io.vertx.lang.ceylon.Converter<com.diabolicallabs.process.manager.service.RuleService, RuleService>() {
        public RuleService convert(com.diabolicallabs.process.manager.service.RuleService src) {
          return new RuleService(src);
        }
      };
    }
  };

  @Ignore
  public static final io.vertx.lang.ceylon.Converter<RuleService, com.diabolicallabs.process.manager.service.RuleService> TO_JAVA = new io.vertx.lang.ceylon.Converter<RuleService, com.diabolicallabs.process.manager.service.RuleService>() {
    public com.diabolicallabs.process.manager.service.RuleService convert(RuleService src) {
      return src.delegate;
    }
  };

  @Ignore public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(RuleService.class);
  @Ignore private final com.diabolicallabs.process.manager.service.RuleService delegate;

  public RuleService(com.diabolicallabs.process.manager.service.RuleService delegate) {
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

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::RuleService")
  public RuleService delete(
    final @TypeInfo("ceylon.language::String") @Name("factHandle")  ceylon.language.String factHandle, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(factHandle);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    RuleService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.RuleService.TO_CEYLON.converter().safeConvert(delegate.delete(arg_0, arg_1));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::RuleService")
  public RuleService fireAllRules(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Integer)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Integer>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Integer>(handler) {
      public Object toCeylon(java.lang.Integer event) {
        return io.vertx.lang.ceylon.ToCeylon.Integer.safeConvert(event);
      }
    };
    RuleService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.RuleService.TO_CEYLON.converter().safeConvert(delegate.fireAllRules(arg_0));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::RuleService")
  public RuleService getQueryResults(
    final @TypeInfo("ceylon.language::String") @Name("queryName")  ceylon.language.String queryName, 
    final @TypeInfo("ceylon.language::String") @Name("resultName")  ceylon.language.String resultName, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.json::Array)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(queryName);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(resultName);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.json.JsonArray>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.json.JsonArray>(handler) {
      public Object toCeylon(io.vertx.core.json.JsonArray event) {
        return io.vertx.lang.ceylon.ToCeylon.JsonArray.safeConvert(event);
      }
    };
    RuleService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.RuleService.TO_CEYLON.converter().safeConvert(delegate.getQueryResults(arg_0, arg_1, arg_2));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::RuleService")
  public RuleService insert(
    final @TypeInfo("ceylon.language::String") @Name("packageName")  ceylon.language.String packageName, 
    final @TypeInfo("ceylon.language::String") @Name("typeName")  ceylon.language.String typeName, 
    final @TypeInfo("ceylon.json::Object") @Name("attributes")  ceylon.json.Object attributes, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::String)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(packageName);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(typeName);
    io.vertx.core.json.JsonObject arg_2 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(attributes);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.String>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.String>(handler) {
      public Object toCeylon(java.lang.String event) {
        return io.vertx.lang.ceylon.ToCeylon.String.safeConvert(event);
      }
    };
    RuleService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.RuleService.TO_CEYLON.converter().safeConvert(delegate.insert(arg_0, arg_1, arg_2, arg_3));
    return this;
  }

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::RuleService")
  public RuleService update(
    final @TypeInfo("ceylon.language::String") @Name("factHandle")  ceylon.language.String factHandle, 
    final @TypeInfo("ceylon.language::String") @Name("factType")  ceylon.language.String factType, 
    final @TypeInfo("ceylon.json::Object") @Name("attributes")  ceylon.json.Object attributes, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(factHandle);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(factType);
    io.vertx.core.json.JsonObject arg_2 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(attributes);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    RuleService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.RuleService.TO_CEYLON.converter().safeConvert(delegate.update(arg_0, arg_1, arg_2, arg_3));
    return this;
  }

}
