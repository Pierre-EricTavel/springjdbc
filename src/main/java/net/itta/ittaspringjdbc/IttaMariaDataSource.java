/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.ittaspringjdbc;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:ittadb_maria.properties")
public class IttaMariaDataSource {
    
    @Autowired
    private Environment env;
    
  
    SingleConnectionDataSource dataSource;
    
    public DataSource getDataSource(){

        if(dataSource==null){
            dataSource = new SingleConnectionDataSource();
            dataSource.setDriverClassName(env.getProperty("ittamariadb.driverClassName"));
            dataSource.setUrl(env.getProperty("ittamariadb.url"));
            dataSource.setUsername(env.getProperty("ittamariadb.username"));
            dataSource.setPassword(env.getProperty("ittamariadb.password"));
            dataSource.setAutoCommit(true);
        }
        return dataSource;
    }

    public IttaMariaDataSource() {
    }

}