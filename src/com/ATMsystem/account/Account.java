package com.ATMsystem.account;
import java.util.*;
import java.lang.*;
public abstract class Account {
    String card;//账号
    StringBuffer password;//密码
    public abstract void freeze();//冻结功能
    public abstract void exit();//退出登录功能
    public  abstract void changepassword();//修改密码功能

}
