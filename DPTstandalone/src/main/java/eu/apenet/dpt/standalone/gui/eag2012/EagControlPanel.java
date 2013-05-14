package eu.apenet.dpt.standalone.gui.eag2012;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import eu.apenet.dpt.standalone.gui.ProfileListModel;
import eu.apenet.dpt.standalone.gui.Utilities;
import eu.apenet.dpt.standalone.gui.eag2012.data.*;
import eu.apenet.dpt.utils.eag2012.*;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * User: Yoann Moranville
 * Date: 28/11/2012
 *
 * @author Yoann Moranville
 */
public class EagControlPanel extends EagPanels {
    private List<LanguageWithScript> languageWithScriptTfs;
    private List<TextFieldWithLanguage> rulesConventionTfs;
    private TextFieldWithLanguage personInstitutionRespTf;

    public EagControlPanel(Eag eag, JTabbedPane tabbedPane, JFrame eag2012Frame, ProfileListModel model, ResourceBundle labels) {
        super(eag, tabbedPane, eag2012Frame, model, labels);
    }

    @Override
    protected JComponent buildEditorPanel(List<String> errors) {
        if(errors == null)
            errors = new ArrayList<String>(0);
        else if(Utilities.isDev && errors.size() > 0) {
            LOG.info("Errors in form:");
            for(String error : errors) {
                LOG.info(error);
            }
        }

        FormLayout layout = new FormLayout(
                "right:max(50dlu;p), 4dlu, 100dlu, 7dlu, right:p, 4dlu, 100dlu",
                EDITOR_ROW_SPEC);

        layout.setColumnGroups(new int[][] { { 1, 3, 5, 7 } });
        PanelBuilder builder = new PanelBuilder(layout);

        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();

        rowNb = 1;

        builder.addLabel(labels.getString("eag2012.idUsedInApeLabel") + "*", cc.xy(1, rowNb));
        JTextField recordIdTf = new JTextField(eag.getControl().getRecordId().getValue());
        recordIdTf.setEnabled(false);
        builder.add(recordIdTf, cc.xy(3, rowNb));
        setNextRow();

        builder.addLabel(labels.getString("eag2012.personInstitutionResponsible"), cc.xy(1, rowNb));
        int sizeEvents = eag.getControl().getMaintenanceHistory().getMaintenanceEvent().size();
        if(sizeEvents > 0) {
            MaintenanceEvent event = eag.getControl().getMaintenanceHistory().getMaintenanceEvent().get(sizeEvents - 1);
            personInstitutionRespTf = new TextFieldWithLanguage(event.getAgent().getContent(), event.getAgent().getLang());
        } else {
            personInstitutionRespTf = new TextFieldWithLanguage("", "");
        }
        personInstitutionRespTf.getTextField().setEnabled(false);
        builder.add(personInstitutionRespTf.getTextField(), cc.xy(3, rowNb));
        builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
        builder.add(personInstitutionRespTf.getLanguageBox(), cc.xy(7, rowNb));
        setNextRow();

        builder.addSeparator(labels.getString("eag2012.usedLanguages"), cc.xyw(1, rowNb, 3));
        JButton addLanguagesBtn = new ButtonEag(labels.getString("eag2012.addLanguagesButton"));
        builder.add(addLanguagesBtn, cc.xy(5, rowNb));
        addLanguagesBtn.addActionListener(new AddLanguagesBtnAction(eag, tabbedPane, model));
        setNextRow();

        if(eag.getControl().getLanguageDeclarations() == null)
            eag.getControl().setLanguageDeclarations(new LanguageDeclarations());
        if(eag.getControl().getLanguageDeclarations().getLanguageDeclaration().size() == 0) {
            LanguageDeclaration languageDeclaration = new LanguageDeclaration();
            languageDeclaration.setLanguage(new Language());
            languageDeclaration.setScript(new Script());
            eag.getControl().getLanguageDeclarations().getLanguageDeclaration().add(languageDeclaration);
        }
        int i = 0;
        languageWithScriptTfs = new ArrayList<LanguageWithScript>(eag.getControl().getLanguageDeclarations().getLanguageDeclaration().size());
        for(LanguageDeclaration languageDeclaration : eag.getControl().getLanguageDeclarations().getLanguageDeclaration()) {
            builder.addLabel(labels.getString("eag2012.language"),    cc.xy (1, rowNb));
            LanguageWithScript languageWithScript = new LanguageWithScript(languageDeclaration.getLanguage().getLanguageCode(), languageDeclaration.getScript().getScriptCode());
            languageWithScriptTfs.add(languageWithScript);
            builder.add(languageWithScript.getLanguageBox(),                     cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.script"),    cc.xy (5, rowNb));
            builder.add(languageWithScript.getScriptBox(), cc.xy(7, rowNb));
            setNextRow();
        }

        builder.addSeparator(labels.getString("eag2012.usedConventions"), cc.xyw(1, rowNb, 3));
        JButton addConventionBtn = new ButtonEag(labels.getString("eag2012.addConventionButton"));
        builder.add(addConventionBtn, cc.xy(5, rowNb));
        addConventionBtn.addActionListener(new AddConventionBtnAction(eag, tabbedPane, model));
        setNextRow();

        if(eag.getControl().getConventionDeclaration().size() == 0)
            eag.getControl().getConventionDeclaration().add(new ConventionDeclaration());
        rulesConventionTfs = new ArrayList<TextFieldWithLanguage>(eag.getControl().getConventionDeclaration().size());
        for(ConventionDeclaration conventionDeclaration : eag.getControl().getConventionDeclaration()) {
            builder.addLabel(labels.getString("eag2012.abbreviation"),    cc.xy (1, rowNb));
            TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(conventionDeclaration.getAbbreviation().getContent(), "", conventionDeclaration.getCitation().get(0).getContent());
            rulesConventionTfs.add(textFieldWithLanguage);
            builder.add(textFieldWithLanguage.getTextField(),                     cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.fullname"),    cc.xy (5, rowNb));
            builder.add(textFieldWithLanguage.getExtraField(),                     cc.xy (7, rowNb));
            setNextRow();
        }


        JButton exitBtn = new ButtonEag(labels.getString("eag2012.exitButton"));
        builder.add(exitBtn, cc.xy (1, rowNb));
        exitBtn.addActionListener(new ExitBtnAction());

        JButton previousTabBtn = new ButtonEag(labels.getString("eag2012.previousTabButton"));
        builder.add(previousTabBtn, cc.xy (3, rowNb));
        previousTabBtn.addActionListener(new ChangeTabBtnAction(eag, tabbedPane, model, false));

        JButton nextTabBtn = new ButtonEag(labels.getString("eag2012.nextTabButton"));
        builder.add(nextTabBtn, cc.xy (5, rowNb));
        nextTabBtn.addActionListener(new ChangeTabBtnAction(eag, tabbedPane, model, true));

        setNextRow();
        JButton saveBtn = new ButtonEag(labels.getString("eag2012.saveButton"));
        builder.add(saveBtn, cc.xy (5, rowNb));
        saveBtn.addActionListener(new SaveBtnAction(eag, tabbedPane, model));

        return builder.getPanel();
    }

    public class AddLanguagesBtnAction extends UpdateEagObject {
        AddLanguagesBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject(false);
            } catch (Eag2012FormException e) {
            }
            LanguageDeclaration languageDeclaration = new LanguageDeclaration();
            languageDeclaration.setLanguage(new Language());
            languageDeclaration.setScript(new Script());
            if(eag.getControl().getLanguageDeclarations() == null)
                eag.getControl().setLanguageDeclarations(new LanguageDeclarations());
            eag.getControl().getLanguageDeclarations().getLanguageDeclaration().add(languageDeclaration);
            reloadTabbedPanel(new EagControlPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 5);
        }
    }

    public class AddConventionBtnAction extends UpdateEagObject {
        AddConventionBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject(false);
            } catch (Eag2012FormException e) {
            }
            ConventionDeclaration conventionDeclaration = new ConventionDeclaration();
            conventionDeclaration.setAbbreviation(new Abbreviation());
            conventionDeclaration.getCitation().add(new Citation());
            eag.getControl().getConventionDeclaration().add(conventionDeclaration);
            reloadTabbedPanel(new EagControlPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 5);
        }
    }

    public class SaveBtnAction extends UpdateEagObject {
        SaveBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject(true);
                super.saveFile(eag.getControl().getRecordId().getValue());
                closeFrame();
            } catch (Eag2012FormException e) {
                reloadTabbedPanel(new EagControlPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 5);
            }
        }
    }

    public class ChangeTabBtnAction extends UpdateEagObject {
        private boolean isNextTab;
        ChangeTabBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model, boolean isNextTab) {
            super(eag, tabbedPane, model);
            this.isNextTab = isNextTab;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject(false);

                if(isNextTab) {
                    reloadTabbedPanel(new EagRelationsPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 6);
                    tabbedPane.setEnabledAt(6, true);
                    tabbedPane.setEnabledAt(5, false);
                } else {
                    reloadTabbedPanel(new EagDescriptionPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 4);
                    tabbedPane.setEnabledAt(4, true);
                    tabbedPane.setEnabledAt(5, false);
                }
            } catch (Eag2012FormException e) {
                reloadTabbedPanel(new EagControlPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 5);
            }
        }
    }

    public abstract class UpdateEagObject extends DefaultBtnAction {

        public UpdateEagObject(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        protected void updateEagObject(boolean save) throws Eag2012FormException {
            errors = new ArrayList<String>();

            boolean hasChanged = false;

            int sizeEvents = eag.getControl().getMaintenanceHistory().getMaintenanceEvent().size();
            if(sizeEvents > 0) {
                MaintenanceEvent event = eag.getControl().getMaintenanceHistory().getMaintenanceEvent().get(sizeEvents - 1);
                event.getAgent().setLang(personInstitutionRespTf.getLanguage());
            }

            if(languageWithScriptTfs.size() == 0 || (languageWithScriptTfs.size() == 1 && StringUtils.isEmpty(languageWithScriptTfs.get(0).getLanguage()))) {
                eag.getControl().setLanguageDeclarations(null);
            } else {
                eag.getControl().getLanguageDeclarations().getLanguageDeclaration().clear();
                for(LanguageWithScript languageWithScript : languageWithScriptTfs) {
                    if(StringUtils.isNotEmpty(languageWithScript.getLanguage())) {
                        LanguageDeclaration languageDeclaration = new LanguageDeclaration();
                        Language language = new Language();
                        language.setLanguageCode(languageWithScript.getLanguage());
                        language.setContent(languageWithScript.getLanguage());
                        Script script = new Script();
                        script.setScriptCode(languageWithScript.getScript());
                        script.setContent(languageWithScript.getScript());
                        languageDeclaration.setLanguage(language);
                        languageDeclaration.setScript(script);
                        eag.getControl().getLanguageDeclarations().getLanguageDeclaration().add(languageDeclaration);
                    }
                }
            }

            if(rulesConventionTfs.size() > 0) {
                eag.getControl().getConventionDeclaration().clear();
                for(TextFieldWithLanguage textFieldWithLanguage : rulesConventionTfs) {
                    if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                        ConventionDeclaration conventionDeclaration = new ConventionDeclaration();
                        Abbreviation abbreviation = new Abbreviation();
                        abbreviation.setContent(textFieldWithLanguage.getTextValue());
                        Citation citation = new Citation();
                        citation.setContent(textFieldWithLanguage.getExtraValue());
                        conventionDeclaration.setAbbreviation(abbreviation);
                        conventionDeclaration.getCitation().add(citation);
                        eag.getControl().getConventionDeclaration().add(conventionDeclaration);
                    }
                }

            }

            if(!errors.isEmpty()) {
                throw new Eag2012FormException("Errors in validation of EAG 2012");
            }
        }
    }
}
