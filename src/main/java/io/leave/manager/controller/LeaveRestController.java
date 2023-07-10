package io.leave.manager.controller;

import io.leave.manager.collection.LeaveBooking;
import io.leave.manager.exception.collection.UserNotFoundException;
import io.leave.manager.service.LeaveBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping(value = "api/leaves")
@RequiredArgsConstructor
public class LeaveRestController {

    private final LeaveBookingService leaveBookingService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public LeaveBooking makeLeave(@RequestBody LeaveBooking leaveBooking, @PathVariable String userId) throws UserNotFoundException, ParseException {
       return leaveBookingService.bookLeave(leaveBooking, userId);
    }


//    CREATE LEAVE

//    UPDATE LEAVE

//    GET LEAVE BY ID

//    GET ALL LEAVES BY A USERNAME OR EMAIL

//    UPDATE LEAVE

//    DELETE OR CANCEL LEAVE


//    SUPERVISOR CONFIRM ADMIN




}
