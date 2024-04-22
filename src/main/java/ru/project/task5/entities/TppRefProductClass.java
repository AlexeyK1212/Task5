package ru.project.task5.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tpp_ref_product_class")
public class TppRefProductClass
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internal_id; // serial PRIMARY KEY ,

    @NotNull
    @Column(unique = true, nullable = false, length = 100) @Nationalized
    private String value; // VARCHAR(100) UNIQUE NOT NULL,

    @Column(length = 50) @Nationalized
    private String gbi_code; // VARCHAR(50),

    @Column(length = 100) @Nationalized
    private String gbi_name; // VARCHAR(100),

    @Column(length = 50) @Nationalized
    private String product_row_code; // VARCHAR(50),

    @Column(length = 100) @Nationalized
    private String product_row_name; // VARCHAR(100),

    @Column(length = 50) @Nationalized
    private String subclass_code; // VARCHAR(50),

    @Column(length = 100) @Nationalized
    private String subclass_name; // VARCHAR(100)
}

