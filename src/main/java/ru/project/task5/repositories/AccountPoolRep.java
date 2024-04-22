package ru.project.task5.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.project.task5.entities.*;


@Repository
public interface AccountPoolRep extends JpaRepository<AccountPool, Integer>  {


    @Query(nativeQuery=true, value= "select b.id id, b.account_number accountnumber from account_pool a\n" +
            "join\n" +
            "account b\n" +
            "on a.branch_code=:branchCode and\n" +
            "a.currency_code=:currencyCode and\n" +
            "a.mdm_code= :mdmCode and\n" +
            "a.priority_code=:priorityCode and\n" +
            "a.registry_type_code=:registryTypeCode and\n" +
            "a.id=b.account_pool_id\n" +
            "limit 1")  // +

    AccountNumberStuct getAccount(
            @Param("branchCode") String branchCode,
            @Param("currencyCode") String currencyCode,
            @Param("mdmCode") String mdmCode,
            @Param("priorityCode") String priorityCode,
            @Param("registryTypeCode") String registryTypeCode

    );


    public static interface AccountNumberStuct {
         Integer getId();
         String getAccountnumber();
    }



}
