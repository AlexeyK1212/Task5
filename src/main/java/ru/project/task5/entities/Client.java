package ru.project.task5.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class Client
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150) @Nationalized
    private String name;
}
