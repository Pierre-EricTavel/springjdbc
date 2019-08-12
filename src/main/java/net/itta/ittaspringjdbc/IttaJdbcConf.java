/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.ittaspringjdbc;

import javax.sql.DataSource;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@ComponentScan("net.itta.ittaspringjdbc")
@EnableTransactionManagement(proxyTargetClass = true,mode = AdviceMode.PROXY)
public class IttaJdbcConf {//implements TransactionManagementConfigurer{
    
    @Bean
    public IttaDataSource ittaDataSource(){
        return new IttaDataSource();
    }
    
    @Bean
    public PeopleJdbcTemplate peopleJdbcTemplate(){
        final NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ittaDataSource().dataSource());
        PeopleJdbcTemplate pjt = new PeopleJdbcTemplate(namedParameterJdbcTemplate);
               
        return pjt;
    }
    @Bean
     public CarJdbcTemplate carJdbcTemplate(){
        final NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ittaDataSource().dataSource());
        CarJdbcTemplate cjt = new CarJdbcTemplate(namedParameterJdbcTemplate);
        return cjt;
    }
     
//     @Bean
//    public DataSourceTransactionManager txManager(){
//      DataSourceTransactionManager dstm=  new DataSourceTransactionManager(ittaDataSource().dataSource());
//      dstm.setRollbackOnCommitFailure(true);
//      return dstm;
//    }
//
//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        return txManager();
//    }
  @Bean
  public PlatformTransactionManager transactionManager() {
      DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
      transactionManager.setDataSource(ittaDataSource().dataSource());
      return transactionManager;
  }
   
}
