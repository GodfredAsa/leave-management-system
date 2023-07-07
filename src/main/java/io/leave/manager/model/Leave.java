package io.leave.manager.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "leaves")
public class Leave {
    @Id
    private Long id;
    private String leaveId;
    private Date startDate;
    private Date endDate;
    private Employee employee;
}
