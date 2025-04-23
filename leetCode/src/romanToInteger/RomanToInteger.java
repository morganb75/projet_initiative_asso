package romanToInteger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RomanToInteger {

    public static Integer calculateRomanToInteger(String s) {

        Map<Character, Integer> map = new HashMap<>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};

        int nextValue = 0;
        int total = 0;

        for (int i = 0; i < s.length(); i++) {
            int value = map.get(s.charAt(i));
            if (i + 1 < s.length()) {
                nextValue = map.get(s.charAt(i + 1));
            } else {
                nextValue = 0;
            }
            if (value >= nextValue) {
                total += value;
            } else {
                total -= value;
            }
        }
        return total;
    }
}
