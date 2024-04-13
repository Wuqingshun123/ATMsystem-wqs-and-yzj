package com.ATMsystem.exception;

public class Less_than_fen extends Exception{
    public Less_than_fen(){
        super();
    }
    public Less_than_fen(String str){
        super(str);
    }
    public static void check(double money) throws Less_than_fen {
        double a = money * 100;
        double b = Math.floor(a);
        if (a != b) throw new Less_than_fen("不允许出现比分小的钱");
    }
}
