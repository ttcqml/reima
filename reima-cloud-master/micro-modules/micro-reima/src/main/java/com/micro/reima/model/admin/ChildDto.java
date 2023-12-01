package com.micro.reima.model.admin;

import java.io.Serializable;

// {"birthday":"2014-12-04","sex":"0","name":"尹修然"}
public class ChildDto implements Serializable {

  private String birthday;
  private String sex;
  private String name;

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
