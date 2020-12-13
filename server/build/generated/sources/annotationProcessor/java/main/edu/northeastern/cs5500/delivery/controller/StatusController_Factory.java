package edu.northeastern.cs5500.delivery.controller;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class StatusController_Factory implements Factory<StatusController> {
  @Override
  public StatusController get() {
    return newInstance();
  }

  public static StatusController_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static StatusController newInstance() {
    return new StatusController();
  }

  private static final class InstanceHolder {
    private static final StatusController_Factory INSTANCE = new StatusController_Factory();
  }
}
