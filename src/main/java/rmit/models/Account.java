package rmit.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String profile_picture;

    private String email;

    private String phoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;

    @Enumerated(value = EnumType.STRING)
    private ERole roles ;

    public Account(){}


    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.email="";
        this.phoneNumber="";

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
        this.username = account.getUsername();
        this.profile_picture = account.getProfile_picture();
        this.email = account.getEmail();
        this.birthday = account.getBirthday();
        this.phoneNumber = account.getPhoneNumber();
    }

    public String generateAccountID() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date());
    }
}
