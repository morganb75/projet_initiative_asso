package integerToRoman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegerToRoman {

    public static String generate(Integer number) {
        Map<Integer, String> map = new HashMap<>() {{
            put(1, "I");
            put(4, "IV");
            put(5, "V");
            put(9, "IX");
            put(10, "X");
            put(40, "XL");
            put(50, "L");
            put(90, "XC");
            put(100, "C");
            put(400, "CD");
            put(500, "D");
            put(900, "CM");
            put(1000, "M");
        }};

        List<Integer> decomposition = new ArrayList<>(map.keySet());
        Collections.sort(decomposition);
        Collections.reverse(decomposition);
        StringBuilder result = new StringBuilder();

        for (Integer val : decomposition) {
            while (number >= val) {
                result.append(map.get(val));
                number -= val;
            }
        }
        return result.toString();
    }
}
