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
public final class ViewModule_ProvideStatusViewFactory implements Factory<View> {
  private final ViewModule module;

  private final Provider<StatusView> statusViewProvider;

  public ViewModule_ProvideStatusViewFactory(ViewModule module,
      Provider<StatusView> statusViewProvider) {
    this.module = module;
    this.statusViewProvider = statusViewProvider;
  }

  @Override
  public View get() {
    return provideStatusView(module, statusViewProvider.get());
  }

  public static ViewModule_ProvideStatusViewFactory create(ViewModule module,
      Provider<StatusView> statusViewProvider) {
    return new ViewModule_ProvideStatusViewFactory(module, statusViewProvider);
  }

  public static View provideStatusView(ViewModule instance, StatusView statusView) {
    return Preconditions.checkNotNull(instance.provideStatusView(statusView), "Cannot return null from a non-@Nullable @Provides method");
  }
}
