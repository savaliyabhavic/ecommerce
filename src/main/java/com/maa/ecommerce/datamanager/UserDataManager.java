package com.maa.ecommerce.datamanager;


import com.maa.ecommerce.models.User;
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

    public User insertUser(User user)
    {

        //TODo update code in a way that end point recieves proper id in the value
        String query = "Insert into user values(NULL, ?, ?, ?, ?, ?)";

        try
        {

            try (Connection conn = this.jdbcTemplate.getDataSource().getConnection())
            {
                try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
                {
                    statement.setString(1, user.getName());
                    statement.setString(2, user.getEmail());
                    statement.setString(3, user.getUsername());
                    statement.setString(4, user.getPassword());
                    statement.setInt(5, user.getRoleid());

                    statement.executeUpdate();
                    try (ResultSet generatedKeys = statement.getGeneratedKeys())
                    {
                        if (generatedKeys.next())
                        {
                            int id = generatedKeys.getInt(1);
                            user.setId(id);
                        }
                    }
                }
            }

        /*int update = this.jdbcTemplate.update(query,preparedStatement-> {
           preparedStatement.setString(1,user.getName());
           preparedStatement.setString(2,user.getEmail());
           preparedStatement.setString(3,user.getUsername());
           preparedStatement.setString(4,user.getPassword());
           preparedStatement.setInt(5,user.getRoleid());
        });

        int userId = getIdOfLastAddedRecord(user);

        if (userId == -1){
            // Throw and Show the Error
        }
        */
            return user;

        } catch (EmptyResultDataAccessException | SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public int getIdOfLastAddedRecord(User user)
    {
        String query = "SELECT id from user where username = ?";
        int id = -1;
        id = this.jdbcTemplate.queryForObject(query, new Object[]{user.getUsername()}, (resultSet, i) ->
        {
            return resultSet.getInt("id");
        });
        return id;
    }


    public List<Map<String, Object>> getAllUser()
    {
        String query = "SELECT id, name, email, username, password, roleid FROM user";
        List<Map<String, Object>> response = jdbcTemplate.queryForList(query);
        return response;
    }


    public User getOneUser(int id)
    {
        String query = "SELECT id, name, email, username, password, roleid FROM user WHERE id = ?";

        try
        {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, ((resultSet, i) ->
            {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleid(resultSet.getInt("roleid"));

                return user;
            }));
        } catch (EmptyResultDataAccessException e)
        {
            return null;
        }

    }

    public User updateUser(User user)
    {
        String query = "UPDATE user SET name = ? , email = ?, username = ?, password = ?, roleid = ? WHERE id = ?";

        int update = this.jdbcTemplate.update(query, preparedStatement ->
        {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getRoleid());
            preparedStatement.setInt(6, user.getId());

        });

        return update == 1 ? user : null;

    }

    public User deleteUser(int id)
    {
        User user = getOneUser(id);

        String query = "DELETE FROM user WHERE id = ?";

        int update = this.jdbcTemplate.update(query, preparedStatement ->
        {
            preparedStatement.setInt(1, id);
        });

        return update == 1 ? user : null;
    }
}
