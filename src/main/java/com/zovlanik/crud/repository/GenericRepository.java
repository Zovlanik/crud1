package com.zovlanik.crud.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T getByID(ID id);

    List<T> getAll();

    void create(T t);

    void update(T t);

    void deleteByID(ID id);

}
