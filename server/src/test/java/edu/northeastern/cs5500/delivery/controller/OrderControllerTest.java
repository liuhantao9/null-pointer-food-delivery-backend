package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.model.Order;
import edu.northeastern.cs5500.delivery.model.OrderItem;
import edu.northeastern.cs5500.delivery.model.OrderStatus;
import edu.northeastern.cs5500.delivery.repository.InMemoryOrderRepository;
import edu.northeastern.cs5500.delivery.service.MongoDBService;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class OrderControllerTest {

    MongoDBService mongoDBService = new MongoDBService();

    private Order order = new Order();
    InMemoryOrderRepository orderRepo = new InMemoryOrderRepository();
    OrderController orderController = new OrderController(orderRepo);

    public OrderControllerTest() {
        order.setUserId("userId");
        order.setRestaurantId("restaurantId");
        order.setDriverId("driverId");
        List<OrderItem> list = new ArrayList<>();
        list.add(new OrderItem("name", 10.0, 1));
        order.setOrderItems(list);
        order.setCreated(new Date(System.currentTimeMillis()));
        order.setOrderStatus(OrderStatus.CANCELED);
    }

    @Test
    public void testAddNewOrder() throws Exception {
        Order testNull = new Order();
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    orderController.addNewOrder(testNull);
                });

        orderController.addNewOrder(order);
        Assertions.assertEquals(order, orderRepo.get(order.getId()));

        Assertions.assertTrue(!order.getId().isEmpty());

        Assertions.assertEquals(order, orderController.addNewOrder(order));

        orderRepo.delete(order.getId());
    }

    @Test
    public void testGetOrderWithDateForCustomer() throws Exception {

        orderController.addNewOrder(order);
        List<Order> res =
                orderController.getOrdersWithinDateForCustomer(
                        "userId",
                        Instant.ofEpochMilli(System.currentTimeMillis() - 10000000),
                        Instant.ofEpochMilli(System.currentTimeMillis() + 10000000));
        Assertions.assertTrue(res.size() == 1);

        res =
                orderController.getOrdersWithinDateForCustomer(
                        "hihi",
                        Instant.ofEpochMilli(System.currentTimeMillis() - 10000000),
                        Instant.ofEpochMilli(System.currentTimeMillis() + 10000000));
        Assertions.assertTrue(res.size() == 0);

        res =
                orderController.getOrdersWithinDateForCustomer(
                        "userId",
                        Instant.ofEpochMilli(System.currentTimeMillis() + 10000000),
                        Instant.ofEpochMilli(System.currentTimeMillis() + 10000001));
        Assertions.assertTrue(res.size() == 0);
        orderRepo.delete(order.getId());
    }

    @Test
    public void testGetOrdersWithinDateForDriver() throws Exception {
        orderController.addNewOrder(order);
        List<Order> res =
                orderController.getOrdersWithinDateForDriver(
                        "driverId",
                        Instant.ofEpochMilli(System.currentTimeMillis() - 10000000),
                        Instant.ofEpochMilli(System.currentTimeMillis() + 10000000));
        Assertions.assertTrue(res.size() == 1);

        res =
                orderController.getOrdersWithinDateForDriver(
                        "hihi",
                        Instant.ofEpochMilli(System.currentTimeMillis() - 10000000),
                        Instant.ofEpochMilli(System.currentTimeMillis() + 10000000));
        Assertions.assertTrue(res.size() == 0);

        res =
                orderController.getOrdersWithinDateForDriver(
                        "driverId",
                        Instant.ofEpochMilli(System.currentTimeMillis() + 10000000),
                        Instant.ofEpochMilli(System.currentTimeMillis() + 10000001));
        Assertions.assertTrue(res.size() == 0);
        orderRepo.delete(order.getId());
    }

    @Test
    public void testGetOrderWithinDateForRestaurant() throws Exception {
        orderController.addNewOrder(order);
        List<Order> res =
                orderController.getOrdersWithinDateForRestaurant(
                        "restaurantId",
                        Instant.ofEpochMilli(System.currentTimeMillis() - 10000000),
                        Instant.ofEpochMilli(System.currentTimeMillis() + 10000000));
        Assertions.assertTrue(res.size() == 1);

        res =
                orderController.getOrdersWithinDateForRestaurant(
                        "hihi",
                        Instant.ofEpochMilli(System.currentTimeMillis() - 10000000),
                        Instant.ofEpochMilli(System.currentTimeMillis() + 10000000));
        Assertions.assertTrue(res.size() == 0);

        res =
                orderController.getOrdersWithinDateForRestaurant(
                        "restaurantId",
                        Instant.ofEpochMilli(System.currentTimeMillis() + 10000000),
                        Instant.ofEpochMilli(System.currentTimeMillis() + 10000001));
        Assertions.assertTrue(res.size() == 0);
        orderRepo.delete(order.getId());
    }

    @Test
    public void testGetOrdersWithStatusForRestaurant() throws Exception {
        orderController.addNewOrder(order);
        List<Order> res =
                orderController.getOrdersWithStatusForRestaurant(
                        "restaurantId", OrderStatus.CANCELED);
        Assertions.assertTrue(res.size() == 1);

        res = orderController.getOrdersWithStatusForRestaurant("hihi", OrderStatus.CANCELED);
        Assertions.assertTrue(res.size() == 0);

        res =
                orderController.getOrdersWithStatusForRestaurant(
                        "restaurantId", OrderStatus.SUBMITTED);
        Assertions.assertTrue(res.size() == 0);
        orderRepo.delete(order.getId());
    }

    @Test
    public void testGetOrdersByStatus() throws Exception {
        orderController.addNewOrder(order);

        List<Order> res = orderController.getOrdersByStatus(OrderStatus.CANCELED);
        Assertions.assertTrue(res.get(0).getId() == order.getId());

        orderRepo.delete(order.getId());
    }

    @Test
    public void testGetOrdersByStatusAndDriverId() throws Exception {
        orderController.addNewOrder(order);
        List<Order> res =
                orderController.getOrdersByStatusAndDriverId(OrderStatus.CANCELED, "driverId");
        Assertions.assertTrue(res.size() == 1);

        res = orderController.getOrdersByStatusAndDriverId(OrderStatus.SUBMITTED, "driverId");
        Assertions.assertTrue(res.size() == 0);

        res = orderController.getOrdersByStatusAndDriverId(OrderStatus.CANCELED, "userId");
        Assertions.assertTrue(res.size() == 0);
        orderRepo.delete(order.getId());
    }

    @Test
    public void testGetAll() throws Exception {
        orderController.addNewOrder(order);
        List<Order> res = orderController.getAll();
        Assertions.assertTrue(res.size() == 1);
        orderRepo.delete(order.getId());
    }

    @Test
    public void testSubmitOrder() throws Exception {
        orderController.addNewOrder(order);
        orderController.submitOrder(order.getId());
        Assertions.assertTrue(order.getOrderStatus().equals(OrderStatus.SUBMITTED));
        orderRepo.delete(order.getId());
    }

    @Test
    public void testCancelOrder() throws Exception {
        order.setOrderStatus(OrderStatus.CREATED);
        orderController.addNewOrder(order);
        orderController.cancelOrder(order.getId());
        Assertions.assertTrue(order.getOrderStatus().equals(OrderStatus.CANCELED));
        orderRepo.delete(order.getId());
    }

    @Test
    public void testValidateOrder() throws Exception {
        orderController.addNewOrder(order);
        orderController.validateOrder(order.getId());
        Assertions.assertTrue(order.getOrderStatus().equals(OrderStatus.VALIDATED));
        orderRepo.delete(order.getId());
    }

    @Test
    public void testPreparingOrder() throws Exception {
        orderController.addNewOrder(order);
        orderController.preparingOrder(order.getId());
        Assertions.assertTrue(order.getOrderStatus().equals(OrderStatus.PREPARING));
        orderRepo.delete(order.getId());
    }

    @Test
    public void testWaitingOrder() throws Exception {
        orderController.addNewOrder(order);
        orderController.waitingOrder(order.getId());
        Assertions.assertTrue(order.getOrderStatus().equals(OrderStatus.WAITING));
        orderRepo.delete(order.getId());
    }

    @Test
    public void testDeliveringOrder() throws Exception {
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    orderController.deliveringOrder("", "hihi");
                });
        orderController.addNewOrder(order);
        orderController.deliveringOrder(order.getId(), order.getDriverId());
        Assertions.assertTrue(order.getOrderStatus().equals(OrderStatus.DELIVERING));
        orderRepo.delete(order.getId());
    }

    @Test
    public void testFinishOrder() throws Exception {
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    orderController.finishOrder("", "driverId");
                });

        orderController.addNewOrder(order);
        orderController.finishOrder(order.getId(), order.getDriverId());
        Assertions.assertTrue(order.getOrderStatus().equals(OrderStatus.FINISHED));
        orderRepo.delete(order.getId());
    }
}
