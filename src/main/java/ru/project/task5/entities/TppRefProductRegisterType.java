package ru.project.task5.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tpp_ref_product_register_type")
public class TppRefProductRegisterType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internal_id; // serial PRIMARY KEY ,

    @Column(name = "value")
    private String value;


 //   @NotNull
//    @Column(unique = true, nullable = false, length = 100) @Nationalized
 //   private String value; // VARCHAR(100) UNIQUE NOT NULL,

    @NotNull
    @Column(length = 100, nullable = false) @Nationalized
    private String register_type_name; // VARCHAR(100) NOT NULL,

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, optional = false)
    //@ForeignKey(name="FK_product_class_code", con)
    @JoinColumn(name = "product_class_code", nullable = false)
    private TppRefProductClass tppRefProductClass; // String product_class_code; // VARCHAR(100) NOT NULL,

    private Timestamp register_type_start_date; // TIMESTAMP,
    private Timestamp register_type_end_date; // TIMESTAMP,

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, optional = false)
////    @JoinColumn(name = "account_type", nullable = false)
//    private TppRefAccountType tppRefAccountType; // account_type; // VARCHAR(100)

}
