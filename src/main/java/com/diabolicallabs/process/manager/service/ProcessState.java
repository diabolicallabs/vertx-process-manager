package com.diabolicallabs.process.manager.service;

import org.kie.api.runtime.process.ProcessInstance;

public enum ProcessState {

  ABORTED(ProcessInstance.STATE_ABORTED),
  ACTIVE(ProcessInstance.STATE_ACTIVE),
  COMPLETED(ProcessInstance.STATE_COMPLETED),
  PENDING(ProcessInstance.STATE_PENDING),
  SUSPENDED(ProcessInstance.STATE_SUSPENDED);

  private int value;

  ProcessState(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
