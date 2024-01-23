package com.example.medicalequipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.User;
import java.util.List;



@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
	@Query("select u from User u where u.username=?1")
	User findByUsername(String username);

	@Query("select u from User u where u.email=?1")
	User findByEmail(String email);

	@Query("select u.user_id from User u where u.username=?1")
	Long findIdByUsername(String username);
	
	@Query(value = "SELECT distinct ru.user_id from reservation r inner join appointment a on r.appointment_id=a.appointment_id\r\n"
			+ "inner join company_admin ca on ca.user_id=a.user_id inner join reservation_user ru on ru.reservation_id=r.reservation_id\r\n"
			+ "where ca.company_id=:company_id", nativeQuery = true)
	List<Long> getRegistratedUsersThatMadeReservationAtCompany(Long company_id);
}