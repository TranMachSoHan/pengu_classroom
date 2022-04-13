package model;


import org.json.JSONObject;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String user_name;
    private String password;
    private String profile_picture;

    public Account(){}

    public Account(String user_name){this.user_name = user_name;}

    //name
    public String getUser_name(){
        return user_name;
    }
    public void setUser_name(String name){
        this.user_name = name;
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

    @Override
    public String toString(){
        String info = "";
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("user_name",this.user_name );
        info = jsonInfo.toString();
        return info;
    }


}
