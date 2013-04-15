package eu.apenet.dpt.standalone.gui.ead2ese;

import eu.apenet.dpt.standalone.gui.*;
import eu.apenet.dpt.standalone.gui.db.RetrieveFromDb;
import eu.apenet.dpt.standalone.gui.progress.ApexActionListener;
import eu.apenet.dpt.standalone.gui.progress.ProgressFrame;
import eu.apenet.dpt.utils.ead2ese.EseConfig;
import eu.apenet.dpt.utils.ead2ese.XMLUtil;
import eu.apenet.dpt.utils.ead2ese.stax.ESEParser;
import eu.apenet.dpt.utils.ead2ese.stax.RecordParser;
import eu.apenet.dpt.utils.util.Ead2EseInformation;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * User: Yoann Moranville Date: 17/11/2011
 *
 * @author Yoann Moranville
 */
public class EseOptionsPanel extends JPanel {

    private static final Logger LOG = Logger.getLogger(EseOptionsPanel.class);
    private static final String TEXT = "TEXT";
    private static final String IMAGE = "IMAGE";
    private static final String VIDEO = "VIDEO";
    private static final String SOUND = "SOUND";
    private static final String THREE_D = "3D";
    private static final String YES = "yes";
    private static final String NO = "no";
    private static final String PROVIDE = "provide";
    private static final String CREATIVE_COMMONS = "creative_commons";
    private static final String CREATIVE_COMMONS_CC0 = "creative_commons_cc0";
    private static final String CREATIVE_COMMONS_PUBLIC_DOMAIN_MARK = "creative_commons_public_domain_mark";
    private static final String EUROPEANA_RIGHTS_STATEMENTS = "europeana_rights_statements";
    private static final String EMPTY_PANEL = "empty_panel";
    private static final String CREATIVE_COMMONS_ALLOW_REMIXING = "Allow remixing";
    private static final String CREATIVE_COMMONS_REQUIRE_SHARE_ALIKE = "Require share-alike";
    private static final String CREATIVE_COMMONS_PROHIBIT_COMMERCIAL_USE = "Prohibit commercial use";
    private static final String EUROPEANA_RIGHTS_FREE = "Free access";
    private static final String EUROPEANA_RIGHTS_PAID = "Paid access";
    private static final String EUROPEANA_RIGHTS_RESTRICTED = "Restricted access";
    private static final String EUROPEANA_RIGHTS_UNKNOWN = "Unknown";
    private String archdescRepository = null;
    private ResourceBundle labels;
    private RetrieveFromDb retrieveFromDb;
    private Map<String, FileInstance> fileInstances;
    private List<File> selectedIndices;
    private JFrame parent;
    private APETabbedPane apeTabbedPane;
    private ButtonGroup typeGroup;
    private ButtonGroup inheritParentGroup;
    private ButtonGroup inheritOriginationGroup;
    private ButtonGroup inheritLanguageGroup;
    private ButtonGroup licenseGroup;
    private ButtonGroup creativeCommonsBtnGrp;
    private JComboBox creativeCommonsComboBox;
    private JComboBox europeanaRightsComboBox;
    private JTextArea contextTextArea;
    private JTextArea dataProviderTextArea;
    private JTextArea providerTextArea;
    private JComboBox languageComboBox;
    private JTextArea additionalRightsTextArea;
    private JCheckBox archdescCheckbox;
    private Map<String, String> languages;
    private static final Border BLACK_LINE = BorderFactory.createLineBorder(Color.BLACK);
    private static final Border GREY_LINE = BorderFactory.createLineBorder(Color.GRAY);
    private DataPreparationToolGUI dataPreparationToolGUI;

    private Ead2EseInformation ead2EseInformation;

    public EseOptionsPanel(ResourceBundle labels, DataPreparationToolGUI dataPreparationToolGUI, JFrame parent, APETabbedPane apeTabbedPane) {
        super(new BorderLayout());
        this.labels = labels;
        this.retrieveFromDb = new RetrieveFromDb();
        this.parent = parent;
        this.dataPreparationToolGUI = dataPreparationToolGUI;
        this.selectedIndices = setIndices(dataPreparationToolGUI.getXmlEadList().getSelectedValues());
        this.apeTabbedPane = apeTabbedPane;
        this.fileInstances = dataPreparationToolGUI.getFileInstances();
        createOptionPanel();
    }

    public void createOptionPanel() {
        labels = dataPreparationToolGUI.getLabels();
        JPanel creativeCommonsPanel = new CreativeCommonsPanel();
        JPanel europeanaRightsPanel = new EuropeanaRightsPanel();
        JPanel emptyPanel = new JPanel();

        JPanel formPanel = new JPanel(new GridLayout(10, 1));

        JPanel extraLicenseCardLayoutPanel = new JPanel(new CardLayout());
        extraLicenseCardLayoutPanel.add(creativeCommonsPanel, CREATIVE_COMMONS);
        extraLicenseCardLayoutPanel.add(europeanaRightsPanel, EUROPEANA_RIGHTS_STATEMENTS);
        extraLicenseCardLayoutPanel.add(emptyPanel, EMPTY_PANEL);
        CardLayout cardLayout = (CardLayout) extraLicenseCardLayoutPanel.getLayout();
        cardLayout.show(extraLicenseCardLayoutPanel, EMPTY_PANEL);


        JPanel panel = new JPanel(new GridLayout(1, 1));
        panel.add(new Label(labels.getString("ese.mandatoryFieldsInfo")));
        panel.add(new Label(""));
        panel.setBorder(BLACK_LINE);
        formPanel.add(panel);

        panel = new JPanel(new GridLayout(1, 3));
        panel.add(new Label(labels.getString("ese.dataProvider") + ":" + "*"));
        dataProviderTextArea = new JTextArea();
        dataProviderTextArea.setLineWrap(true);
        dataProviderTextArea.setWrapStyleWord(true);
        determineDaoInformation();
        String repository = ead2EseInformation.getRepository();
        JScrollPane dptaScrollPane = new JScrollPane(dataProviderTextArea);
        dptaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(dptaScrollPane);
        JPanel panel2 = new JPanel(new GridLayout(2, 1));
        archdescCheckbox = new JCheckBox(labels.getString("ese.mappingFromArchdesc") + " /ead/archdesc/did/repository", true);
        archdescCheckbox.addItemListener(new CheckboxItemListener());
        panel2.add(archdescCheckbox);
        panel2.add(new JLabel(labels.getString("ese.archdescValue") + ": " + archdescRepository));
        panel2.setVisible(false);
        panel.add(panel2);
        if (repository != null && !repository.equals("")) {
            dataProviderTextArea.setText(repository);
        } else {
            if (archdescRepository != null) {
                panel2.setVisible(true);
                dataProviderTextArea.setText(archdescRepository);
            }
        }
        panel.setBorder(BLACK_LINE);
        formPanel.add(panel);

        panel = new JPanel(new GridLayout(1, 3));
        panel.add(new Label(labels.getString("ese.provider") + ":" + "*"));
        providerTextArea = new JTextArea();
        providerTextArea.setLineWrap(true);
        providerTextArea.setWrapStyleWord(true);
        JScrollPane ptaScrollPane = new JScrollPane(providerTextArea);
        ptaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(ptaScrollPane);
        panel.add(new Label(""));
        panel.setBorder(BLACK_LINE);
        formPanel.add(panel);

        panel = new JPanel(new GridLayout(5, 3));
        typeGroup = new ButtonGroup();
        JRadioButton radioButton;

        panel.add(new Label(labels.getString("ese.type") + ":" + "*"));
        String currentRoleType = ead2EseInformation.getRoleType();
        radioButton = new JRadioButton("TEXT");
        if (currentRoleType.equals("TEXT")) {
            radioButton.setSelected(true);
        }
        radioButton.setActionCommand(TEXT);
        typeGroup.add(radioButton);
        panel.add(radioButton);

        panel.add(new JLabel(""));
        radioButton = new JRadioButton("IMAGE");
        if (currentRoleType.equals("IMAGE")) {
            radioButton.setSelected(true);
        }
        radioButton.setActionCommand(IMAGE);
        typeGroup.add(radioButton);
        panel.add(radioButton);

        panel.add(new JLabel(""));
        radioButton = new JRadioButton("VIDEO");
        if (currentRoleType.equals("VIDEO")) {
            radioButton.setSelected(true);
        }
        radioButton.setActionCommand(VIDEO);
        typeGroup.add(radioButton);
        panel.add(radioButton);

        panel.add(new JLabel(""));
        radioButton = new JRadioButton("SOUND");
        if (currentRoleType.equals("SOUND")) {
            radioButton.setSelected(true);
        }
        radioButton.setActionCommand(SOUND);
        typeGroup.add(radioButton);
        panel.add(radioButton);

        panel.add(new JLabel(""));
        radioButton = new JRadioButton("3D");
        if (currentRoleType.equals("3D")) {
            radioButton.setSelected(true);
        }
        radioButton.setActionCommand(THREE_D);
        typeGroup.add(radioButton);
        panel.add(radioButton);

        panel.setBorder(GREY_LINE);
        formPanel.add(panel);

        panel = new JPanel(new GridLayout(1, 3));
        panel.add(new Label(labels.getString("ese.hierarchyPrefix") + ":"));
        contextTextArea = new JTextArea(labels.getString("ese.hierarchyPrefixDefault") + ":");
        contextTextArea.setLineWrap(true);
        contextTextArea.setWrapStyleWord(true);
        JScrollPane ctaScrollPane = new JScrollPane(contextTextArea);
        ctaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(ctaScrollPane);
        panel.add(new Label(""));
        panel.setBorder(BLACK_LINE);
        formPanel.add(panel);

        panel = new JPanel(new GridLayout(2, 3));
        panel.add(new Label(labels.getString("ese.inheritParent") + ":" + "*"));
        inheritParentGroup = new ButtonGroup();
        radioButton = new JRadioButton(labels.getString("ese.yes"));
        radioButton.setActionCommand(YES);
        inheritParentGroup.add(radioButton);
        panel.add(radioButton);
        panel.add(new JLabel(""));
        radioButton = new JRadioButton(labels.getString("ese.no"), true);
        radioButton.setActionCommand(NO);
        inheritParentGroup.add(radioButton);
        panel.add(radioButton);
        panel.setBorder(GREY_LINE);
        formPanel.add(panel);

        panel = new JPanel(new GridLayout(2, 3));
        panel.add(new Label(labels.getString("ese.inheritOrigination") + ":" + "*"));
        inheritOriginationGroup = new ButtonGroup();
        radioButton = new JRadioButton(labels.getString("ese.yes"));
        radioButton.setActionCommand(YES);
        inheritOriginationGroup.add(radioButton);
        panel.add(radioButton);
        panel.add(new JLabel(""));
        radioButton = new JRadioButton(labels.getString("ese.no"), true);
        radioButton.setActionCommand(NO);
        inheritOriginationGroup.add(radioButton);
        panel.add(radioButton);
        panel.setBorder(BLACK_LINE);
        formPanel.add(panel);

        panel = new JPanel(new GridLayout(3, 3));
        panel.add(new Label(labels.getString("ese.inheritLanguage") + ":" + "*"));
        inheritLanguageGroup = new ButtonGroup();
        radioButton = new JRadioButton(labels.getString("ese.yes"));
        radioButton.setActionCommand(YES);
        inheritLanguageGroup.add(radioButton);
        panel.add(radioButton);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        radioButton = new JRadioButton(labels.getString("ese.no"), true);
        radioButton.setActionCommand(NO);
        inheritLanguageGroup.add(radioButton);
        panel.add(radioButton);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        radioButton = new JRadioButton(labels.getString("ese.provide") + ":");
        radioButton.setActionCommand(PROVIDE);
        inheritLanguageGroup.add(radioButton);
        panel.add(radioButton);

        languageComboBox = new JComboBox(getAllLanguages());
        panel.add(languageComboBox);
        panel.setBorder(GREY_LINE);
        formPanel.add(panel);

        JPanel mainLicensePanel = new JPanel(new BorderLayout());
        panel = new JPanel(new GridLayout(4, 2));
        panel.add(new Label(labels.getString("ese.specifyLicense") + ":" + "*"));
        licenseGroup = new ButtonGroup();
        radioButton = new JRadioButton("Creative Commons");
        radioButton.setActionCommand(CREATIVE_COMMONS);
        radioButton.addActionListener(new ChangePanelActionListener(extraLicenseCardLayoutPanel));
        licenseGroup.add(radioButton);
        panel.add(radioButton);
        panel.add(new JLabel(""));
        radioButton = new JRadioButton("Creative Commons CC0");
        radioButton.setActionCommand(CREATIVE_COMMONS_CC0);
        radioButton.addActionListener(new ChangePanelActionListener(extraLicenseCardLayoutPanel));
        licenseGroup.add(radioButton);
        panel.add(radioButton);
        panel.add(new JLabel(""));
        radioButton = new JRadioButton("Creative Commons Public Domain Mark");
        radioButton.setActionCommand(CREATIVE_COMMONS_PUBLIC_DOMAIN_MARK);
        radioButton.addActionListener(new ChangePanelActionListener(extraLicenseCardLayoutPanel));
        licenseGroup.add(radioButton);
        panel.add(radioButton);
        panel.add(new JLabel(""));
        radioButton = new JRadioButton("Europeana rights statements");
        radioButton.setActionCommand(EUROPEANA_RIGHTS_STATEMENTS);
        radioButton.addActionListener(new ChangePanelActionListener(extraLicenseCardLayoutPanel));
        licenseGroup.add(radioButton);
        panel.add(radioButton);
        mainLicensePanel.add(panel, BorderLayout.WEST);

        mainLicensePanel.add(extraLicenseCardLayoutPanel, BorderLayout.EAST);
        mainLicensePanel.setBorder(BLACK_LINE);
        formPanel.add(mainLicensePanel);

        panel = new JPanel(new GridLayout(1, 1));
        panel.add(new Label(labels.getString("ese.specifyAdditionalRightsInfo") + ":"));
        additionalRightsTextArea = new JTextArea();
        additionalRightsTextArea.setLineWrap(true);
        additionalRightsTextArea.setWrapStyleWord(true);
        JScrollPane artaScrollPane = new JScrollPane(additionalRightsTextArea);
        artaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(artaScrollPane);
        panel.setBorder(GREY_LINE);
        formPanel.add(panel);

        JButton createEseBtn = new JButton(labels.getString("ese.createEseBtn"));
        JButton cancelBtn = new JButton(labels.getString("ese.cancelBtn"));

        createEseBtn.addActionListener(new CreateEseActionListener());
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5));

        buttonPanel.add(new JLabel(""));
        buttonPanel.add(cancelBtn);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(createEseBtn);
        buttonPanel.add(new JLabel(""));

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private List<File> setIndices(Object[] indices) {
        List<File> files = new ArrayList<File>();
        for (Object index : indices) {
            if (index instanceof File) {
                files.add((File) index);
            }
        }
        return files;
    }

    public void close() {
        parent.setVisible(false);
        parent.dispose();
    }

    public String[] getAllLanguages() {
        String[] isoLanguages = Locale.getISOLanguages();
        Map<String, String> languagesTemp = new LinkedHashMap<String, String>(isoLanguages.length);
        languages = new LinkedHashMap<String, String>(isoLanguages.length);
        for (String isoLanguage : isoLanguages) {
            languagesTemp.put(new Locale(isoLanguage).getDisplayLanguage(Locale.ENGLISH), isoLanguage);
        }

        List<String> tempList = new LinkedList<String>(languagesTemp.keySet());
        Collections.sort(tempList, String.CASE_INSENSITIVE_ORDER);

        for (String tempLanguage : tempList) {
            languages.put(tempLanguage, languagesTemp.get(tempLanguage));
        }

        return languages.keySet().toArray(new String[]{});
    }

    private EseConfig fillConfig() {
        EseConfig config = new EseConfig();

        Enumeration<AbstractButton> enumeration = typeGroup.getElements();
        while (enumeration.hasMoreElements()) {
            AbstractButton btn = enumeration.nextElement();
            if (btn.isSelected()) {
                config.setType(btn.getText());
            }
        }

        config.setInheritElementsFromFileLevel(false);
        enumeration = inheritParentGroup.getElements();
        while (enumeration.hasMoreElements()) {
            AbstractButton btn = enumeration.nextElement();
            if (btn.isSelected() && btn.getActionCommand().equals(YES)) {
                config.setInheritElementsFromFileLevel(true);
            }
        }

        config.setInheritOrigination(false);
        enumeration = inheritOriginationGroup.getElements();
        while (enumeration.hasMoreElements()) {
            AbstractButton btn = enumeration.nextElement();
            if (btn.isSelected() && btn.getActionCommand().equals(YES)) {
                config.setInheritOrigination(true);
                config.setInheritCustodhist(true);
                config.setInheritAltformavail(true);
                config.setInheritControlaccess(true);
            }
        }

        config.setInheritLanguage(false);
        enumeration = inheritLanguageGroup.getElements();
        while (enumeration.hasMoreElements()) {
            AbstractButton btn = enumeration.nextElement();
            if (btn.isSelected()) {
                if (btn.getActionCommand().equals(YES)) {
                    config.setInheritLanguage(true);
                } else if (btn.getActionCommand().equals(PROVIDE)) {
                    config.setLanguage(languages.get(languageComboBox.getSelectedItem().toString()));
                }
            }
        }

        if (contextTextArea != null && StringUtils.isNotEmpty(contextTextArea.getText())) {
            config.setContextInformationPrefix(contextTextArea.getText());
        }

        config.setDataProvider(dataProviderTextArea.getText());
        config.setProvider(providerTextArea.getText());

        enumeration = licenseGroup.getElements();
        while (enumeration.hasMoreElements()) {
            AbstractButton btn = enumeration.nextElement();
            if (btn.isSelected()) {
                config.setRights(getCorrectRights(btn.getActionCommand()));
            }
        }

        if (additionalRightsTextArea != null && StringUtils.isNotEmpty(additionalRightsTextArea.getText())) {
            config.setRightsAdditionalInformation(additionalRightsTextArea.getText());
        }
        return config;
    }

    private String getCorrectRights(String type) {
        if (type.equals(CREATIVE_COMMONS)) {
            CreativeCommonsType creativeCommonsType = new CreativeCommonsType();
            Enumeration<AbstractButton> enumeration = creativeCommonsBtnGrp.getElements();
            while (enumeration.hasMoreElements()) {
                AbstractButton btn = enumeration.nextElement();
                if (btn.isSelected()) {
                    creativeCommonsType.setBtnChecked(btn.getActionCommand());
                }
            }
            String urlType = creativeCommonsType.getUrlType();
            CreativeCommons creativeCommons = CreativeCommons.getCreativeCommonsByCountryName(creativeCommonsComboBox.getSelectedItem().toString());
            String url = CreativeCommons.constructUrl(creativeCommons);
            return MessageFormat.format(url, urlType);
        } else if (type.equals(EUROPEANA_RIGHTS_STATEMENTS)) {
            String europeanaRights = europeanaRightsComboBox.getSelectedItem().toString();
            if (europeanaRights.equals(EUROPEANA_RIGHTS_FREE)) {
                return "http://www.europeana.eu/rights/rr-f/";
            } else if (europeanaRights.equals(EUROPEANA_RIGHTS_PAID)) {
                return "http://www.europeana.eu/rights/rr-p/";
            } else if (europeanaRights.equals(EUROPEANA_RIGHTS_RESTRICTED)) {
                return "http://www.europeana.eu/rights/rr-r/";
            } else {
                return "http://www.europeana.eu/rights/unknown/";
            }
        } else if (type.equals(CREATIVE_COMMONS_CC0)) {
            return "http://creativecommons.org/publicdomain/zero/1.0/";
        } else {
            return "http://creativecommons.org/publicdomain/mark/1.0/";
        }
    }

    private boolean isRadioBtnChecked(ButtonGroup buttonGroup) {
        Enumeration<AbstractButton> enumeration = buttonGroup.getElements();
        while (enumeration.hasMoreElements()) {
            AbstractButton btn = enumeration.nextElement();
            if (btn.isSelected()) {
                return true;
            }
        }
        return false;
    }

    private void checkIfAllFilled() throws Exception {
        if (typeGroup == null) {
            throw new Exception("typeGroup is null");
        } else {
            if (!isRadioBtnChecked(typeGroup)) {
                throw new Exception("typeGroup is not checked");
            }
        }

        if (inheritOriginationGroup == null) {
            throw new Exception("inheritOriginationGroup is null");
        } else {
            if (!isRadioBtnChecked(inheritOriginationGroup)) {
                throw new Exception("inheritOriginationGroup is not checked");
            }
        }

        if (inheritParentGroup == null) {
            throw new Exception("inheritParentGroup is null");
        } else if (!isRadioBtnChecked(inheritParentGroup)) {
            throw new Exception("inheritParentGroup is not checked");
        }

        if (inheritLanguageGroup == null) {
            throw new Exception("inheritLanguageGroup is null");
        } else if (!isRadioBtnChecked(inheritLanguageGroup)) {
            throw new Exception("inheritLanguageGroup is not checked");
        }

        if (StringUtils.isEmpty(dataProviderTextArea.getText())) {
            throw new Exception("dataProviderTextField is empty");
        }

        if (StringUtils.isEmpty(providerTextArea.getText())) {
            throw new Exception("providerTextField is empty");
        }

        if (licenseGroup == null) {
            throw new Exception("licenseGroup is null");
        } else if (!isRadioBtnChecked(licenseGroup)) {
            throw new Exception("licenseGroup is not checked");
        }

        if (typeGroup.getSelection().getActionCommand().equals(TEXT)) {
            if (inheritLanguageGroup.getSelection().getActionCommand().equals(NO) && ead2EseInformation.getLanguageCode() == null) {
                throw new Exception("selected type requires language inheritance");
            }
        }
    }

    public void determineDaoInformation() {
        File index = selectedIndices.get(0);
        FileInstance fileInstance = fileInstances.get(index.getName());
        try {
            ead2EseInformation = new Ead2EseInformation(new File(fileInstance.getCurrentLocation()), retrieveFromDb.retrieveRoleType(), archdescRepository);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(EseOptionsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(ead2EseInformation.getArchdescRepository() != null) {
            archdescRepository = ead2EseInformation.getArchdescRepository();
        }
    }

    public class ChangePanelActionListener implements ActionListener {

        private JPanel extraLicenseCardLayoutPanel;

        ChangePanelActionListener(JPanel extraLicenseCardLayoutPanel) {
            this.extraLicenseCardLayoutPanel = extraLicenseCardLayoutPanel;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            CardLayout cardLayout = (CardLayout) extraLicenseCardLayoutPanel.getLayout();
            if (actionEvent.getActionCommand().equals(CREATIVE_COMMONS_CC0) || actionEvent.getActionCommand().equals(CREATIVE_COMMONS_PUBLIC_DOMAIN_MARK)) {
                cardLayout.show(extraLicenseCardLayoutPanel, EMPTY_PANEL);
            } else {
                cardLayout.show(extraLicenseCardLayoutPanel, actionEvent.getActionCommand());
            }
        }
    }

    public class CreateEseActionListener extends ApexActionListener {

        public void actionPerformed(ActionEvent e) {
//            dataPreparationToolGUI.disableAllBtnAndItems(); //todo: FIX!
            continueLoop = true;
            final ApexActionListener apexActionListener = this;
            new Thread(new Runnable() {
                public void run() {
                    StringWriter writer = new StringWriter();

                    int numberOfFiles = selectedIndices.size();
                    int currentFileNumberBatch = 0;

                    try {
                        checkIfAllFilled();
                    } catch (Exception ex1) {
                        DataPreparationToolGUI.createErrorOrWarningPanel(ex1, false, labels.getString("ese.formNotFilledError"), parent);
                    }

                    ProgressFrame progressFrame = new ProgressFrame(labels, parent, true, true, apexActionListener);
                    ProgressFrame.ApeProgressBar progressBar = progressFrame.getProgressBarBatch();

                    try {
                        apeTabbedPane.setEseConversionErrorText(labels.getString("ese.conversionEseStarted") + "\n");
                        writer.append(labels.getString("ese.conversionEseStarted") + "\n");
                        SummaryWorking summaryWorking = new SummaryWorking(dataPreparationToolGUI.getResultArea(), progressBar);
                        summaryWorking.setTotalNumberFiles(numberOfFiles);
                        summaryWorking.setCurrentFileNumberBatch(currentFileNumberBatch);
                        Thread threadRunner = new Thread(summaryWorking);
                        threadRunner.setName(SummaryWorking.class.toString());
                        threadRunner.start();
                        
                        try {
                            EseConfig config = fillConfig();
                            for (File selectedIndexFile : selectedIndices) {
                                if (!continueLoop)
                                    break;
                                
                                SwingUtilities.invokeLater(new TransformEse(config, selectedIndexFile));
                                apeTabbedPane.appendEseConversionErrorText(MessageFormat.format(labels.getString("ese.convertedAndSaved"), selectedIndexFile.getAbsolutePath(), retrieveFromDb.retrieveDefaultSaveFolder()) + "\n");
                                writer.append(MessageFormat.format(labels.getString("ese.convertedAndSaved"), selectedIndexFile.getAbsolutePath(), retrieveFromDb.retrieveDefaultSaveFolder()) + "\n");
                            }
                            apeTabbedPane.checkFlashingTab(APETabbedPane.TAB_ESE, Utilities.FLASHING_GREEN_COLOR);
                            close();
                        } catch (Exception ex) {
                            apeTabbedPane.checkFlashingTab(APETabbedPane.TAB_ESE, Utilities.FLASHING_RED_COLOR);
                        } finally {
                            if (summaryWorking != null) {
                                summaryWorking.stop();
                            }
                            if (threadRunner != null) {
                                threadRunner.interrupt();
                            }
                        }
                    } catch (Exception e) {
                        LOG.error(e);
                    }
                    
                    if (progressFrame != null) {
                        progressFrame.stop();
                    }
                    dataPreparationToolGUI.getFinalAct().run();
                    dataPreparationToolGUI.getXmlEadList().clearSelection();
                    if (continueLoop) {
                        dataPreparationToolGUI.setResultAreaText(labels.getString("finished"));
                    } else {
                        dataPreparationToolGUI.setResultAreaText(labels.getString("aborted"));
                    }
                    apeTabbedPane.setEseConversionErrorText(writer.toString());
                    dataPreparationToolGUI.enableSaveBtn();
                    dataPreparationToolGUI.enableRadioButtons();
                }
            }).start();
        }
    }

    private class TransformEse implements Runnable {

        private EseConfig config;
        private File selectedIndex;

        TransformEse(EseConfig config, File selectedIndex) {
            this.config = config;
            this.selectedIndex = selectedIndex;
        }

        public void run() {
            try {
                RetrieveFromDb retrieveFromDb = new RetrieveFromDb();
                int lastIndex = selectedIndex.getName().lastIndexOf('.');
                String xmlOutputFilename = retrieveFromDb.retrieveDefaultSaveFolder() + selectedIndex.getName().substring(0, lastIndex) + "-ese" + selectedIndex.getName().substring(lastIndex);
                FileInstance fileInstance = fileInstances.get(selectedIndex.getName());
                String loc;
                if (fileInstance.isConverted() || fileInstance.getLastOperation().equals(FileInstance.Operation.SAVE)) {
                    loc = fileInstance.getCurrentLocation();
                } else {
                    loc = fileInstance.getOriginalPath();
                }
                File outputFile = new File(xmlOutputFilename);
                config.getTransformerXML2XML().transform(new File(loc), outputFile);
                if (analyzeESEXML(outputFile) <= 1) {
                    apeTabbedPane.appendEseConversionErrorText(labels.getString("ese.fileEmpty"));
                } else {
                    fileInstance.setEseLocation(outputFile.getAbsolutePath());
                    if (StringUtils.isNotEmpty(fileInstance.getEseLocation())) {
                        apeTabbedPane.getDataPreparationToolGUI().addEseFileToList(new File(fileInstance.getEseLocation()));
                    }
                }
            } catch (Exception e) {
                LOG.error("Error when converting file into ESE", e);
            }
        }

        private int analyzeESEXML(File outputFile) throws XMLStreamException, SAXException, IOException {
            XMLStreamReader xmlReader = XMLUtil.getXMLStreamReader(outputFile);
            ESEParser parser = new ESEParser();
            RecordParser recordParser = new RecordParser();
            parser.registerParser(recordParser);
            // count number of records
            parser.parse(xmlReader, null);
            int numberOfRecords = recordParser.getNumberOfRecords();
            if (numberOfRecords <= 1) {
                outputFile.delete();
            } else {
                XMLUtil.validateESE(outputFile);
            }
            return numberOfRecords;
        }
    }

    private class CreativeCommonsPanel extends JPanel {

        CreativeCommonsPanel() {
            super(new GridLayout(4, 1));
            creativeCommonsBtnGrp = new ButtonGroup();
            JCheckBox checkBox = new JCheckBox(CREATIVE_COMMONS_ALLOW_REMIXING);
            add(checkBox);
            creativeCommonsBtnGrp.add(checkBox);
            checkBox = new JCheckBox(CREATIVE_COMMONS_PROHIBIT_COMMERCIAL_USE);
            add(checkBox);
            creativeCommonsBtnGrp.add(checkBox);
            checkBox = new JCheckBox(CREATIVE_COMMONS_REQUIRE_SHARE_ALIKE);
            add(checkBox);
            creativeCommonsBtnGrp.add(checkBox);
            creativeCommonsComboBox = new JComboBox(CreativeCommons.getCountryNames().toArray());
            add(creativeCommonsComboBox);
        }
    }

    private class EuropeanaRightsPanel extends JPanel {

        EuropeanaRightsPanel() {
            super(new GridLayout(1, 1));
            String[] rights = {EUROPEANA_RIGHTS_FREE, EUROPEANA_RIGHTS_PAID, EUROPEANA_RIGHTS_RESTRICTED, EUROPEANA_RIGHTS_UNKNOWN};
            europeanaRightsComboBox = new JComboBox(rights);
            add(europeanaRightsComboBox);
        }
    }

    private enum CreativeCommons {

        UNPORTED("Unported", "", "3.0"),
        ARGENTINA("Argentina", "ar", "2.5"),
        AUSTRALIA("Australia", "au", "3.0"),
        AUSTRIA("Austria", "at", "3.0"),
        BELGIUM("Belgium", "be", "2.0"),
        BRAZIL("Brazil", "br", "3.0"),
        BULGARIA("Bulgaria", "bg", "2.5"),
        CANADA("Canada", "ca", "2.5"),
        CHILE("Chile", "cl", "3.0"),
        CHINA_MAINLAND("China Mainland", "cn", "3.0"),
        COLOMBIA("Colombia", "co", "2.5"),
        COSTA_RICA("Costa Rica", "cr", "3.0"),
        CROATIA("Croatia", "hr", "3.0"),
        CZECH_REPUBLIC("Czech Republic", "cz", "3.0"),
        DENMARK("Denmark", "dk", "2.5"),
        ECUADOR("Ecuador", "ec", "3.0"),
        ESTONIA("Estonia", "ee", "3.0"),
        FINLAND("Finland", "fi", "1.0"),
        FRANCE("France", "fr", "3.0"),
        GERMANY("Germany", "de", "3.0"),
        GREECE("Greece", "gr", "3.0"),
        GUATEMALA("Guatemala", "gt", "3.0"),
        HONG_KONG("Hong Kong", "hk", "3.0"),
        HUNGARY("Hungary", "hu", "2.5"),
        INDIA("India", "in", "2.5"),
        IRELAND("Ireland", "ie", "3.0"),
        ISRAEL("Israel", "il", "2.5"),
        ITALY("Italy", "it", "3.0"),
        JAPAN("Japan", "jp", "2.1"),
        LUXEMBOURG("Luxembourg", "lu", "3.0"),
        MACEDONIA("Macedonia", "mk", "2.5"),
        MALAYSIA("Malaysia", "my", "2.5"),
        MALTA("Malta", "mt", "2.5"),
        MEXICO("Mexico", "mx", "2.5"),
        NETHERLANDS("Netherlands", "nl", "3.0"),
        NEW_ZEALAND("New Zealand", "nz", "3.0"),
        NORWAY("Norway", "no", "3.0"),
        PERU("Peru", "pe", "2.5"),
        PHILIPPINES("Philippines", "ph", "3.0"),
        POLAND("Poland", "pl", "3.0"),
        PORTUGAL("Portugal", "pt", "3.0"),
        PUERTO_RICO("Puerto Rico", "pr", "3.0"),
        ROMANIA("Romania", "ro", "3.0"),
        SERBIA("Serbia", "rs", "3.0"),
        SINGAPORE("Singapore", "sg", "3.0"),
        SLOVENIA("Slovenia", "si", "2.5"),
        SOUTH_AFRICA("South Africa", "za", "2.5"),
        SOUTH_KOREA("Korea", "ko", "2.0"),
        SPAIN("Spain", "es", "3.0"),
        SWEDEN("Sweden", "se", "2.5"),
        SWITZERLAND("Switzerland", "ch", "3.0"),
        TAIWAN("Taiwan", "tw", "3.0"),
        THAILAND("Thailand", "th", "3.0"),
        UK_ENGLAND_WALES("UK: England & Wales", "uk", "2.0"),
        UK_SCOTLAND("UK: Scotland", "scotland", "2.5"),
        UGANDA("Uganda", "ug", "3.0"),
        UNITED_STATES("United States", "us", "3.0"),
        VIETNAM("Vietnam", "vn", "3.0");
        private static final String URL_START = "http://creativecommons.org/license/{0}/";
        private static final String URL_SEP = "/";
        private String countryName;
        private String countryCode;
        private String version;

        CreativeCommons(String countryName, String countryCode, String version) {
            this.countryName = countryName;
            this.countryCode = countryCode;
            this.version = version;
        }

        public static String constructUrl(CreativeCommons creativeCommons) {
            if (StringUtils.isNotEmpty(creativeCommons.countryCode)) {
                return URL_START + creativeCommons.version + URL_SEP + creativeCommons.countryCode + URL_SEP;
            }
            return URL_START + creativeCommons.version + URL_SEP;
        }

        public static List<String> getCountryNames() {
            List<String> countries = new ArrayList<String>();
            for (CreativeCommons creativeCommons : CreativeCommons.values()) {
                countries.add(creativeCommons.countryName);
            }
            return countries;
        }

        public static CreativeCommons getCreativeCommonsByCountryName(String countryName) {
            for (CreativeCommons creativeCommons : CreativeCommons.values()) {
                if (creativeCommons.countryName.equals(countryName)) {
                    return creativeCommons;
                }
            }
            return null;
        }
    }

    private class CreativeCommonsType {

        private boolean isAllowsRemixing;
        private boolean isProhibitCommercialUse;
        private boolean isRequireShareAlike;

        CreativeCommonsType() {
            isAllowsRemixing = false;
            isProhibitCommercialUse = false;
            isRequireShareAlike = false;
        }

        public void setAllowsRemixing(boolean AllowsRemixing) {
            isAllowsRemixing = AllowsRemixing;
        }

        public void setProhibitCommercialUse(boolean prohibitCommercialUse) {
            isProhibitCommercialUse = prohibitCommercialUse;
        }

        public void setRequireShareAlike(boolean requireShareAlike) {
            isRequireShareAlike = requireShareAlike;
        }

        public void setBtnChecked(String btnName) {
            if (btnName.equals(CREATIVE_COMMONS_ALLOW_REMIXING)) {
                setAllowsRemixing(true);
            } else if (btnName.equals(CREATIVE_COMMONS_PROHIBIT_COMMERCIAL_USE)) {
                setProhibitCommercialUse(true);
            } else if (btnName.equals(CREATIVE_COMMONS_REQUIRE_SHARE_ALIKE)) {
                setRequireShareAlike(true);
            }
        }

        public String getUrlType() {
            //No check: by-nd
            //remix: by
            //remix + prohibit: by-nc
            //remix + prohibit + require: by-nc-sa
            //remix + require: by-sa
            //prohibit: by-nc-nd
            if (isAllowsRemixing) {
                if (isProhibitCommercialUse && !isRequireShareAlike) {
                    return "by-nc";
                } else if (isProhibitCommercialUse && isRequireShareAlike) {
                    return "by-nc-sa";
                } else if (isRequireShareAlike) {
                    return "by-sa";
                } else {
                    return "by";
                }
            } else if (isProhibitCommercialUse) {
                return "by-nc-nd";
            } else {
                return "by-nd";
            }
        }
    }

    private class CheckboxItemListener implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                dataProviderTextArea.setText("");
            }
            if (e.getStateChange() == ItemEvent.SELECTED) {
                dataProviderTextArea.setText(archdescRepository);
            }
        }
    }
}
