package rmit.models;


import org.json.JSONObject;

import javax.persistence.*;

@Entity
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



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUser_name(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
