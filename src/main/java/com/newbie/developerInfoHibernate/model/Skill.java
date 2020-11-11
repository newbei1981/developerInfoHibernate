package com.newbie.developerInfoHibernate.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany//(fetch = FetchType.EAGER)
    @JoinTable (name="developer_skills",
            joinColumns=@JoinColumn (name="skill_id"),
            inverseJoinColumns=@JoinColumn(name="developer_id"))
    private Set<Developer> developers;

    public Skill(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public Skill(){}

    public Skill(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
