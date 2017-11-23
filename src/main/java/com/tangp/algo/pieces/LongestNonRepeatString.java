package com.tangp.algo.pieces;

import java.util.*;
import java.util.stream.Collectors;

//计算字符串a中最长不重复子串
public class LongestNonRepeatString {
    public static void main(String[] args) {
        String target = "abc1234612341234567121231234";
        System.out.println(String.join(",", longestNonRepeat(target)));
    }

    public static List<String> longestNonRepeat(String src) {
        if (src == null || src.length() <= 1) {
            return Arrays.asList(src);
        }
        Set<String> result = new HashSet<String>();

        Map<String, Integer> marker = new HashMap<String, Integer>(src.length());
        int maxLength = 0, searchIndex = 0;
        for (int idx = 0, size = src.length(); idx < size; idx++) {
            String c = String.valueOf(src.charAt(idx));
            if (marker.containsKey(c)) {
                if (searchIndex <= marker.get(c)) {
                    String subRet = src.substring(searchIndex, idx);
                    int length = subRet.length();

                    if (maxLength < length) {
                        maxLength = length;
                        result.clear();
                        result.add(subRet);
                    } else if (maxLength == length) {
                        result.add(subRet);
                    }
                    searchIndex = marker.get(c) + 1;
                } else {
                    searchIndex = searchIndex + 1;
                }
            }
            marker.put(c, idx);
        }
        return result.stream().collect(Collectors.toList());
    }
}
