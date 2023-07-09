package io.leave.manager.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
@Data
@Builder
public class User {

    @Id @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    private String firstName;
    private String lastName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Indexed(unique = true)
    private String email;
    private String username;
    private String role;
    private String[] authorities;
    private boolean isSupervisor;

//    TODO
//    CONFIGURE DATABASE
//    ADD SWAGGER FOR API DOCUMENTATION
//    DOCUMENT SERVICE IN THE README FILE
//    IMPLEMENTS UNIT, INTEGRATION AND SYSTEM OR API TESTING FOR THE SERVICES
//    ENSURE TO USE YOUR RESPONSE TYPE FOR RETURNING APPROPRIATE RESPONSES
//    ALSO ADD EXCEPTIONS HANDLING TO THIS APPLICATION TO MAKE IT ROBUST
}
