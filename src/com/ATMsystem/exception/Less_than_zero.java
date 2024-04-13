package com.ATMsystem.exception;

public class Less_than_zero extends Exception{
    public Less_than_zero(){
        super();
    }
    public Less_than_zero(String str){
        super(str);
    }
    public static void check(double money) throws Less_than_zero {
        if (money <= 0) throw new Less_than_zero("输入必须大于0");
    }
}
