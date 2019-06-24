package com.lambdaschool.money.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "quotes")
public class Profile extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long profileid;

    @Column(nullable = false)
    private String quote;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userid")
//    private User user;

    public Profile()
    {
    }

    public Profile(String quote)
    {
        this.quote = quote;
//        this.user = user;
    }

    public long getProfileid()
    {
        return profileid;
    }

    public void setProfileid(long profileid)
    {
        this.profileid = profileid;
    }

    public String getQuote()
    {
        return quote;
    }

    public void setQuote(String quote)
    {
        this.quote = quote;
    }

//    public User getUser()
//    {
//        return user;
//    }
//
//    public void setUser(User user)
//    {
//        this.user = user;
//    }
}