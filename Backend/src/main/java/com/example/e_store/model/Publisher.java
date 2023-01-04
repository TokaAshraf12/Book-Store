package com.example.e_store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Publisher {
    @Id
    private String name;
    @Column(nullable = false, length = 100)
    private String address;
    @Column(nullable = false, length = 15)
    private String phone;
}
