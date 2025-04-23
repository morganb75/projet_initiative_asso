import integerToRoman.IntegerToRoman;
import rechercheArbreBinaire.BinaryTree;
import romanToInteger.RomanToInteger;

public class Main {
    public static void main(String[] args) throws Exception {

        //        int[] nums = {2, 7, 11, 15};
        //        int[] array = {120, 80, 150, 110, 50, 200, 70, 90, 100, 30};
        //        int target = 9;
        //        int palindrome = 123321;
        //        String s = "A man, a plan, a canal: Panama";
        //        //        System.out.println(Arrays.toString(Exo1.twoSum(nums, target)));
        //        //        System.out.println(Exo2.isPalindrome(palindrome));
        //        //        System.out.println(ValidPalindrome.isPalindrome(s));
        //        //        System.out.println(Addition.addition(array));
        //        //        System.out.println(Addition.additionRecursive(array,0));
        //        int num = 10;
        //        System.out.println(num + "! = " + Recursive.factorielle(num));
        //        System.out.println(num + "! = " + Recursive.factorielleRecursive(num));
        //        System.out.println(num + "! = " + Recursive.factorielleRecursiveTerminale(num, 1));

        //RECHERCHE EN BINARY TREE (STRUCTURE RECURSIVE) cf package rechercheArbreBinaire*******
        //          8
        //        /   \
        //       3    10
        //      / \     \
        //     1   6     14
        //        / \    /
        //       4   7  13
        //
        //        BinaryTree tree = new BinaryTree(8, "huit",
        //                new BinaryTree(3, "trois",
        //                        new BinaryTree(1, "un", null, null),
        //                        new BinaryTree(6, "six",
        //                                new BinaryTree(4, "quatre", null, null),
        //                                new BinaryTree(7, "sept", null, null))),
        //                new BinaryTree(10, "dix", null,
        //                        new BinaryTree(14, "quatorze",
        //                                new BinaryTree(13, "treize", null, null), null)));
        //        System.out.println(tree.get(13));
        //        tree.set(5, "cinq");
        //        System.out.println(tree.get(5));
        //        System.out.println(tree.toString());
        //***************************************************************************************
//        String input = "III";
//        String input2 = "LVIII";
//        String input3 = "XIX";
//        System.out.println(RomanToInteger.calculateRomanToInteger(input));
//        System.out.println(RomanToInteger.calculateRomanToInteger(input2));
//        System.out.println(RomanToInteger.calculateRomanToInteger(input3));
        Integer number = 3749;
        System.out.println(IntegerToRoman.generate(number));
    }
}