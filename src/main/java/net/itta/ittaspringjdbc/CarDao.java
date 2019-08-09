package net.itta.ittaspringjdbc;


import net.itta.ittaspringjdbc.business.Car;
import net.itta.ittaspringjdbc.business.People;


public interface CarDao extends GenericDao<String, Car>{

    People getOwnerFromSerialno(String serialno);
    int setOwner(String serialno, int ownerid);
    
    
}
