package owntryspringboot.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "car", path = "cars")
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
//    @Query("SELECT c from Car c where c.brand=?1")
    List<Car> findByBrand(@Param("brand") String brand);
//
//    @Query("SELECT c from Car c where c.color=?1")
    List<Car> findByColor(@Param("color")String color);
}
