package com.localWeatherAPI.dataLayer;

import com.localWeatherAPI.dataLayer.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByCity(String city);

    List<Location> findAllByOrderByCountryAsc();

    List<Location> findByCountryOrderByCityAsc(String country);
}