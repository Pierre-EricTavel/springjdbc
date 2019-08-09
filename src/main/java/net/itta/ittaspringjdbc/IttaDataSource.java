/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.ittaspringjdbc;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:ittadb.properties")
public class IttaDataSource {
    
    @Autowired
   private Environment env;
    
    public DataSource dataSource(){
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setDriverClassName(env.getProperty("ittadb.driverClassName"));
        dataSource.setUrl(env.getProperty("ittadb.url"));
        dataSource.setUsername(env.getProperty("ittadb.username"));
        dataSource.setPassword(env.getProperty("ittadb.password"));

        return dataSource;
    }
    
    
    
}
