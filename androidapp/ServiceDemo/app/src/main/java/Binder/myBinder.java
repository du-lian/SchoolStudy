package Binder;

import android.os.Binder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class myBinder extends Binder {
    public String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日   hh-mm-ss");
        return format.format(new Date());
    }

    public StringBuffer reverseWords(){
        Scanner scanner = new Scanner(System.in);
        String font = scanner.nextLine();


        StringBuffer sb = new StringBuffer(font);
        return sb.reverse();

    }
}
