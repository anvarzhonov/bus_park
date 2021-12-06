package ru.anvarzhonov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.anvarzhonov.models.BusDriver;

import java.util.List;

@Repository
public interface BusDriverRepository extends JpaRepository<BusDriver, Long>, JpaSpecificationExecutor<BusDriver> {
    List<BusDriver> findBusDriverByDriverLicenceLike(String workExperience);
}
