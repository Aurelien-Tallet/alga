package org.example;

import java.util.List;

public class StringPatternSearchTest {

    private enum Color {
        Black("\u001B[30m"),
        Red   ("\u001B[31m"),
        Green    ("\u001B[32m"),
        Yellow    ("\u001B[33m"),
        Blue    ("\u001B[34m"),
        Purple    ("\u001B[35m"),
        Cyan    ("\u001B[36m"),
        Bold("\u001B[1m"),
        Reset("\u001B[0m"),
        Underline("\u001B[4m");
        private String value;
        public String getValue() {
            return value;
        }
        @Override
        public String toString() {
            return value;
        }
        Color(String value) {
            this.value = value;
        }
    }
    private record TestCase(String text, String pattern, int expected) {
    }
    private final static List<TestCase> TestCases = List.of(
            new TestCase("ABCBABDABACDABABCABAB", "ABAB", 12),
            new TestCase("BABABDABACDABABCABAB", "DABACD", 5),
            new TestCase("ABAEEBDDDDABACDEEEABADDDBCABAB", "DABACDAB", -1),
            new TestCase("baababababbaa", "babb",7),
            new TestCase("baaaabaa","baaa", 0),
            new TestCase("bababaa","babaa", 2),
            new TestCase("ababababac","ababac", 4)
    );
    private static boolean tests(StringPatternSearch algo,String text, String pattern, int expected) {
        var result = true;
        try {
            int index = algo.search(text, pattern);
            System.out.println(Color.Bold + "" + Color.Underline + "text:" + Color.Reset + " " + text + " " + Color.Bold + " " + Color.Underline + "pattern:" + Color.Reset + " " + pattern + " " + Color.Bold + " " + Color.Underline + "index:" + Color.Reset + " " +index);
            if (index != expected) {
                throw new RuntimeException("Expected: " + expected + " but got: " + index);
            }
        } catch (Exception e) { // catch all exceptions
            System.out.println(Color.Red + e.getMessage() + Color.Reset);
            result = false;
        }
        return result;
    }

    public static void run() {
        List<StringPatternSearch> algorithms = List.of(
                new NaiveSearch(),
                new KnuthMorrisPratt(),
                new RabinKarp(101),
                new BoyerMoore()
        );
        for (StringPatternSearch algo : algorithms) {
            var NbTests = TestCases.size();
            for (TestCase testCase : TestCases) {
               boolean test = tests(algo, testCase.text(), testCase.pattern(), testCase.expected());
               if (!test) NbTests--;
            }
            if(NbTests == TestCases.size()) {
                System.out.println(Color.Green + "(✔) All tests passed for: " + algo.getClass().getSimpleName() + Color.Reset);
            } else {
                System.out.println(Color.Red + "(✘) " + (TestCases.size() - NbTests) + "/" + TestCases.size() + " tests failed for: " + algo.getClass().getSimpleName() + Color.Reset);
            }
        }
    }
}
