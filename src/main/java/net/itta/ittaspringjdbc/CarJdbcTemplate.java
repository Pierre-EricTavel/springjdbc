/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.ittaspringjdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.itta.ittaspringjdbc.business.Car;

import net.itta.ittaspringjdbc.business.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan("net.itta.ittaspringjdbc")
public class CarJdbcTemplate  implements CarDao{


    NamedParameterJdbcTemplate  jdbcTemplate;
    @Autowired
    PeopleJdbcTemplate peopleJdbcTemplate;
   
    final String tablename="Cars";

    public CarJdbcTemplate(IttaDataSource IttaDataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(IttaDataSource.dataSource());
    }

  
    @Override
    public Collection<Car> findAll() {
        return jdbcTemplate.query("select * from PEOPLE", new CarMapper());
    }

     
    @Override
    public People getOwnerFromSerialno(String serialno) {
         Map param= new HashMap();
         param.put("serialno", serialno);
        int ownerid = jdbcTemplate.queryForObject("select Ownerid from CARS where serialno=:serialno", param, Integer.class);
        return peopleJdbcTemplate.findOne(ownerid);
    }

    @Override
    public int setOwner(String serialno, int ownerid) {
         return jdbcTemplate.getJdbcTemplate().update("update CARS set Ownerid= ? where serialno=?", ownerid,serialno);
    }

    @Override
    public Car findOne(String serialno) {
         Map param= new HashMap();
         param.put("serialno", serialno);
         Car c=null;
        try {
                c= jdbcTemplate.queryForObject("select serialno , modelid, ownerid, color  from CARS where serialno=:serialno", param , new CarMapper());
                if(c!=null)
                    c.setOwner(peopleJdbcTemplate.findOne(c.getOwnerid()));
        } catch (DataAccessException e) {
            System.out.println(e);
        }
        return c;
    }

    @Override
    public int create(Car car) {
        String sql = "insert into cars (serialno , modelid, ownerid) values( :serialno , :modelid, :ownerid)";
      SqlParameterSource parameterSource = new BeanPropertySqlParameterSource( car);
      return jdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public int update(Car car) {
      String sql = "update Cars  set serialno = :serialno , modelid = :modelid, ownerid = :ownerid where serialno=:serialno";
      SqlParameterSource parameterSource = new BeanPropertySqlParameterSource( car);
      return jdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public int deleteById(String serialno) {
    String sql = "delete from cars where serialno=?"; 
    return jdbcTemplate.getJdbcTemplate().update(sql,  serialno);}

    @Override
    public int saveOrUpdate(Car car) {
         int res=0;
       if((res=update(car))==0)
         res=create(car);
       return res;
    }

    List<Car> findAllByOwner(Integer ownerid) {
        return jdbcTemplate.getJdbcTemplate().query("select * from Cars where ownerid=?",new Object[]{ownerid},  new CarMapper());
    }
    
}
