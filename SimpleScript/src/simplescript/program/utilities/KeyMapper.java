package simplescript.program.utilities;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import simplescript.configurator.ConfigurationConstants;

/**
 * Utility class - handles keyboard character mappings
 * 
 * @author Georgi Iliev
 *
 */
public class KeyMapper {

    /**
     * The local keyboard`s key mappings.
     */
    private static final Map<Character, Integer> keyboardDictionary = populateKeyDictionary();

    /**
     * <h1><i>retrieveKey</i></h1>
     * <p>
     * <p>
     * {@code public static int retrieveKey(char key)}
     * </p>
     * Finds the key mapping value of a key and returns it.</p>
     * 
     * @param key
     *            - the key to be pressed.
     * @return An integer value of the keyboard bound key.
     * @throws IllegalArgumentException
     */
    public static int retrieveKey(char key) throws IllegalArgumentException {
	Integer keyCode = (Integer) keyboardDictionary.get(new Character(key));

	if (keyCode == null) {
	    throw new IllegalArgumentException(ConfigurationConstants.NEWLINE + "Cannot type character: [" + key
		    + "] with this keyboard!");
	}

	return keyCode.intValue();
    }

    /**
     * <h1><i>populateKeyDictionary</i></h1>
     * <p>
     * <p>
     * {@code private static Map<Character, Integer> populateKeyDictionary()}
     * </p>
     * Populates a dictionary containing the local keyboard mappings.</p>
     * 
     * @return
     */
    private static Map<Character, Integer> populateKeyDictionary() {
	Map<Character, Integer> keyDictionary = new HashMap<Character, Integer>(128);

	keyDictionary.put(new Character('a'), new Integer(KeyEvent.VK_A));
	keyDictionary.put(new Character('b'), new Integer(KeyEvent.VK_B));
	keyDictionary.put(new Character('c'), new Integer(KeyEvent.VK_C));
	keyDictionary.put(new Character('d'), new Integer(KeyEvent.VK_D));
	keyDictionary.put(new Character('e'), new Integer(KeyEvent.VK_E));
	keyDictionary.put(new Character('f'), new Integer(KeyEvent.VK_F));
	keyDictionary.put(new Character('g'), new Integer(KeyEvent.VK_G));
	keyDictionary.put(new Character('h'), new Integer(KeyEvent.VK_H));
	keyDictionary.put(new Character('i'), new Integer(KeyEvent.VK_I));
	keyDictionary.put(new Character('j'), new Integer(KeyEvent.VK_J));
	keyDictionary.put(new Character('k'), new Integer(KeyEvent.VK_K));
	keyDictionary.put(new Character('l'), new Integer(KeyEvent.VK_L));
	keyDictionary.put(new Character('m'), new Integer(KeyEvent.VK_M));
	keyDictionary.put(new Character('n'), new Integer(KeyEvent.VK_N));
	keyDictionary.put(new Character('o'), new Integer(KeyEvent.VK_O));
	keyDictionary.put(new Character('p'), new Integer(KeyEvent.VK_P));
	keyDictionary.put(new Character('q'), new Integer(KeyEvent.VK_Q));
	keyDictionary.put(new Character('r'), new Integer(KeyEvent.VK_R));
	keyDictionary.put(new Character('s'), new Integer(KeyEvent.VK_S));
	keyDictionary.put(new Character('t'), new Integer(KeyEvent.VK_T));
	keyDictionary.put(new Character('u'), new Integer(KeyEvent.VK_U));
	keyDictionary.put(new Character('v'), new Integer(KeyEvent.VK_V));
	keyDictionary.put(new Character('w'), new Integer(KeyEvent.VK_W));
	keyDictionary.put(new Character('x'), new Integer(KeyEvent.VK_X));
	keyDictionary.put(new Character('y'), new Integer(KeyEvent.VK_Y));
	keyDictionary.put(new Character('z'), new Integer(KeyEvent.VK_Z));
	keyDictionary.put(new Character('A'), new Integer(KeyEvent.VK_A));
	keyDictionary.put(new Character('B'), new Integer(KeyEvent.VK_B));
	keyDictionary.put(new Character('C'), new Integer(KeyEvent.VK_C));
	keyDictionary.put(new Character('D'), new Integer(KeyEvent.VK_D));
	keyDictionary.put(new Character('E'), new Integer(KeyEvent.VK_E));
	keyDictionary.put(new Character('F'), new Integer(KeyEvent.VK_F));
	keyDictionary.put(new Character('G'), new Integer(KeyEvent.VK_G));
	keyDictionary.put(new Character('H'), new Integer(KeyEvent.VK_H));
	keyDictionary.put(new Character('I'), new Integer(KeyEvent.VK_I));
	keyDictionary.put(new Character('J'), new Integer(KeyEvent.VK_J));
	keyDictionary.put(new Character('K'), new Integer(KeyEvent.VK_K));
	keyDictionary.put(new Character('L'), new Integer(KeyEvent.VK_L));
	keyDictionary.put(new Character('M'), new Integer(KeyEvent.VK_M));
	keyDictionary.put(new Character('N'), new Integer(KeyEvent.VK_N));
	keyDictionary.put(new Character('O'), new Integer(KeyEvent.VK_O));
	keyDictionary.put(new Character('P'), new Integer(KeyEvent.VK_P));
	keyDictionary.put(new Character('Q'), new Integer(KeyEvent.VK_Q));
	keyDictionary.put(new Character('R'), new Integer(KeyEvent.VK_R));
	keyDictionary.put(new Character('S'), new Integer(KeyEvent.VK_S));
	keyDictionary.put(new Character('T'), new Integer(KeyEvent.VK_T));
	keyDictionary.put(new Character('U'), new Integer(KeyEvent.VK_U));
	keyDictionary.put(new Character('V'), new Integer(KeyEvent.VK_V));
	keyDictionary.put(new Character('W'), new Integer(KeyEvent.VK_W));
	keyDictionary.put(new Character('X'), new Integer(KeyEvent.VK_X));
	keyDictionary.put(new Character('Y'), new Integer(KeyEvent.VK_Y));
	keyDictionary.put(new Character('Z'), new Integer(KeyEvent.VK_Z));
	keyDictionary.put(new Character(' '), new Integer(KeyEvent.VK_SPACE));
	keyDictionary.put(new Character('.'), new Integer(KeyEvent.VK_PERIOD));
	keyDictionary.put(new Character(','), new Integer(KeyEvent.VK_COMMA));
	keyDictionary.put(new Character('`'), new Integer(KeyEvent.VK_BACK_QUOTE));
	keyDictionary.put(new Character('!'), new Integer(KeyEvent.VK_EXCLAMATION_MARK));
	keyDictionary.put(new Character(':'), new Integer(KeyEvent.VK_COLON));
	keyDictionary.put(new Character(';'), new Integer(KeyEvent.VK_SEMICOLON));
	keyDictionary.put(new Character('@'), new Integer(KeyEvent.VK_AT));
	keyDictionary.put(new Character('#'), new Integer(KeyEvent.VK_NUMBER_SIGN));
	keyDictionary.put(new Character('$'), new Integer(KeyEvent.VK_DOLLAR));
	keyDictionary.put(new Character('_'), new Integer(KeyEvent.VK_UNDERSCORE));
	keyDictionary.put(new Character('+'), new Integer(KeyEvent.VK_PLUS));
	keyDictionary.put(new Character('-'), new Integer(KeyEvent.VK_MINUS));
	keyDictionary.put(new Character('/'), new Integer(KeyEvent.VK_SLASH));
	keyDictionary.put(new Character('\\'), new Integer(KeyEvent.VK_BACK_SLASH));
	keyDictionary.put(new Character('0'), new Integer(KeyEvent.VK_0));
	keyDictionary.put(new Character('1'), new Integer(KeyEvent.VK_1));
	keyDictionary.put(new Character('2'), new Integer(KeyEvent.VK_2));
	keyDictionary.put(new Character('3'), new Integer(KeyEvent.VK_3));
	keyDictionary.put(new Character('4'), new Integer(KeyEvent.VK_4));
	keyDictionary.put(new Character('5'), new Integer(KeyEvent.VK_5));
	keyDictionary.put(new Character('6'), new Integer(KeyEvent.VK_6));
	keyDictionary.put(new Character('7'), new Integer(KeyEvent.VK_7));
	keyDictionary.put(new Character('8'), new Integer(KeyEvent.VK_8));
	keyDictionary.put(new Character('9'), new Integer(KeyEvent.VK_9));

	return keyDictionary;
    }
}
