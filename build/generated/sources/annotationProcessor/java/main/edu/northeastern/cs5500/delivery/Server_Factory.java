package edu.northeastern.cs5500.delivery;

import dagger.internal.Factory;
import edu.northeastern.cs5500.delivery.view.View;
import java.util.Set;
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
public final class Server_Factory implements Factory<Server> {
  private final Provider<Set<View>> viewsProvider;

  public Server_Factory(Provider<Set<View>> viewsProvider) {
    this.viewsProvider = viewsProvider;
  }

  @Override
  public Server get() {
    Server instance = newInstance();
    Server_MembersInjector.injectViews(instance, viewsProvider.get());
    return instance;
  }

  public static Server_Factory create(Provider<Set<View>> viewsProvider) {
    return new Server_Factory(viewsProvider);
  }

  public static Server newInstance() {
    return new Server();
  }
}
