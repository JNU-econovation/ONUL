package com.example.onul_app;

import java.util.List;

public class Memberinfo {
    private String name;
    private String nickname;
    private String email;

    public Memberinfo(String name,String nickname,String email){
        this.name=name;
        this.nickname=nickname;
        this.email=email;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getNickname(){
        return this.nickname;
    }
    public void setNickname(String nickname){
        this.nickname=nickname;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email=email;
    }

}
