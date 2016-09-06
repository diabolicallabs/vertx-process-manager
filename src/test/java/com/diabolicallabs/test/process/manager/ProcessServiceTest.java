package com.diabolicallabs.test.process.manager;

import com.diabolicallabs.process.manager.Verticle;
import com.diabolicallabs.process.manager.rxjava.service.KnowledgeServiceFactory;
import com.diabolicallabs.process.manager.rxjava.service.ProcessInstanceService;
import com.diabolicallabs.process.manager.rxjava.service.ProcessService;
import com.diabolicallabs.process.manager.service.ProcessState;
import io.vertx.core.json.JsonObject;
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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@RunWith(io.vertx.ext.unit.junit.VertxUnitRunner.class)
public class ProcessServiceTest {

  Logger logger = LoggerFactory.getLogger(ProcessServiceTest.class);

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void before(TestContext context) {

    rule.vertx().deployVerticle(Verticle.class.getName(), context.asyncAssertSuccess());

  }

  @Test
  public void testCreateAndStartProcess(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2")
          .flatMap(nothing -> {
            return service.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2");
          })
          .flatMap(nothing -> {
            return service.getProcessServiceObservable();
          });
      })
      .flatMap(service -> {
        return service.createObservable("VertxKieServerClientTest.KieServerClientTest");
          //VertxKieServerClientTest.KieServerClientTest
      })
      .flatMap(processInstanceService -> {
        instanceServiceAtomicReference.set(processInstanceService);
        return processInstanceService.getInstanceIdObservable();
      })
      .doOnNext(id -> {
        rxVertx.eventBus().consumer("kie.process.instance." + id + ".complete", handler -> {
          async.complete();
        });
      })
      .flatMap(nothing -> {
        return instanceServiceAtomicReference.get().startObservable();
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

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2")
          .flatMap(nothing -> {
            return service.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2");
          })
          .flatMap(nothing -> {
            return service.getProcessServiceObservable();
          });
      })
      .flatMap(service -> {
        return service.startProcessObservable("VertxKieServerClientTest.KieServerClientTest");
      })
      .doOnNext(instanceServiceAtomicReference::set)
      .delay(3, TimeUnit.SECONDS)
      .flatMap(ProcessInstanceService::getStateObservable)
      .map(state -> state.equals(ProcessState.COMPLETED))
      .subscribe(
        context::assertTrue,
        context::fail,
        async::complete
      );
  }

  @Test
  public void testStartProcessWithVarsFromProcessService(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2")
          .flatMap(nothing -> {
            return service.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2");
          })
          .flatMap(nothing -> {
            return service.getProcessServiceObservable();
          });
      })
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", false)
          .put("doTimer", false)
          .put("doSignal", false)
          .put("doSubprocess", true);
        return service.startProcessWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(instanceServiceAtomicReference::set)
      .delay(3, TimeUnit.SECONDS)
      .flatMap(ProcessInstanceService::getStateObservable)
      .map(state -> state.equals(ProcessState.COMPLETED))
      .subscribe(
        context::assertTrue,
        context::fail,
        async::complete
      );
  }

  @Test
  public void testProcessInstanceId(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2")
          .flatMap(nothing -> {
            return service.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2");
          })
          .flatMap(nothing -> {
            return service.getProcessServiceObservable();
          });
      })
      .flatMap(service -> {
        return service.createObservable("VertxKieServerClientTest.KieServerClientTest");
      })
      .flatMap(ProcessInstanceService::getInstanceIdObservable)

      .subscribe(
        context::assertNotNull,
        context::fail,
        async::complete
      );
  }

  @Test
  public void testParentProcessInstanceId(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2")
          .flatMap(nothing -> {
            return service.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2");
          })
          .flatMap(nothing -> {
            return service.getProcessServiceObservable();
          });
      })
      .flatMap(service -> {
        return service.createObservable("VertxKieServerClientTest.KieServerClientTest");
      })
      .flatMap(ProcessInstanceService::getParentInstanceIdObservable)
      .doOnNext(id -> {
        logger.info("Parent process instance id: " + id);
      })
      .map(id -> id == 0l)
      .subscribe(
        context::assertTrue,
        context::fail,
        async::complete
      );
  }

  @Test
  public void testProcessName(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2")
          .flatMap(nothing -> {
            return service.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2");
          })
          .flatMap(nothing -> {
            return service.getProcessServiceObservable();
          });
      })
      .flatMap(service -> {
        return service.createObservable("VertxKieServerClientTest.KieServerClientTest");
      })
      .flatMap(ProcessInstanceService::getNameObservable)
      .doOnNext(name -> {
        logger.info("Process name: " + name);
      })
      .map(name -> name.equals("KieServerClientTest"))
      .subscribe(
        context::assertTrue,
        context::fail,
        async::complete
      );
  }

  @Test
  public void testProcessState(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2")
          .flatMap(nothing -> {
            return service.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2");
          })
          .flatMap(nothing -> {
            return service.getProcessServiceObservable();
          });
      })
      .flatMap(service -> {
        return service.createObservable("VertxKieServerClientTest.KieServerClientTest");
      })
      .flatMap(ProcessInstanceService::getStateObservable)
      .doOnNext(state -> {
        logger.info("Process state: " + state);
      })
      .map(state -> state.equals(ProcessState.PENDING))
      .subscribe(
        context::assertTrue,
        context::fail,
        async::complete
      );
  }

  @Test
  public void testSignalProcess(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2")
          .flatMap(nothing -> {
            return service.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2");
          })
          .flatMap(nothing -> {
            return service.getProcessServiceObservable();
          });
      })
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", false)
          .put("doTimer", false)
          .put("doSignal", true)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(instanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::startObservable)
      .delay(1, TimeUnit.SECONDS)
      .doOnNext(id -> {
        instanceServiceAtomicReference.get().signalEventObservable("CatchSignal", null);
      })
      .delay(1, TimeUnit.SECONDS)
      .flatMap(id -> {
        return instanceServiceAtomicReference.get().getStateObservable();
      })
      .doOnNext(state -> {
        logger.info("Process state: " + state);
      })
      .map(state -> state.equals(ProcessState.COMPLETED))
      .subscribe(
        context::assertTrue,
        context::fail,
        async::complete
      );
  }

  @Test
  public void testSignalAllProcesses(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2")
          .flatMap(nothing -> {
            return service.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2");
          })
          .flatMap(nothing -> {
            return service.getProcessServiceObservable();
          })
          .doOnNext(processServiceAtomicReference::set);
      })
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", false)
          .put("doTimer", false)
          .put("doSignal", true)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(instanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::startObservable)
      .delay(1, TimeUnit.SECONDS)
      .doOnNext(id -> {
        processServiceAtomicReference.get().signalEventObservable("CatchSignal", null);
      })
      .delay(1, TimeUnit.SECONDS)
      .flatMap(id -> {
        return instanceServiceAtomicReference.get().getStateObservable();
      })
      .doOnNext(state -> {
        logger.info("Process state: " + state);
      })
      .map(state -> state.equals(ProcessState.COMPLETED))
      .subscribe(
        context::assertTrue,
        context::fail,
        async::complete
      );
  }

  @Test
  public void testSignalProcessInstance(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessInstanceService> instanceServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2")
          .flatMap(nothing -> {
            return service.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2");
          })
          .flatMap(nothing -> {
            return service.getProcessServiceObservable();
          })
          .doOnNext(processServiceAtomicReference::set);
      })
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", false)
          .put("doTimer", false)
          .put("doSignal", true)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(instanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::startObservable)
      .delay(1, TimeUnit.SECONDS)
      .doOnNext(id -> {
        processServiceAtomicReference.get().signalEventForProcessObservable("CatchSignal", id, null);
      })
      .delay(1, TimeUnit.SECONDS)
      .flatMap(id -> {
        return instanceServiceAtomicReference.get().getStateObservable();
      })
      .doOnNext(state -> {
        logger.info("Process state: " + state);
      })
      .map(state -> state.equals(ProcessState.COMPLETED))
      .subscribe(
        context::assertTrue,
        context::fail,
        async::complete
      );
  }

  @Test
  public void testStartProcessWithVars(TestContext context) {

    Async async = context.async();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2")
          .flatMap(nothing -> {
            return service.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2");
          })
          .flatMap(nothing -> {
            return service.getProcessServiceObservable();
          });
      })
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", false)
          .put("doTimer", true)
          .put("doSignal", false)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .subscribe(
        context::assertNotNull,
        context::fail,
        async::complete
      );
  }

  @Test
  public void testAbortProcess(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessInstanceService> processInstanceServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2")
          .flatMap(nothing -> {
            return service.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2");
          })
          .flatMap(nothing -> {
            return service.getProcessServiceObservable();
          })
          .doOnNext(processServiceAtomicReference::set);
      })
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", false)
          .put("doTimer", true)
          .put("doSignal", false)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(processInstanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::getInstanceIdObservable)
      .flatMap(id -> processServiceAtomicReference.get().abortObservable(id))
      .flatMap(nothing -> processInstanceServiceAtomicReference.get().getStateObservable())
      .map(state -> state.equals(ProcessState.ABORTED))
      .subscribe(
        context::assertTrue,
        context::fail,
        async::complete
      );
  }


  @Test
  public void testAbortProcessInstance(TestContext context) {

    Async async = context.async();

    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessInstanceService> processInstanceServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2")
          .flatMap(nothing -> {
            return service.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2");
          })
          .flatMap(nothing -> {
            return service.getProcessServiceObservable();
          })
          .doOnNext(processServiceAtomicReference::set);
      })
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", false)
          .put("doTimer", true)
          .put("doSignal", false)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(processInstanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::abortObservable)
      .flatMap(nothing -> processInstanceServiceAtomicReference.get().getStateObservable())
      .map(state -> state.equals(ProcessState.ABORTED))
      .subscribe(
        context::assertTrue,
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
  }
}
