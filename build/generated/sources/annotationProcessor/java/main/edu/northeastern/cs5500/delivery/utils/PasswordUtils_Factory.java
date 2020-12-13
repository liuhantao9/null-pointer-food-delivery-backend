package edu.northeastern.cs5500.delivery.utils;

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
public final class PasswordUtils_Factory implements Factory<PasswordUtils> {
  @Override
  public PasswordUtils get() {
    return newInstance();
  }

  public static PasswordUtils_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PasswordUtils newInstance() {
    return new PasswordUtils();
  }

  private static final class InstanceHolder {
    private static final PasswordUtils_Factory INSTANCE = new PasswordUtils_Factory();
  }
}
