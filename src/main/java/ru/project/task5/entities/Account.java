package ru.project.task5.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // serial PRIMARY KEY,

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, optional = false)
    @JoinColumn(name = "account_pool_id", nullable = false) @ToString.Exclude
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private AccountPool accountPool; // account_pool_id integer,

    @Column(length = 25) @Nationalized
    private String account_number;// VARCHAR(25),

    @Column(columnDefinition = "BOOLEAN")
    private Boolean bussy; // BOOLEAN
}
