import java.util.Scanner;
import java.util.regex.Pattern;
public class main{
    public static String calc(String input){
        String ret = "";
        short arab = 2;
        short rome = 2;
        float first = 0;
        float second = 0;
        boolean math;
        int result = 0;
        String tar1 = "^[\\d]+";
        String tar2 = "^[IVXLCDM]+";
        int [] arrArab = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String [] arrRome = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        String [] arr = input.split("[\\+\\-\\*\\/]");
        try {
            if (input.length() < 2) throw new Exception("строка не является математическим выражением");
            if (arr.length > 2 || arr.length < 2) throw new Exception("формат математического выражения не удовлетворяет заданию");
        }
        catch (Exception except) { System.out.println(except.getMessage()); System.exit(1);}
        String mo = input.replaceFirst(arr[0], "").replaceFirst(arr[1], "");
        if(!Pattern.matches(tar1, arr[0])){
            if(!Pattern.matches(tar2, arr[0]))   {}
            else {
                short ind;
                short p = 0;
                short i = 12;
                while (i >= 0 && p < arr[0].length()) {
                    if(i>0 && (arrRome[i].length()+p)>(arr[0].length()-p)){i--;}
                    else{
                        if (arr[0].substring(p, arrRome[i].length()+p).equals(arrRome[i])) {
                            first += arrArab[i];
                            p += arrRome[i].length();
                        }
                        else   {i--; }}
                }
                rome--;
            }
        }
        else {first = Integer.parseInt(arr[0]); arab--; }
        if(!Pattern.matches(tar1, arr[1])){
            if(!Pattern.matches(tar2, arr[1]))   {}
            else {
                short p = 0;
                short i = 12;
                while (i >= 0 && p < arr[1].length()) {
                    if(i>0 && (arrRome[i].length()+p)>(arr[1].length()-p)){i--;}
                    else{
                        if (arr[1].substring(p, arrRome[i].length()+p).equals(arrRome[i])) {
                            second += arrArab[i];
                            p += arrRome[i].length();
                        }
                        else {i--;}}
                }
                rome--;
            }
        }
        else {second = Integer.parseInt(arr[1]); arab--;}
        try{
            if(arab>0 && rome>0) throw new Exception("используются одновременно разные системы счисления");
            if(mo.equals("/") && second == 0) throw new Exception("делить на 0 нельзя");
            if(first > 10 || first < 1) throw new Exception("incorrect value1");
            if(second > 10 || second < 1) throw new Exception("incorrect value2");
        }
        catch (Exception except) { System.out.println(except.getMessage()); System.exit(1);}
        switch (mo) {
            case ("+"): {
                result = (int) ((first) + (second));
                break;
            }
            case ("-"): {
                result = (int) ((first) - (second));
                break;
            }
            case ("*"): {
                result = (int) ((first) * (second));
                break;
            }
            case ("/"): {
                result = (int) ((first) / (second));
                break;
            }
        }
        if(rome==0) {
            try {
                if(result < 1 ) throw new Exception("отрицательные числел у римлян нет");
                else {
                    var i = 12;
                    while (result > 0) {
                        if (result >= arrArab[i]) {
                            ret += arrRome[i];
                            result -= arrArab[i];
                        }
                        else i--;
                    }
                }
            }
            catch (Exception except) { System.out.println(except.getMessage()); System.exit(1);}
        }
        else ret += result;
        return ret;
    }
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String strline = in.nextLine().replaceAll("\\s+", "");
        System.out.println(calc(strline));
    }
}
