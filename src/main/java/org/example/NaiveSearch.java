package org.example;

public class NaiveSearch implements StringPatternSearch {
    @Override
    public int search(String T, String P) {
        int n = T.length();
        int m = P.length();
        for (int k = 0; k <= n - m; k++) {
            boolean found = true;
            for (int i = 0; i < m; i++) {
                if (P.charAt(i) != T.charAt(k + i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return k;
            }
        }
        return -1;
    }

}
