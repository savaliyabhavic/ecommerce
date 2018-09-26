package com.maa.ecommerce.services;


import com.maa.ecommerce.datamanager.ProductDataManger;
import com.maa.ecommerce.models.ProductModelForUpIn;
import com.maa.ecommerce.models.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    ProductDataManger productDataManger;

    public List<Map<String, Object>> getAllproducts()
    {
        return this.productDataManger.getAllProducts();
    }

    public ProductModel getProduct(int id)
    {
        return this.productDataManger.getProdduct(id);
    }


    public ProductModelForUpIn addProduct(ProductModelForUpIn model)
    {
        return this.productDataManger.addProduct(model);
    }


    public ProductModelForUpIn updateProduct(ProductModelForUpIn model)
    {
        return this.productDataManger.updateProduct(model);
    }

    public ProductModel removeProduct(int id)
    {
        return this.productDataManger.removeProduct(id);
    }

    public List<Map<String, Object>> searchProduct(String name) {
        return this.productDataManger.searchProduct(name);
    }
}
