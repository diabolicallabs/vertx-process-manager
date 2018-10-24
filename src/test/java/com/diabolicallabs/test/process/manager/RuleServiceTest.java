package com.diabolicallabs.test.process.manager;

import com.diabolicallabs.process.manager.Verticle;
import com.diabolicallabs.process.manager.reactivex.service.KnowledgeService;
import com.diabolicallabs.process.manager.reactivex.service.KnowledgeServiceFactory;
import com.diabolicallabs.process.manager.reactivex.service.SessionService;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.reactivex.core.Vertx;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicReference;

@RunWith(io.vertx.ext.unit.junit.VertxUnitRunner.class)
public class RuleServiceTest {

  Logger logger = LoggerFactory.getLogger(RuleServiceTest.class);

  AtomicReference<KnowledgeService> knowledgeServiceAtomicReference = new AtomicReference<>();

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void before(TestContext context) {

    Async async = context.async();

    rule.vertx().deployVerticle(Verticle.class.getName(), handler -> {

      Vertx rxVertx = new Vertx(rule.vertx());
      KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);

      Single.just(knowledgeServiceFactory)
        .flatMap(KnowledgeServiceFactory::rxGetKnowledgeService)
        .doOnSuccess(knowledgeServiceAtomicReference::set)
        .flatMapCompletable(service -> {
          return service.rxAddClassPathResource("VertxRule.drl")
            .andThen(service.rxBuild());
        })
        .subscribe(
          async::complete,
          context::fail
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

    Single.just(knowledgeServiceAtomicReference.get())
      .flatMap(KnowledgeService::rxGetSessionService)
      .flatMap(SessionService::rxGetRuleService)
      .flatMap(ruleService -> {

        JsonObject json = new JsonObject()
          .put("name", "Goku")
          .put("age", 737)
          .put("race", "Saiyan");

        return ruleService.rxInsert("com.diabolicallabs.vertxkieserverclienttest", "DragonballCharacter", json)
          .flatMap(nothing -> ruleService.rxFireAllRules())
          .flatMap(numFired -> {
            System.out.println("Number of rules fired: " + numFired);
            return ruleService.rxGetQueryResults("characters", "character");
          });
      })
      .doOnSuccess(objects -> System.out.println(objects.encodePrettily()))
      .subscribe(
        objects -> {
          context.assertNotNull(objects);
          async.complete();
        },
        context::fail
      );
  }

}
