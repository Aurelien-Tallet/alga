package org.example;

public class RabinKarp implements StringPatternSearch {

    // number of characters in the input alphabet
    public final static int d = 256;

    // PRIME NUMBER
    public int q;

    public RabinKarp(int q) {
        this.q = q;
    }
    @Override
    public int search( String txt, String pat) {
        int M = pat.length();
        int N = txt.length();
        int i, j;
        int p = 0; // hash for pattern
        int t = 0; // hash for text
        int h = 1;

        for (i = 0; i < M - 1; i++)
            h = (h * d) % this.q;

        // Claculate the hash value of pattern
        for (i = 0; i < M; i++) {
            p = (d * p + pat.charAt(i)) % this.q;
            t = (d * t + txt.charAt(i)) % this.q;
        }

        // Slide the pattern over text one by one
        for (i = 0; i <= N - M; i++) {
            // Check the hash values of current window of text and pattern.

            if (p == t) {
                /* Check for characters one by one */
                for (j = 0; j < M; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }
                if (j == M)
                    return i;
            }

            // Calculate next hash value
            if (i < N - M) {
                t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + M)) % this.q;

                // if t is negative -> convert to positive
                if (t < 0)
                    t = (t + this.q);
            }
        }

        return -1; //not found
    }

}
