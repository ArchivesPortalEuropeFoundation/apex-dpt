package eu.apenet.dpt.standalone.gui.eaccpf;

/*
 * #%L
 * Data Preparation Tool Standalone mapping tool
 * %%
 * Copyright (C) 2009 - 2014 Archives Portal Europe
 * %%
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and limitations under the Licence.
 * #L%
 */


import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang.StringUtils;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import eu.apenet.dpt.standalone.gui.ProfileListModel;
import eu.apenet.dpt.standalone.gui.Utilities;
import eu.apenet.dpt.standalone.gui.commons.ButtonTab;
import eu.apenet.dpt.standalone.gui.commons.DefaultBtnAction;
import eu.apenet.dpt.standalone.gui.commons.swingstructures.LanguageWithScript;
import eu.apenet.dpt.utils.eaccpf.AgencyCode;
import eu.apenet.dpt.utils.eaccpf.Control;
import eu.apenet.dpt.utils.eaccpf.EacCpf;
import eu.apenet.dpt.utils.eaccpf.Language;
import eu.apenet.dpt.utils.eaccpf.LanguageDeclaration;
import eu.apenet.dpt.utils.eaccpf.MaintenanceAgency;
import eu.apenet.dpt.utils.eaccpf.MaintenanceHistory;
import eu.apenet.dpt.utils.eaccpf.OtherRecordId;
import eu.apenet.dpt.utils.eaccpf.RecordId;
import eu.apenet.dpt.utils.eaccpf.Script;
import eu.apenet.dpt.utils.util.LanguageIsoList;
import eu.apenet.dpt.utils.util.XmlTypeEacCpf;

/**
 * Class for the panel "control" of the apeEAC-CPF creation form.
 */
public class EacCpfControlPanel extends EacCpfPanel {
	
	private String idAutogeneratedControl;
	private JTextField idControl; //disabled, extracted from eaccpf.getControl().getRecordId()
	private JTextField personInstitutionResponsibleTextField;
	private List<JTextField> localIdentifierForInstitution;	
	private JComboBox<String> scriptFirst;
	private JComboBox<String> languageFirst;
	private LanguageWithScript languageWithScript;
	private JTextField jTextFieldInstitutionForPersonResponsible;

	/**
	 * Constructor.
	 *
	 * @param eaccpf
	 * @param tabbedPane
	 * @param mainTabbedPane
	 * @param eacCpfFrame
	 * @param model
	 * @param labels
	 * @param entityType
	 * @param firstLanguage
	 * @param firstScript
	 */
	public EacCpfControlPanel(EacCpf eaccpf, JTabbedPane tabbedPane, JTabbedPane mainTabbedPane, JFrame eacCpfFrame, ProfileListModel model, ResourceBundle labels, XmlTypeEacCpf entityType, String firstLanguage, String firstScript) {
		super(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript);
	}

	/**
	 * Builds and answer the control tab for the given layout.
	 *
	 * @param errors List of errors.
	 * @return the control tab.
	 */
	protected JComponent buildEditorPanel(List<String> errors) {
		// Checks and initialize the errors list.
		if (errors == null) {
			errors = new ArrayList<String>(0);
		} else if (Utilities.isDev && errors.size() > 0) {
			LOG.info("Errors in form:");
			for (String error : errors) {
				LOG.info(error);
			}
		}

		// Define the layaout for the form.
		FormLayout layout = new FormLayout(
				"right:max(50dlu;p), 4dlu, 100dlu, 7dlu, right:p, 4dlu, 100dlu",
				EDITOR_ROW_SPEC);
		layout.setColumnGroups(new int[][] { { 1, 3, 5, 7 } });

		// Construct the panel.
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints(); // Constraints for the cells;

		// First row of the panel.
		builder = this.buildEntityTypeText(builder, cc);
		
        // Second row is the panel.
		builder = buildMainPanel(builder,cc);

		builder.addSeparator("", cc.xyw(1, this.rowNb, 7));
		setNextRow();
		JButton previousTabBtn = new ButtonTab(labels.getString("eaccpf.commons.previousTab"));
		builder.add(previousTabBtn, cc.xy (1, rowNb));
		previousTabBtn.addActionListener(new PreviousTabBtnAction(this.eaccpf, this.tabbedPane, this.model));

		// Row for exit and save buttons.
		setNextRow();
		JButton exitBtn = new ButtonTab(this.labels.getString("eaccpf.commons.exit"));
		builder.add(exitBtn, cc.xy(1, this.rowNb));
		exitBtn.addActionListener(new ExitBtnAction(this.eaccpf, this.tabbedPane, this.model));

		JButton saveBtn = new ButtonTab(labels.getString("eaccpf.commons.save"));
		builder.add(saveBtn, cc.xy (5, this.rowNb));
		saveBtn.addActionListener(new SaveBtnAction(this.eaccpf, this.tabbedPane, this.model));

		// Define the change tab listener.
		this.removeChangeListener();
		this.tabbedPane.addChangeListener(new ChangeTabListener (this.eaccpf, this.tabbedPane, this.model, 3));

		return builder.getPanel();
	}
	
	/**
	 * Method to build the label with the text related to the entity type.
	 *
	 * @param builder
	 * @param cc
	 * @return the PanelBuilder
	 */
	private PanelBuilder buildEntityTypeText(PanelBuilder builder, CellConstraints cc) {
		// First row of the panel.
		this.rowNb = 1;

		// Try to recover the type.
		String type = "";
		if (this.eaccpf != null
				&& this.eaccpf.getCpfDescription() != null
				&& this.eaccpf.getCpfDescription().getIdentity() != null
				&& this.eaccpf.getCpfDescription().getIdentity().getEntityType() != null
				&& this.eaccpf.getCpfDescription().getIdentity().getEntityType().getValue() != null
				&& !StringUtils.isEmpty(this.eaccpf.getCpfDescription().getIdentity().getEntityType().getValue())) {
			type = this.eaccpf.getCpfDescription().getIdentity().getEntityType().getValue();
		} else {
			type = this.entityType.getName();
		}
/*
		if (XmlTypeEacCpf.EAC_CPF_CORPORATEBODY.getName().equalsIgnoreCase(type)) {
			builder.addLabel(this.labels.getString("eaccpf.commons.type") + " " + this.labels.getString("eaccpf.commons.corporateBody"), cc.xyw(1, this.rowNb, 3));
			this.entityType = XmlTypeEacCpf.EAC_CPF_CORPORATEBODY;
		} else if (XmlTypeEacCpf.EAC_CPF_FAMILY.getName().equalsIgnoreCase(type)) {
			builder.addLabel(this.labels.getString("eaccpf.commons.type") + " " + this.labels.getString("eaccpf.commons.family"), cc.xyw(1, this.rowNb, 3));
			this.entityType = XmlTypeEacCpf.EAC_CPF_FAMILY;
		} else if (XmlTypeEacCpf.EAC_CPF_PERSON.getName().equalsIgnoreCase(type)) {
			builder.addLabel(this.labels.getString("eaccpf.commons.type") + " " + this.labels.getString("eaccpf.commons.person"), cc.xyw(1, this.rowNb, 3));
			this.entityType = XmlTypeEacCpf.EAC_CPF_PERSON;
		} else {
			builder.addLabel(this.labels.getString("eaccpf.commons.unrecognized.type"), cc.xyw (1, this.rowNb, 3));
		}

        this.setNextRow();
		builder.addSeparator("", cc.xyw(1, this.rowNb, 7));
*/
        this.setNextRow();

		return builder;
	}

	private PanelBuilder buildMainPanel(PanelBuilder builder, CellConstraints cc) {
		
		JLabel jLabelIdInTheApeEACCPF = new JLabel(this.labels.getString("eaccpf.control.idintheapeeaccpf"));
		builder.add(jLabelIdInTheApeEACCPF, cc.xy (1, rowNb));
		//extracted from dashboard implementation
		Random random = new Random();
		String value = "";
//		revert revision
//		String mainagencycode = (this.eaccpf.getControl().getMaintenanceAgency().getAgencyCode()!=null && this.eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue()!=null )?this.eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue():"";
		if(this.eaccpf.getControl().getRecordId().getValue()==null || this.eaccpf.getControl().getRecordId().getValue().isEmpty()){
			// The "<recordId>" should follow the pattern
			//     "eac_[eag:recordId]_[eac-cpf:recordId]"
			// as is described on issue #1348 comment 5.
			int fakeId = random.nextInt(1000000000);
			value = Integer.toString(fakeId);
//			value = "eac_" + mainagencycode + "_" + Integer.toString(fakeId);
		}else{
			value = this.eaccpf.getControl().getRecordId().getValue();
		}
		JTextField jTextFieldIdInTheApeEACCPF = new JTextField(value); 
		jTextFieldIdInTheApeEACCPF.setEnabled(false); //put like disabled, it's not editable
		builder.add(jTextFieldIdInTheApeEACCPF, cc.xy (3, rowNb));
		this.idAutogeneratedControl = jTextFieldIdInTheApeEACCPF.getText(); //It's not shown because field is autogenerated and it's not editabled (eaccpf object has no access to this value by form)
		setNextRow();
		
		JLabel jLabelLinkPersonResponsible = new JLabel(this.labels.getString("eaccpf.control.personinstitutionresponsiblefordescription"));
		builder.add(jLabelLinkPersonResponsible, cc.xy (1, rowNb));
		String content = "";
		if (StringUtils.isNotEmpty(responsible)) {
			content = responsible;
		} else if (this.eaccpf.getControl().getMaintenanceHistory().getMaintenanceEvent().size()>0
				&& this.eaccpf.getControl().getMaintenanceHistory().getMaintenanceEvent().get(this.eaccpf.getControl().getMaintenanceHistory().getMaintenanceEvent().size() - 1).getAgent() != null
                && this.eaccpf.getControl().getMaintenanceHistory().getMaintenanceEvent().get(this.eaccpf.getControl().getMaintenanceHistory().getMaintenanceEvent().size() - 1).getAgent().getContent() != null
                && !this.eaccpf.getControl().getMaintenanceHistory().getMaintenanceEvent().get(this.eaccpf.getControl().getMaintenanceHistory().getMaintenanceEvent().size() - 1).getAgent().getContent().isEmpty()) {
            content = this.eaccpf.getControl().getMaintenanceHistory().getMaintenanceEvent().get(this.eaccpf.getControl().getMaintenanceHistory().getMaintenanceEvent().size() - 1).getAgent().getContent();
        } else {
        	content = MAINTENANCE_AGENT_HUMAN;
        }
		
		JTextField jTextFieldPersonResponsible = new JTextField(content);
		builder.add(jTextFieldPersonResponsible, cc.xy (3, rowNb));
		this.personInstitutionResponsibleTextField = jTextFieldPersonResponsible;
		setNextRow();
		JLabel jLabelIdentifierForPersonResponsible = new JLabel(this.labels.getString("eaccpf.control.identifierofinstitutionresponsible") + "*");
		builder.add(jLabelIdentifierForPersonResponsible, cc.xy (1, rowNb));
		content = eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue()!=null?eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue():"";
		JTextField jTextFieldIdentifierForPersonResponsible = new JTextField(content);
		builder.add(jTextFieldIdentifierForPersonResponsible, cc.xy (3, rowNb));
		this.idControl = jTextFieldIdentifierForPersonResponsible;
		setNextRow();
		//Field to Institution responsible for the description
		JLabel jLabelInstitutionForPersonResponsible = new JLabel(this.labels.getString("eaccpf.control.institutionResponsibleForTheDescription"));
		builder.add(jLabelInstitutionForPersonResponsible, cc.xy(1, this.rowNb));
		String contentInstitution = this.eaccpf.getControl().getMaintenanceAgency().getAgencyName().getContent()!=null?this.eaccpf.getControl().getMaintenanceAgency().getAgencyName().getContent():"";
		this.jTextFieldInstitutionForPersonResponsible = new JTextField(contentInstitution);
		builder.add(this.jTextFieldInstitutionForPersonResponsible, cc.xy(3, this.rowNb));
		setNextRow();
		
		if(StringUtils.isEmpty(content)){
			builder.add(createErrorLabel(this.labels.getString("eaccpf.control.error.emptyidentifier")), cc.xyw(1, this.rowNb, 3));
			setNextRow();
		}
		if (this.eaccpf.getControl().getOtherRecordId().isEmpty()) {
			this.eaccpf.getControl().getOtherRecordId().add(new OtherRecordId());
		}

		List<OtherRecordId> otherRecordIds = this.eaccpf.getControl().getOtherRecordId();
		this.localIdentifierForInstitution = new ArrayList<JTextField>(otherRecordIds.size());
		for (OtherRecordId otherRecordId : otherRecordIds) {
			// Create element.
			JTextField jTextFieldLocalIdentifierPersonResponsible = new JTextField(otherRecordId.getContent());

			// Add elements to the list.
			this.localIdentifierForInstitution.add(jTextFieldLocalIdentifierPersonResponsible);
			builder.addLabel(this.labels.getString("eaccpf.control.otherRecordIdentifier"), cc.xy(1, this.rowNb));
			builder.add(jTextFieldLocalIdentifierPersonResponsible, cc.xy(3, this.rowNb));
			this.setNextRow();
		}
		
		JButton nextTabBtn = new ButtonTab(this.labels.getString("eaccpf.control.addlocalidentifier"));
		builder.add(nextTabBtn, cc.xy (1, this.rowNb));
		nextTabBtn.addActionListener(new AddLocalIdentifier(eaccpf,tabbedPane,model));
		setNextRow();
		
		builder.addLabel(this.labels.getString("eaccpf.control.usedlanguagesandscriptsfordescription"), cc.xyw(1, this.rowNb, 7));
		setNextRow();
		
		builder.addLabel(labels.getString("eaccpf.commons.select.language")+ "*" + ":", cc.xy(1, rowNb));
        LanguageWithScript languageWithScript = new LanguageWithScript(firstLanguage, firstScript, labels);
        JComboBox<String> scriptBox = languageWithScript.getScriptBox();
        //fix for script part, it's not selecting values
        boolean found = false;
        int size = scriptBox.getModel().getSize();
        for(int i=0;!found && i<size;i++){
        	String element = scriptBox.getModel().getElementAt(i);
        	if(element!=null && element.equalsIgnoreCase(firstScript)){
        		scriptBox.setSelectedIndex(i);
        		found = true;
        	}
        }
        //end fix for script part
        builder.add(languageWithScript.getLanguageBox(), cc.xy (3, rowNb));
        this.languageFirst = languageWithScript.getLanguageBox();
        builder.addLabel(labels.getString("eaccpf.control.selectascript")+ "*" + ":", cc.xy(5, rowNb));
        builder.add(languageWithScript.getScriptBox(), cc.xy(7, rowNb));
        this.scriptFirst = languageWithScript.getScriptBox();
        this.languageWithScript = languageWithScript;
        setNextRow();
		
		return builder;
	}
	
	public class AddLocalIdentifier extends UpdateEacCpfObject{
		
		public AddLocalIdentifier(EacCpf eacCpf, JTabbedPane tabbedPane,ProfileListModel model) {
			super(eacCpf, tabbedPane, model);
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				super.updateJAXBObject(false);
			} catch (EacCpfFormException e) {
				reloadTabbedPanel(new EacCpfControlPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 3);
			}

			boolean emptyId = false;
			boolean emptytype = false;
			for (int i = 0; !emptyId && !emptytype && i < localIdentifierForInstitution.size(); i++) {
				if (StringUtils.isEmpty(trimStringValue(localIdentifierForInstitution.get(i).getText()))) {
					JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.identity.error.empty.identifier"));
					emptyId = true;
				}
			}

			List<OtherRecordId> otherRecordIds = eaccpf.getControl().getOtherRecordId();
			if ((!emptyId && !emptytype)
					|| (otherRecordIds.size() > 0 && emptyId && emptytype)) {
				otherRecordIds.add(new OtherRecordId());
			}

			reloadTabbedPanel(new EacCpfControlPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 3);
		}
	}

	/**
	 * Method that removes the existing "ChangeTabListener".
	 */
	private void removeChangeListener() {
		// Check the current "ChangeListeners" and remove the non desired ones.
		ChangeListener[] changeListeners = this.tabbedPane.getChangeListeners();
		List<ChangeListener> changeListenerList = new LinkedList<ChangeListener>();
		for (int i = 0; i < changeListeners.length; i++) {
			ChangeListener changeListener = changeListeners[i];

			if (changeListener instanceof ChangeTabListener) {
				changeListenerList.add(changeListener);
			}
		}

		if (changeListenerList != null) {
			for (int i = 0; i < changeListenerList.size(); i++) {
				this.tabbedPane.removeChangeListener(changeListenerList.get(i));
			}
		}
	}
	
	protected boolean checkStartTabFields() {
		boolean state = true;
		if(firstLanguage==null || firstLanguage.isEmpty() || firstLanguage.equals("---")){
			state = false;
			this.tabbedPane.setComponentAt(0, null);
			JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.control.error.emptylanguage"));
		}else if(firstScript==null || firstScript.isEmpty() || firstScript.equals("---")){
			state = false;
			JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.control.error.emptyscript"));
		}
		return state;
	}

	/**
	 * Class to performs the actions when the user clicks on button save.
	 */
	public class SaveBtnAction extends UpdateEacCpfObject {
		SaveBtnAction(EacCpf eaccpf, JTabbedPane tabbedPane, ProfileListModel model) {
			super(eaccpf, tabbedPane, model);
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				super.updateJAXBObject(true);
				String content = eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue()!=null?eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue():"";
				if(!StringUtils.isEmpty(content)){
					eaccpf = cleanIncompleteData(eaccpf);
					eaccpf = updatesControl(eaccpf);
					super.saveFile(eaccpf.getControl().getRecordId().getValue());
				}
				reloadTabbedPanel(new EacCpfControlPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 3);
			} catch (EacCpfFormException e) {
				reloadTabbedPanel(new EacCpfControlPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 3);
			}
		}
	}

	/**
	 * Class to performs the actions when the user clicks on button for previous tab.
	 */
	public class PreviousTabBtnAction extends UpdateEacCpfObject {
		PreviousTabBtnAction(EacCpf eaccpf, JTabbedPane tabbedPane, ProfileListModel model) {
			super(eaccpf, tabbedPane, model);
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				super.updateJAXBObject(false);
				removeChangeListener();
				if(checkStartTabFields()){
					String content = eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue()!=null?eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue():"";
					if(StringUtils.isEmpty(content)){
						reloadTabbedPanel(new EacCpfControlPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 3);
					}else{
						reloadTabbedPanel(new EacCpfRelationsPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 2);
					}
				}
			} catch (EacCpfFormException e) {
				reloadTabbedPanel(new EacCpfControlPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 3);
			}
		}
	}

	/**
	 * Class for update the JABX EAC-CPF object.
	 */
	public abstract class UpdateEacCpfObject extends DefaultBtnAction {
		
		UpdateEacCpfObject(EacCpf eacCpf, JTabbedPane tabbedPane, ProfileListModel model) {
			super(eacCpf, tabbedPane, model);
		}

		protected void updateJAXBObject(boolean save) throws EacCpfFormException {
			errors = new ArrayList<String>();

			// Checks the person responsible
			String personResponsible = personInstitutionResponsibleTextField.getText();
			if (StringUtils.isNotEmpty(personResponsible)
					&& StringUtils.isNotEmpty(trimStringValue(personResponsible))) {
				responsible = personResponsible;
			} else {
				responsible = EacCpfPanel.MAINTENANCE_AGENT_HUMAN;
			}

			//empty values, instance all if necesary
			if(this.eaccpf.getControl()==null){
				this.eaccpf.setControl(new Control());
			}
			if(this.eaccpf.getControl().getMaintenanceHistory()==null){
				this.eaccpf.getControl().setMaintenanceHistory(new MaintenanceHistory());
			}
			if(this.eaccpf.getControl().getMaintenanceAgency()==null){
				this.eaccpf.getControl().setMaintenanceAgency(new MaintenanceAgency());
			}
			if(this.eaccpf.getControl().getMaintenanceAgency().getAgencyCode()==null){
				this.eaccpf.getControl().getMaintenanceAgency().setAgencyCode(new AgencyCode());
			}
			if(this.eaccpf.getControl().getRecordId()==null){
				this.eaccpf.getControl().setRecordId(new RecordId());
			}
			if(localIdentifierForInstitution!=null){
				this.eaccpf.getControl().getOtherRecordId().clear();
				for (int i = 0; i < localIdentifierForInstitution.size(); i++) {
					boolean updated = false;
					JTextField identifier = localIdentifierForInstitution.get(i);
					OtherRecordId otherRecordId= new OtherRecordId();
					if(!trimStringValue(identifier.getText()).isEmpty()){
						otherRecordId.setContent(trimStringValue(identifier.getText()));
						otherRecordId.setLocalType(EacCpfPanel.LOCAL_TYPE_ORIGINAL);
						updated = true;
					}
					if (updated) {
						this.eaccpf.getControl().getOtherRecordId().add(otherRecordId);
					}
				}
			}
			//idControl
			this.eaccpf.getControl().getMaintenanceAgency().getAgencyCode().setValue(trimStringValue(idControl.getText()));
			//agencyName
			this.eaccpf.getControl().getMaintenanceAgency().getAgencyName().setContent(jTextFieldInstitutionForPersonResponsible.getText());
			//comboBox updates
			firstLanguage = LanguageIsoList.getIsoCode(trimStringValue(languageFirst.getSelectedItem().toString()));
			firstScript = scriptFirst.getSelectedItem().toString();
			//generates a language declaration node
			LanguageDeclaration languageDeclaration = new LanguageDeclaration();
			//language converted
			Language language = new Language();
            language.setLanguageCode(firstLanguage);
            languageDeclaration.setLanguage(language);
            //script converted part
            Script script = new Script();
            if(!languageWithScript.getScriptBox().getSelectedItem().toString().equals("---")){
            	script.setScriptCode(trimStringValue(languageWithScript.getScript()));
            }
			languageDeclaration.setScript(script);
			//update language declaration
			this.eaccpf.getControl().setLanguageDeclaration(languageDeclaration);
			//update record id
			// The "<recordId>" should follow the pattern
			//     "eac_[eag:recordId]_[eac-cpf:recordId]"
			// as is described on issue #1348 comment 5.
//			reverted revision
			this.eaccpf.getControl().getRecordId().setValue(idAutogeneratedControl);
			if(!errors.isEmpty()) {
				throw new EacCpfFormException("Errors in validation of EAC-CPF");
			}
		}
	}

	/**
	 * Class to performs the actions when the user clicks in other tab.
	 */
	public class ChangeTabListener extends UpdateEacCpfObject implements ChangeListener {
		private int currentTab;
		ChangeTabListener(EacCpf eaccpf, JTabbedPane tabbedPane, ProfileListModel model, int indexTab) {
			super (eaccpf, tabbedPane, model);
			this.currentTab = indexTab;
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			int selectedIndex = this.tabbedPane.getSelectedIndex();
			// Checks if clicks in different tab.
			if (this.currentTab != selectedIndex) {
				try {
					super.updateJAXBObject(true);
					String content = eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue()!=null?eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue():"";
					removeChangeListener();
					if(!StringUtils.isEmpty(content) && checkStartTabFields()){
						switch (selectedIndex) {
							case 0:
								String mainagencycode = eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue();
								reloadTabbedPanel(new EacCpfIdentityPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, false, labels, entityType, firstLanguage, firstScript, mainagencycode).buildEditorPanel(errors), 0);
								break;
							case 1:
								reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 1);
								break;
							case 2:
								reloadTabbedPanel(new EacCpfRelationsPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 2);
								break;
							default:
								reloadTabbedPanel(new EacCpfControlPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 3);
						}
					}else{
						reloadTabbedPanel(new EacCpfControlPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 3);
					}
				} catch (EacCpfFormException ex) {
					reloadTabbedPanel(new EacCpfControlPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 3);
				}
			}
		}
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			// Empty.
		}
	}
	
	/**
	 * Class to performs the action when the user clicks in the exit button
	 */
	protected class ExitBtnAction extends UpdateEacCpfObject {
		/**
		 * Constructor.
		 *
		 * @param eaccpf
		 * @param tabbedPane
		 * @param model
		 */   
		public ExitBtnAction(EacCpf eaccpf, JTabbedPane tabbedPane, ProfileListModel model) {
			super(eaccpf, tabbedPane, model);
		}
		
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
        	int event = JOptionPane.showConfirmDialog(tabbedPane,labels.getString("eaccpf.commons.exitConfirm"),labels.getString("eaccpf.eacCpfItem"),JOptionPane.YES_NO_OPTION);
        	try{
	        	if(event == JOptionPane.YES_OPTION){
	        		super.updateJAXBObject(true);
	        		String content = eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue()!=null?eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue():"";
	        		if(StringUtils.isEmpty(content)){
	        			reloadTabbedPanel(new EacCpfControlPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 3);
	        		}else if(checkStartTabFields()){
	        			super.saveFile(eaccpf.getControl().getRecordId().getValue());
	        			closeFrame();
	        		}
	        	}else if(event == JOptionPane.NO_OPTION){	
	        		EacCpfFrame.inUse(false);
	                closeFrame();
	        	}
			} catch (EacCpfFormException e) {
				reloadTabbedPanel(new EacCpfControlPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 3);
			}
        }
    }
}