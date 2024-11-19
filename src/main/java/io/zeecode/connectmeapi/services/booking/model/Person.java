package io.zeecode.connectmeapi.services.booking.model;

import io.zeecode.connectmeapi.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "persons")
public class Person extends BaseEntity {

    private String firstName;
    private String lastName;
    private String middleName;
    private String email;

    @OneToMany(mappedBy = "client")
    private Set<ServiceAgreement> receivesServices;
    @OneToMany(mappedBy = "serviceProvider")
    private Set<ServiceAgreement> providesServices;

    public Person() {

    }

}