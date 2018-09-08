package com.maa.ecommerce.controller;


import com.maa.ecommerce.exceptions.ErrorDetails;
import com.maa.ecommerce.exceptions.HandlerException;
import com.maa.ecommerce.models.ProductModel;
import com.maa.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping
    public List<Map<String, Object>> getAllProducts () throws HandlerException
    {

        List<Map<String, Object>> res = this.productService.getAllproducts();

        if (res.isEmpty()){
            throw new HandlerException(HttpStatus.NOT_FOUND.value(), "Products Not Available!");
        }else {
            return res;
        }
    }

    @GetMapping("/{id}")
    public ProductModel getProduct(@PathVariable int id) throws HandlerException
    {
        ProductModel res = this.productService.getProduct(id);

        if (res == null){
            throw new HandlerException(HttpStatus.NOT_FOUND.value(), "Not Found!");
        }else {
            return res;
        }
    }


    @PostMapping
    public ProductModel addProduct(@RequestBody ProductModel model) throws HandlerException
    {
        ProductModel res = this.productService.addProduct(model);

        if (res == null){
            throw new HandlerException(HttpStatus.NOT_ACCEPTABLE.value(), "Not Added!");
        }else {
            return res;
        }
    }


    @PutMapping
    public ProductModel updateProduct(@RequestBody ProductModel model) throws HandlerException
    {
        ProductModel res = this.productService.updateProduct(model);
        if (res == null){
            throw  new HandlerException(HttpStatus.NOT_ACCEPTABLE.value(), "Not Updated!");
        }else {
            return res;
        }
    }

    @DeleteMapping("/{id}")
    public ProductModel removeProduct(@PathVariable int id) throws HandlerException
    {
        ProductModel res = this.productService.removeProduct(id);

        if (res == null){
            throw new HandlerException(HttpStatus.NOT_FOUND.value(), "Not Removed!");
        }else {
            return res;
        }
    }


    @ExceptionHandler(HandlerException.class)
    public ErrorDetails handleException(HandlerException e) {
        return e.getErrorDetails();
    }
}
