package org.example;

import java.util.List;

public class StringPatternSearchTest {

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
    private static void tests(StringPatternSearch algo,String text, String pattern, int expected) {
        int index = algo.search(text, pattern);
        System.out.println("text: " + text + " pattern: " + pattern + " index: " + index);
        if (index != expected) {
            throw new RuntimeException("Expected: " + expected + " but got: " + index);
        }
    }

    public static void run() {
        List<StringPatternSearch> algorithms = List.of(
                new NaiveSearch(),
                new KnuthMorrisPratt(),
                new RabinKarp(101),
                new BoyerMoore()
        );
        for (StringPatternSearch algo : algorithms) {
            for (TestCase testCase : TestCases) {
                tests(algo, testCase.text(), testCase.pattern(), testCase.expected());
            }
            System.out.println("All tests passed for: " + algo.getClass().getSimpleName());
        }
    }
}
