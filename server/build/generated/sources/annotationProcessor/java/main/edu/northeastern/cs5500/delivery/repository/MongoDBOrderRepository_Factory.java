package edu.northeastern.cs5500.delivery.repository;

import dagger.internal.Factory;
import edu.northeastern.cs5500.delivery.model.Order;
import edu.northeastern.cs5500.delivery.service.MongoDBService;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MongoDBOrderRepository_Factory implements Factory<MongoDBOrderRepository> {
  private final Provider<Class<Order>> clazzProvider;

  private final Provider<MongoDBService> mongoDBServiceProvider;

  public MongoDBOrderRepository_Factory(Provider<Class<Order>> clazzProvider,
      Provider<MongoDBService> mongoDBServiceProvider) {
    this.clazzProvider = clazzProvider;
    this.mongoDBServiceProvider = mongoDBServiceProvider;
  }

  @Override
  public MongoDBOrderRepository get() {
    return newInstance(clazzProvider.get(), mongoDBServiceProvider.get());
  }

  public static MongoDBOrderRepository_Factory create(Provider<Class<Order>> clazzProvider,
      Provider<MongoDBService> mongoDBServiceProvider) {
    return new MongoDBOrderRepository_Factory(clazzProvider, mongoDBServiceProvider);
  }

  public static MongoDBOrderRepository newInstance(Class<Order> clazz,
      MongoDBService mongoDBService) {
    return new MongoDBOrderRepository(clazz, mongoDBService);
  }
}
