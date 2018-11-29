package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.model.User;
import com.company.project.model.UserAuth;
import com.company.project.service.UserService;
import com.company.project.vo.UserModifyVO;
import com.company.project.vo.UserRegisiterVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;


@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据用户名查询用户
        User user = findBy("username",s);
        if(null == user){
            return null;
        }
        UserAuth userAuth = new UserAuth(user.getUsername(),user.getPassword(),
                null);
        return userAuth;
    }

    @Override
    public User regisiter(UserRegisiterVO userVO) {
        //注册用户
        User user = new User();
        user.setUsername(userVO.getUsername());
        user.setPassword(DigestUtils.md5DigestAsHex(userVO.getPassword().getBytes()));
        user.setNickname(userVO.getNickname());
        user.setSex(userVO.getSex());
        save(user);
        return null;
    }

    @Cacheable(value = "user",key="'user'.concat(':').concat(#username)")
    @Override
    public User findByUsername(String username) {
        User user = findBy("username",username);
        user.setPassword(null);
        return user;
    }

    @CachePut(value = "user",key="'user'.concat(':').concat(#username)")
    @Override
    public User modify(String username,UserModifyVO userModifyVO) {
        User user = findBy("username",username);
        int index = 0;
        String nickname = userModifyVO.getNickname();
        Integer sex = userModifyVO.getSex();
        //昵称
        if(StringUtils.isNotBlank(nickname)){
            index++;
            user.setNickname(nickname);
        }
        //性别
        if(null != sex){
            index++;
            user.setSex(sex);
        }
        if(index > 0){
            update(user);
        }
        user.setPassword(null);
        return user;
    }
}
