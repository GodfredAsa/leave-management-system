package io.leave.manager.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@Builder
public class User{
    @Id @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    @NotNull(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    private String lastName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password is required")
    private String password;
    @Indexed(unique = true)
    private String email;
    @NotNull(message = "username is required")
    @Indexed(unique = true)
    private String username;
    private String roles;
    private boolean isSupervisor;

}
