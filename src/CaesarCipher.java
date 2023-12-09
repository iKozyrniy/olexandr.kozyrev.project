import java.util.Arrays;

class CaesarCipher {
    String encrypt(String text, int key) {
        final char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        StringBuilder newText = new StringBuilder();

        for (char letter : text.toCharArray()) {
            if (Character.isLetter(letter)) {
                char lowerLetter = Character.toLowerCase(letter);
                if ((lowerLetter + key) > 'z') {
                    int i = (lowerLetter + key) - 'z' - 1;
                    char encryptedChar = letters[i];
                    if (Character.isUpperCase(letter)) {
                        encryptedChar = Character.toUpperCase(encryptedChar);
                    }
                    newText.append(encryptedChar);
                } else {
                    char encryptedChar = letters[Arrays.binarySearch(letters, lowerLetter) + key];
                    if (Character.isUpperCase(letter)) {
                        encryptedChar = Character.toUpperCase(encryptedChar);
                    }
                    newText.append(encryptedChar);
                }
            } else {
                newText.append(letter);
            }
        }
        return newText.toString();
    }

    String decrypt(String text, int key) {
        StringBuilder newText = new StringBuilder();

        for (char letter : text.toCharArray()) {
            if (Character.isLetter(letter)) {
                char decryptedChar = (char) (letter - key);
                if (Character.isUpperCase(letter) && decryptedChar < 'A') {
                    decryptedChar = (char) (decryptedChar + 26);
                } else if (Character.isLowerCase(letter) && decryptedChar < 'a') {
                    decryptedChar = (char) (decryptedChar + 26);
                }
                newText.append(decryptedChar);
            } else {
                newText.append(letter);
            }
        }
        return newText.toString();
    }
}