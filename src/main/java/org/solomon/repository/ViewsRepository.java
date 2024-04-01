package org.solomon.repository;

import org.solomon.model.Views;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewsRepository extends MongoRepository<Views, String> {
}
