package carsharing.repository;

import java.util.List;

public interface Repository<T, S> {

    default void save(T t){};

    default void save(T t, S s){};

    default List<T> getAll(){
        return List.of();
    };

    default List<T> getAll(S s) {
        return List.of();
    }

    default void update(T t, String id) {};

    T getEntity(int id);

}
