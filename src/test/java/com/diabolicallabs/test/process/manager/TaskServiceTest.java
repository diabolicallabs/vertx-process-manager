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
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static com.diabolicallabs.process.manager.service.VertxTaskEventListener.*;

@RunWith(io.vertx.ext.unit.junit.VertxUnitRunner.class)
public class TaskServiceTest {

  Logger logger = LoggerFactory.getLogger(TaskServiceTest.class);

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void before(TestContext context) {

    rule.vertx().deployVerticle(Verticle.class.getName(), context.asyncAssertSuccess());

  }

  private Observable createServicesObservable(AtomicReference<KnowledgeService> knowledgeServiceAR, AtomicReference<ProcessService> processServiceAR, AtomicReference<TaskService> taskServiceAR) {

    Vertx rxVertx = new Vertx(rule.vertx());

    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);

    return Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .doOnNext(knowledgeServiceAR::set)
      .flatMap(knowledgeService -> {
        return Observable.merge(
          knowledgeService.getProcessServiceObservable().doOnNext(processServiceAR::set),
          knowledgeService.getTaskServiceObservable().doOnNext(taskServiceAR::set)
        );
      });
  }

  @Test
  public void testHumanProcess(TestContext context) {

    Async async = context.async();

    AtomicReference<KnowledgeService> knowledgeServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessInstanceService> processInstanceServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
    AtomicReference<TaskService> taskServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());

    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .doOnNext(knowledgeServiceAtomicReference::set)
      .flatMap(knowledgeService -> {
        return Observable.merge(
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2"),
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2")
        ).last()
          .flatMap(nothing -> knowledgeService.getTaskServiceObservable())
          .doOnNext(taskServiceAtomicReference::set)
          .flatMap(taskService -> knowledgeService.getTaskServiceAddressObservable())
          .doOnNext(address -> {

                rxVertx.eventBus().consumer(address, handler -> {

                  String event = handler.headers().get("event");
                  JsonObject message = (JsonObject) handler.body();
                  UserTask task = new UserTask(message);

                  logger.info("UserTask event: " + event + " message: " + message.encodePrettily());

                  if (event.equals(TASK_ADDED)) {
                    context.assertTrue(message.getJsonArray("potential_owners").getList().contains("ioniki"));
                    logger.info("About to claim task " + task.id);
                    taskServiceAtomicReference.get().claim(task.id, "ioniki", context.asyncAssertSuccess());
                  } else if (event.equals(TASK_CLAIMED)) {
                    logger.info("About to start task " + task.id);
                    taskServiceAtomicReference.get().start(task.id, "ioniki", context.asyncAssertSuccess());
                  } else if (event.equals(TASK_STARTED)) {
                    JsonObject response = new JsonObject().put("out", "Squidly");
                    logger.info("About to complete task " + task.id);
                    taskServiceAtomicReference.get().complete(task.id, "ioniki", response, context.asyncAssertSuccess());
                  } else if (event.equals(TASK_COMPLETED)) {
                    async.complete();
                  }
                });

              });
          })
      .flatMap(nothing -> knowledgeServiceAtomicReference.get().getProcessServiceObservable())
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", true)
          .put("doTimer", false)
          .put("doSignal", false)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(processInstanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::startObservable)
      .delay(5, TimeUnit.SECONDS)
      .subscribe(
        context::assertNotNull,
        context::fail
      );
  }

  @Test
  public void testFailure(TestContext context) {

    Async async = context.async();

    AtomicReference<KnowledgeService> knowledgeServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessInstanceService> processInstanceServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
    AtomicReference<TaskService> taskServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());

    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .doOnNext(knowledgeServiceAtomicReference::set)
      .flatMap(knowledgeService -> {
        return Observable.merge(
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2"),
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2")
        ).last()
          .flatMap(nothing -> knowledgeService.getTaskServiceObservable())
          .doOnNext(taskServiceAtomicReference::set)
          .flatMap(taskService -> knowledgeService.getTaskServiceAddressObservable())
          .doOnNext(address -> {

            rxVertx.eventBus().consumer(address, handler -> {

              String event = handler.headers().get("event");
              JsonObject message = (JsonObject) handler.body();
              UserTask task = new UserTask(message);

              logger.info("UserTask event: " + event + " message: " + message.encodePrettily());

              if (event.equals(TASK_ADDED)) {
                context.assertTrue(message.getJsonArray("potential_owners").getList().contains("ioniki"));
                logger.info("About to claim task " + task.id);
                taskServiceAtomicReference.get().claim(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_CLAIMED)) {
                logger.info("About to start task " + task.id);
                taskServiceAtomicReference.get().start(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_STARTED)) {
                JsonObject response = new JsonObject().put("out", "Squidly");
                logger.info("About to complete task " + task.id);
                taskServiceAtomicReference.get().fail(task.id, "ioniki", response, context.asyncAssertSuccess());
              } else if (event.equals(TASK_FAILED)) {
                async.complete();
              }
            });

          });
      })
      .flatMap(nothing -> knowledgeServiceAtomicReference.get().getProcessServiceObservable())
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", true)
          .put("doTimer", false)
          .put("doSignal", false)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(processInstanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::startObservable)
      .delay(5, TimeUnit.SECONDS)
      .subscribe(
        context::assertNotNull,
        context::fail
      );
  }

  @Test
  public void testForward(TestContext context) {

    Async async = context.async();

    AtomicReference<KnowledgeService> knowledgeServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessInstanceService> processInstanceServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
    AtomicReference<TaskService> taskServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());

    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .doOnNext(knowledgeServiceAtomicReference::set)
      .flatMap(knowledgeService -> {
        return Observable.merge(
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2"),
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2")
        ).last()
          .flatMap(nothing -> knowledgeService.getTaskServiceObservable())
          .doOnNext(taskServiceAtomicReference::set)
          .flatMap(taskService -> knowledgeService.getTaskServiceAddressObservable())
          .doOnNext(address -> {

            rxVertx.eventBus().consumer(address, handler -> {

              String event = handler.headers().get("event");
              JsonObject message = (JsonObject) handler.body();
              UserTask task = new UserTask(message);

              logger.info("UserTask event: " + event + " message: " + message.encodePrettily());

              if (event.equals(TASK_ADDED)) {
                context.assertTrue(message.getJsonArray("potential_owners").getList().contains("ioniki"));
                logger.info("About to claim task " + task.id);
                taskServiceAtomicReference.get().claim(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_CLAIMED)) {
                logger.info("About to start task " + task.id);
                taskServiceAtomicReference.get().start(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_STARTED)) {
                logger.info("About to complete task " + task.id);
                taskServiceAtomicReference.get().forward(task.id, "ioniki", "kawika", context.asyncAssertSuccess());
              } else if (event.equals(TASK_FORWARDED)) {
                async.complete();
              }
            });

          });
      })
      .flatMap(nothing -> knowledgeServiceAtomicReference.get().getProcessServiceObservable())
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", true)
          .put("doTimer", false)
          .put("doSignal", false)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(processInstanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::startObservable)
      .delay(5, TimeUnit.SECONDS)
      .subscribe(
        context::assertNotNull,
        context::fail
      );
  }

  @Test
  public void testAddComment(TestContext context) {

    Async async = context.async();

    AtomicReference<KnowledgeService> knowledgeServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessInstanceService> processInstanceServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
    AtomicReference<TaskService> taskServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());

    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .doOnNext(knowledgeServiceAtomicReference::set)
      .flatMap(knowledgeService -> {
        return Observable.merge(
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2"),
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2")
        ).last()
          .flatMap(nothing -> knowledgeService.getTaskServiceObservable())
          .doOnNext(taskServiceAtomicReference::set)
          .flatMap(taskService -> knowledgeService.getTaskServiceAddressObservable())
          .doOnNext(address -> {

            rxVertx.eventBus().consumer(address, handler -> {

              String event = handler.headers().get("event");
              JsonObject message = (JsonObject) handler.body();
              UserTask task = new UserTask(message);

              logger.info("UserTask event: " + event + " message: " + message.encodePrettily());

              if (event.equals(TASK_ADDED)) {
                context.assertTrue(message.getJsonArray("potential_owners").getList().contains("ioniki"));
                logger.info("About to claim task " + task.id);
                taskServiceAtomicReference.get().claim(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_CLAIMED)) {
                logger.info("About to start task " + task.id);
                taskServiceAtomicReference.get().start(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_STARTED)) {
                JsonObject response = new JsonObject().put("out", "Squidly");
                logger.info("About to complete task " + task.id);
                taskServiceAtomicReference.get().addComment(task.id, "ioniki", "Mongoose", context.asyncAssertSuccess());
                async.complete();
              }
            });

          });
      })
      .flatMap(nothing -> knowledgeServiceAtomicReference.get().getProcessServiceObservable())
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", true)
          .put("doTimer", false)
          .put("doSignal", false)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(processInstanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::startObservable)
      .delay(5, TimeUnit.SECONDS)
      .subscribe(
        context::assertNotNull,
        context::fail
      );
  }

  @Test
  public void testStop(TestContext context) {

    Async async = context.async();

    AtomicReference<KnowledgeService> knowledgeServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessInstanceService> processInstanceServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
    AtomicReference<TaskService> taskServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());

    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .doOnNext(knowledgeServiceAtomicReference::set)
      .flatMap(knowledgeService -> {
        return Observable.merge(
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2"),
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2")
        ).last()
          .flatMap(nothing -> knowledgeService.getTaskServiceObservable())
          .doOnNext(taskServiceAtomicReference::set)
          .flatMap(taskService -> knowledgeService.getTaskServiceAddressObservable())
          .doOnNext(address -> {

            rxVertx.eventBus().consumer(address, handler -> {

              String event = handler.headers().get("event");
              JsonObject message = (JsonObject) handler.body();
              UserTask task = new UserTask(message);

              logger.info("UserTask event: " + event + " message: " + message.encodePrettily());

              if (event.equals(TASK_ADDED)) {
                context.assertTrue(message.getJsonArray("potential_owners").getList().contains("ioniki"));
                logger.info("About to claim task " + task.id);
                taskServiceAtomicReference.get().claim(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_CLAIMED)) {
                logger.info("About to start task " + task.id);
                taskServiceAtomicReference.get().start(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_STARTED)) {
                logger.info("About to stop task " + task.id);
                taskServiceAtomicReference.get().stop(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_STOPPED)) {
                async.complete();
              }
            });

          });
      })
      .flatMap(nothing -> knowledgeServiceAtomicReference.get().getProcessServiceObservable())
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", true)
          .put("doTimer", false)
          .put("doSignal", false)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(processInstanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::startObservable)
      .delay(5, TimeUnit.SECONDS)
      .subscribe(
        context::assertNotNull,
        context::fail
      );
  }

  @Test
  public void testSuspendResume(TestContext context) {

    Async async = context.async();

    AtomicReference<KnowledgeService> knowledgeServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessInstanceService> processInstanceServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
    AtomicReference<TaskService> taskServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());

    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .doOnNext(knowledgeServiceAtomicReference::set)
      .flatMap(knowledgeService -> {
        return Observable.merge(
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2"),
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2")
        ).last()
          .flatMap(nothing -> knowledgeService.getTaskServiceObservable())
          .doOnNext(taskServiceAtomicReference::set)
          .flatMap(taskService -> knowledgeService.getTaskServiceAddressObservable())
          .doOnNext(address -> {

            rxVertx.eventBus().consumer(address, handler -> {

              String event = handler.headers().get("event");
              JsonObject message = (JsonObject) handler.body();
              UserTask task = new UserTask(message);

              logger.info("UserTask event: " + event + " message: " + message.encodePrettily());

              if (event.equals(TASK_ADDED)) {
                context.assertTrue(message.getJsonArray("potential_owners").getList().contains("ioniki"));
                logger.info("About to claim task " + task.id);
                taskServiceAtomicReference.get().claim(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_CLAIMED)) {
                logger.info("About to start task " + task.id);
                taskServiceAtomicReference.get().start(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_STARTED)) {
                logger.info("About to stop task " + task.id);
                taskServiceAtomicReference.get().suspend(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_SUSPENDED)) {
                taskServiceAtomicReference.get().resume(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_RESUMED)) {
                async.complete();
              }
            });

          });
      })
      .flatMap(nothing -> knowledgeServiceAtomicReference.get().getProcessServiceObservable())
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", true)
          .put("doTimer", false)
          .put("doSignal", false)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(processInstanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::startObservable)
      .delay(5, TimeUnit.SECONDS)
      .subscribe(
        context::assertNotNull,
        context::fail
      );
  }

  @Test
  public void testSkip(TestContext context) {

    Async async = context.async();

    AtomicReference<KnowledgeService> knowledgeServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessInstanceService> processInstanceServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
    AtomicReference<TaskService> taskServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());

    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .doOnNext(knowledgeServiceAtomicReference::set)
      .flatMap(knowledgeService -> {
        return Observable.merge(
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2"),
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2")
        ).last()
          .flatMap(nothing -> knowledgeService.getTaskServiceObservable())
          .doOnNext(taskServiceAtomicReference::set)
          .flatMap(taskService -> knowledgeService.getTaskServiceAddressObservable())
          .doOnNext(address -> {

            rxVertx.eventBus().consumer(address, handler -> {

              String event = handler.headers().get("event");
              JsonObject message = (JsonObject) handler.body();
              UserTask task = new UserTask(message);

              logger.info("UserTask event: " + event + " message: " + message.encodePrettily());

              if (event.equals(TASK_ADDED)) {
                context.assertTrue(message.getJsonArray("potential_owners").getList().contains("ioniki"));
                logger.info("About to claim task " + task.id);
                taskServiceAtomicReference.get().claim(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_CLAIMED)) {
                logger.info("About to start task " + task.id);
                taskServiceAtomicReference.get().start(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_STARTED)) {
                logger.info("About to stop task " + task.id);
                taskServiceAtomicReference.get().skip(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_SKIPPED)) {
                async.complete();
              }
            });

          });
      })
      .flatMap(nothing -> knowledgeServiceAtomicReference.get().getProcessServiceObservable())
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", true)
          .put("doTimer", false)
          .put("doSignal", false)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(processInstanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::startObservable)
      .delay(5, TimeUnit.SECONDS)
      .subscribe(
        context::assertNotNull,
        context::fail
      );
  }

  @Test
  public void testDelegate(TestContext context) {

    Async async = context.async();

    AtomicReference<KnowledgeService> knowledgeServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessInstanceService> processInstanceServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
    AtomicReference<TaskService> taskServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());

    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .doOnNext(knowledgeServiceAtomicReference::set)
      .flatMap(knowledgeService -> {
        return Observable.merge(
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2"),
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2")
        ).last()
          .flatMap(nothing -> knowledgeService.getTaskServiceObservable())
          .doOnNext(taskServiceAtomicReference::set)
          .flatMap(taskService -> knowledgeService.getTaskServiceAddressObservable())
          .doOnNext(address -> {

            rxVertx.eventBus().consumer(address, handler -> {

              String event = handler.headers().get("event");
              JsonObject message = (JsonObject) handler.body();
              UserTask task = new UserTask(message);

              logger.info("UserTask event: " + event + " message: " + message.encodePrettily());

              if (event.equals(TASK_ADDED)) {
                context.assertTrue(message.getJsonArray("potential_owners").getList().contains("ioniki"));
                logger.info("About to claim task " + task.id);
                taskServiceAtomicReference.get().claim(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_CLAIMED)) {
                logger.info("About to start task " + task.id);
                taskServiceAtomicReference.get().delegate(task.id, "ioniki", "kawika", context.asyncAssertSuccess());
              } else if (event.equals(TASK_DELEGATED)) {
                context.assertTrue(true);
                async.complete();
              }
            });

          });
      })
      .flatMap(nothing -> knowledgeServiceAtomicReference.get().getProcessServiceObservable())
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", true)
          .put("doTimer", false)
          .put("doSignal", false)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(processInstanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::startObservable)
      .delay(5, TimeUnit.SECONDS)
      .subscribe(
        context::assertNotNull,
        context::fail
      );
  }

  @Test
  public void testRelease(TestContext context) {

    Async async = context.async();

    AtomicReference<KnowledgeService> knowledgeServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessInstanceService> processInstanceServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
    AtomicReference<TaskService> taskServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());

    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .doOnNext(knowledgeServiceAtomicReference::set)
      .flatMap(knowledgeService -> {
        return Observable.merge(
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2"),
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2")
        ).last()
          .flatMap(nothing -> knowledgeService.getTaskServiceObservable())
          .doOnNext(taskServiceAtomicReference::set)
          .flatMap(taskService -> knowledgeService.getTaskServiceAddressObservable())
          .doOnNext(address -> {

            rxVertx.eventBus().consumer(address, handler -> {

              String event = handler.headers().get("event");
              JsonObject message = (JsonObject) handler.body();
              UserTask task = new UserTask(message);

              logger.info("UserTask event: " + event + " message: " + message.encodePrettily());

              if (event.equals(TASK_ADDED)) {
                context.assertTrue(message.getJsonArray("potential_owners").getList().contains("ioniki"));
                logger.info("About to claim task " + task.id);
                taskServiceAtomicReference.get().claim(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_CLAIMED)) {
                logger.info("About to start task " + task.id);
                taskServiceAtomicReference.get().release(task.id, "ioniki", context.asyncAssertSuccess());
              } else if (event.equals(TASK_RELEASED)) {
                context.assertTrue(true);
                async.complete();
              }
            });

          });
      })
      .flatMap(nothing -> knowledgeServiceAtomicReference.get().getProcessServiceObservable())
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", true)
          .put("doTimer", false)
          .put("doSignal", false)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(processInstanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::startObservable)
      .delay(5, TimeUnit.SECONDS)
      .subscribe(
        context::assertNotNull,
        context::fail
      );
  }


  @Test
  public void testGetContent(TestContext context) {

    Async async = context.async();

    AtomicReference<KnowledgeService> knowledgeServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessInstanceService> processInstanceServiceAtomicReference = new AtomicReference<>();
    AtomicReference<ProcessService> processServiceAtomicReference = new AtomicReference<>();
    AtomicReference<TaskService> taskServiceAtomicReference = new AtomicReference<>();

    Vertx rxVertx = new Vertx(rule.vertx());

    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    Observable.just(knowledgeServiceFactory)
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      .doOnNext(knowledgeServiceAtomicReference::set)
      .flatMap(knowledgeService -> {
        return Observable.merge(
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2"),
          knowledgeService.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2")
        ).last()
          .flatMap(nothing -> knowledgeService.getTaskServiceObservable())
          .doOnNext(taskServiceAtomicReference::set)
          .flatMap(taskService -> knowledgeService.getTaskServiceAddressObservable())
          .doOnNext(address -> {

            rxVertx.eventBus().consumer(address, handler -> {

              String event = handler.headers().get("event");
              JsonObject message = (JsonObject) handler.body();
              UserTask task = new UserTask(message);

              logger.info("UserTask event: " + event + " message: " + message.encodePrettily());

              if (event.equals(TASK_ADDED)) {
                context.assertTrue(message.getJsonArray("potential_owners").getList().contains("ioniki"));
                logger.info("About to get content task " + task.id);
                taskServiceAtomicReference.get().getContent(task.id, contentHandler -> {
                  context.assertTrue(contentHandler.succeeded());
                  logger.info("Content: " + contentHandler.result());
                  async.complete();
                });
              }
            });

          });
      })
      .flatMap(nothing -> knowledgeServiceAtomicReference.get().getProcessServiceObservable())
      .flatMap(service -> {
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("taskIn", "human input")
          .put("doHuman", true)
          .put("doTimer", false)
          .put("doSignal", false)
          .put("doSubprocess", false);

        return service.createWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      .doOnNext(processInstanceServiceAtomicReference::set)
      .flatMap(ProcessInstanceService::startObservable)
      .delay(5, TimeUnit.SECONDS)
      .subscribe(
        context::assertNotNull,
        context::fail
      );
  }
}
