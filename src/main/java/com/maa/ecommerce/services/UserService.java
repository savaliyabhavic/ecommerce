package com.maa.ecommerce.services;


import com.maa.ecommerce.datamanager.UserDataManager;
import com.maa.ecommerce.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService
{

    @Autowired
    UserDataManager userDataManager;

    public User insertUser(User user){
        // Business Logic is here
        return this.userDataManager.insertUser(user);
    }

    public List<Map<String, Object>> getAllUsers()
    {
        return this.userDataManager.getAllUser();
    }

    public User getOneUser(int id)
    {
        return this.userDataManager.getOneUser(id);
    }

    public User updateUser(User user)
    {
        return this.userDataManager.updateUser(user);
    }

    public User deleteUser(int id)
    {
        return this.userDataManager.deleteUser(id);
    }
}
