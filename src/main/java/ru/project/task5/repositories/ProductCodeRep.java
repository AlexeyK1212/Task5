package ru.project.task5.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.project.task5.entities.*;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductCodeRep extends JpaRepository<ProductCode, Integer>  {


}
