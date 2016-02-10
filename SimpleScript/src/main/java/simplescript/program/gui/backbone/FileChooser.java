package simplescript.program.gui.backbone;

import java.awt.Dimension;
import java.io.File;
import java.util.UUID;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import simplescript.program.gui.ISimpleScriptComponent;
import simplescript.program.utilities.ComponentMetrics;

/**
 * JFileChooser implementation for "OPEN" button.
 * 
 * @author Georgi Iliev
 *
 */
public class FileChooser extends JFileChooser implements ISimpleScriptComponent {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public FileChooser(FileFilter filter, ComponentMetrics metrics) {
	this.setPreferredSize(new Dimension(metrics.width, metrics.height - 40));
	this.addChoosableFileFilter(filter);
	this.setFileFilter(filter);
	setAdditionalAttributes();
    }

    public void setAdditionalAttributes() {
	File myComputer = this.getFileSystemView().getParentDirectory(new File("C:\\"));
	File desktop = this.getFileSystemView().getParentDirectory(myComputer);
	this.setCurrentDirectory(desktop);
	this.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

}
