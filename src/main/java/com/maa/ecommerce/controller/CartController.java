package com.maa.ecommerce.controller;


import com.maa.ecommerce.exceptions.ErrorDetails;
import com.maa.ecommerce.exceptions.HandlerException;
import com.maa.ecommerce.models.CartModel;
import com.maa.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping
    CartModel addProductToCart(@RequestBody CartModel cartModel) throws HandlerException {
        CartModel result = this.cartService.addProductToCart(cartModel);
        if (result == null){
            throw new HandlerException(HttpStatus.NOT_ACCEPTABLE.value(), "Not Added!");
        }else{
            return result;
        }
    }

    @GetMapping("/{id}")
    public List<Map<String, Object>> getProductsFromCartByUserId(@PathVariable int id) throws HandlerException {
        List<Map<String, Object>> result = this.cartService.getProductFromCartByUserId(id);
        if (result == null){
            throw new HandlerException(HttpStatus.NOT_FOUND.value(), "Not Found!");
        }else {
            return result;
        }
    }

    @DeleteMapping("/{id}")
    public CartModel removeProductFromCartByCartId(@PathVariable int id) throws HandlerException {
        CartModel result = this.cartService.removeProductFromCartByCartId(id);
        if (result == null){
            throw new HandlerException(HttpStatus.NOT_FOUND.value(), "Please Give Valid Id");
        }else {
            return result;
        }
    }

    @DeleteMapping("usr/{id}")
    public List<Map<String, Object>> removeProdcutFromCartByUserId(@PathVariable int id) throws HandlerException {
        List<Map<String, Object>> result = this.cartService.removeProductFromCartByUserId(id);
        if (result == null){
            throw new HandlerException(HttpStatus.NOT_FOUND.value(), "Not Deleted!");
        }else {
            return result;
        }
    }

    @PutMapping()
    public CartModel updateProductQty(@RequestBody CartModel model) throws HandlerException {
        CartModel result = this.cartService.updateProductQty(model);
        if (result == null){
            throw new HandlerException(HttpStatus.NOT_ACCEPTABLE.value(), "Not Updated!");
        }else {
            return result;
        }
    }



    @ExceptionHandler(HandlerException.class)
    public ErrorDetails handleException(HandlerException e){
        return e.getErrorDetails();
    }

}
