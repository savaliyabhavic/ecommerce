package com.maa.ecommerce.services;

import com.maa.ecommerce.datamanager.CartDataManager;
import com.maa.ecommerce.models.CartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CartService {

    @Autowired
    CartDataManager dataManager;

    public CartModel addProductToCart(CartModel cartModel) {
        return this.dataManager.addProductToCart(cartModel);
    }

    public List<Map<String, Object>> getProductFromCartByUserId(int id) {
        return this.dataManager.getProductFromCartByUserId(id);
    }

    public CartModel removeProductFromCartByCartId(int id) {
        return this.dataManager.removeProductFromCartByCartId(id);
    }

    public List<Map<String, Object>> removeProductFromCartByUserId(int id) {
        return this.dataManager.removeProductFromCartByUserId(id);
    }

    public CartModel updateProductQty(CartModel model) {
        return this.dataManager.updateProductQty(model);
    }
}
