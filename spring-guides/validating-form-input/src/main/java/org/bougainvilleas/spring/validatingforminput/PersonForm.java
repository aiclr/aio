package org.bougainvilleas.spring.validatingforminput;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {

  //Does not allow a null value
  @NotNull
  //Allows names between 2 and 30 characters long.
  @Size(min = 2, max = 30)
  private String name;

  @NotNull
  //Does not allow the age to be less than 18.
  @Min(18)
  private Integer age;

  @Override
  public String toString() {
    return "PersonForm{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
