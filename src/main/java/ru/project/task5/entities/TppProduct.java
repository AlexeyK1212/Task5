package ru.project.task5.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Table(name = "tpp_product")
public class TppProduct
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // serial PRIMARY KEY,

@OneToMany(mappedBy = "tppProduct")
    List<Agreement> Agreements;

    @Column(name = "product_code_id")
    private Long product_code_id;

    @Column(name = "client_id")
    private Long client_id;



    @Column(length = 50, nullable = false) @Nationalized
    private String type; // VARCHAR(50),

    @Column(length = 50, nullable = false) @Nationalized
    private String number; // VARCHAR(50),

    @Column(nullable = false)
    private Long priority; // BIGINT,

    @Column(nullable = false)
    private Timestamp date_of_conclusion; // TIMESTAMP,
    private Timestamp start_date_time; // TIMESTAMP,
    private Timestamp end_date_time; // TIMESTAMP,

    private Long days; // BIGINT,

    @Column(columnDefinition = "DECIMAL")
    private Float penalty_rate; // DECIMAL,

    @Column(columnDefinition = "DECIMAL")
    private Float nso; // DECIMAL,

    @Column(columnDefinition = "DECIMAL")
    private Float threshold_amount; // DECIMAL,

    @Column(length = 50) @Nationalized
    private String requisite_type; // VARCHAR(50),

    @Column(length = 50) @Nationalized
    private String interest_rate_type; // VARCHAR(50),

    @Column(columnDefinition = "DECIMAL")
    private Float tax_rate; // DECIMAL,

    @Column(length = 100) @Nationalized
    private String reasone_close; // VARCHAR(100),

    @Column(length = 50) @Nationalized
    private String state; // VARCHAR(50)
}

