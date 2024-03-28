package com.ATMsystem.main;

import com.ATMsystem.account.User;
import com.ATMsystem.input.Regist;
import com.ATMsystem.interver.*;
import com.ATMsystem.introduction.Introduce;

import java.util.*;
import java.lang.*;

public class Main {
    public static int count = 0;
    public static void main(String[] args) {
        User[] users = new User[1000];
        Scanner scan = new Scanner(System.in);
        Introduce.printintroduce();
        Wait.jixu();
        Opration.initialoption(users, count);
    }
}
