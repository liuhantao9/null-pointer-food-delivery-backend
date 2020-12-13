package edu.northeastern.cs5500.delivery.model;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ModelModule_ProvideCustomerClassFactory implements Factory<Class<Customer>> {
  private final ModelModule module;

  public ModelModule_ProvideCustomerClassFactory(ModelModule module) {
    this.module = module;
  }

  @Override
  public Class<Customer> get() {
    return provideCustomerClass(module);
  }

  public static ModelModule_ProvideCustomerClassFactory create(ModelModule module) {
    return new ModelModule_ProvideCustomerClassFactory(module);
  }

  public static Class<Customer> provideCustomerClass(ModelModule instance) {
    return Preconditions.checkNotNull(instance.provideCustomerClass(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
