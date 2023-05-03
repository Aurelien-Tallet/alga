package org.example;

public class BoyerMoore implements StringPatternSearch {
    @Override
    public int search(String str, String motif)
    {
        int len = str.length();
        int motl = motif.length();
        int i = 0;
        while(i + motl < len + 1){
            int j = motl - 1;
            while(j >= 0 && str.charAt(i + j) == motif.charAt(j))
                j--;
            if(j < 0)
                return i;
            i += motl - motif.lastIndexOf(str.charAt(i + j)) -1;
        }
        return -1;
    }
}
