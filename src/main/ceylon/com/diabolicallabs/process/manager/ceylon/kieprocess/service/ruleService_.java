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
@Name("ruleService")
@com.redhat.ceylon.compiler.java.metadata.Object
public class ruleService_ implements ReifiedType {

  private static final ruleService_ instance = new ruleService_();
  public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(ruleService_.class);

  @Ignore
  public TypeDescriptor $getType$() {
    return $TypeDescriptor$;
  }

  @Ignore
  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::ruleService")
  public static ruleService_ get_() {
    return instance;
  }


  @TypeInfo("com.diabolicallabs.process.manager.ceylon.kieprocess.service::RuleService")
  public RuleService createProxy(
    final @TypeInfo("io.vertx.ceylon.core::Vertx") @Name("vertx")  Vertx vertx, 
    final @TypeInfo("ceylon.language::String") @Name("address")  ceylon.language.String address) {
    io.vertx.core.Vertx arg_0 = io.vertx.ceylon.core.Vertx.TO_JAVA.safeConvert(vertx);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(address);
    RuleService ret = com.diabolicallabs.process.manager.ceylon.kieprocess.service.RuleService.TO_CEYLON.converter().safeConvert(com.diabolicallabs.process.manager.service.RuleService.createProxy(arg_0, arg_1));
    return ret;
  }

}
