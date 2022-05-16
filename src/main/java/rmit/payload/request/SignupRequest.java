package rmit.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
    @NotBlank
    private String username;

    private String role;

    private String title;

    private String nickName;

    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public String getTitle() {
        return title;
    }

    public String getNickName() {
        return nickName;
    }

    public void setRole(String role) {
        this.role = role;
    }
}