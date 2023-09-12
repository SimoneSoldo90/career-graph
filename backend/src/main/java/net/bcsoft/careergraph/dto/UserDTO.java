package net.bcsoft.careergraph.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDTO(Long id, @JsonProperty("sso_uid") String ssoUid, @JsonProperty("first_name") String firstName, @JsonProperty("last_name") String lastName, String email) {}

/*
public class UserDTO {
    Integer id;
    @JsonProperty("sso_uid")
    String ssoUid;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    String email;

    public UserDTO() {
    }

    public UserDTO(Integer id, String ssoUid, String firstName, String lastName, String email) {
        this.id = id;
        this.ssoUid = ssoUid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSsoUid() {
        return ssoUid;
    }

    public void setSsoUid(String ssoUid) {
        this.ssoUid = ssoUid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
*/