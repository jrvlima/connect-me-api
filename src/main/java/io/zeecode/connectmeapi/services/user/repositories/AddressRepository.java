package io.zeecode.connectmeapi.services.user.repositories;

import io.zeecode.connectmeapi.services.user.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "addresses", path = "addresses")
public interface AddressRepository extends PagingAndSortingRepository<Address, Long>, CrudRepository<Address, Long> {
}
