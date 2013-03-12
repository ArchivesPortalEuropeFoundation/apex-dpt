package eu.apenet.dpt.standalone.gui.eag2012;

import eu.apenet.dpt.standalone.gui.ProfileListModel;
import eu.apenet.dpt.standalone.gui.eag2012.data.Eag;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * User: Yoann Moranville
 * Date: 03/10/2012
 *
 * @author Yoann Moranville
 */
public abstract class EagPanels {
    protected static final Logger LOG = Logger.getLogger(EagPanels.class);

    private static final int NB_ROWS = 150;
    private static final String EDITOR_ROW = "p, 3dlu, ";
    protected static String EDITOR_ROW_SPEC;
    protected static String[] languages;
    protected static String[] languagesDisplay;
    static {
        String temp = "";
        for(int i = 0; i < NB_ROWS; i++) {
            temp += EDITOR_ROW;
        }
        temp += "p";
        EDITOR_ROW_SPEC = temp;

        String[] isoLanguages = Locale.getISOLanguages();
        Map<String, String> languagesTemp = new LinkedHashMap<String, String>(isoLanguages.length);
        LinkedList<String> languagesList = new LinkedList<String>();
        for(String isoLanguage : isoLanguages)
            languagesTemp.put(new Locale(isoLanguage).getISO3Language(), isoLanguage);//DisplayLanguage(Locale.ENGLISH), isoLanguage);

        List<String> tempList = new LinkedList<String>(languagesTemp.keySet());
        Collections.sort(tempList, String.CASE_INSENSITIVE_ORDER);

        for(String tempLanguage : tempList)
            languagesList.add(tempLanguage);

        languages = languagesList.toArray(new String[]{});

        languagesList.add("---");
        languagesDisplay = languagesList.toArray(new String[]{});
    }

    protected final String[] continents = {"Africa", "Antarctica", "Asia", "Australia", "Europe", "North America", "South America"};
    protected final String[] yesOrNo = {"yes", "no"};
    protected final String[] photographAllowance = {"depending on the material", "no", "yes", "yes (without flash)"};

    protected JComboBox continentCombo = new JComboBox(continents);
    protected JComboBox accessiblePublicCombo = new JComboBox(yesOrNo);
    protected JComboBox facilitiesForDisabledCombo = new JComboBox(yesOrNo);
    protected JComboBox photographAllowanceCombo = new JComboBox(photographAllowance);

    protected int rowNb;
    protected JTabbedPane tabbedPane;
    protected Eag eag;
    protected JFrame eag2012Frame;
    protected ProfileListModel model;
    protected ResourceBundle labels;

    public EagPanels(Eag eag, JTabbedPane tabbedPane, JFrame eag2012Frame, ProfileListModel model, ResourceBundle labels) {
        this.eag2012Frame = eag2012Frame;
        this.eag = eag;
        this.tabbedPane = tabbedPane;
        this.model = model;
        this.labels = labels;
    }

    protected void closeFrame() {
        eag2012Frame.dispose();
        eag2012Frame.setVisible(false);
    }

    protected void setNextRow() {
        rowNb += 2;
    }

    protected static JLabel createErrorLabel(String errorMsg) {
        JLabel label = new JLabel("<html><font color=red>" + errorMsg + "</font></html>");
        label.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
        return label;
    }

    protected abstract JComponent buildEditorPanel(List<String> errors);

    protected class ExitBtnAction implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            Eag2012Frame.inUse(false);
            closeFrame();
        }
    }
}
