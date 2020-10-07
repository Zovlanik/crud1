package com.zovlanik.crud;

import com.zovlanik.crud.view.MainView;
import com.zovlanik.crud.view.SkillView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в меню создания объектов:");
        MainView mv = new MainView();
        mv.begin();
    }
}
