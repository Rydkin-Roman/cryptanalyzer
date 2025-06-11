package org.cryptanalyzer;

public class BruteForce {
    public static int caesarBruteForce(String content) {
        int alphabetSize = Constants.ALPHABET.size();
        int maxMatches = 0;
        int bestKey = 0;

        for (int key = 1; key <= alphabetSize; key++) {
            String decryptedText = Decrypt.caesarDecrypt(content, key);

            int matches = 0;
            for (String word : Constants.WORDS) {
                if (decryptedText.contains(word)) {
                    matches++;
                }
            }

            if (matches > maxMatches) {
                maxMatches = matches;
                bestKey = key;
            }
        }
        return bestKey;
    }

}
