package com.local_weather_API.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class LocationRepositoryTest {

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void when_no_city_with_requested_name_in_db_repo_should_return_empty_optional(){
        // given

        // when
        String requestedCityName = "Szczecin";
        Optional <Location> location = locationRepository.findByCity(requestedCityName);

        // then
        Assertions.assertTrue(location.isEmpty());
    }

}