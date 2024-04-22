package ru.project.task5.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.project.task5.entities.*;

import java.util.List;
import java.util.Optional;


@Repository
public interface TppProductRegisterRep extends JpaRepository<TppProductRegister, Integer>  {


    @Query(nativeQuery=true, value= "select count(*) from tpp_product_register where product_id= :instanceID and type= :registryTypeCode")

    Integer getTppProductRegister(
            @Param("instanceID") Long instanceID, @Param("registryTypeCode") String registryTypeCode );

}
