/*
 * Class: CMSC203
 * Instructor: Gary Thai
 * Description: CryptoManager is a utility class that encrypts and decrypts a
 *              phrase using three classical (and insecure) ciphers: Vigenere,
 *              Playfair and Caesar. All methods are static.
 * Due: 10/25/26
 * Platform/compiler: Eclipse IDE / Java (JDK)
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Santiago Piguillem
 */

/**
 * This is a utility class that encrypts and decrypts a phrase using three
 * different approaches.
 *
 * The first approach is called the Vigenere Cipher. Vigenere encryption
 * is a method of encrypting alphabetic text based on the letters of a keyword.
 *
 * The second approach is Playfair Cipher. It encrypts two letters (a digraph)
 * at a time instead of just one.
 *
 * The third approach is Caesar Cipher. It is a simple replacement cipher.
 *
 * @author Huseyin Aygun
 * @version 8/3/2025
 */
public class CryptoManager {

    private static final char LOWER_RANGE = ' ';
    private static final char UPPER_RANGE = '_';
    private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;
    // Use 64-character matrix (8X8) for Playfair cipher
    private static final String ALPHABET64 =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&'()*+,-./:;<=>?@[\\]^_";

    /**
     * Determines whether every character of a string lies inside the allowed
     * ASCII range (from LOWER_RANGE ' ' to UPPER_RANGE '_').
     *
     * @param plainText the string to check
     * @return true if all characters are within bounds, false if any is outside
     */
    public static boolean isStringInBounds(String plainText) {
        for (int i = 0; i < plainText.length(); i++) {
            if (!(plainText.charAt(i) >= LOWER_RANGE && plainText.charAt(i) <= UPPER_RANGE)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vigenere Cipher is a method of encrypting alphabetic text
     * based on the letters of a keyword. It works as below:
     *      Choose a keyword (e.g., KEY).
     *      Repeat the keyword to match the length of the plaintext.
     *      Each letter in the plaintext is shifted by the position of the
     *      corresponding letter in the keyword (A = 0, B = 1, ..., Z = 25).
     *
     * Here the shift is computed over the 64-character alphabet ALPHABET64.
     *
     * @param plainText the (uppercase) text to encrypt
     * @param key       the keyword used to encrypt
     * @return the encrypted text, a message if the key is empty, or
     *         "The selected string is not in bounds, Try again." if the text
     *         or the key is out of bounds
     */
    public static String vigenereEncryption(String plainText, String key) {
        if (key.length() == 0) {
            return "There is no Encription for:" + plainText;
        } else {
            if (isStringInBounds(plainText) && isStringInBounds(key)) {

                String encription = "";

                // Repeat the key until it is longer than the plain text
                String keyloop = key;
                while (keyloop.length() <= plainText.length()) {
                    keyloop += key;
                }

                // Add the key value to each character value and wrap around
                for (int i = 0; i < plainText.length(); i++) {
                    int position = ALPHABET64.indexOf(plainText.charAt(i))
                            + ALPHABET64.indexOf(keyloop.charAt(i));
                    int encripted = Math.floorMod(position, RANGE);
                    encription += ALPHABET64.charAt(encripted);
                }
                return encription;

            } else {
                return "The selected string is not in bounds, Try again.";
            }
        }
    }

    /**
     * Decrypts a string that was encrypted with the Vigenere Cipher.
     * This is the inverse of vigenereEncryption.
     *
     * @param encryptedText the encrypted text
     * @param key           the keyword used to encrypt the original text
     * @return the original plain text, a message if the key is empty, or
     *         "The selected string is not in bounds, Try again." if the text
     *         or the key is out of bounds
     */
    public static String vigenereDecryption(String encryptedText, String key) {
        if (key.length() == 0) {
            return "There is no Decription for:" + encryptedText;
        } else {
            if (isStringInBounds(encryptedText) && isStringInBounds(key)) {

                String decryption = "";

                // Repeat the key until it is longer than the encrypted text
                String keyloop = key;
                while (keyloop.length() <= encryptedText.length()) {
                    keyloop += key;
                }

                // Subtract the key value from each character value and wrap around
                for (int i = 0; i < encryptedText.length(); i++) {
                    int position = ALPHABET64.indexOf(encryptedText.charAt(i))
                            - ALPHABET64.indexOf(keyloop.charAt(i));
                    int decrypted = Math.floorMod(position, RANGE);
                    decryption += ALPHABET64.charAt(decrypted);
                }
                return decryption;

            } else {
                return "The selected string is not in bounds, Try again.";
            }
        }
    }

    /**
     * Playfair Cipher encrypts two letters at a time instead of just one.
     * It works as follows:
     * A matrix (8X8 in our case) is built using a keyword
     * Plaintext is split into letter pairs (e.g., ME ET YO UR).
     * Encryption rules depend on the positions of the letters in the matrix:
     *     Same row: replace each letter with the one to its right.
     *     Same column: replace each with the one below.
     *     Rectangle: replace each letter with the one in its own row but in
     *     the column of the other letter in the pair.
     * If the text has an odd length, 'X' is appended to complete the last pair.
     *
     * @param plainText the (uppercase) text to encrypt
     * @param key       the keyword used to build the 8x8 matrix
     * @return the encrypted text, or
     *         "The selected string is not in bounds, Try again." if the text
     *         or the key is out of bounds
     */
    public static String playfairEncryption(String plainText, String key) {

        if (isStringInBounds(plainText) && isStringInBounds(key)) {

            char[][] table = buildTable(key);

            String encription = "";
            for (int i = 0; i < plainText.length(); i++) {

                // Take the next pair; pad with 'X' if one character is left
                char char1 = plainText.charAt(i);
                i++;
                char char2;
                if (i == plainText.length()) {
                    char2 = 'X';
                } else {
                    char2 = plainText.charAt(i);
                }

                int[] char1index = localization(table, char1);
                int[] char2index = localization(table, char2);

                if (char1index[0] == char2index[0]) {
                    // Same row: move each character one step to the right
                    char encripted1;
                    char encripted2;

                    if (7 == char2index[1] && char1index[1] == 7) {
                        char1index[1] = 0;
                        char2index[1] = 0;
                        encripted1 = table[char1index[0]][char1index[1]];
                        encripted2 = table[char2index[0]][char2index[1]];
                        encription += encripted1;
                        encription += encripted2;
                    } else if (char1index[1] == 7) {
                        char1index[1] = 0;
                        encripted1 = table[char1index[0]][char1index[1]];
                        encripted2 = table[char2index[0]][char2index[1] + 1];
                        encription += encripted1;
                        encription += encripted2;
                    } else if (char2index[1] == 7) {
                        char2index[1] = 0;
                        encripted1 = table[char1index[0]][char1index[1] + 1];
                        encripted2 = table[char2index[0]][char2index[1]];
                        encription += encripted1;
                        encription += encripted2;
                    } else {
                        encripted1 = table[char1index[0]][char1index[1] + 1];
                        encripted2 = table[char2index[0]][char2index[1] + 1];
                        encription += encripted1;
                        encription += encripted2;
                    }

                } else if (char1index[1] == char2index[1]) {
                    // Same column: move each character one step down
                    char encripted1;
                    char encripted2;

                    if (7 == char2index[0] && char1index[0] == 7) {
                        char1index[0] = 0;
                        char2index[0] = 0;
                        encripted1 = table[char1index[0]][char1index[1]];
                        encripted2 = table[char2index[0]][char2index[1]];
                        encription += encripted1;
                        encription += encripted2;
                    } else if (char1index[0] == 7) {
                        char1index[0] = 0;
                        encripted1 = table[char1index[0]][char1index[1]];
                        encripted2 = table[char2index[0] + 1][char2index[1]];
                        encription += encripted1;
                        encription += encripted2;
                    } else if (char2index[0] == 7) {
                        char2index[0] = 0;
                        encripted1 = table[char1index[0] + 1][char1index[1]];
                        encripted2 = table[char2index[0]][char2index[1]];
                        encription += encripted1;
                        encription += encripted2;
                    } else {
                        encripted1 = table[char1index[0] + 1][char1index[1]];
                        encripted2 = table[char2index[0] + 1][char2index[1]];
                        encription += encripted1;
                        encription += encripted2;
                    }

                } else if (char1index[0] != char2index[0] && char1index[1] != char2index[1]) {
                    // Rectangle: swap the columns of the two characters
                    char encripted1 = table[char1index[0]][char2index[1]];
                    char encripted2 = table[char2index[0]][char1index[1]];

                    encription += encripted1;
                    encription += encripted2;
                }
            }
            return encription;

        } else {
            return "The selected string is not in bounds, Try again.";
        }
    }

    /**
     * Builds the 8x8 Playfair matrix. The characters of the key (each one only
     * once, in order) come first, followed by the remaining characters of the
     * 64-character alphabet, filling the matrix left to right, top to bottom.
     *
     * @param key the keyword used to build the matrix
     * @return an 8x8 matrix containing each of the 64 characters exactly once
     */
    public static char[][] buildTable(String key) {
        String alphabetTable = ALPHABET64;

        // Walk the key backwards, moving each key character to the front;
        // the first key character ends up first and duplicates collapse
        for (int i = key.length() - 1; i > -1; i--) {
            int charindex = alphabetTable.indexOf(key.charAt(i));
            String beforei = alphabetTable.substring(0, charindex);
            String afteri = alphabetTable.substring(1 + charindex);
            alphabetTable = alphabetTable.charAt(charindex) + beforei + afteri;
        }

        // Fill the matrix left to right, top to bottom
        char[][] table = new char[8][8];
        int counter = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = alphabetTable.charAt(counter++);
            }
        }
        return table;
    }

    /**
     * Finds the position of a character inside a matrix.
     *
     * @param array     the matrix to search
     * @param character the character to look for
     * @return a two-element array {row, column}; {0, 0} if it is not found
     */
    public static int[] localization(char[][] array, char character) {
        int[] n = new int[2];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (character == array[i][j]) {
                    n[0] = i;
                    n[1] = j;
                    return n;
                }
            }
        }
        return n;
    }

    /**
     * Decrypts a string that was encrypted with the Playfair Cipher.
     * This is the inverse of playfairEncryption: same row moves left, same
     * column moves up, rectangle swaps columns. A trailing 'X' (the padding
     * added by the encryption) is removed.
     *
     * @param encryptedText the encrypted text
     * @param key           the keyword used to build the 8x8 matrix
     * @return the original plain text, or
     *         "The selected string is not in bounds, Try again." if the text
     *         or the key is out of bounds
     */
    public static String playfairDecryption(String encryptedText, String key) {

        if (isStringInBounds(encryptedText) && isStringInBounds(key)) {

            char[][] table = buildTable(key);

            String decryption = "";
            for (int i = 0; i < encryptedText.length(); i++) {

                // Take the next pair; ignore a single leftover character
                char char1 = encryptedText.charAt(i);
                i++;
                char char2;
                if (i == encryptedText.length()) {
                    break;
                } else {
                    char2 = encryptedText.charAt(i);
                }

                int[] char1index = localization(table, char1);
                int[] char2index = localization(table, char2);

                if (char1index[0] == char2index[0]) {
                    // Same row: move each character one step to the left
                    char encripted1;
                    char encripted2;

                    if (0 == char2index[1] && char1index[1] == 0) {
                        char1index[1] = 7;
                        char2index[1] = 7;
                        encripted1 = table[char1index[0]][char1index[1]];
                        encripted2 = table[char2index[0]][char2index[1]];
                        decryption += encripted1;
                        decryption += encripted2;
                    } else if (char1index[1] == 0) {
                        char1index[1] = 7;
                        encripted1 = table[char1index[0]][char1index[1]];
                        encripted2 = table[char2index[0]][char2index[1] - 1];
                        decryption += encripted1;
                        decryption += encripted2;
                    } else if (char2index[1] == 0) {
                        char2index[1] = 7;
                        encripted1 = table[char1index[0]][char1index[1] - 1];
                        encripted2 = table[char2index[0]][char2index[1]];
                        decryption += encripted1;
                        decryption += encripted2;
                    } else {
                        encripted1 = table[char1index[0]][char1index[1] - 1];
                        encripted2 = table[char2index[0]][char2index[1] - 1];
                        decryption += encripted1;
                        decryption += encripted2;
                    }

                } else if (char1index[1] == char2index[1]) {
                    // Same column: move each character one step up
                    char encripted1;
                    char encripted2;

                    if (0 == char2index[0] && char1index[0] == 0) {
                        char1index[0] = 7;
                        char2index[0] = 7;
                        encripted1 = table[char1index[0]][char1index[1]];
                        encripted2 = table[char2index[0]][char2index[1]];
                        decryption += encripted1;
                        decryption += encripted2;
                    } else if (char1index[0] == 0) {
                        char1index[0] = 7;
                        encripted1 = table[char1index[0]][char1index[1]];
                        encripted2 = table[char2index[0] - 1][char2index[1]];
                        decryption += encripted1;
                        decryption += encripted2;
                    } else if (char2index[0] == 0) {
                        char2index[0] = 7;
                        encripted1 = table[char1index[0] - 1][char1index[1]];
                        encripted2 = table[char2index[0]][char2index[1]];
                        decryption += encripted1;
                        decryption += encripted2;
                    } else {
                        encripted1 = table[char1index[0] - 1][char1index[1]];
                        encripted2 = table[char2index[0] - 1][char2index[1]];
                        decryption += encripted1;
                        decryption += encripted2;
                    }

                } else if (char1index[0] != char2index[0] && char1index[1] != char2index[1]) {
                    // Rectangle: swap the columns of the two characters
                    char encripted1 = table[char1index[0]][char2index[1]];
                    char encripted2 = table[char2index[0]][char1index[1]];

                    decryption += encripted1;
                    decryption += encripted2;
                }
            }

            // Remove the 'X' that was added to complete the last pair
            if (!(decryption.isEmpty()) && decryption.charAt(decryption.length() - 1) == 'X') {
                String before = decryption.substring(0, decryption.length() - 1);
                decryption = before;
            }
            return decryption;

        } else {
            return "The selected string is not in bounds, Try again.";
        }
    }

    /**
     * Caesar Cipher is a simple substitution cipher that replaces each letter in a message
     * with a letter some fixed number of positions down the alphabet.
     * For example, with a shift of 3, 'A' would become 'D', 'B' would become 'E', and so on.
     *
     * @param plainText the (uppercase) text to encrypt
     * @param key       the number of positions to shift each character
     * @return the encrypted text, or
     *         "The selected string is not in bounds, Try again." if the text
     *         is out of bounds
     */
    public static String caesarEncryption(String plainText, int key) {

        if (isStringInBounds(plainText)) {
            String encription = "";
            for (int i = 0; i < plainText.length(); i++) {
                // Shift, then wrap inside the range LOWER_RANGE..UPPER_RANGE
                int characterandkey = plainText.charAt(i) + key;
                int position = characterandkey - (int) LOWER_RANGE;
                int encripted = Math.floorMod(position, RANGE) + LOWER_RANGE;
                encription += (char) encripted;
            }
            return encription;

        } else {
            return "The selected string is not in bounds, Try again.";
        }
    }

    /**
     * Decrypts a string that was encrypted with the Caesar Cipher.
     * This is the inverse of caesarEncryption.
     *
     * @param encryptedText the encrypted text
     * @param key           the number of positions used to encrypt the original text
     * @return the original plain text, or
     *         "The selected string is not in bounds, Try again." if the text
     *         is out of bounds
     */
    public static String caesarDecryption(String encryptedText, int key) {

        if (isStringInBounds(encryptedText)) {
            String decryption = "";
            for (int i = 0; i < encryptedText.length(); i++) {
                // Shift back, then wrap inside the range LOWER_RANGE..UPPER_RANGE
                int characteranddecriptyon = encryptedText.charAt(i) - key;
                int position = characteranddecriptyon - (int) LOWER_RANGE;
                int decryptionf = Math.floorMod(position, RANGE) + LOWER_RANGE;
                decryption += (char) decryptionf;
            }
            return decryption;

        } else {
            return "The selected string is not in bounds, Try again.";
        }
    }
}
