package com.guner.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "OWNER_DETAILS")
@Setter
@Getter
public class Owner {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Blog> blogList;

    /*
        aynı sonucu verir.
        Owner class:
        @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "owner_id")
        private List<Blog> blogList;

        Blog class:
        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Owner owner;
     */

    public Owner() { }

    public Owner(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Owner{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
    }
}