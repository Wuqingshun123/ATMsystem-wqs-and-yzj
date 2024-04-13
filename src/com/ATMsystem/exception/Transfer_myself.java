package com.ATMsystem.exception;

public class Transfer_myself extends Exception{
    public Transfer_myself(){
        super();
    }
    public Transfer_myself(String str){
        super(str);
    }
    public static void Check(String a, String b) throws Transfer_myself {
        if (a.compareTo(b) == 0) throw new Transfer_myself("不允许对自己转账");
    }
}
