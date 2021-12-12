package ru.anvarzhonov.repository.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.anvarzhonov.models.BusDriver;

public class BusDriverSpecification {
    public static Specification<BusDriver> driverLicenceLike(String licenceFilter) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("driverLicence"), "%" + licenceFilter + "%");
    }
}
