package com.diabolicallabs.test.process.manager;

import com.diabolicallabs.process.manager.Verticle;
import com.diabolicallabs.process.manager.rxjava.service.*;
import com.diabolicallabs.process.manager.service.UserTask;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.rxjava.core.Vertx;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import rx.Observable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static com.diabolicallabs.process.manager.service.VertxTaskEventListener.*;

@RunWith(io.vertx.ext.unit.junit.VertxUnitRunner.class)
public class RuleServiceTest {

  Logger logger = LoggerFactory.getLogger(RuleServiceTest.class);

  AtomicReference<KnowledgeService> knowledgeServiceAtomicReference = new AtomicReference<>();
  AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
  AtomicReference<TaskService> taskServiceAtomicReference = new AtomicReference<>();
  AtomicReference<RuleService> ruleServiceAtomicReference = new AtomicReference<>();

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void before(TestContext context) {

    Async async = context.async();

    rule.vertx().deployVerticle(Verticle.class.getName(), handler -> {

      Vertx rxVertx = new Vertx(rule.vertx());
      KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);

      Observable.just(knowledgeServiceFactory)
        .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
        .doOnNext(knowledgeServiceAtomicReference::set)
        .flatMap(service -> {
          return service.addClassPathResourceObservable("VertxRule.drl").last()
            .flatMap(nothing -> {
              return service.getProcessServiceObservable().doOnNext(processServiceAtomicReference::set);
            })
            .flatMap(nothing -> {
              return service.getTaskServiceObservable().doOnNext(taskServiceAtomicReference::set);
            })
            .flatMap(nothing -> {
              return service.getRuleServiceObservable().doOnNext(ruleServiceAtomicReference::set);
            });
        })
        .subscribe(
          context::assertNotNull,
          context::fail,
          async::complete
        );
    });

  }

  @After
  public void after(TestContext context) {
    knowledgeServiceAtomicReference.get().close();
  }

  @Test
  public void testRule(TestContext context) {

    Async async = context.async();

      Observable.just(ruleServiceAtomicReference.get())
      .flatMap(ruleService -> {

        JsonObject json = new JsonObject()
            .put("name", "Goku")
            .put("age", 737)
            .put("race", "Saiyan");

        return ruleService.insertObservable("com.diabolicallabs.vertxkieserverclienttest", "DragonballCharacter", json);
      })
      .doOnNext(factHandle -> System.out.println("Fact handle: " + factHandle))
      .flatMap(factHandle -> ruleServiceAtomicReference.get().fireAllRulesObservable())
      .doOnNext(numFired -> System.out.println("Rules fired: " + numFired))
      .flatMap(nothing -> ruleServiceAtomicReference.get().getQueryResultsObservable("characters", "character"))
      .doOnNext(objects -> System.out.println(objects.encodePrettily()))
      .subscribe(
        context::assertNotNull,
        context::fail,
        async::complete
      );
  }

}
