package edu.northeastern.cs5500.delivery;

import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.SetBuilder;
import edu.northeastern.cs5500.delivery.controller.CustomerController;
import edu.northeastern.cs5500.delivery.controller.CustomerController_Factory;
import edu.northeastern.cs5500.delivery.controller.DriverController;
import edu.northeastern.cs5500.delivery.controller.DriverController_Factory;
import edu.northeastern.cs5500.delivery.controller.OrderController;
import edu.northeastern.cs5500.delivery.controller.OrderController_Factory;
import edu.northeastern.cs5500.delivery.controller.RestaurantController;
import edu.northeastern.cs5500.delivery.controller.RestaurantController_Factory;
import edu.northeastern.cs5500.delivery.model.Customer;
import edu.northeastern.cs5500.delivery.model.Driver;
import edu.northeastern.cs5500.delivery.model.ModelModule;
import edu.northeastern.cs5500.delivery.model.Restaurant;
import edu.northeastern.cs5500.delivery.repository.GenericOrderRepository;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import edu.northeastern.cs5500.delivery.repository.RepositoryModule;
import edu.northeastern.cs5500.delivery.repository.RepositoryModule_ProvideCustomerRepositoryFactory;
import edu.northeastern.cs5500.delivery.repository.RepositoryModule_ProvideDriverRepositoryFactory;
import edu.northeastern.cs5500.delivery.repository.RepositoryModule_ProvideOrderRepositoryFactory;
import edu.northeastern.cs5500.delivery.repository.RepositoryModule_ProvideRestaurantRepositoryFactory;
import edu.northeastern.cs5500.delivery.service.MongoDBService;
import edu.northeastern.cs5500.delivery.service.MongoDBService_Factory;
import edu.northeastern.cs5500.delivery.view.CustomerView;
import edu.northeastern.cs5500.delivery.view.CustomerView_Factory;
import edu.northeastern.cs5500.delivery.view.DriverView;
import edu.northeastern.cs5500.delivery.view.DriverView_Factory;
import edu.northeastern.cs5500.delivery.view.OrderView;
import edu.northeastern.cs5500.delivery.view.OrderView_Factory;
import edu.northeastern.cs5500.delivery.view.RestaurantView;
import edu.northeastern.cs5500.delivery.view.RestaurantView_Factory;
import edu.northeastern.cs5500.delivery.view.StatusView;
import edu.northeastern.cs5500.delivery.view.StatusView_Factory;
import edu.northeastern.cs5500.delivery.view.View;
import edu.northeastern.cs5500.delivery.view.ViewModule;
import edu.northeastern.cs5500.delivery.view.ViewModule_ProvideCustomerViewFactory;
import edu.northeastern.cs5500.delivery.view.ViewModule_ProvideDriverViewFactory;
import edu.northeastern.cs5500.delivery.view.ViewModule_ProvideOrderViewFactory;
import edu.northeastern.cs5500.delivery.view.ViewModule_ProvideRestaurantViewFactory;
import edu.northeastern.cs5500.delivery.view.ViewModule_ProvideStatusViewFactory;
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
final class DaggerServerComponent implements ServerComponent {
  private final ViewModule viewModule;

  private Provider<MongoDBService> mongoDBServiceProvider;

  private Provider<GenericRepository<Customer>> provideCustomerRepositoryProvider;

  private Provider<CustomerController> customerControllerProvider;

  private Provider<CustomerView> customerViewProvider;

  private Provider<StatusView> statusViewProvider;

  private Provider<GenericOrderRepository> provideOrderRepositoryProvider;

  private Provider<OrderController> orderControllerProvider;

  private Provider<OrderView> orderViewProvider;

  private Provider<GenericRepository<Restaurant>> provideRestaurantRepositoryProvider;

  private Provider<RestaurantController> restaurantControllerProvider;

  private Provider<RestaurantView> restaurantViewProvider;

  private Provider<GenericRepository<Driver>> provideDriverRepositoryProvider;

  private Provider<DriverController> driverControllerProvider;

  private Provider<DriverView> driverViewProvider;

  private DaggerServerComponent(ViewModule viewModuleParam,
      RepositoryModule repositoryModuleParam) {
    this.viewModule = viewModuleParam;
    initialize(viewModuleParam, repositoryModuleParam);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static ServerComponent create() {
    return new Builder().build();
  }

  private View getProvideCustomerView() {
    return ViewModule_ProvideCustomerViewFactory.provideCustomerView(viewModule, customerViewProvider.get());}

  private View getProvideStatusView() {
    return ViewModule_ProvideStatusViewFactory.provideStatusView(viewModule, statusViewProvider.get());}

  private View getProvideOrderView() {
    return ViewModule_ProvideOrderViewFactory.provideOrderView(viewModule, orderViewProvider.get());}

  private View getProvideRestaurantView() {
    return ViewModule_ProvideRestaurantViewFactory.provideRestaurantView(viewModule, restaurantViewProvider.get());}

  private View getProvideDriverView() {
    return ViewModule_ProvideDriverViewFactory.provideDriverView(viewModule, driverViewProvider.get());}

  private Set<View> getSetOfView() {
    return SetBuilder.<View>newSetBuilder(5).add(getProvideCustomerView()).add(getProvideStatusView()).add(getProvideOrderView()).add(getProvideRestaurantView()).add(getProvideDriverView()).build();}

  @SuppressWarnings("unchecked")
  private void initialize(final ViewModule viewModuleParam,
      final RepositoryModule repositoryModuleParam) {
    this.mongoDBServiceProvider = DoubleCheck.provider(MongoDBService_Factory.create());
    this.provideCustomerRepositoryProvider = RepositoryModule_ProvideCustomerRepositoryFactory.create(repositoryModuleParam, mongoDBServiceProvider);
    this.customerControllerProvider = CustomerController_Factory.create(provideCustomerRepositoryProvider);
    this.customerViewProvider = DoubleCheck.provider(CustomerView_Factory.create(JsonTransformer_Factory.create(), customerControllerProvider));
    this.statusViewProvider = DoubleCheck.provider(StatusView_Factory.create());
    this.provideOrderRepositoryProvider = RepositoryModule_ProvideOrderRepositoryFactory.create(repositoryModuleParam, mongoDBServiceProvider);
    this.orderControllerProvider = OrderController_Factory.create(provideOrderRepositoryProvider);
    this.orderViewProvider = DoubleCheck.provider(OrderView_Factory.create(JsonTransformer_Factory.create(), orderControllerProvider));
    this.provideRestaurantRepositoryProvider = RepositoryModule_ProvideRestaurantRepositoryFactory.create(repositoryModuleParam, mongoDBServiceProvider);
    this.restaurantControllerProvider = RestaurantController_Factory.create(provideRestaurantRepositoryProvider);
    this.restaurantViewProvider = DoubleCheck.provider(RestaurantView_Factory.create(JsonTransformer_Factory.create(), restaurantControllerProvider));
    this.provideDriverRepositoryProvider = RepositoryModule_ProvideDriverRepositoryFactory.create(repositoryModuleParam, mongoDBServiceProvider);
    this.driverControllerProvider = DriverController_Factory.create(provideDriverRepositoryProvider);
    this.driverViewProvider = DoubleCheck.provider(DriverView_Factory.create(JsonTransformer_Factory.create(), driverControllerProvider));
  }

  @Override
  public Server server() {
    return injectServer(Server_Factory.newInstance());}

  private Server injectServer(Server instance) {
    Server_MembersInjector.injectViews(instance, getSetOfView());
    return instance;
  }

  static final class Builder {
    private ViewModule viewModule;

    private RepositoryModule repositoryModule;

    private Builder() {
    }

    public Builder viewModule(ViewModule viewModule) {
      this.viewModule = Preconditions.checkNotNull(viewModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder modelModule(ModelModule modelModule) {
      Preconditions.checkNotNull(modelModule);
      return this;
    }

    public Builder repositoryModule(RepositoryModule repositoryModule) {
      this.repositoryModule = Preconditions.checkNotNull(repositoryModule);
      return this;
    }

    public ServerComponent build() {
      if (viewModule == null) {
        this.viewModule = new ViewModule();
      }
      if (repositoryModule == null) {
        this.repositoryModule = new RepositoryModule();
      }
      return new DaggerServerComponent(viewModule, repositoryModule);
    }
  }
}
