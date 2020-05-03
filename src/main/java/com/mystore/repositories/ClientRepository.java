package com.mystore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
