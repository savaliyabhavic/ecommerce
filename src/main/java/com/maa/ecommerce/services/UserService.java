package com.maa.ecommerce.services;


import com.maa.ecommerce.datamanager.UserDataManager;
import com.maa.ecommerce.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService
{

    @Autowired
    UserDataManager userDataManager;

    public UserModel insertUser(UserModel userModel){
        // Business Logic is here
        return this.userDataManager.insertUser(userModel);
    }

    public List<Map<String, Object>> getAllUsers()
    {
        return this.userDataManager.getAllUser();
    }

    public UserModel getOneUser(int id)
    {
        return this.userDataManager.getOneUser(id);
    }

    public UserModel updateUser(UserModel userModel)
    {
        return this.userDataManager.updateUser(userModel);
    }

    public UserModel deleteUser(int id)
    {
        return this.userDataManager.deleteUser(id);
    }
}
