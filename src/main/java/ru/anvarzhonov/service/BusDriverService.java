package ru.anvarzhonov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.anvarzhonov.models.BusDriver;
import ru.anvarzhonov.repository.BusDriverRepository;
import ru.anvarzhonov.repository.specifications.BusDriverSpecification;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BusDriverService {
    private final BusDriverRepository repository;

    @Autowired
    public BusDriverService(BusDriverRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<BusDriver> showAll() {return repository.findAll();}

    @Transactional
    public BusDriver getById(Long id) {return repository.findById(id).orElse(null);}

    @Transactional
    public void addOrUpdate(BusDriver busDriver) {repository.save(busDriver);}

    @Transactional
    public void remove(Long id) {repository.deleteById(id);}

    public List<BusDriver> getByDriverLicence(String nameFilter){

        Specification<BusDriver> specification = Specification.where(null);
        specification = specification.and(BusDriverSpecification.driverLicenceLike(nameFilter));

        return repository.findAll(specification);

    }
}
