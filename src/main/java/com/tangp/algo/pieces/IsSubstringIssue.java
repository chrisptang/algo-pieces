package com.tangp.algo.pieces;

import java.util.HashMap;
import java.util.Map;

public class IsSubstringIssue {
    //有两个字符串a和b，计算是否a或者将a中的字符重新排序后是b的一个连续子串.

    public static void main(String[] args) {
        String a = "12356", b = "123456789056231dadw221134455";
        System.out.println(isSubstringOf(a, b));
    }

    public static boolean isSubstringOf(String searchingStr, String targetStr) {
        if (searchingStr == null || targetStr == null || targetStr.length() < searchingStr.length()) {
            return false;
        } else if (searchingStr == targetStr) {
            return true;
        }
        Map<Character, Integer> searchingStrMap = new HashMap<>();
        Map<Character, Integer> tempStrMap = new HashMap<>();
        for (int idx = 0, size = searchingStr.length(); idx < size; idx++) {
            mapOperation(searchingStrMap, searchingStr.charAt(idx), Operation.Insert);

            mapOperation(tempStrMap, targetStr.charAt(idx), Operation.Insert);
        }
        if (mapEquals(searchingStrMap, tempStrMap)) {
            return true;
        }
        for (int idx = searchingStr.length(), stop = targetStr.length(); idx < stop; idx++) {
            char cToAdd = targetStr.charAt(idx), cToRemove = targetStr.charAt(idx - searchingStr.length());
            int code1 = mapOperation(tempStrMap, cToAdd, Operation.Insert);
            int code2 = mapOperation(tempStrMap, cToRemove, Operation.Remove);
            if (!searchingStrMap.containsKey(cToAdd) || searchingStrMap.get(cToAdd).intValue() != code1
                    || (searchingStrMap.containsKey(cToRemove) && searchingStrMap.get(cToRemove).intValue() != code2)) {
                continue;
            } else {
                if (mapEquals(searchingStrMap, tempStrMap)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int mapOperation(final Map<Character, Integer> a, char c, Operation operation) {
        int curValue = a.containsKey(c) ? a.get(c) : 0;
        switch (operation) {
            case Insert:
                curValue++;
                break;
            case Remove:
                --curValue;
                break;
        }
        if (curValue <= 0) {
            a.remove(c);
        } else {
            a.put(c, curValue);
        }
        return curValue;
    }

    private static boolean mapEquals(final Map<Character, Integer> a, final Map<Character, Integer> b) {
        if (a == null || b == null || a.size() != b.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : a.entrySet()) {
            if (!b.containsKey(entry.getKey())) {
                return false;
            }
            if (entry.getValue().intValue() != b.get(entry.getKey()).intValue()) {
                return false;
            }
        }
        return true;
    }

    enum Operation {
        Insert, Remove;
    }
}
