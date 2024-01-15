package edu.school21.ORM.models;


import edu.school21.ORM.annotations.OrmColumn;
import edu.school21.ORM.annotations.OrmColumnId;
import edu.school21.ORM.annotations.OrmEntity;
import jakarta.persistence.GenerationType;

import java.util.StringJoiner;

@OrmEntity(table = "simple_cat")
public class Cat {
    @OrmColumnId(id = GenerationType.IDENTITY)
    private Long id;
    @OrmColumn(name = "name", length = 10)
    private String name;
    @OrmColumn(name = "color", length = 10)
    private String color;
    @OrmColumn(name = "age")
    private Integer age;

    public Cat(String name, String color, Integer age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public Cat(Long id, String name, String color, Integer age) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Cat.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("color='" + color + "'")
                .add("age=" + age)
                .toString();
    }
}
