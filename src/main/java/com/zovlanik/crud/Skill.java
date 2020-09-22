package main.java.com.zovlanik.crud;

class Skill {
    long id;
    String name;

    Skill(long id, String name){
        this.id = id;
        this.name = name;
    }
    Skill(String name){

        this.name = name;
    }

    public String toString(){

        return id + "," + name;
    }
}