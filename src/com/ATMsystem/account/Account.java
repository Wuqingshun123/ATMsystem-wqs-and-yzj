package com.ATMsystem.account;
import java.util.*;
import java.lang.*;
public abstract class Account {
    public String card;//账号
    public StringBuffer password = new StringBuffer();//密码
    public abstract void freeze(HashSet<User> users);//冻结功能
    public abstract void exit();//退出功能


    public abstract void cancelaccout(HashSet<User> users);//注销功能
}
