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
@RequiredArgsConstructor
@ToString(exclude = "planets")
@EqualsAndHashCode(of = "id")
public class Ruler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "ruler", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Planet> planets = new HashSet<>();
}
