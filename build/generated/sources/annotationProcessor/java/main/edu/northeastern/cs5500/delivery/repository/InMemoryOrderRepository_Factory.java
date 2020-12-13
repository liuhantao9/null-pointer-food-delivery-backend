package edu.northeastern.cs5500.delivery.repository;

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
public final class InMemoryOrderRepository_Factory implements Factory<InMemoryOrderRepository> {
  @Override
  public InMemoryOrderRepository get() {
    return newInstance();
  }

  public static InMemoryOrderRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static InMemoryOrderRepository newInstance() {
    return new InMemoryOrderRepository();
  }

  private static final class InstanceHolder {
    private static final InMemoryOrderRepository_Factory INSTANCE = new InMemoryOrderRepository_Factory();
  }
}
