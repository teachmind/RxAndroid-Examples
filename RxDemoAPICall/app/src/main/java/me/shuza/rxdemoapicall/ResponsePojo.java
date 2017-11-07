package me.shuza.rxdemoapicall;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * :=  created by:  Shuza
 * :=  create date:  11/5/2017
 * :=  (C) CopyRight Shuza
 * :=  www.shuza.me
 * :=  shuza.sa@gmail.com
 * :=  Fun  :  Coffee  :  Code
 **/

public class ResponsePojo {
    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("blog")
    @Expose
    String blog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }
}
