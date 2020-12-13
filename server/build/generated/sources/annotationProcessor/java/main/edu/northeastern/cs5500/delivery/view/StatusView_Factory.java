package edu.northeastern.cs5500.delivery.view;

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
public final class StatusView_Factory implements Factory<StatusView> {
  @Override
  public StatusView get() {
    return newInstance();
  }

  public static StatusView_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static StatusView newInstance() {
    return new StatusView();
  }

  private static final class InstanceHolder {
    private static final StatusView_Factory INSTANCE = new StatusView_Factory();
  }
}
