package io.leave.manager.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "leaves")
@Builder
public class LeaveBooking {
    @Id @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    @NotNull(message = "Start Date Must Not Be Null")
    private LocalDate startDate;
    @NotNull(message = "End Date Must Not Be Null")
    private LocalDate endDate;
    private String status;
    private User user;
}
