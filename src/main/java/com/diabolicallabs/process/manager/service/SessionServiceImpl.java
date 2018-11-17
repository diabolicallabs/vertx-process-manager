package com.diabolicallabs.process.manager.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.serviceproxy.ProxyHelper;
import io.vertx.serviceproxy.ServiceBinder;
import org.kie.api.runtime.KieSession;

public class SessionServiceImpl implements SessionService {

  private Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

  private Vertx vertx;
  private JsonObject config;
  private String address;

  ProcessService processService;
  RuleService ruleService;

  MessageConsumer<JsonObject> processServiceConsumer;
  MessageConsumer<JsonObject> ruleServiceConsumer;

  KieSession kieSession;

  public SessionServiceImpl(Vertx vertx, JsonObject config, String address, KieSession session) {
    this.vertx = vertx;
    this.config = config;
    this.address = address;
    this.kieSession = session;
  }

  @Override
  public SessionService getProcessService(Handler<AsyncResult<ProcessService>> handler) {

    if (processService != null) {
      handler.handle(Future.succeededFuture(processService));
      return this;
    }

    String serviceAddress = address + ".ProcessService";
    ProcessServiceImpl serviceImpl = new ProcessServiceImpl(vertx, serviceAddress, kieSession);
<<<<<<< HEAD
    processServiceConsumer = ProxyHelper.registerService(ProcessService.class, vertx, serviceImpl, serviceAddress);
    handler.handle(Future.succeededFuture(ProcessService.createProxy(vertx, serviceAddress)));

=======
    processServiceConsumer = new ServiceBinder(vertx)
        .setAddress(serviceAddress)
        .register(ProcessService.class, serviceImpl);
>>>>>>> 577d43a2903b2b678aa55cedbe3d84be347f0a82
    return this;
  }

  @Override
  public SessionService getRuleService(Handler<AsyncResult<RuleService>> handler) {

    if (ruleService == null) {

      String serviceAddress = address + ".RuleService";

      RuleServiceImpl serviceImpl = new RuleServiceImpl(vertx, serviceAddress, kieSession);
      ruleServiceConsumer = new ServiceBinder(vertx)
          .setAddress(serviceAddress)
          .register(RuleService.class, serviceImpl);
      ruleService = RuleService.createProxy(vertx, serviceAddress);
    }
    handler.handle(Future.succeededFuture(ruleService));

    return this;
  }

  @Override
  public void close() {

    if (processServiceConsumer != null) new ServiceBinder(vertx).unregister(processServiceConsumer);
    if (ruleServiceConsumer != null) new ServiceBinder(vertx).unregister(ruleServiceConsumer);
  }
}
