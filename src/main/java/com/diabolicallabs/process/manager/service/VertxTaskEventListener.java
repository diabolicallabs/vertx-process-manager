package com.diabolicallabs.process.manager.service;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.kie.api.task.TaskEvent;
import org.kie.api.task.TaskLifeCycleEventListener;

public class VertxTaskEventListener implements TaskLifeCycleEventListener {

  public static final String TASK_ADDED = "TaskAdded";
  public static final String TASK_CLAIMED = "TaskClaimed";
  public static final String TASK_COMPLETED = "TaskCompleted";
  public static final String TASK_EXITED = "TaskExited";
  public static final String TASK_NOMINATED = "TaskNominated";
  public static final String TASK_ACTIVATED = "TaskActivated";
  public static final String TASK_SKIPPED = "TaskSkipped";
  public static final String TASK_STARTED = "TaskStarted";
  public static final String TASK_STOPPED = "TaskStopped";
  public static final String TASK_FAILED = "TaskFailed";
  public static final String TASK_RELEASED = "TaskReleased";
  public static final String TASK_RESUMED = "TaskResumed";
  public static final String TASK_SUSPENDED = "TaskSuspended";
  public static final String TASK_FORWARDED = "TaskForwarded";
  public static final String TASK_DELEGATED = "TaskDelegated";

  Logger logger = LoggerFactory.getLogger(VertxTaskEventListener.class);

  private String publishAddress;
  private Vertx vertx;

  public VertxTaskEventListener(Vertx vertx, String publishAddress) {
    this.vertx = vertx;
    this.publishAddress = publishAddress;
    logger.info("Creating a new listener for publish address " + publishAddress);
  }

  @Override
  public void beforeTaskActivatedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void beforeTaskClaimedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void beforeTaskSkippedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void beforeTaskStartedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void beforeTaskStoppedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void beforeTaskCompletedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void beforeTaskFailedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void beforeTaskAddedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void beforeTaskExitedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void beforeTaskReleasedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void beforeTaskResumedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void beforeTaskSuspendedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void beforeTaskForwardedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void beforeTaskDelegatedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void beforeTaskNominatedEvent(TaskEvent taskEvent) {
  }

  @Override
  public void afterTaskActivatedEvent(TaskEvent taskEvent) {

    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_ACTIVATED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }

  @Override
  public void afterTaskClaimedEvent(TaskEvent taskEvent) {

    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_CLAIMED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }

  @Override
  public void afterTaskSkippedEvent(TaskEvent taskEvent) {

    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_SKIPPED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }

  @Override
  public void afterTaskStartedEvent(TaskEvent taskEvent) {

    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_STARTED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }

  @Override
  public void afterTaskStoppedEvent(TaskEvent taskEvent) {

    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_STOPPED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }

  @Override
  public void afterTaskCompletedEvent(TaskEvent taskEvent) {

    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_COMPLETED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }

  @Override
  public void afterTaskFailedEvent(TaskEvent taskEvent) {

    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_FAILED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }

  @Override
  public void afterTaskAddedEvent(TaskEvent taskEvent) {

    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_ADDED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }

  @Override
  public void afterTaskExitedEvent(TaskEvent taskEvent) {

    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_EXITED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }

  @Override
  public void afterTaskReleasedEvent(TaskEvent taskEvent) {

    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_RELEASED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }

  @Override
  public void afterTaskResumedEvent(TaskEvent taskEvent) {
    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_RESUMED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }

  @Override
  public void afterTaskSuspendedEvent(TaskEvent taskEvent) {
    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_SUSPENDED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }

  @Override
  public void afterTaskForwardedEvent(TaskEvent taskEvent) {
    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_FORWARDED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }

  @Override
  public void afterTaskDelegatedEvent(TaskEvent taskEvent) {
    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_DELEGATED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }

  @Override
  public void afterTaskNominatedEvent(TaskEvent taskEvent) {
    DeliveryOptions options = new DeliveryOptions().addHeader("event", TASK_NOMINATED);
    vertx.eventBus().publish(publishAddress, new UserTask(taskEvent.getTask()).toJson(), options);
  }
}
