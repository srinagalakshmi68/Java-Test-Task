package com.example.demo.repository;

import com.example.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	 List<Client> findByNameStartingWith(String name);

	    List<Client> findByDobGreaterThanEqual(LocalDate dob);

	    List<Client> findByPhonesContaining(String phone);

	    List<Client> findByEmailsContaining(String email);

}
