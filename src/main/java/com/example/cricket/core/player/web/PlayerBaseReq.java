package com.example.cricket.core.player.web;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.example.cricket.base.BaseRequest;

public class PlayerBaseReq extends BaseRequest 
{

    @NotEmpty
    private String surname;

    @NotEmpty
    private String name;

    private int height;

    private int weight;

    private int age;

    @NotEmpty
    private List<@Valid Id> teams;


    public String getSurname() 
    {
        return surname;
    }

    public void setSurname(String surname) 
    {
        this.surname = surname;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getHeight() 
    {
        return height;
    }

    public void setHeight(int height) 
    {
        this.height = height;
    }

    public int getWeight() 
    {
        return weight;
    }

    public void setWeight(int weight) 
    {
        this.weight = weight;
    }

    public int getAge() 
    {
        return age;
    }

    public void setAge(int age) 
    {
        this.age = age;
    }

    public List<Id> getTeams() 
    {
        return teams;
    }

    public void setTeams(List<Id> teams) 
    {
        this.teams = teams;
    }
}
