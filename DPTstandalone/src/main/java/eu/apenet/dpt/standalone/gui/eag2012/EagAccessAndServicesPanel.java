package eu.apenet.dpt.standalone.gui.eag2012;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import eu.apenet.dpt.standalone.gui.ProfileListModel;
import eu.apenet.dpt.standalone.gui.Utilities;
import static eu.apenet.dpt.standalone.gui.eag2012.EagPanels.LOG;
import eu.apenet.dpt.standalone.gui.eag2012.data.*;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * User: Yoann Moranville
 * Date: 28/11/2012
 *
 * @author Yoann Moranville
 */
public class EagAccessAndServicesPanel extends EagPanels {
    private List<TextFieldWithLanguage> openingHoursTfs;
    private List<TextFieldWithLanguage> closingDatesTfs;
    private List<TextFieldWithLanguage> travellingDirectionsTfs;
    private List<TextFieldWithLanguage> restaccessTfs;
    private List<TextFieldWithLanguage> termsOfUseTfs;
    private List<TextFieldWithLanguage> accessibilityTfs;
    private JTextField telephoneSearchroomTf;
    private JTextField emailSearchroomTf;
    private JTextField emailTitleSearchroomTf;
    private JTextField webpageSearchroomTf;
    private JTextField webpageTitleSearchroomTf;
    private JTextField workplacesSearchroomTf;
    private JTextField computerplacesSearchroomTf;
    private List<TextFieldWithLanguage> computerplacesDescriptionTfs;
    private JTextField microfilmplacesSearchroomTf;
    private List<TextFieldWithLanguage> readersticketSearchroomTfs;
    private List<TextFieldWithLanguage> advancedordersSearchroomTfs;
    private List<TextFieldWithLanguage> researchServicesSearchroomTfs;
    private JTextField telephoneLibraryTf;
    private JTextField emailLibraryTf;
    private JTextField emailTitleLibraryTf;
    private JTextField webpageLibraryTf;
    private JTextField webpageTitleLibraryTf;
    private JTextField monographicPubLibraryTf;
    private JTextField serialPubLibraryTf;
    private List<TextFieldWithLanguage> internetAccessDescTfs;
    private JTextField telephoneRestorationlabTf;
    private JTextField emailRestorationlabTf;
    private JTextField emailTitleRestorationlabTf;
    private JTextField webpageRestorationlabTf;
    private JTextField webpageTitleRestorationlabTf;
    private List<TextFieldWithLanguage> descriptionRestorationServiceTfs;
    private JTextField telephoneReproductionServiceTf;
    private JTextField emailReproductionServiceTf;
    private JTextField emailTitleReproductionServiceTf;
    private JTextField webpageReproductionServiceTf;
    private JTextField webpageTitleReproductionServiceTf;
    private List<TextFieldWithLanguage> descriptionReproductionServiceTfs;
    private JComboBox microformServicesCombo = new JComboBox(yesOrNo);
    private JComboBox photographServicesCombo = new JComboBox(yesOrNo);
    private JComboBox digitalServicesCombo = new JComboBox(yesOrNo);
    private JComboBox photocopyServicesCombo = new JComboBox(yesOrNo);
    private TextFieldWithLanguage refreshmentTf;

    private List<TextFieldWithLanguage> exhibitionTfs;
    private List<TextFieldWithLanguage> toursAndSessionsTfs;
    private List<TextFieldWithLanguage> otherServicesTfs;

    public EagAccessAndServicesPanel(Eag eag, JTabbedPane tabbedPane, JFrame eag2012Frame, ProfileListModel model, ResourceBundle labels) {
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

        Repository repository = eag.getArchguide().getDesc().getRepositories().getRepository().get(0);

        if(repository.getTimetable().getOpening().size() == 0) {
            repository.getTimetable().getOpening().add(new Opening());
        }
        openingHoursTfs = new ArrayList<TextFieldWithLanguage>(repository.getTimetable().getOpening().size());
        for(Opening opening : repository.getTimetable().getOpening()) {
            builder.addLabel(labels.getString("eag2012.openingHoursLabel") + "*",    cc.xy (1, rowNb));
            TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(opening.getContent(), opening.getLang());
            openingHoursTfs.add(textFieldWithLanguage);
            builder.add(textFieldWithLanguage.getTextField(), cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"),    cc.xy (5, rowNb));
            builder.add(textFieldWithLanguage.getLanguageBox(), cc.xy(7, rowNb));
            setNextRow();
        }
        if(errors.contains("openingHoursTfs")) {
            builder.add(createErrorLabel(labels.getString("eag2012.errors.openingHours")),          cc.xy (1, rowNb));
            setNextRow();
        }

        if(repository.getTimetable().getClosing().size() == 0) {
            repository.getTimetable().getClosing().add(new Closing());
        }
        closingDatesTfs = new ArrayList<TextFieldWithLanguage>(repository.getTimetable().getClosing().size());
        for(Closing closing : repository.getTimetable().getClosing()) {
            builder.addLabel(labels.getString("eag2012.closingDatesLabel"), cc.xy(1, rowNb));
            TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(closing.getContent(), closing.getLang());
            closingDatesTfs.add(textFieldWithLanguage);
            builder.add(textFieldWithLanguage.getTextField(), cc.xy(3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"),    cc.xy (5, rowNb));
            builder.add(textFieldWithLanguage.getLanguageBox(), cc.xy(7, rowNb));
            setNextRow();
        }

        if(repository.getDirections().size() == 0)
            repository.getDirections().add(new Directions());
        travellingDirectionsTfs = new ArrayList<TextFieldWithLanguage>(repository.getDirections().size());
        for(Directions directions : repository.getDirections()) {
            builder.addLabel(labels.getString("eag2012.travellingDirections"),    cc.xy (1, rowNb));
            String str = "";
            String citation = "";
            for(Object obj : directions.getContent()) {
                if(obj instanceof String) {
                    str += (String)obj;
                } else if(obj instanceof Citation) {
                    citation += ((Citation) obj).getHref();
                }
            }
            TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(str, directions.getLang(), citation);
            travellingDirectionsTfs.add(textFieldWithLanguage);
            builder.add(textFieldWithLanguage.getTextField(),                     cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
            builder.add(textFieldWithLanguage.getLanguageBox(), cc.xy(7, rowNb));
            setNextRow();

            builder.addLabel(labels.getString("eag2012.linkLabel"), cc.xy(1, rowNb));
            builder.add(textFieldWithLanguage.getExtraField(),                                            cc.xy (3, rowNb));
            setNextRow();
        }

        JButton addTravellingDirectionsBtn = new ButtonEag(labels.getString("eag2012.addTravellingDirectionsButton"));
        builder.add(addTravellingDirectionsBtn, cc.xy (1, rowNb));
        addTravellingDirectionsBtn.addActionListener(new AddTravellingDirectionsBtnAction(eag, tabbedPane, model));
        setNextRow();

        builder.addLabel(labels.getString("eag2012.accessiblePublicLabel") + "*",    cc.xy (1, rowNb));
        if(Arrays.asList(yesOrNo).contains(repository.getAccess().getQuestion())) {
            accessiblePublicCombo.setSelectedItem(repository.getAccess().getQuestion());
        }
        builder.add(accessiblePublicCombo, cc.xy(3, rowNb));
        setNextRow();

        if(repository.getAccess().getRestaccess().size() == 0)
            repository.getAccess().getRestaccess().add(new Restaccess());
        restaccessTfs = new ArrayList<TextFieldWithLanguage>(repository.getAccess().getRestaccess().size());
        int last = repository.getAccess().getRestaccess().size() - 1;
        for(Restaccess restaccess : repository.getAccess().getRestaccess()) {
            builder.addLabel(labels.getString("eag2012.accessRestrictions"),    cc.xy (1, rowNb));
            TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(restaccess.getContent(), restaccess.getLang());
            restaccessTfs.add(textFieldWithLanguage);
            builder.add(textFieldWithLanguage.getTextField(),                     cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
            builder.add(textFieldWithLanguage.getLanguageBox(),                                            cc.xy (7, rowNb));
            setNextRow();
            if(last-- == 0) {
                JButton addRestaccessBtn = new ButtonEag(labels.getString("eag2012.addRestaccessButton"));
                builder.add(addRestaccessBtn, cc.xy (1, rowNb));
                addRestaccessBtn.addActionListener(new AddRestaccessBtnAction(eag, tabbedPane, model));
                setNextRow();
            }
        }


        if(repository.getAccess().getTermsOfUse().size() == 0)
            repository.getAccess().getTermsOfUse().add(new TermsOfUse());
        termsOfUseTfs = new ArrayList<TextFieldWithLanguage>(repository.getAccess().getTermsOfUse().size());
        for(TermsOfUse termsOfUse : repository.getAccess().getTermsOfUse()) {
            builder.addLabel(labels.getString("eag2012.termsOfUse"),    cc.xy (1, rowNb));
            TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(termsOfUse.getContent(), termsOfUse.getLang(), termsOfUse.getHref());
            termsOfUseTfs.add(textFieldWithLanguage);
            builder.add(textFieldWithLanguage.getTextField(),                     cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
            builder.add(textFieldWithLanguage.getLanguageBox(),                   cc.xy(7, rowNb));
            setNextRow();

            builder.addLabel(labels.getString("eag2012.linkLabel"), cc.xy(1, rowNb));
            builder.add(textFieldWithLanguage.getExtraField(),                                            cc.xy (3, rowNb));
            setNextRow();
        }

        builder.addLabel(labels.getString("eag2012.disabledAccessLabel") + "*", cc.xy(1, rowNb));
        if(repository.getAccessibility().size() > 0 && Arrays.asList(yesOrNo).contains(repository.getAccessibility().get(0).getQuestion())) {
            facilitiesForDisabledCombo.setSelectedItem(repository.getAccessibility().get(0).getQuestion());
        }
        builder.add(facilitiesForDisabledCombo, cc.xy(3, rowNb));
        setNextRow();

//        if(repository.getAccessibility().size() == 0){
//            LOG.info("create new accessibility");
//            repository.getAccessibility().add(new Accessibility());
//        }
        accessibilityTfs = new ArrayList<TextFieldWithLanguage>(repository.getAccessibility().size());
        for(Accessibility accessibility : repository.getAccessibility()) {
            builder.addLabel(labels.getString("eag2012.facilitiesForDisabledPersons"),    cc.xy (1, rowNb));
            TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(accessibility.getContent(), accessibility.getLang());
            accessibilityTfs.add(textFieldWithLanguage);
            builder.add(textFieldWithLanguage.getTextField(),                     cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
            builder.add(textFieldWithLanguage.getLanguageBox(),                                            cc.xy (7, rowNb));
            if(last-- == 0) {
                JButton addAccessibilityBtn = new ButtonEag(labels.getString("eag2012.addFacilityButton"));
                builder.add(addAccessibilityBtn, cc.xy (7, rowNb));
                addAccessibilityBtn.addActionListener(new AddAccessibilityBtnAction(eag, tabbedPane, model));
            }
            setNextRow();
        }

        builder.addSeparator(labels.getString("eag2012.searchroom"), cc.xyw(1, rowNb, 7));
        setNextRow();
        if(repository.getServices() == null)
            repository.setServices(new Services());
        if(repository.getServices().getSearchroom() == null)
            repository.getServices().setSearchroom(new Searchroom());
        Searchroom searchroom = repository.getServices().getSearchroom();

        if(searchroom.getContact() == null)
            searchroom.setContact(new Contact());

        if(searchroom.getContact().getTelephone().size() == 0) {
            telephoneSearchroomTf = new JTextField();
        } else {
            telephoneSearchroomTf = new JTextField(searchroom.getContact().getTelephone().get(0).getContent());
        }
        builder.addLabel(labels.getString("eag2012.telephoneLabel"),    cc.xy (1, rowNb));
        builder.add(telephoneSearchroomTf, cc.xy(3, rowNb));
        setNextRow();

        if(searchroom.getContact().getEmail().size() == 0)
            searchroom.getContact().getEmail().add(new Email());
        builder.addLabel(labels.getString("eag2012.emailLabel"),    cc.xy (1, rowNb));
        emailSearchroomTf = new JTextField(searchroom.getContact().getEmail().get(0).getHref());
        builder.add(emailSearchroomTf,    cc.xy (3, rowNb));
        builder.addLabel(labels.getString("eag2012.linkTitleLabel"),    cc.xy (5, rowNb));
        emailTitleSearchroomTf = new JTextField(searchroom.getContact().getEmail().get(0).getContent());
        builder.add(emailTitleSearchroomTf,    cc.xy (7, rowNb));
        setNextRow();

        if(searchroom.getWebpage().size() == 0)
            searchroom.getWebpage().add(new Webpage());
        builder.addLabel(labels.getString("eag2012.webpageLabel"),    cc.xy (1, rowNb));
        webpageSearchroomTf = new JTextField(searchroom.getWebpage().get(0).getHref());
        builder.add(webpageSearchroomTf,    cc.xy (3, rowNb));
        builder.addLabel(labels.getString("eag2012.linkTitleLabel"),    cc.xy (5, rowNb));
        webpageTitleSearchroomTf = new JTextField(searchroom.getWebpage().get(0).getContent());
        builder.add(webpageTitleSearchroomTf,    cc.xy (7, rowNb));
        setNextRow();

        if(searchroom.getWorkPlaces() == null)
            searchroom.setWorkPlaces(new WorkPlaces());
        builder.addLabel(labels.getString("eag2012.workplaces") + "*",    cc.xy (1, rowNb));
        try {
            workplacesSearchroomTf = new JTextField(searchroom.getWorkPlaces().getNum().getContent());
        } catch (NullPointerException npe) {
            workplacesSearchroomTf = new JTextField();
        }
        builder.add(workplacesSearchroomTf,    cc.xy (3, rowNb));
        if(errors.contains("workplacesSearchroomTf")) {
            setNextRow();
            builder.add(createErrorLabel(labels.getString("eag2012.errors.workplaces")),          cc.xy (1, rowNb));
        }
        setNextRow();

        if(searchroom.getComputerPlaces() == null) {
            ComputerPlaces computerPlaces = new ComputerPlaces();
            Num num = new Num();
            num.setUnit("site");
            computerPlaces.setNum(num);
            searchroom.setComputerPlaces(computerPlaces);
        }
        builder.addLabel(labels.getString("eag2012.computerplaces"),    cc.xy (1, rowNb));
        computerplacesSearchroomTf = new JTextField(searchroom.getComputerPlaces().getNum().getContent());
        builder.add(computerplacesSearchroomTf,    cc.xy (3, rowNb));
        if(searchroom.getComputerPlaces().getDescriptiveNote() == null){
            JButton addDescriptionBtn = new ButtonEag(labels.getString("eag2012.addDescriptionButton"));
            builder.add(addDescriptionBtn, cc.xy (5, rowNb));
            addDescriptionBtn.addActionListener(new AddComputerplacesDescriptionBtnAction(eag, tabbedPane, model));
        }
        setNextRow();
        if(searchroom.getComputerPlaces().getDescriptiveNote() != null){
            computerplacesDescriptionTfs = new ArrayList<TextFieldWithLanguage>(searchroom.getComputerPlaces().getDescriptiveNote().getP().size());
            for (P p : searchroom.getComputerPlaces().getDescriptiveNote().getP()) {
                builder.addLabel(labels.getString("eag2012.computerplacesDescription"), cc.xy(1, rowNb));
                TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(p.getContent(), p.getLang());
                computerplacesDescriptionTfs.add(textFieldWithLanguage);
                builder.add(textFieldWithLanguage.getTextField(), cc.xy(3, rowNb));
                builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
                builder.add(textFieldWithLanguage.getLanguageBox(), cc.xy(7, rowNb));
                setNextRow();
            }
            JButton addDescriptionBtn = new ButtonEag(labels.getString("eag2012.addDescriptionButton"));
            builder.add(addDescriptionBtn, cc.xy (5, rowNb));
            addDescriptionBtn.addActionListener(new AddComputerplacesDescriptionBtnAction(eag, tabbedPane, model));
            setNextRow();
        }

        if(searchroom.getMicrofilmPlaces() == null) {
            MicrofilmPlaces microfilmPlaces = new MicrofilmPlaces();
            Num num = new Num();
            num.setUnit("site");
            microfilmPlaces.setNum(num);
            searchroom.setMicrofilmPlaces(microfilmPlaces);
        }
        if(searchroom.getPhotographAllowance() == null) {
            searchroom.setPhotographAllowance(new PhotographAllowance());
        }
        builder.addLabel(labels.getString("eag2012.microfilmplaces"),    cc.xy (1, rowNb));
        microfilmplacesSearchroomTf = new JTextField(searchroom.getMicrofilmPlaces().getNum().getContent());
        builder.add(microfilmplacesSearchroomTf,    cc.xy (3, rowNb));
        builder.addLabel(labels.getString("eag2012.photographAllowance"),    cc.xy (5, rowNb));
        if(Arrays.asList(photographAllowance).contains(searchroom.getPhotographAllowance().getValue())) {
            photographAllowanceCombo.setSelectedItem(searchroom.getPhotographAllowance().getValue());
        }
        builder.add(photographAllowanceCombo, cc.xy(7, rowNb));
        setNextRow();

        if(searchroom.getReadersTicket().size() == 0)
            searchroom.getReadersTicket().add(new ReadersTicket());
        readersticketSearchroomTfs = new ArrayList<TextFieldWithLanguage>(searchroom.getReadersTicket().size());
        for(ReadersTicket readersTicket : searchroom.getReadersTicket()) {
            builder.addLabel(labels.getString("eag2012.readersTicket"),    cc.xy (1, rowNb));
            TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(readersTicket.getContent(), readersTicket.getLang(), readersTicket.getHref());
            readersticketSearchroomTfs.add(textFieldWithLanguage);
            builder.add(textFieldWithLanguage.getTextField(),                     cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
            builder.add(textFieldWithLanguage.getLanguageBox(), cc.xy(7, rowNb));
            setNextRow();

            builder.addLabel(labels.getString("eag2012.linkLabel"), cc.xy(1, rowNb));
            builder.add(textFieldWithLanguage.getExtraField(),                                            cc.xy (3, rowNb));
            setNextRow();
        }
        JButton addReadersticketBtn = new ButtonEag(labels.getString("eag2012.addReadersticketButton"));
        builder.add(addReadersticketBtn, cc.xy (1, rowNb));
        addReadersticketBtn.addActionListener(new AddReadersticketBtnAction(eag, tabbedPane, model));
        setNextRow();

        if(searchroom.getAdvancedOrders().size() == 0)
            searchroom.getAdvancedOrders().add(new AdvancedOrders());
        advancedordersSearchroomTfs = new ArrayList<TextFieldWithLanguage>(searchroom.getAdvancedOrders().size());
        for(AdvancedOrders advancedOrders : searchroom.getAdvancedOrders()) {
            builder.addLabel(labels.getString("eag2012.advancedOrders"),    cc.xy (1, rowNb));
            TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(advancedOrders.getContent(), advancedOrders.getLang(), advancedOrders.getHref());
            advancedordersSearchroomTfs.add(textFieldWithLanguage);
            builder.add(textFieldWithLanguage.getTextField(),                     cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
            builder.add(textFieldWithLanguage.getLanguageBox(), cc.xy(7, rowNb));
            setNextRow();

            builder.addLabel(labels.getString("eag2012.linkLabel"), cc.xy(1, rowNb));
            builder.add(textFieldWithLanguage.getExtraField(),                                            cc.xy (3, rowNb));
            setNextRow();
        }
        JButton addAdvancedordersBtn = new ButtonEag(labels.getString("eag2012.addAdvancedordersButton"));
        builder.add(addAdvancedordersBtn, cc.xy (1, rowNb));
        addAdvancedordersBtn.addActionListener(new AddAdvancedordersBtnAction(eag, tabbedPane, model));
        setNextRow();

        if(searchroom.getResearchServices().size() == 0) {
            DescriptiveNote descriptiveNote = new DescriptiveNote();
            descriptiveNote.getP().add(new P());
            ResearchServices researchServices = new ResearchServices();
            researchServices.setDescriptiveNote(descriptiveNote);
            searchroom.getResearchServices().add(researchServices);
        }
        researchServicesSearchroomTfs = new ArrayList<TextFieldWithLanguage>(searchroom.getResearchServices().size());
        for(ResearchServices researchServices : searchroom.getResearchServices()) {
            if(researchServices.getDescriptiveNote() == null) {
                DescriptiveNote descriptiveNote = new DescriptiveNote();
                descriptiveNote.setP(new ArrayList<P>(){{ add(new P()); }});
                researchServices.setDescriptiveNote(descriptiveNote);
            }
            builder.addLabel(labels.getString("eag2012.researchServices"),    cc.xy (1, rowNb));
            TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(researchServices.getDescriptiveNote().getP().get(0).getContent(), researchServices.getDescriptiveNote().getP().get(0).getLang());
            researchServicesSearchroomTfs.add(textFieldWithLanguage);
            builder.add(textFieldWithLanguage.getTextField(),                     cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
            builder.add(textFieldWithLanguage.getLanguageBox(),                     cc.xy (7, rowNb));
            setNextRow();
        }
        JButton addResearchservicesBtn = new ButtonEag(labels.getString("eag2012.addResearchservicesButton"));
        builder.add(addResearchservicesBtn, cc.xy (1, rowNb));
        addResearchservicesBtn.addActionListener(new AddResearchservicesBtnAction(eag, tabbedPane, model));
        setNextRow();


        builder.addSeparator(labels.getString("eag2012.library"), cc.xyw(1, rowNb, 7));
        setNextRow();

        if(repository.getServices().getLibrary() == null)
            repository.getServices().setLibrary(new Library());
        Library library = repository.getServices().getLibrary();

        if(library.getContact() == null)
            library.setContact(new Contact());

        if(library.getContact().getTelephone().size() == 0) {
            telephoneLibraryTf = new JTextField();
        } else {
            telephoneLibraryTf = new JTextField(library.getContact().getTelephone().get(0).getContent());
        }
        builder.addLabel(labels.getString("eag2012.telephoneLabel"),    cc.xy (1, rowNb));
        builder.add(telephoneLibraryTf, cc.xy(3, rowNb));
        setNextRow();

        if(library.getContact().getEmail().size() == 0)
            library.getContact().getEmail().add(new Email());
        builder.addLabel(labels.getString("eag2012.emailLabel"),    cc.xy (1, rowNb));
        emailLibraryTf = new JTextField(library.getContact().getEmail().get(0).getHref());
        builder.add(emailLibraryTf,    cc.xy (3, rowNb));
        builder.addLabel(labels.getString("eag2012.linkTitleLabel"),    cc.xy (5, rowNb));
        emailTitleLibraryTf = new JTextField(library.getContact().getEmail().get(0).getContent());
        builder.add(emailTitleLibraryTf,    cc.xy (7, rowNb));
        setNextRow();

        if(library.getWebpage().size() == 0)
            library.getWebpage().add(new Webpage());
        builder.addLabel(labels.getString("eag2012.webpageLabel"),    cc.xy (1, rowNb));
        webpageLibraryTf = new JTextField(library.getWebpage().get(0).getHref());
        builder.add(webpageLibraryTf,    cc.xy (3, rowNb));
        builder.addLabel(labels.getString("eag2012.linkTitleLabel"),    cc.xy (5, rowNb));
        webpageTitleLibraryTf = new JTextField(library.getWebpage().get(0).getContent());
        builder.add(webpageTitleLibraryTf,    cc.xy (7, rowNb));
        setNextRow();

        if(library.getMonographicpub() == null) {
            Monographicpub monographicpub = new Monographicpub();
            Num num = new Num();
            num.setUnit("site");
            monographicpub.setNum(num);
            library.setMonographicpub(monographicpub);
        }
        if(library.getSerialpub() == null) {
            Serialpub serialpub = new Serialpub();
            Num num = new Num();
            num.setUnit("site");
            serialpub.setNum(num);
            library.setSerialpub(serialpub);
        }
        builder.addLabel(labels.getString("eag2012.monographicPub"),    cc.xy (1, rowNb));
        monographicPubLibraryTf = new JTextField(library.getMonographicpub().getNum().getContent());
        builder.add(monographicPubLibraryTf,    cc.xy (3, rowNb));
        builder.addLabel(labels.getString("eag2012.serialPub"),    cc.xy (5, rowNb));
        serialPubLibraryTf = new JTextField(library.getSerialpub().getNum().getContent());
        builder.add(serialPubLibraryTf,    cc.xy (7, rowNb));
        setNextRow();



        builder.addSeparator(labels.getString("eag2012.internetAccess"), cc.xyw(1, rowNb, 7));
        setNextRow();

        if(repository.getServices().getInternetAccess() == null) {
            DescriptiveNote descriptiveNote = new DescriptiveNote();
            descriptiveNote.getP().add(new P());
            InternetAccess internetAccess = new InternetAccess();
            internetAccess.setDescriptiveNote(descriptiveNote);
            repository.getServices().setInternetAccess(internetAccess);
        }
        InternetAccess internetAccess = repository.getServices().getInternetAccess();
        internetAccessDescTfs = new ArrayList<TextFieldWithLanguage>(internetAccess.getDescriptiveNote().getP().size());
        for(P p : internetAccess.getDescriptiveNote().getP()) {
            TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(p.getContent(), p.getLang());
            internetAccessDescTfs.add(textFieldWithLanguage);
            builder.addLabel(labels.getString("eag2012.descriptionLabel"),    cc.xy (1, rowNb));
            builder.add(textFieldWithLanguage.getTextField(), cc.xy(3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
            builder.add(textFieldWithLanguage.getLanguageBox(), cc.xy(7, rowNb));
            setNextRow();
        }
        JButton addInternetAccessBtn = new ButtonEag(labels.getString("eag2012.addInternetAccessButton"));
        builder.add(addInternetAccessBtn, cc.xy (1, rowNb));
        addInternetAccessBtn.addActionListener(new AddInternetAccessBtnAction(eag, tabbedPane, model));
        setNextRow();

        builder.addSeparator(labels.getString("eag2012.technicalServices"), cc.xyw(1, rowNb, 7));
        setNextRow();
        builder.addSeparator(labels.getString("eag2012.conservationLab"), cc.xyw(1, rowNb, 7));
        setNextRow();

        if(repository.getServices().getTechservices() == null)
            repository.getServices().setTechservices(new Techservices());
        if(repository.getServices().getTechservices().getRestorationlab() == null)
            repository.getServices().getTechservices().setRestorationlab(new Restorationlab());
        Restorationlab restorationlab = repository.getServices().getTechservices().getRestorationlab();


        if(restorationlab.getDescriptiveNote() == null)
            restorationlab.setDescriptiveNote(new DescriptiveNote());
        if(restorationlab.getDescriptiveNote().getP().size() == 0)
            restorationlab.getDescriptiveNote().getP().add(new P());

        descriptionRestorationServiceTfs = new ArrayList<TextFieldWithLanguage>(restorationlab.getDescriptiveNote().getP().size());
        for(P p : restorationlab.getDescriptiveNote().getP()) {
            TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(p.getContent(), p.getLang());
            descriptionRestorationServiceTfs.add(textFieldWithLanguage);
            builder.addLabel(labels.getString("eag2012.descriptionLabel"),    cc.xy (1, rowNb));
            builder.add(textFieldWithLanguage.getTextField(), cc.xy(3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
            builder.add(textFieldWithLanguage.getLanguageBox(), cc.xy(7, rowNb));
            setNextRow();
        }
        JButton addDescriptionRestorationBtn = new ButtonEag(labels.getString("eag2012.addDescriptionTranslationButton"));
        builder.add(addDescriptionRestorationBtn, cc.xy (1, rowNb));
        addDescriptionRestorationBtn.addActionListener(new AddDescriptionRestorationBtnAction(eag, tabbedPane, model));
        setNextRow();


        if(restorationlab.getContact() == null)
            restorationlab.setContact(new Contact());

        if(restorationlab.getContact().getTelephone().size() == 0) {
            telephoneRestorationlabTf = new JTextField();
        } else {
            telephoneRestorationlabTf = new JTextField(restorationlab.getContact().getTelephone().get(0).getContent());
        }
        builder.addLabel(labels.getString("eag2012.telephoneLabel"),    cc.xy (1, rowNb));
        builder.add(telephoneRestorationlabTf, cc.xy(3, rowNb));
        setNextRow();

        if(restorationlab.getContact().getEmail().size() == 0)
            restorationlab.getContact().getEmail().add(new Email());
        builder.addLabel(labels.getString("eag2012.emailLabel"),    cc.xy (1, rowNb));
        emailRestorationlabTf = new JTextField(restorationlab.getContact().getEmail().get(0).getHref());
        builder.add(emailRestorationlabTf,    cc.xy (3, rowNb));
        builder.addLabel(labels.getString("eag2012.linkTitleLabel"),    cc.xy (5, rowNb));
        emailTitleRestorationlabTf = new JTextField(restorationlab.getContact().getEmail().get(0).getContent());
        builder.add(emailTitleRestorationlabTf,    cc.xy (7, rowNb));
        setNextRow();

        if(restorationlab.getWebpage().size() == 0)
            restorationlab.getWebpage().add(new Webpage());
        builder.addLabel(labels.getString("eag2012.webpageLabel"),    cc.xy (1, rowNb));
        webpageRestorationlabTf = new JTextField(restorationlab.getWebpage().get(0).getHref());
        builder.add(webpageRestorationlabTf,    cc.xy (3, rowNb));
        builder.addLabel(labels.getString("eag2012.linkTitleLabel"),    cc.xy (5, rowNb));
        webpageTitleRestorationlabTf = new JTextField(restorationlab.getWebpage().get(0).getContent());
        builder.add(webpageTitleRestorationlabTf,    cc.xy (7, rowNb));
        setNextRow();



        builder.addSeparator(labels.getString("eag2012.reproductionService"), cc.xyw(1, rowNb, 7));
        setNextRow();

        if(repository.getServices().getTechservices().getReproductionser() == null)
            repository.getServices().getTechservices().setReproductionser(new Reproductionser());
        Reproductionser reproductionser = repository.getServices().getTechservices().getReproductionser();

        if(reproductionser.getDescriptiveNote() == null)
            reproductionser.setDescriptiveNote(new DescriptiveNote());
        if(reproductionser.getDescriptiveNote().getP().size() == 0)
            reproductionser.getDescriptiveNote().getP().add(new P());

        descriptionReproductionServiceTfs = new ArrayList<TextFieldWithLanguage>(reproductionser.getDescriptiveNote().getP().size());
        for(P p : reproductionser.getDescriptiveNote().getP()) {
            TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(p.getContent(), p.getLang());
            descriptionReproductionServiceTfs.add(textFieldWithLanguage);
            builder.addLabel(labels.getString("eag2012.descriptionLabel"),    cc.xy (1, rowNb));
            builder.add(textFieldWithLanguage.getTextField(), cc.xy(3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
            builder.add(textFieldWithLanguage.getLanguageBox(), cc.xy(7, rowNb));
            setNextRow();
        }
        JButton addDescriptionReproductionBtn = new ButtonEag(labels.getString("eag2012.addDescriptionTranslationButton"));
        builder.add(addDescriptionReproductionBtn, cc.xy (1, rowNb));
        addDescriptionReproductionBtn.addActionListener(new AddDescriptionReproductionBtnAction(eag, tabbedPane, model));
        setNextRow();

        if(reproductionser.getContact() == null)
            reproductionser.setContact(new Contact());

        if(reproductionser.getContact().getTelephone().size() == 0) {
            telephoneReproductionServiceTf = new JTextField();
        } else {
            telephoneReproductionServiceTf = new JTextField(reproductionser.getContact().getTelephone().get(0).getContent());
        }
        builder.addLabel(labels.getString("eag2012.telephoneLabel"),    cc.xy (1, rowNb));
        builder.add(telephoneReproductionServiceTf, cc.xy(3, rowNb));
        setNextRow();

        if(reproductionser.getContact().getEmail().size() == 0)
            reproductionser.getContact().getEmail().add(new Email());
        builder.addLabel(labels.getString("eag2012.emailLabel"),    cc.xy (1, rowNb));
        emailReproductionServiceTf = new JTextField(reproductionser.getContact().getEmail().get(0).getHref());
        builder.add(emailReproductionServiceTf,    cc.xy (3, rowNb));
        builder.addLabel(labels.getString("eag2012.linkTitleLabel"),    cc.xy (5, rowNb));
        emailTitleReproductionServiceTf = new JTextField(reproductionser.getContact().getEmail().get(0).getContent());
        builder.add(emailTitleReproductionServiceTf,    cc.xy (7, rowNb));
        setNextRow();

        if(reproductionser.getWebpage().size() == 0)
            reproductionser.getWebpage().add(new Webpage());
        builder.addLabel(labels.getString("eag2012.webpageLabel"),    cc.xy (1, rowNb));
        webpageReproductionServiceTf = new JTextField(reproductionser.getWebpage().get(0).getHref());
        builder.add(webpageReproductionServiceTf,    cc.xy (3, rowNb));
        builder.addLabel(labels.getString("eag2012.linkTitleLabel"),    cc.xy (5, rowNb));
        webpageTitleReproductionServiceTf = new JTextField(reproductionser.getWebpage().get(0).getContent());
        builder.add(webpageTitleReproductionServiceTf,    cc.xy (7, rowNb));
        setNextRow();

        builder.addLabel(labels.getString("eag2012.microformServices"), cc.xy(1, rowNb));
        if(reproductionser.getMicroformser() == null)
            reproductionser.setMicroformser(new Microformser());
        if(Arrays.asList(yesOrNo).contains(reproductionser.getMicroformser().getQuestion())) {
            microformServicesCombo.setSelectedItem(reproductionser.getMicroformser().getQuestion());
        }
        builder.add(microformServicesCombo, cc.xy(3, rowNb));
        setNextRow();

        builder.addLabel(labels.getString("eag2012.photographServices"), cc.xy(1, rowNb));
        if(reproductionser.getPhotographser() == null)
            reproductionser.setPhotographser(new Photographser());
        if(Arrays.asList(yesOrNo).contains(reproductionser.getPhotographser().getQuestion())) {
            photographServicesCombo.setSelectedItem(reproductionser.getPhotographser().getQuestion());
        }
        builder.add(photographServicesCombo, cc.xy(3, rowNb));
        setNextRow();

        builder.addLabel(labels.getString("eag2012.digitalServices"), cc.xy(1, rowNb));
        if(reproductionser.getDigitalser() == null)
            reproductionser.setDigitalser(new Digitalser());
        if(Arrays.asList(yesOrNo).contains(reproductionser.getDigitalser().getQuestion())) {
            digitalServicesCombo.setSelectedItem(reproductionser.getDigitalser().getQuestion());
        }
        builder.add(digitalServicesCombo, cc.xy(3, rowNb));
        setNextRow();

        builder.addLabel(labels.getString("eag2012.photocopyServices"), cc.xy(1, rowNb));
        if(reproductionser.getPhotocopyser() == null)
            reproductionser.setPhotocopyser(new Photocopyser());
        if(Arrays.asList(yesOrNo).contains(reproductionser.getPhotocopyser().getQuestion())) {
            photocopyServicesCombo.setSelectedItem(reproductionser.getPhotocopyser().getQuestion());
        }
        builder.add(photocopyServicesCombo, cc.xy(3, rowNb));
        setNextRow();



        builder.addSeparator(labels.getString("eag2012.recreationalServices"), cc.xyw(1, rowNb, 7));
        setNextRow();

        if(repository.getServices().getRecreationalServices() == null)
            repository.getServices().setRecreationalServices(new RecreationalServices());
        RecreationalServices recreationalServices = repository.getServices().getRecreationalServices();

        if(recreationalServices.getRefreshment() == null) {
            DescriptiveNote descriptiveNote = new DescriptiveNote();
            descriptiveNote.getP().add(new P());
            Refreshment refreshment = new Refreshment();
            refreshment.setDescriptiveNote(descriptiveNote);
            recreationalServices.setRefreshment(refreshment);
        }
        builder.addLabel(labels.getString("eag2012.refreshment"), cc.xy(1, rowNb));
        refreshmentTf = new TextFieldWithLanguage(recreationalServices.getRefreshment().getDescriptiveNote().getP().get(0).getContent(), recreationalServices.getRefreshment().getDescriptiveNote().getP().get(0).getLang());
        builder.add(refreshmentTf.getTextField(), cc.xy(3, rowNb));
        builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
        builder.add(refreshmentTf.getLanguageBox(), cc.xy(7, rowNb));
        setNextRow();

        if(recreationalServices.getExhibition().size() == 0) {
            recreationalServices.getExhibition().add(new Exhibition());
        }
        exhibitionTfs = new ArrayList<TextFieldWithLanguage>(recreationalServices.getExhibition().size());
        for(Exhibition exhibition : recreationalServices.getExhibition()) {
            if(exhibition.getDescriptiveNote() == null) {
                DescriptiveNote descriptiveNote = new DescriptiveNote();
                descriptiveNote.getP().add(new P());
                exhibition.setDescriptiveNote(descriptiveNote);
                exhibition.setWebpage(new Webpage());
            }

            builder.addLabel(labels.getString("eag2012.exhibition"),    cc.xy (1, rowNb));
            TextFieldWithLanguage exhibitionTf = new TextFieldWithLanguage(exhibition.getDescriptiveNote().getP().get(0).getContent(), exhibition.getDescriptiveNote().getP().get(0).getLang(), exhibition.getWebpage().getHref(), exhibition.getWebpage().getContent());
            exhibitionTfs.add(exhibitionTf);
            builder.add(exhibitionTf.getTextField(),                     cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
            builder.add(exhibitionTf.getLanguageBox(),                     cc.xy (7, rowNb));
            setNextRow();

            builder.addLabel(labels.getString("eag2012.webpageLabel"), cc.xy(1, rowNb));
            builder.add(exhibitionTf.getExtraField(),                                            cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.linkTitleLabel"), cc.xy(5, rowNb));
            builder.add(exhibitionTf.getSecondExtraField(),                                            cc.xy (7, rowNb));
            setNextRow();
        }
        JButton addExhibitionsBtn = new ButtonEag(labels.getString("eag2012.addExhibitionsButton"));
        builder.add(addExhibitionsBtn, cc.xy (1, rowNb));
        addExhibitionsBtn.addActionListener(new AddExhibitionsBtnAction(eag, tabbedPane, model));
        setNextRow();

        if(recreationalServices.getToursSessions().size() == 0) {
            recreationalServices.getToursSessions().add(new ToursSessions());
        }
        toursAndSessionsTfs = new ArrayList<TextFieldWithLanguage>(recreationalServices.getToursSessions().size());
        for(ToursSessions toursSessions : recreationalServices.getToursSessions()) {
            if(toursSessions.getDescriptiveNote() == null) {
                DescriptiveNote descriptiveNote = new DescriptiveNote();
                descriptiveNote.getP().add(new P());
                toursSessions.setDescriptiveNote(descriptiveNote);
                toursSessions.setWebpage(new Webpage());
            }
            builder.addLabel(labels.getString("eag2012.guidedToursAndSessions"),    cc.xy (1, rowNb));
            TextFieldWithLanguage textFieldWithLanguage = new TextFieldWithLanguage(toursSessions.getDescriptiveNote().getP().get(0).getContent(), toursSessions.getDescriptiveNote().getP().get(0).getLang(), toursSessions.getWebpage().getHref(), toursSessions.getWebpage().getContent());
            toursAndSessionsTfs.add(textFieldWithLanguage);
            builder.add(textFieldWithLanguage.getTextField(),                     cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
            builder.add(textFieldWithLanguage.getLanguageBox(),                     cc.xy (7, rowNb));
            setNextRow();
            builder.addLabel(labels.getString("eag2012.webpageLabel"), cc.xy(1, rowNb));
            builder.add(textFieldWithLanguage.getExtraField(),                                            cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.linkTitleLabel"), cc.xy(5, rowNb));
            builder.add(textFieldWithLanguage.getSecondExtraField(),                                            cc.xy (7, rowNb));
            setNextRow();
        }
        JButton addToursSessionsBtn = new ButtonEag(labels.getString("eag2012.addToursSessionsButton"));
        builder.add(addToursSessionsBtn, cc.xy (1, rowNb));
        addToursSessionsBtn.addActionListener(new AddToursSessionsBtnAction(eag, tabbedPane, model));
        setNextRow();

        if(recreationalServices.getOtherServices().size() == 0) {
            recreationalServices.getOtherServices().add(new OtherServices());
        }
        otherServicesTfs = new ArrayList<TextFieldWithLanguage>(recreationalServices.getOtherServices().size());
        for(OtherServices otherServices : recreationalServices.getOtherServices()) {
            if(otherServices.getDescriptiveNote() == null) {
                DescriptiveNote descriptiveNote = new DescriptiveNote();
                otherServices.setDescriptiveNote(descriptiveNote);
            }
            if(otherServices.getDescriptiveNote().getP().size() == 0) {
                otherServices.getDescriptiveNote().getP().add(new P());
            }
            if(otherServices.getWebpage() == null) {
                otherServices.setWebpage(new Webpage());
            }
            builder.addLabel(labels.getString("eag2012.otherServices"),    cc.xy (1, rowNb));
            TextFieldWithLanguage otherServicesTf = new TextFieldWithLanguage(otherServices.getDescriptiveNote().getP().get(0).getContent(), otherServices.getDescriptiveNote().getP().get(0).getLang(), otherServices.getWebpage().getHref(), otherServices.getWebpage().getContent());
            otherServicesTfs.add(otherServicesTf);
            builder.add(otherServicesTf.getTextField(),                     cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.language"), cc.xy(5, rowNb));
            builder.add(otherServicesTf.getLanguageBox(),                     cc.xy (7, rowNb));
            setNextRow();
            builder.addLabel(labels.getString("eag2012.webpageLabel"), cc.xy(1, rowNb));
            builder.add(otherServicesTf.getExtraField(),                                            cc.xy (3, rowNb));
            builder.addLabel(labels.getString("eag2012.linkTitleLabel"), cc.xy(5, rowNb));
            builder.add(otherServicesTf.getSecondExtraField(),                                            cc.xy (7, rowNb));
            setNextRow();
        }
        JButton addOtherServicesBtn = new ButtonEag(labels.getString("eag2012.addOtherServicesButton"));
        builder.add(addOtherServicesBtn, cc.xy (1, rowNb));
        addOtherServicesBtn.addActionListener(new AddOtherServicesBtnAction(eag, tabbedPane, model));
        setNextRow();


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

    public class AddTravellingDirectionsBtnAction extends UpdateEagObject {
        AddTravellingDirectionsBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject();
            } catch (Eag2012FormException e) {
            }
            eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getDirections().add(new Directions());
            reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
        }
    }
    public class AddRestaccessBtnAction extends UpdateEagObject {
        AddRestaccessBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject();
            } catch (Eag2012FormException e) {
            }
            eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getAccess().getRestaccess().add(new Restaccess());
            reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
        }
    }
    public class AddAccessibilityBtnAction extends UpdateEagObject {
        AddAccessibilityBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject();
            } catch (Eag2012FormException e) {
            }
//            if(eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getAccessibility().isEmpty()){
//                LOG.info("create new accessibility");
//                eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getAccessibility().add(new Accessibility());
//            }
            reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
        }
    }
    public class AddComputerplacesDescriptionBtnAction extends UpdateEagObject {
        AddComputerplacesDescriptionBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject();
            } catch (Eag2012FormException e) {
            }
            
            if(eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getSearchroom().getComputerPlaces().getDescriptiveNote() == null){
                DescriptiveNote descriptiveNote = new DescriptiveNote();
                descriptiveNote.setP(new ArrayList<P>());
                descriptiveNote.getP().add(new P());
                eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getSearchroom().getComputerPlaces().setDescriptiveNote(descriptiveNote);
            } else {
                eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getSearchroom().getComputerPlaces().getDescriptiveNote().getP().add(new P());
            }
            reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
        }
    }
    public class AddReadersticketBtnAction extends UpdateEagObject {
        AddReadersticketBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject();
            } catch (Eag2012FormException e) {
            }
            eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getSearchroom().getReadersTicket().add(new ReadersTicket());
            reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
        }
    }
    public class AddAdvancedordersBtnAction extends UpdateEagObject {
        AddAdvancedordersBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject();
            } catch (Eag2012FormException e) {
            }
            eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getSearchroom().getAdvancedOrders().add(new AdvancedOrders());
            reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
        }
    }
    public class AddResearchservicesBtnAction extends UpdateEagObject {
        AddResearchservicesBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject();
            } catch (Eag2012FormException e) {
            }
            eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getSearchroom().getResearchServices().add(new ResearchServices());
            reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
        }
    }
    public class AddExhibitionsBtnAction extends UpdateEagObject {
        AddExhibitionsBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject();
            } catch (Eag2012FormException e) {
            }
            eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getRecreationalServices().getExhibition().add(new Exhibition());
            reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
        }
    }
    public class AddToursSessionsBtnAction extends UpdateEagObject {
        AddToursSessionsBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject();
            } catch (Eag2012FormException e) {
            }
            eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getRecreationalServices().getToursSessions().add(new ToursSessions());
            reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
        }
    }
    public class AddOtherServicesBtnAction extends UpdateEagObject {
        AddOtherServicesBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject();
            } catch (Eag2012FormException e) {
            }
            eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getRecreationalServices().getOtherServices().add(new OtherServices());
            reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
        }
    }
    public class AddInternetAccessBtnAction extends UpdateEagObject {
        AddInternetAccessBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject();
            } catch (Eag2012FormException e) {
            }
            if(eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getInternetAccess() == null) {
                eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().setInternetAccess(new InternetAccess());
            }
            if(eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getInternetAccess().getDescriptiveNote() == null) {
                eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getInternetAccess().setDescriptiveNote(new DescriptiveNote());
            }
            eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getInternetAccess().getDescriptiveNote().getP().add(new P());
            reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
        }
    }

    public class AddDescriptionReproductionBtnAction extends UpdateEagObject {
        AddDescriptionReproductionBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject();
            } catch (Eag2012FormException e) {
            }
            Reproductionser reproductionser = eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getTechservices().getReproductionser();
            if(reproductionser.getDescriptiveNote() == null) {
                reproductionser.setDescriptiveNote(new DescriptiveNote());
            }
            reproductionser.getDescriptiveNote().getP().add(new P());
            reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
        }
    }

    public class AddDescriptionRestorationBtnAction extends UpdateEagObject {
        AddDescriptionRestorationBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject();
            } catch (Eag2012FormException e) {
            }
            Restorationlab restorationlab = eag.getArchguide().getDesc().getRepositories().getRepository().get(0).getServices().getTechservices().getRestorationlab();
            if(restorationlab.getDescriptiveNote() == null) {
                restorationlab.setDescriptiveNote(new DescriptiveNote());
            }
            restorationlab.getDescriptiveNote().getP().add(new P());
            reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
        }
    }


    public class SaveBtnAction extends UpdateEagObject {
        SaveBtnAction(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                super.updateEagObject();
                super.saveFile(eag.getControl().getRecordId().getValue());
                closeFrame();
            } catch (Eag2012FormException e) {
                reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
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
                super.updateEagObject();

                if(isNextTab) {
                    reloadTabbedPanel(new EagDescriptionPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 4);
                    tabbedPane.setEnabledAt(4, true);
                    tabbedPane.setEnabledAt(3, false);
                } else {
                    reloadTabbedPanel(new EagContactPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 2);
                    tabbedPane.setEnabledAt(2, true);
                    tabbedPane.setEnabledAt(3, false);
                }
            } catch (Eag2012FormException e) {
                reloadTabbedPanel(new EagAccessAndServicesPanel(eag, tabbedPane, eag2012Frame, model, labels).buildEditorPanel(errors), 3);
            }
        }
    }

    public abstract class UpdateEagObject extends DefaultBtnAction {

        public UpdateEagObject(Eag eag, JTabbedPane tabbedPane, ProfileListModel model) {
            super(eag, tabbedPane, model);
        }

        @Override
        protected void updateEagObject() throws Eag2012FormException {
            errors = new ArrayList<String>();

            boolean hasChanged = false;

            if(eag.getArchguide().getDesc().getRepositories().getRepository().size() == 1) { //todo: BECAUSE FOR NOW ONLY ONE REPOSITORY!!!!
                Repository repository = eag.getArchguide().getDesc().getRepositories().getRepository().get(0);

                boolean openingTimeExists = false;
                if(openingHoursTfs.size() > 0) {
                    repository.getTimetable().getOpening().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : openingHoursTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            Opening opening = new Opening();
                            opening.setContent(textFieldWithLanguage.getTextValue());
                            opening.setLang(textFieldWithLanguage.getLanguage());
                            repository.getTimetable().getOpening().add(opening);
                            openingTimeExists = true;
                        }
                    }
                }
                if(!openingTimeExists) {
                    errors.add("openingHoursTfs");
                }

                if(closingDatesTfs.size() > 0) {
                    repository.getTimetable().getClosing().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : closingDatesTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            Closing closing = new Closing();
                            closing.setContent(textFieldWithLanguage.getTextValue());
                            closing.setLang(textFieldWithLanguage.getLanguage());
                            repository.getTimetable().getClosing().add(closing);
                        }
                    }
                }

                if(travellingDirectionsTfs.size() > 0) {
                    repository.getDirections().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : travellingDirectionsTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            Directions directions = new Directions();
                            directions.setLang(textFieldWithLanguage.getLanguage());
                            directions.getContent().add(textFieldWithLanguage.getTextValue());
                            if(StringUtils.isNotEmpty(textFieldWithLanguage.getExtraValue())) {
                                Citation citation = new Citation();
                                citation.setHref(textFieldWithLanguage.getExtraValue());
                                directions.getContent().add(citation);
                            }
                            repository.getDirections().add(directions);
                            hasChanged = true;
                        }
                    }
                }
                repository.getAccess().setQuestion((String)accessiblePublicCombo.getSelectedItem());

                if(restaccessTfs.size() > 0) {
                    repository.getAccess().getRestaccess().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : restaccessTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            Restaccess restaccess = new Restaccess();
                            restaccess.setContent(textFieldWithLanguage.getTextValue());
                            restaccess.setLang(textFieldWithLanguage.getLanguage());
                            repository.getAccess().getRestaccess().add(restaccess);
                        }
                    }
                }

                if(termsOfUseTfs.size() > 0) {
                    repository.getAccess().getTermsOfUse().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : termsOfUseTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            TermsOfUse termsOfUse = new TermsOfUse();
                            termsOfUse.setLang(textFieldWithLanguage.getLanguage());
                            termsOfUse.setContent(textFieldWithLanguage.getTextValue());
                            if(StringUtils.isNotEmpty(textFieldWithLanguage.getExtraValue())) {
                                termsOfUse.setHref(textFieldWithLanguage.getExtraValue());
                            }
                            repository.getAccess().getTermsOfUse().add(termsOfUse);
                            hasChanged = true;
                        }
                    }
                }

//                if(repository.getAccessibility() == null){
//                    LOG.info("create new accessibility");
//                    repository.setAccessibility(new ArrayList<Accessibility>(){{ add(new Accessibility()); }});
//                }
                repository.getAccessibility().get(0).setQuestion((String) facilitiesForDisabledCombo.getSelectedItem());
                repository.getAccessibility().get(0).setLang(accessibilityTfs.get(0).getLanguage());
                repository.getAccessibility().get(0).setContent(accessibilityTfs.get(0).getTextValue());

/*
                if(accessibilityTfs.size() > 0) {
                    repository.getAccessibility().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : accessibilityTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            Accessibility accessibility = new Accessibility();
                            accessibility.setLang(textFieldWithLanguage.getLanguage());
                            accessibility.setContent(textFieldWithLanguage.getTextValue());
                            repository.getAccessibility().add(accessibility);
                            hasChanged = true;
                        }
                    }
                }*/

                Searchroom searchroom = repository.getServices().getSearchroom();
                if(!searchroom.getContact().getTelephone().isEmpty())
                    searchroom.getContact().getTelephone().get(0).setContent(telephoneSearchroomTf.getText());
                else{
                    Telephone telephone = new Telephone();
                    telephone.setContent(telephoneSearchroomTf.getText());
                    searchroom.getContact().getTelephone().add(telephone);
                }
                
                if(StringUtils.isNotEmpty(emailSearchroomTf.getText())) {
                    searchroom.getContact().getEmail().get(0).setHref(emailSearchroomTf.getText());
                    if(StringUtils.isNotEmpty(emailTitleSearchroomTf.getText())) {
                        searchroom.getContact().getEmail().get(0).setContent(emailTitleSearchroomTf.getText());
                    }
                }
                if(StringUtils.isNotEmpty(webpageSearchroomTf.getText())) {
                    searchroom.getWebpage().get(0).setHref(webpageSearchroomTf.getText());
                    if(StringUtils.isNotEmpty(webpageTitleSearchroomTf.getText())) {
                        searchroom.getWebpage().get(0).setContent(webpageTitleSearchroomTf.getText());
                    }
                }

                if(StringUtils.isNotEmpty(workplacesSearchroomTf.getText())) {
                    Num num = new Num();
                    num.setUnit("site");
                    num.setContent(workplacesSearchroomTf.getText());
                    searchroom.getWorkPlaces().setNum(num);
                } else {
                    errors.add("workplacesSearchroomTf");
                }

                if(StringUtils.isNotEmpty(computerplacesSearchroomTf.getText())) {
                    Num num = new Num();
                    num.setUnit("site");
                    num.setContent(computerplacesSearchroomTf.getText());
                    searchroom.getComputerPlaces().setNum(num);
                }
                
                if (computerplacesDescriptionTfs != null && computerplacesDescriptionTfs.size() > 0) {
                    searchroom.getComputerPlaces().getDescriptiveNote().getP().clear();
                    for (TextFieldWithLanguage textFieldWithLanguage : computerplacesDescriptionTfs) {
                        if (StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            P p = new P();
                            p.setContent(textFieldWithLanguage.getTextValue());
                            p.setLang(textFieldWithLanguage.getLanguage());
                            searchroom.getComputerPlaces().getDescriptiveNote().getP().add(p);
                            hasChanged = true;
                        }
                    }
                }

                if(StringUtils.isNotEmpty(microfilmplacesSearchroomTf.getText())) {
                    Num num = new Num();
                    num.setUnit("site");
                    num.setContent(microfilmplacesSearchroomTf.getText());
                    searchroom.getMicrofilmPlaces().setNum(num);
                }

                searchroom.getPhotographAllowance().setValue((String)photographAllowanceCombo.getSelectedItem());

                if(readersticketSearchroomTfs.size() > 0) {
                    searchroom.getReadersTicket().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : readersticketSearchroomTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            ReadersTicket readersTicket = new ReadersTicket();
                            readersTicket.setLang(textFieldWithLanguage.getLanguage());
                            readersTicket.setContent(textFieldWithLanguage.getTextValue());
                            readersTicket.setHref(textFieldWithLanguage.getExtraValue());
                            searchroom.getReadersTicket().add(readersTicket);
                            hasChanged = true;
                        }
                    }
                }

                if(advancedordersSearchroomTfs.size() > 0) {
                    searchroom.getAdvancedOrders().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : advancedordersSearchroomTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            AdvancedOrders advancedOrders = new AdvancedOrders();
                            advancedOrders.setLang(textFieldWithLanguage.getLanguage());
                            advancedOrders.setContent(textFieldWithLanguage.getTextValue());
                            advancedOrders.setHref(textFieldWithLanguage.getExtraValue());
                            searchroom.getAdvancedOrders().add(advancedOrders);
                            hasChanged = true;
                        }
                    }
                }

                if(researchServicesSearchroomTfs.size() > 0) {
                    searchroom.getResearchServices().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : researchServicesSearchroomTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            ResearchServices researchServices = new ResearchServices();
                            DescriptiveNote descriptiveNote = new DescriptiveNote();
                            descriptiveNote.setP(new ArrayList<P>() {{
                                add(new P());
                            }});
                            descriptiveNote.getP().get(0).setContent(textFieldWithLanguage.getTextValue());
                            descriptiveNote.getP().get(0).setLang(textFieldWithLanguage.getLanguage());
                            researchServices.setDescriptiveNote(descriptiveNote);
                            searchroom.getResearchServices().add(researchServices);
                            hasChanged = true;
                        }
                    }
                }

                boolean libraryExists = false;
                Library library = repository.getServices().getLibrary();
                if(!library.getContact().getTelephone().isEmpty()){
                    library.getContact().getTelephone().get(0).setContent(telephoneLibraryTf.getText());
                    libraryExists = true;
                }
                else{
                    Telephone telephone = new Telephone();
                    telephone.setContent(telephoneLibraryTf.getText());
                    library.getContact().getTelephone().add(telephone);
                    libraryExists = true;
                }
                if(StringUtils.isNotEmpty(emailLibraryTf.getText())) {
                    library.getContact().getEmail().get(0).setHref(emailLibraryTf.getText());
                    if(StringUtils.isNotEmpty(emailTitleLibraryTf.getText())) {
                        library.getContact().getEmail().get(0).setContent(emailTitleLibraryTf.getText());
                    }
                    libraryExists = true;
                }
                if(StringUtils.isNotEmpty(webpageLibraryTf.getText())) {
                    library.getWebpage().get(0).setHref(webpageLibraryTf.getText());
                    if(StringUtils.isNotEmpty(webpageTitleLibraryTf.getText())) {
                        library.getWebpage().get(0).setContent(webpageTitleLibraryTf.getText());
                    }
                    libraryExists = true;
                }
                if(StringUtils.isNotEmpty(monographicPubLibraryTf.getText())) {
                    Num num = new Num();
                    num.setUnit("site");
                    num.setContent(monographicPubLibraryTf.getText());
                    library.getMonographicpub().setNum(num);
                }
                if(StringUtils.isNotEmpty(serialPubLibraryTf.getText())) {
                    Num num = new Num();
                    num.setUnit("site");
                    num.setContent(serialPubLibraryTf.getText());
                    library.getSerialpub().setNum(num);
                    libraryExists = true;
                }
                library.setQuestion("yes");
                if(!libraryExists) {
                    repository.getServices().setLibrary(null);
                }

                InternetAccess internetAccess = repository.getServices().getInternetAccess();
                internetAccess.setQuestion("yes");
                boolean isInternetAccessFilled = false;
                if(internetAccessDescTfs.size() > 0) {
                    internetAccess.getDescriptiveNote().getP().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : internetAccessDescTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            P p = new P();
                            p.setContent(textFieldWithLanguage.getTextValue());
                            p.setLang(textFieldWithLanguage.getLanguage());
                            internetAccess.getDescriptiveNote().getP().add(p);
                            isInternetAccessFilled = true;
                        }
                    }
                }
                if(!isInternetAccessFilled) {
                    repository.getServices().setInternetAccess(null);
                }

                Techservices techservices = repository.getServices().getTechservices();
                Restorationlab restorationlab = techservices.getRestorationlab();

                boolean isRestorationDescFilled = false;
                boolean restorationLabExists = false;
                if(descriptionRestorationServiceTfs.size() > 0) {
                    restorationlab.getDescriptiveNote().getP().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : descriptionRestorationServiceTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            P p = new P();
                            p.setContent(textFieldWithLanguage.getTextValue());
                            p.setLang(textFieldWithLanguage.getLanguage());
                            restorationlab.getDescriptiveNote().getP().add(p);
                            isRestorationDescFilled = true;
                            restorationLabExists = true;
                        }
                    }
                }
                if(!isRestorationDescFilled) {
                    restorationlab.setDescriptiveNote(null);
                }

                if(!restorationlab.getContact().getTelephone().isEmpty()){
                    restorationlab.getContact().getTelephone().get(0).setContent(telephoneRestorationlabTf.getText());
                    restorationLabExists = true;
                }
                else{
                    Telephone telephone = new Telephone();
                    telephone.setContent(telephoneRestorationlabTf.getText());
                    restorationlab.getContact().getTelephone().add(telephone);
                    restorationLabExists = true;
                }
                if(StringUtils.isNotEmpty(emailRestorationlabTf.getText())) {
                    restorationlab.getContact().getEmail().get(0).setHref(emailRestorationlabTf.getText());
                    if(StringUtils.isNotEmpty(emailTitleRestorationlabTf.getText())) {
                        restorationlab.getContact().getEmail().get(0).setContent(emailTitleRestorationlabTf.getText());
                    }
                    restorationLabExists = true;
                }
                if(StringUtils.isNotEmpty(webpageRestorationlabTf.getText())) {
                    restorationlab.getWebpage().get(0).setHref(webpageRestorationlabTf.getText());
                    if(StringUtils.isNotEmpty(webpageTitleRestorationlabTf.getText())) {
                        restorationlab.getWebpage().get(0).setContent(webpageTitleRestorationlabTf.getText());
                    }
                    restorationLabExists = true;
                }
                restorationlab.setQuestion("yes");

                if(!restorationLabExists)
                    techservices.setRestorationlab(null);


                Reproductionser reproductionser = techservices.getReproductionser();
                boolean isReproductionDescFilled = false;
                if(descriptionReproductionServiceTfs.size() > 0) {
                    reproductionser.getDescriptiveNote().getP().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : descriptionReproductionServiceTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            P p = new P();
                            p.setContent(textFieldWithLanguage.getTextValue());
                            p.setLang(textFieldWithLanguage.getLanguage());
                            reproductionser.getDescriptiveNote().getP().add(p);
                            isReproductionDescFilled = true;
                        }
                    }
                }
                if(!isReproductionDescFilled) {
                    reproductionser.setDescriptiveNote(null);
                }

                boolean contactExists = false;
                if(!reproductionser.getContact().getTelephone().isEmpty()){
                    reproductionser.getContact().getTelephone().get(0).setContent(telephoneReproductionServiceTf.getText());
                    contactExists = true;
                }
                else{
                    Telephone telephone = new Telephone();
                    telephone.setContent(telephoneReproductionServiceTf.getText());
                    reproductionser.getContact().getTelephone().add(telephone);
                    contactExists = true;
                }
                if(StringUtils.isNotEmpty(emailReproductionServiceTf.getText())) {
                    reproductionser.getContact().getEmail().get(0).setHref(emailReproductionServiceTf.getText());
                    if(StringUtils.isNotEmpty(emailTitleReproductionServiceTf.getText())) {
                        reproductionser.getContact().getEmail().get(0).setContent(emailTitleReproductionServiceTf.getText());
                    }
                    contactExists = true;
                }
                if(!contactExists)
                    reproductionser.setContact(null);

                if(StringUtils.isNotEmpty(webpageReproductionServiceTf.getText())) {
                    reproductionser.getWebpage().get(0).setHref(webpageReproductionServiceTf.getText());
                    if(StringUtils.isNotEmpty(webpageTitleReproductionServiceTf.getText())) {
                        reproductionser.getWebpage().get(0).setContent(webpageTitleReproductionServiceTf.getText());
                    }
                } else {
                    reproductionser.getWebpage().clear();
                }

                reproductionser.getMicroformser().setQuestion((String)microformServicesCombo.getSelectedItem());
                reproductionser.getPhotographser().setQuestion((String)photographServicesCombo.getSelectedItem());
                reproductionser.getDigitalser().setQuestion((String)digitalServicesCombo.getSelectedItem());
                reproductionser.getPhotocopyser().setQuestion((String)photocopyServicesCombo.getSelectedItem());
                reproductionser.setQuestion("yes");


                RecreationalServices recreationalServices = repository.getServices().getRecreationalServices();
                if(StringUtils.isNotEmpty(refreshmentTf.getTextValue())) {
                    recreationalServices.getRefreshment().getDescriptiveNote().getP().get(0).setContent(refreshmentTf.getTextValue());
                    recreationalServices.getRefreshment().getDescriptiveNote().getP().get(0).setLang(refreshmentTf.getLanguage());
                }

                if(exhibitionTfs.size() > 0) {
                    recreationalServices.getExhibition().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : exhibitionTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            Exhibition exhibition = new Exhibition();
                            DescriptiveNote descriptiveNote = new DescriptiveNote();
                            descriptiveNote.setP(new ArrayList<P>(){{ add(new P()); }});
                            exhibition.setDescriptiveNote(descriptiveNote);
                            exhibition.getDescriptiveNote().getP().get(0).setLang(textFieldWithLanguage.getLanguage());
                            exhibition.getDescriptiveNote().getP().get(0).setContent(textFieldWithLanguage.getTextValue());
                            if(StringUtils.isNotEmpty(textFieldWithLanguage.getExtraValue())) {
                                exhibition.setWebpage(new Webpage());
                                exhibition.getWebpage().setHref(textFieldWithLanguage.getExtraValue());
                                exhibition.getWebpage().setContent(textFieldWithLanguage.getSecondExtraValue());
                            }
                            recreationalServices.getExhibition().add(exhibition);
                            hasChanged = true;
                        }
                    }
                }

                if(toursAndSessionsTfs.size() > 0) {
                    recreationalServices.getToursSessions().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : toursAndSessionsTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            ToursSessions toursSessions = new ToursSessions();
                            DescriptiveNote descriptiveNote = new DescriptiveNote();
                            descriptiveNote.setP(new ArrayList<P>(){{ add(new P()); }});
                            toursSessions.setDescriptiveNote(descriptiveNote);
                            toursSessions.getDescriptiveNote().getP().get(0).setLang(textFieldWithLanguage.getLanguage());
                            toursSessions.getDescriptiveNote().getP().get(0).setContent(textFieldWithLanguage.getTextValue());
                            if(StringUtils.isNotEmpty(textFieldWithLanguage.getExtraValue())) {
                                toursSessions.setWebpage(new Webpage());
                                toursSessions.getWebpage().setHref(textFieldWithLanguage.getExtraValue());
                                toursSessions.getWebpage().setContent(textFieldWithLanguage.getSecondExtraValue());
                            }
                            recreationalServices.getToursSessions().add(toursSessions);
                            hasChanged = true;
                        }
                    }
                }

                if(otherServicesTfs.size() > 0) {
                    recreationalServices.getOtherServices().clear();
                    for(TextFieldWithLanguage textFieldWithLanguage : otherServicesTfs) {
                        if(StringUtils.isNotEmpty(textFieldWithLanguage.getTextValue())) {
                            OtherServices otherServices = new OtherServices();
                            DescriptiveNote descriptiveNote = new DescriptiveNote();
                            descriptiveNote.setP(new ArrayList<P>(){{ add(new P()); }});
                            otherServices.setDescriptiveNote(descriptiveNote);
                            otherServices.getDescriptiveNote().getP().get(0).setLang(textFieldWithLanguage.getLanguage());
                            otherServices.getDescriptiveNote().getP().get(0).setContent(textFieldWithLanguage.getTextValue());
                            if(StringUtils.isNotEmpty(textFieldWithLanguage.getExtraValue())) {
                                otherServices.setWebpage(new Webpage());
                                otherServices.getWebpage().setHref(textFieldWithLanguage.getExtraValue());
                                otherServices.getWebpage().setContent(textFieldWithLanguage.getSecondExtraValue());
                            }
                            recreationalServices.getOtherServices().add(otherServices);
                            hasChanged = true;
                        }
                    }
                }
            }



            if(!errors.isEmpty()) {
                throw new Eag2012FormException("Errors in validation of EAG 2012");
            }
        }
    }
}
