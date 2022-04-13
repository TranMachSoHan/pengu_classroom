package rmit.models;


import lombok.Data;
import org.json.JSONObject;

import javax.persistence.*;

@Entity
@Data
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name", nullable = true)
    private String user_name;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "profile_picture", nullable = true)
    private String profile_picture;

    public Account(){}

    public Account(String user_name){this.user_name = user_name;}

    public Account(int id, String user_name, String password, String profile_picture) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
        this.profile_picture = profile_picture;
    }

    @Override
    public String toString(){
        String info = "";
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("user_name",this.user_name );
        info = jsonInfo.toString();
        return info;
    }

    public void updateAccount(Account account){
        this.id = account.getId();
        this.user_name = account.getUser_name();
        this.password = account.getPassword();
        this.profile_picture = account.getProfile_picture();
    }

}
