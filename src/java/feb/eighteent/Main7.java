package src.java.feb.eighteent;

public class Main7 {
    public int reverse(int x) {
        boolean ltZero = x < 0;
        StringBuilder num = new StringBuilder(String.valueOf(Math.abs(x)));
        num = num.reverse();
        int index = 0;
        while (index < num.length() && num.charAt(index) == '0') {
            index++;
        }
        if(index == num.length()){
            return 0;
        }
        int res = 0;
        try{
            res = Integer.parseInt(num.substring(index, num.length()));
        }catch (Exception e){

        }
        return ltZero ? res * -1 : res;
    }
}
