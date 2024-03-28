package com.ATMsystem.main;

import com.ATMsystem.account.User;
import com.ATMsystem.input.Regist;
import com.ATMsystem.interver.*;
import com.ATMsystem.introduction.Introduce;

import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Introduce.printintroduce();//前言
        Wait.jixu();
        Opration.initialoption();//初始界面
    }
}
