package io.leave.manager.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Document(collection = "leaves")
@Builder
public class LeaveBooking {
    @Id @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    @NotNull(message = "Start Date Must Not Be Null")
    @DateTimeFormat(fallbackPatterns = "MM/dd/yyyy")
    private String startDate;
    @NotNull(message = "End Date Must Not Be Null")
    @DateTimeFormat(fallbackPatterns = "MM/dd/yyyy")
    private String endDate;
    private String status;
    private Long duration;
    @NotNull(message = "Start Date Must Not Be Null")
    private User user;
}
