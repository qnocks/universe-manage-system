package com.qnocks.universemanagesystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "planet")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ruler_id", referencedColumnName = "id")
    private Ruler ruler;
}
