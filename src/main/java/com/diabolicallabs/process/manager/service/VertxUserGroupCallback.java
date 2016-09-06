package com.diabolicallabs.process.manager.service;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.kie.api.task.UserGroupCallback;

import java.util.List;

public class VertxUserGroupCallback implements UserGroupCallback {

  Logger logger = LoggerFactory.getLogger(VertxTaskEventListener.class);

  @Override
  public boolean existsUser(String s) {

    logger.debug("Check if user exists: " + s);
    return true;
  }

  @Override
  public boolean existsGroup(String s) {
    logger.debug("Check if group exists: " + s);
    return true;
  }

  @Override
  public List<String> getGroupsForUser(String s, List<String> list, List<String> list1) {
    logger.debug("Check if group exists for user: " + s);
    return list;
  }
}
