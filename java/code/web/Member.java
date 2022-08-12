package web;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name="members")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int code;
    
    @Column(unique = true, nullable = false)
    public String email;
    
    @Column(nullable = false)
    String password;
    
    @Column(name="first_name", nullable = false)
    String firstName;
    
    @Column(name="last_name", nullable = false)
    String lastName;
    
    @Column(nullable = false)
    String type = "unknown"; // unknown, member, staff, administrator
    
    @ManyToOne
    Team team;
    
    public boolean isAdmin() {
        return "Admin".equals(type);
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getFirstname(){
        return firstName;
    }
    
    public String getLastname(){
        return lastName;
    }
    
    public String getType(){
        return type;
    }
    
    public Team getTeam(){
        return team;
    }
}
