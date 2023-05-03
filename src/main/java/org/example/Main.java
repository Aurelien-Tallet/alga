package org.example;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        StringPatternSearchTest.run();

        // TEST AHO CORASICK WITH MULTIPLE PATTERNS (RETURN ALL PATTERN PRESENT IN THE TEXT)
        String[] P = {"he", "she", "his", "hers"} ;
        String T = "HERE IS A SIMPLE EXAMPLE";
        AhoCorasick ahoCorasick = new AhoCorasick(Arrays.asList(P));
        var result = ahoCorasick.search(T.toLowerCase());
        System.out.println(result);
    }

}