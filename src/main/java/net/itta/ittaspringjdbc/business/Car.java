package net.itta.ittaspringjdbc.business;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Car {
    String serialno;
    Integer modelid;
    String color;
    Integer ownerid;
    People owner; 

    public Car(String serialno, Integer modelid, String color, Integer ownerid) {
        this.serialno = serialno;
        this.modelid = modelid;
        this.color = color;
        this.ownerid = ownerid;
    } 
    @Override
    public String toString() {
        return "Car{" + "serialno=" + serialno + ", modelid=" + modelid + ", color=" + color + ", ownerid=" + ownerid + '}'  + getOwner();
    }


}
