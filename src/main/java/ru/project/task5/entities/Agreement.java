package ru.project.task5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Table(name = "agreement")
@Entity
public class Agreement
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // serial PRIMARY KEY,

//    @ManyToOne  //(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, optional = false)
//    @JoinColumn(name = "product_id")//, nullable = false)
    // @OnDelete(action = OnDeleteAction.CASCADE)


    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TppProduct tppProduct; // product_id integer,

    @Column
    private  Long product_id;


    @Column(length = 50) @Nationalized
    private String generalAgreementId; // VARCHAR(50),

    @Column(length = 50) @Nationalized
    private String supplementary_agreement_id; // VARCHAR(50),

    @Column(length = 50) @Nationalized
    private String arrangement_type; // VARCHAR(50),

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, optional = false)
    @JoinColumn(name = "sheduler_job_id", nullable = false, insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ShedulerJob shedulerJob; //sheduler_job_id BIGINT,

    @Column
    private  Long sheduler_job_id;


    @Column(length = 50, nullable = false) @Nationalized
    private String number;// VARCHAR(50),

    @Column(nullable = false)
    private Timestamp opening_date; //TIMESTAMP,
    private Timestamp closing_date; //TIMESTAMP,
    private Timestamp cancel_date; //TIMESTAMP,

    private Long validity_duration; //BIGINT,

    @Column(length = 100) @Nationalized
    private String cancellation_reason; // VARCHAR(100),

    @Column(length = 50) @Nationalized
    private String status; // VARCHAR(50),

    private Timestamp interest_calculation_date; // TIMESTAMP,

    @Column(columnDefinition = "DECIMAL")
    private Float interest_rate; // DECIMAL,

    @Column(columnDefinition = "DECIMAL")
    private Float coefficient; // DECIMAL,

    @Column(length = 50) @Nationalized
    private String coefficient_action; // VARCHAR(50),

    @Column(columnDefinition = "DECIMAL")
    private Float minimum_interest_rate; // DECIMAL,

    @Column(columnDefinition = "DECIMAL")
    private Float minimum_interest_rate_coefficient; // DECIMAL,

    @Column(length = 50) @Nationalized
    private String minimum_interest_rate_coefficient_action; // VARCHAR(50),

    @Column(columnDefinition = "DECIMAL")
    private Float maximal_interest_rate; // DECIMAL,

    @Column(columnDefinition = "DECIMAL")
    private Float maximal_interest_rate_coefficient; // DECIMAL,

    @Column(length = 50) @Nationalized
    private String maximal_interest_rate_coefficient_action; // VARCHAR(50)
}
