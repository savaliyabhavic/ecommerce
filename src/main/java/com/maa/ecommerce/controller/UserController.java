package com.maa.ecommerce.controller;


import com.maa.ecommerce.exceptions.ErrorDetails;
import com.maa.ecommerce.exceptions.NotFoundException;
import com.maa.ecommerce.models.User;
import com.maa.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public User addUser(@RequestBody User user){
        User response = this.userService.insertUser(user);
        return response;
    }

    @GetMapping
    public List<Map<String, Object>> getUsers(){
        return this.userService.getAllUsers();
    }

    //TODO handle this endpoint so that we don't 500 internal server error in response
    @GetMapping("/{id}")
    User getOneUser(@PathVariable int id, HttpServletResponse response) throws NotFoundException
    {
        User u = this.userService.getOneUser(id);

        if (u == null){
            throw new NotFoundException();
        }else {
            return u;
        }

    }

    @ExceptionHandler(NotFoundException.class)
    public ErrorDetails userNotFound(NotFoundException e){
        return e.getErrorDetails("User Not Found Please Enter Valid");
    }

    @PutMapping
    User updateUser(@RequestBody User user){
        return this.userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    User deleteUser(@PathVariable int id){
        return this.userService.deleteUser(id);
    }

}
