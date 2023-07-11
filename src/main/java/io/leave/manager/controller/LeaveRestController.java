package io.leave.manager.controller;

import io.leave.manager.collection.LeaveBooking;
import io.leave.manager.dto.LeaveRequest;
import io.leave.manager.exception.ExceptionHandling;
import io.leave.manager.exception.collection.LeaveNotFoundException;
import io.leave.manager.service.LeaveBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/leaves")
@RequiredArgsConstructor
public class LeaveRestController extends ExceptionHandling {

    private final LeaveBookingService leaveBookingService;

    @RequestMapping(value = "/users" , method = RequestMethod.POST)
    public LeaveBooking requestLeave(@RequestBody LeaveRequest request) throws MessagingException {
       return leaveBookingService.requestForLeave(request);
    }

    @RequestMapping(value = "/{leaveId}/users/{email}" , method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Optional<LeaveBooking> findLeaveByIdAndUserEmail(@PathVariable String leaveId, @PathVariable String email) throws LeaveNotFoundException {
        return leaveBookingService.findLeaveByIdAndUserEmail(leaveId, email);
    }

    @RequestMapping(value = "/{leaveId}/users/{email}" , method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequestedLeave(@PathVariable String leaveId, @PathVariable String email) throws LeaveNotFoundException{
        leaveBookingService.cancelRequestedLeave(leaveId, email);
    }
}
