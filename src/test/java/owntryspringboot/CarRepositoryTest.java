package owntryspringboot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import owntryspringboot.domain.Car;
import owntryspringboot.domain.CarRepository;
import owntryspringboot.domain.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void saveCar() {
        Owner owner = new Owner("Shashank", "Reddy");
        entityManager.persistAndFlush(owner);
        Car car = new Car("Tesla", "Model X", "White", "ABC-1234",2017, 86000, owner);
        entityManager.persistAndFlush(car);
        Assert.assertNotNull(car.getId());
    }

    @Test
    public void deleteCars() {
        Owner owner = new Owner("Shashank", "Reddy");
        entityManager.persistAndFlush(owner);
        entityManager.persistAndFlush(new Car("Tesla", "Model X", "White","ABC-1234", 2017, 86000, owner));
        entityManager.persistAndFlush(new Car("Mini", "Cooper", "Yellow","BWS-3007", 2015, 24500, owner));

        carRepository.deleteAll();
        List<?> resultantList = StreamSupport.stream(carRepository.findAll().spliterator(), false).collect(Collectors.toList());
        Assert.assertFalse(resultantList.size() > 0);
    }
}
