package com.diabolicallabs.test.process.manager;

import com.diabolicallabs.process.manager.Verticle;
import com.diabolicallabs.process.manager.reactivex.service.KnowledgeServiceFactory;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.reactivex.core.Vertx;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(io.vertx.ext.unit.junit.VertxUnitRunner.class)
public class KnowledgeServiceTest {

  Logger logger = LoggerFactory.getLogger(KnowledgeServiceTest.class);

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void before(TestContext context) {

    rule.vertx().deployVerticle(Verticle.class.getName(), context.asyncAssertSuccess());

  }

  @Test
  public void testGetProcessDefinitions(TestContext context) {

    Async async = context.async();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Single.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::rxGetKnowledgeService)
      .flatMap(service -> {
        return service.rxAddClassPathResource("org.jbpm.KieServerClientTest.v1.0.bpmn2")
            .toSingleDefault(service.rxProcessDefinitions());
      })
      .doOnSuccess(definitions -> {
        System.out.println(definitions.blockingGet().encodePrettily());
      })
      .subscribe(
          definitions -> {
            context.assertTrue(definitions.blockingGet().size() > 0);
            async.complete();
          },
        context::fail
      );
  }

  @Test
  public void testAddKnowledgeResource(TestContext context) {

    Async async = context.async();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Single.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::rxGetKnowledgeService)
      .flatMap(service -> {
        return service.rxAddClassPathResource("org.jbpm.KieServerClientTest.v1.0.bpmn2")
            .toSingleDefault(service);
      })
      .subscribe(
          service -> {
            context.assertNotNull(service);
            async.complete();
          },
        context::fail
      );
  }
}
