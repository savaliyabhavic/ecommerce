package com.maa.ecommerce.datamanager;


import com.maa.ecommerce.models.CartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Map;

@Service
public class CartDataManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public CartModel addProductToCart(CartModel cartModel) {

        String query = "INSERT INTO cart_details VALUES(NULL, ?, ?, ?)";
        try(Connection connection = this.jdbcTemplate.getDataSource().getConnection();
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ){
            statement.setInt(1, cartModel.getUser_id());
            statement.setInt(2, cartModel.getProduct_id());
            statement.setInt(3, cartModel.getProduct_qty());

            statement.executeUpdate();

            try(ResultSet keys = statement.getGeneratedKeys()){

                if (keys.next()){
                    cartModel.setId(keys.getInt(1));
                    return cartModel;
                }else {
                    return null;
                }
            }

        }catch (SQLException e){
            return null;
        }
    }

    public List<Map<String, Object>> getProductFromCartByUserId(int id) {
        String query = "SELECT id, user_id, product_id, product_qty FROM cart_details WHERE user_id = ?";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(query, id);

        if (list.isEmpty()){
            return null;
        }else {
            return list;
        }
    }

    public CartModel getSingleProductFromCart(int cartId){
        String query = "SELECT id, user_id, product_id, product_qty FROM cart_details WHERE id = ?";

        CartModel result = this.jdbcTemplate.queryForObject(query, new Object[]{cartId}, ((resultSet, i) -> {
            CartModel res = new CartModel();
            res.setId(resultSet.getInt("id"));
            res.setUser_id(resultSet.getInt("user_id"));
            res.setProduct_id(resultSet.getInt("product_id"));
            res.setProduct_qty(resultSet.getInt("product_qty"));
            return res;
        }));

        return result;

    }

    public CartModel removeProductFromCartByCartId(int id) {

        CartModel res = getSingleProductFromCart(id);

        if (res == null){
            return null;
        }

        String query = "DELETE FROM cart_details WHERE id = ?";

        int update = this.jdbcTemplate.update(query, preparedStatement -> {
            preparedStatement.setInt(1, id);
        });

        if (update == 1){
            return res;
        }else {
            return null;
        }

    }

    public List<Map<String, Object>> removeProductFromCartByUserId(int id) {

        List<Map<String, Object>> res = getProductFromCartByUserId(id);

        if (res == null){
            return null;
        }

        String query = "DELETE FROM cart_details where user_id = ?";

        int update = this.jdbcTemplate.update(query, preparedStatement -> {
            preparedStatement.setInt(1, id);
        });

        if (1 <= update){
            return res;
        }else {
            return null;
        }

    }

    public CartModel updateProductQty(CartModel model) {

        String query = "UPDATE cart_details SET product_qty = ? WHERE id = ?";
        int update = this.jdbcTemplate.update(query, (preparedStatement -> {
            preparedStatement.setInt(1, model.getProduct_qty());
            preparedStatement.setInt(2, model.getId());
        }));

        if (update == 1){
            return model;
        }else {
            return null;
        }
    }
}
