package io.zeecode.connectmeapi.services.skillmanagement.model;

import io.zeecode.connectmeapi.domain.BaseEntity;
import jakarta.persistence.*;
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
@Table(name = "skills")
public class Skill extends BaseEntity {

    private String description;
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private SkillCategory category;

    @Column(columnDefinition = "tsvector")
    private String searchVector;

    public Skill() {

    }


}