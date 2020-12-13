package edu.northeastern.cs5500.delivery.view;

import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import edu.northeastern.cs5500.delivery.JsonTransformer;
import edu.northeastern.cs5500.delivery.controller.OrderController;
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
public final class OrderView_MembersInjector implements MembersInjector<OrderView> {
  private final Provider<JsonTransformer> jsonTransformerProvider;

  private final Provider<OrderController> orderControllerProvider;

  public OrderView_MembersInjector(Provider<JsonTransformer> jsonTransformerProvider,
      Provider<OrderController> orderControllerProvider) {
    this.jsonTransformerProvider = jsonTransformerProvider;
    this.orderControllerProvider = orderControllerProvider;
  }

  public static MembersInjector<OrderView> create(Provider<JsonTransformer> jsonTransformerProvider,
      Provider<OrderController> orderControllerProvider) {
    return new OrderView_MembersInjector(jsonTransformerProvider, orderControllerProvider);}

  @Override
  public void injectMembers(OrderView instance) {
    injectJsonTransformer(instance, jsonTransformerProvider.get());
    injectOrderController(instance, orderControllerProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.delivery.view.OrderView.jsonTransformer")
  public static void injectJsonTransformer(OrderView instance, JsonTransformer jsonTransformer) {
    instance.jsonTransformer = jsonTransformer;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.delivery.view.OrderView.orderController")
  public static void injectOrderController(OrderView instance, OrderController orderController) {
    instance.orderController = orderController;
  }
}
