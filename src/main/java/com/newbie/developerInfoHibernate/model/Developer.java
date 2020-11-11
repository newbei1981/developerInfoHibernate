package com.newbie.developerInfoHibernate.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "developer")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn (name="account_id")

    private Account account;


    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable (name="developer_skills",
            joinColumns=@JoinColumn (name="developer_id"),
            inverseJoinColumns=@JoinColumn(name="skill_id"))
    private Set<Skill> skills;

    public Developer(){};

    public Developer(Long id, String name, Account account, Set<Skill> skills) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        String s = "";
        if (skills != null) skills.stream().forEach(n -> s.concat(" ").concat(n.toString()));
        return "Developer " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account=" + account + " skills ^ :" + s;
    }
}
