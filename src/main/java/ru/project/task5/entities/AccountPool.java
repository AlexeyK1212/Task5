package ru.project.task5.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account_pool")
public class AccountPool
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // serial PRIMARY KEY,

    @OneToMany(mappedBy = "accountPool", fetch = FetchType.EAGER)
    private List<Account> accountList;

    @Column(length = 50) @Nationalized
    private String branch_code; // VARCHAR(50),

    @Column(length = 30) @Nationalized
    private String currency_code; // VARCHAR(30),

    @Column(length = 50) @Nationalized
    private String mdm_code; // VARCHAR(50),

    @Column(length = 30) @Nationalized
    private String priority_code; // VARCHAR(30),

    @Column(length = 50) @Nationalized
    private String registry_type_code; // VARCHAR(50)
}

