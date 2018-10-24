package com.diabolicallabs.test.process.manager;

import com.diabolicallabs.process.manager.Verticle;
import com.diabolicallabs.process.manager.reactivex.service.*;
import com.diabolicallabs.process.manager.service.ProcessState;
import io.reactivex.Observable;
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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@RunWith(io.vertx.ext.unit.junit.VertxUnitRunner.class)
public class ProcessServiceTest {

  Logger logger = LoggerFactory.getLogger(ProcessServiceTest.class);

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
            return service.rxAddClassPathResource("org.jbpm.KieServerClientTest.v1.0.bpmn2")
                .andThen(service.rxAddClassPathResource("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2")
                .andThen(service.rxBuild()));
          })
          .subscribe(
              async::complete,
              context::assertNotNull
          );
    });

  }

  @After
  public void after(TestContext context) {
    knowledgeServiceAtomicReference.get().close();
  }

  @Test
  public void testCreateAndStartProcess(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());

    Single.just(knowledgeServiceAtomicReference.get())
      .flatMap(KnowledgeService::rxGetSessionService)
      .flatMap(SessionService::rxGetProcessService)
        .flatMap(service -> {
          return service.rxCreate("VertxKieServerClientTest.KieServerClientTest");
        })
        .flatMap(processInstanceService -> {
          instanceServiceAtomicReference.set(processInstanceService);
          return processInstanceService.rxGetInstanceId();
        })
        .doOnSuccess(id -> {
          rxVertx.eventBus().consumer("kie.process.instance." + id + ".complete", handler -> {
            async.complete();
          });
        })
        .flatMap(nothing -> {
          return instanceServiceAtomicReference.get().rxStart();
        })
        .subscribe(
            context::assertNotNull,
            context::fail
        );
  }

  @Test
  public void testStartProcess(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Single.just(knowledgeServiceAtomicReference.get())
      .flatMap(KnowledgeService::rxGetSessionService)
      .flatMap(SessionService::rxGetProcessService)
        .flatMap(service -> {
          return service.rxStartProcess("VertxKieServerClientTest.KieServerClientTest");
        })
        .doOnSuccess(instanceServiceAtomicReference::set)
        .delay(3, TimeUnit.SECONDS)
        .flatMap(ProcessInstanceService::rxGetState)
        .map(state -> state.equals(ProcessState.COMPLETED))
        .subscribe(
            completed -> {
              context.assertTrue(completed);
              async.complete();
            },
            context::fail
        );
  }

  @Test
  public void testStartProcessWithVarsFromProcessService(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Single.just(knowledgeServiceAtomicReference.get())
      .flatMap(KnowledgeService::rxGetSessionService)
      .flatMap(SessionService::rxGetProcessService)
        .flatMap(service -> {
          JsonObject json = new JsonObject();
          json.put("display", "Goats")
              .put("doHuman", false)
              .put("doTimer", false)
              .put("doSignal", false)
              .put("doSubprocess", true);
          return service.rxStartProcessWithVariables("VertxKieServerClientTest.KieServerClientTest", json);
        })
        .doOnSuccess(instanceServiceAtomicReference::set)
        .delay(3, TimeUnit.SECONDS)
        .flatMap(ProcessInstanceService::rxGetState)
        .map(state -> state.equals(ProcessState.COMPLETED))
        .subscribe(
            completed -> {
              context.assertTrue(completed);
              async.complete();
            },
            context::fail
        );
  }

  @Test
  public void testProcessInstanceId(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Single.just(knowledgeServiceAtomicReference.get())
      .flatMap(KnowledgeService::rxGetSessionService)
      .flatMap(SessionService::rxGetProcessService)
        .flatMap(service -> {
          return service.rxCreate("VertxKieServerClientTest.KieServerClientTest");
        })
        .flatMap(ProcessInstanceService::rxGetParentInstanceId)
        .subscribe(
            id -> {
              context.assertNotNull(id);
              async.complete();
            },
            context::fail
        );
  }

  @Test
  public void testParentProcessInstanceId(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Single.just(knowledgeServiceAtomicReference.get())
      .flatMap(KnowledgeService::rxGetSessionService)
      .flatMap(SessionService::rxGetProcessService)
        .flatMap(service -> {
          return service.rxCreate("VertxKieServerClientTest.KieServerClientTest");
        })
        .flatMap(ProcessInstanceService::rxGetParentInstanceId)
        .doOnSuccess(id -> {
          logger.info("Parent process instance id: " + id);
        })
        .map(id -> id == 0l)
        .subscribe(
            truth -> {
              context.assertTrue(truth);
              async.complete();
            },
            context::fail
        );
  }

  @Test
  public void testProcessName(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Single.just(knowledgeServiceAtomicReference.get())
      .flatMap(KnowledgeService::rxGetSessionService)
      .flatMap(SessionService::rxGetProcessService)
        .flatMap(service -> {
          return service.rxCreate("VertxKieServerClientTest.KieServerClientTest");
        })
        .flatMap(ProcessInstanceService::rxGetName)
        .doOnSuccess(name -> {
          logger.info("Process name: " + name);
        })
        .map(name -> name.equals("KieServerClientTest"))
        .subscribe(
            truth -> {
              context.assertTrue(truth);
              async.complete();
            },
            context::fail
        );
  }

  @Test
  public void testProcessState(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Single.just(knowledgeServiceAtomicReference.get())
      .flatMap(KnowledgeService::rxGetSessionService)
      .flatMap(SessionService::rxGetProcessService)
        .flatMap(service -> {
          return service.rxCreate("VertxKieServerClientTest.KieServerClientTest");
        })
        .flatMap(ProcessInstanceService::rxGetState)
        .doOnSuccess(state -> {
          logger.info("Process state: " + state);
        })
        .map(state -> state.equals(ProcessState.PENDING))
        .subscribe(
            truth -> {
              context.assertTrue(truth);
              async.complete();
            },
            context::fail
        );
  }

  @Test
  public void testSignalProcess(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Single.just(knowledgeServiceAtomicReference.get())
      .flatMap(KnowledgeService::rxGetSessionService)
      .flatMap(SessionService::rxGetProcessService)
        .flatMap(service -> {
          JsonObject json = new JsonObject();
          json.put("display", "Goats")
              .put("doHuman", false)
              .put("doTimer", false)
              .put("doSignal", true)
              .put("doSubprocess", false);

          return service.rxCreateWithVariables("VertxKieServerClientTest.KieServerClientTest", json);
        })
        .doOnSuccess(instanceServiceAtomicReference::set)
        .flatMap(ProcessInstanceService::rxStart)
        .delay(1, TimeUnit.SECONDS)
        .doOnSuccess(id -> {
          instanceServiceAtomicReference.get().rxSignalEvent("CatchSignal", null);
        })
        .delay(1, TimeUnit.SECONDS)
        .flatMap(id -> {
          return instanceServiceAtomicReference.get().rxGetState();
        })
        .doOnSuccess(state -> {
          logger.info("Process state: " + state);
        })
        .map(state -> state.equals(ProcessState.COMPLETED))
        .subscribe(
            truth -> {
              context.assertTrue(truth);
              async.complete();
            },
            context::fail
        );
  }

  @Test
  public void testSignalAllProcesses(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();

    Single.just(knowledgeServiceAtomicReference.get())
      .flatMap(KnowledgeService::rxGetSessionService)
      .flatMap(SessionService::rxGetProcessService)
      .doOnSuccess(processServiceAtomicReference::set)
        .flatMap(service -> {
          JsonObject json = new JsonObject();
          json.put("display", "Goats")
              .put("doHuman", false)
              .put("doTimer", false)
              .put("doSignal", true)
              .put("doSubprocess", false);

          return service.rxCreateWithVariables("VertxKieServerClientTest.KieServerClientTest", json);
        })
        .doOnSuccess(instanceServiceAtomicReference::set)
        .flatMap(ProcessInstanceService::rxStart)
        .delay(1, TimeUnit.SECONDS)
        .doOnSuccess(id -> {
          processServiceAtomicReference.get().rxSignalEvent("CatchSignal", null);
        })
        .delay(1, TimeUnit.SECONDS)
        .flatMap(id -> {
          return instanceServiceAtomicReference.get().rxGetState();
        })
        .doOnSuccess(state -> {
          logger.info("Process state: " + state);
        })
        .map(state -> state.equals(ProcessState.COMPLETED))
        .subscribe(
            truth -> {
              context.assertTrue(truth);
              async.complete();
            },
            context::fail
        );
  }

  @Test
  public void testSignalProcessInstance(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();

    Single.just(knowledgeServiceAtomicReference.get())
      .flatMap(KnowledgeService::rxGetSessionService)
      .flatMap(SessionService::rxGetProcessService)
      .doOnSuccess(processServiceAtomicReference::set)
        .flatMap(service -> {
          JsonObject json = new JsonObject();
          json.put("display", "Goats")
              .put("doHuman", false)
              .put("doTimer", false)
              .put("doSignal", true)
              .put("doSubprocess", false);

          return service.rxCreateWithVariables("VertxKieServerClientTest.KieServerClientTest", json);
        })
        .doOnSuccess(instanceServiceAtomicReference::set)
        .flatMap(ProcessInstanceService::rxStart)
        .delay(1, TimeUnit.SECONDS)
        .doOnSuccess(id -> {
          processServiceAtomicReference.get().rxSignalEventForProcess("CatchSignal", id, null);
        })
        .delay(1, TimeUnit.SECONDS)
        .flatMap(id -> {
          return instanceServiceAtomicReference.get().rxGetState();
        })
        .doOnSuccess(state -> {
          logger.info("Process state: " + state);
        })
        .map(state -> state.equals(ProcessState.COMPLETED))
        .subscribe(
            truth -> {
              context.assertTrue(truth);
              async.complete();
            },
            context::fail
        );
  }

  @Test
  public void testStartProcessWithVars(TestContext context) {

    Async async = context.async();

    Single.just(knowledgeServiceAtomicReference.get())
      .flatMap(KnowledgeService::rxGetSessionService)
      .flatMap(SessionService::rxGetProcessService)
        .flatMap(service -> {
          JsonObject json = new JsonObject();
          json.put("display", "Goats")
              .put("doHuman", false)
              .put("doTimer", true)
              .put("doSignal", false)
              .put("doSubprocess", false);

          return service.rxCreateWithVariables("VertxKieServerClientTest.KieServerClientTest", json);
        })
        .subscribe(
            id -> {
              context.assertNotNull(id);
              async.complete();
            },
            context::fail
        );
  }

  @Test
  public void testAbortProcess(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> processInstanceServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();

    Single.just(knowledgeServiceAtomicReference.get())
      .flatMap(KnowledgeService::rxGetSessionService)
      .flatMap(SessionService::rxGetProcessService)
      .doOnSuccess(processServiceAtomicReference::set)
        .flatMap(service -> {
          JsonObject json = new JsonObject();
          json.put("display", "Goats")
              .put("doHuman", false)
              .put("doTimer", true)
              .put("doSignal", false)
              .put("doSubprocess", false);

          return service.rxCreateWithVariables("VertxKieServerClientTest.KieServerClientTest", json);
        })
        .doOnSuccess(processInstanceServiceAtomicReference::set)
        .flatMap(ProcessInstanceService::rxGetInstanceId)
        .flatMap(id -> processServiceAtomicReference.get().rxAbort(id).toSingleDefault(id))
        .flatMap(nothing -> processInstanceServiceAtomicReference.get().rxGetState())
        .map(state -> state.equals(ProcessState.ABORTED))
        .subscribe(
            truth -> {
              context.assertTrue(truth);
              async.complete();
            },
            context::fail
        );
  }


  @Test
  public void testAbortProcessInstance(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> processInstanceServiceAtomicReference = new AtomicReference<>();

    Single.just(knowledgeServiceAtomicReference.get())
      .flatMap(KnowledgeService::rxGetSessionService)
      .flatMap(SessionService::rxGetProcessService)
        .flatMap(service -> {
          JsonObject json = new JsonObject();
          json.put("display", "Goats")
              .put("doHuman", false)
              .put("doTimer", true)
              .put("doSignal", false)
              .put("doSubprocess", false);

          return service.rxCreateWithVariables("VertxKieServerClientTest.KieServerClientTest", json);
        })
        .doOnSuccess(processInstanceServiceAtomicReference::set)
        .doOnSuccess(ProcessInstanceService::rxAbort)
        .flatMap(nothing -> processInstanceServiceAtomicReference.get().rxGetState())
        .map(state -> state.equals(ProcessState.ABORTED))
        .subscribe(
            truth -> {
              context.assertTrue(truth);
              async.complete();
            },
            context::fail
        );
  }

}
