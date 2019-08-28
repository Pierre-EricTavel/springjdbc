/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.ittaspringjdbc;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan("net.itta.ittaspringjdbc")
@EnableTransactionManagement(proxyTargetClass = true,mode = AdviceMode.PROXY)
public class IttaJdbcConf {
    
//    @Bean
//    public IttaDataSource ittaDataSource(){
//        return new IttaDataSource();
//    }
    
    
    @Bean
    public IttaMariaDataSource ittaDataSource(){
        System.out.println("---------------creating ittaDataSource---------------------------");
        return new IttaMariaDataSource();
    }
    
    
    @Bean
    public PeopleJdbcTemplate peopleJdbcTemplate(){
        final NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ittaDataSource().getDataSource());
        PeopleJdbcTemplate pjt = new PeopleJdbcTemplate(namedParameterJdbcTemplate);
               
        return pjt;
    }
    @Bean
     public CarJdbcTemplate carJdbcTemplate(){
        final NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ittaDataSource().getDataSource());
        CarJdbcTemplate cjt = new CarJdbcTemplate(namedParameterJdbcTemplate);
        return cjt;
    }
     

  @Bean()
  public DataSourceTransactionManager transactionManager() {
      DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
      transactionManager.setDataSource(ittaDataSource().getDataSource());
      return transactionManager;
  }
   
  
  
}

