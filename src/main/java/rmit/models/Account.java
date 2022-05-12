package rmit.models;


import lombok.Data;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String profile_picture;

    @Enumerated(value = EnumType.STRING)
    private ERole roles ;

    public Account(){}


    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString(){
        String info = "";
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("user_name",this.username );
        info = jsonInfo.toString();
        return info;
    }

    public void updateAccount(Account account){
        this.id = account.getId();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.profile_picture = account.getProfile_picture();
    }


}
