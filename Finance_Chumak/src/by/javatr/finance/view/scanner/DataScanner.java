package by.javatr.finance.view.scanner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DataScanner {
    private static final Scanner scanner = new Scanner(System.in);

    public static String enterString(){
        return scanner.next();
    }

    public static BigDecimal enterBigDecimal(){
        while(!scanner.hasNextBigDecimal()){
            scanner.next();
        }
        return scanner.nextBigDecimal();
    }

    public static double enterDouble() throws InputMismatchException {
        while(!scanner.hasNextDouble()){
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public static int enterInt() throws InputMismatchException {
        while(!scanner.hasNextInt()){
            scanner.next();
        }
        int data = scanner.nextInt();
        return data;
    }

    public static Date enterDate(){
        boolean flag;
        Date date = null;
        do {
            flag = false;
            String data = scanner.next();
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            try {
                date = ft.parse(data);
            }catch (ParseException ex){
                flag = true;
            }
        } while(flag);
        return date;
    }
}
