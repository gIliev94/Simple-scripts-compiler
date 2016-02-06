package simplescript.program.gui.backbone;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * FileFilter implementation for "OPEN" button - restricts the accepted file
 * types to a plain text file.
 * 
 * @author Georgi Iliev
 *
 */
public class TxtFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
	return file.getName().toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() {
	return "Text Documents (*.txt)";
    }

}
