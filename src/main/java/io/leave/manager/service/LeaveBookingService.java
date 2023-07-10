package io.leave.manager.service;

import io.leave.manager.collection.LeaveBooking;
import io.leave.manager.exception.collection.UserNotFoundException;

import java.text.ParseException;

public interface LeaveBookingService {
    LeaveBooking bookLeave(LeaveBooking leaveBooking, String userId) throws UserNotFoundException, ParseException;
    LeaveBooking updateBookedLeave(String id, LeaveBooking leaveBooking);
    void cancelBookedLeave(String id);
    LeaveBooking findLeaveById(String id);

}
