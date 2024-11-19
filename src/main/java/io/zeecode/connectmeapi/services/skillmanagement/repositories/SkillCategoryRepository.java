package io.zeecode.connectmeapi.services.skillmanagement.repositories;

import io.zeecode.connectmeapi.services.skillmanagement.model.SkillCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "skillcategories", path = "skillcategories")
public interface SkillCategoryRepository extends PagingAndSortingRepository<SkillCategory, Long>, CrudRepository<SkillCategory, Long> {
}
