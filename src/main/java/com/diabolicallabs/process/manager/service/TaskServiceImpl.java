package com.diabolicallabs.process.manager.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.Map;

public class TaskServiceImpl implements TaskService {

  Vertx vertx;
  String address;
  org.kie.api.task.TaskService taskService;

  public TaskServiceImpl(Vertx vertx, String address, org.kie.api.task.TaskService taskService) {
    this.vertx = vertx;
    this.address = address;
    this.taskService = taskService;
  }

  @Override
  public TaskService addComment(Long taskId, String userId, String comment, Handler<AsyncResult<Void>> handler) {

    taskService.addComment(taskId, userId, comment);
    handler.handle(Future.succeededFuture());

    return this;
  }

  @Override
  public TaskService claim(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {

    taskService.claim(taskId, userId);
    handler.handle(Future.succeededFuture());

    return this;
  }

  @Override
  public TaskService complete(Long taskId, String userId, JsonObject data, Handler<AsyncResult<Void>> handler) {

    taskService.complete(taskId, userId, data.getMap());
    handler.handle(Future.succeededFuture());

    return this;
  }

  @Override
  public TaskService delegate(Long taskId, String userId, String newUserId, Handler<AsyncResult<Void>> handler) {

    taskService.delegate(taskId, userId, newUserId);
    handler.handle(Future.succeededFuture());

    return this;
  }

  @Override
  public TaskService exit(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    taskService.exit(taskId, userId);
    handler.handle(Future.succeededFuture());
    return this;
  }

  @Override
  public TaskService fail(Long taskId, String userId, JsonObject data, Handler<AsyncResult<Void>> handler) {
    taskService.fail(taskId, userId, data.getMap());
    handler.handle(Future.succeededFuture());
    return this;
  }

  @Override
  public TaskService forward(Long taskId, String userId, String newUserId, Handler<AsyncResult<Void>> handler) {
    taskService.forward(taskId, userId, newUserId);
    handler.handle(Future.succeededFuture());
    return this;
  }

  @Override
  public TaskService getContent(Long taskId, Handler<AsyncResult<JsonObject>> handler) {

    Map<String, Object> content = taskService.getTaskContent(taskId);
    JsonObject json = new JsonObject(content);
    handler.handle(Future.succeededFuture(json));

    return this;
  }

  @Override
  public TaskService release(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    taskService.release(taskId, userId);
    handler.handle(Future.succeededFuture());
    return this;
  }

  @Override
  public TaskService resume(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    taskService.resume(taskId, userId);
    handler.handle(Future.succeededFuture());
    return this;
  }

  @Override
  public TaskService skip(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    taskService.skip(taskId, userId);
    handler.handle(Future.succeededFuture());
    return this;
  }

  @Override
  public TaskService start(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    taskService.start(taskId, userId);
    handler.handle(Future.succeededFuture());
    return this;
  }

  @Override
  public TaskService stop(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    taskService.stop(taskId, userId);
    handler.handle(Future.succeededFuture());
    return this;
  }

  @Override
  public TaskService suspend(Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    taskService.suspend(taskId, userId);
    handler.handle(Future.succeededFuture());
    return this;
  }
}
