package net.itta.ittaspringjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.itta.ittaspringjdbc.business.Car;
import org.springframework.jdbc.core.RowMapper;


public class CarMapper implements RowMapper<Car>{

    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        Car c = new Car(rs.getString("serialno"), rs.getInt("modelid"),rs.getString("color"), rs.getInt("ownerid"));
        return c;
        
    }
    
}
