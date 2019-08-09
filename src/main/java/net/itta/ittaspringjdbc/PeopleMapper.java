package net.itta.ittaspringjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.itta.ittaspringjdbc.business.People;
import org.springframework.jdbc.core.RowMapper;


public class PeopleMapper implements RowMapper<People>{

    @Override
    public People mapRow(ResultSet rs, int rowNum) throws SQLException {
        People p = new People(rs.getInt("id"),rs.getString("name"), rs.getDate("birthdate"));
        return p;
        
    }
    
}
