package io.leave.manager.service;

import io.leave.manager.collection.LeaveBooking;
import io.leave.manager.dto.LeaveRequest;
import io.leave.manager.exception.collection.LeaveNotFoundException;
import io.leave.manager.exception.collection.LeaveRequestException;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface LeaveBookingService {
    LeaveBooking requestForLeave(LeaveRequest request) throws MessagingException;
    void cancelRequestedLeave(String leaveId, String email) throws LeaveNotFoundException;
    Optional<LeaveBooking> findLeaveByIdAndUserEmail(String leaveId, String email) throws LeaveNotFoundException;
    List<LeaveBooking> getPendingLeaveBookings();
}
