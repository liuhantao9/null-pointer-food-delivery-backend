package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.model.Order;
import edu.northeastern.cs5500.delivery.model.OrderStatus;
import edu.northeastern.cs5500.delivery.repository.GenericOrderRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderController {
    // private final GenericRepository<Order> orderRepo;
    private final GenericOrderRepository orderRepo;

    @Inject
    OrderController(GenericOrderRepository orders) {
        this.orderRepo = orders;

        log.info("OrderController > construct");
    }

    @Nonnull
    public Order addNewOrder(Order order) throws Exception {

        if (!order.isValid()) {
            throw new Exception("InvalidOrderException");
        }

        String id = UUID.randomUUID().toString();
        order.setId(id);

        Order orderFromRepo = orderRepo.get(id);
        if (id != null && orderFromRepo != null) {
            return orderFromRepo;
        }

        orderRepo.add(order);
        return order;
    }

    private Order updateDriverIdAndStatus(Order order, String driverId, String status)
            throws Exception {
        OrderStatus orderStatus = OrderStatus.valueOf(status);
        order.setOrderStatus(orderStatus);
        order.setDriverId(driverId);
        orderRepo.update(order);
        return order;
    }

    private Order updateStatus(Order order, String status) throws Exception {
        OrderStatus orderStatus = OrderStatus.valueOf(status);
        return updateStatus(order, orderStatus);
    }

    private Order updateStatus(Order order, OrderStatus orderStatus) throws Exception {
        order.setOrderStatus(orderStatus);
        orderRepo.update(order);
        return order;
    }

    public List<Order> getOrdersWithinDateForCustomer(
            String userId, Instant startTime, Instant endTime) {
        return this.orderRepo.getOrdersWithinDateForCustomer(userId, startTime, endTime);
    }

    public List<Order> getOrdersWithinDateForDriver(
            String driverId, Instant startTime, Instant endTime) {
        return this.orderRepo.getOrdersWithinDateForDriver(driverId, startTime, endTime);
    }

    public List<Order> getOrdersWithinDateForRestaurant(
            String restaurantId, Instant startTime, Instant endTime) {
        return this.orderRepo.getOrdersWithinDateForRestaurant(restaurantId, startTime, endTime);
    }

    public List<Order> getOrdersWithStatusForRestaurant(
            String restaurantId, OrderStatus orderStatus) {
        return this.orderRepo.getOrdersWithStatusForRestaurant(restaurantId, orderStatus);
    }

    public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
        return this.orderRepo.getOrdersByStatus(orderStatus);
    }

    public List<Order> getOrdersByStatusAndDriverId(OrderStatus orderStatus, String driverId) {
        return this.orderRepo.getOrdersByStatusAndDriverId(orderStatus, driverId);
    }

    public List<Order> getAll() {
        return new ArrayList(this.orderRepo.getAll());
    }

    public Order submitOrder(String orderId) throws Exception {
        Order order = orderRepo.get(orderId);
        return updateStatus(order, "SUBMITTED");
    }

    public Order cancelOrder(String orderId) throws Exception {
        Order order = orderRepo.get(orderId);
        return updateStatus(order, "CANCELED");
    }

    public Order validateOrder(String orderId) throws Exception {
        Order order = orderRepo.get(orderId);
        return updateStatus(order, "VALIDATED");
    }

    public Order preparingOrder(String orderId) throws Exception {
        Order order = orderRepo.get(orderId);
        return updateStatus(order, "PREPARING");
    }

    public Order waitingOrder(String orderId) throws Exception {
        Order order = orderRepo.get(orderId);
        return updateStatus(order, "WAITING");
    }

    public Order deliveringOrder(String orderId, String driverId) throws Exception {
        Order order = orderRepo.get(orderId);
        return updateDriverIdAndStatus(order, driverId, "DELIVERING");
    }

    public Order finishOrder(String orderId, String driverId) throws Exception {
        Order order = orderRepo.get(orderId);
        return updateDriverIdAndStatus(order, driverId, "FINISHED");
    }
}
