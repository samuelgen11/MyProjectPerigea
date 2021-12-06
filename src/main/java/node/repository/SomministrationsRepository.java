package node.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import node.entity.SomministrationsEntity;



@Repository
public interface SomministrationsRepository extends MongoRepository<SomministrationsEntity, String> {
	
}
