/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.ittaspringjdbc.business;

import java.util.Date;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor

public class People {
    int id;
    String name;
    Date birthDate;
    List<Car> cars;
    
    @Override
    public String toString() {
        return "People{" + "id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", cars=" + cars + '}';
    }

    public People(int id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

 
}
