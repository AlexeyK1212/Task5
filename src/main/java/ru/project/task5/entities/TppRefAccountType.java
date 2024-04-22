package ru.project.task5.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tpp_ref_account_type")
public class TppRefAccountType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internal_id; // serial PRIMARY KEY ,

    @NotNull
    @Column(unique = true, nullable = false, length = 100)
    @Nationalized
    private String value; // VARCHAR(100) UNIQUE NOT NULL
}
