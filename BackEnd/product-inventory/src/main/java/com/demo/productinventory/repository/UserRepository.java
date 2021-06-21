package com.demo.productinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.productinventory.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
