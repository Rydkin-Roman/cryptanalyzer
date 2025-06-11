package org.cryptanalyzer;

public class Encrypt {
    public static String caesarEncrypt(String content, int key) {
        StringBuilder result = new StringBuilder();

        int alphabetSize = Constants.ALPHABET.size();

        for (char ch : content.toCharArray()) {
            int index = Constants.ALPHABET.indexOf(ch);

            if (index != -1) {
                int newIndex = (index + key) % alphabetSize;
                result.append(Constants.ALPHABET.get(newIndex));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

}
