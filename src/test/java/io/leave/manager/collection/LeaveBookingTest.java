package io.leave.manager.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static io.leave.manager.constant.LeaveConstant.PENDING;
import static org.junit.jupiter.api.Assertions.*;

class LeaveBookingTest {

    private LeaveBooking leaveBooking;
    private User user;

    @BeforeEach
    public void setUp(){
        this.leaveBooking = LeaveBooking.builder()
                .status(PENDING)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(3))
                .build();
        user = User.builder()
                .roles("ADMIN")
                .firstName("A")
                .lastName("B")
                .username("ab")
                .email("a@test.com")
                .build();
    }

    @Test
    public void givenDatesCreateBuildLeaveWithoutUser(){
        assertAll(
                () -> assertNull(leaveBooking.getUser()),
                () -> assertEquals(leaveBooking.getStatus(), "PENDING"),
                () -> assertNotNull(leaveBooking.getStartDate()),
                () -> assertNotNull(leaveBooking.getEndDate())
        );
    }

    @Test
    void testCreateLeaveWithUser(){
        this.leaveBooking.setUser(this.user);

        assertAll(
                () -> assertNotNull(leaveBooking.getUser()),
                () -> assertNotNull(leaveBooking.getUser().getFirstName()),
                () -> assertNotNull(leaveBooking.getUser().getLastName()),
                () -> assertNotNull(leaveBooking.getUser().getEmail()),
                () -> assertEquals(leaveBooking.getStatus(), "PENDING"),
                () -> assertNotNull(leaveBooking.getStartDate()),
                () -> assertNotNull(leaveBooking.getEndDate())
        );
    }

}