package org.cryptanalyzer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticalAnalysis {
    private static Map<Character, Integer> getCharFrequency(String text) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : text.toCharArray()) {
            if (Constants.ALPHABET.contains(ch)) {
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            }
        }
        return freq;
    }

    private static List<Character> sortByFrequency(Map<Character, Integer> freqMap) {
        return freqMap.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static String getResult(String encryptedText, String referenceText) {
        Map<Character, Integer> encryptedFreq = getCharFrequency(encryptedText);
        Map<Character, Integer> referenceFreq = getCharFrequency(referenceText);

        List<Character> encryptedSorted = sortByFrequency(encryptedFreq);
        List<Character> referenceSorted = sortByFrequency(referenceFreq);

        StringBuilder result = getStringBuilder(encryptedText, encryptedSorted, referenceSorted);

        return result.toString();
    }

    private static StringBuilder getStringBuilder(String encryptedText, List<Character> encryptedSorted, List<Character> referenceSorted) {
        Map<Character, Character> decodeMap = new HashMap<>();
        for (int i = 0; i < encryptedSorted.size() && i < referenceSorted.size(); i++) {
            decodeMap.put(encryptedSorted.get(i), referenceSorted.get(i));
        }

        StringBuilder result = new StringBuilder();
        for (char ch : encryptedText.toCharArray()) {
            if (decodeMap.containsKey(ch)) {
                result.append(decodeMap.get(ch));
            } else {
                result.append(ch);
            }
        }
        return result;
    }


}
