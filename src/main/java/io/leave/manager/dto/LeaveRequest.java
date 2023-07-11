package io.leave.manager.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private String userEmail;

}
