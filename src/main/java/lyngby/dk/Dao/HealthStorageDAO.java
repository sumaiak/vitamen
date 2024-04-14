package lyngby.dk.Dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lyngby.dk.Dto.HealthProductDTO;
import lyngby.dk.ENtities.HealthProduct;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HealthStorageDAO implements IDAO {
    private static EntityManagerFactory emf;

    public HealthStorageDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Object create(Object o) {
        return null;
    }

    @Override
    public Object update(Object o) {
        return null;
    }

    @Override
    public Object delete(int id) {
        return null;
    }

    @Override
    public Set getAll() {
        return null;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public void addProductToStorage(int storageId, int productId) {

    }

    @Override
    public Set<HealthProduct> getProductsByStorageShelf(int storageId) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<HealthProduct> query = em.createQuery(
                    "SELECT h FROM HealthProduct h WHERE h.storage.id = :storageId",
                    HealthProduct.class
            );
            query.setParameter("storageId", storageId);

            List<HealthProduct> productList = query.getResultList();
            return new HashSet<>(productList);
        }
    }
}