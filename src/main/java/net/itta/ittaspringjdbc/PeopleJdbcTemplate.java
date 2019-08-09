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
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan("net.itta.ittaspringjdbc")
public class PeopleJdbcTemplate  implements PeopleDao{


    NamedParameterJdbcTemplate  jdbcTemplate;
    
   @Autowired
    CarJdbcTemplate carJdbcTemplate;
   
    final String tablename="PEOPLE";

    public PeopleJdbcTemplate(IttaDataSource IttaDataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(IttaDataSource.dataSource());
    }

     @Override
    public People findOne(Integer id) {
         Map param= new HashMap();param.put("id", id);
         People p = null;
         try {
              p=jdbcTemplate.queryForObject("select id,name,birthdate from PEOPLE where id=:id", param , new PeopleMapper());
         } catch (Exception e) {
             System.out.println(e);
         }
       return p;
    }
    @Override
    public Collection<People> findAll() {
        return jdbcTemplate.query("select id, name, birthdate from PEOPLE", new PeopleMapper());
    }

    @Override
    public int create(People people) {
       String sql = "insert into PEOPLE (id,name,birthdate) values(:id, :name, :birthDate)";
      SqlParameterSource parameterSource = new BeanPropertySqlParameterSource( people);
      return jdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public int update(People people) {
      String sql = "update PEOPLE  set name= :name , birthdate=:birthDate where id=:id";
      SqlParameterSource parameterSource = new BeanPropertySqlParameterSource( people);
      return jdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public int deleteById(Integer id) {
       String sql = "delete from PEOPLE where id=?"; 
       return jdbcTemplate.getJdbcTemplate().update(sql,  id);
    }

    @Override
    public int saveOrUpdate(People people) {
        int res=0;
       if((res=update(people))==0)
         res=create(people);
       return res;
    }
    
    public int addCarToWner(Car c, Integer ownerid) {
        c.setOwnerid(ownerid);
        return carJdbcTemplate.saveOrUpdate(c);
    }

    public int removeCarbySerialNoFromOwner(String serialno) {
        String sql = "update Cars  set ownerid = NULL where serialno=?";
      return jdbcTemplate.getJdbcTemplate().update(sql, serialno);
    }
    public int changeCarOwnerbySerialNo(String serialno, Integer ownerid) {
        String sql = "update Cars  set ownerid = ownerid where serialno=?";
      return jdbcTemplate.getJdbcTemplate().update(sql, ownerid, serialno);
    }
   
    public List<Car> getCarsFormOwner(int ownerid) {
        return carJdbcTemplate.findAllByOwner(ownerid);
    }
    
    

}
;