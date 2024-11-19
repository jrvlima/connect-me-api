package io.zeecode.connectmeapi.services.user.model;

import io.zeecode.connectmeapi.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author João Vinagre Lima
 * @version 1.0
 * @created 17-nov-2024 17:08:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    private String address;
    private String complement;
    private String city;
    private String country;
    private String state;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Address() {

    }


}