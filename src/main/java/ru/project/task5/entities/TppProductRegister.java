package ru.project.task5.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "tpp_product_register")
public class TppProductRegister
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // serial PRIMARY KEY ,

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, optional = false)
//    @JoinColumn(name = "product_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private TppProduct tppProduct; //  product_id; // BIGINT,

//    @NotNull
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, optional = false)
 //   @JoinColumn(name = "type", nullable = false)
 //   private TppRefProductRegisterType tppRefProductRegisterType; // String type; // VARCHAR(100) NOT NULL,

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, optional = false)
//    @JoinColumn(name = "account", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)

    @Column(name = "product_id")
    private Long product_id;


    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "value", insertable = false, updatable = false)
    private TppRefProductRegisterType tppRefProductRegisterType;


    @Column(name = "type")
    private String type;


    private Long account; // BIGINT,

    @Column(length = 30) @Nationalized
    private String currency_code; // VARCHAR(30),

    @Column(length = 50) @Nationalized
    private String state; // VARCHAR(50),

    @Column(length = 25) @Nationalized
    private String account_number; // VARCHAR(25)
}
