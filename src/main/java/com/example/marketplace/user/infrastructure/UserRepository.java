package com.example.marketplace.user.infrastructure;


import com.example.marketplace.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}