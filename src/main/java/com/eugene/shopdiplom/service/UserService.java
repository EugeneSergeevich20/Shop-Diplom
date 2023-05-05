package com.eugene.shopdiplom.service;

import com.eugene.shopdiplom.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean save(UserDTO userDTO);

}
