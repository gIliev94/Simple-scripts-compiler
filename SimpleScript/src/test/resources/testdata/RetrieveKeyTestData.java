package testdata;

import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * Test data for retrievement of key code - expected mappings for keys.
 * 
 * @author Georgi Iliev
 *
 */
public class RetrieveKeyTestData {

    private RetrieveKeyTestData() {
    }

    /**
     * Loads a collection of EXPECTED key mappings(key -> code ).
     * 
     * @param keyMappings
     */
    public static void loadExpectedKeyMappings(Map<Character, Integer> keyMappings) {
	keyMappings.put('A', KeyEvent.VK_A);
	keyMappings.put(' ', KeyEvent.VK_SPACE);
	keyMappings.put('0', KeyEvent.VK_0);
	keyMappings.put('=', KeyEvent.VK_EQUALS);
	keyMappings.put('/', KeyEvent.VK_SLASH);
    }
}
