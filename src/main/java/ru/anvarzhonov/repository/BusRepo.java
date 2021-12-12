package ru.anvarzhonov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anvarzhonov.models.Bus;

/**
 * todo Document type BusRepo
 */
@Repository
public interface BusRepo extends JpaRepository<Bus, Long> {
}
