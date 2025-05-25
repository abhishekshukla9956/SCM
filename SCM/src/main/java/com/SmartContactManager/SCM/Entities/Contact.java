package com.SmartContactManager.SCM.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "contact")
public class Contact {


    
     @Id
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String picture;
    private String description;
    private boolean favourite=false;
    private String websiteLink;
    private String linkedInLink;

    //mapping
    //many to one
    @ManyToOne
    @JoinColumn(name = "user_user_id",referencedColumnName = "userId")
    private User user;
    
    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
        private List<SocialLink> links =new ArrayList<>(); 

}
