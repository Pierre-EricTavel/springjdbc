/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.ittaspringjdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("net.itta.ittaspringjdbc")
public class IttaJdbcConf {
    

    @Bean(name = "IttaDataSource")
    public IttaDataSource IttaDataSource(){
        return new IttaDataSource();
    }
    
    @Bean(name = "peopleJdbcTemplate")
    public PeopleJdbcTemplate peopleJdbcTemplate(){
        return new PeopleJdbcTemplate( IttaDataSource());
    }
   @Bean(name = "carJdbcTemplate")
    public CarJdbcTemplate carJdbcTemplate(){
        return new CarJdbcTemplate( IttaDataSource());
    }
  
}
