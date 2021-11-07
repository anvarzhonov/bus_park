package ru.anvarzhonov.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "bus_driver")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BusDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    @Column(name = "driver_licence")
    private String driverLicence;

    @Column(name = "work_experience")
    private int workExperience;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BusDriver busDriver = (BusDriver) o;
        return id != null && Objects.equals(id, busDriver.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
