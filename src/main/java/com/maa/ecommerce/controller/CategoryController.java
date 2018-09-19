package com.maa.ecommerce.controller;


import com.maa.ecommerce.exceptions.ErrorDetails;
import com.maa.ecommerce.exceptions.HandlerException;
import com.maa.ecommerce.models.CategoryModel;
import com.maa.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Map<String, Object>> getAllCategory() throws HandlerException
    {

        List<Map<String, Object>> list = this.categoryService.getAllCategory();
        if (list == null){
            throw new HandlerException(HttpStatus.NOT_FOUND.value(), "There is No Category Please Add First!");
        }else {
            return list;
        }
    }

    @GetMapping("/{id}")
    public CategoryModel getCategory(@PathVariable int id) throws HandlerException
    {
        CategoryModel res  =  this.categoryService.getCategory(id);

        if (res == null){
            throw new HandlerException(HttpStatus.NOT_FOUND.value(), "Please Enter Found!");
        }else {
            return res;
        }
    }

    @GetMapping("/{id}/products")
    public List<Map<String, Object>> getProductsByCategory(@PathVariable int id) throws HandlerException
    {
        List<Map<String, Object>> resMaps = this.categoryService.getProductsByCategory(id);
        if (resMaps == null){
            throw new HandlerException(HttpStatus.NOT_FOUND.value(), "Result Not Found!");
        }

        return resMaps;
    }

    @PostMapping
    public CategoryModel addCategory(@RequestBody CategoryModel model) throws HandlerException
    {
        CategoryModel res = this.categoryService.addCategory(model);

        if (res == null){
            throw new HandlerException(HttpStatus.NOT_ACCEPTABLE.value(), "Not Added");
        }else {
            return res;
        }

    }

    @PutMapping
    public CategoryModel updateCategory(@RequestBody CategoryModel model) throws HandlerException
    {
        CategoryModel res =  this.categoryService.updateCategory(model);

        if (res == null){
            throw new HandlerException(HttpStatus.NOT_ACCEPTABLE.value(), "Not Updated!");
        }else {
            return res;
        }
    }

    @DeleteMapping("/{id}")
    public CategoryModel removeCategory(@PathVariable int id) throws HandlerException
    {
        CategoryModel res =  this.categoryService.removeCategory(id);

        if (res == null){
            throw new HandlerException(HttpStatus.NOT_ACCEPTABLE.value(), "Not Removed!");
        }else {
            return res;
        }
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HandlerException.class)
    public ErrorDetails showErrorForCatergory(HandlerException e){
        return e.getErrorDetails();
    }


}
