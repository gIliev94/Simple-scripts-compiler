package testdata;

import java.util.Set;

/**
 * Test data for command and command format validations.
 * 
 * @author Georgi Iliev
 *
 */
public class CommandsTestData {

    private CommandsTestData() {
    }

    /**
     * Loads a collection of VALID command keywords, that are not case
     * sensitive.
     * 
     * @param validCommands
     */
    public static void loadValidCommandKeywords(Set<String> validCommands) {
	validCommands.add("line");
	validCommands.add("point");
	validCommands.add("move");
	validCommands.add("click");
	validCommands.add("delay");
	validCommands.add("press");
	validCommands.add("open");
	validCommands.add("text");
    }

    /**
     * Loads a collection of INVALID command keywords, that are not case
     * sensitive.
     * 
     * @param invalidCommands
     */
    public static void loadInvalidCommandKeywords(Set<String> invalidCommands) {
	invalidCommands.add("mock");
	invalidCommands.add("hover");
	invalidCommands.add("moovit");
	invalidCommands.add("terrorize");
	invalidCommands.add("crouch");
	invalidCommands.add("stay");
	invalidCommands.add("alter");
	invalidCommands.add("type");
    }

    /**
     * Loads a collection of VALID command statements, that are not case
     * sensitive.
     * 
     * @param validStatements
     */
    public static void loadValidStatements(Set<String> validStatements) {
	validStatements.add("line #FF0000 400 400 320 400 0");
	validStatements.add("point #FFFFFF 320 240");
	validStatements.add("delay 3000");
	validStatements.add("open cmd.exe");
	validStatements.add("press CTRL+S");
	validStatements.add("click left");
	validStatements.add("move 1700 750");
	validStatements.add("text It`s alright.");
    }

    /**
     * Loads a collection of INVALID command statements, that are not case
     * sensitive.
     * 
     * @param invalidStatements
     */
    public static void loadInvalidStatements(Set<String> invalidStatements) {
	invalidStatements.add("line FF0000 400 400 320 400 0");
	invalidStatements.add("point #FFFFFF 320");
	invalidStatements.add("delay3 3000");
	invalidStatements.add("cmd.exe");
	invalidStatements.add("press CTRL+S twice");
	invalidStatements.add("click 3");
	invalidStatements.add("move 1700 750 200");
	invalidStatements.add("text ™This is invalid.☺");
    }

}
