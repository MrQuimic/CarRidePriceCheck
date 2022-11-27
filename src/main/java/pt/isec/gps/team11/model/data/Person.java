package pt.isec.gps.team11.model.data;

import java.util.Date;
import java.util.Objects;

public class Person {
    private String name;
    private int age;
    private String email;
    private Date birthDate;

    public Person(String name, String email, Date birthDate) {
        this.name = name;
        this.age = 20; //TODO PUT DYNAMIC FROM BIRTHDATE
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(!(obj instanceof Person))
            return  false;

        Person aux = (Person) obj;

        return this.hashCode() == aux.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getEmail());
    }
}
