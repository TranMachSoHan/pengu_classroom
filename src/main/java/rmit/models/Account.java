package rmit.models;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.json.JSONObject;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;

    private String password;

    private String profile_picture;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Account(){}


    public Account(int id, String username, String password, String profile_picture, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.profile_picture = profile_picture;
        this.role = role;
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

    public String generateAccountID() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date());
    }
}
