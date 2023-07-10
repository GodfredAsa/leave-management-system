package io.leave.manager.service.impl;

import io.leave.manager.collection.LeaveBooking;
import io.leave.manager.collection.User;
import io.leave.manager.exception.collection.UserNotFoundException;
import io.leave.manager.repository.LeaveBookingRepository;
import io.leave.manager.repository.UserRepository;
import io.leave.manager.service.LeaveBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static io.leave.manager.constant.UserImplConstant.USER_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveBookingService {

    private final LeaveBookingRepository leaveBookingRepository;
    private final UserRepository userRepository;

    @Override
    public LeaveBooking bookLeave(LeaveBooking leaveBooking, String userId) throws UserNotFoundException, ParseException {
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()) throw new UserNotFoundException(USER_NOT_FOUND_BY_ID);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date firstDate = sdf.parse(leaveBooking.getStartDate());
        Date secondDate = sdf.parse(leaveBooking.getEndDate());
        long datar = Math.abs(secondDate.getTime() - firstDate.getTime());
        long duration = TimeUnit.DAYS.convert(datar, TimeUnit.DAYS);
        leaveBooking.setDuration(duration);
        return leaveBookingRepository.save(leaveBooking);
    }

    @Override
    public LeaveBooking updateBookedLeave(String id, LeaveBooking leaveBooking) {
        return null;
    }

    @Override
    public void cancelBookedLeave(String id) {

    }

    @Override
    public LeaveBooking findLeaveById(String id) {
        return null;
    }
}
