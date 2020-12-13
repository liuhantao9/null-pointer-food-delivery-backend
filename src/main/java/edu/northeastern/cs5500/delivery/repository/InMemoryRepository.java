package edu.northeastern.cs5500.delivery.repository;

import edu.northeastern.cs5500.delivery.model.Model;
import edu.northeastern.cs5500.delivery.model.Order;
import edu.northeastern.cs5500.delivery.model.OrderStatus;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.inject.Inject;

public class InMemoryRepository<T extends Model> implements GenericRepository<T> {

    HashMap<String, T> collection;

    @Inject
    public InMemoryRepository() {
        collection = new HashMap<>();
    }

    @Nullable
    public T get(String id) {
        return collection.get(id);
    }

    @Override
    public T add(T item) {
        String id = item.getId();
        if (id == null) {
            id = UUID.randomUUID().toString();
            item.setId(id);
        }
        collection.put(id, item);
        return item;
    }

    @Override
    public T update(T item) {
        collection.put(item.getId(), item);
        return item;
    }

    @Override
    public void delete(String id) {
        collection.remove(id);
    }

    @Override
    public Collection<T> getAll() {
        return collection.values();
    }

    @Override
    public long count() {
        return collection.size();
    }

    public List<Order> getOrdersWithinDateForCustomer(
            String userId, Instant startTime, Instant endTime) {
        List<Order> res = new ArrayList<>();
        for (String id : collection.keySet()) {
            Order order = (Order) collection.get(id);
            if (order.getUserId().equals(userId)
                    && order.getCreated().toInstant().isAfter(startTime)
                    && order.getCreated().toInstant().isBefore(endTime)) {
                res.add(order);
            }
        }
        return res;
    }

    public List<Order> getOrdersWithinDateForRestaurant(
            String restaurantId, Instant startTime, Instant endTime) {
        List<Order> res = new ArrayList<>();
        for (String id : collection.keySet()) {
            Order order = (Order) collection.get(id);
            if (order.getUserId().equals(restaurantId)
                    && order.getCreated().toInstant().isAfter(startTime)
                    && order.getCreated().toInstant().isBefore(endTime)) {
                res.add(order);
            }
        }
        return res;
    }

    public List<Order> getOrdersWithStatusForRestaurant(
            String restaurantId, OrderStatus orderStatus) {
        List<Order> res = new ArrayList<>();
        for (String id : collection.keySet()) {
            Order order = (Order) collection.get(id);
            if (order.getUserId().equals(restaurantId)
                    && order.getOrderStatus().equals(orderStatus)) {
                res.add(order);
            }
        }
        return res;
    }

    public List<Order> getOrdersWithinDateForDriver(
            String driverId, Instant startTime, Instant endTime) {
        List<Order> res = new ArrayList<>();
        for (String id : collection.keySet()) {
            Order order = (Order) collection.get(id);
            if (order.getUserId().equals(driverId)
                    && order.getCreated().toInstant().isAfter(startTime)
                    && order.getCreated().toInstant().isBefore(endTime)) {
                res.add(order);
            }
        }
        return res;
    }

    public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
        List<Order> res = new ArrayList<>();
        for (String id : collection.keySet()) {
            Order order = (Order) collection.get(id);
            if (order.getOrderStatus().equals(orderStatus)) {
                res.add(order);
            }
        }
        return res;
    }

    public List<Order> getOrdersByStatusAndDriverId(OrderStatus orderStatus, String driverId) {
        List<Order> res = new ArrayList<>();
        for (String id : collection.keySet()) {
            Order order = (Order) collection.get(id);
            if (order.getUserId().equals(driverId) && order.getOrderStatus().equals(orderStatus)) {
                res.add(order);
            }
        }
        return res;
    }
}
