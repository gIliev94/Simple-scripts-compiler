package testdata;

import java.util.Map;

import simplescript.language.scripType.Keywords;
import simplescript.language.scripType.processors.ClickProcessor;
import simplescript.language.scripType.processors.CommandProcessor;
import simplescript.language.scripType.processors.DelayProcessor;
import simplescript.language.scripType.processors.LineProcessor;
import simplescript.language.scripType.processors.MoveProcessor;
import simplescript.language.scripType.processors.OpenProcessor;
import simplescript.language.scripType.processors.PointProcessor;
import simplescript.language.scripType.processors.PressProcessor;
import simplescript.language.scripType.processors.TextProcessor;

/**
 * Test data for dispatchment - expected processors for every command keyword.
 * 
 * @author Georgi Iliev
 *
 */
public class DispatchmentTestData {

    private DispatchmentTestData() {
    }

    /**
     * Loads a collection of EXPECTED dispatchment test data.
     * 
     * @param expectedDispatchment
     */
    public static void loadExpectedDispatchment(Map<String, CommandProcessor> expectedDispatchment) {
	expectedDispatchment.put(Keywords.LINE, new LineProcessor(null, null));
	expectedDispatchment.put(Keywords.POINT, new PointProcessor(null, null));
	expectedDispatchment.put(Keywords.MOVE, new MoveProcessor(null));
	expectedDispatchment.put(Keywords.CLICK, new ClickProcessor(null));
	expectedDispatchment.put(Keywords.DELAY, new DelayProcessor(null));
	expectedDispatchment.put(Keywords.OPEN, new OpenProcessor(null));
	expectedDispatchment.put(Keywords.PRESS, new PressProcessor(null));
	expectedDispatchment.put(Keywords.TEXT, new TextProcessor(null));
    }

}
