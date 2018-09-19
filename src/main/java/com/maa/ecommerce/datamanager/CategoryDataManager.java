package com.maa.ecommerce.datamanager;

import com.maa.ecommerce.models.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Map;

@Service
public class CategoryDataManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllCategory()
    {
        String query = "SELECT id, name FROM category";
        List<Map<String, Object>> list;
        list = this.jdbcTemplate.queryForList(query);
        return list;

    }

    public CategoryModel getCategory(int id)
    {
        String query = "SELECT id, name FROM category WHERE id = ?";
        try
        {
            return this.jdbcTemplate.queryForObject(query, new Object[]{id},((rs, rowNum) -> {
                CategoryModel model = new CategoryModel();
                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                return model;
            }));
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public CategoryModel addCategory(CategoryModel model)
    {
        String query = "INSERT INTO category VALUES(Null, ?)";
        try (Connection con = this.jdbcTemplate.getDataSource().getConnection();
             PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1,model.getName());
            int update = statement.executeUpdate();

            if (update == 1){
                try (ResultSet keys = statement.getGeneratedKeys())
                {
                    if (keys.next())
                    {
                        int id = keys.getInt(1);
                        model.setId(id);

                        return model;
                    }else {
                        return null;
                    }
                }
            }else {
                return null;
            }

        }catch (SQLException e){
            return null;
        }
    }

    public CategoryModel updateCategory(CategoryModel model)
    {
        String query = "UPDATE category SET name = ? WHERE id = ?";

        int update = this.jdbcTemplate.update(query, (ps -> {
            ps.setString(1, model.getName());
            ps.setInt(2, model.getId());
        }));

        if (update == 1){
            return model;
        }else {
            return null;
        }
    }

    public CategoryModel removeCategory(int id)
    {
        String query ="DELETE FROM category WHERE id = ? ";
        CategoryModel res = getCategory(id);

        if (res == null){
            return null;
        }

        int update = this.jdbcTemplate.update(query, (ps -> ps.setInt(1,id)));

        if (update == 1){
            return res;
        }else {
            return null;
        }
    }

    public List<Map<String, Object>> getProductsByCategory(int id)
    {
        String query = "SELECT \n" +
                            "prod.id as pid, \n" +
                            "prod.name as pname, \n" +
                            "prod.descrption as pdesc, \n" +
                            "prod.qty as pqty, \n" +
                            "prod.price as pprice, \n" +
                            "prod.category as pcat,\n" +
                            "\n" +
                            "cat.id as catid,\n" +
                            "cat.name as cname\n" +
                            "\n" +
                        "FROM category AS cat INNER JOIN product AS prod ON prod.category = ? AND cat.id = ?";

        return this.jdbcTemplate.queryForList(query, id, id);


    }
}
