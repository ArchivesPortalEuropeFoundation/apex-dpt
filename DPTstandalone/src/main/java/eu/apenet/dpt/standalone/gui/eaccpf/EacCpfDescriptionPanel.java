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

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import eu.apenet.dpt.standalone.gui.commons.swingstructures.CommonsPropertiesPanels;
import eu.apenet.dpt.standalone.gui.commons.swingstructures.ScrollPane;
import eu.apenet.dpt.standalone.gui.commons.swingstructures.TextAreaWithLanguage;
import eu.apenet.dpt.standalone.gui.commons.swingstructures.TextFieldWithLanguage;
import eu.apenet.dpt.standalone.gui.eaccpf.swingstructures.TextFieldWithComboBoxEacCpf;
import eu.apenet.dpt.standalone.gui.eaccpf.swingstructures.TextFieldsWithRadioButtonForDates;
import eu.apenet.dpt.standalone.gui.listener.FocusManagerListener;
import eu.apenet.dpt.utils.eaccpf.Address;
import eu.apenet.dpt.utils.eaccpf.AddressLine;
import eu.apenet.dpt.utils.eaccpf.BiogHist;
import eu.apenet.dpt.utils.eaccpf.CpfDescription;
import eu.apenet.dpt.utils.eaccpf.Date;
import eu.apenet.dpt.utils.eaccpf.DateRange;
import eu.apenet.dpt.utils.eaccpf.DateSet;
import eu.apenet.dpt.utils.eaccpf.Description;
import eu.apenet.dpt.utils.eaccpf.DescriptiveNote;
import eu.apenet.dpt.utils.eaccpf.EacCpf;
import eu.apenet.dpt.utils.eaccpf.ExistDates;
import eu.apenet.dpt.utils.eaccpf.FromDate;
import eu.apenet.dpt.utils.eaccpf.Function;
import eu.apenet.dpt.utils.eaccpf.Functions;
import eu.apenet.dpt.utils.eaccpf.Occupation;
import eu.apenet.dpt.utils.eaccpf.Occupations;
import eu.apenet.dpt.utils.eaccpf.P;
import eu.apenet.dpt.utils.eaccpf.Place;
import eu.apenet.dpt.utils.eaccpf.PlaceEntry;
import eu.apenet.dpt.utils.eaccpf.PlaceRole;
import eu.apenet.dpt.utils.eaccpf.Places;
import eu.apenet.dpt.utils.eaccpf.RelationEntry;
import eu.apenet.dpt.utils.eaccpf.Relations;
import eu.apenet.dpt.utils.eaccpf.ResourceRelation;
import eu.apenet.dpt.utils.eaccpf.StructureOrGenealogy;
import eu.apenet.dpt.utils.eaccpf.Term;
import eu.apenet.dpt.utils.eaccpf.ToDate;
import eu.apenet.dpt.utils.util.LanguageIsoList;
import eu.apenet.dpt.utils.util.XmlTypeEacCpf;

/**
 * Class for the panel "description" of the apeEAC-CPF creation form.
 */
public class EacCpfDescriptionPanel extends EacCpfPanel {

	private static final String PLACES = "places";
	private static final String FUNCTIONS = "functions";
	private static final String OCCUPATIONS = "occupations";
	
	private List<String> countriesListWithEmpty;
	private List<JTextField> placeEntryPlaceTextFields;
	private List<JComboBox> placeEntryPlaceJComboBoxs;
	private List<JTextField> placeEntryPlaceVocabularies;
	private List<JComboBox> placeEntryCountryJComboBoxs;
	private List<TextFieldWithComboBoxEacCpf> placeEntryPlaceRoles;
	private List<List<TextFieldWithComboBoxEacCpf>> addressDetailsTextFieldsWithComboBoxes;
	private List<JButton> placeEntryAddressDetailsButton;
	private List<JButton> placeEntryDateButton;
	private List<JButton> placeEntryDateRangeButton;
	private List<JTextField> placesFunctionJTextfield;
	private List<JComboBox> placesFunctionJComboBoxes;
	private List<JTextField> placesVocabularyJTextFields;
	private List<TextAreaWithLanguage> placesDescriptionTextfields;
	private List<List<JTextField>> placeFunctionPlaceJtextfields;
	private List<List<JComboBox>> placeFunctionPlaceJComboBoxes;
	private List<JTextField> ocupationPlaceOcupationJTextfields;
	private List<JComboBox> ocupationPlaceOcupationLanguagesJComboboxes;
	private List<JTextField> ocupationPlaceLinkToControlledVocabularyTextFields;
	private List<List<JTextField>> ocupationPlacePlaceJTextFields;
	private List<List<JComboBox>> ocupationPlaceCountryPlaceJComboBoxes;
	private List<TextAreaWithLanguage> ocupationPlaceOcupationDescriptionTextFields;
	private List<TextAreaWithLanguage> genealogyTextFields;
	private List<JComboBox> genealogyLanguagesJComboBoxes;
	private List<TextAreaWithLanguage> biographyHistoryJTextfields;
	private List<JComboBox> biographyHistoryJComboBoxes;
	
	private List<TextFieldWithLanguage> resourcesRelationTitle;
	private List<JTextField> resourcesRelationIdentifier;
	private List<JTextField> resourcesRelationLink;
	private List<JTextField> resourcesRelationAgencyName;
	private List<TextFieldsWithRadioButtonForDates> resourcesRelationDate;
	private List<List<TextAreaWithLanguage>> resourcesRelationDescriptive;
	
	//DATES, using the same structure made into EacCpfIdentityPanel
	//Map<Integer - index of place/function/occupation in this tab, TextFieldsWithRadioButtonForDates - structured content
	private Map<Integer, List<TextFieldsWithRadioButtonForDates>> placesDates;
	private Map<Integer, List<TextFieldsWithRadioButtonForDates>> functionsDates;
	private Map<Integer, List<TextFieldsWithRadioButtonForDates>> occupationsDates;
	private Map<String, String> countriesMap;
	
	private boolean showErrorDateRange = true;

	public boolean isShowErrorDateRange() {
		return this.showErrorDateRange;
	}

	public void setShowErrorDateRange(boolean showErrorDateRange) {
		this.showErrorDateRange = showErrorDateRange;
	}
	
	
	/** METHODS TO APPEND FORM OBJECTS TO BE MANAGED IN UPDATE EACCPF OBJECT **/
	private void appendPlaceEntryRole(TextFieldWithComboBoxEacCpf jTextfieldRolePlace) {
		if(this.placeEntryPlaceRoles==null){
			this.placeEntryPlaceRoles = new ArrayList<TextFieldWithComboBoxEacCpf>();
		}
		this.placeEntryPlaceRoles.add(jTextfieldRolePlace);
	}

	private void appendPlaceEntryCountry(JComboBox jComboboxCountry) {
		if(this.placeEntryCountryJComboBoxs==null){
			this.placeEntryCountryJComboBoxs = new ArrayList<JComboBox>();
		}
		this.placeEntryCountryJComboBoxs.add(jComboboxCountry);
	}

	private void appendPlaceEntryVocabularyPlace(JTextField jTextfieldControlledLabelPlace) {
		if(this.placeEntryPlaceVocabularies==null){
			this.placeEntryPlaceVocabularies = new ArrayList<JTextField>();
		}
		this.placeEntryPlaceVocabularies.add(jTextfieldControlledLabelPlace);
	}

	private void appendPlaceEntryPlace(JTextField textField,JComboBox languageBox) {
		if(this.placeEntryPlaceTextFields==null){
			this.placeEntryPlaceTextFields = new ArrayList<JTextField>();
		}
		this.placeEntryPlaceTextFields.add(textField);
		if(this.placeEntryPlaceJComboBoxs==null){
			this.placeEntryPlaceJComboBoxs = new ArrayList<JComboBox>();
		}
		this.placeEntryPlaceJComboBoxs.add(languageBox);
	}
	
	private void addGenealogyLanguage(JComboBox genealogyLanguageComboBox) {
		if(this.genealogyLanguagesJComboBoxes==null){
			this.genealogyLanguagesJComboBoxes = new ArrayList<JComboBox>();
		}
		this.genealogyLanguagesJComboBoxes.add(genealogyLanguageComboBox);
	}

	private void addGenealogyTextField(TextAreaWithLanguage genealogyTextField) {
		if(this.genealogyTextFields==null){
			this.genealogyTextFields = new ArrayList<TextAreaWithLanguage>();
		}
		this.genealogyTextFields.add(genealogyTextField);
	}
	
	private void addBiographyHistoryJComboBoxes(JComboBox languageJComboBox) {
		if(this.biographyHistoryJComboBoxes==null){
			this.biographyHistoryJComboBoxes = new ArrayList<JComboBox>();
		}
		this.biographyHistoryJComboBoxes.add(languageJComboBox);
	}

	private void addBiographyHistoryJTextfield(TextAreaWithLanguage targetBiographyHistory) {
		if(this.biographyHistoryJTextfields==null){
			this.biographyHistoryJTextfields = new ArrayList<TextAreaWithLanguage>();
		}
		this.biographyHistoryJTextfields.add(targetBiographyHistory);
	}

	private void appendAddressDetails(boolean newAddressLine,TextFieldWithComboBoxEacCpf jTextfieldAddress) {
		if(this.addressDetailsTextFieldsWithComboBoxes == null){
			this.addressDetailsTextFieldsWithComboBoxes = new ArrayList<List<TextFieldWithComboBoxEacCpf>>();
		}
		if(newAddressLine){
			this.addressDetailsTextFieldsWithComboBoxes.add(new ArrayList<TextFieldWithComboBoxEacCpf>());
		}
		this.addressDetailsTextFieldsWithComboBoxes.get(this.addressDetailsTextFieldsWithComboBoxes.size()-1).add(jTextfieldAddress);
	}

	private void addPlaceEntryAddressDetailsButton(JButton targetAddressDetailsButton) {
		if(this.placeEntryAddressDetailsButton==null){
			this.placeEntryAddressDetailsButton = new ArrayList<JButton>();
		}
		this.placeEntryAddressDetailsButton.add(targetAddressDetailsButton);
	}

	private void addPlaceEntryDateButton(JButton targetDateButton) {
		if(this.placeEntryDateButton==null){
			this.placeEntryDateButton = new ArrayList<JButton>();
		}
		this.placeEntryDateButton.add(targetDateButton);
	}

	private void addPlaceEntryDateRangeButton(JButton targetDateRangeButton) {
		if(this.placeEntryDateRangeButton==null){
			this.placeEntryDateRangeButton = new ArrayList<JButton>();
		}
		this.placeEntryDateRangeButton.add(targetDateRangeButton);
	}
	
	private void addOcupationPlaceOcupationDescription(TextAreaWithLanguage jTextFieldLinkDescriptionOcupation) {
		if(this.ocupationPlaceOcupationDescriptionTextFields==null){
			this.ocupationPlaceOcupationDescriptionTextFields = new ArrayList<TextAreaWithLanguage>();
		}
		this.ocupationPlaceOcupationDescriptionTextFields.add(jTextFieldLinkDescriptionOcupation);
	}

	private void addOcupationPlaceCountryPlaceJComboBoxes(boolean newInstance,JComboBox jComboboxLanguageOcupationPlace) {
		if(this.ocupationPlaceCountryPlaceJComboBoxes == null){
			this.ocupationPlaceCountryPlaceJComboBoxes = new ArrayList<List<JComboBox>>();
		}
		if(newInstance){
			this.ocupationPlaceCountryPlaceJComboBoxes.add(new ArrayList<JComboBox>());
		}
		this.ocupationPlaceCountryPlaceJComboBoxes.get(this.ocupationPlaceCountryPlaceJComboBoxes.size()-1).add(jComboboxLanguageOcupationPlace);
	}

	private void addOcupationPlacePlaceJTextField(boolean newInstance,JTextField placeTextField) {
		if(this.ocupationPlacePlaceJTextFields==null){
			this.ocupationPlacePlaceJTextFields = new ArrayList<List<JTextField>>();
		}
		if(newInstance){
			this.ocupationPlacePlaceJTextFields.add(new ArrayList<JTextField>());
		}
		this.ocupationPlacePlaceJTextFields.get(this.ocupationPlacePlaceJTextFields.size()-1).add(placeTextField);
	}

	private void addOcupationPlaceLinkToControlledVocabulary(JTextField jLinkOcupationOcupation) {
		if(this.ocupationPlaceLinkToControlledVocabularyTextFields==null){
			this.ocupationPlaceLinkToControlledVocabularyTextFields = new ArrayList<JTextField>();
		}
		this.ocupationPlaceLinkToControlledVocabularyTextFields.add(jLinkOcupationOcupation);
	}

	private void addOcupationPlaceOcupationLanguageJComboBox(JComboBox jComboBoxLanguage) {
		if(this.ocupationPlaceOcupationLanguagesJComboboxes==null){
			this.ocupationPlaceOcupationLanguagesJComboboxes = new ArrayList<JComboBox>();
		}
		this.ocupationPlaceOcupationLanguagesJComboboxes.add(jComboBoxLanguage);
	}

	private void addOcupationPlaceOcupationJTextField(JTextField textField) {
		if(this.ocupationPlaceOcupationJTextfields==null){
			this.ocupationPlaceOcupationJTextfields=new ArrayList<JTextField>();
		}
		this.ocupationPlaceOcupationJTextfields.add(textField);
	}
	
	private void addPlaceFunctionCountryJComboBoxes(boolean first,JComboBox jComboboxCountry) {
		if(this.placeFunctionPlaceJComboBoxes == null){
			this.placeFunctionPlaceJComboBoxes = new ArrayList<List<JComboBox>>();
		}
		if(first){
			this.placeFunctionPlaceJComboBoxes.add(new ArrayList<JComboBox>());
		}
		this.placeFunctionPlaceJComboBoxes.get(this.placeFunctionPlaceJComboBoxes.size()-1).add(jComboboxCountry);
	}

	private void addPlaceFunctionPlaceJTextfields(boolean first,JTextField jTextfieldPlace) {
		if(this.placeFunctionPlaceJtextfields == null){
			this.placeFunctionPlaceJtextfields = new ArrayList<List<JTextField>>();
		}
		if(first){
			this.placeFunctionPlaceJtextfields.add(new ArrayList<JTextField>());
		}
		this.placeFunctionPlaceJtextfields.get(this.placeFunctionPlaceJtextfields.size()-1).add(jTextfieldPlace);
	}

	private void addPlaceFunctionDescriptionTextfields(TextAreaWithLanguage jTextfieldDescription) {
		if(this.placesDescriptionTextfields==null){
			this.placesDescriptionTextfields = new ArrayList<TextAreaWithLanguage>();
		}
		this.placesDescriptionTextfields.add(jTextfieldDescription);
	}

	private void addPlaceFunctionVocabularyJTextField(JTextField jTextfieldVocabularyLink) {
		if(this.placesVocabularyJTextFields==null){
			this.placesVocabularyJTextFields=new ArrayList<JTextField>();
		}
		this.placesVocabularyJTextFields.add(jTextfieldVocabularyLink);
	}

	private void addPlaceFunctionLanguageJComboBox(JComboBox jComboboxLanguage) {
		if(this.placesFunctionJComboBoxes==null){
			this.placesFunctionJComboBoxes = new ArrayList<JComboBox>();
		}
		this.placesFunctionJComboBoxes.add(jComboboxLanguage);
	}

	private void addPlaceFunctionPlacesJTextfield(JTextField jTextfieldFunction) {
		if(this.placesFunctionJTextfield==null){
			this.placesFunctionJTextfield = new ArrayList<JTextField>();
		}
		this.placesFunctionJTextfield.add(jTextfieldFunction);
	}
	
	/**
	 * Constructor.
	 *
	 * @param eaccpf
	 * @param tabbedPane
	 * @param mainTabbedPane
	 * @param eacCpfFrame
	 * @param model
	 * @param labels
	 */
	public EacCpfDescriptionPanel(EacCpf eaccpf, JTabbedPane tabbedPane, JTabbedPane mainTabbedPane, JFrame eacCpfFrame, ProfileListModel model, ResourceBundle labels, XmlTypeEacCpf entityType, String firstLanguage, String firstScript) {
		super(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript);
	}

	/**
	 * Builds and answer the description tab for the given layout.
	 *
	 * @param errors List of errors.
	 * @return the description tab.
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

		// Define the layout for the form.
		FormLayout layout = new FormLayout(
                "right:max(50dlu;p), 4dlu, 100dlu, 7dlu, right:p, 4dlu, 100dlu",
                EDITOR_ROW_SPEC);
		layout.setColumnGroups(new int[][] { { 1, 3, 5, 7 } });

		// Construct the panel.
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints(); // Constraints for the cells;

        // Second row of the panel.
		builder = buildMainFramePanel(builder,cc);

		// Call method to build the main buttons zone.
		this.buildButtons(builder, cc);

		// Define the change tab listener.
		this.removeChangeListener();
		this.tabbedPane.addChangeListener(new ChangeTabListener (this.eaccpf, this.tabbedPane, this.model, 1));

		JPanel panel = builder.getPanel();
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(new FocusManagerListener(panel));
		return panel;
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
        this.setNextRow();

		return builder;
	}

	/**
	 * Method to build the main buttons zone.
	 *
	 * @param builder the PanelBuilder to add the buttons.
	 * @param cc the constraints to use.
	 * @return the PanelBuilder with the buttons.
	 */
	private PanelBuilder buildButtons(PanelBuilder builder, CellConstraints cc) {
		// Row for the next and previous tab buttons.
		setNextRow();
		builder.addSeparator("", cc.xyw(1, this.rowNb, 7));
		setNextRow();
		JButton previousTabBtn = new ButtonTab(this.labels.getString("eaccpf.commons.previousTab"));
		builder.add(previousTabBtn, cc.xy (1, rowNb));
		previousTabBtn.addActionListener(new ChangeTabBtnAction(this.eaccpf, this.tabbedPane, this.model, false));

		JButton nextTabBtn = new ButtonTab(this.labels.getString("eaccpf.commons.nextTab"));
		builder.add(nextTabBtn, cc.xy (5, this.rowNb));
		nextTabBtn.addActionListener(new ChangeTabBtnAction(this.eaccpf, this.tabbedPane, this.model, true));

		// Row for exit and save buttons.
		setNextRow();
		JButton exitBtn = new ButtonTab(this.labels.getString("eaccpf.commons.exit"));
		builder.add(exitBtn, cc.xy(1, this.rowNb));
		exitBtn.addActionListener(new ExitBtnAction(this.eaccpf, this.tabbedPane, this.model));

		JButton saveBtn = new ButtonTab(this.labels.getString("eaccpf.commons.save"));
		builder.add(saveBtn, cc.xy (5, this.rowNb));
		saveBtn.addActionListener(new SaveBtnAction(this.eaccpf, this.tabbedPane, this.model));

		return builder;
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
	/**
	 * Main method which returns a builder with all form components
	 */
	private PanelBuilder buildMainFramePanel(PanelBuilder builder, CellConstraints cc) {
		fillListModels();
		// Call method to build the text of the entity type.
		builder = buildEntityTypeText(builder, cc);
		builder = buildPlaceLocationDescriptionForm(builder,cc);
		builder = buildBiographiesForm(builder,cc);
		builder = buildImageEacCpfForm(builder,cc);
		return builder;
	}
	/**
	 * Fill all list used into main-frame panel
	 */
	private void fillListModels() {
		//fill countries
		String[] locales = Locale.getISOCountries();
		countriesListWithEmpty = new ArrayList<String>();
        countriesListWithEmpty.add(TextFieldWithComboBoxEacCpf.DEFAULT_VALUE);
		countriesMap = new HashMap<String,String>();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            countriesListWithEmpty.add(obj.getDisplayCountry(Locale.ENGLISH));
            countriesMap.put(obj.getDisplayCountry(Locale.ENGLISH),obj.getISO3Country());
        }
        //DATES
        this.placesDates = new HashMap<Integer, List<TextFieldsWithRadioButtonForDates>>();
        this.functionsDates = new HashMap<Integer, List<TextFieldsWithRadioButtonForDates>>();
        this.occupationsDates = new HashMap<Integer, List<TextFieldsWithRadioButtonForDates>>();
	}
	/**
	 * Method which builds place location and descriptions related with places
	 */
	private PanelBuilder buildPlaceLocationDescriptionForm(PanelBuilder builder, CellConstraints cc) {
		if (this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses() != null
                && !this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().isEmpty()) {
			boolean places = false;
			boolean functions = false;
			boolean ocupations = false;
			boolean structureOrGenealogy = false;
			boolean lastPlaces = false;
			boolean lastFunctions = false;
			boolean lastOccupations = false;
			int counter = this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();
			for(int i=0;i<counter;i++){
				Object object = this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i);
				boolean flagIsFirst = false;
				
				//check if there is the neccesary instancesof, if not the form needs to show before section
				if(object instanceof Places){
					flagIsFirst = !places;
					places = true;
					if(i+1==counter){ 
						lastPlaces = true;
					}
				}else if (object instanceof Functions) {
					flagIsFirst = !functions;
					functions = true;
					if(!places){
						builder = buildPlaces(builder,cc,null);
						places = true;
					}
					if(i+1==counter){ 
						lastFunctions = true;
					}
				}else if (object instanceof Occupations) {
					flagIsFirst = !ocupations;
					ocupations = true;
					if(!places){
						builder = buildPlaces(builder,cc,null);
					}
					if(!functions){
						builder = buildFunctions(builder,cc,null,true);
						functions = true;
					}
					if(i+1==counter){ 
						lastOccupations = true;
					}
				}else if (object instanceof StructureOrGenealogy) {
					flagIsFirst = !structureOrGenealogy;
					structureOrGenealogy = true;
					if(!places){
						builder = buildPlaces(builder,cc,null);
					}
					if(!functions){
						builder = buildFunctions(builder,cc,null,true);
						functions = true;
					}
					if(!ocupations){
						builder = buildOcupation(builder,cc,null,true);
						ocupations = true;
					}
				}
				
				builder = buildPlaceByPlacesLocationDescription(object,builder,cc,flagIsFirst); //build current form
				//if it's the last element, call the next elements to be written at empty way
				if(lastPlaces){
					builder = buildFunctions(builder,cc,null,true);
					builder = buildOcupation(builder,cc,null,true);
					builder = buildGenealogy(builder,cc,null,true);
				}else if(lastFunctions){
					builder = buildOcupation(builder,cc,null,true);
					builder = buildGenealogy(builder,cc,null,true);
				}else if(lastOccupations){
					builder = buildGenealogy(builder,cc,null,true);
				}
			}
		}else{ //put other one empty, it calls to else of next method
			builder = buildPlaceByPlacesLocationDescription(null,builder,cc,true);
		}
		return builder;
	}
	/**
	 * Method which builds biography parts
	 */
	private PanelBuilder buildBiographiesForm(PanelBuilder builder,CellConstraints cc) {
		builder.addSeparator(this.labels.getString("eaccpf.description.biography"), cc.xyw(1, this.rowNb, 7));
		setNextRow();
		if (this.eaccpf.getCpfDescription() != null && this.eaccpf.getCpfDescription().getDescription() != null
                && this.eaccpf.getCpfDescription().getDescription().getBiogHist() != null) {
			builder = buildBiographies(builder,cc,this.eaccpf.getCpfDescription().getDescription().getBiogHist());
		}else{
			builder = buildBiography(builder,cc,null,null,true);
		}
		setNextRow();
		JButton jButtonAddFurtherBiographyDescription = new ButtonTab(this.labels.getString("eaccpf.description.button.addfurtherbiographydescription"));
		jButtonAddFurtherBiographyDescription.addActionListener(new AddFurtherBiographyDescriptionButton(this.eaccpf, this.tabbedPane, model));
		builder.add(jButtonAddFurtherBiographyDescription, cc.xy (1, rowNb));
		setNextRow();
		return builder;
	}
	/**
	 * Builds biographies part, manages P objects with content and language
	 */
	private PanelBuilder buildBiographies(PanelBuilder builder,CellConstraints cc, List<BiogHist> biogHistList) {
		if(biogHistList!=null && !biogHistList.isEmpty()){
			for (BiogHist biogHist : biogHistList) {
//	            if (biogHist.getChronListOrPOrCitation() != null && !biogHist.getChronListOrPOrCitation().isEmpty()) {
	            	List<Object> paragraphs = biogHist.getChronListOrPOrCitation();
	            	String content = "";
	            	String lang = "";
	                for (Object paragraph : paragraphs) {
	                    if (paragraph instanceof P) {
	                    	P par = ((P) paragraph);
	                    	content = par.getContent();
	                    	lang = par.getLang();
	                    	builder = buildBiography(builder,cc,content,lang,false);
	                    }
	                }
//	            }
			}
		}else{
			builder = buildBiography(builder,cc,null,null,true);
		}
		return builder;
	}
	/**
	 * Method which build the image for the Eac-Cpf
	 * @param builder
	 * @param cc
	 * @return
	 */
	private PanelBuilder buildImageEacCpfForm(PanelBuilder builder,CellConstraints cc) {
		if (this.eaccpf.getCpfDescription().getRelations()!=null && 
				!this.eaccpf.getCpfDescription().getRelations().getResourceRelation().isEmpty()) {
			
			List<ResourceRelation> resourceRelations = this.eaccpf.getCpfDescription().getRelations().getResourceRelation();
			boolean draw = false;
			int counter = 0;
			for (int i = 0; i < resourceRelations.size(); i++) {
				
				ResourceRelation resourceRelation = resourceRelations.get(i);
				
				if(resourceRelation != null && StringUtils.isNotEmpty(resourceRelation.getArcrole()) && resourceRelation.getArcrole().equals(ARCROLE_IMAGE)){
					draw = true;
						// Recovers the values of the relation entry elements.
						String title = "";
						String language = "";
						String id = "";
						String link ="";
						String agencyName = "";
						Date date = null;
						List<String> descriptiveNote = new ArrayList<String>();
						int index=counter;
						for (RelationEntry relationEntry : resourceRelation.getRelationEntry()) {
							// Title of the relation.
							// /eacCpf/cpfDescription/relations/resourceRelation/relationEntry@localType='title' and @xml:lang='???'
							if(StringUtils.isNotEmpty(relationEntry.getLocalType()) && relationEntry.getLocalType().equals(EacCpfRelationsPanel.LOCALTYPE_TITLE)) {
								if(StringUtils.isNotEmpty(relationEntry.getContent())){
									title = relationEntry.getContent();
									
								}
							}
							
							// Id of the relation.
				            // /eacCpf/cpfDescription/relations/resourceRelation/relationEntry@localType='id'
							if(StringUtils.isNotEmpty(relationEntry.getLocalType()) && relationEntry.getLocalType().equals(EacCpfRelationsPanel.LOCALTYPE_ID)
									&& StringUtils.isNotEmpty(relationEntry.getContent())) {
								id = relationEntry.getContent();
								
							}
							// Agency name of the relation.
							// /eacCpf/cpfDescription/relations/resourceRelation/relationEntry@localType='agencyName'
							if(StringUtils.isNotEmpty(relationEntry.getLocalType()) && relationEntry.getLocalType().equals(EacCpfRelationsPanel.LOCALTYPE_AGENCYNAME)) {
								agencyName = relationEntry.getContent();
								
							}
							// Language of the relation.
							// /eacCpf/cpfDescription/relations/resourceRelation/relationEntry@lang
							if(StringUtils.isEmpty(language) && StringUtils.isNotEmpty(relationEntry.getLang())) {
								language = relationEntry.getLang();
							}
						}
							
						//Link the image /eacCpf/cpfDescription/relations/resourceRelation@xlink:href
						if(StringUtils.isNotEmpty(resourceRelation.getHref())){
							link = resourceRelation.getHref();
						
						}
						
						//date in resourceRelation
						
						if(resourceRelation.getDate() != null){
							if (StringUtils.isNotEmpty(resourceRelation.getDate().getContent())){
								date = resourceRelation.getDate();
							
							}
						}
						
						//descriptiveNote in resourceRelation
						
						if(resourceRelation.getDescriptiveNote() != null && !resourceRelation.getDescriptiveNote().getP().isEmpty()
				                /*&& StringUtils.isNotEmpty(resourceRelation.getDescriptiveNote().getP().get(0).getContent())*/){
							for (P p: resourceRelation.getDescriptiveNote().getP()) {
								descriptiveNote.add(p.getContent());
							}
							if (StringUtils.isEmpty(language)) {
								language = resourceRelation.getDescriptiveNote().getP().get(0).getLang();
							}	
							
						}
							
						if (StringUtils.isEmpty(language)
								&& StringUtils.isNotEmpty(firstLanguage)
								&& (resourceRelation!=null 
									&& StringUtils.isEmpty(resourceRelation.getHref())
									&& (resourceRelation.getDate() == null
										|| (resourceRelation.getDate()!=null && StringUtils.isEmpty(resourceRelation.getDate().getContent())))
									&& (resourceRelation.getDescriptiveNote() == null 
										|| (resourceRelation.getDescriptiveNote() != null
											&& resourceRelation.getDescriptiveNote().getP().isEmpty()))) 
								|| resourceRelation == null) {
							language = firstLanguage;
						}
					drawImageEacCpfForm(builder,cc,title,language, id, link, agencyName, date, descriptiveNote,index);
					counter++;
				}
			} 
			if(!draw){
				drawImageEacCpfForm(builder,cc,null,(firstLanguage!=null && !firstLanguage.isEmpty())?firstLanguage:"",null,null,null, null, null,0);
			}
		}else{	
			drawImageEacCpfForm(builder,cc,null,(firstLanguage!=null && !firstLanguage.isEmpty())?firstLanguage:"",null,null,null, null, null,0);
		}
		
		setNextRow();
		JButton jButtonAddFurtherImage= new ButtonTab(this.labels.getString("eaccpf.description.button.addfurtherImage"));
		jButtonAddFurtherImage.addActionListener(new AddFurtherImageButton(this.eaccpf, this.tabbedPane, model));
		builder.add(jButtonAddFurtherImage, cc.xy (1, rowNb));
		setNextRow();
		return builder;
	}
	/**
	 * This method draw the different image's fields in the form
	 */
	private PanelBuilder drawImageEacCpfForm(PanelBuilder builder,CellConstraints cc,String title,String language, String id, String link, String agencyName, Date dateResource, List<String> descriptiveNote,int index){
		// Create elements.
		TextFieldWithLanguage resourceRelationTitle = null;
		JTextField resourceRelationIdentifier = null;
		JTextField resourceRelationLink = null;
		JTextField resourceRelationAgencyName = null;
		TextFieldsWithRadioButtonForDates resourceRelationDate = null;
		List<TextAreaWithLanguage> resourceRelationDescriptiveList = new ArrayList<TextAreaWithLanguage>();
		
		resourceRelationTitle = new TextFieldWithLanguage(title!=null?title:"", language!=null?language:"");
		resourceRelationIdentifier = new JTextField(id!=null?id:"");
		resourceRelationLink = new JTextField(link!=null?link:"");
		resourceRelationAgencyName = new JTextField(agencyName!=null?agencyName:"");
		if (descriptiveNote != null && !descriptiveNote.isEmpty()) {
			for (String content: descriptiveNote) {
				resourceRelationDescriptiveList.add(new TextAreaWithLanguage(content,""));
			}
		} else {
			resourceRelationDescriptiveList.add(new TextAreaWithLanguage("",""));
		}
		
		//dates
       if (dateResource==null){
    	   dateResource = new Date();
       }
		
		boolean isDateUndefined = this.isUndefinedDate(dateResource.getLocalType());
        boolean isStillDate = (!isDateUndefined && dateResource.getLocalType()!=null && (dateResource.getLocalType().equals("open")));
        resourceRelationDate = new TextFieldsWithRadioButtonForDates(this.labels.getString("eaccpf.commons.unknown.date"), this.labels.getString("eaccpf.commons.date.known"),
                "",
                		dateResource.getContent(),
                isDateUndefined, isStillDate,isStillDate, dateResource.getStandardDate(),
                "", false, "", "", false, "", false);
	 
        //instance elements if necessary
		if(this.resourcesRelationTitle == null){
			this.resourcesRelationTitle = new ArrayList<TextFieldWithLanguage>();
		}
		if(this.resourcesRelationIdentifier == null){
			this.resourcesRelationIdentifier = new ArrayList<JTextField>();
		}
		if(this.resourcesRelationLink == null){
			this.resourcesRelationLink = new ArrayList<JTextField>();
		}
		if(this.resourcesRelationAgencyName == null){
			this.resourcesRelationAgencyName= new ArrayList<JTextField>();
		}
		if(this.resourcesRelationDate == null){
			this.resourcesRelationDate = new ArrayList<TextFieldsWithRadioButtonForDates>();
		}
		if(this.resourcesRelationDescriptive == null){
			this.resourcesRelationDescriptive = new ArrayList<List<TextAreaWithLanguage>>();
		}
		// Add elements to the list.
		this.resourcesRelationTitle.add(resourceRelationTitle);
		this.resourcesRelationIdentifier.add(resourceRelationIdentifier);
		this.resourcesRelationLink.add(resourceRelationLink);
		this.resourcesRelationAgencyName.add(resourceRelationAgencyName);
		this.resourcesRelationDate.add(resourceRelationDate);
	    this.resourcesRelationDescriptive.add(resourceRelationDescriptiveList);
		
		// Set title.
		setNextRow();
		builder.addSeparator(this.labels.getString("eaccpf.description.image"), cc.xyw(1, this.rowNb, 7));
		setNextRow();
		
		// Add elements to the panel.
		// Title and language.
		builder.addLabel(this.labels.getString("eaccpf.description.image.title"), cc.xy(1, this.rowNb));
		builder.add(resourceRelationTitle.getTextField(), cc.xy(3, this.rowNb));
		builder.addLabel(this.labels.getString("eaccpf.commons.select.language"), cc.xy(5, this.rowNb));
		builder.add(resourceRelationTitle.getLanguageBox(), cc.xy(7, this.rowNb));
		setNextRow();
		
		//Add the element id to the panel.
		builder.addLabel(this.labels.getString("eaccpf.description.image.identifier"), cc.xy(1, this.rowNb));
		builder.add(resourceRelationIdentifier, cc.xy(3, this.rowNb));
		setNextRow();
	
		//add link to the panel
		builder.addLabel(this.labels.getString("eaccpf.description.image.link")+"*", cc.xy(1, this.rowNb));
		builder.add(resourceRelationLink, cc.xy(3, this.rowNb));
		setNextRow();
		
		//add agencyName to the panel
		builder.addLabel(this.labels.getString("eaccpf.description.image.agencyName"), cc.xy(1, this.rowNb));
		builder.add(resourceRelationAgencyName, cc.xy(3, this.rowNb));
		setNextRow();
		
		//add date to the panel
		
		// First date row. Normal date text fields.
        builder.addLabel(this.labels.getString("eaccpf.description.image.date"), cc.xy(1, this.rowNb));
        resourceRelationDate.getDateTextField().addFocusListener(new AddIsoText(resourceRelationDate, EacCpfIdentityPanel.UNKNOWN_DATE));
        builder.add(resourceRelationDate.getDateTextField(), cc.xy(3, this.rowNb));

        // Second date row. Unknown radiobuttons.
        setNextRow();
        resourceRelationDate.getDateDefinedRB().addActionListener(new AddUndefinedTexts(resourceRelationDate, EacCpfIdentityPanel.KNOWN_DATE));
        builder.add(resourceRelationDate.getDateDefinedRB(), cc.xy(3, this.rowNb));

        setNextRow();
        resourceRelationDate.getDateUndefinedRB().addActionListener(new AddUndefinedTexts(resourceRelationDate, EacCpfIdentityPanel.UNKNOWN_DATE));
        builder.add(resourceRelationDate.getDateUndefinedRB(), cc.xy(3, this.rowNb));
        setNextRow();
		
     // Third date row. Standard dates.
      
        builder.addLabel(this.labels.getString("eaccpf.commons.iso.date"), cc.xy(1, this.rowNb));
        resourceRelationDate.getStandardDateTextField().addFocusListener(new CheckIsoText(resourceRelationDate, EacCpfIdentityPanel.UNKNOWN_DATE));
        builder.add(resourceRelationDate.getStandardDateTextField(), cc.xy(3, this.rowNb));
		setNextRow();
		
		//descriptiveNote in resourceRelation
		for (TextAreaWithLanguage resourceRelationDescriptive: resourceRelationDescriptiveList) {
			builder.addLabel(this.labels.getString("eaccpf.description.image.description"), cc.xy(1, this.rowNb));
			builder.add(resourceRelationDescriptive.getTextField(), cc.xyw(3, this.rowNb, 4));
			setNextRow();
		}
		//button to add more descriptions
		setNextRow();
		JButton jButtonAddFurtherImage= new ButtonTab(this.labels.getString("eaccpf.description.button.addfurtherDescriptionImage"));
		jButtonAddFurtherImage.addActionListener(new AddFurtherDescriptionImageButton(this.eaccpf, this.tabbedPane, model,index));
		builder.add(jButtonAddFurtherImage, cc.xy (1, rowNb));
		setNextRow();
        
        return builder;
	}
	
	/**
	 * Class to performs the addition of new relation with image in the description tab
	 * section if the previous values are filled.
	 */
	public class AddFurtherImageButton extends UpdateEacCpfObject {

		public AddFurtherImageButton(EacCpf eacCpf, JTabbedPane tabbedPane,ProfileListModel model) {
			super(eacCpf, tabbedPane, model);
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				super.updateJAXBObject(false);
			} catch (EacCpfFormException e) {
				reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 1);
			}

			boolean empty = false;  
			for(int i=0;i<resourcesRelationLink.size();i++){
				if (StringUtils.isEmpty(resourcesRelationLink.get(i).getText())) {
					JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.description.error.empty.link"));
					empty = true;
				}
			}
			// Check the content and add the elements.
			if (eaccpf.getCpfDescription() == null) {
				eaccpf.setCpfDescription(new CpfDescription());
			}
			if (eaccpf.getCpfDescription().getRelations() == null) {
				eaccpf.getCpfDescription().setRelations(new Relations());
			}
			if(!empty 
					|| (empty && resourcesRelationLink.size() > eaccpf.getCpfDescription().getRelations().getResourceRelation().size())){
				ResourceRelation resourceRelation = new ResourceRelation();
				resourceRelation.setArcrole(ARCROLE_IMAGE);
				eaccpf.getCpfDescription().getRelations().getResourceRelation().add(resourceRelation);
			}
			reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 1);
		}
	}
	
	/**
	 * Class to performs the addition of a new image description if the previous values are filled
	 */
	public class AddFurtherDescriptionImageButton extends UpdateEacCpfObject {
		private int index;
		public AddFurtherDescriptionImageButton(EacCpf eacCpf, JTabbedPane tabbedPane,ProfileListModel model, Integer index) {
			super(eacCpf, tabbedPane, model);
			this.index = (index!=null)?index:0;
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				super.updateJAXBObject(false);
			} catch (EacCpfFormException e) {
				reloadTabbedPanel(new EacCpfDescriptionPanel(this.eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 1);
			}

			boolean empty = false;
			List<TextAreaWithLanguage> resourceRelationDescriptiveList = resourcesRelationDescriptive.get(this.index); 
				
				for(int i=0;i<resourceRelationDescriptiveList.size() && !empty ;i++){
					if(StringUtils.isEmpty(resourceRelationDescriptiveList.get(i).getTextValue())){
						JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.description.error.empty.description"));
						empty = true;
					}
				}
	
			if (eaccpf.getCpfDescription() == null) {
				eaccpf.setCpfDescription(new CpfDescription());
			}
			if (eaccpf.getCpfDescription().getRelations() == null) {
				eaccpf.getCpfDescription().setRelations(new Relations());
			}
			if(eaccpf.getCpfDescription().getRelations().getResourceRelation() == null){
				eaccpf.getCpfDescription().getRelations().getResourceRelation().add(new ResourceRelation());
			}
			List<ResourceRelation> resourceRelationsImage = new ArrayList<ResourceRelation>();
			List<ResourceRelation> resourceRelations = eaccpf.getCpfDescription().getRelations().getResourceRelation();
			for (ResourceRelation resourceRelation: resourceRelations) {
				if (resourceRelation.getArcrole() != null
						&& resourceRelation.getArcrole().equalsIgnoreCase(ARCROLE_IMAGE)) {
					resourceRelationsImage.add(resourceRelation);
				}
			}
			if (resourceRelationsImage!=null && !resourceRelationsImage.isEmpty() && resourceRelationsImage.size() > this.index){
				ResourceRelation resourceRelation = resourceRelationsImage.get(this.index);
				
				if(resourceRelation!=null && resourceRelation.getDescriptiveNote()!=null 
						&& resourceRelation.getDescriptiveNote().getP()!=null){
					
					P p=new P();
					resourceRelation.getDescriptiveNote().getP().add(p);
				}	
			}	
			reloadTabbedPanel(new EacCpfDescriptionPanel(this.eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 1);
		}
	}
	
	/*************************************************************/
	/**
	 * Method which build Places and Places Location form part.
	 * Builds for parts, places, functions ocupations and genealogy.
	 * It returns the builder with this four elements.
	 */
	private PanelBuilder buildPlaceByPlacesLocationDescription(Object object,PanelBuilder builder, CellConstraints cc,boolean isFirst) {
		if(object!=null){
			if(object instanceof Places){
				builder = buildPlaces(builder,cc,(Places)object);
			}
			if(object instanceof Functions){
				builder = buildFunctions(builder,cc,(Functions)object,isFirst);
			}
			if (object instanceof Occupations) {
				builder = buildOcupation(builder,cc,(Occupations)object,isFirst);
			}
			if (object instanceof StructureOrGenealogy) {
				StructureOrGenealogy structureOrGeneaoly = (StructureOrGenealogy) object;
				builder = buildGenealogy(builder,cc,structureOrGeneaoly,isFirst);
	    		setNextRow();
			}
		}else{
			builder = buildPlaces(builder,cc,null);
			builder = buildFunctions(builder,cc,null,isFirst);
			builder = buildOcupation(builder,cc,null,isFirst);
    		builder = buildGenealogy(builder,cc,null,isFirst);
		}
		
		return builder;
	}
	/**
	 * Build occupation parts (form)
	 */
	private PanelBuilder buildOcupation(PanelBuilder builder,CellConstraints cc, Occupations ocupations, boolean isFirst) {
		
		if(ocupations!=null && ocupations.getOccupation().size()>0){
			for(int i=0;i<ocupations.getOccupation().size();i++){
				builder.addSeparator(this.labels.getString("eaccpf.description.ocupation"), cc.xyw(1, this.rowNb, 7));
				setNextRow();
				
				Occupation ocupation = ocupations.getOccupation().get(i);
				builder = buildOcupationPlace(builder,cc,ocupation,i==0);
				setNextRow();
				
				JButton jButtonAddFurtherPlaceOcupation = new ButtonTab(this.labels.getString("eaccpf.description.button.addFurtherPlaceOcupation"));
				jButtonAddFurtherPlaceOcupation.addActionListener(new AddFurtherPlaceOccupation(this.eaccpf, this.tabbedPane, this.model, i));
        		builder.add(jButtonAddFurtherPlaceOcupation, cc.xy (1, rowNb));
        		setNextRow();
				
				builder = buildOcupationDateSet(builder,cc,ocupation,i);
				setNextRow();
			}
		} else {
			builder.addSeparator(this.labels.getString("eaccpf.description.ocupation"), cc.xyw(1, this.rowNb, 7));
			setNextRow();
			
			builder = buildOcupationPlace(builder,cc,null,isFirst);
			setNextRow();
			
			JButton jButtonAddFurtherPlaceOcupation = new ButtonTab(this.labels.getString("eaccpf.description.button.addFurtherPlaceOcupation"));
			jButtonAddFurtherPlaceOcupation.addActionListener(new AddFurtherPlaceOccupation(this.eaccpf, this.tabbedPane, this.model, null));
    		builder.add(jButtonAddFurtherPlaceOcupation, cc.xy (1, rowNb));
    		setNextRow();
			
			builder = buildOcupationDateSet(builder,cc,null,0);
			setNextRow();
		}
		
		JButton jButtonAddFurtherOcupation = new ButtonTab(this.labels.getString("eaccpf.description.button.addFurtherOcupation"));
		jButtonAddFurtherOcupation.addActionListener(new AddFurtherOccupation(this.eaccpf, this.tabbedPane, this.model));
		builder.add(jButtonAddFurtherOcupation, cc.xy (1, rowNb));
		setNextRow();
		
		return builder;
	}
	/**
	 * Builds functions part (if there are severals or if there is none.
	 * Also appends Add Further Places button.
	 */
	private PanelBuilder buildFunctions(PanelBuilder builder,CellConstraints cc, Functions functionObjects,boolean isFirst) {
		if(functionObjects!=null && functionObjects.getFunction() != null && !functionObjects.getFunction().isEmpty()) {
            for (int i=0;i<functionObjects.getFunction().size();i++) {
	        	 builder.addSeparator(this.labels.getString("eaccpf.description.function"), cc.xyw(1, this.rowNb, 7));
	    		 setNextRow();
	    		 
            	 Function function = functionObjects.getFunction().get(i);
                 builder = buildPlaceFunction(builder,cc,function,i==0);
                 setNextRow();

         		 JButton jButtonAddFurtherPlaceFunction = new ButtonTab(this.labels.getString("eaccpf.description.button.addFurtherPlaceFunction"));
        		 jButtonAddFurtherPlaceFunction.addActionListener(new AddFurtherPlaceFunction(this.eaccpf, this.tabbedPane, this.model, i));
         		 builder.add(jButtonAddFurtherPlaceFunction, cc.xy (1, rowNb));
         		 setNextRow();
         		 
                 if (function.getDateSet() != null || function.getDateRange() != null || function.getDate() != null) {
         			builder = buildDatesOrDatesRangeFunctions(builder,cc,function,i);
             		setNextRow();
         		 }
                 builder = buildAddDateDateRangeLabelAndJButtons(builder,cc,FUNCTIONS,i, true);
            }
		}else{
			builder.addSeparator(this.labels.getString("eaccpf.description.function"), cc.xyw(1, this.rowNb, 7));
			setNextRow();
			
            builder = buildPlaceFunction(builder,cc,null,isFirst);
            setNextRow();
            
            JButton jButtonAddFurtherPlaceFunction = new ButtonTab(this.labels.getString("eaccpf.description.button.addFurtherPlaceFunction"));
    		jButtonAddFurtherPlaceFunction.addActionListener(new AddFurtherPlaceFunction(this.eaccpf, this.tabbedPane, this.model,null));
     		builder.add(jButtonAddFurtherPlaceFunction, cc.xy (1, rowNb));
            setNextRow();
            
            builder = buildDatesOrDatesRangeFunctions(builder,cc,null,0);
     		setNextRow();
     		builder = buildAddDateDateRangeLabelAndJButtons(builder,cc,FUNCTIONS,0, true);
		}
		
 		JButton jButtonAddFurtherFunction = new ButtonTab(this.labels.getString("eaccpf.description.button.addFurtherFunction"));
		jButtonAddFurtherFunction.addActionListener(new AddFurtherFunction(this.eaccpf, this.tabbedPane, this.model));
		builder.add(jButtonAddFurtherFunction, cc.xy (1, rowNb));
		setNextRow();
		
		return builder;
	}
	/**
	 * Method which builds places part
	 */
	private PanelBuilder buildPlaces(PanelBuilder builder, CellConstraints cc,Places places) {
		if(places!=null && places.getPlace()!=null && !places.getPlace().isEmpty()){
			List<Place> placesList = places.getPlace();
			for(int i=0;i<placesList.size();i++){
				Place place = placesList.get(i);
				builder = buildPlace(builder,cc,place,i);
			}
		}else{
			builder = buildPlace(builder,cc,null,null);
		}
		JButton jButtonAddFurtherPlace = new ButtonTab(this.labels.getString("eaccpf.description.button.addFurtherPlace"));
		jButtonAddFurtherPlace.addActionListener(new AddFurtherAddressButton(this.eaccpf, this.tabbedPane, this.model));
		builder.add(jButtonAddFurtherPlace, cc.xy (1, rowNb));
		setNextRow();
		return builder;
	}
	/**
	 * Method which builds an individual biography form part
	 */
	private PanelBuilder buildBiography(PanelBuilder builder, CellConstraints cc, String content, String lang,boolean isFirst) {
		JLabel jLabelLinkDescriptionGenealogy = new JLabel(this.labels.getString("eaccpf.description.description"));
		builder.add(jLabelLinkDescriptionGenealogy, cc.xy (1, rowNb));
		TextAreaWithLanguage textFieldWithLanguage = null;
		if(content!=null){
			textFieldWithLanguage = new TextAreaWithLanguage(content,lang!=null?lang:"");
			JScrollPane targetBiographyHistory = textFieldWithLanguage.getTextField();
            builder.add(targetBiographyHistory, cc.xy (3, rowNb));
            addBiographyHistoryJTextfield(textFieldWithLanguage);
			JLabel jLabelLanguageGenealogy = new JLabel(this.labels.getString("eaccpf.description.selectlanguage"));
			builder.add(jLabelLanguageGenealogy, cc.xy (5, rowNb));
			JComboBox languageJComboBox = textFieldWithLanguage.getLanguageBox();
			builder.add(languageJComboBox, cc.xy (7, rowNb));
			addBiographyHistoryJComboBoxes(languageJComboBox);
		}else{
			textFieldWithLanguage = new TextAreaWithLanguage(content,lang!=null?lang:"");
			builder.add(textFieldWithLanguage.getTextField(), cc.xy (3, rowNb));
			addBiographyHistoryJTextfield(textFieldWithLanguage);
			JLabel jLabelLanguageBiography = new JLabel(this.labels.getString("eaccpf.description.selectlanguage"));
			builder.add(jLabelLanguageBiography, cc.xy (5, rowNb));
			JComboBox jComboboxLanguageBiography = buildLanguageJComboBox(/*isFirst*/);
			builder.add(jComboboxLanguageBiography, cc.xy (7, rowNb));
			addBiographyHistoryJComboBoxes(jComboboxLanguageBiography);
		}
		setNextRow();
		return builder;
	}

	/**
	 * Method which builds genealogy part form.
	 * It could load paragraph parts and language if object<StructureOrGenealogy> is used.
	 */
	private PanelBuilder buildGenealogy(PanelBuilder builder, CellConstraints cc,StructureOrGenealogy structureOrGenealogy,boolean isFirst) {
		builder.addSeparator(this.labels.getString("eaccpf.description.genealogy"), cc.xyw(1, this.rowNb, 7));
		setNextRow();
		
		String content = "";
		String lang = "";
		TextAreaWithLanguage textFieldWithLanguage = null;
		if(structureOrGenealogy!=null && structureOrGenealogy.getMDiscursiveSet()!=null && !structureOrGenealogy.getMDiscursiveSet().isEmpty()){
			List<Object> mDiscursiveSet = structureOrGenealogy.getMDiscursiveSet();
            for (int i=0;i<mDiscursiveSet.size();i++) {
            	content = "";
            	lang = "";
            	Object paragraph = mDiscursiveSet.get(i);
            	if(paragraph!=null && paragraph instanceof P){
            		P p = (P)paragraph;
                	content = ((P) p).getContent();
                	lang = ((P) p).getLang();
        			JLabel jLabelLinkDescriptionGenealogy = new JLabel(this.labels.getString("eaccpf.description.description"));
            		builder.add(jLabelLinkDescriptionGenealogy, cc.xy (1, rowNb));
//                  textFieldWithLanguage = new TextFieldWithLanguage(content,lang);
        			textFieldWithLanguage = new TextAreaWithLanguage(content!=null?content:"", lang!=null?lang:"");
                    ScrollPane genealogyTextField = textFieldWithLanguage.getTextField();
                    builder.add(genealogyTextField, cc.xy (3, rowNb));
                    addGenealogyTextField(textFieldWithLanguage);
        			JLabel jLabelLanguageGenealogy = new JLabel(this.labels.getString("eaccpf.description.selectlanguage"));
        			builder.add(jLabelLanguageGenealogy, cc.xy (5, rowNb));
        			JComboBox genealogyLanguageComboBox = textFieldWithLanguage.getLanguageBox();
        			builder.add(genealogyLanguageComboBox, cc.xy (7, rowNb));
        			addGenealogyLanguage(genealogyLanguageComboBox);
        			setNextRow();
            	}
            }
		}else{
			JLabel jLabelLinkDescriptionGenealogy = new JLabel(this.labels.getString("eaccpf.description.description"));
			builder.add(jLabelLinkDescriptionGenealogy, cc.xy (1, rowNb));
//			JTextField jTextFieldLinkDescriptionGenealogy = new JTextField(content);
//			builder.add(jTextFieldLinkDescriptionGenealogy, cc.xy (3, rowNb));
			TextAreaWithLanguage jTextFieldLinkDescriptionGenealogy = new TextAreaWithLanguage(content, "");
			builder.add(jTextFieldLinkDescriptionGenealogy.getTextField(), cc.xy (3, rowNb));
			addGenealogyTextField(jTextFieldLinkDescriptionGenealogy);
			JLabel jLabelLanguageGenealogy = new JLabel(this.labels.getString("eaccpf.description.selectlanguage"));
			builder.add(jLabelLanguageGenealogy, cc.xy (5, rowNb));
			JComboBox jComboboxLanguageGenealogy = buildLanguageJComboBox(/*isFirst*/);
			builder.add(jComboboxLanguageGenealogy, cc.xy (7, rowNb));
			addGenealogyLanguage(jComboboxLanguageGenealogy);
			setNextRow();
		}
		
		setNextRow();
		
		JButton jButtonAddFurtherGenealogyDescription = new ButtonTab(this.labels.getString("eaccpf.description.button.addfurthergenealogydescription"));
		jButtonAddFurtherGenealogyDescription.addActionListener(new AddFurtherGenealogy(this.eaccpf, this.tabbedPane, this.model));
		builder.add(jButtonAddFurtherGenealogyDescription, cc.xy (1, rowNb));
		setNextRow();
		
		return builder;
	}

	/**
	 * Builds a JComboBox with all languages used in several parts of this form.
	 * Languages are provided by CommonsPropertiesPanels class.
	 */
	private JComboBox buildLanguageJComboBox(){
		JComboBox jComboboxLanguageGenealogy = new JComboBox(CommonsPropertiesPanels.languagesDisplay);
		if(StringUtils.isNotEmpty(this.firstLanguage)){
//			Locale.ENGLISH.getISO3Language()
			jComboboxLanguageGenealogy.setSelectedItem(LanguageIsoList.getLanguageStr(this.firstLanguage));
		}else{
			jComboboxLanguageGenealogy.setSelectedItem("---");
		}
		return jComboboxLanguageGenealogy;
	}
	/**
	 * Builds occupation dateset part. 
	 * It decides what kind of dates are and manage it into form.
	 */
	private PanelBuilder buildOcupationDateSet(PanelBuilder builder, CellConstraints cc,Occupation occupation,Integer index) {
		if(occupation!=null && (occupation.getDateSet()!=null || occupation.getDate()!=null || occupation.getDateRange()!=null)){
			index = index!=null?index:0;
			List<Object> dateOrDateRange = new ArrayList<Object>();
			if(occupation.getDate()!=null){
				dateOrDateRange.add(occupation.getDate());
			}else if(occupation.getDateRange()!=null){
				dateOrDateRange.add(occupation.getDateRange());
			}else{
				dateOrDateRange.addAll(occupation.getDateSet().getDateOrDateRange());
			}
			builder = buildListDatesAndRefreshGlobalListElements(builder,cc,dateOrDateRange,OCCUPATIONS,index, true);
		}
		builder = buildAddDateDateRangeLabelAndJButtons(builder,cc,OCCUPATIONS,index, true);
		return builder;
	}
	
	/**
	 * Build occupations part form. 
	 */
	private PanelBuilder buildOcupationPlace(PanelBuilder builder, CellConstraints cc,Occupation occupation,boolean flag) {
		//first line
		JLabel jLabelOcupation = new JLabel(this.labels.getString("eaccpf.description.ocupation"));
		builder.add(jLabelOcupation, cc.xy (1, rowNb));
		TextFieldWithLanguage textFieldWithLanguage = null;
		if(occupation!=null && occupation.getTerm()!=null && occupation.getTerm().getContent()!=null){
			textFieldWithLanguage = new TextFieldWithLanguage(occupation.getTerm().getContent(), occupation.getTerm().getLang());
			JTextField textField = textFieldWithLanguage.getTextField();
			builder.add(textField, cc.xy (3, rowNb));
			addOcupationPlaceOcupationJTextField(textField);
		}else{
			JTextField jTextfieldOcupation = new JTextField();
			builder.add(jTextfieldOcupation, cc.xy (3, rowNb));
			addOcupationPlaceOcupationJTextField(jTextfieldOcupation);
		}
		
		JLabel jLabelLanguage = new JLabel(this.labels.getString("eaccpf.description.selectlanguage"));
		builder.add(jLabelLanguage, cc.xy (5, rowNb));
		if(textFieldWithLanguage!=null){
			JComboBox jComboBoxLanguage = textFieldWithLanguage.getLanguageBox();
			builder.add(jComboBoxLanguage, cc.xy (7, rowNb));
			addOcupationPlaceOcupationLanguageJComboBox(jComboBoxLanguage);
		}else{
			JComboBox jComboboxLanguage = buildLanguageJComboBox(/*flag*/);
//			if(!flag){
//				jComboboxLanguage.setSelectedItem("---");
//			}
			builder.add(jComboboxLanguage, cc.xy (7, rowNb));
			addOcupationPlaceOcupationLanguageJComboBox(jComboboxLanguage);
		}
		setNextRow();
		//second line
		JLabel jLabelLinkOcupation = new JLabel(this.labels.getString("eaccpf.description.linktocontrolledocupations"));
		builder.add(jLabelLinkOcupation, cc.xy (1, rowNb));
		JTextField jLinkOcupationOcupation = new JTextField((occupation!=null && occupation.getTerm()!=null && occupation.getTerm().getVocabularySource()!=null)?occupation.getTerm().getVocabularySource():"");
		builder.add(jLinkOcupationOcupation, cc.xy (3, rowNb));
		addOcupationPlaceLinkToControlledVocabulary(jLinkOcupationOcupation);
		setNextRow();
		//third line
		JLabel jLabelLinkDescriptionOcupation = new JLabel(this.labels.getString("eaccpf.description.descriptionocupations"));
		builder.add(jLabelLinkDescriptionOcupation, cc.xy (1, rowNb));
		String content = "";
		if(occupation!=null && occupation.getDescriptiveNote()!=null && occupation.getDescriptiveNote().getP()!=null){
			for(P p : occupation.getDescriptiveNote().getP()){
				if(p.getContent()!=null){
					content += p.getContent();
				}
			}
		}
//		JTextField jTextFieldLinkDescriptionOcupation = new JTextField(content);
		TextAreaWithLanguage jTextFieldLinkDescriptionOcupation = new TextAreaWithLanguage(content, "");
		builder.add(jTextFieldLinkDescriptionOcupation.getTextField(), cc.xyw(3, this.rowNb, 4));
		addOcupationPlaceOcupationDescription(jTextFieldLinkDescriptionOcupation);
		setNextRow();
		
		textFieldWithLanguage = null;
		//each place entry, four part lines
		if(occupation!=null && occupation.getPlaceEntry()!=null && !occupation.getPlaceEntry().isEmpty()){
			List<PlaceEntry> placeEntries = occupation.getPlaceEntry();
			for (int i=0;i<placeEntries.size();i++) {
				PlaceEntry placeEntry = placeEntries.get(i);
				JLabel jLabelOcupationPlace = new JLabel(this.labels.getString("eaccpf.description.place"));
				builder.add(jLabelOcupationPlace, cc.xy (1, rowNb));
				JTextField placeTextField = new JTextField((placeEntry.getContent()!=null)?placeEntry.getContent():"");
				builder.add(placeTextField, cc.xy (3, rowNb));
				addOcupationPlacePlaceJTextField(i==0,placeTextField);
				JLabel jLabelLanguageOcupationPlace = new JLabel(this.labels.getString("eaccpf.description.country"));
				builder.add(jLabelLanguageOcupationPlace, cc.xy (5, rowNb));
				JComboBox placeCountryJComboBox = buildCountryJComboBox();
				if(placeEntry.getCountryCode()!=null){
					placeCountryJComboBox.setSelectedItem(parseIsoToCountry(placeEntry.getCountryCode()));
				}
				builder.add(placeCountryJComboBox, cc.xy (7, rowNb));
				addOcupationPlaceCountryPlaceJComboBoxes(i==0,placeCountryJComboBox);
				setNextRow();
			}
		}else{ //empty part
			JLabel jLabelOcupationPlace = new JLabel(this.labels.getString("eaccpf.description.place"));
			builder.add(jLabelOcupationPlace, cc.xy (1, rowNb));
			JTextField jTextfieldOcupationPlace = new JTextField();
			builder.add(jTextfieldOcupationPlace, cc.xy (3, rowNb));
			addOcupationPlacePlaceJTextField(true,jTextfieldOcupationPlace);
			JLabel jLabelLanguageOcupationPlace = new JLabel(this.labels.getString("eaccpf.description.country"));
			builder.add(jLabelLanguageOcupationPlace, cc.xy (5, rowNb));
			JComboBox placeCountryJComboBox = buildCountryJComboBox();
			builder.add(placeCountryJComboBox, cc.xy (7, rowNb));
			addOcupationPlaceCountryPlaceJComboBoxes(true,placeCountryJComboBox);
		}
		
		return builder;
	}
	
   /**
	* Parse iso country to country string in the current locale.
	* Map<String,String> countriesMap is used to get information.
	* countriesMap must be filled (fillListModels)
	*/
	private String parseIsoToCountry(String isoCountry) {
		if(isoCountry!=null && (isoCountry.length()==2 || isoCountry.length()==3)){
			Iterator<String> itCountries = countriesMap.keySet().iterator();
			boolean found = false;
			while(!found && itCountries.hasNext()){
				String countryName = itCountries.next();
				String value = countriesMap.get(countryName);
				if(isoCountry.equalsIgnoreCase(value)){
					isoCountry = countryName;
					found = true;
				}
			}
		}
		return isoCountry;
	}
	
	/**
	 * Method which builds function form part.
	 * It should put dates from ocupation object and a selector with
	 * Add Date or Date Range radio buttons.
	 */
	private PanelBuilder buildDatesOrDatesRangeFunctions(PanelBuilder builder, CellConstraints cc,Object object,Integer index) {
		index = index!=null?index:0;
		if(object!=null){
			List<Object> listDates = new ArrayList<Object>();
			Function function = ((Function)object);
			if(function.getDateSet()!=null){
				listDates.addAll(function.getDateSet().getDateOrDateRange());
			}else if(function.getDate()!=null){
				listDates.add(function.getDate());
			}else if(function.getDateRange()!=null){
				listDates.add(function.getDateRange());
			}
			builder = buildListDatesAndRefreshGlobalListElements(builder,cc,listDates,FUNCTIONS,index, true);
		}
		return builder;
	}
	/**
	 * Build part of function form related to places.
	 */
	private PanelBuilder buildPlaceFunction(PanelBuilder builder, CellConstraints cc, Function function,boolean flag) {
		//first line
		JLabel jLabelFunction = new JLabel(this.labels.getString("eaccpf.description.function"));
		builder.add(jLabelFunction, cc.xy (1, rowNb));
		TextFieldWithLanguage textFieldWithLanguage = null;
		if(function!=null && function.getTerm()!=null){
			textFieldWithLanguage = new TextFieldWithLanguage(function.getTerm().getContent(), function.getTerm().getLang());
			JTextField textField = textFieldWithLanguage.getTextField();
			builder.add(textField, cc.xy (3, rowNb));
			addPlaceFunctionPlacesJTextfield(textField);
		}else{
			JTextField jTextfieldFunction = new JTextField();
			builder.add(jTextfieldFunction, cc.xy (3, rowNb));
			addPlaceFunctionPlacesJTextfield(jTextfieldFunction);
		}
		JLabel jLabelLanguage = new JLabel(this.labels.getString("eaccpf.description.selectlanguage"));
		builder.add(jLabelLanguage, cc.xy (5, rowNb));
		if(textFieldWithLanguage!=null){
			JComboBox languageBox = textFieldWithLanguage.getLanguageBox();
			builder.add(languageBox, cc.xy (7, rowNb));
			addPlaceFunctionLanguageJComboBox(languageBox);
		}else{
			JComboBox jComboboxLanguage = buildLanguageJComboBox(/*flag*/);
//			if(!flag){
//				jComboboxLanguage.setSelectedItem("---");
//			}
			builder.add(jComboboxLanguage, cc.xy (7, rowNb));
			addPlaceFunctionLanguageJComboBox(jComboboxLanguage);
		}
		setNextRow();
		//second line
		JLabel jLabelVocabulary = new JLabel(this.labels.getString("eaccpf.description.linktovocabulary"));
		builder.add(jLabelVocabulary, cc.xy (1, rowNb));
		JTextField jTextfieldVocabularyLink = new JTextField((function!=null && function.getTerm()!=null && function.getTerm().getVocabularySource()!=null)?function.getTerm().getVocabularySource():"");
		builder.add(jTextfieldVocabularyLink, cc.xy (3, rowNb));
		addPlaceFunctionVocabularyJTextField(jTextfieldVocabularyLink);
		setNextRow();
		//third line
		JLabel jLabelDescription = new JLabel(this.labels.getString("eaccpf.description.descriptionfunction"));
		builder.add(jLabelDescription, cc.xy (1, rowNb));
		String content = "";
		if(function!=null && function.getDescriptiveNote()!=null && function.getDescriptiveNote().getP()!=null){
			List<P> paragraphs = function.getDescriptiveNote().getP();
	        for (P p : paragraphs) {
	            if (p.getContent() != null && !p.getContent().isEmpty()) {
	                content += p.getContent();
	            }
	        }
		}
		TextAreaWithLanguage jTextFieldLinkDescriptionOcupation = new TextAreaWithLanguage(content, "");
		builder.add(jTextFieldLinkDescriptionOcupation.getTextField(), cc.xyw(3, this.rowNb, 4));
		addPlaceFunctionDescriptionTextfields(jTextFieldLinkDescriptionOcupation);
		setNextRow();
		//fourth part, each place and country for functions
		if(function!=null && function.getPlaceEntry() != null && !function.getPlaceEntry().isEmpty()){
			List<PlaceEntry> placeEntries = function.getPlaceEntry();
			for (int i=0;placeEntries.size()>i;i++) {
				PlaceEntry placeEntry = placeEntries.get(i);
            	JLabel jLabelPlace = new JLabel(this.labels.getString("eaccpf.description.place"));
				builder.add(jLabelPlace, cc.xy (1, rowNb));
				JTextField jTextfieldPlace = new JTextField(placeEntry.getContent()!=null?placeEntry.getContent():"");
				builder.add(jTextfieldPlace, cc.xy (3, rowNb));
				addPlaceFunctionPlaceJTextfields(i==0,jTextfieldPlace);
				
				JLabel jCountryLabel = new JLabel(this.labels.getString("eaccpf.description.country"));
        		builder.add(jCountryLabel, cc.xy (5, rowNb));
        		JComboBox jComboboxCountry = buildCountryJComboBox();
				
                if(placeEntry.getCountryCode()!=null){
            		jComboboxCountry.setSelectedItem(parseIsoToCountry(placeEntry.getCountryCode()));
                }
                
                builder.add(jComboboxCountry, cc.xy (7, rowNb));
        		addPlaceFunctionCountryJComboBoxes(i==0,jComboboxCountry);
        		setNextRow();
            }
		}else{ //empty one
        	JLabel jLabelPlace = new JLabel(this.labels.getString("eaccpf.description.place"));
			builder.add(jLabelPlace, cc.xy (1, rowNb));
			JTextField jTextfieldPlace = new JTextField();
			builder.add(jTextfieldPlace, cc.xy (3, rowNb));
			addPlaceFunctionPlaceJTextfields(true,jTextfieldPlace);
        	JLabel jCountryLabel = new JLabel(this.labels.getString("eaccpf.description.country"));
    		builder.add(jCountryLabel, cc.xy (5, rowNb));
    		JComboBox countryJComboBox = buildCountryJComboBox();
    		builder.add(countryJComboBox, cc.xy (7, rowNb));
    		addPlaceFunctionCountryJComboBoxes(true,countryJComboBox);
    		setNextRow();
		}
		
		return builder;
	}

	/**
	 * Method which builds an individual place.
	 * Uses placeNumber to follow which part of place it is.
	 */
	private PanelBuilder buildPlace(PanelBuilder builder, CellConstraints cc,Place place, Integer placeNumber) {
		builder.addSeparator(this.labels.getString("eaccpf.description.place"), cc.xyw(1, this.rowNb, 7));
		setNextRow();
		placeNumber = placeNumber!=null?placeNumber:0; //checks for null values, index must be controlled
		boolean filled = false;
		if(place!=null && place.getPlaceEntry()!=null){ //each one
			if (!place.getPlaceEntry().isEmpty()) {
				for (PlaceEntry placeEntry : place.getPlaceEntry()) {
					if (StringUtils.isNotEmpty(trimStringValue(placeEntry.getContent()))) {
						filled = true;
					}
					builder = buildPlaceEntry(builder,cc,placeEntry,true, placeNumber, filled);
				}
			} else {
				builder = buildPlaceEntry(builder,cc,null,true, placeNumber, filled);
			}
			if (place.getAddress() != null && place.getAddress().getAddressLine() != null && !place.getAddress().getAddressLine().isEmpty()) {
				builder = buildAddressDetails(builder,cc,place.getAddress().getAddressLine(), filled);
			}else{
				builder = buildAddressDetails(builder,cc,null, filled);
			}
			builder = buildButtonAddFurtherAddressDetails(builder,cc, placeNumber, filled);
			builder = buildPlaceDateRange(builder,cc,place,placeNumber, filled);
			builder = buildPlaceDateRangeButton(builder,cc,placeNumber, filled);
		}else{ //empty one
			builder = buildPlaceEntry(builder,cc,null,true, placeNumber, filled);
			builder = buildAddressDetails(builder,cc,null, filled);
			builder = buildButtonAddFurtherAddressDetails(builder,cc, null, filled);
			builder = buildPlaceDateRange(builder,cc,null,placeNumber, filled);
			builder = buildPlaceDateRangeButton(builder,cc,placeNumber, filled);
		}
		setNextRow();
		return builder;
	}
	/**
	 * Functions add further part with a button.
	 */
	private PanelBuilder buildButtonAddFurtherAddressDetails(PanelBuilder builder, CellConstraints cc, Integer index, boolean filled) {
		JButton addAddressDetails = new ButtonTab(this.labels.getString("eaccpf.description.button.addaddressdetails"));
		addAddressDetails.addActionListener(new AddFurtherAddressAndComponentButton(this.eaccpf, this.tabbedPane, this.model, index));
		addAddressDetails.setEnabled(filled);
		this.addPlaceEntryAddressDetailsButton(addAddressDetails);
		builder.add(addAddressDetails, cc.xy (1, rowNb));
		setNextRow();
		return builder;
	}
	
	/**
	 * Little method which builds and returns a builder with Date Range form part
	 */
	private PanelBuilder buildPlaceDateRangeButton(PanelBuilder builder,CellConstraints cc,Integer index, boolean filled) {
		builder = buildAddDateDateRangeLabelAndJButtons(builder,cc,PLACES,index, filled);
		return builder;
	}
	/**
	 * Similar to buildAddDateDateRangeRadioButtons but the behavior now 
	 * works with buttons
	 */
	private PanelBuilder buildAddDateDateRangeLabelAndJButtons(final PanelBuilder builder,final CellConstraints cc,String section,Integer index, boolean filled) {
		//Buttons to add new entries.
		if(section!=null){
			setNextRow();
			//label.getString() is used for fill label to, recycling section params it could be obtained, it could be PLACES, FUNCTIONS or OCCUPATIONS so there are only three possibilities
			builder.addLabel(this.labels.getString((section.equals(PLACES)?"eaccpf.description.adddatesofusefortheplace":(section.equals(FUNCTIONS)?"eaccpf.description.adddatesofuseforthefunction":"eaccpf.description.adddatesofusefortheoccupation"))), cc.xy(1, this.rowNb));
			JButton addSingleDateBtn = new ButtonTab(this.labels.getString("eaccpf.commons.add.single.date"));
			addSingleDateBtn.addActionListener(new AddSingleOrRangeDateAction(this.eaccpf, this.tabbedPane, this.model, section, false, index));
			addSingleDateBtn.setEnabled(filled);
			if (section.equals(PLACES)) {
				this.addPlaceEntryDateButton(addSingleDateBtn);
			}
			builder.add(addSingleDateBtn, cc.xy(3, this.rowNb));
			JButton addRangeDateBtn = new ButtonTab(this.labels.getString("eaccpf.commons.add.range.date"));
			addRangeDateBtn.addActionListener(new AddSingleOrRangeDateAction(this.eaccpf, this.tabbedPane, this.model, section, true, index));
			addRangeDateBtn.setEnabled(filled);
			if (section.equals(PLACES)) {
				this.addPlaceEntryDateRangeButton(addRangeDateBtn);
			}
			builder.add(addRangeDateBtn, cc.xy(5, this.rowNb));
		}
		setNextRow();
		return builder;
	}
	
	/**
	 * Method which build and returns a Place Date Range form part.
	 * It could call to buildPlaceDateRangeDateSet or buildPlaceDateRangeDateRange
	 */
	private PanelBuilder buildPlaceDateRange(PanelBuilder builder,CellConstraints cc, Place place,Integer index, boolean filled) {
		if(place!=null){
			index = index!=null?index:0;
			if (place.getDateSet() != null || place.getDateRange() != null || place.getDate() != null) {
				List<Object> listDates = new ArrayList<Object>();
				if(place.getDateSet()!=null){
					listDates.addAll(place.getDateSet().getDateOrDateRange());
				}else if(place.getDate()!=null){
					listDates.add(place.getDate());
				}else if(place.getDateRange()!=null){
					listDates.add(place.getDateRange());
				}
        		builder = buildListDatesAndRefreshGlobalListElements(builder,cc,listDates,PLACES,index, filled);
			}
		}
		return builder;
	}

	/**
	 * Index must indicate section part 
	 */
	private PanelBuilder buildListDatesAndRefreshGlobalListElements(PanelBuilder builder, CellConstraints cc, List<Object> sectionDates, String section , Integer index, boolean filled) {
		index = index!=null?index:0; //index must be controlled, assigned to 0 if it's not found
		setNextRow();
		List<TextFieldsWithRadioButtonForDates> datesForSectionList = new ArrayList<TextFieldsWithRadioButtonForDates>();
		for (Object object : sectionDates) {
			setNextRow();
			//Type of date.
			boolean isDateRange = false;
			//Create element.
			TextFieldsWithRadioButtonForDates dateTextField = null;
			//decide which kind of element, each element has a different object
			if(object instanceof Date){
				Date date = (Date) object;
				boolean isDateUndefined = this.isUndefinedDate(date.getLocalType());
				boolean isStillDate = (!isDateUndefined && date.getLocalType()!=null && (date.getLocalType().equals("open")));
				dateTextField = new TextFieldsWithRadioButtonForDates(this.labels.getString("eaccpf.commons.unknown.date"), this.labels.getString("eaccpf.commons.date.known"),
						(this.entityType.getName().equals(XmlTypeEacCpf.EAC_CPF_PERSON.getName()))?this.labels.getString("eaccpf.commons.date.open.person"):this.labels.getString("eaccpf.commons.date.open.corpfam"),
						date.getContent(), 
						isDateUndefined, isStillDate,isStillDate, date.getStandardDate(),
												"", false, "", "", false, "", false);
			}else if(object instanceof DateRange){
				isDateRange = true;
				DateRange dateRange = (DateRange) object;
				String dateFrom = "";
				String dateFromStandard = "";
				String dateTo = "";
				String dateToStandard = "";

				if (dateRange.getFromDate() != null) {
					if (dateRange.getFromDate().getContent() != null
							&& !dateRange.getFromDate().getContent().isEmpty()) {
						dateFrom = dateRange.getFromDate().getContent();
					}
					if (dateRange.getFromDate().getStandardDate() != null
							&& !dateRange.getFromDate().getStandardDate().isEmpty()) {
						dateFromStandard = dateRange.getFromDate().getStandardDate();
					}
				}
				if (dateRange.getToDate() != null) {
					if (dateRange.getToDate().getContent() != null
							&& !dateRange.getToDate().getContent().isEmpty()) {
						dateTo = dateRange.getToDate().getContent();
					}
					if (dateRange.getToDate().getStandardDate() != null
							&& !dateRange.getToDate().getStandardDate().isEmpty()) {
						dateToStandard = dateRange.getToDate().getStandardDate();
					}
				}

				boolean isDateFromUndefined = isUndefinedFromDate(dateRange);
				boolean isDateToUndefined = isUndefinedToDate(dateRange);
				boolean isDateFromOpen = isOpenFromDate(dateRange);
				boolean isDateToOpen = isOpenToDate(dateRange);
				boolean isStillDate = (!isDateToUndefined && (isDateFromOpen || isDateToOpen));
				dateTextField = new TextFieldsWithRadioButtonForDates(
						this.labels.getString("eaccpf.commons.unknown.date"),
						this.labels.getString("eaccpf.commons.date.known"),
						(this.entityType.getName().equals(XmlTypeEacCpf.EAC_CPF_PERSON.getName()))?this.labels.getString("eaccpf.commons.date.open.person"):this.labels.getString("eaccpf.commons.date.open.corpfam"),
						"", 
						false ,
						isDateFromOpen , isDateToOpen,
						"", 
						dateFrom, 
						isDateFromUndefined, 
						dateFromStandard, 
						dateTo, 
						isDateToUndefined, 
						dateToStandard, 
						true);
			}
			// Add elements to the list.
			datesForSectionList.add(dateTextField);
			// Add elements to the panel.
			if(isDateRange){
				// First date row. Normal date text fields.
				builder.addLabel(this.labels.getString("eaccpf.commons.from.date"), cc.xy(1, this.rowNb));
				dateTextField.getDateFromTextField().addFocusListener(new AddIsoText(dateTextField, EacCpfIdentityPanel.UNKNOWN_DATE_FROM));
				dateTextField.getDateFromTextField().setEnabled(filled);
				builder.add(dateTextField.getDateFromTextField(), cc.xy(3, this.rowNb));
				builder.addLabel(this.labels.getString("eaccpf.commons.to.date"), cc.xy(5, this.rowNb));
				dateTextField.getDateToTextField().addFocusListener(new AddIsoText(dateTextField, EacCpfIdentityPanel.UNKNOWN_DATE_TO));
				dateTextField.getDateToTextField().setEnabled(filled);
				builder.add(dateTextField.getDateToTextField(), cc.xy(7, this.rowNb));

				// Second date row. Radio buttons.
				setNextRow();
				dateTextField.getDateFromDefinedRB().addActionListener(new AddUndefinedTexts(dateTextField, EacCpfIdentityPanel.KNOWN_DATE_FROM));
				dateTextField.getDateToDefinedRB().addActionListener(new AddUndefinedTexts(dateTextField, EacCpfIdentityPanel.KNOWN_DATE_TO));
				dateTextField.getDateFromDefinedRB().setEnabled(filled);
				dateTextField.getDateToDefinedRB().setEnabled(filled);
				builder.add(dateTextField.getDateFromDefinedRB(), cc.xy(3, this.rowNb));
				builder.add(dateTextField.getDateToDefinedRB(), cc.xy(7, this.rowNb));
				
				this.setNextRow();
				dateTextField.getDateFromUndefinedRB().addActionListener(new AddUndefinedTexts(dateTextField, EacCpfIdentityPanel.UNKNOWN_DATE_FROM));
				dateTextField.getDateToUndefinedRB().addActionListener(new AddUndefinedTexts(dateTextField, EacCpfIdentityPanel.UNKNOWN_DATE_TO));
				dateTextField.getDateFromUndefinedRB().setEnabled(filled);
				dateTextField.getDateToUndefinedRB().setEnabled(filled);
				builder.add(dateTextField.getDateFromUndefinedRB(), cc.xy(3, this.rowNb));
				builder.add(dateTextField.getDateToUndefinedRB(), cc.xy(7, this.rowNb));
				
				setNextRow();
//				dateTextField.getDateFromStillRB().addActionListener(new AddUndefinedTexts(dateTextField, EacCpfIdentityPanel.STILL_DATE_FROM));
				dateTextField.getDateToStillRB().addActionListener(new AddUndefinedTexts(dateTextField, EacCpfIdentityPanel.STILL_DATE_TO));
//				useDateTF.getDateFromStillRB().setEnabled(filled);
				dateTextField.getDateToStillRB().setEnabled(filled);
//				builder.add(useDateTF.getDateFromStillRB(), cc.xy(3, this.rowNb));
				builder.add(dateTextField.getDateToStillRB(), cc.xy(7, this.rowNb));

				// Third date row. Standard dates.
				setNextRow();
				builder.addLabel(this.labels.getString("eaccpf.commons.iso.date"), cc.xy(1, this.rowNb));
				dateTextField.getStandardDateFromTextField().addFocusListener(new CheckIsoText(dateTextField, EacCpfIdentityPanel.UNKNOWN_DATE_FROM));
				dateTextField.getStandardDateFromTextField().setEnabled(filled);
				builder.add(dateTextField.getStandardDateFromTextField(), cc.xy(3, this.rowNb));
				builder.addLabel(this.labels.getString("eaccpf.commons.iso.date"), cc.xy(5, this.rowNb));
				dateTextField.getStandardDateToTextField().addFocusListener(new CheckIsoText(dateTextField, EacCpfIdentityPanel.UNKNOWN_DATE_TO));
				dateTextField.getStandardDateToTextField().setEnabled(filled);
				builder.add(dateTextField.getStandardDateToTextField(), cc.xy(7, this.rowNb));
			}else{
				// First date row. Normal date text fields.
				builder.addLabel(this.labels.getString("eaccpf.commons.date"), cc.xy(1, this.rowNb));
				dateTextField.getDateTextField().addFocusListener(new AddIsoText(dateTextField, EacCpfIdentityPanel.UNKNOWN_DATE));
				dateTextField.getDateTextField().setEnabled(filled);
				builder.add(dateTextField.getDateTextField(), cc.xy(3, this.rowNb));

				// Second date row. Unknown radiobuttons.
				setNextRow();
				dateTextField.getDateDefinedRB().addActionListener(new AddUndefinedTexts(dateTextField, EacCpfIdentityPanel.KNOWN_DATE));
				dateTextField.getDateDefinedRB().setEnabled(filled);
				builder.add(dateTextField.getDateDefinedRB(), cc.xy(3, this.rowNb));
				this.setNextRow();
				dateTextField.getDateUndefinedRB().addActionListener(new AddUndefinedTexts(dateTextField, EacCpfIdentityPanel.UNKNOWN_DATE));
				dateTextField.getDateUndefinedRB().setEnabled(filled);
				builder.add(dateTextField.getDateUndefinedRB(), cc.xy(3, this.rowNb));
				setNextRow();
				dateTextField.getDateStillRB().addActionListener(new AddUndefinedTexts(dateTextField, EacCpfIdentityPanel.STILL_DATE));
				dateTextField.getDateStillRB().setEnabled(filled);
				builder.add(dateTextField.getDateStillRB(), cc.xy(3, this.rowNb));

				// Third date row. Standard dates.
				this.setNextRow();
				builder.addLabel(this.labels.getString("eaccpf.commons.iso.date"), cc.xy(1, this.rowNb));
				dateTextField.getStandardDateTextField().addFocusListener(new CheckIsoText(dateTextField, EacCpfIdentityPanel.UNKNOWN_DATE));
				dateTextField.getStandardDateTextField().setEnabled(filled);
				builder.add(dateTextField.getStandardDateTextField(), cc.xy(3, this.rowNb));
			}
		}
		if(section!=null){ //store all dates operation
			if(section.equals(PLACES)){
				placesDates.put(index,datesForSectionList);
			}else if(section.equals(FUNCTIONS)){
				functionsDates.put(index,datesForSectionList);
			}else if(section.equals(OCCUPATIONS)){
				occupationsDates.put(index,datesForSectionList);
			}
		}
		return builder;
	}
	/**
	 * Builds placeEntry form part.
	 */
	private PanelBuilder buildPlaceEntry(PanelBuilder builder,CellConstraints cc, PlaceEntry placeEntry, boolean flag, int index, boolean filled) {
		JLabel jLabelPlace = new JLabel(this.labels.getString("eaccpf.description.place"));
		builder.add(jLabelPlace, cc.xy (1, rowNb));
		if(placeEntry!=null){ //placeEntry part
			TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(placeEntry.getContent(), placeEntry.getLang());
			JTextField placeEntryTextField = textFieldWithLanguage.getTextField();
			placeEntryTextField.addKeyListener(new CheckPlaceContent(placeEntryTextField, index));
			builder.add(placeEntryTextField, cc.xy (3, rowNb));
			JLabel jLabelLanguage = new JLabel(this.labels.getString("eaccpf.description.selectlanguage"));
			builder.add(jLabelLanguage, cc.xy (5, rowNb));
			JComboBox placeEntryLanguage = textFieldWithLanguage.getLanguageBox();
			placeEntryLanguage.setEnabled(filled);
			builder.add(placeEntryLanguage, cc.xy (7, rowNb));
			appendPlaceEntryPlace(placeEntryTextField,placeEntryLanguage);
		}else{ //empty one
			JTextField jTextfieldPlace = new JTextField();
			jTextfieldPlace.addKeyListener(new CheckPlaceContent(jTextfieldPlace, index));
			builder.add(jTextfieldPlace, cc.xy (3, rowNb));
			JLabel jLabelLanguage = new JLabel(this.labels.getString("eaccpf.description.selectlanguage"));
			builder.add(jLabelLanguage, cc.xy (5, rowNb));
			JComboBox jComboboxLanguage = buildLanguageJComboBox(/*flag*/);
			if(!flag){
				jComboboxLanguage.setSelectedItem("---");
			}
			// Disable combo action.
			jComboboxLanguage.setEnabled(filled);
			builder.add(jComboboxLanguage, cc.xy (7, rowNb));
			appendPlaceEntryPlace(jTextfieldPlace,jComboboxLanguage);
		}
		setNextRow();
		//second line
		JLabel jControlledPlaceLabel = new JLabel(this.labels.getString("eaccpf.description.linkvocabularyplaces"));
		builder.add(jControlledPlaceLabel, cc.xy (1, rowNb));
		JTextField jTextfieldControlledLabelPlace = new JTextField();
		if(placeEntry!=null){
			String vocabLink = placeEntry.getVocabularySource();
			jTextfieldControlledLabelPlace.setText(vocabLink);
		}
		jTextfieldControlledLabelPlace.setEnabled(filled);
		builder.add(jTextfieldControlledLabelPlace, cc.xy (3, rowNb));
		appendPlaceEntryVocabularyPlace(jTextfieldControlledLabelPlace);
		setNextRow();
		//third line
		JLabel jCountryLabel = new JLabel(this.labels.getString("eaccpf.description.country"));
		builder.add(jCountryLabel, cc.xy (1, rowNb));
		JComboBox jComboboxCountry = buildCountryJComboBox();
		if(placeEntry!=null){
			String isoCountry = placeEntry.getCountryCode();
			if(isoCountry!=null && !isoCountry.isEmpty()){
				jComboboxCountry.setSelectedItem(parseIsoToCountry(isoCountry));
			}
		}
		jComboboxCountry.setEnabled(filled);
		builder.add(jComboboxCountry, cc.xy (3, rowNb));
		appendPlaceEntryCountry(jComboboxCountry);
		setNextRow();
		// role of the place has been moved from above to this part because placeEntry is needed 
		// and there is not index (iterator var) by schema (if this part is between "add DATES" and 
		// "add further address details" is impossible to put an index different from '0'
		JLabel jRolePlaceLabel = new JLabel(this.labels.getString("eaccpf.description.roleplace"));
		builder.add(jRolePlaceLabel, cc.xy (1, rowNb));
		TextFieldWithComboBoxEacCpf jComboBoxRolePlace = null;
		if(placeEntry!=null){
			jComboBoxRolePlace = new TextFieldWithComboBoxEacCpf("", placeEntry.getLocalType(), TextFieldWithComboBoxEacCpf.TYPE_PLACE_ROLE, this.entityType, this.labels);
		} else {
			jComboBoxRolePlace = new TextFieldWithComboBoxEacCpf("", "", TextFieldWithComboBoxEacCpf.TYPE_PLACE_ROLE, this.entityType, this.labels);
		}
		jComboBoxRolePlace.getComboBox().setEnabled(filled);
		builder.add(jComboBoxRolePlace.getComboBox(), cc.xy (3, rowNb));
		appendPlaceEntryRole(jComboBoxRolePlace);
		setNextRow();
		
		return builder;
	}
	/**
	 * Builds generic country JComboBox to be used into form.
	 */
	private JComboBox buildCountryJComboBox() {
		JComboBox jCountry = new JComboBox(countriesListWithEmpty.toArray());
		jCountry.setSelectedItem(TextFieldWithComboBoxEacCpf.DEFAULT_VALUE); //if this method is called empty_default_value is selected
		return jCountry;
	}
	/**
	 * Build address details form part.
	 */
	private PanelBuilder buildAddressDetails(PanelBuilder builder, CellConstraints cc, List<AddressLine> addressLines, boolean filled) {
		if(addressLines!=null && !addressLines.isEmpty()){
			for(int i=0;i<addressLines.size();i++){ //each address detail
				AddressLine address = addressLines.get(i); 
				String text = "";
				String component = "";
				// Recover address text.
				if (StringUtils.isNotEmpty(address.getContent())) {
					text = address.getContent();
				}
				// Recover address component.
				if (StringUtils.isNotEmpty(address.getLocalType())) {
					component = address.getLocalType();
				}

				TextFieldWithComboBoxEacCpf addressDetailsComponentTFWCb = buildComponentsJComboBox(address);
				JLabel jAddressDetailsLabel = new JLabel(this.labels.getString("eaccpf.description.addressdetails"));
				builder.add(jAddressDetailsLabel, cc.xy (1, rowNb));
				addressDetailsComponentTFWCb.getTextField().setEnabled(filled);
				builder.add(addressDetailsComponentTFWCb.getTextField(), cc.xy (3, rowNb));
				JLabel jComponentLabel = new JLabel(this.labels.getString("eaccpf.description.component"));
				builder.add(jComponentLabel, cc.xy (5, rowNb));
				addressDetailsComponentTFWCb.getComboBox().setEnabled(filled);
				builder.add(addressDetailsComponentTFWCb.getComboBox(), cc.xy (7, rowNb));
				setNextRow();
				appendAddressDetails(i==0,addressDetailsComponentTFWCb);
			}
		}else{ //empty one for not defined address-line
			TextFieldWithComboBoxEacCpf addressDetailsComponentTFWCb = buildComponentsJComboBox(new AddressLine());
			JLabel jAddressDetailsLabel = new JLabel(this.labels.getString("eaccpf.description.addressdetails"));
			builder.add(jAddressDetailsLabel, cc.xy (1, rowNb));
			addressDetailsComponentTFWCb.getTextField().setEnabled(filled);
			builder.add(addressDetailsComponentTFWCb.getTextField(), cc.xy (3, rowNb));
			JLabel jComponentLabel = new JLabel(this.labels.getString("eaccpf.description.component"));
			builder.add(jComponentLabel, cc.xy (5, rowNb));
			addressDetailsComponentTFWCb.getComboBox().setEnabled(filled);
			builder.add(addressDetailsComponentTFWCb.getComboBox(), cc.xy (7, rowNb));
			setNextRow();
			appendAddressDetails(true,addressDetailsComponentTFWCb);
		}
		
		return builder;
	}
	/**
	 * Generates a JComboBox using information into address line (address param).
	 */
	private TextFieldWithComboBoxEacCpf buildComponentsJComboBox(AddressLine address) {
		String text = "";
		String component = "";
		// Recover address text.
		if (StringUtils.isNotEmpty(address.getContent())) {
			text = address.getContent();
		}
		// Recover address component.
		if (StringUtils.isNotEmpty(address.getLocalType())) {
			component = address.getLocalType();
		}

		TextFieldWithComboBoxEacCpf addressDetailsComponentTFWCb = new TextFieldWithComboBoxEacCpf(text, component, TextFieldWithComboBoxEacCpf.TYPE_ADDRESS_COMPONENT, this.entityType, this.labels);
		return addressDetailsComponentTFWCb;
	}
	
	/** INTERNAL CLASS AND EVENTS **/ 

	/**
	 * Class to enable or disable the fields and buttons in "Place" section
	 * when the value on the field "Place" is filled or not.
	 */
	public class CheckPlaceContent implements KeyListener {
		private JTextField placeField;
		private int index;

		/**
		 * Constructor.
		 *
		 * @param placeField
		 */
		public CheckPlaceContent(JTextField placeField, int index) {
			this.placeField = placeField;
			this.index = index;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			this.setCorrectEnableValue();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			this.setCorrectEnableValue();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			this.setCorrectEnableValue();
		}

		/**
		 * Method to enable or to disable the fields, selects and combo boxes
		 * accordingly to the value of the "place" field.
		 */
		private void setCorrectEnableValue() {
			boolean filled = false;
			if (StringUtils.isNotEmpty(trimStringValue(this.placeField.getText()))) {
				filled = true;
			}

			// @xml:lang of the <placeEntry>.
			placeEntryPlaceJComboBoxs.get(this.index).setEnabled(filled);

			// @vocabularySource of the <placeEntry>.
			placeEntryPlaceVocabularies.get(this.index).setEnabled(filled);

			// @countryCode of the <placeEntry>.
			placeEntryCountryJComboBoxs.get(this.index).setEnabled(filled);

			// @localType of the <placeEntry>.
			placeEntryPlaceRoles.get(this.index).getComboBox().setEnabled(filled);

			// Content and @localType of the <addressLine>.
			List<TextFieldWithComboBoxEacCpf> addressLineTFWCBs = addressDetailsTextFieldsWithComboBoxes.get(this.index);
			if (addressLineTFWCBs != null && !addressLineTFWCBs.isEmpty()) {
				for (TextFieldWithComboBoxEacCpf textFieldWithComboBoxEacCpf: addressLineTFWCBs) {
					// Content of the <addressLine>.
					textFieldWithComboBoxEacCpf.getTextField().setEnabled(filled);
					// @localType of the <addressLine>.
					textFieldWithComboBoxEacCpf.getComboBox().setEnabled(filled);
				}
			}

			// Button "Add further address details".
			placeEntryAddressDetailsButton.get(this.index).setEnabled(filled);

			// Date information of the <place>.
			List<TextFieldsWithRadioButtonForDates> datesTFWRBs = placesDates.get(this.index);
			if (datesTFWRBs != null && !datesTFWRBs.isEmpty()) {
				for(TextFieldsWithRadioButtonForDates textFieldsWithRadioButtonForDates: datesTFWRBs) {
					if (textFieldsWithRadioButtonForDates.isDateRange()) {
						// Content of the <dateFrom>.
						textFieldsWithRadioButtonForDates.getDateFromTextField().setEnabled(filled);
						// Known value of the <dateFrom>.
						textFieldsWithRadioButtonForDates.getDateFromDefinedRB().setEnabled(filled);
						// @localtype="open" of the <dateFrom>.
//						textFieldsWithRadioButtonForDates.getDateFromStillRB().setEnabled(filled);
						// @localtype="unknown" of the <dateFrom>.
						textFieldsWithRadioButtonForDates.getDateFromUndefinedRB().setEnabled(filled);
						// @standardDate of the <dateFrom>.
						textFieldsWithRadioButtonForDates.getStandardDateFromTextField().setEnabled(filled);

						// Content of the <dateTo>.
						textFieldsWithRadioButtonForDates.getDateToTextField().setEnabled(filled);
						// Known value of the <dateTo>.
						textFieldsWithRadioButtonForDates.getDateToDefinedRB().setEnabled(filled);
						// @localtype="open" of the <dateTo>.
						textFieldsWithRadioButtonForDates.getDateToStillRB().setEnabled(filled);
						// @localtype="unknown" of the <dateTo>.
						textFieldsWithRadioButtonForDates.getDateToUndefinedRB().setEnabled(filled);
						// @standardDate of the <dateTo>.
						textFieldsWithRadioButtonForDates.getStandardDateToTextField().setEnabled(filled);
					} else {
						// Content of the <date>.
						textFieldsWithRadioButtonForDates.getDateTextField().setEnabled(filled);
						// Known value of the <date>.
						textFieldsWithRadioButtonForDates.getDateDefinedRB().setEnabled(filled);
						// @localtype="open" of the <date>.
						textFieldsWithRadioButtonForDates.getDateStillRB().setEnabled(filled);
						// @localtype="unknown" of the <date>.
						textFieldsWithRadioButtonForDates.getDateUndefinedRB().setEnabled(filled);
						// @standardDate of the <date>.
						textFieldsWithRadioButtonForDates.getStandardDateTextField().setEnabled(filled);
					}
				}
			}

			// Button "Add single date".
			placeEntryDateButton.get(this.index).setEnabled(filled);

			// Button "Add date range".
			placeEntryDateRangeButton.get(this.index).setEnabled(filled);
		}
	}

	public class CheckIsoText implements FocusListener {
		private TextFieldsWithRadioButtonForDates tfwcbfDates;
		private String dateType;

		/**
		 * Constructor.
		 *
		 * @param tfwcbfDates
		 * @param dateType
		 */
		public CheckIsoText(TextFieldsWithRadioButtonForDates tfwcbfDates, String dateType) {
			this.tfwcbfDates = tfwcbfDates;
			this.dateType = dateType;
		}

		@Override
		public void focusGained(FocusEvent e) {
			// No action
		}

		@Override
		public void focusLost(FocusEvent e) {
			boolean valid = true;
			if(e.getOppositeComponent() instanceof ButtonTab){
				ActionListener[] actionListeners = ((ButtonTab)e.getOppositeComponent()).getActionListeners();
				for(ActionListener actionListener:actionListeners){
					if(actionListener instanceof ChangeTabBtnAction){
						valid = false;
					}
				}
			}else if(e.getOppositeComponent() instanceof JTabbedPane){
				valid = false;
			}
			if(valid || globalIsActivated){
				if (EacCpfIdentityPanel.UNKNOWN_DATE.equalsIgnoreCase(this.dateType)) {
					if (!this.tfwcbfDates.getStandardDateValue().isEmpty()) {
						if (parseStandardDate(this.tfwcbfDates.getStandardDateValue()).isEmpty()) {
							if(!globalIsActivated){
								globalIsActivated = true;
								JOptionPane.showMessageDialog(tabbedPane, labels.getString("eaccpf.commons.error.no.standard.date"));
								globalIsActivated = false;
							}
							this.tfwcbfDates.getStandardDateTextField().setText("");
						}
					}
				} else if (EacCpfIdentityPanel.UNKNOWN_DATE_FROM.equalsIgnoreCase(this.dateType)) {
					if (!this.tfwcbfDates.getStandardDateFromValue().isEmpty()) {
						if (parseStandardDate(this.tfwcbfDates.getStandardDateFromValue()).isEmpty()) {
							if(!globalIsActivated){
								globalIsActivated = true;
								JOptionPane.showMessageDialog(tabbedPane, labels.getString("eaccpf.commons.error.no.standard.date"));
								globalIsActivated = false;
							}
							this.tfwcbfDates.getStandardDateFromTextField().setText("");
						}
					}

					if(!checkDates(this.tfwcbfDates.getStandardDateFromValue(), this.tfwcbfDates.getStandardDateToValue())){
						if (isShowErrorDateRange()) {
							setShowErrorDateRange(false);
							if(!globalIsActivated){
								globalIsActivated = true;
								JOptionPane.showMessageDialog(tabbedPane, labels.getString("eaccpf.commons.error.no.standard.dateRange"));
								globalIsActivated = false;
							}
							this.tfwcbfDates.getDateFromTextField().setText("");
							this.tfwcbfDates.getStandardDateFromTextField().setText("");
							setShowErrorDateRange(true);
						}
					}
				} else if (EacCpfIdentityPanel.UNKNOWN_DATE_TO.equalsIgnoreCase(this.dateType)) {
					if (!this.tfwcbfDates.getStandardDateToValue().isEmpty()) {
						if (parseStandardDate(this.tfwcbfDates.getStandardDateToValue()).isEmpty()) {
							if(!globalIsActivated){
								globalIsActivated = true;
								JOptionPane.showMessageDialog(tabbedPane, labels.getString("eaccpf.commons.error.no.standard.date"));
								globalIsActivated = false;
							}
							this.tfwcbfDates.getStandardDateToTextField().setText("");
						}
					}

					if(!checkDates(this.tfwcbfDates.getStandardDateFromValue(), this.tfwcbfDates.getStandardDateToValue())){
						if (isShowErrorDateRange()) {
							setShowErrorDateRange(false);
							globalIsActivated = true;
							JOptionPane.showMessageDialog(tabbedPane, labels.getString("eaccpf.commons.error.no.standard.dateRange"));
							globalIsActivated = false;
							this.tfwcbfDates.getDateToTextField().setText("");
							this.tfwcbfDates.getStandardDateToTextField().setText("");
							setShowErrorDateRange(true);
						}
					}
				}
			}
		}
	}
	
	protected boolean checkStartTabFields() {
		boolean state = true;
		if(firstLanguage==null || firstLanguage.isEmpty() || firstLanguage.equals("---")){
			state = false;
			JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.emptylanguage"));
		}else if(firstScript==null || firstScript.isEmpty() || firstScript.equals("---")){
			state = false;
			JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.emptyscript"));
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
				if(checkStartTabFields()){
					eaccpf = cleanIncompleteData(eaccpf);
					eaccpf = updatesControl(eaccpf);
					super.saveFile(eaccpf.getControl().getRecordId().getValue());
				}
				reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
			} catch (EacCpfFormException e) {
				reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
			}
		}
	}

	/**
	 * Class to performs the actions when the user clicks on button for next or previous tab.
	 */
	public class ChangeTabBtnAction extends UpdateEacCpfObject {
		private boolean isNextTab;
		ChangeTabBtnAction(EacCpf eaccpf, JTabbedPane tabbedPane, ProfileListModel model, boolean isNextTab) {
			super(eaccpf, tabbedPane, model);
			this.isNextTab = isNextTab;
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				super.updateJAXBObject(false);
				if(areRightDates()){
					removeChangeListener();
					if (this.isNextTab) {
						reloadTabbedPanel(new EacCpfRelationsPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels, entityType, firstLanguage, firstScript).buildEditorPanel(errors), 2);
					} else if(checkStartTabFields()){
						String mainagencycode = eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue();
						reloadTabbedPanel(new EacCpfIdentityPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, false, labels, entityType, firstLanguage, firstScript,mainagencycode).buildEditorPanel(errors), 0);
					}
				}else{
					reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
				}
			} catch (EacCpfFormException e) {
				reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
			}
		}
	}
	
	public class AddFurtherBiographyDescriptionButton extends UpdateEacCpfObject{
		
		public AddFurtherBiographyDescriptionButton(EacCpf eacCpf, JTabbedPane tabbedPane,ProfileListModel model) {
			super(eacCpf, tabbedPane, model);
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				//first save current form
//				storeCurrentForm();
				super.updateJAXBObject(false);
				
			} catch (EacCpfFormException e1) {
				reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
			}
			
			//second put new node into eaccpf object
			putEmptyBiogHist();
			
			//third refresh and save current form with empty field
//			refreshCurrentTab();
			reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
		}

		private void putEmptyBiogHist() {
			BiogHist lastBiography = null;

			if(this.eaccpf.getCpfDescription().getDescription().getBiogHist()!=null && this.eaccpf.getCpfDescription().getDescription().getBiogHist().size()>0){
				lastBiography = this.eaccpf.getCpfDescription().getDescription().getBiogHist().get(this.eaccpf.getCpfDescription().getDescription().getBiogHist().size()-1);
			}

			if (lastBiography!=null
					&& !lastBiography.getChronListOrPOrCitation().isEmpty()) {
				// Checks the elements in the apeEAC-CPF object.
				boolean empty = false;
				int size = 0;
				for (int i = 0; i < lastBiography.getChronListOrPOrCitation().size(); i++) {
					Object object = lastBiography.getChronListOrPOrCitation().get(i);
					if (object instanceof P) {
						P p = (P) object;
						if (StringUtils.isEmpty(p.getContent())
								|| StringUtils.isEmpty(trimStringValue(p.getContent()))) {
							if (!empty) {
								JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.biohist"));
							}
							empty = true;
						}
						size++;
					}
				}

				// Checks the sizes of the list.
				if (!empty && size < biographyHistoryJTextfields.size()) {
					JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.biohist"));
				}

			} else {
				JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.biohist"));
			}

			// Adds empty P.
			P emptyP = new P();
			emptyP.setLang((firstLanguage!=null && !StringUtils.isEmpty(firstLanguage) && !firstLanguage.equals("---"))?firstLanguage:null);
			if(lastBiography!=null && lastBiography.getChronListOrPOrCitation()!=null){
				lastBiography.getChronListOrPOrCitation().add(emptyP);
			}
		}
	}
	
	public class AddFurtherGenealogy extends UpdateEacCpfObject{

		public AddFurtherGenealogy(EacCpf eacCpf, JTabbedPane tabbedPane,ProfileListModel model) {
			super(eacCpf, tabbedPane, model);
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				//first save current form
				super.updateJAXBObject(false);
				
			} catch (EacCpfFormException e1) {
				reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
			}
			
			//second put new node into eaccpf object
			putEmptyGenealogy();
			
			//third refresh and save current form with empty field
			reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
		}

		private void putEmptyGenealogy() {
			boolean found = false;
			StructureOrGenealogy lastGenealogy = null;

			for(int i=0;!(found && lastGenealogy!=null) && i<this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();i++){
				Object object = this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i);
				if(object instanceof StructureOrGenealogy){
					lastGenealogy = (StructureOrGenealogy) object;
				}else{
					found = true;
				}
			}

			if (lastGenealogy!=null
					&& !lastGenealogy.getMDiscursiveSet().isEmpty()) {
				// Checks the elements in the apeEAC-CPF object.
				boolean empty = false;
				int size = 0;
				for (int i = 0; i < lastGenealogy.getMDiscursiveSet().size(); i++) {
					Object object = lastGenealogy.getMDiscursiveSet().get(i);
					if (object instanceof P) {
						P p = (P) object;
						if (StringUtils.isEmpty(p.getContent())
								|| StringUtils.isEmpty(trimStringValue(p.getContent()))) {
							if (!empty) {
								JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.genealogy"));
							}
							empty = true;
						}
						size++;
					}
				}

				// Checks the sizes of the list.
				if (!empty && size < genealogyTextFields.size()) {
					JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.genealogy"));
				}

			} else {
				JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.genealogy"));
			}

			// Adds empty P.
			P emptyP = new P();
			emptyP.setLang((firstLanguage!=null && !StringUtils.isEmpty(firstLanguage) && !firstLanguage.equals("---"))?firstLanguage:null);
			if(lastGenealogy!=null && lastGenealogy.getMDiscursiveSet()!=null){
				lastGenealogy.getMDiscursiveSet().add(emptyP);
			}
		}
	}
	
	public class AddFurtherOccupation extends UpdateEacCpfObject{

		public AddFurtherOccupation(EacCpf eacCpf, JTabbedPane tabbedPane,ProfileListModel model) {
			super(eacCpf, tabbedPane, model);
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			int total = 0;
			try {
				if(this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses()!=null){
					for(int i=0;i<this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();i++){
						Object object = this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i);
						if(object instanceof Occupations){
							total = ((Occupations)object).getOccupation().size();
						}
					}
				}
				//first save current form
				super.updateJAXBObject(false);
				
			} catch (EacCpfFormException e1) {
				reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
			}
			
			//second put new node into eaccpf object
			putEmptyOcupation(total);
			
			//third refresh and save current form with empty field
			reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
		}

		private void putEmptyOcupation(int total) {
			Occupation occupation = new Occupation();
			PlaceEntry emptyPlaceEntry = new PlaceEntry();
			emptyPlaceEntry.setLang((firstLanguage!=null && !StringUtils.isEmpty(firstLanguage) && !firstLanguage.equals("---"))?firstLanguage:null);
			occupation.getPlaceEntry().add(emptyPlaceEntry);
			
			int counter = eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();
			
			Occupations lastOccupations = null;
			boolean found = false;
			int foundIndex = -1;
			for(int i = 0;!(found && lastOccupations!=null) && i<counter;i++){
				if(!(eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i) instanceof Occupations)){
					found = true;
				}else if(eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i) instanceof Occupations){
					found = false;
					foundIndex = i;
					lastOccupations = (Occupations) eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i);
				}
			}
			boolean thereAreSomeFieldsEmpty = lastOccupations!=null && lastOccupations.getOccupation().size()<total;
			boolean emptyNodeInside = false;
			if(lastOccupations==null){
				lastOccupations = new Occupations();
				lastOccupations.getOccupation().add(new Occupation());
				eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(lastOccupations);
				emptyNodeInside = true;
			}
			if(lastOccupations!=null){
				if(thereAreSomeFieldsEmpty){
					JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.occupation"));
					if(!emptyNodeInside){
						lastOccupations.getOccupation().add(new Occupation());
					}
				}else{
					int foundIndexToBeMoved = -1;
					thereAreSomeFieldsEmpty = total>lastOccupations.getOccupation().size();
					for(int x=0;!thereAreSomeFieldsEmpty && x<lastOccupations.getOccupation().size();x++){
						if(lastOccupations.getOccupation().get(x).getTerm()==null){
							thereAreSomeFieldsEmpty = true;
							foundIndexToBeMoved = x;
						}
					}
					
					if(thereAreSomeFieldsEmpty){ 
						JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.occupation"));//alert
						if(total>lastOccupations.getOccupation().size()){
							lastOccupations.getOccupation().add(occupation);
						}
					}
					
					if(!thereAreSomeFieldsEmpty){
						lastOccupations.getOccupation().add(occupation); //put an empty one
					}else if(foundIndexToBeMoved>-1){
						Occupation targetOccupation = lastOccupations.getOccupation().get(foundIndexToBeMoved);
						lastOccupations.getOccupation().add(targetOccupation);
						lastOccupations.getOccupation().remove(foundIndexToBeMoved);
					}
					if(foundIndex>-1){
						eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().set(foundIndex, lastOccupations);
					}
				}
			}
		}
	}
	
	public class AddFurtherPlaceOccupation extends UpdateEacCpfObject{

		int index;
		
		public AddFurtherPlaceOccupation(EacCpf eacCpf, JTabbedPane tabbedPane,ProfileListModel model,Integer index) {
			super(eacCpf, tabbedPane, model);
			this.index = (index!=null && index>0)?index:0;
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				//first save current form
				super.updateJAXBObject(false);
				
			} catch (EacCpfFormException e1) {
				reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
			}
			
			//second put new node into eaccpf object
			putEmptyPlaceOcupation();
			
			//third refresh and save current form with empty field
			reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
		}

		private void putEmptyPlaceOcupation() {
			
//			String lastPlaceTargetFunctionDetails = ocupationPlacePlaceJTextFields.get(this.index).get(ocupationPlacePlaceJTextFields.get(this.index).size()-1).getText(); //get previews ocupation if exists
			
			boolean found = false;
			Occupations lastOccupations = null;
			int counter = this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();
			for (int i = 0;!(lastOccupations!=null && found) && i<counter;i++){
				if(this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i) instanceof Occupations){
					lastOccupations = (Occupations) this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i); 
				}else{
					found = true;
				}
			}
			if(lastOccupations!=null){
				if(lastOccupations.getOccupation()!=null && lastOccupations.getOccupation().size()>this.index){
					List<PlaceEntry> placeEntries = lastOccupations.getOccupation().get(this.index).getPlaceEntry();
					boolean isEmpty = false;
					for(PlaceEntry placeEntry:placeEntries){
						if(placeEntry!=null && placeEntry.getContent().isEmpty()){
							isEmpty = true;
						}
					}
					if(!isEmpty){
						isEmpty = ocupationPlacePlaceJTextFields.get(this.index).size()>lastOccupations.getOccupation().get(this.index).getPlaceEntry().size();
						if(isEmpty){
							JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.place"));
						}
						lastOccupations.getOccupation().get(this.index).getPlaceEntry().add(new PlaceEntry());
					}else{
						JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.place"));
					}
				}else{
					lastOccupations.getOccupation().add(new Occupation());
					JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.place"));
				}
			}else{
				JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.place"));
			}
		}
		
	}
	
	public class AddFurtherFunction extends UpdateEacCpfObject{

		AddFurtherFunction(EacCpf eacCpf, JTabbedPane tabbedPane,ProfileListModel model) {
			super(eacCpf, tabbedPane, model);
		}

		public void actionPerformed(ActionEvent actionEvent) {
			int total = 0;
			try {
				if(this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses()!=null){
					for(int i=0;i<this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();i++){
						Object object = this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i);
						if(object instanceof Functions){
							total = ((Functions)object).getFunction().size();
						}
					}
				}
				//first save current form
				super.updateJAXBObject(false);
				
			} catch (EacCpfFormException e1) {
				reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
			}
			
			//second put new node into eaccpf object
			putEmptyFurtherFunctionIntoDescriptionNode(total);
			
			//third refresh and save current form with empty field
			reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
		}

		private void putEmptyFurtherFunctionIntoDescriptionNode(int total) {
			Function function = new Function();
			PlaceEntry emptyPlaceEntry = new PlaceEntry();
			emptyPlaceEntry.setLang((firstLanguage!=null && !StringUtils.isEmpty(firstLanguage) && !firstLanguage.equals("---"))?firstLanguage:null);
			function.getPlaceEntry().add(emptyPlaceEntry);
			if(placesFunctionJTextfield!=null){
				int counter = eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();
				
				Functions lastFunctions = null;
				boolean found = false;
				int foundIndex = -1;
				for(int i = 0;!(found && lastFunctions!=null) && i<counter;i++){
					if(!(eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i) instanceof Functions)){
						found = true;
					}else if(eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i) instanceof Functions){
						found = false;
						foundIndex = i;
						lastFunctions = (Functions) eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i);
					}
				}
				boolean thereAreSomeFieldsEmpty = (total>0 && (lastFunctions==null || lastFunctions.getFunction().size()>total)); // there are at least one empty to be filled, so total is needed to flag user message
				boolean emptyNodeInside = false;
				if(lastFunctions==null){
					lastFunctions = new Functions();
					lastFunctions.getFunction().add(new Function());
					//search occupation node, all sorted
					int index = -1;
					for(int i=0;i<eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();i++){
						Object section = eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i);
						if(section instanceof Occupations){
							index = i;
						}
					}
					if(index>-1){
						eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(index,lastFunctions);
					}else{
						eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(lastFunctions);
					}
					emptyNodeInside = true;
				}
				if(lastFunctions!=null){
					if(thereAreSomeFieldsEmpty){
						JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.function"));
						if(!emptyNodeInside){
							lastFunctions.getFunction().add(new Function());
						}
					}else{
						int foundIndexToBeMoved = -1;
						thereAreSomeFieldsEmpty = total>lastFunctions.getFunction().size(); //there was deleted some empty nodes
						for(int x=0;!thereAreSomeFieldsEmpty && x<lastFunctions.getFunction().size();x++){
							if(lastFunctions.getFunction().get(x).getTerm()==null){
								thereAreSomeFieldsEmpty = true;
								foundIndexToBeMoved = x;
							}
						}
						
						if(thereAreSomeFieldsEmpty){ 
							JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.function"));//alert
							if(total>lastFunctions.getFunction().size()){
								lastFunctions.getFunction().add(function); //restored empty node
							}
						}
						if(!thereAreSomeFieldsEmpty){
							lastFunctions.getFunction().add(function); //put an empty one
						}else if(foundIndexToBeMoved>-1){
							Function targetFunction = lastFunctions.getFunction().get(foundIndexToBeMoved);
							lastFunctions.getFunction().add(targetFunction);
							lastFunctions.getFunction().remove(foundIndexToBeMoved);
						}
						if(foundIndex>-1){
							eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().set(foundIndex, lastFunctions);
						}
					}
				}
			}
		}
	}
	
	public class AddFurtherPlaceFunction extends UpdateEacCpfObject{

		private int index;
		
		public AddFurtherPlaceFunction(EacCpf eacCpf, JTabbedPane tabbedPane,ProfileListModel model,Integer index) {
			super(eacCpf, tabbedPane, model);
			this.index = (index!=null)?index:0;
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				//first save current form
				super.updateJAXBObject(false);
				
			} catch (EacCpfFormException e1) {
				reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
			}
			
			//second put new node into eaccpf object
			putEmptyAddressComponentIntoFunction();
			
			//third refresh and save current form with empty field
			reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
		}

		private void putEmptyAddressComponentIntoFunction() {
			if(placeFunctionPlaceJtextfields!=null && this.index<placeFunctionPlaceJtextfields.size()){
				
				int counter = eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();
				int i = 0;
				Functions lastFunctions = null;
				boolean found = false;
				for(;!(found && lastFunctions!=null) && i<counter;i++){
					if(!(eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i) instanceof Functions)){
						found = true;
					}else{
						lastFunctions = (Functions) eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i);
					}
				}
				if(lastFunctions!=null){
					boolean isEmpty = false;
					if(lastFunctions.getFunction()!=null && lastFunctions.getFunction().size()>index){
						List<PlaceEntry> placeEntries = lastFunctions.getFunction().get(index).getPlaceEntry();
						if(placeEntries!=null){
							for(PlaceEntry placeEntry:placeEntries){
								if(placeEntry!=null && placeEntry.getContent().isEmpty()){
									isEmpty = true;
								}
							}
							if(!isEmpty){
								isEmpty = placeFunctionPlaceJtextfields.get(this.index).size()>placeEntries.size();
								if(isEmpty){
									lastFunctions.getFunction().get(index).getPlaceEntry().add(new PlaceEntry());
								}
							}
						}
					}else{
						isEmpty = true;
						lastFunctions.getFunction().add(new Function());
					}
					if(isEmpty){
						JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.place"));
					}else{
						lastFunctions.getFunction().get(index).getPlaceEntry().add(new PlaceEntry());
					}
				}else{
					JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.place"));
				}
				
			}
		}
		
	}
	
	public class AddFurtherAddressAndComponentButton extends UpdateEacCpfObject{

		private int index;
		
		public AddFurtherAddressAndComponentButton(EacCpf eacCpf,JTabbedPane tabbedPane, ProfileListModel model, Integer placeEntryNumber) {
			super(eacCpf, tabbedPane, model);
			this.index = placeEntryNumber!=null?placeEntryNumber:0;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				//first save current form
				super.updateJAXBObject(false);
				
			} catch (EacCpfFormException e1) {
				reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
			}
			
			//second put new node into eaccpf object
			putEmptyAddressComponentIntoPlaces();
			
			//third refresh and save current form with empty field
			reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
		}

		private void putEmptyAddressComponentIntoPlaces() {
			if(addressDetailsTextFieldsWithComboBoxes!=null && placeEntryPlaceJComboBoxs!=null){
				List<TextFieldWithComboBoxEacCpf> addressDetailsList = addressDetailsTextFieldsWithComboBoxes.get(index);
				if (addressDetailsList != null && !addressDetailsList.isEmpty()) {
					boolean emptyText = false;
					boolean emptyCombo = false;

					for (int i = 0; !emptyText && i < addressDetailsList.size(); i++) {
						emptyCombo = false;
						TextFieldWithComboBoxEacCpf textFieldWithComboBoxEacCpf = addressDetailsList.get(i);
						String text = textFieldWithComboBoxEacCpf.getTextFieldValue();
						String combo = textFieldWithComboBoxEacCpf.getComboBoxValue(TextFieldWithComboBoxEacCpf.TYPE_ADDRESS_COMPONENT, entityType);
						if (StringUtils.isEmpty(trimStringValue(text))) {
							JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.place"));
							emptyText = true;
						}

						if (StringUtils.isEmpty(trimStringValue(combo))
										|| combo.equals(TextFieldWithComboBoxEacCpf.DEFAULT_VALUE)) {
							emptyCombo = true;
						}
					}

					if ((!emptyText && !emptyCombo)
							|| (emptyText && emptyCombo)
							|| (!emptyText && emptyCombo)) {
						Places currentPlaces = (Places) eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(0);
						currentPlaces.getPlace().get(index).getAddress().getAddressLine().add(new AddressLine()); //put a new one
					}
				}
			}
		}

	}

	
	public class AddFurtherAddressButton extends UpdateEacCpfObject{
		
		public AddFurtherAddressButton(EacCpf eacCpf, JTabbedPane tabbedPane, ProfileListModel model) {
			super(eacCpf, tabbedPane, model);
		}
		
		public void actionPerformed(ActionEvent e) {
			int total = 0;
			try {
				if(this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses()!=null){
					for(int i=0;i<this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();i++){
						Object object = this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i);
						if(object instanceof Places){
							total = ((Places)object).getPlace().size();
						}
					}
				}
				//first save current form
				super.updateJAXBObject(false);
				
			} catch (EacCpfFormException e1) {
				reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
			}
			
			//second put new node into eaccpf object
			putEmptyFurtherAddressIntoDescriptionNode(total);
			
			//third refresh and save current form with empty field
			reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
		}
		
		private void putEmptyFurtherAddressIntoDescriptionNode(int total) {
			Place emptyPlace = new Place();
			emptyPlace.setAddress(new Address());
			emptyPlace.setPlaceRole(new PlaceRole());
			PlaceEntry emptyPlaceEntry = new PlaceEntry();
			emptyPlaceEntry.setLang((firstLanguage!=null && !StringUtils.isEmpty(firstLanguage) && !firstLanguage.equals("---"))?firstLanguage:null);
			emptyPlace.getPlaceEntry().add(emptyPlaceEntry);
			boolean found = false;
			Places lastPlaces = null;
			int counter = eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();
			int i = 0;
			for(;!found && i<counter;i++){
				if(!(eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i) instanceof Places)){
					found = true;
				}else{
					lastPlaces = (Places) eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i);
				}
			}
			if(lastPlaces!=null){
				boolean lastNodeIsBlank = lastPlaces.getPlace().size()<total; //check if the last element is empty
				if(lastNodeIsBlank){ //put into target position
					lastPlaces.getPlace().add(emptyPlace);
					JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.places"));
				}else{
					for(int j=0;!lastNodeIsBlank && j<lastPlaces.getPlace().size();j++){
						Place place = lastPlaces.getPlace().get(j);
						if(place.getPlaceEntry()!=null && place.getPlaceEntry().size()>0){
							lastNodeIsBlank = (place.getPlaceEntry().get(place.getPlaceEntry().size()-1).getContent()!=null && 
								place.getPlaceEntry().get(place.getPlaceEntry().size()-1).getContent().isEmpty());
						}else{
							lastNodeIsBlank = true;
						}
						if(lastNodeIsBlank){
							int placeIndex = lastPlaces.getPlace().indexOf(place);
							lastPlaces.getPlace().add(place);
							lastPlaces.getPlace().remove(placeIndex);
						}
					}
					if(!lastNodeIsBlank){ //put into target position
						lastPlaces.getPlace().add(emptyPlace);
					}else{
						JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.places"));
					}
				}
			}else{
				JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.places"));
			}
		}
		
	}
	
	/**
	 * Class to performs the addition of new dates or dateRanges in name
	 * section and existence section if the previous values are filled.
	 */
	public class AddSingleOrRangeDateAction extends UpdateEacCpfObject {
		
		private String sectionType;
		private boolean isDateRange;
		private int figureElement;

		/**
		 * Constructor.
		 *
		 * @param eacCpf
		 * @param tabbedPane
		 * @param model
		 * @param sectionType
		 * @param isDateRange
		 * @param index
		 */
		public AddSingleOrRangeDateAction(EacCpf eacCpf, JTabbedPane tabbedPane, ProfileListModel model, String sectionType, boolean isDateRange, Integer index) {
			super(eacCpf, tabbedPane, model);
			this.sectionType = sectionType;
			this.isDateRange = isDateRange;
			this.figureElement = index!=null?index:0;
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try{
				super.updateJAXBObject(false);
			}catch(EacCpfFormException e){
				reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
			}
			//Recover the lists of elements for the current section.
			List<TextFieldsWithRadioButtonForDates> textFieldsWithRadioButtonForDatesList = null;
			if (this.sectionType!=null) {
				if(this.sectionType.equals(PLACES) && placesDates!=null && placesDates.size()>0 && placesDates.containsKey(this.figureElement)){
					textFieldsWithRadioButtonForDatesList = placesDates.get(this.figureElement);
				}else if(this.sectionType.equals(FUNCTIONS) && functionsDates!=null && functionsDates.size()>0 && functionsDates.containsKey(this.figureElement)){
					textFieldsWithRadioButtonForDatesList = functionsDates.get(this.figureElement);
				}else if(this.sectionType.equals(OCCUPATIONS) && occupationsDates!=null && occupationsDates.size()>0 && occupationsDates.containsKey(this.figureElement)){
					textFieldsWithRadioButtonForDatesList = occupationsDates.get(this.figureElement);
				}
			}
			boolean emptyDate = false;
			boolean emptyDateRange = false;
			if(textFieldsWithRadioButtonForDatesList!=null){ //if it's not found, it means there is any previews dates
				for (int i = 0; !emptyDate && !emptyDateRange && i < textFieldsWithRadioButtonForDatesList.size(); i++) {
					TextFieldsWithRadioButtonForDates textFieldsWithRadioButtonForDates = textFieldsWithRadioButtonForDatesList.get(i);
					//Check if there are empty box, if there are one and is the previews selected report it
					if (textFieldsWithRadioButtonForDates.isSelectedDateDefinedRB() && !textFieldsWithRadioButtonForDates.isDateRange()) {
						// Check if some date value is empty.
						if (StringUtils.isEmpty(textFieldsWithRadioButtonForDates.getDateValue())) {
							if(!this.isDateRange){
								if(!globalIsActivated){
									globalIsActivated = true;
									JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.single.date"));
									globalIsActivated = false;
								}
							}
							emptyDate = true;
						}
					} else  if (textFieldsWithRadioButtonForDates.isDateRange()) {
						// Check if some dateRage is empty (both dateFrom and dateTo).
						if ((textFieldsWithRadioButtonForDates.isSelectedDateFromDefinedRB() && StringUtils.isEmpty(textFieldsWithRadioButtonForDates.getDateFromValue()) )
								|| (textFieldsWithRadioButtonForDates.isSelectedDateToDefinedRB() && StringUtils.isEmpty(textFieldsWithRadioButtonForDates.getDateToValue()))) {
							if(this.isDateRange || 
									!(!this.isDateRange && (StringUtils.isEmpty(textFieldsWithRadioButtonForDates.getDateFromValue()) && StringUtils.isEmpty(textFieldsWithRadioButtonForDates.getDateToValue()))) ||
									((StringUtils.isEmpty(textFieldsWithRadioButtonForDates.getDateFromValue()) || StringUtils.isEmpty(textFieldsWithRadioButtonForDates.getDateToValue())) && 
											!((StringUtils.isEmpty(textFieldsWithRadioButtonForDates.getDateFromValue()) && textFieldsWithRadioButtonForDates.isSelectedDateFromDefinedRB() )&& 
													StringUtils.isEmpty(textFieldsWithRadioButtonForDates.getDateToValue()) && textFieldsWithRadioButtonForDates.isSelectedDateToDefinedRB()))
								){
								if(!globalIsActivated){
									globalIsActivated = true;
									JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.range.date"));
									globalIsActivated = false;
								}
							}
							emptyDateRange = true;
						}
					}
				}
				if(!emptyDateRange || !emptyDate){
					checkAndFillDates(textFieldsWithRadioButtonForDatesList,emptyDate,emptyDateRange);
				}
			}else{
				checkAndFillDates(textFieldsWithRadioButtonForDatesList,emptyDate,emptyDateRange);
			}
			reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
		}
		private void checkAndFillDates(List<TextFieldsWithRadioButtonForDates> textFieldsWithRadioButtonForDatesList,boolean emptyDate,boolean emptyDateRange) {
			//Checks if its needed to add new block, depending of the selected action.
			Date date = null;
			DateRange dateRange = null;
			if (!this.isDateRange) {
				date = new Date();
			} else {
				dateRange = new DateRange();
			}
			List<Object> datesList = null;
			Object objectDates = null;
			boolean found = false;
			int lastIndexFound = -1;
			if (this.sectionType!=null) {
				Places places = null;
				Functions functions = null;
				Occupations occupations = null;
				int i=-1;
				int size=-1;
				//choose each section (for target dates) and fill it to be used with target dates objects
				if (this.sectionType.equals(PLACES)) {
					for(i=0;!(places!=null && !found) && i<this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();i++){
						if(this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i) instanceof Places){
							places = (Places)this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i);
							lastIndexFound = i;
						}else{
							found = true;
						}
					}
					if(places!=null && places.getPlace().size()>figureElement){
						objectDates = (places.getPlace().get(figureElement).getDate()!=null)?places.getPlace().get(figureElement).getDate():
							(places.getPlace().get(figureElement).getDateRange()!=null?
								places.getPlace().get(figureElement).getDateRange():
								(places.getPlace().get(figureElement).getDateSet()!=null?
										places.getPlace().get(figureElement).getDateSet():null
								)
							);
					}else{
						places = new Places();
						this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(places);
						lastIndexFound=i>0?i-1:0;
					}
				} else if(this.sectionType.equals(FUNCTIONS)){
					for(i=0;!(functions!=null) && i<this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();i++){
						if(this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i) instanceof Functions){
							functions = (Functions)this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i);
							lastIndexFound = i;
						}
					}
					if(functions!=null && functions.getFunction().size()>figureElement){
						objectDates = (functions.getFunction().get(figureElement).getDate()!=null)?functions.getFunction().get(figureElement).getDate():
							(functions.getFunction().get(figureElement).getDateRange()!=null?
								functions.getFunction().get(figureElement).getDateRange():
								(functions.getFunction().get(figureElement).getDateSet()!=null?
									functions.getFunction().get(figureElement).getDateSet():null
								)								
							);
					}else{
						Functions foundObject = null;
						if(this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses()!=null && this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size()>0){
							for(Object object : this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses()){
								if(object instanceof Functions)
								foundObject = (Functions)object;
							}
						}
						if(foundObject!=null){
							foundObject.getFunction().add(new Function());
						}else{
							functions = new Functions();
							//try to reorder if necessary
							if(this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size()>0){
								boolean exit = false;
								for(int a=0;!exit && a<this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();a++){
									Object section = this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(a);
									if(section!=null){
										if(section instanceof Occupation){
											this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(a, functions);
											exit = true;
										}
									}
								}
								if(!found){
									this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(functions);
									lastIndexFound=i>0?i-1:0;
								}
							}else{
								this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(functions);
								lastIndexFound=i>0?i-1:0;
							}
						}
					}
				} else if(this.sectionType.equals(OCCUPATIONS)){
					size = this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();
					for(i=0;!(occupations!=null && !found) && i<size;i++){
						if(this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i) instanceof Occupations){
							occupations = (Occupations)this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(i);
							lastIndexFound = i;
						}else{
							found = true;
						}
					}
					if(occupations!=null && occupations.getOccupation().size()>figureElement){
						objectDates = (occupations.getOccupation().get(figureElement).getDate()!=null)?occupations.getOccupation().get(figureElement).getDate():
							(occupations.getOccupation().get(figureElement).getDateRange()!=null?
								occupations.getOccupation().get(figureElement).getDateRange():
								(occupations.getOccupation().get(figureElement).getDateSet()!=null?
									occupations.getOccupation().get(figureElement).getDateSet():null
								)								
							);
					}else{
						Occupations foundObject = null;
						if(this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses()!=null && this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size()>0){
							for(Object object : this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses()){
								if(object instanceof Occupations)
								foundObject = (Occupations)object;
							}
						}
						if(foundObject!=null){
							foundObject.getOccupation().add(new Occupation());
						}else{
							occupations = new Occupations();
							this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(occupations);
							lastIndexFound=i>0?i-1:0;
						}
					}
				}
				if(objectDates!=null){ //let's continue filling object dates
					datesList = getAllDatesFromDatesObject(objectDates);
					//Checks elements in the dates list.
					DateSet dateSet = null;
					if (!datesList.isEmpty()) {
						int dateSize = 0;
						int dateRangeSize = 0;
						
						if(textFieldsWithRadioButtonForDatesList!=null){
							for (int x = 0; x < textFieldsWithRadioButtonForDatesList.size(); x++) {
								TextFieldsWithRadioButtonForDates textFieldsWithCheckBoxForDates = textFieldsWithRadioButtonForDatesList.get(x);

								if (textFieldsWithCheckBoxForDates.isDateRange()) {
									dateRangeSize++;
								} else {
									dateSize++;
								}
							}
						}
						
						if (date != null || dateRange != null) {
							dateSet = new DateSet();
							dateSet.getDateOrDateRange().addAll(datesList);
							
							int eacDatesSize = (datesList!=null)?datesList.size():0;
							
							if (date != null && ( !(emptyDate || emptyDateRange) || dateSize > (eacDatesSize - dateRangeSize) )) {
								dateSet.getDateOrDateRange().add(date);
							} else if(dateRange!=null && (!(emptyDateRange || emptyDate)  || dateSize > (eacDatesSize - dateRangeSize) )){
								dateSet.getDateOrDateRange().add(dateRange);
							}
						}
					}
					//Add dates to the target section.
					if ((this.sectionType.equals(PLACES) && placesDates!=null) || (this.sectionType.equals(FUNCTIONS) && functionsDates!=null) || (this.sectionType.equals(OCCUPATIONS) && occupationsDates!=null)) {
						/*Object section = */fillTargetDateAndStoreIntoSection(dateSet!=null?dateSet:(date!=null?date:dateRange),(places!=null)?places:(functions!=null?functions:occupations));
					}
				}else{
					//Add dates to the target section.
					if ((this.sectionType.equals(PLACES) && placesDates!=null) || (this.sectionType.equals(FUNCTIONS) && functionsDates!=null) || (this.sectionType.equals(OCCUPATIONS) && occupationsDates!=null)) {
						boolean sectionWasNull = ((this.sectionType.equals(PLACES) && places==null) || (this.sectionType.equals(FUNCTIONS) && functions==null) || (this.sectionType.equals(OCCUPATIONS) || occupations==null));
						Object section = fillTargetDateAndStoreIntoSection(/*dateSet!=null?dateSet:(*/date!=null?date:dateRange/*)*/,(places!=null)?places:(functions!=null?functions:occupations));
						if(section!=null){
							if(places!=null){
								places.getPlace().set(this.figureElement,(Place)section);
								if(!sectionWasNull){
									this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().set(lastIndexFound,places);
								}else{
									boolean reFound = false;
									int z=0;
									for(;z<this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();z++){
										Object seekSection = this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(z);
										if(seekSection instanceof Places){
											reFound = true;
										}
									}
									//refound flag for place
									if(!reFound){
										this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(places);
									}
								}
							}else if(functions!=null){
								functions.getFunction().set(this.figureElement,(Function)section);
								if(!sectionWasNull){
									this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().set(lastIndexFound,functions);
								}else{
									boolean reFound = false;
									int z=0;
									for(;z<this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();z++){
										Object seekSection = this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(z);
										if(seekSection instanceof Functions){
											reFound = true;
										}
									}
									//refound flag for function
									if(!reFound){
										this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(functions);
									}
								}
							}else if(occupations!=null){
								occupations.getOccupation().set(this.figureElement,(Occupation)section);
								if(!sectionWasNull){
									this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().set(lastIndexFound,occupations);
								}else{
									boolean reFound = false;
									int z=0;
									for(;z<this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().size();z++){
										Object seekSection = this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().get(z);
										if(seekSection instanceof Occupations){
											reFound = true;
										}
									}
									//refound flag for occupation
									if(!reFound){
										this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(occupations);
									}
								}
							}
						}
					}
				}
			}
		}

		/**
		 * Fill a list of objects (instanceof Date, DateRange or DateSet)
		 * using the param.
		 */
		private List<Object> getAllDatesFromDatesObject(Object objectDates) {
			List<Object> datesList = new ArrayList<Object>();
			if(objectDates!=null){
				if (objectDates instanceof Date) { //Only Date element.
					datesList.add((Date) objectDates);
				}else if (objectDates instanceof DateRange) { //Only DateRange element.
					datesList.add((DateRange) objectDates);
				}else if (objectDates instanceof DateSet) { //Any combination of Date and DateRange elements.
					for (Object object : ((DateSet)objectDates).getDateOrDateRange()) {
						datesList.add(object);
					}
				}
			}
			return datesList;
		}
		/**
		 * Fill dates elements (Date, DateRange or DateSet)
		 * for sections (Places, Functions, Occupations) using figure section
		 * and returns modified section (at Object way) or null if is an invalid section
		 * using a generic way for all cases.
		 */
		private Object fillTargetDateAndStoreIntoSection(Object datesObject,Object section) {
			if(section!=null){
				if(section instanceof Places){
					if(((Places)section).getPlace().size()<=this.figureElement){
						Place place = new Place();
						place.getPlaceEntry().add(new PlaceEntry());
						((Places)section).getPlace().add(place);
					}
					Place place = ((Places)section).getPlace().get(this.figureElement);
					place.setDate(datesObject instanceof Date?(Date)datesObject:null);
					place.setDateRange(datesObject instanceof DateRange?(DateRange)datesObject:null);
					place.setDateSet(datesObject instanceof DateSet?(DateSet)datesObject:null);
					return place;
				}else if(section instanceof Functions){
					if(((Functions)section).getFunction().size()<=this.figureElement){
						Function function = new Function();
						function.getPlaceEntry().add(new PlaceEntry());
						((Functions)section).getFunction().add(function);
					}
					Function function = ((Functions)section).getFunction().get(this.figureElement);
					function.setDate(datesObject instanceof Date?(Date)datesObject:null);
					function.setDateRange(datesObject instanceof DateRange?(DateRange)datesObject:null);
					function.setDateSet(datesObject instanceof DateSet?(DateSet)datesObject:null);
					return function;
				}else if(section instanceof Occupations){
					if(((Occupations)section).getOccupation().size()<=this.figureElement){
						Occupation ocupation = new Occupation();
						ocupation.getPlaceEntry().add(new PlaceEntry());
						((Occupations)section).getOccupation().add(ocupation);
					}
					Occupation ocupation = ((Occupations)section).getOccupation().get(this.figureElement);
					ocupation.setDate(datesObject instanceof Date?(Date)datesObject:null);
					ocupation.setDateRange(datesObject instanceof DateRange?(DateRange)datesObject:null);
					ocupation.setDateSet(datesObject instanceof DateSet?(DateSet)datesObject:null);
					return ocupation;
				}
			}
			return null;
		}
	}
	
	public class AddIsoText implements FocusListener {
		private TextFieldsWithRadioButtonForDates tfwcbfDates;
		private String dateType;

		/**
		 * Constructor.
		 *
		 * @param tfwcbfDates
		 * @param dateType
		 */
		public AddIsoText(TextFieldsWithRadioButtonForDates tfwcbfDates, String dateType) {
			this.tfwcbfDates = tfwcbfDates;
			this.dateType = dateType;
		}

		@Override
		public void focusGained(FocusEvent e) {
			// No action
		}

		@Override
		public void focusLost(FocusEvent e) {
			boolean valid = true;
			if(e.getOppositeComponent() instanceof ButtonTab){
				ActionListener[] actionListeners = ((ButtonTab)e.getOppositeComponent()).getActionListeners();
				for(ActionListener actionListener:actionListeners){
					if(actionListener instanceof ChangeTabBtnAction){
						valid = false;
					}
				}
			}else if(e.getOppositeComponent() instanceof JTabbedPane){
				valid = false;
			}
			if(valid || globalIsActivated){
				if (EacCpfIdentityPanel.UNKNOWN_DATE.equalsIgnoreCase(this.dateType)) {
					this.tfwcbfDates.getStandardDateTextField().setText(parseStandardDate(this.tfwcbfDates.getDateValue()));
				} else if (EacCpfIdentityPanel.UNKNOWN_DATE_FROM.equalsIgnoreCase(this.dateType)) {
					this.tfwcbfDates.getStandardDateFromTextField().setText(parseStandardDate(this.tfwcbfDates.getDateFromValue()));

					if(!checkDates(this.tfwcbfDates.getStandardDateFromValue(), this.tfwcbfDates.getStandardDateToValue())){
						if (isShowErrorDateRange()) {
							setShowErrorDateRange(false);
							globalIsActivated = true;
							JOptionPane.showMessageDialog(tabbedPane, labels.getString("eaccpf.commons.error.no.standard.dateRange"));
							globalIsActivated = false;
							this.tfwcbfDates.getDateFromTextField().setText("");
							this.tfwcbfDates.getStandardDateFromTextField().setText("");
							setShowErrorDateRange(true);
						}
					}
				} else if (EacCpfIdentityPanel.UNKNOWN_DATE_TO.equalsIgnoreCase(this.dateType)) {
					this.tfwcbfDates.getStandardDateToTextField().setText(parseStandardDate(this.tfwcbfDates.getDateToValue()));

					if(!checkDates(this.tfwcbfDates.getStandardDateFromValue(), this.tfwcbfDates.getStandardDateToValue())){
						if (isShowErrorDateRange()) {
							setShowErrorDateRange(false);
							globalIsActivated = true;
							JOptionPane.showMessageDialog(tabbedPane, labels.getString("eaccpf.commons.error.no.standard.dateRange"));
							globalIsActivated = false;
							this.tfwcbfDates.getDateToTextField().setText("");
							this.tfwcbfDates.getStandardDateToTextField().setText("");
							setShowErrorDateRange(true);
						}
					}
				}
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

			if(!errors.isEmpty()) {
				throw new EacCpfFormException("Errors in validation of EAC-CPF");
			}
			
			if (this.eaccpf!=null && this.eaccpf.getCpfDescription() != null){
				// Save the current existDates fill in Identity tab.
				// (1.1, get previews dates) /eacCpf/cpfDescription/description/existDates
				ExistDates existDates = this.eaccpf.getCpfDescription().getDescription().getExistDates();

				this.eaccpf.getCpfDescription().setDescription(new Description()); //empty object

				// (1.2, put previews dates) /eacCpf/cpfDescription/description/existDates
				this.eaccpf.getCpfDescription().getDescription().setExistDates(existDates);
				
				// (2 get places part) /eacCpf/cpfDescription/description/places
				Places places = getPlaces(save);
				if(places!=null && places.getPlace()!=null && !places.getPlace().isEmpty()){
					this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(places);
				}
				
				// (3 get functions part) /eacCpf/cpfDescription/description/functions
				Functions functions = getFunctions(save);
				if(functions!=null && functions.getFunction()!=null && !functions.getFunction().isEmpty()){
					this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(functions);
				}
				
				// (4 get occupations part) /eacCpf/cpfDescription/description/occupations
				Occupations occupations = getOccupations(save);
				if(occupations!=null && occupations.getOccupation()!=null && !occupations.getOccupation().isEmpty()){
					this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(occupations);
				}
				
				// (5 get structures and genealogies parts) /eacCpf/cpfDescription/description/structureOrGenealogy
				StructureOrGenealogy genealogy = getStructureOrGenealogy(save);
				if(genealogy!=null && genealogy.getMDiscursiveSet()!=null && !genealogy.getMDiscursiveSet().isEmpty()){
					this.eaccpf.getCpfDescription().getDescription().getPlacesOrLocalDescriptionsOrLegalStatuses().add(genealogy);
				}
				
				// (6 get BioHist node) /eacCpf/cpfDescription/description/bioghist
				BiogHist bioghist = getBiogHist(save);
				if(bioghist!=null && bioghist.getChronListOrPOrCitation()!=null && !bioghist.getChronListOrPOrCitation().isEmpty()){
					this.eaccpf.getCpfDescription().getDescription().getBiogHist().add(bioghist);
				}
				
				// (7 get Relations node) /eacCpf/cpfDescription/relations
				
				// Clear the current resource relations list.
				List<ResourceRelation> resourceRelationList=null;
				List<ResourceRelation> resourceRelationsToBePreserved = new ArrayList<ResourceRelation>();
				if(this.eaccpf.getCpfDescription().getRelations()!=null){
					resourceRelationList = this.eaccpf.getCpfDescription().getRelations().getResourceRelation();
				}
				for(int i=0;resourceRelationList!=null && i < resourceRelationList.size();  i++){
					if(resourceRelationList.get(i)!=null && (resourceRelationList.get(i).getArcrole()==null || !resourceRelationList.get(i).getArcrole().equals(ARCROLE_IMAGE))){
						resourceRelationsToBePreserved.add(resourceRelationList.get(i));
					}
				}
				if(this.eaccpf.getCpfDescription().getRelations()!=null && this.eaccpf.getCpfDescription().getRelations().getResourceRelation()!=null){
					this.eaccpf.getCpfDescription().getRelations().getResourceRelation().clear();
				}
				
				List<ResourceRelation> resourceRelations = getResourceRelation(save);
				
				if(this.eaccpf.getCpfDescription().getRelations()!=null && this.eaccpf.getCpfDescription().getRelations().getResourceRelation()!=null){
					this.eaccpf.getCpfDescription().getRelations().getResourceRelation().addAll(resourceRelationsToBePreserved);
				}
				
				if(resourceRelations!=null && !resourceRelations.isEmpty()){
					if (this.eaccpf.getCpfDescription().getRelations() == null) {
						this.eaccpf.getCpfDescription().setRelations(new Relations());
					}
					this.eaccpf.getCpfDescription().getRelations().getResourceRelation().addAll(resourceRelations);
				}
				
			}
		}
		
		private List<ResourceRelation> getResourceRelation(boolean save){
			
			List<ResourceRelation> resourceRelations = new ArrayList<ResourceRelation>();
			
 			for (int i = 0; i < resourcesRelationTitle.size(); i++) {
				ResourceRelation resourceRelation = new ResourceRelation();
				String language = null;
				resourceRelation.setArcrole(ARCROLE_IMAGE);
				resourceRelation.setResourceRelationType(SUBJECT_OF);
				resourceRelation.setType(SIMPLE);
				boolean write = false;
				boolean write2 = false;
				boolean write3 = false;
				boolean write4 = false;
				boolean write5 = false;
				boolean write6 = false;
				
				if(resourcesRelationTitle.get(i).getLanguage()!=null && 
						!resourcesRelationTitle.get(i).getLanguage().equals(TextFieldWithComboBoxEacCpf.DEFAULT_VALUE)){
					
					language = resourcesRelationTitle.get(i).getLanguage();
				}
				
				if(resourcesRelationTitle.get(i)!=null && StringUtils.isNotEmpty(resourcesRelationTitle.get(i).getTextValue())){
					write = true;
					//title
					RelationEntry relationEntryTitle = new RelationEntry();
					relationEntryTitle.setContent(trimStringValue(resourcesRelationTitle.get(i).getTextValue()));
					relationEntryTitle.setLocalType(EacCpfRelationsPanel.LOCALTYPE_TITLE);
					if (StringUtils.isNotEmpty(language)) {
						relationEntryTitle.setLang(language);
					}
					resourceRelation.getRelationEntry().add(relationEntryTitle);
				}
				
				//id
				String id = trimStringValue(resourcesRelationIdentifier.get(i).getText());
				if (StringUtils.isNotEmpty(id)) {
					RelationEntry relationEntry = new RelationEntry();
					relationEntry.setContent(id);
					relationEntry.setLocalType(EacCpfRelationsPanel.LOCALTYPE_ID);
					if (StringUtils.isNotEmpty(language)) {
						relationEntry.setLang(language);
					}
					resourceRelation.getRelationEntry().add(relationEntry);
					write2 = true;
				}
				
				//link
				String link = trimStringValue(resourcesRelationLink.get(i).getText());
				if (StringUtils.isNotEmpty(link)) {
					resourceRelation.setHref(link);
					write3 = true;
				}
				
				//agencyName
				String agencyName = trimStringValue(resourcesRelationAgencyName.get(i).getText());
				if (StringUtils.isNotEmpty(agencyName)){
					RelationEntry relationEntry = new RelationEntry();
					relationEntry.setContent(agencyName);
					relationEntry.setLocalType(EacCpfRelationsPanel.LOCALTYPE_AGENCYNAME);
					if(StringUtils.isNotEmpty(agencyName)){
						relationEntry.setLang(language);
					}
					resourceRelation.getRelationEntry().add(relationEntry);
					write4 = true;
				}
				 
				//date
				 Date date = null;
		            if (StringUtils.isNotEmpty(trimStringValue(resourcesRelationDate.get(i).getDateValue()))) {
		                date = new Date();
		                date.setContent(trimStringValue(resourcesRelationDate.get(i).getDateValue()));
		                if (StringUtils.isNotEmpty(parseStandardDate(trimStringValue(resourcesRelationDate.get(i).getStandardDateValue())))) {
		                    date.setStandardDate(parseStandardDate(trimStringValue(resourcesRelationDate.get(i).getStandardDateValue())));
		                }
		            } else if (resourcesRelationDate.get(i).isSelectedDateUndefinedRB()) {
		                date = new Date();
		                date.setContent(EacCpfIdentityPanel.UNKNOWN);
		            } else if (resourcesRelationDate.get(i).isSelectedDateStillRB()) {
		                date = new Date();
		                date.setContent("open");
		            }

		            if(date != null && resourcesRelationDate.get(i).isSelectedDateUndefinedRB()){
		                date.setLocalType(EacCpfIdentityPanel.UNKNOWN);
		            }else if(date != null && resourcesRelationDate.get(i).isSelectedDateStillRB()){
		                date.setLocalType("open");
		            }
		            if(date!=null){
		            	resourceRelation.setDate(date);
		            	write5 = true;
		            }
				    
				 // Try to recover the descriptive note of the relation.
					// /eacCpf/cpfDescription/relations/resourceRelation/descriptiveNote
		            DescriptiveNote descriptiveNote = new DescriptiveNote();
		            for(TextAreaWithLanguage resourceRelationDescriptive: resourcesRelationDescriptive.get(i)) {
						String description = trimStringValue(resourceRelationDescriptive.getTextValue());
						if (StringUtils.isNotEmpty(description)) {
							P p = new P();
							p.setContent(description);
							if (StringUtils.isNotEmpty(language)) {
								p.setLang(language);
							}
							descriptiveNote.getP().add(p);
							write6 = true;
						}
		            }
		            if (write6) {
						resourceRelation.setDescriptiveNote(descriptiveNote);
		            }
				    
				if((save && write3) || (!save && (write || write2 || write3 || write4 || write5 || write6))){
					resourceRelations.add(resourceRelation);
				}	
			}	
			
			return resourceRelations;
		}
		
		private BiogHist getBiogHist(boolean save) {
			BiogHist biogHist = new BiogHist();
			
			if(biographyHistoryJTextfields!=null){
				for(int i=0;i<biographyHistoryJTextfields.size();i++){
					if(biographyHistoryJTextfields!=null && biographyHistoryJTextfields.size()>i){
						if(!biographyHistoryJTextfields.get(i).getTextValue().isEmpty()
								&& StringUtils.isNotEmpty(trimStringValue(biographyHistoryJTextfields.get(i).getTextValue()))/* || !save*/){
							P p = new P();
							p.setLang(biographyHistoryJComboBoxes.get(i).getSelectedItem()!=null?LanguageIsoList.getIsoCode(biographyHistoryJComboBoxes.get(i).getSelectedItem().toString()):"");
							p.setContent(trimStringValue(biographyHistoryJTextfields.get(i).getTextValue()));
							biogHist.getChronListOrPOrCitation().add(p);
						}
					}
				}
			}
			
			return biogHist;
		}

		private StructureOrGenealogy getStructureOrGenealogy(boolean save) {
			
			StructureOrGenealogy genealogy = new StructureOrGenealogy();
			if(genealogyTextFields!=null){
				for(int i=0;i<genealogyTextFields.size();i++){
					if(genealogyTextFields!=null && genealogyTextFields.size()>i){
						if(!genealogyTextFields.get(i).getTextValue().isEmpty()
								&& StringUtils.isNotEmpty(trimStringValue(genealogyTextFields.get(i).getTextValue()))/* || !save*/){
							P p = new P();
							p.setLang(LanguageIsoList.getIsoCode((genealogyLanguagesJComboBoxes.get(i).getSelectedItem()!=null)?genealogyLanguagesJComboBoxes.get(i).getSelectedItem().toString():""));
							p.setContent(trimStringValue(genealogyTextFields.get(i).getTextValue()));
							genealogy.getMDiscursiveSet().add(p);
						}
					}
				}
			}
			return genealogy;
		}

		private Places getPlaces(boolean save) {
			Places places = new Places();
			
			if(placeEntryPlaceTextFields!=null){
				
				PlaceEntry placeEntry = null;
				
				for(int i=0;i<placeEntryCountryJComboBoxs.size();i++){
					//fill each place
					Place place = new Place();
					
					boolean write = false;
					
					//fill each PlaceEntry object
					placeEntry = new PlaceEntry();
					
					String placeEntryPlaceText = placeEntryPlaceTextFields.get(i).getText();
					if(placeEntryPlaceText!=null && !StringUtils.isEmpty(placeEntryPlaceText)){
						write = true;
						placeEntry.setContent(trimStringValue(placeEntryPlaceText));
					}
					
					if(placeEntryPlaceJComboBoxs!=null && placeEntryPlaceJComboBoxs.size()>i && placeEntryPlaceJComboBoxs.get(i).getSelectedItem()!=null){
						String item = placeEntryPlaceJComboBoxs.get(i).getSelectedItem().toString();
						if(item!=null && !item.isEmpty() && !item.equals(TextFieldWithComboBoxEacCpf.DEFAULT_VALUE)){
//							write = true;
							placeEntry.setLang(LanguageIsoList.getIsoCode(trimStringValue(item)));
						}
					}
					if(placeEntryCountryJComboBoxs!=null && placeEntryCountryJComboBoxs.size()>i){
						if(placeEntryCountryJComboBoxs.get(i).getSelectedItem()!=null){
							String item = placeEntryCountryJComboBoxs.get(i).getSelectedItem().toString();
							if(item!=null && !item.isEmpty() && !item.equals(TextFieldWithComboBoxEacCpf.DEFAULT_VALUE)){
								write = true;
								if(item!=null && !item.isEmpty() && countriesMap.containsKey(item)){
									item = countriesMap.get(item);
								}
								placeEntry.setCountryCode(item);
							}
						}
					}
					
					if(placeEntryPlaceRoles!=null && placeEntryPlaceRoles.size()>i && placeEntryPlaceRoles.get(i).getComboBoxValue(TextFieldWithComboBoxEacCpf.TYPE_PLACE_ROLE, entityType)!=null){
						String item = placeEntryPlaceRoles.get(i).getComboBoxValue(TextFieldWithComboBoxEacCpf.TYPE_PLACE_ROLE, entityType);
						if(item!=null && !item.isEmpty() && !item.equals(TextFieldWithComboBoxEacCpf.DEFAULT_VALUE)){
							write = true;
							placeEntry.setLocalType(trimStringValue(item));
						}
					}
					boolean write4 = false;
					if(placeEntryPlaceVocabularies!=null && placeEntryPlaceVocabularies.size()>i){
						if (StringUtils.isNotEmpty(placeEntryPlaceVocabularies.get(i).getText())
								&& StringUtils.isNotEmpty(trimStringValue(placeEntryPlaceVocabularies.get(i).getText()))) {
							write4 = true;
							placeEntry.setVocabularySource(trimStringValue(placeEntryPlaceVocabularies.get(i).getText().toString()));
						}
					}
					
					if(write || write4){ //used to be sure if there should be a placeEntry node
						place.getPlaceEntry().add(placeEntry);
					}
					
					Address address = new Address();
					boolean write2 = false; //refresh flag
					
					//fill each AddressLine object
					
					for(TextFieldWithComboBoxEacCpf addressJTextField : addressDetailsTextFieldsWithComboBoxes.get(i)){
						AddressLine addressLine = new AddressLine();
						
						if(placeEntryPlaceJComboBoxs!=null && placeEntryPlaceJComboBoxs.size()>i && placeEntryPlaceJComboBoxs.get(i).getSelectedItem()!=null){
							String item = placeEntryPlaceJComboBoxs.get(i).getSelectedItem().toString();
							if(item!=null && !item.isEmpty() && !item.equals(TextFieldWithComboBoxEacCpf.DEFAULT_VALUE)){
//								write2 = true;
								addressLine.setLang(LanguageIsoList.getIsoCode(trimStringValue(item))); //the only lang element available from this part of the form is the same that contains placeEntry
							}
						}
						
						if(addressDetailsTextFieldsWithComboBoxes!=null && addressDetailsTextFieldsWithComboBoxes.size()>i){
							String addressLineContent = addressJTextField.getTextFieldValue();
							if(addressLineContent!=null && !addressLineContent.isEmpty()){
								write2 = true;
								addressLine.setContent(trimStringValue(addressLineContent));
							}
							//component
							String addressLineComponent = addressJTextField.getComboBoxValue(TextFieldWithComboBoxEacCpf.TYPE_ADDRESS_COMPONENT, entityType);
							if(!addressLineComponent.isEmpty() && !TextFieldWithComboBoxEacCpf.DEFAULT_VALUE.equalsIgnoreCase(addressLineComponent)){
								write2 = true;
								addressLine.setLocalType(trimStringValue(addressLineComponent));
							}
						}
						
						if((addressLine.getContent()!=null && !addressLine.getContent().isEmpty()) || (addressLine.getLocalType()!=null && !addressLine.getLocalType().isEmpty() ) ){
							write2 = true;
							address.getAddressLine().add(addressLine);
						}
					}
					
					place.setAddress(address);
					
					//date or dateRange part
					boolean write3 = false;
					if(placesDates!=null && placesDates.containsKey(i) && placesDates.get(i)!=null && placesDates.get(i).size()>0){
						place = (Place)updateSectionDates(place,placesDates.get(i));
						if(place.getDate()!=null || place.getDateRange()!=null || place.getDateSet()!=null){
							write3 = true; //store place into places node for dates part
							//check if there is an placeEntry to be placed
							if(place.getPlaceEntry().isEmpty()){
								place.getPlaceEntry().add(placeEntry);
							}
						}
					}
					
					if((write || write2 || write3 || write4 && !save) || (save && write)){ //put if there are any new information
						places.getPlace().add(place);
					}
				}
			}
			return places;
		}

		private Functions getFunctions(boolean save) {
			Functions functions = new Functions();
			if(placesFunctionJTextfield!=null){
				for(int i=0;i<placesFunctionJTextfield.size();i++){
					Function function = new Function();
					boolean write = false;
					Term term = new Term();
					String content = placesFunctionJTextfield.get(i).getText();
					if(StringUtils.isNotEmpty(content)){
						term.setContent(trimStringValue(content));
						write = true;
					}
					
					if(placesFunctionJComboBoxes!=null && placesFunctionJComboBoxes.size()>i && placesFunctionJComboBoxes.get(i).getSelectedItem()!=null){
						term.setLang(LanguageIsoList.getIsoCode(trimStringValue(placesFunctionJComboBoxes.get(i).getSelectedItem().toString())));
//						write = true;
					}
					boolean write5 = false;
					if(placesVocabularyJTextFields!=null && placesVocabularyJTextFields.size()>i){
						if (StringUtils.isNotEmpty(placesVocabularyJTextFields.get(i).getText())
								&& StringUtils.isNotEmpty(trimStringValue(placesVocabularyJTextFields.get(i).getText()))) {
							term.setVocabularySource(trimStringValue(placesVocabularyJTextFields.get(i).getText()));
							write5 = true;
						}
					}
					if(write || write5){
						function.setTerm(term);
					}
					
					boolean write2 = false;

					if(placesDescriptionTextfields!=null && placesDescriptionTextfields.size()>i){
						if (StringUtils.isNotEmpty(placesDescriptionTextfields.get(i).getTextValue())
								&& StringUtils.isNotEmpty(trimStringValue(placesDescriptionTextfields.get(i).getTextValue()))) {
							DescriptiveNote descriptiveNote = new DescriptiveNote();
							P p = new P();
							p.setContent(trimStringValue(placesDescriptionTextfields.get(i).getTextValue()));
							
							if(placesFunctionJComboBoxes!=null && placesFunctionJComboBoxes.size()>i && placesFunctionJComboBoxes.get(i).getSelectedItem()!=null){
								p.setLang(LanguageIsoList.getIsoCode(placesFunctionJComboBoxes.get(i).getSelectedItem().toString())); //the only lang element available from this part of the form is the same that contains term
							}
							
							descriptiveNote.getP().add(p);
							function.setDescriptiveNote(descriptiveNote);
							write2 = true;
						}
					}
					boolean write3 = false;
					//place textfield 
					if(placeFunctionPlaceJtextfields!=null && placeFunctionPlaceJtextfields.size()>i){
						for(int j=0; j<placeFunctionPlaceJtextfields.get(i).size();j++){
							boolean write3Temp = false;
							JTextField placeFunctionJTextField = placeFunctionPlaceJtextfields.get(i).get(j); 
							PlaceEntry placeEntry = new PlaceEntry();
							String contentFunctionPlace = trimStringValue(placeFunctionJTextField.getText());
							if(contentFunctionPlace!=null && StringUtils.isNotEmpty(contentFunctionPlace)){
								write3 = true;
								write3Temp = true;
								placeEntry.setContent(contentFunctionPlace);
							}
							
							if(placeFunctionPlaceJComboBoxes!=null && placeFunctionPlaceJComboBoxes.size()>i){
								if(placeFunctionPlaceJComboBoxes.get(i).size()>j && placeFunctionPlaceJComboBoxes.get(i).get(j).getSelectedItem()!=null){
									JComboBox countryCodeJComboBox = placeFunctionPlaceJComboBoxes.get(i).get(j);
									String countryCode = countryCodeJComboBox.getSelectedItem().toString();
									if(countryCode!=null && !countryCode.isEmpty() && !countryCode.equals(TextFieldWithComboBoxEacCpf.DEFAULT_VALUE)){
										write3Temp = true;
										write3 = true;
										if(countryCode!=null && !countryCode.isEmpty() && countriesMap.containsKey(countryCode)){
											countryCode = countriesMap.get(countryCode);
										}
										placeEntry.setCountryCode(countryCode);
										
									}
								}
							}
							
							if(placesFunctionJComboBoxes!=null && placesFunctionJComboBoxes.size()>i && placesFunctionJComboBoxes.get(i).getSelectedItem()!=null){
								placeEntry.setLang(LanguageIsoList.getIsoCode(placesFunctionJComboBoxes.get(i).getSelectedItem().toString())); //the only lang element available from this part of the form is the same that contains term
//								write3 = true;
							}
							if(write3Temp){
								function.getPlaceEntry().add(placeEntry);
							}
						}
					}
					
					//date or dateRange part
					boolean write4 = false;
					if(functionsDates!=null && functionsDates.containsKey(i) && functionsDates.get(i)!=null && functionsDates.get(i).size()>0){
						function = (Function)updateSectionDates(function,functionsDates.get(i));
						if(function.getDate()!=null || function.getDateRange()!=null || function.getDateSet()!=null){
							write4 = true; //store place into places node for dates part
							//check if there is an placeEntry to be placed
							if(function.getTerm() == null){
								function.setTerm(term);
							}
						}
					}
					
					if((!save && (write || write2 || write3 || write4 || write5)) || (save && write) ){
						functions.getFunction().add(function);
					}
				}
			}
			if(functions.getFunction().isEmpty()){
				functions = null;
			}
			return functions;
		}

		private Occupations getOccupations(boolean save) {
			Occupations occupations = new Occupations();
			if(ocupationPlaceOcupationJTextfields!=null){
				for(int i=0;i<ocupationPlaceOcupationJTextfields.size();i++){
					Occupation occupation = new Occupation();
					
					Term term = new Term();
					String termContent = trimStringValue(ocupationPlaceOcupationJTextfields.get(i).getText());
					
					boolean write = false;
					
					if(termContent!=null && StringUtils.isNotEmpty(termContent)){
						term.setContent(termContent);
						write = true;
					}
					
					if(ocupationPlaceOcupationLanguagesJComboboxes!=null && ocupationPlaceOcupationLanguagesJComboboxes.size()>i && ocupationPlaceOcupationLanguagesJComboboxes.get(i).getSelectedItem()!=null){
						term.setLang(LanguageIsoList.getIsoCode(ocupationPlaceOcupationLanguagesJComboboxes.get(i).getSelectedItem().toString()));
//						write = true;
					}
					boolean write5 = false;
					if(ocupationPlaceLinkToControlledVocabularyTextFields!=null && ocupationPlaceLinkToControlledVocabularyTextFields.size()>i){
						if (StringUtils.isNotEmpty(ocupationPlaceLinkToControlledVocabularyTextFields.get(i).getText())
								&& StringUtils.isNotEmpty(trimStringValue(ocupationPlaceLinkToControlledVocabularyTextFields.get(i).getText()))) {
							term.setVocabularySource(trimStringValue(ocupationPlaceLinkToControlledVocabularyTextFields.get(i).getText()));
							write5 = true;
						}
					}
					if(write || write5){
						occupation.setTerm(term);
					}
					
					boolean write2 = false;
					if(ocupationPlaceOcupationDescriptionTextFields!=null && ocupationPlaceOcupationDescriptionTextFields.size()>i){
						DescriptiveNote descriptiveNote = new DescriptiveNote();
	                    P p = new P();
	                    if(ocupationPlaceOcupationLanguagesJComboboxes!=null && ocupationPlaceOcupationLanguagesJComboboxes.size()>i && ocupationPlaceOcupationLanguagesJComboboxes.get(i).getSelectedItem()!=null){
							p.setLang(LanguageIsoList.getIsoCode(ocupationPlaceOcupationLanguagesJComboboxes.get(i).getSelectedItem().toString()));
//							write2 = true;
						}
	                    String pContent = trimStringValue(ocupationPlaceOcupationDescriptionTextFields.get(i).getTextValue());
	                    if(pContent!=null && !pContent.isEmpty()){
	                    	if(StringUtils.isNotEmpty(pContent)){
	                    		p.setContent(pContent);
		                    	write2 = true;
	                    	}
	                    	descriptiveNote.getP().add(p);
		                    occupation.setDescriptiveNote(descriptiveNote);
	                    }
					}
					boolean write3 = false;
					if(ocupationPlaceCountryPlaceJComboBoxes!=null && ocupationPlaceCountryPlaceJComboBoxes.size()>i){
						for(int x=0;x<ocupationPlaceCountryPlaceJComboBoxes.get(i).size();x++){
							boolean write3Temp = false;
							
							PlaceEntry placeEntry = new PlaceEntry();
							
							String countryCode = ocupationPlaceCountryPlaceJComboBoxes.get(i).get(x).getSelectedItem().toString();
							if(countryCode!=null && !countryCode.isEmpty() && !countryCode.equals(TextFieldWithComboBoxEacCpf.DEFAULT_VALUE)){
								if(countryCode!=null && !countryCode.isEmpty() && countriesMap.containsKey(countryCode)){
									countryCode = countriesMap.get(countryCode);
								}
								placeEntry.setCountryCode(countryCode);
//								write3 = true;
							}
							
							if(ocupationPlaceOcupationLanguagesJComboboxes!=null && ocupationPlaceOcupationLanguagesJComboboxes.size()>i && ocupationPlaceOcupationLanguagesJComboboxes.get(i).getSelectedItem()!=null){
								placeEntry.setLang(LanguageIsoList.getIsoCode(ocupationPlaceOcupationLanguagesJComboboxes.get(i).getSelectedItem().toString()));
//								write3 = true;
							}
							
							if(ocupationPlacePlaceJTextFields!=null && ocupationPlacePlaceJTextFields.size()>i){
								String placeEntryContent = trimStringValue(ocupationPlacePlaceJTextFields.get(i).get(x).getText());
								if(placeEntryContent!=null && !placeEntryContent.isEmpty()){
									placeEntry.setContent(placeEntryContent);
									write3Temp = true;
									write3 = true;
								}
							}
							if(write3Temp){
								occupation.getPlaceEntry().add(placeEntry);
							}
						}
					}
					
					//date or dateRange part
					boolean write4 = false;
					if(occupationsDates!=null && occupationsDates.containsKey(i) && occupationsDates.get(i)!=null && occupationsDates.get(i).size()>0){
						occupation = (Occupation)updateSectionDates(occupation,occupationsDates.get(i));
						if(occupation.getDate()!=null || occupation.getDateRange()!=null || occupation.getDateSet()!=null){
							write4 = true;
							if(occupation.getTerm()==null){
								occupation.setTerm(term);
							}
						}
					}

					if((!save && (write || write2 || write3 || write4 || write5)) || (save && write)){
						occupations.getOccupation().add(occupation);
					}
				}
			}
			if(occupations.getOccupation().isEmpty()){
				occupations = null;
			}
			return occupations;
		}
		
		private Object updateSectionDates(Object section, List<TextFieldsWithRadioButtonForDates> occupationDatesTfsWCbList) {

			// Save use dates of the name.
			if (occupationDatesTfsWCbList != null
					&& !occupationDatesTfsWCbList.isEmpty()
					&& occupationDatesTfsWCbList.size() == 1
					&& !occupationDatesTfsWCbList.get(0).isDateRange()) {
				// Date.
				Date date = this.fillDateValues(occupationDatesTfsWCbList.get(0));
				if (date != null) {
					if(section instanceof Occupation){
						((Occupation)section).setDate(date);
					}else if(section instanceof Place){
						((Place)section).setDate(date);
					}else if(section instanceof Function){
						((Function)section).setDate(date);
					}
				}
			} else if (occupationDatesTfsWCbList != null
					&& !occupationDatesTfsWCbList.isEmpty()
					&& occupationDatesTfsWCbList.size() == 1
					&& occupationDatesTfsWCbList.get(0).isDateRange()) {
				// DateRange.
				DateRange dateRange = this.fillDateRangeValues(occupationDatesTfsWCbList.get(0));

				if (dateRange != null) {
					if(section instanceof Occupation){
						((Occupation)section).setDateRange(dateRange);
					}else if(section instanceof Place){
						((Place)section).setDateRange(dateRange);
					}else if(section instanceof Function){
						((Function)section).setDateRange(dateRange);
					}
				}
			} else if (occupationDatesTfsWCbList != null
					&& !occupationDatesTfsWCbList.isEmpty()
					&& occupationDatesTfsWCbList.size() > 1) {
				// DateSet.
				DateSet dateSet = new DateSet();

				for (TextFieldsWithRadioButtonForDates tfwcbfDates: occupationDatesTfsWCbList) {
					if (tfwcbfDates.isDateRange()) {
						// DateRange.
						DateRange dateRange = fillDateRangeValues(tfwcbfDates);

						if (dateRange != null) {
							dateSet.getDateOrDateRange().add(dateRange);
						}
					} else {
						// Date.
						Date date = this.fillDateValues(tfwcbfDates);

						if (date != null) {
							dateSet.getDateOrDateRange().add(date);
						}
					}
				}

				if (dateSet.getDateOrDateRange().size() == 1) {
					Object object = dateSet.getDateOrDateRange().get(0);

					// Date.
					if (object instanceof Date) {
						Date date = (Date) object;
						if (StringUtils.isNotEmpty(trimStringValue(date.getContent()))) {
							if(section instanceof Occupation){
								((Occupation)section).setDate(date);
							}else if(section instanceof Place){
								((Place)section).setDate(date);
							}else if(section instanceof Function){
								((Function)section).setDate(date);
							}
						}
					}

					// DateRange.
					if (object instanceof DateRange) {
						DateRange dateRange = (DateRange) object;
						if (dateRange.getFromDate() != null
								&& StringUtils.isNotEmpty(trimStringValue(dateRange.getFromDate().getContent()))
								&& dateRange.getToDate() != null
								&& StringUtils.isNotEmpty(trimStringValue(dateRange.getToDate().getContent()))) {
							if(section instanceof Occupation){
								((Occupation)section).setDateRange(dateRange);
							}else if(section instanceof Place){
								((Place)section).setDateRange(dateRange);
							}else if(section instanceof Function){
								((Function)section).setDateRange(dateRange);
							}
						}
					}
				} else {
					if(section instanceof Occupation){
						((Occupation)section).setDateSet(dateSet);
					}else if(section instanceof Place){
						((Place)section).setDateSet(dateSet);
					}else if(section instanceof Function){
						((Function)section).setDateSet(dateSet);
					}
				}
			}
			
			return section;
		}
		
		/**
		 * Method to recover the date values from the TextFieldsWithCheckBoxForDates
		 *
		 * @param tfwcbfDates
		 * @return the Date.
		 */
		private Date fillDateValues(TextFieldsWithRadioButtonForDates tfwcbfDates) {
			Date date = null;
			if (StringUtils.isNotEmpty(trimStringValue(tfwcbfDates.getDateValue()))) {
				date = new Date();
				date.setContent(trimStringValue(tfwcbfDates.getDateValue()));
				if (StringUtils.isNotEmpty(parseStandardDate(trimStringValue(tfwcbfDates.getStandardDateValue())))) {
					date.setStandardDate(parseStandardDate(trimStringValue(tfwcbfDates.getStandardDateValue())));
				}
			} else if (tfwcbfDates.isSelectedDateUndefinedRB()) {
				date = new Date();
				date.setContent(EacCpfIdentityPanel.UNKNOWN);
			} else if (tfwcbfDates.isSelectedDateStillRB()) {
				date = new Date();
				date.setContent("open");
			}

			if(date != null && tfwcbfDates.isSelectedDateUndefinedRB()){
				date.setLocalType(EacCpfIdentityPanel.UNKNOWN);
			}else if(date != null && tfwcbfDates.isSelectedDateStillRB()){
				date.setLocalType("open");
			}
			return date;
		}

		/**
		 * Method to recover the dateRange values from the TextFieldsWithCheckBoxForDates
		 *
		 * @param tfwcbfDates
		 * @return the tfwcbfDateRanges.
		 */
		private DateRange fillDateRangeValues(TextFieldsWithRadioButtonForDates tfwcbfDateRanges) {
			DateRange dateRange = null;
			FromDate fromDate = null;
			ToDate toDate = null;

			// From date.
			if (StringUtils.isNotEmpty(trimStringValue(tfwcbfDateRanges.getDateFromValue()))) {
				fromDate = new FromDate();
				fromDate.setContent(trimStringValue(tfwcbfDateRanges.getDateFromValue()));

				if (StringUtils.isNotEmpty(parseStandardDate(trimStringValue(tfwcbfDateRanges.getStandardDateFromValue())))) {
					fromDate.setStandardDate(parseStandardDate(trimStringValue(tfwcbfDateRanges.getStandardDateFromValue())));
				}
			} else if (tfwcbfDateRanges.isSelectedDateFromUndefinedRB()) {
				fromDate = new FromDate();
				fromDate.setContent(EacCpfIdentityPanel.UNKNOWN);
			}

			// To date.
			if (StringUtils.isNotEmpty(trimStringValue(tfwcbfDateRanges.getDateToValue()))) {
				toDate = new ToDate();
				toDate.setContent(trimStringValue(tfwcbfDateRanges.getDateToValue()));

				if (StringUtils.isNotEmpty(parseStandardDate(trimStringValue(tfwcbfDateRanges.getStandardDateToValue())))) {
					toDate.setStandardDate(parseStandardDate(trimStringValue(tfwcbfDateRanges.getStandardDateToValue())));
				}
			} else if (tfwcbfDateRanges.isSelectedDateToUndefinedRB()) {
				toDate = new ToDate();
				toDate.setContent(EacCpfIdentityPanel.UNKNOWN);
			} else if (tfwcbfDateRanges.isSelectedDateToStillRB()) {
				toDate = new ToDate();
				toDate.setContent("open");
			}

			// Date range.
			if (fromDate != null || toDate != null) {
				dateRange = new DateRange();
				dateRange.setFromDate(fromDate);
				dateRange.setToDate(toDate);
				
				if(tfwcbfDateRanges.isSelectedDateFromUndefinedRB() && tfwcbfDateRanges.isSelectedDateToUndefinedRB()){
					dateRange.setLocalType(EacCpfIdentityPanel.UNKNOWN);
				}else if(tfwcbfDateRanges.isSelectedDateFromUndefinedRB() && tfwcbfDateRanges.isSelectedDateToDefinedRB()){
					dateRange.setLocalType(EacCpfIdentityPanel.UNKNOWN_INITIAL_DATE);
				}else if(tfwcbfDateRanges.isSelectedDateFromDefinedRB() && tfwcbfDateRanges.isSelectedDateToUndefinedRB()){
					dateRange.setLocalType(EacCpfIdentityPanel.UNKNOWN_END_DATE);
				}else if(tfwcbfDateRanges.isSelectedDateToStillRB()){
					dateRange.setLocalType("open");
				}
			}

			return dateRange;
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
					super.updateJAXBObject(false);
					removeChangeListener();
					if(areRightDates()){
						switch (selectedIndex) {
							case 0:
								String mainagencycode = eaccpf.getControl().getMaintenanceAgency().getAgencyCode().getValue();
								reloadTabbedPanel(new EacCpfIdentityPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, false, labels,entityType,firstLanguage,firstScript,mainagencycode).buildEditorPanel(errors), 0);
								break;
							case 2:
								reloadTabbedPanel(new EacCpfRelationsPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 2);
								break;
							case 3:
								reloadTabbedPanel(new EacCpfControlPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 3);
								break;
							default:
								reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
						}
					}else{
						reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
					}
				} catch (EacCpfFormException ex) {
					reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
				}
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			// Empty.
		}
	}
	
	private boolean areRightDates() {
		boolean error = false;
		boolean emptyDate = false;
		if(placesDates!=null && !placesDates.isEmpty()){
			Iterator<Integer> keySetIt = placesDates.keySet().iterator();
			while(!error && keySetIt.hasNext()){
				List<TextFieldsWithRadioButtonForDates> useDatesTfsWCbList = placesDates.get(keySetIt.next()); 
				error = !isRightDate(useDatesTfsWCbList);
				emptyDate = isEmptyDate(useDatesTfsWCbList);
			}
		}
		if(!error && (functionsDates!=null && !functionsDates.isEmpty()) ){
			Iterator<Integer> keySetIt = functionsDates.keySet().iterator();
			while(!error && keySetIt.hasNext()){
				List<TextFieldsWithRadioButtonForDates> useDatesTfsWCbList = functionsDates.get(keySetIt.next()); 
				error = !isRightDate(useDatesTfsWCbList);
				emptyDate = isEmptyDate(useDatesTfsWCbList);
			}
		}
		if(!error && (occupationsDates!=null && !occupationsDates.isEmpty()) ){
			Iterator<Integer> keySetIt = occupationsDates.keySet().iterator();
			while(!error && keySetIt.hasNext()){
				List<TextFieldsWithRadioButtonForDates> useDatesTfsWCbList = occupationsDates.get(keySetIt.next()); 
				error = !isRightDate(useDatesTfsWCbList);
				emptyDate = isEmptyDate(useDatesTfsWCbList);
			}
		}
		if(!error && (resourcesRelationDate!=null && !resourcesRelationDate.isEmpty())){
			error = !isRightDate(resourcesRelationDate);
			emptyDate = isEmptyDate(resourcesRelationDate);
		}
		if(error && emptyDate){
			checkEmpty(error,true);
		}else{
			checkEmpty(error,false);
		}
		return !error;
	}


	private void checkEmpty(boolean error,boolean fail) {
		if(!error && (placesDates!=null && !placesDates.isEmpty())){
			Iterator<Integer> keySetIt = placesDates.keySet().iterator();
			while(!error && keySetIt.hasNext()){
				List<TextFieldsWithRadioButtonForDates> useDatesTfsWCbList = placesDates.get(keySetIt.next()); 
				error = !isStandardDate(useDatesTfsWCbList);
			}
		}
		if(!error && (functionsDates!=null && !functionsDates.isEmpty()) ){
			Iterator<Integer> keySetIt = functionsDates.keySet().iterator();
			while(!error && keySetIt.hasNext()){
				List<TextFieldsWithRadioButtonForDates> useDatesTfsWCbList = functionsDates.get(keySetIt.next()); 
				error = !isStandardDate(useDatesTfsWCbList);
			}
		}
		if(!error && (occupationsDates!=null && !occupationsDates.isEmpty()) ){
			Iterator<Integer> keySetIt = occupationsDates.keySet().iterator();
			while(!error && keySetIt.hasNext()){
				List<TextFieldsWithRadioButtonForDates> useDatesTfsWCbList = occupationsDates.get(keySetIt.next()); 
				error = !isStandardDate(useDatesTfsWCbList);
			}
		}
		if(error){
			if(!globalIsActivated){
				globalIsActivated = true;
				JOptionPane.showMessageDialog(tabbedPane, labels.getString("eaccpf.commons.error.no.standard.date"));
				globalIsActivated = false;
			}
		}else if(fail){
			globalIsActivated = true;
			JOptionPane.showMessageDialog(this.tabbedPane, labels.getString("eaccpf.commons.error.empty.date"));
			globalIsActivated = false;
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
	        		if(checkStartTabFields()){
	        			super.saveFile(eaccpf.getControl().getRecordId().getValue());
	        			closeFrame();
	        		}
	        	}else if(event == JOptionPane.NO_OPTION){	
	        		EacCpfFrame.inUse(false);
	                closeFrame();
	        	}
			} catch (EacCpfFormException e) {
				reloadTabbedPanel(new EacCpfDescriptionPanel(eaccpf, tabbedPane, mainTabbedPane, eacCpfFrame, model, labels,entityType,firstLanguage,firstScript).buildEditorPanel(errors), 1);
			}
        }
    }
}