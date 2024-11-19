package io.zeecode.connectmeapi.services.booking.repositories;

import io.zeecode.connectmeapi.services.booking.model.ServiceAgreement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "serviceagreements", path = "serviceagreements")
public interface ServiceAgreementRepository extends PagingAndSortingRepository<ServiceAgreement, Long>, CrudRepository<ServiceAgreement, Long> {
}
