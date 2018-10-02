/**
 * Length of Last Word
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 * return the length of last word in the string.
 *
 * If the last word does not exist, return 0.
 *
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * For example,
 * Given s = "Hello World",
 * return 5.
 * Created by wuyan on 2015/12/20.
 */
import java.util.Scanner;

public class Solution58 {
    public int lengthOfLastWord(String s) {
        if(s == null || s.length() == 0)
            return 0;
        int result = 0;
        int len = s.length();

        boolean flag = false;
        for(int i = len - 1; i >= 0; i--){
            char c = s.charAt(i);
            if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
                flag = true;
                result++;
            }else{
                if(flag)
                    return result;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            String str = cin.nextLine();
            Solution58 LLW = new Solution58();
            int result = LLW.lengthOfLastWord(str);
            System.out.println(result);
        }
        cin.close();
    }
}
