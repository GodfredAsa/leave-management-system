package io.leave.manager.repository;

import io.leave.manager.collection.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
    Optional<User> findByUsername(String username);
}
