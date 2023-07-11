package io.leave.manager.service;

import io.leave.manager.collection.LeaveBooking;
import io.leave.manager.repository.LeaveBookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.List;
import static io.leave.manager.constant.EmailConstant.CONFIRMED_LEAVE_REQUEST;
import static io.leave.manager.constant.EmailConstant.PENDING_LEAVE_REQUEST;
import static io.leave.manager.constant.LeaveConstant.CONFIRMED;

@Service
public class LeaveConfirmationService {

    @Autowired private EmailService emailService;
    @Autowired private LeaveBookingRepository leaveBookingRepository;
    @Autowired LeaveBookingService leaveBookingService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "0 30 7 ? * *")
    public void automateLeaveScheduler() throws MessagingException {
        List<LeaveBooking> bookings = leaveBookingService.getPendingLeaveBookings();
        for (LeaveBooking booking: bookings) {
            if(isNotification(booking.getStartDate(), booking.getEndDate())){
                emailService.sendRegistrationEmailNotification(booking.getUser().getEmail(), PENDING_LEAVE_REQUEST);
                LOGGER.info(String.format("\n %s Your Leave Request Will Be Confirmed on %s", booking.getUser().getUsername(), booking.getStartDate()));
            }
            if(booking.getStartDate().isEqual(LocalDate.now())){
                booking.setStatus(CONFIRMED);
                leaveBookingRepository.save(booking);
                emailService.sendRegistrationEmailNotification(booking.getUser().getEmail(), CONFIRMED_LEAVE_REQUEST);
                LOGGER.info(String.format("\n %s Leave Confirmed on %s. Enjoy Your Holiday",
                        booking.getUser().getUsername(), booking.getStartDate()));
            }
        }
    }

    private boolean isNotification(LocalDate startDate, LocalDate endDate){
        return startDate.isEqual(LocalDate.now()) || startDate.plusDays(3).isEqual(endDate);
    }


}
