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
public final class ViewModule_ProvideOrderViewFactory implements Factory<View> {
  private final ViewModule module;

  private final Provider<OrderView> orderViewProvider;

  public ViewModule_ProvideOrderViewFactory(ViewModule module,
      Provider<OrderView> orderViewProvider) {
    this.module = module;
    this.orderViewProvider = orderViewProvider;
  }

  @Override
  public View get() {
    return provideOrderView(module, orderViewProvider.get());
  }

  public static ViewModule_ProvideOrderViewFactory create(ViewModule module,
      Provider<OrderView> orderViewProvider) {
    return new ViewModule_ProvideOrderViewFactory(module, orderViewProvider);
  }

  public static View provideOrderView(ViewModule instance, OrderView orderView) {
    return Preconditions.checkNotNull(instance.provideOrderView(orderView), "Cannot return null from a non-@Nullable @Provides method");
  }
}
