package io.zeecode.connectmeapi.services.user.repositories;

import io.zeecode.connectmeapi.services.user.model.Person;
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
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long>, CrudRepository<Person, Long> {

    @Query(value = "SELECT p.* FROM persons p WHERE p.search_vector @@ to_tsquery('english', :query)", nativeQuery = true)
    List<Person> searchAllPersons(@Param("query") String query);

    @Query(value = "SELECT p.* FROM persons p WHERE p.search_vector @@ to_tsquery('english', :query)", countQuery = "SELECT count(*) FROM persons p WHERE p.search_vector @@ to_tsquery('english', :query)", nativeQuery = true)
    Page<Person> searchPersons(@Param("query") String query, Pageable pageable);
}
