package edu.school21.ORM.models;

import edu.school21.ORM.annotations.OrmColumn;
import edu.school21.ORM.annotations.OrmColumnId;
import edu.school21.ORM.annotations.OrmEntity;

@OrmEntity(table = "simple_user")
public class User {
    static Long idGenerator = 1L;
    @OrmColumnId
    private Long id;
    @OrmColumn(name = "first_name", length = 10)
    private String firstName;
    @OrmColumn(name = "last_name", length = 10)
    private String lastName;
    @OrmColumn(name = "age")
    private Integer age;

    public User() {};

    public User(String firstName, String lastName, Integer age) {
        this.id = idGenerator;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        ++idGenerator;
    }

    public User(Long id, String firstName, String lastName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
