package ru.project.task5.repositories;


import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.project.task5.entities.*;

import java.util.List;
import java.util.Optional;


@Repository
public interface TppRefProductRegisterTypeRep extends JpaRepository<TppRefProductRegisterType, Integer>  {

  //  List<TppRefProductRegisterType> findByTppRefProductClassAndTppRefAccountType(@NotNull TppRefProductClass tppRefProductClass, TppRefAccountType tppRefAccountType);

  //  List<TppRefProductRegisterType> findByTppRefProductClassValueAndTppRefAccountTypeValue(String val, String accType);

  //  List<TppRefProductRegisterType> findByTppRefProductClassValue(String val);

    @Query(nativeQuery=true, value= "select b.value from tpp_ref_product_class a join tpp_ref_product_register_type b on a.value= b.product_class_code and a.value=:value ")  // +

    List<String> getTppRefProductRegisterType(
            @Param("value") String value );

    @Query(nativeQuery=true, value= "select value from tpp_ref_product_register_type where value = :value limit 1")
    String getByRegistryTypeCode(@Param("value")  String registryTypeCode);



    public static interface AccountNumberStuct {
        Integer getId();
        String getAccountnumber();
    }



}
