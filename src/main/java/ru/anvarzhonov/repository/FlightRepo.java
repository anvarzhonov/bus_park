package ru.anvarzhonov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anvarzhonov.models.Bus;
import ru.anvarzhonov.models.BusFlight;

/**
 * todo Document type FlightRepo
 */
@Repository
public interface FlightRepo extends JpaRepository<BusFlight, Long> {

}
