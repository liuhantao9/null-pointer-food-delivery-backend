package edu.northeastern.cs5500.delivery.repository;

import java.util.Collection;
import javax.annotation.Nonnull;

public interface GenericRepository<T> {
    public T get(@Nonnull String id);

    public T add(@Nonnull T item);

    public T update(@Nonnull T item);

    public void delete(@Nonnull String id);

    public Collection<T> getAll();

    public long count();
}
