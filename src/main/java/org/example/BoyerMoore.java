package org.example;

public class BoyerMoore implements StringPatternSearch {
    @Override
    public int search(String text, String pattern)
    {
        int len = text.length();
        int motl = pattern.length() - 1;
        int i = 0;
        while(i < len){
            int j = motl - 1;
            while(j >= 0 && text.charAt(i + j) == pattern.charAt(j))
            {
                j--;
            }
            if(j < 0)
                return i;
            i += motl - pattern.lastIndexOf(text.charAt(i + j)) -1;
        }
        return -1;
    }
}
