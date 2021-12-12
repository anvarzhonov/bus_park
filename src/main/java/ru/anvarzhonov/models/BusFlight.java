package ru.anvarzhonov.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bus_flight")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BusFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flight")
    private Long id;

    @Column(name = "number_flight")
    private int numberFlight;

    @Column(name = "name_start_flight")
    private String startFlight;

    @Column(name = "name_end_flight")
    private String endFlight;


    @OneToMany(mappedBy = "busFlight", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<BusDriver> busDriverList;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        BusFlight busFlight = (BusFlight) o;
        return id != null && Objects.equals(id, busFlight.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
