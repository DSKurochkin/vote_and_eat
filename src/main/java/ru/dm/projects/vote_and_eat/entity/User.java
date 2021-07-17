package ru.dm.projects.vote_and_eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(name ="surname" )
    private String surname;

    // while without roles
//    @JoinColumn(name="roles_id")
//    private Set<Role> roles;





}
