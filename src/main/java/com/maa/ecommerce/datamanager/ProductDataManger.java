package com.maa.ecommerce.datamanager;

import com.maa.ecommerce.models.ProductModelForUpIn;
import com.maa.ecommerce.models.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Map;


@Service
public class ProductDataManger
{
    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> getAllProducts()
    {
        String query = "SELECT prod.id as id, " +
                        "prod.name as name, " +
                        "prod.descrption as descrption, " +
                        "prod.qty as qty, " +
                        "prod.price as price, " +
                        "prod.category as category, " +
                        "cat.id as cid, " +
                        "cat.name as cname " +
                        "FROM product as prod INNER JOIN category as cat WHERE prod.category = cat.id";

        List<Map<String, Object>> maps;
        maps = this.jdbcTemplate.queryForList(query);

        return maps;

    }

    public ProductModelForUpIn addProduct(ProductModelForUpIn model)
    {
        String query = "INSERT INTO product values(null, ?, ?, ?, ?, ?)";
        try(Connection con = this.jdbcTemplate.getDataSource().getConnection();
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ){
            statement.setString(1, model.getName());
            statement.setString(2,model.getDescrption());
            statement.setInt(3,model.getQty());
            statement.setInt(4, model.getPrice());
            statement.setInt(5,model.getCategory());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys();)
            {
                if (generatedKeys.next())
                {
                    int id = generatedKeys.getInt(1);
                    model.setId(id);

                    return model;
                }else {
                    return null;
                }
            }

        } catch (SQLException e)
        {
            return null;
        }

    }

    public ProductModel getProdduct(int id)
    {
        String query = "SELECT prod.id as id, " +
                        "prod.name as name, " +
                        "prod.descrption as descrption, " +
                        "prod.qty as qty, " +
                        "prod.price as price, " +
                        "prod.category as category, " +
                        "cat.id as cid, " +
                        "cat.name as cname " +
                        "FROM product AS prod INNER JOIN category AS cat WHERE prod.id = ? AND cat.id = prod.category";

        try
        {
            return this.jdbcTemplate.queryForObject(query, new Object[] {id}, ((rs, rowNum) -> {
                ProductModel res = new ProductModel();
                res.setId(rs.getInt("id"));
                res.setName(rs.getString("name"));
                res.setDescrption(rs.getString("descrption"));
                res.setQty(rs.getInt("qty"));
                res.setPrice(rs.getInt("price"));
                res.setCategory(rs.getInt("category"));
                res.setCid(rs.getInt("cid"));
                res.setCname(rs.getString("cname"));
                return res;
            }));
        }catch (EmptyResultDataAccessException e){

            return null;
        }
    }

    public ProductModelForUpIn updateProduct(ProductModelForUpIn model)
    {
        String query = "Update product set name = ? , descrption = ? ,qty = ? ,price = ? ,category = ? Where id = ?";

        int update = this.jdbcTemplate.update(query, ps -> {
            ps.setString(1,model.getName());
            ps.setString(2, model.getDescrption());
            ps.setInt(3, model.getQty());
            ps.setInt(4, model.getPrice());
            ps.setInt(5, model.getCategory());
            ps.setInt(6, model.getId());
        });

        if (update == 1){
            return model;
        }else {
            return null;
        }
    }

    public ProductModel removeProduct(int id)
    {
        ProductModel res = getProdduct(id);

        if (res == null){
            return null;
        }

        String query = "DELETE FROM product WHERE id = ?";

        int update =  this.jdbcTemplate.update(query, ps -> ps.setInt(1, id));

        if (update == 1){
            return res;
        }else {
            return null;
        }
    }
}
