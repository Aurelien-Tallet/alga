package org.example;

public class Glouton implements StringPatternSearch {
    @Override
    public int search( String txt, String pat) {
        int n = txt.length();
        int m = pat.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == m) {
            return i - m;
        } else {
            return -1;
        }
    }
}
