package model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name="user_id")
    private Account account;

    public Teacher(Account account) {this.setAccount(account);}

    public Teacher() {}

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Collection<Course> courses;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
