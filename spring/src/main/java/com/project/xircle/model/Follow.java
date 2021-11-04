package com.project.xircle.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Follow extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fllow_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "following")
    User following;

    @ManyToOne
    @JoinColumn(name = "follower")
    User follower;
}
