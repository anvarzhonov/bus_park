package ru.anvarzhonov.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bus")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bus")
    private Long id;

    @Column(name = "bus_number")
    private int busNumber;

    @ManyToOne
    @JoinColumn(name= "id_driver")
    private BusDriver busDriver;

    @ManyToOne
    @JoinColumn(name = "id_company")
    private InsuranceCompany insuranceCompany;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Bus bus = (Bus) o;
        return id != null && Objects.equals(id, bus.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
