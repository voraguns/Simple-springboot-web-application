package web;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="teams")
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int code;
    
    @Column(unique = true, nullable = false)
    String name;
    String type = "unknown";
}
