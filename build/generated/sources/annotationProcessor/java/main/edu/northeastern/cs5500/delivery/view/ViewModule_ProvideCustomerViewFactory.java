package edu.northeastern.cs5500.delivery.view;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class ViewModule_ProvideCustomerViewFactory implements Factory<View> {
  private final ViewModule module;

  private final Provider<CustomerView> customerViewProvider;

  public ViewModule_ProvideCustomerViewFactory(ViewModule module,
      Provider<CustomerView> customerViewProvider) {
    this.module = module;
    this.customerViewProvider = customerViewProvider;
  }

  @Override
  public View get() {
    return provideCustomerView(module, customerViewProvider.get());
  }

  public static ViewModule_ProvideCustomerViewFactory create(ViewModule module,
      Provider<CustomerView> customerViewProvider) {
    return new ViewModule_ProvideCustomerViewFactory(module, customerViewProvider);
  }

  public static View provideCustomerView(ViewModule instance, CustomerView customerView) {
    return Preconditions.checkNotNull(instance.provideCustomerView(customerView), "Cannot return null from a non-@Nullable @Provides method");
  }
}
