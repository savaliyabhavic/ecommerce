package com.maa.ecommerce.datamanager;


import com.maa.ecommerce.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Map;

@Service
public class UserDataManager
{

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserModel insertUser(UserModel userModel)
    {
        String query = "Insert into user values(NULL, ?, ?, ?, ?, ?)";

        try
        {

            try (Connection conn = this.jdbcTemplate.getDataSource().getConnection())
            {
                try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
                {
                    statement.setString(1, userModel.getName());
                    statement.setString(2, userModel.getEmail());
                    statement.setString(3, userModel.getUsername());
                    statement.setString(4, userModel.getPassword());
                    statement.setInt(5, userModel.getRoleid());

                    statement.executeUpdate();
                    try (ResultSet generatedKeys = statement.getGeneratedKeys())
                    {
                        if (generatedKeys.next())
                        {
                            int id = generatedKeys.getInt(1);
                            userModel.setId(id);
                        }
                    }
                }
            }

            return userModel;

        } catch (EmptyResultDataAccessException | SQLException e)
        {
            return null;
        }
    }

    public int getIdOfLastAddedRecord(UserModel userModel)
    {
        String query = "SELECT id from user where username = ?";
        int id = -1;
        id = this.jdbcTemplate.queryForObject(query, new Object[]{userModel.getUsername()},
                (resultSet, i) -> resultSet.getInt("id"));
        return id;
    }


    public List<Map<String, Object>> getAllUser()
    {
        String query = "SELECT id, name, email, username, password, roleid FROM user";
        List<Map<String, Object>> response;
        response = jdbcTemplate.queryForList(query);
        return response;
    }


    public UserModel getOneUser(int id)
    {
        String query = "SELECT id, name, email, username, password, roleid FROM user WHERE id = ?";

        try
        {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, ((resultSet, i) ->
            {
                UserModel userModel = new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setName(resultSet.getString("name"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setUsername(resultSet.getString("username"));
                userModel.setPassword(resultSet.getString("password"));
                userModel.setRoleid(resultSet.getInt("roleid"));

                return userModel;
            }));
        } catch (EmptyResultDataAccessException e)
        {
            return null;
        }

    }

    public UserModel updateUser(UserModel userModel)
    {
        String query = "UPDATE user SET name = ? , email = ?, username = ?, password = ?, roleid = ? WHERE id = ?";

        int update = this.jdbcTemplate.update(query, preparedStatement ->
        {

            preparedStatement.setString(1, userModel.getName());
            preparedStatement.setString(2, userModel.getEmail());
            preparedStatement.setString(3, userModel.getUsername());
            preparedStatement.setString(4, userModel.getPassword());
            preparedStatement.setInt(5, userModel.getRoleid());
            preparedStatement.setInt(6, userModel.getId());

        });

        return update == 1 ? userModel : null;

    }

    public UserModel deleteUser(int id)
    {
        UserModel userModel = getOneUser(id);

        String query = "DELETE FROM user WHERE id = ?";

        int update = this.jdbcTemplate.update(query,
                preparedStatement -> preparedStatement.setInt(1, id));

        return update == 1 ? userModel : null;
    }
}
