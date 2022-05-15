package dto;

//import controller.IdManager;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PersonDTO implements Serializable { // Помнить что этот класс может бить null, переделать конструкторы при необходимости
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDateTime birthday; //Поле может быть null
    private float weight; //Значение поля должно быть больше 0
//    private String passportID; //Длина строки не должна быть больше 33, Значение этого поля должно быть уникальным, Поле может быть null


    public PersonDTO(String name, LocalDateTime birthday, float weight, String passportID) {
        this.name = name;
        this.birthday = birthday;
        this.weight = weight;
//        this.passportID = passportID;
    }
    public PersonDTO(String name, LocalDateTime birthday, float weight) {
        this.name = name;
        this.birthday = birthday;
        this.weight = weight;
//        this.passportID = IdManager.setPersonID(Math.abs(UUID.randomUUID().hashCode()));
//        IdManager.savePersonID(passportID);
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public float getWeight() {
        return weight;
    }

//    public String getPassportID() {
//        return passportID;
//    }

    @Override
    public String toString() {
        return  "name=" + name+
                ", birthday=" + birthday +
                ", weight=" + weight
//                +", passportID=" + passportID
                ;
    }
}
