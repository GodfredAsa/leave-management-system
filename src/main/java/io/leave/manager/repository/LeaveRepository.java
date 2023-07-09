package io.leave.manager.repository;

import io.leave.manager.collection.Leave;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends MongoRepository<Leave, Long> {
}
