package com.eugene.shopdiplom.dao;

import com.eugene.shopdiplom.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByName(String name);

}
