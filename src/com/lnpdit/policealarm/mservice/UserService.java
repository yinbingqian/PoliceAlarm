package com.lnpdit.policealarm.mservice;

import com.lnpdit.policealarm.base.framework.BaseService;
import com.lnpdit.policealarm.entity.UserEntity;
import com.lnpdit.policealarm.entity.http.request.RegisterEntity;

public interface UserService extends BaseService {
    
    void userRegister(RegisterEntity registerEntity);

    void userLogin(String username, String password);

    void getUserInfo(String userid);

    void getContacts(String userid);
    
    void updateUserInfo(UserEntity userEntity);
    
    void test(String cityCode);

}
