package com.diabolicallabs.process.manager;


import com.diabolicallabs.process.manager.service.KnowledgeServiceFactory;
import com.diabolicallabs.process.manager.service.KnowledgeServiceFactoryImpl;
import io.vertx.core.Future;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.serviceproxy.ProxyHelper;

public class Verticle extends AbstractVerticle {
  @Override
  public void start(Future<Void> startFuture) throws Exception {


    KnowledgeServiceFactoryImpl serviceImpl = new KnowledgeServiceFactoryImpl(getVertx());
    ProxyHelper.registerService(KnowledgeServiceFactory.class, getVertx(), serviceImpl, KnowledgeServiceFactory.DEFAULT_ADDRESS);

/*
    KieHelper kieHelper = new KieHelper();
    KieBase kieBase = kieHelper
      .addResource(ResourceFactory.newClassPathResource("org.jbpm.KieServerClientTest.v1.0.bpmn2"))
      .addResource(ResourceFactory.newClassPathResource("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2"))
      .build();

    KieSession ksession = kieBase.newKieSession();
    ProcessInstance processInstance = ksession.startProcess("VertxKieServerClientTest.KieServerClientTest");

    RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get()
      .newDefaultInMemoryBuilder().addEnvironmentEntry("IS_JTA_TRANSACTION", false);
    RuntimeManager manager = RuntimeManagerFactory.Factory.get().newPerProcessInstanceRuntimeManager(builder.get(), "com.diabolicallabs:VertxKieServerClientTest:1.0");

    RuntimeEngine runtime = manager.getRuntimeEngine(EmptyContext.get());

    ksession = runtime.getKieSession();
    ksession.getKieBase().getProcesses().stream().forEach(process -> { System.out.println(process.getId());});

    processInstance = ksession.startProcess("VertxKieServerClientTest.KieServerClientTest");
*/
    startFuture.complete();
  }
}
