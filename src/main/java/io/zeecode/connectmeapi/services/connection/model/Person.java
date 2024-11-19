package io.zeecode.connectmeapi.services.connection.model;

import io.zeecode.connectmeapi.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "persons")
public class Person extends BaseEntity {

    private String firstName;
    private String lastName;
    private String middleName;
    private String email;

    public Person() {

    }

}