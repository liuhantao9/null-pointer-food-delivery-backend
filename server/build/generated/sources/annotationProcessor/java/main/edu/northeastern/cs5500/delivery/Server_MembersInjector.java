package edu.northeastern.cs5500.delivery;

import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
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
public final class Server_MembersInjector implements MembersInjector<Server> {
  private final Provider<Set<View>> viewsProvider;

  public Server_MembersInjector(Provider<Set<View>> viewsProvider) {
    this.viewsProvider = viewsProvider;
  }

  public static MembersInjector<Server> create(Provider<Set<View>> viewsProvider) {
    return new Server_MembersInjector(viewsProvider);}

  @Override
  public void injectMembers(Server instance) {
    injectViews(instance, viewsProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.delivery.Server.views")
  public static void injectViews(Server instance, Set<View> views) {
    instance.views = views;
  }
}
