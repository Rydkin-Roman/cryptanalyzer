package org.cryptanalyzer;

public class Decrypt {
    public static String caesarDecrypt(String content, int key) {
        StringBuilder result = new StringBuilder();

        int alphabetSize = Constants.ALPHABET.size();

        for (char ch : content.toCharArray()) {
            int index = Constants.ALPHABET.indexOf(ch);

            if (index != -1) {
                int newIndex = (index - key) % alphabetSize;
                if (newIndex < 0) {
                    newIndex += alphabetSize;
                }
                result.append(Constants.ALPHABET.get(newIndex));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

}
