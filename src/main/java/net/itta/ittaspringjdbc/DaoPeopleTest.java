package net.itta.ittaspringjdbc;

import java.util.Date;
import java.util.List;
import net.itta.ittaspringjdbc.business.Car;
import net.itta.ittaspringjdbc.business.People;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("net.itta.ittaspringjdbc")
public class DaoPeopleTest {

    public static void main(String[] args) {
         testPeople();
                 }

    static void testCar()  {
       ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(IttaJdbcConf.class);
        PeopleJdbcTemplate peopleJdbcTemplate = context.getBean(PeopleJdbcTemplate.class);
        CarJdbcTemplate CarJdbcTemplate = context.getBean(CarJdbcTemplate.class);
        if (peopleJdbcTemplate.findOne(1) == null) {
            peopleJdbcTemplate.create(new People(1, "aaaaaaaaaa", new Date()));
        } 
        if (CarJdbcTemplate.findOne("GE12345") == null) {
            CarJdbcTemplate.create(new Car("GE12345", 1, "bleu", 1));
        }
        Car pp = CarJdbcTemplate.findOne("GE12345");
        System.out.println("found " + pp);
        pp.setColor("green");
        System.out.println("nb updaté = " + CarJdbcTemplate.update(pp));

        System.out.println("nb supprimé =" + CarJdbcTemplate.deleteById(pp.getSerialno()));
        System.out.println("nb supprimé =" + peopleJdbcTemplate.deleteById(1));
    }

    static void testPeople() { 
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(IttaJdbcConf.class);
        PeopleJdbcTemplate peopleJdbcTemplate = context.getBean(PeopleJdbcTemplate.class);
          CarJdbcTemplate CarJdbcTemplate = context.getBean(CarJdbcTemplate.class);
          People p = new People(1, "aaaaaaaaaa", new Date());
        if (peopleJdbcTemplate.findOne(1) == null) {
            peopleJdbcTemplate.create(p);
        }
        Car c =new Car("GE12345", 1, "bleu" ,null);
        Car c2 =new Car("VD12345", 1, "bleu",null);
        if (CarJdbcTemplate.findOne("GE12345") == null) {
            CarJdbcTemplate.create(c);
        }
        CarJdbcTemplate.setOwner("GE12345", 1);
        if (CarJdbcTemplate.findOne("VD12345") == null) {
            int r=peopleJdbcTemplate.addCarToWner(c2, p.getId()); 
            r=peopleJdbcTemplate.removeCarbySerialNoFromOwner(c2.getSerialno());
        }
           
         List<Car> lc=peopleJdbcTemplate.getCarsFormOwner(p.getId());
        
        People pp = peopleJdbcTemplate.findOne(1);
        System.out.println("found " + pp);
        pp.setName("toto");
        System.out.println("nb updaté = " + peopleJdbcTemplate.update(pp));
    
        System.out.println("removeCar GE12345 bySerialNoFromOwner 1= " + peopleJdbcTemplate.removeCarbySerialNoFromOwner("GE12345"));
        //System.out.println("remove VD12345 CarbySerialNoFromOwner 1 = " +peopleJdbcTemplate.removeCarbySerialNoFromOwner("VD12345"));
        System.out.println("nb supprimé =" + peopleJdbcTemplate.deleteById(1));
        System.out.println("nb supprimé =" + CarJdbcTemplate.deleteById(c.getSerialno()));
        System.out.println("nb supprimé =" + CarJdbcTemplate.deleteById(c2.getSerialno()));
        

    }

}
