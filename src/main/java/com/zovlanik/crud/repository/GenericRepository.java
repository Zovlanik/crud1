package com.zovlanik.crud.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T getById(ID id);

    List<T> getAll();

    void create(T t);

    void update(T t);

    void deleteById(ID id);

}
