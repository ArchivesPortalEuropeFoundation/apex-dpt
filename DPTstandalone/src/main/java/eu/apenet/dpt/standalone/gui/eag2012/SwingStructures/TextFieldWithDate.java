package eu.apenet.dpt.standalone.gui.eag2012.SwingStructures;

import javax.swing.*;

/**
 * User: Yoann Moranville
 * Date: 10/12/2012
 *
 * @author Yoann Moranville
 */
public class TextFieldWithDate {
    private TextFieldWithLanguage textFieldWithLanguage;
    private JTextField dateField;
    private JTextField fromDateField;
    private JTextField toDateField;
    private boolean isDateRange;

    public TextFieldWithDate(String text, String language, String fromDate, String toDate, String date) {
        textFieldWithLanguage = new TextFieldWithLanguage(text, language);
        fromDateField = new JTextField(fromDate);
        toDateField = new JTextField(toDate);
        dateField = new JTextField(date);
        isDateRange = false;
    }

    public boolean isDateRange() {
        return isDateRange;
    }

    public void setDateRange(boolean dateRange) {
        isDateRange = dateRange;
    }

    public JTextField getTextField() {
        return textFieldWithLanguage.getTextField();
    }

    public JComboBox getLanguageBox() {
        return textFieldWithLanguage.getLanguageBox();
    }

    public String getTextValue() {
        return textFieldWithLanguage.getTextValue();
    }

    public String getLanguage() {
        return textFieldWithLanguage.getLanguage();
    }

    public JTextField getFromDateField() {
        return fromDateField;
    }

    public JTextField getToDateField() {
        return toDateField;
    }

    public JTextField getDateField() {
        return dateField;
    }

    public String getFromDate() {
        return fromDateField.getText();
    }

    public String getToDate() {
        return toDateField.getText();
    }

    public String getDate() {
        return dateField.getText();
    }
}