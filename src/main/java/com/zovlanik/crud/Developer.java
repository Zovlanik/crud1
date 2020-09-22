package main.java.com.zovlanik.crud;

import java.util.Arrays;

public class Developer {
    long id;
    String name;
    long[] skills;
    long account;

    Developer (long id, String name, long[] skills, long account ) {
        this.id = id;
        this.name = name;
        this.skills = skills;
        this.account = account;
    }
    public String toString(){
        String str = "";
        for (long l : skills){
            str += l + ",";
        }
        return id + "-" + name + "-" + str + "-" + account;
    }
}
