package com.maa.ecommerce.controller;


import com.maa.ecommerce.exceptions.ErrorDetails;
import com.maa.ecommerce.exceptions.HandlerException;
import com.maa.ecommerce.models.UserModel;
import com.maa.ecommerce.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    UserController(){
        logger.info("Bhavic class to string method is;----- ");
    }

    @PostMapping
    public UserModel addUser(@RequestBody UserModel userModel){
        return this.userService.insertUser(userModel);
    }

    @GetMapping
    public List<Map<String, Object>> getUsers(){
        return this.userService.getAllUsers();
    }

    @GetMapping("/{id}")
    UserModel getOneUser(@PathVariable int id, HttpServletResponse response) throws HandlerException
    {
        UserModel u = this.userService.getOneUser(id);

        if (u == null){
            throw new HandlerException(HttpStatus.NOT_FOUND.value(), "UserModel Not Found Please Enter Valid");
        }else {
            return u;
        }

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HandlerException.class)
    public ErrorDetails userNotFound(HandlerException e){
        return e.getErrorDetails();
    }

    @PutMapping
    UserModel updateUser(@RequestBody UserModel userModel){
        return this.userService.updateUser(userModel);
    }

    @DeleteMapping("/{id}")
    UserModel deleteUser(@PathVariable int id){
        return this.userService.deleteUser(id);
    }

}
