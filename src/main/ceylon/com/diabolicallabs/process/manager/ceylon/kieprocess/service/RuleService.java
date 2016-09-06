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
  public RuleService insert(
    final @TypeInfo("ceylon.json::Object") @Name("fact")  ceylon.json.Object fact, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.json.JsonObject arg_0 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(fact);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    RuleService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.RuleService.TO_CEYLON.converter().safeConvert(delegate.insert(arg_0, arg_1));
    return this;
  }

}
