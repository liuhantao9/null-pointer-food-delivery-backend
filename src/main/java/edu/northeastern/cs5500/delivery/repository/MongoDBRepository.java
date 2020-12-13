package edu.northeastern.cs5500.delivery.repository;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.northeastern.cs5500.delivery.model.Model;
import edu.northeastern.cs5500.delivery.service.MongoDBService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.inject.Inject;

public class MongoDBRepository<T extends Model> implements GenericRepository<T> {

    MongoCollection<T> collection;

    @Inject
    public MongoDBRepository(Class<T> clazz, MongoDBService mongoDBService) {
        MongoDatabase mongoDatabase = mongoDBService.getMongoDatabase();
        collection = mongoDatabase.getCollection(clazz.getName(), clazz);
    }

    @Nullable
    public T get(String id) {
        return collection.find(eq("_id", id)).first();
    }

    @Override
    public T add(T item) {
        if (item.getId() == null) {
            item.setId(UUID.randomUUID().toString());
        }
        collection.insertOne(item);
        return item;
    }

    @Override
    public T update(T item) {
        return collection.findOneAndReplace(eq("_id", item.getId()), item);
    }

    @Override
    public void delete(String id) {
        collection.deleteOne(eq("_id", id));
    }

    @Override
    public Collection<T> getAll() {
        return collection.find().into(new ArrayList<>());
    }

    @Override
    public long count() {
        return collection.countDocuments();
    }
}
