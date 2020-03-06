package com.local_weather_API.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;



@ExtendWith(SpringExtension.class)
@DataJpaTest
class WeatherRepositoryTest {

    @Autowired
    WeatherRepository weatherRepository;
    @Autowired
    TestEntityManager testentityManager;


    @Test
    void when_there_are_weathers_linked_to_the_location_in_db_the_repo_should_return_them () {
        // given
        Location locationX = new Location();
        Weather weather1 = new Weather();
        weather1.setLocation(locationX);
        Weather weather2 = new Weather();
        weather2.setLocation(locationX);
        Weather weather3 = new Weather();
        weather3.setLocation(locationX);
        testentityManager.persist(locationX);
        testentityManager.persist(weather1);
        testentityManager.persist(weather2);
        testentityManager.persist(weather3);

        // when
        List<Weather> weathers = weatherRepository.findByLocation(locationX);

        // then
        Assertions.assertEquals(3, weathers.size());
    }

}