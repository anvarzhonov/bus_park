package ru.anvarzhonov.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "bus_driver")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_driver")
    private Long id;

    @Column(name = "name_surname")
    private String nameSurname;

    @Column(name = "driver_license")
    private String driverLicence;

    @Column(name = "work_experience")
    private int workExperience;

    @Column(name = "kol_flight")
    private int countFlight;

    @OneToMany(mappedBy = "busDriver",cascade = CascadeType.ALL)
    private List<Bus> buses;

    @ManyToOne
    @JoinColumn(name = "id_flight")
    private BusFlight busFlight;

}
