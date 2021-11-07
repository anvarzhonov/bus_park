package ru.anvarzhonov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anvarzhonov.models.BusDriver;
import ru.anvarzhonov.repository.BusDriverRepository;

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
        if (!nameFilter.contains("%")){
            nameFilter = String.join("","%",nameFilter, "%");
        }


        return repository.findBusDriverByDriverLicenceLike(nameFilter);
    }
}
