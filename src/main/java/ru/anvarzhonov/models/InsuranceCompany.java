package ru.anvarzhonov.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "passenger_insurance_company")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private Long id;

    @Column(name = "name_insurance_company")
    private String nameCompany;

    @Column(name = "contract_number")
    private int contractNumber;

    @OneToMany(mappedBy = "insuranceCompany",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Bus> busList;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        InsuranceCompany that = (InsuranceCompany) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
