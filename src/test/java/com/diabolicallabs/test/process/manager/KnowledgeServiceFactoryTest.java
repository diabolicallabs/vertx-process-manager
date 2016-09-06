package com.diabolicallabs.test.process.manager;

import com.diabolicallabs.process.manager.Verticle;
import com.diabolicallabs.process.manager.rxjava.service.KnowledgeService;
import com.diabolicallabs.process.manager.rxjava.service.KnowledgeServiceFactory;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.rxjava.core.Vertx;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import rx.Observable;

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
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .doOnNext(service -> {
        System.out.println(service);
      })
      .subscribe(
        context::assertNotNull,
        context::fail,
        async::complete
      );
  }

  @Test
  public void testAddKnowledgeResource(TestContext context) {

    Async async = context.async();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2");
      })
      .subscribe(
        context::assertNull,
        context::fail,
        async::complete
      );
    /*
    factory.getKnowledgeService(handler -> {
      context.assertTrue(handler.succeeded());
      KnowledgeService service = handler.result();
      service.addClassPathResource("org.jbpm.KieServerClientTest.v1.0.bpmn2", serviceHandler -> {
        context.assertTrue(serviceHandler.succeeded());
        async.complete();
      });
    });
    */
  }
}
