package io.zeecode.connectmeapi.services.skillmanagement.model;

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
@Table(name = "skill_categories")
public class SkillCategory extends BaseEntity {

    private String name;
    @OneToMany(mappedBy = "category")
    private Set<Skill> skills;

    public SkillCategory() {
    }
}
