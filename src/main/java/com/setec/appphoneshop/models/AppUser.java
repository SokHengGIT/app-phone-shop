package com.setec.appphoneshop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table (name = "appuser")
@Setter
@Getter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name", unique = true, nullable = false, length = 50)
    private String username;
    @Column(name = "password", nullable = false, length = 200)
    private String password;
    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns=@JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<Role> roles;
}
