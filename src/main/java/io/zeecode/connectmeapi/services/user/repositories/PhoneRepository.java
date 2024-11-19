package io.zeecode.connectmeapi.services.user.repositories;

import io.zeecode.connectmeapi.services.user.model.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "phones", path = "phones")
public interface PhoneRepository extends PagingAndSortingRepository<Phone, Long>, CrudRepository<Phone, Long> {
}
