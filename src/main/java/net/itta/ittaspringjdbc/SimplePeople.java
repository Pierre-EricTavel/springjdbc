/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.ittaspringjdbc;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.itta.ittaspringjdbc.business.People;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 *
 * @author Administrator
 */
public class SimplePeople {

    public static void main(String[] args) {

        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        dataSource.setUrl("jdbc:derby://localhost:1527/ITTA");
        dataSource.setUsername("administrator");
        dataSource.setPassword("Pa$$w0rd");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            //jdbcTemplate.execute("drop table toto");
            //jdbcTemplate.execute("create table toto (id int, nom varchar(20) )");
            jdbcTemplate.execute("insert into PEOPLE (id,name,birthdate) values(1,'dupont', '1969-06-15')");
            jdbcTemplate.execute("insert into PEOPLE (id,name,birthdate) values(2,'dupond', '1969-06-15')");

            int compte = jdbcTemplate.queryForObject("select count(*) from PEOPLE", Integer.class);
            System.out.println("compte = " + compte);

            compte = jdbcTemplate.update("update PEOPLE set name = ?, birthdate =? where id=?", "durand", "1970-05-06", 1);
            System.out.println("MAJ = " + compte);

            ArrayList<Object[]> alo = new ArrayList<>();
            alo.add(new Object[]{"Schmidt", "1978-05-06", 1});
            alo.add(new Object[]{"Popov", "1977-05-06", 2});

            int[] ti = jdbcTemplate.batchUpdate("update PEOPLE set name = ?, birthdate =? where id=?", alo);
            System.out.println(Arrays.toString(ti));

            Map<String, Object> ligne = jdbcTemplate.queryForMap("select id,name,birthdate from PEOPLE where id=?", 1);
            ligne.forEach((k, v) -> System.out.println(k + " = " + v + " " + v.getClass()));

            SqlRowSet srs = jdbcTemplate.queryForRowSet("select id,name,birthdate from PEOPLE where id=?", 1);
            System.out.println("queryForRowSet");
            while (srs.next()) {
                System.out.println("id=" + srs.getInt("ID") + ", name=" + srs.getString("Name") + ", name=" + srs.getDate("birthdate"));
            }
            System.out.println("queryForList");
            System.out.println(jdbcTemplate.queryForList("select id,name,birthdate from PEOPLE"));

            People p = jdbcTemplate.queryForObject("select id,name,birthdate from PEOPLE where id=1", new PeopleMapper());
            System.out.println(p);

            NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(dataSource);
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("nom", "Tarzan");
            params.addValue("anniversaire", "1890-01-05");
            params.addValue("id", 2);
            compte = namedTemplate.update("update PEOPLE set name = :nom, birthdate = :anniversaire where id= :id", params);

            SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("PEOPLE");
            Map<String, Object> tobeinsert = new HashMap<String, Object>();
            tobeinsert.put("name", "Jane");
            tobeinsert.put("birthdate", "1895-01-11");
            tobeinsert.put("id", 3);
            insert.execute(tobeinsert);

            System.out.println(namedTemplate.query("select id,name,birthdate from PEOPLE", new PeopleMapper()));
            System.out.println("MAJ = " + compte);
        } finally {
            jdbcTemplate.execute("delete from people");
            dataSource.destroy();
        }

    }

}
