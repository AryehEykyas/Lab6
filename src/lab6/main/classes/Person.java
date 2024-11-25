package lab6.main.classes;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    private final LocalDate birthday; //Поле может быть null
    private final float height; //Значение поля должно быть больше 0
    private final String passportID;
    //Длина строки должна быть не меньше 7, строка не может быть пустой, поле может быть null

    public Person(LocalDate birthday, float height, String passportID) {
        this.birthday = birthday;
        this.height = height;
        this.passportID = passportID;
    }

    public boolean validate() {
        if (height <= 0) {
            return false;
        }
        if (passportID != null && (passportID.length() < 7 || passportID.isBlank())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Person{" +
                "birthday=" + birthday +
                ", height=" + height +
                ", passportID='" + passportID + '\'' +
                '}';
    }
}