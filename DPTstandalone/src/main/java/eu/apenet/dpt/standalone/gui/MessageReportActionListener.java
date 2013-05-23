/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.apenet.dpt.standalone.gui;

import eu.apenet.dpt.standalone.gui.db.RetrieveFromDb;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.JList;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Stefan Papp
 */
class MessageReportActionListener implements ActionListener {

    private RetrieveFromDb retrieveFromDb;
    private JList xmlEadList;
    private Map<String, FileInstance> fileInstances;
    private ResourceBundle labels;
    private final Component parent;

    MessageReportActionListener(RetrieveFromDb retrieveFromDb, JList xmlEadList, Map<String, FileInstance> fileInstances, ResourceBundle labels, Component parent) {
        this.retrieveFromDb = retrieveFromDb;
        this.xmlEadList = xmlEadList;
        this.fileInstances = fileInstances;
        this.labels = labels;
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        String defaultOutputDirectory = retrieveFromDb.retrieveDefaultSaveFolder();
        StringBuffer stringBuffer = new StringBuffer();

        for (Object selectedValue : xmlEadList.getSelectedValues()) {
            File selectedFile = (File) selectedValue;
            String filename = selectedFile.getName();
            FileInstance fileInstance = fileInstances.get(filename);

            //todo: do we really need this?
            filename = filename.startsWith("temp_") ? filename.replace("temp_", "") : filename;

            if (fileInstance.getConversionErrors() != null || fileInstance.getValidationErrors() != null || fileInstance.getEuropeanaConversionErrors() != null) {
                stringBuffer.append("========================================\n");
                stringBuffer.append(filename + "\n");
                stringBuffer.append("========================================\n");
            }
            if (fileInstance.getConversionErrors() != null) {
                stringBuffer.append(fileInstance.getConversionErrors());
                stringBuffer.append("\n");
            }
            if (fileInstance.getValidationErrors() != null) {
                stringBuffer.append(fileInstance.getValidationErrors());
                stringBuffer.append("\n");
            }
            if (fileInstance.getEuropeanaConversionErrors() != null) {
                stringBuffer.append(fileInstance.getEuropeanaConversionErrors());
                stringBuffer.append("\n");
            }
        }

        try {
            FileUtils.writeStringToFile(new File(defaultOutputDirectory + "/report.txt"), stringBuffer.toString());
            JOptionPane.showMessageDialog(parent, MessageFormat.format(labels.getString("filesInOutput"), defaultOutputDirectory) + ".", labels.getString("fileSaved"), JOptionPane.INFORMATION_MESSAGE, Utilities.icon);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MessageReportActionListener.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
