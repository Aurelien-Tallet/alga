package org.example;
import java.util.stream.IntStream;

public class KnuthMorrisPratt implements StringPatternSearch {

    // Pre-processes the pattern to calculate the longest proper prefix which is also a suffix for each prefix
    private static int[] computeLPS(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0; // Length of the previous longest prefix suffix
        int i = 1;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
    @Override
    public int search(String text, String pattern) {
        int[] lps = computeLPS(pattern);
        return IntStream.range(0, text.length())
                .filter(i -> pattern.charAt(0) == text.charAt(i))
                .filter(i -> {
                    int j = 0;
                    while (j < pattern.length() && pattern.charAt(j) == text.charAt(i + j)) {
                        j++;
                    }
                    return j == pattern.length();
                })
                .findFirst()
                .orElse(-1);
    }

}
