package edu.northeastern.cs5500.delivery.repository;

import dagger.internal.Factory;
import edu.northeastern.cs5500.delivery.model.Model;
import javax.annotation.processing.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class InMemoryRepository_Factory<T extends Model> implements Factory<InMemoryRepository<T>> {
  @Override
  public InMemoryRepository<T> get() {
    return newInstance();
  }

  @SuppressWarnings("unchecked")
  public static <T extends Model> InMemoryRepository_Factory<T> create() {
    return InstanceHolder.INSTANCE;
  }

  public static <T extends Model> InMemoryRepository<T> newInstance() {
    return new InMemoryRepository<T>();
  }

  private static final class InstanceHolder {
    @SuppressWarnings("rawtypes")
    private static final InMemoryRepository_Factory INSTANCE = new InMemoryRepository_Factory();
  }
}
