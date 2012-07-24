package eu.apenet.dpt.standalone.gui;

import eu.apenet.dpt.utils.util.Xsd_enum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * User: Yoann Moranville
 * Date: 06/04/2012
 *
 * @author Yoann Moranville
 */
public class XsdSelectorListener implements ActionListener {
    private DataPreparationToolGUI dataPreparationToolGUI;

    public XsdSelectorListener(DataPreparationToolGUI dataPreparationToolGUI) {
        this.dataPreparationToolGUI = dataPreparationToolGUI;
    }

    public void actionPerformed(ActionEvent e){
        if(dataPreparationToolGUI.getList().getSelectedValue() != null){
            FileInstance fileInstance = dataPreparationToolGUI.getFileInstances().get(((File)dataPreparationToolGUI.getList().getSelectedValue()).getName());
            fileInstance.setValidationSchema(e.getActionCommand());
            if(fileInstance.getValidationSchema().equals(Xsd_enum.XSD_EAG_SCHEMA) || fileInstance.getValidationSchema().equals(Xsd_enum.XSD_APE_EAG_SCHEMA))
                fileInstance.setFileType(FileInstance.FileType.EAG);
            else if(fileInstance.getValidationSchema().equals(Xsd_enum.XSD_EAC_SCHEMA))
                fileInstance.setFileType(FileInstance.FileType.EAC_CPF);
        }
    }
}