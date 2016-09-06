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
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Ceylon(major = 8)
public class KnowledgeServiceFactory implements ReifiedType {

  @Ignore
  public static final io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.process.manager.service.KnowledgeServiceFactory, KnowledgeServiceFactory> TO_CEYLON = new io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.process.manager.service.KnowledgeServiceFactory, KnowledgeServiceFactory>() {
    public io.vertx.lang.ceylon.Converter<com.diabolicallabs.process.manager.service.KnowledgeServiceFactory, KnowledgeServiceFactory> converter(final TypeDescriptor... descriptors) {
      return new io.vertx.lang.ceylon.Converter<com.diabolicallabs.process.manager.service.KnowledgeServiceFactory, KnowledgeServiceFactory>() {
        public KnowledgeServiceFactory convert(com.diabolicallabs.process.manager.service.KnowledgeServiceFactory src) {
          return new KnowledgeServiceFactory(src);
        }
      };
    }
  };

  @Ignore
  public static final io.vertx.lang.ceylon.Converter<KnowledgeServiceFactory, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory> TO_JAVA = new io.vertx.lang.ceylon.Converter<KnowledgeServiceFactory, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory>() {
    public com.diabolicallabs.process.manager.service.KnowledgeServiceFactory convert(KnowledgeServiceFactory src) {
      return src.delegate;
    }
  };

  @Ignore public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(KnowledgeServiceFactory.class);
  @Ignore private final com.diabolicallabs.process.manager.service.KnowledgeServiceFactory delegate;

  public KnowledgeServiceFactory(com.diabolicallabs.process.manager.service.KnowledgeServiceFactory delegate) {
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

  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::KnowledgeServiceFactory")
  public KnowledgeServiceFactory getKnowledgeService(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|com.diabolicallabs.process.manager.ceylon.kieprocess.service::KnowledgeService)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<com.diabolicallabs.process.manager.service.KnowledgeService>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<com.diabolicallabs.process.manager.service.KnowledgeService>(handler) {
      public Object toCeylon(com.diabolicallabs.process.manager.service.KnowledgeService event) {
        return com.diabolicallabs.process.manager.ceylon.kieprocess.service.KnowledgeService.TO_CEYLON.converter().safeConvert(event);
      }
    };
    KnowledgeServiceFactory ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.KnowledgeServiceFactory.TO_CEYLON.converter().safeConvert(delegate.getKnowledgeService(arg_0));
    return this;
  }

}
