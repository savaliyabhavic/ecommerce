package com.maa.ecommerce.services;


import com.maa.ecommerce.datamanager.CategoryDataManager;
import com.maa.ecommerce.models.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    @Autowired
    CategoryDataManager categoryDataManager;

    public List<Map<String, Object>> getAllCategory() {
        return this.categoryDataManager.getAllCategory();
    }

    public CategoryModel getCategory(int id)
    {
        return this.categoryDataManager.getCategory(id);
    }

    public CategoryModel addCategory(CategoryModel model)
    {
        return this.categoryDataManager.addCategory(model);
    }

    public CategoryModel updateCategory(CategoryModel model)
    {
        return this.categoryDataManager.updateCategory(model);
    }

    public CategoryModel removeCategory(int id)
    {
        return this.categoryDataManager.removeCategory(id);
    }

    public List<Map<String, Object>> getProductsByCategory(int id)
    {
        return this.categoryDataManager.getProductsByCategory(id);
    }
}
