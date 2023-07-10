package io.leave.manager.repository;

import io.leave.manager.collection.LeaveBooking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveBookingRepository extends MongoRepository<LeaveBooking, String> {

}
