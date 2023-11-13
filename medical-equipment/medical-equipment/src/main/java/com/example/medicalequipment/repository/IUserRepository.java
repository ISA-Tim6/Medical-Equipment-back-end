package com.example.medicalequipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.User;
import java.util.List;



@Repository
public interface IUserRepository extends JpaRepository<User, Long>{


}
