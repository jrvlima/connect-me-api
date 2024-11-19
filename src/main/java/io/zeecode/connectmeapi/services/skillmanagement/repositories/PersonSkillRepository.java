package io.zeecode.connectmeapi.services.skillmanagement.repositories;

import io.zeecode.connectmeapi.services.skillmanagement.model.PersonSkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "personskills", path = "personskills")
public interface PersonSkillRepository extends PagingAndSortingRepository<PersonSkill, Long>, CrudRepository<PersonSkill, Long> {
}
