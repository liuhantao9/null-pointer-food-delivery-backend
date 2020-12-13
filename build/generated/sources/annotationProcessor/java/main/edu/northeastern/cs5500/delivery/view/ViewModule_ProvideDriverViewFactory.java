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
public final class ViewModule_ProvideDriverViewFactory implements Factory<View> {
  private final ViewModule module;

  private final Provider<DriverView> driverViewProvider;

  public ViewModule_ProvideDriverViewFactory(ViewModule module,
      Provider<DriverView> driverViewProvider) {
    this.module = module;
    this.driverViewProvider = driverViewProvider;
  }

  @Override
  public View get() {
    return provideDriverView(module, driverViewProvider.get());
  }

  public static ViewModule_ProvideDriverViewFactory create(ViewModule module,
      Provider<DriverView> driverViewProvider) {
    return new ViewModule_ProvideDriverViewFactory(module, driverViewProvider);
  }

  public static View provideDriverView(ViewModule instance, DriverView driverView) {
    return Preconditions.checkNotNull(instance.provideDriverView(driverView), "Cannot return null from a non-@Nullable @Provides method");
  }
}
