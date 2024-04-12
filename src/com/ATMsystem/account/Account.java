package com.ATMsystem.account;
import java.io.IOException;
import java.util.*;
import java.lang.*;
public abstract class Account {
    public String card;//账号
    public StringBuffer password = new StringBuffer();//密码
    public abstract void freeze(HashSet<User> users, User user) throws IOException;//冻结功能
    public abstract void exit();//退出功能


    public abstract int cancelaccout(HashSet<User> users, User user) throws IOException;//注销功能
}
