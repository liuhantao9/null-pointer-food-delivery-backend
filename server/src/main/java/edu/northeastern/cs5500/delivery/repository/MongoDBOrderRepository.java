package edu.northeastern.cs5500.delivery.repository;

// import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lt;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import edu.northeastern.cs5500.delivery.model.Order;
import edu.northeastern.cs5500.delivery.model.OrderStatus;
import edu.northeastern.cs5500.delivery.service.MongoDBService;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.inject.Inject;

public class MongoDBOrderRepository implements GenericOrderRepository {

    MongoCollection<Order> collection;

    @Inject
    public MongoDBOrderRepository(Class<Order> clazz, MongoDBService mongoDBService) {
        MongoDatabase mongoDatabase = mongoDBService.getMongoDatabase();
        collection = mongoDatabase.getCollection(clazz.getName(), clazz);
        System.out.println(clazz);
    }

    @Override
    public List<Order> getOrdersWithinDateForCustomer(
            String userId, Instant startTime, Instant endTime) {
        FindIterable<Order> documents =
                collection.find(
                        Filters.and(
                                eq("userId", userId),
                                gte("created", startTime),
                                lt("created", endTime)));
        documents = documents.sort(Sorts.descending("created"));
        MongoCursor<Order> cursor = documents.iterator();
        List<Order> orders = new ArrayList<Order>();
        while (cursor.hasNext()) {
            Order doc = cursor.next();
            orders.add(doc);
        }
        return orders;
    }

    @Nullable
    public Order get(String id) {
        return collection.find(eq("_id", id)).first();
    }

    @Override
    public Order add(Order item) {
        if (item.getId() == null) {
            item.setId(UUID.randomUUID().toString());
        }
        collection.insertOne(item);
        return item;
    }

    @Override
    public Order update(Order item) {
        return collection.findOneAndReplace(eq("_id", item.getId()), item);
    }

    @Override
    public void delete(String id) {
        collection.deleteOne(eq("_id", id));
    }

    @Override
    public Collection<Order> getAll() {
        return collection.find().into(new ArrayList<>());
    }

    @Override
    public long count() {
        return collection.countDocuments();
    }

    @Override
    public List<Order> getOrdersWithinDateForRestaurant(
            String restaurantId, Instant startTime, Instant endTime) {
        FindIterable<Order> documents =
                collection.find(
                        Filters.and(
                                eq("restaurantId", restaurantId),
                                gte("created", startTime),
                                lt("created", endTime)));
        documents = documents.sort(Sorts.descending("created"));
        MongoCursor<Order> cursor = documents.iterator();
        List<Order> orders = new ArrayList<Order>();
        while (cursor.hasNext()) {
            Order doc = cursor.next();
            orders.add(doc);
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersWithStatusForRestaurant(
            String restaurantId, OrderStatus orderStatus) {
        FindIterable<Order> documents =
                collection.find(
                        Filters.and(
                                eq("restaurantId", restaurantId),
                                eq("orderStatus", orderStatus.name())));
        documents = documents.sort(Sorts.descending("created"));
        MongoCursor<Order> cursor = documents.iterator();
        List<Order> orders = new ArrayList<Order>();
        while (cursor.hasNext()) {
            Order doc = cursor.next();
            orders.add(doc);
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersWithinDateForDriver(
            String driverId, Instant startTime, Instant endTime) {
        FindIterable<Order> documents =
                collection.find(
                        Filters.and(
                                eq("driverId", driverId),
                                gte("created", startTime),
                                lt("created", endTime)));
        documents = documents.sort(Sorts.descending("created"));
        MongoCursor<Order> cursor = documents.iterator();
        List<Order> orders = new ArrayList<Order>();
        while (cursor.hasNext()) {
            Order doc = cursor.next();
            orders.add(doc);
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
        FindIterable<Order> doc = collection.find(Filters.eq("orderStatus", orderStatus.name()));
        doc = doc.sort(Sorts.descending("created"));
        MongoCursor<Order> cursor = doc.iterator();
        List<Order> orders = new ArrayList<Order>();
        while (cursor.hasNext()) {
            Order order = cursor.next();
            orders.add(order);
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByStatusAndDriverId(OrderStatus orderStatus, String driverId) {
        FindIterable<Order> doc =
                collection.find(
                        Filters.and(
                                eq("orderStatus", orderStatus.name()), eq("driverId", driverId)));
        doc = doc.sort(Sorts.descending("created"));
        MongoCursor<Order> cursor = doc.iterator();
        List<Order> orders = new ArrayList<Order>();
        while (cursor.hasNext()) {
            Order order = cursor.next();
            orders.add(order);
        }
        return orders;
    }
}
