package edu.northeastern.cs5500.delivery.repository;

import edu.northeastern.cs5500.delivery.model.Order;
import edu.northeastern.cs5500.delivery.model.OrderStatus;
import java.time.Instant;
import java.util.List;

public interface GenericOrderRepository extends GenericRepository<Order> {
    public List<Order> getOrdersWithinDateForCustomer(
            String userId, Instant startTime, Instant endTime);

    public List<Order> getOrdersWithinDateForRestaurant(
            String restaurantId, Instant startTime, Instant endTime);

    public List<Order> getOrdersWithStatusForRestaurant(
            String restaurantId, OrderStatus orderStatus);

    public List<Order> getOrdersWithinDateForDriver(
            String driverId, Instant startTime, Instant endTime);

    public List<Order> getOrdersByStatus(OrderStatus orderStatus);

    public List<Order> getOrdersByStatusAndDriverId(OrderStatus orderStatus, String driverId);
}
