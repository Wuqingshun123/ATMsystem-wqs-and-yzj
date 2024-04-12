package data.currency;

import com.ATMsystem.account.User;
import com.ATMsystem.interver.Wait;

public class Credit {
    public static int maxwithdraw(User user, double money){
        int credit = user.credit;
        int max = 0;
        if (credit == 0) return max;
        if (credit <= 20) max = 5000;
        else if (credit <= 40) max = 10000;
        else if (credit <= 60) max = 20000;
        else if (credit <= 80) max = 50000;
        else if (credit <= 100) max = 100000;
        if (money > max){
            System.out.printf("您的信誉分为%d, 最大取款和存款和转账的额度为%d元\n", user.credit, max);
            Wait.exit();
            return -1;
        }
        return max;
    }
    public static int maxtransfers(User user){
        int credit = user.credit;
        int transfers = user.transfers;
        int max = 0;
        if (credit == 0) return -1;
        if (credit <= 20) max = 6;
        else if (credit <= 40) max = 8;
        else if (credit <= 60) max = 16;
        else if (credit <= 80) max = 20;
        else if (credit <= 100) max = 40;
        if (transfers > max){
            System.out.printf("您的信誉分为%d, 您今天最多只可以操作货币%d次\n", credit, max);
            Wait.exit();
            return -1;
        }
        return 1;
    }
    public static int maxloan(User user, double money){
        int credit = user.credit;
        int max = 0;
        if (credit == 0) return max;
        if (credit <= 20) max = 100000;
        else if (credit <= 40) max = 200000;
        else if (credit <= 60) max = 300000;
        else if (credit <= 80) max = 400000;
        else if (credit <= 100) max = 500000;
        if (money > max){
            System.out.printf("您的信誉分为%d, 您最多只可以贷款%.2f\n", credit, max);
            Wait.exit();
            return -1;
        }
        return 1;
    }
    public static double payback(User user, double loan){
        int credit = user.credit;
        double max = 0;
        int day = 0;
        if (credit == 0) return -1;
        if (credit <= 20) {
            max = 0.1;
            day = 2;
        }
        else if (credit <= 40) {
            max = 0.12;
            day = 3;
        }
        else if (credit <= 60) {
            max = 0.15;
            day = 4;
        }
        else if (credit <= 80) {
            max = 0.17;
            day = 5;
        }
        else if (credit <= 100) {
            max = 0.2;
            day = 6;
        }
        return ((loan * (1 + max))) / day;
    }
    public static int payday(User user){
        int credit = user.credit;
        int day = 0;
        if (credit == 0) return -1;
        if (credit <= 20) day = 2;
        else if (credit <= 40) day = 3;
        else if (credit <= 60) day = 4;
        else if (credit <= 80) day = 5;
        else if (credit <= 100) day = 6;
        return day;
    }
}
