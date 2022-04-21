package rmit.models;


import org.json.JSONObject;

import javax.persistence.*;

@MappedSuperclass
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String user_name;

    private String password;

    private String profile_picture;

    public Account(){}


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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


}
