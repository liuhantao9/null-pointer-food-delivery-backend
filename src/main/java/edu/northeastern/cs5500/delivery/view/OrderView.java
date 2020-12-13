package edu.northeastern.cs5500.delivery.view;

import static spark.Spark.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.northeastern.cs5500.delivery.JsonTransformer;
import edu.northeastern.cs5500.delivery.controller.OrderController;
import edu.northeastern.cs5500.delivery.model.Order;
import edu.northeastern.cs5500.delivery.model.OrderStatus;
import java.time.Instant;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class OrderView implements View {

    @Inject
    public OrderView() {}

    @Inject JsonTransformer jsonTransformer;

    @Inject OrderController orderController;

    @Override
    public void register() {
        // TODO Auto-generated method stub
        log.info("OrderView > register");

        post( // create a new order
                "/order",
                (req, res) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    // JsonNode jsonNode = mapper.readTree(req.body());
                    // String userId = jsonNode.get("userId").asText();

                    Order order = mapper.readValue(req.body(), Order.class);
                    order = orderController.addNewOrder(order);
                    res.type("application/json");
                    return order;
                },
                jsonTransformer);

        post(
                "/customer/ordersbydate",
                (req, res) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});
                    Instant startDate = Instant.parse(paramMap.get("startDate"));
                    Instant endDate = Instant.parse(paramMap.get("endDate"));
                    String userId = paramMap.get("userId");
                    res.type("application/json");
                    return orderController.getOrdersWithinDateForCustomer(
                            userId, startDate, endDate);
                },
                jsonTransformer);

        post(
                "/driver/ordersbydate",
                (req, res) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});
                    Instant startDate = Instant.parse(paramMap.get("startDate"));
                    Instant endDate = Instant.parse(paramMap.get("endDate"));
                    String driverId = paramMap.get("driverId");
                    res.type("application/json");
                    return orderController.getOrdersWithinDateForDriver(
                            driverId, startDate, endDate);
                },
                jsonTransformer);

        post(
                "/restaurant/ordersbydate",
                (req, res) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});
                    Instant startDate = Instant.parse(paramMap.get("startDate"));
                    Instant endDate = Instant.parse(paramMap.get("endDate"));
                    String restaurantId = paramMap.get("restaurantId");
                    res.type("application/json");
                    return orderController.getOrdersWithinDateForRestaurant(
                            restaurantId, startDate, endDate);
                },
                jsonTransformer);

        post(
                "/restaurant/:restaurantId/order/submitted",
                (req, res) -> {
                    String restaurantId = req.params(":restaurantId");
                    res.type("application/json");
                    return orderController.getOrdersWithStatusForRestaurant(
                            restaurantId, OrderStatus.SUBMITTED);
                },
                jsonTransformer);

        post(
                "/restaurant/:restaurantId/order/preparing",
                (req, res) -> {
                    String restaurantId = req.params(":restaurantId");
                    res.type("application/json");
                    return orderController.getOrdersWithStatusForRestaurant(
                            restaurantId, OrderStatus.PREPARING);
                },
                jsonTransformer);

        post(
                "/restaurant/:restaurantId/order/waiting",
                (req, res) -> {
                    String restaurantId = req.params(":restaurantId");
                    res.type("application/json");
                    return orderController.getOrdersWithStatusForRestaurant(
                            restaurantId, OrderStatus.WAITING);
                },
                jsonTransformer);

        post(
                "/orders/waiting",
                (req, res) -> {
                    res.type("application/json");
                    return orderController.getOrdersByStatus(OrderStatus.WAITING);
                },
                jsonTransformer);

        post(
                "/orders/delivering",
                (req, res) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});
                    String driverId = paramMap.get("driverId");

                    res.type("application/json");
                    return orderController.getOrdersByStatusAndDriverId(
                            OrderStatus.DELIVERING, driverId);
                },
                jsonTransformer);

        post(
                "/orders/all",
                (req, res) -> {
                    res.type("application/json");
                    return orderController.getAll();
                },
                jsonTransformer);

        post(
                "/submitOrder/:orderId",
                (req, res) -> {
                    final String orderId = req.params(":orderId");
                    Order order = orderController.submitOrder(orderId);
                    res.type("application/json");
                    return order;
                },
                jsonTransformer);

        post(
                "/cancelOrder/:orderId",
                (req, res) -> {
                    final String orderId = req.params(":orderId");
                    Order order = orderController.cancelOrder(orderId);
                    res.type("application/json");
                    return order;
                },
                jsonTransformer);

        post(
                "/validateOrder/:orderId",
                (req, res) -> {
                    final String orderId = req.params(":orderId");
                    Order order = orderController.validateOrder(orderId);
                    res.type("application/json");
                    return order;
                },
                jsonTransformer);

        post(
                "/preparingOrder/:orderId",
                (req, res) -> {
                    final String orderId = req.params(":orderId");
                    Order order = orderController.preparingOrder(orderId);
                    res.type("application/json");
                    return order;
                },
                jsonTransformer);

        post(
                "/waitingOrder/:orderId",
                (req, res) -> {
                    final String orderId = req.params(":orderId");
                    Order order = orderController.waitingOrder(orderId);
                    res.type("application/json");
                    return order;
                },
                jsonTransformer);

        post(
                "/deliveringOrder",
                (req, res) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});
                    String driverId = paramMap.get("driverId");
                    String orderId = paramMap.get("orderId");
                    Order order = orderController.deliveringOrder(orderId, driverId);
                    res.type("application/json");
                    return order;
                },
                jsonTransformer);

        post(
                "/finishOrder",
                (req, res) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});
                    String driverId = paramMap.get("driverId");
                    String orderId = paramMap.get("orderId");
                    Order order = orderController.finishOrder(orderId, driverId);
                    res.type("application/json");
                    return order;
                },
                jsonTransformer);
    }
}
