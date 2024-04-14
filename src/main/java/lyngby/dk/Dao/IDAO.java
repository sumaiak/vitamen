package lyngby.dk.Dao;

import lyngby.dk.Dto.HealthProductDTO;
import lyngby.dk.ENtities.HealthProduct;

import java.util.Set;

public interface IDAO<T> {
    T create(T t);

    T update(T t);

    T delete(int id);

    Set<T> getAll();

    T getById(int id);
    void addProductToStorage(int storageId, int productId);
    Set<HealthProduct> getProductsByStorageShelf(int storageId);
}
