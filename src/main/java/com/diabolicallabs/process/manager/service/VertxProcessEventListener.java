package com.diabolicallabs.process.manager.service;

import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.kie.api.event.process.*;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.api.runtime.process.ProcessInstance;

public class VertxProcessEventListener implements ProcessEventListener {

  Vertx vertx;
  Logger logger = LoggerFactory.getLogger(VertxProcessEventListener.class);

  public VertxProcessEventListener(Vertx vertx) {
    this.vertx = vertx;
  }

  @Override
  public void beforeProcessStarted(ProcessStartedEvent processStartedEvent) {

    ProcessInstance instance = processStartedEvent.getProcessInstance();
    logger.debug("About to start {0}", instance.getProcessName());
  }

  @Override
  public void afterProcessStarted(ProcessStartedEvent processStartedEvent) {
    ProcessInstance instance = processStartedEvent.getProcessInstance();
    logger.debug("Started {0} with id {1}", instance.getProcessName(), instance.getId());
  }

  @Override
  public void beforeProcessCompleted(ProcessCompletedEvent processCompletedEvent) {
    ProcessInstance instance = processCompletedEvent.getProcessInstance();
    logger.debug("About to complete {0} with id {1}", instance.getProcessName(), instance.getId());
  }

  @Override
  public void afterProcessCompleted(ProcessCompletedEvent processCompletedEvent) {

    ProcessInstance instance = processCompletedEvent.getProcessInstance();
    logger.debug("Completed {0} with id {1}", instance.getProcessName(), instance.getId());

    Long id = processCompletedEvent.getProcessInstance().getId();
    vertx.eventBus().send("kie.process.instance." + id + ".complete", "Complete");
  }

  @Override
  public void beforeNodeTriggered(ProcessNodeTriggeredEvent processNodeTriggeredEvent) {
    NodeInstance nodeInstance = processNodeTriggeredEvent.getNodeInstance();
    ProcessInstance processInstance = nodeInstance.getProcessInstance();
    logger.debug("About to trigger node {0} from {1} {2}", nodeInstance.getNodeName(), processInstance.getProcessName(), processInstance.getId());
  }

  @Override
  public void afterNodeTriggered(ProcessNodeTriggeredEvent processNodeTriggeredEvent) {
    NodeInstance nodeInstance = processNodeTriggeredEvent.getNodeInstance();
    ProcessInstance processInstance = nodeInstance.getProcessInstance();
    logger.debug("After node triggered {0} from {1} {2}", nodeInstance.getNodeName(), processInstance.getProcessName(), processInstance.getId());
  }

  @Override
  public void beforeNodeLeft(ProcessNodeLeftEvent processNodeLeftEvent) {
    NodeInstance nodeInstance = processNodeLeftEvent.getNodeInstance();
    ProcessInstance processInstance = nodeInstance.getProcessInstance();
    logger.debug("Before node left {0} from {1} {2}", nodeInstance.getNodeName(), processInstance.getProcessName(), processInstance.getId());
  }

  @Override
  public void afterNodeLeft(ProcessNodeLeftEvent processNodeLeftEvent) {
    NodeInstance nodeInstance = processNodeLeftEvent.getNodeInstance();
    ProcessInstance processInstance = nodeInstance.getProcessInstance();
    logger.debug("After node left {0} from {1} {2}", nodeInstance.getNodeName(), processInstance.getProcessName(), processInstance.getId());
  }

  @Override
  public void beforeVariableChanged(ProcessVariableChangedEvent processVariableChangedEvent) {
    String id = processVariableChangedEvent.getVariableId();
    Object oldValue = processVariableChangedEvent.getOldValue();
    Object newValue = processVariableChangedEvent.getNewValue();
    logger.debug("Variable {0} about to change from {1} to {2}", id, oldValue, newValue);
  }

  @Override
  public void afterVariableChanged(ProcessVariableChangedEvent processVariableChangedEvent) {
    String id = processVariableChangedEvent.getVariableId();
    Object oldValue = processVariableChangedEvent.getOldValue();
    Object newValue = processVariableChangedEvent.getNewValue();
    logger.debug("Variable {0} changed from {1} to {2}", id, oldValue, newValue);
  }
}
