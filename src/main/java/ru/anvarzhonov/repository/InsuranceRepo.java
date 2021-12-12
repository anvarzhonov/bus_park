package ru.anvarzhonov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anvarzhonov.models.Bus;
import ru.anvarzhonov.models.InsuranceCompany;

/**
 * todo Document type InsuranceRepo
 */
@Repository
public interface InsuranceRepo extends JpaRepository<InsuranceCompany, Long> {
}
