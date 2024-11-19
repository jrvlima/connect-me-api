package io.zeecode.connectmeapi.services.connection.repositories;

import io.zeecode.connectmeapi.services.connection.model.Friendship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "friendships", path = "friendships")
public interface FriendshipRepository extends PagingAndSortingRepository<Friendship, Long>, CrudRepository<Friendship, Long> {
}
