package com.company.project.service;

import com.company.project.core.Service;
import com.company.project.model.User;
import com.company.project.vo.UserModifyVO;
import com.company.project.vo.UserRegisiterVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends Service<User>, UserDetailsService {

    User regisiter(UserRegisiterVO userVO);

    User findByUsername(String username);

    User modify(String username,UserModifyVO userModifyVO);
}
