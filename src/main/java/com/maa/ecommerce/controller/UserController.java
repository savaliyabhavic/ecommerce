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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public UserModel addUser(@RequestBody UserModel userModel)
            throws HandlerException {
        UserModel result = this.userService.insertUser(userModel);
        if (result == null){
            throw new HandlerException(HttpStatus.NOT_ACCEPTABLE.value(),
                    "Not Added!");
        }else {
            return result;
        }
    }

    @GetMapping
    public List<Map<String, Object>> getUsers()
            throws HandlerException {

        List<Map<String, Object>> results = this.userService.getAllUsers();
        if (results.isEmpty()){
            throw new HandlerException(HttpStatus.NOT_FOUND.value(),
                    "Not Found!");
        }else {
            return results;
        }

    }

    @GetMapping("/{id}")
    UserModel getOneUser(@PathVariable int id, HttpServletResponse response)
            throws HandlerException {
        UserModel u = this.userService.getOneUser(id);

        if (u == null){
            throw new HandlerException(HttpStatus.NOT_FOUND.value(),
                    "UserModel Not Found Please Enter Valid");
        }else {
            return u;
        }

    }

    @PutMapping
    UserModel updateUser(@RequestBody UserModel userModel)
            throws HandlerException {

        UserModel result = this.userService.updateUser(userModel);

        if (result == null){
            throw new HandlerException(HttpStatus.NOT_ACCEPTABLE.value(),
                    "Please Enter Valid Data");
        }else {
            return result;
        }
    }

    @DeleteMapping("/{id}")
    UserModel deleteUser(@PathVariable int id) throws HandlerException {
        UserModel res = this.userService.deleteUser(id);

        if (res == null)
            throw new HandlerException(HttpStatus.NOT_ACCEPTABLE.value(), "Not Deleted");
        else
            return res;

    }

    @ExceptionHandler(HandlerException.class)
    public ErrorDetails userNotFound(HandlerException e){
        return e.getErrorDetails();
    }

}
