package owntryspringboot.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    @Query("SELECT c from Car c where c.brand=?1")
    List<Car> findByBrand(String brand);

    @Query("SELECT c from Car c where c.color=?1")
    List<Car> findByColor(String color);
}
