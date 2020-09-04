package com.mystore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
