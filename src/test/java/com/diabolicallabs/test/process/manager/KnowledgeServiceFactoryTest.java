package com.diabolicallabs.test.process.manager;

import com.diabolicallabs.process.manager.Verticle;
import com.diabolicallabs.process.manager.reactivex.service.KnowledgeServiceFactory;
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
public class KnowledgeServiceFactoryTest {

  Logger logger = LoggerFactory.getLogger(KnowledgeServiceFactoryTest.class);

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void before(TestContext context) {

    rule.vertx().deployVerticle(Verticle.class.getName(), context.asyncAssertSuccess());

  }

  @Test
  public void testGetKnowledgeService(TestContext context) {

    Async async = context.async();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Single.just(knowledgeServiceFactory)
        .flatMap(KnowledgeServiceFactory::rxGetKnowledgeService)
        .doOnSuccess(service -> {
          System.out.println(service);
        })
        .subscribe(
            knowledgeService -> {
              context.assertNotNull(knowledgeService);
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
