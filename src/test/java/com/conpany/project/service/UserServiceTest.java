package com.conpany.project.service;

import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.vo.UserModifyVO;
import com.company.project.vo.UserRegisiterVO;
import com.conpany.project.Tester;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends Tester {

    @Autowired
    private UserService userService;

    @Test
    public void testRegisiter(){
        UserRegisiterVO userRegisiterVO = new UserRegisiterVO();
        userRegisiterVO.setUsername("18216497718");
        userRegisiterVO.setPassword("666666");
        userRegisiterVO.setNickname("小新");
        userRegisiterVO.setSex(0);
        userService.regisiter(userRegisiterVO);
    }

    @Test
    public void testFindByUsername(){
        User user = userService.findByUsername("18216497718");
        Assert.assertNotNull(user);
    }

    public void testModify(){
        UserModifyVO userModifyVO = new UserModifyVO();
        userModifyVO.setSex(1);
        userService.modify("18216497718",userModifyVO);
    }
}
