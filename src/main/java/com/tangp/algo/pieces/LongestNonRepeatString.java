package com.tangp.algo.pieces;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 计算给定字符串中最长不重复子串
 *
 * @author tangp
 */
public class LongestNonRepeatString {
    public static void main(String[] args) {
        String target = "abc12345123412345678121231234abcd";
        System.out.println(String.join(",", find(target)));
        System.out.println("Another result:>>>>>>>>");
        System.out.println(String.join(",", longestNonRepeat(target)));
    }

    /**
     * 核心思想为：
     *
     * 循环查找，利用一个Map来标识每个出现过的character所在的index
     * 查找到不重复字串后，从发生了重复字符的下一个index开始查找；
     *
     * @param str
     * @return
     */
    public static Set<String> longestNonRepeat(String str) {
        if (str == null || str.length() <= 1) {
            return new HashSet<>(Arrays.asList(str));
        }

        final Set<String> result = new HashSet<>();

        //用于保存查找到的不重复字串的各个字符所在的index；`
        Map<Character, Integer> characterIndexMarker = new HashMap<>(str.length());

        //当前查找到的最长字串的长度；
        //int currentFoundNonRepeatedStrLength = 0;
        int repeatedPosition = -1;

        for (int index = 0, size = str.length(); index < size; index++) {
            char c = str.charAt(index);
            if (characterIndexMarker.containsKey(c)) {

                String foundNonRepeatedString = str.substring(repeatedPosition + 1, index);
                qualifyTempResult(result, foundNonRepeatedString);

                //标识出现了重复字符的地方；
                repeatedPosition = characterIndexMarker.get(c);

                //从发生了重复字符的下一个index开始查找，也就是repeatedPosition + 1；
                characterIndexMarker.clear();
                for (int idx = repeatedPosition + 1; idx <= index; idx++) {
                    characterIndexMarker.put(str.charAt(idx), idx);
                }
            } else {
                characterIndexMarker.put(c, index);
            }
        }
        //查找结束，最后再检查一次当前查找字串是否符合要求；
        qualifyTempResult(result, str.substring(repeatedPosition + 1));

        return result;
    }

    /**
     * 检查查找到的不重复字串结果是否符合要求：
     * 1. 如果比现有的结果长，则清空结果后，push；
     * 2. 如果一样长，则直接push；
     *
     * @param resultSet
     * @param tempResult
     * @return
     */
    private static int qualifyTempResult(Set<String> resultSet, String tempResult) {
        if (resultSet.isEmpty()) {
            resultSet.add(tempResult);
            return tempResult.length();
        }
        String existingResult = resultSet.iterator().next();
        if (tempResult.length() > existingResult.length()) {
            //如果此次查找到的字串长于现有的长度，则替换之；
            resultSet.clear();
            resultSet.add(tempResult);
            return tempResult.length();
        } else if (tempResult.length() == existingResult.length()) {
            //如果此次查找到的结果和之前的结果长度一致，则push当前结果；
            resultSet.add(tempResult);
        }
        //到此，resultSet中的字符串长度均相等，因此可以只返回existingResult.length()；
        return existingResult.length();
    }

    /**
     * 递归实现方式
     *
     * 核心思想是：从字符串的第0个位置开始查找，如果查找到一个重复出现的character，则将当前不重复字串放入临时结果集a，
     * 同时从发生了重复字符的地方开始重新查找，得到字串的结果集b，根据这两个结果a/b中的的字符串长度情况，返回最长结果；
     *
     * 由于使用递归实现，如果字符串过长，会发生栈溢出风险；
     *
     * @param str
     * @return
     */
    public static Set<String> find(String str) {
        if (null == str || str.length() <= 1) {
            return new HashSet<>(Arrays.asList(str));
        }

        Set<String> out = new HashSet<>();
        Map<Character, Integer> characterIndexMarker = new HashMap<>();
        for (int index = 0, size = str.length(); index < size; index++) {
            char c = str.charAt(index);
            if (characterIndexMarker.containsKey(c)) {
                String foundSubString = str.substring(0, index);
                out.add(foundSubString);

                //查找到一个不重复字串后，从发生了重复字符的地方开始重新查找；
                Set<String> subOut = find(str.substring(characterIndexMarker.get(c) + 1));
                if (subOut == null || subOut.isEmpty()) {
                    return out;
                }

                //每个子结果集都只存放最长的串，因此可以保证只取其中之一就能用于对比串的长度；
                String subOutSampleString = subOut.iterator().next();

                if (foundSubString.length() > subOutSampleString.length()) {
                    //如果本次查找到的字串长度比子结果中的串都长，则只使用本次的查找结果；
                    return out;
                } else if (foundSubString.length() == subOutSampleString.length()) {
                    //如果本次查找到的字串长度比子结果中的串一样长，则merge这两个结果集，同时返回；
                    out.addAll(subOut);
                    return out;
                } else {
                    //如果本次查找到的字串长度并不比子结果中的串长，则使用子结果集；
                    return subOut;
                }
            } else {
                characterIndexMarker.put(c, index);
            }
        }

        //如果至此都没有发现有重复出现的字符，则说明整个str都是不重复的；
        out.add(str);
        return out;
    }
}
