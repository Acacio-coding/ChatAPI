package com.ifsc.chatapi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_groups")
@Getter
@Setter
@RequiredArgsConstructor
public class GroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "group_participants", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "participants", nullable = false)
    private Set<Integer> participants;
}
