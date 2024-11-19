package io.zeecode.connectmeapi.services.skillmanagement.repositories;

import io.zeecode.connectmeapi.services.skillmanagement.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "skills", path = "skills")
public interface SkillRepository extends PagingAndSortingRepository<Skill, Long>, CrudRepository<Skill, Long> {
    @Query("select s from #{#entityName} s where s.name  like %?1%")
    List<Skill> findByNameLike(String name);

    List<Skill> findByName(String name);

    @Query(value = "SELECT s.* FROM skills s WHERE s.search_vector @@ to_tsquery('english', :query)", nativeQuery = true)
    List<Skill> searchAllSkills(@Param("query") String query);

    @Query(value = "SELECT s.* FROM skills s WHERE s.search_vector @@ to_tsquery('english', :query)", countQuery = "SELECT count(*) FROM skills s WHERE s.search_vector @@ to_tsquery('english', :query)", nativeQuery = true)
    Page<Skill> searchSkills(@Param("query") String query, Pageable pageable);
}
