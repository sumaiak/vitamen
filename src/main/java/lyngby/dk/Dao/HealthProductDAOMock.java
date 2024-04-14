package lyngby.dk.Dao;

import lyngby.dk.Dto.HealthProductDTO;

import java.util.Set;

public interface HealthProductDAOMock {
    Set<HealthProductDTO> getAll();
    HealthProductDTO getById(int id);
    Set<HealthProductDTO> getByCategory(String category);
    HealthProductDTO create(HealthProductDTO healthProduct);
    HealthProductDTO update(HealthProductDTO healthProduct);
    HealthProductDTO delete(int id);
    Set<HealthProductDTO> getTwoWeeksToExpire();
}
