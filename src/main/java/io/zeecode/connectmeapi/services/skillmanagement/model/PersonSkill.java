package io.zeecode.connectmeapi.services.skillmanagement.model;

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
 * @created 17-nov-2024 17:08:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "person_skills")
public class PersonSkill extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public PersonSkill() {

    }

}