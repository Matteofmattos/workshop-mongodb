package com.matteof_mattos.workshopMongo.repositories;

import com.matteof_mattos.workshopMongo.models.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {  }
