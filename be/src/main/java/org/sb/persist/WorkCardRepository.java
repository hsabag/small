package org.sb.persist;

import org.sb.persist.data.WorkCardEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkCardRepository extends MongoRepository<WorkCardEntity, String> {


}