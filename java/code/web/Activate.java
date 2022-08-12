package web;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity @Table(name="activates")
class Activate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int code;
    
    @OneToOne
    Member member;
    
    String secret;
}
