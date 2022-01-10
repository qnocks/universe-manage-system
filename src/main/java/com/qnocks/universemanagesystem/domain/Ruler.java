package com.qnocks.universemanagesystem.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ruler")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "planets")
@EqualsAndHashCode(of = "id")
public class Ruler {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "ruler", cascade = CascadeType.ALL)
    private Set<Planet> planets = new HashSet<>();
}
