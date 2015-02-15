package org.sb.persist;

import org.sb.persist.data.CompanyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CompanyRepository extends MongoRepository<CompanyEntity, String> {

	@Query("{ 'entity.name' : ?0 }")
    public CompanyEntity findByName(String name);

}