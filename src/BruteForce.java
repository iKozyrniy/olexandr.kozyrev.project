class BruteForce {
    CaesarCipher caesarCipher = new CaesarCipher();
    String bruteForce(String encryptedText) {
        StringBuilder result = new StringBuilder();
        for (int key = 1; key <= 25; key++) {
            String decryptedText = caesarCipher.decrypt(encryptedText, key);
            result.append("Можливе значення ключа ").append(key).append(":\n").append(decryptedText).append("\n \n");
        }
        return result.toString();
    }
}