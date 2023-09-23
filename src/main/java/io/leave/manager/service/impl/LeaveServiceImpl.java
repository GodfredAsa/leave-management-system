package io.leave.manager.service.impl;

import io.leave.manager.collection.LeaveBooking;
import io.leave.manager.dto.LeaveRequest;
import io.leave.manager.exception.collection.LeaveNotFoundException;
import io.leave.manager.exception.collection.UserNotFoundException;
import io.leave.manager.repository.LeaveBookingRepository;
import io.leave.manager.repository.UserRepository;
import io.leave.manager.service.EmailService;
import io.leave.manager.service.LeaveBookingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import static io.leave.manager.constant.LeaveConstant.*;
import static io.leave.manager.constant.UserImplConstant.USER_WITH_EMAIL_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveBookingService {

    private final LeaveBookingRepository leaveBookingRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    @Override
    public LeaveBooking requestForLeave(LeaveRequest request) throws MessagingException, UserNotFoundException {
       if(userRepository.findByEmail(request.getUserEmail()) == null) throw new UserNotFoundException(String.format(USER_WITH_EMAIL_NOT_FOUND, request.getUserEmail()));
        LeaveBooking leaveBooking = buildLeave(request);
        emailService.sendRegistrationEmailNotification(leaveBooking.getUser().getEmail(), LEAVE_REQUEST);
        return leaveBookingRepository.save(leaveBooking);
    }

    private LeaveBooking buildLeave(LeaveRequest request) {

        return LeaveBooking.builder()
                .status(PENDING)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .user(userRepository.findByEmail(request.getUserEmail()))
                .build();
    }

    @Override
    public void cancelRequestedLeave(String leaveId, String email) throws LeaveNotFoundException{
        Optional<LeaveBooking> leaveBooking = getRequestedLeave(leaveId);
        if(leaveBooking.isEmpty() || !leaveBooking.get().getUser().getEmail().equals(email)){
            throw new LeaveNotFoundException(LEAVE_NOT_FOUND);
        }
        leaveBookingRepository.deleteById(leaveId);
    }

    @Override
    public Optional<LeaveBooking> findLeaveByIdAndUserEmail(String id, String email) throws LeaveNotFoundException {
        Optional<LeaveBooking> leaveBooking = getRequestedLeave(id);
        if(leaveBooking.isEmpty() || !leaveBooking.get().getUser().getEmail().equals(email)){
            throw new LeaveNotFoundException(LEAVE_NOT_FOUND);
        }
        return leaveBooking;
    }

    @Override
    public List<LeaveBooking> getPendingLeaveBookings(){
        return leaveBookingRepository.findAll()
                .stream()
                .filter(leave -> leave.getStatus().equals(PENDING))
                .toList();
    }

    private Optional<LeaveBooking> getRequestedLeave(String id) {
        return  leaveBookingRepository.findById(id);
    }
}
