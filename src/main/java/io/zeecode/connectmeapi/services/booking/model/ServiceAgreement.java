package io.zeecode.connectmeapi.services.booking.model;


import io.zeecode.connectmeapi.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author João Vinagre Lima
 * @version 1.0
 * @created 17-nov-2024 17:08:49
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "service_agreements")
public class ServiceAgreement extends BaseEntity {

    private LocalDateTime date;
    @OneToMany()
    private Set<PersonSkill> skills;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Person client;
    @ManyToOne
    @JoinColumn(name = "service_provider_id")
    private Person serviceProvider;

    public ServiceAgreement() {

    }
}