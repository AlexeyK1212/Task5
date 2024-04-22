package ru.project.task5.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;
import org.springframework.stereotype.Component;

@Component
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_code")
public class ProductCode
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false) @Nationalized
    private String name;
}
