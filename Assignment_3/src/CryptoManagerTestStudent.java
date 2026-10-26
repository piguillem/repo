/*
 * Class: CMSC203
 * Instructor: Gary Thai
 * Description: JUnit 5 tests for every public method of CryptoManager:
 *              isStringInBounds, vigenereEncryption, vigenereDecryption,
 *              playfairEncryption, playfairDecryption, caesarEncryption,
 *              caesarDecryption, buildTable and localization.
 * Due: 10/25/26
 * Platform/compiler: Eclipse IDE / Java (JDK)
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Santiago Piguillem
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class CryptoManagerTestStudent {

    private static final String OUT_OF_BOUNDS = "The selected string is not in bounds, Try again.";
    private static final String KEY_MONTGOMERY = "MONTGOMERY";

    /*
     * Matrix built from the key MONTGOMERY (used in several Playfair tests):
     *   Row0: M O N T G E R Y
     *   Row1: A B C D F H I J
     *   Row2: K L P Q S U V W
     *   Row3: X Z 0 1 2 3 4 5
     *   Row4: 6 7 8 9 (space) ! " #
     *   Row5: $ % & ' ( ) * +
     *   Row6: , - . / : ; < =
     *   Row7: > ? @ [ \ ] ^ _
     */

    /** Prints the programmer's name at the end of the test output. */
    @AfterAll
    public static void printProgrammerName() {
        System.out.println();
        System.out.println("Programmer: Santiago Piguillem");
    }

    // ---------------------------------------------------------------
    // isStringInBounds
    // ---------------------------------------------------------------

    @Test
    public void testIsStringInBoundsValidSymbolsAndDigits() {
        assertTrue(CryptoManager.isStringInBounds("ROOM 12B, FLOOR #3!"));
    }

    @Test
    public void testIsStringInBoundsEmptyString() {
        assertTrue(CryptoManager.isStringInBounds(""));
    }

    @Test
    public void testIsStringInBoundsLowerCaseIsOutside() {
        assertFalse(CryptoManager.isStringInBounds("Maryland"));
    }

    @Test
    public void testIsStringInBoundsBadCharacterAtTheEnd() {
        assertFalse(CryptoManager.isStringInBounds("ALMOST OK{"));
    }

    // ---------------------------------------------------------------
    // Vigenere
    // ---------------------------------------------------------------

    @Test
    public void testVigenereEncryptionKnownValue() {
        // M (12) + D (3) = 15 -> P
        assertEquals("P", CryptoManager.vigenereEncryption("M", "D"));
        assertEquals("LX5OPV$M7-OE81", CryptoManager.vigenereEncryption("ATTACK AT DAWN", "LEMON"));
    }

    @Test
    public void testVigenereEncryptionWrapsPastTheEndOfTheAlphabet() {
        // '_' is index 63, 'B' is index 1: 64 mod 64 = 0 -> 'A'
        assertEquals("A", CryptoManager.vigenereEncryption("_", "B"));
    }

    @Test
    public void testVigenereEncryptionKeyLongerThanText() {
        // H (7) + S (18) = 25 -> Z ; I (8) + E (4) = 12 -> M
        assertEquals("ZM", CryptoManager.vigenereEncryption("HI", "SECRETKEY"));
    }

    @Test
    public void testVigenereEncryptionOutOfBoundsText() {
        assertEquals(OUT_OF_BOUNDS, CryptoManager.vigenereEncryption("BAD~TEXT", "KEY"));
    }

    @Test
    public void testVigenereEncryptionOutOfBoundsKey() {
        assertEquals(OUT_OF_BOUNDS, CryptoManager.vigenereEncryption("GOOD TEXT", "k3y"));
    }

    @Test
    public void testVigenereEncryptionEmptyKeyDoesNotEncrypt() {
        String result = CryptoManager.vigenereEncryption("HELLO", "");
        assertNotEquals("HELLO", result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testVigenereDecryptionKnownValue() {
        // P (15) - D (3) = 12 -> M
        assertEquals("M", CryptoManager.vigenereDecryption("P", "D"));
        assertEquals("ATTACK AT DAWN", CryptoManager.vigenereDecryption("LX5OPV$M7-OE81", "LEMON"));
    }

    @Test
    public void testVigenereDecryptionWrapsBelowZero() {
        // 'A' is index 0, 'B' is index 1: -1 mod 64 = 63 -> '_'
        assertEquals("_", CryptoManager.vigenereDecryption("A", "B"));
    }

    @Test
    public void testVigenereDecryptionOutOfBounds() {
        assertEquals(OUT_OF_BOUNDS, CryptoManager.vigenereDecryption("bad text", "KEY"));
    }

    @Test
    public void testVigenereRoundTripWithDigitsAndSymbols() {
        String text = "CAMPUS 2026";
        String encrypted = CryptoManager.vigenereEncryption(text, "RAMS");
        assertEquals("TAY7!S,*'2(", encrypted);
        assertEquals(text, CryptoManager.vigenereDecryption(encrypted, "RAMS"));
    }

    // ---------------------------------------------------------------
    // Playfair
    // ---------------------------------------------------------------

    @Test
    public void testPlayfairEncryptionSameRow() {
        // M (0,0) and E (0,5): each moves one to the right
        assertEquals("OR", CryptoManager.playfairEncryption("ME", KEY_MONTGOMERY));
    }

    @Test
    public void testPlayfairEncryptionSameRowWrapsAround() {
        // R (0,6) -> Y (0,7); Y (0,7) wraps to M (0,0)
        assertEquals("YM", CryptoManager.playfairEncryption("RY", KEY_MONTGOMERY));
    }

    @Test
    public void testPlayfairEncryptionSameColumn() {
        // M (0,0) -> A (1,0); A (1,0) -> K (2,0)
        assertEquals("AK", CryptoManager.playfairEncryption("MA", KEY_MONTGOMERY));
    }

    @Test
    public void testPlayfairEncryptionSameColumnWrapsAround() {
        // K (2,0) -> X (3,0); '>' (7,0) wraps to M (0,0)
        assertEquals("XM", CryptoManager.playfairEncryption("K>", KEY_MONTGOMERY));
    }

    @Test
    public void testPlayfairEncryptionRectangle() {
        // U (2,5) and X (3,0) -> K (2,0) and 3 (3,5)
        assertEquals("K3", CryptoManager.playfairEncryption("UX", KEY_MONTGOMERY));
    }

    @Test
    public void testPlayfairEncryptionRepeatedLetterPair() {
        // Both O's are the same cell: each moves right to N
        assertEquals("NN", CryptoManager.playfairEncryption("OO", KEY_MONTGOMERY));
    }

    @Test
    public void testPlayfairEncryptionOddLengthIsPaddedWithX() {
        // Example from the assignment clarification: ME ET YO UX -> OR RG MN K3
        assertEquals("ORRGMNK3", CryptoManager.playfairEncryption("MEETYOU", KEY_MONTGOMERY));
    }

    @Test
    public void testPlayfairEncryptionOutOfBounds() {
        assertEquals(OUT_OF_BOUNDS, CryptoManager.playfairEncryption("NO {BRACES}", "TERP"));
        assertEquals(OUT_OF_BOUNDS, CryptoManager.playfairEncryption("FINE", "ter|p"));
    }

    @Test
    public void testPlayfairDecryptionKnownValues() {
        assertEquals("MEETYOU", CryptoManager.playfairDecryption("ORRGMNK3", KEY_MONTGOMERY));
        assertEquals("ME", CryptoManager.playfairDecryption("OR", KEY_MONTGOMERY));
    }

    @Test
    public void testPlayfairDecryptionWrapsAround() {
        // Inverse of the wrap-around encryptions above
        assertEquals("RY", CryptoManager.playfairDecryption("YM", KEY_MONTGOMERY));
        assertEquals("K>", CryptoManager.playfairDecryption("XM", KEY_MONTGOMERY));
    }

    @Test
    public void testPlayfairDecryptionRectangle() {
        // D (1,3) and M (0,0) -> A (1,0) and T (0,3)
        assertEquals("AT", CryptoManager.playfairDecryption("DM", KEY_MONTGOMERY));
    }

    @Test
    public void testPlayfairDecryptionOutOfBounds() {
        assertEquals(OUT_OF_BOUNDS, CryptoManager.playfairDecryption("lower", "TERP"));
    }

    @Test
    public void testPlayfairRoundTripWithSpacesAndDigits() {
        String text = "CODE 101";
        String encrypted = CryptoManager.playfairEncryption(text, "TERP");
        assertEquals("EWTR9212", encrypted);
        assertEquals(text, CryptoManager.playfairDecryption(encrypted, "TERP"));
    }

    @Test
    public void testPlayfairRoundTripPhraseWithOddLength() {
        String text = "HELLO WORLD"; // 11 characters, so an 'X' is added then removed
        String encrypted = CryptoManager.playfairEncryption(text, "SECURITY");
        assertEquals("BIMMP9LPSPA0", encrypted);
        assertEquals(text, CryptoManager.playfairDecryption(encrypted, "SECURITY"));
    }

    // ---------------------------------------------------------------
    // buildTable and localization (public helper methods)
    // ---------------------------------------------------------------

    @Test
    public void testBuildTableStartsWithKeyWithoutDuplicates() {
        char[][] table = CryptoManager.buildTable(KEY_MONTGOMERY);
        assertEquals("MONTGERY", new String(table[0]));
        assertEquals("ABCDFHIJ", new String(table[1]));
        assertEquals(">?@[\\]^_", new String(table[7]));
    }

    @Test
    public void testBuildTableWithEmptyKeyIsTheAlphabetInOrder() {
        char[][] table = CryptoManager.buildTable("");
        assertEquals("ABCDEFGH", new String(table[0]));
        assertEquals('_', table[7][7]);
    }

    @Test
    public void testBuildTableContainsEachCharacterExactlyOnce() {
        char[][] table = CryptoManager.buildTable("BANANA 7");
        StringBuilder all = new StringBuilder();
        for (char[] row : table) {
            all.append(row);
        }
        assertEquals(64, all.length());
        for (char c = ' '; c <= '_'; c++) {
            assertEquals(all.indexOf(String.valueOf(c)), all.lastIndexOf(String.valueOf(c)),
                    "Character appears more than once: " + c);
            assertTrue(all.indexOf(String.valueOf(c)) >= 0, "Character is missing: " + c);
        }
    }

    @Test
    public void testLocalizationFindsRowAndColumn() {
        char[][] table = CryptoManager.buildTable(KEY_MONTGOMERY);
        assertArrayEquals(new int[] {0, 0}, CryptoManager.localization(table, 'M'));
        assertArrayEquals(new int[] {0, 5}, CryptoManager.localization(table, 'E'));
        assertArrayEquals(new int[] {2, 5}, CryptoManager.localization(table, 'U'));
        assertArrayEquals(new int[] {4, 4}, CryptoManager.localization(table, ' '));
        assertArrayEquals(new int[] {7, 7}, CryptoManager.localization(table, '_'));
    }

    // ---------------------------------------------------------------
    // Caesar
    // ---------------------------------------------------------------

    @Test
    public void testCaesarEncryptionKnownValues() {
        assertEquals("E", CryptoManager.caesarEncryption("A", 4));
        assertEquals("H[[HJR'H['KH^U", CryptoManager.caesarEncryption("ATTACK AT DAWN", 7));
    }

    @Test
    public void testCaesarEncryptionNegativeKey() {
        assertEquals("C@GGJ", CryptoManager.caesarEncryption("HELLO", -5));
    }

    @Test
    public void testCaesarEncryptionWrapsAroundTheRange() {
        // '^' (94) + 2 wraps to ' ' (32); '_' (95) + 2 wraps to '!' (33)
        assertEquals(" !", CryptoManager.caesarEncryption("^_", 2));
    }

    @Test
    public void testCaesarEncryptionKeyLargerThanRange() {
        assertEquals("ABC", CryptoManager.caesarEncryption("ABC", 64));
        assertEquals("%&'", CryptoManager.caesarEncryption("ABC", 100));
        assertEquals("]^_", CryptoManager.caesarEncryption("ABC", -100));
    }

    @Test
    public void testCaesarEncryptionOutOfBounds() {
        assertEquals(OUT_OF_BOUNDS, CryptoManager.caesarEncryption("no lower case", 3));
    }

    @Test
    public void testCaesarDecryptionKnownValues() {
        assertEquals("A", CryptoManager.caesarDecryption("E", 4));
        assertEquals("ATTACK AT DAWN", CryptoManager.caesarDecryption("H[[HJR'H['KH^U", 7));
    }

    @Test
    public void testCaesarDecryptionWrapsBelowTheRange() {
        // ' ' (32) - 2 wraps to '^' (94); '!' (33) - 2 wraps to '_' (95)
        assertEquals("^_", CryptoManager.caesarDecryption(" !", 2));
    }

    @Test
    public void testCaesarDecryptionOutOfBounds() {
        assertEquals(OUT_OF_BOUNDS, CryptoManager.caesarDecryption("~tilde~", 3));
    }

    @Test
    public void testCaesarRoundTripWithManyKeys() {
        String text = "TERP 2026: GO!";
        for (int key = -130; key <= 130; key += 13) {
            assertEquals(text, CryptoManager.caesarDecryption(CryptoManager.caesarEncryption(text, key), key));
        }
    }
}
