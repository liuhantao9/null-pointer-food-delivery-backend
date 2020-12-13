// package edu.northeastern.cs5500.delivery.repository;

// import dagger.Module;
// import dagger.Provides;
// import edu.northeastern.cs5500.delivery.model.Customer;

// @Module
// public class RepositoryModule {
//     @Provides
//     public GenericRepository<Customer> provideCustomerRepository() {
//         return new InMemoryRepository<>();
//     }
// }

// Here's an example of how you imght swap out the in-memory repository for a database-backed
// repository:

package edu.northeastern.cs5500.delivery.repository;

import dagger.Module;
import dagger.Provides;
import edu.northeastern.cs5500.delivery.model.Customer;
import edu.northeastern.cs5500.delivery.model.Driver;
import edu.northeastern.cs5500.delivery.model.Order;
import edu.northeastern.cs5500.delivery.model.Restaurant;
import edu.northeastern.cs5500.delivery.service.MongoDBService;

@Module
public class RepositoryModule {
    // @Provides
    // public GenericOrderRepository provideMongoDBOrderRepository(MongoDBService mongoDBService) {
    //     return new MongoDBOrderRepository(Order.class, mongoDBService);
    // }

    @Provides
    public GenericRepository<Customer> provideCustomerRepository(MongoDBService mongoDBService) {
        return new MongoDBRepository<>(Customer.class, mongoDBService);
    }

    @Provides
    public GenericOrderRepository provideOrderRepository(MongoDBService mongoDBService) {
        // return new MongoDBRepository<>(Order.class, mongoDBService);
        return new MongoDBOrderRepository(Order.class, mongoDBService);
    }

    @Provides
    public GenericRepository<Restaurant> provideRestaurantRepository(
            MongoDBService mongoDBService) {
        return new MongoDBRepository<>(Restaurant.class, mongoDBService);
    }

    @Provides
    public GenericRepository<Driver> provideDriverRepository(MongoDBService mongoDBService) {
        return new MongoDBRepository<>(Driver.class, mongoDBService);
    }
}
