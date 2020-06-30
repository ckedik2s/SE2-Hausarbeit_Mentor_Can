package org.HardCore.gui.views;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.HardCore.gui.components.TopPanel;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.gui.windows.CreateStellenanzeigeWindow;
import org.HardCore.gui.windows.DeleteStellenanzeigeWindow;
import org.HardCore.gui.windows.DeleteWindow;
import org.HardCore.gui.windows.StellenanzeigeWindow;
import org.HardCore.model.objects.dto.StellenanzeigeDTO;
import org.HardCore.model.objects.dto.UnternehmenDTO;
import org.HardCore.process.proxy.SearchControlProxy;
import org.HardCore.services.util.BuildGrid;

import java.sql.SQLException;
import java.util.List;

public class StellenanzeigeView extends VerticalLayout implements View {

    private StellenanzeigeDTO selektiert;
    private List<StellenanzeigeDTO> list;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        //User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        UnternehmenDTO unternehmenDTO = new UnternehmenDTO(((MyUI) UI.getCurrent()).getUserDTO());
        this.setUp(unternehmenDTO);
    }

    private void setUp(UnternehmenDTO unternehmenDTO) {

        //Top Layer
        this.addComponent(new TopPanel());
        setStyleName("schrift-profil");
        Label line = new Label("<hr>", ContentMode.HTML);
        this.addComponent(line);
        line.setSizeFull();
        //Tabelle
        final Grid<StellenanzeigeDTO> grid = new Grid<>("Ihre Stellenanzeigen");
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);
        grid.setStyleName("schrift-tabelle");
        SingleSelect<StellenanzeigeDTO> selection = grid.asSingleSelect();

        //Tabelle befüllen
        try {
            list = SearchControlProxy.getInstance().getAnzeigenForUser();
        } catch (SQLException e) {
            Notification.show("Es ist ein SQL-Fehler aufgetreten. Bitte informieren Sie einen Administrator!");
        }
        BuildGrid.buildGrid(grid);
        grid.addColumn(StellenanzeigeDTO::getAnzahl_bewerber).setCaption("Anzahl der Bewerber");
        grid.setItems(list);

        //ShowButton
        Button showButton = new Button("Bearbeiten");
        showButton.setEnabled(false);

        //CreateButton
        Button createButton = new Button("Erstellen");

        //DeleteButton
        Button deleteButton = new Button("Löschen");
        deleteButton.setEnabled(false);

        //Tabellen Select Config
        grid.addSelectionListener(new SelectionListener<StellenanzeigeDTO>() {
            @Override
            public void selectionChange(SelectionEvent<StellenanzeigeDTO> event) {
                if (selection.getValue() == null) {
                    showButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                } else {
                    System.out.println("Zeile selektiert: " + selection.getValue());
                    selektiert = selection.getValue();
                    deleteButton.setEnabled(true);
                    showButton.setEnabled(true);
                }
            }
        });

        //ShowButton Config Stellenanzeige Bearbeiten
        showButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                StellenanzeigeWindow window = new StellenanzeigeWindow(selektiert, grid, unternehmenDTO);
                UI.getCurrent().addWindow(window);
            }
        });

        //CreateButton Config
        createButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                CreateStellenanzeigeWindow window = new CreateStellenanzeigeWindow(new StellenanzeigeDTO(), grid, unternehmenDTO);
                UI.getCurrent().addWindow(window);
            }
        });

        //deleteButton Config
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                DeleteStellenanzeigeWindow deleteStellenanzeigeWindow = new DeleteStellenanzeigeWindow(selektiert);
                UI.getCurrent().addWindow(new DeleteWindow(deleteStellenanzeigeWindow));
            }
        });

        //HorizontalLayout
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(showButton);
        horizontalLayout.addComponent(createButton);
        horizontalLayout.addComponent(deleteButton);

        //Darstellung
        addComponent(grid);
        addComponent(horizontalLayout);
        setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
    }
}
