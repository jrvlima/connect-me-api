package io.zeecode.connectmeapi.services.user.model;

import io.zeecode.connectmeapi.domain.BaseEntity;
import jakarta.persistence.*;
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

    @OneToOne
    private User user;
    @OneToMany(mappedBy = "person")
    private Set<Address> addresses;
    @OneToMany(mappedBy = "person")
    private Set<Phone> phones;

    @Column(columnDefinition = "tsvector")
    private String searchVector;

    public Person() {

    }

}