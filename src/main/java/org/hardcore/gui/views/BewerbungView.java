package org.hardcore.gui.views;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.hardcore.gui.components.TopPanel;
import org.hardcore.gui.ui.MyUI;
import org.hardcore.gui.windows.DeleteBewerbungWindow;
import org.hardcore.gui.windows.DeleteWindow;
import org.hardcore.model.objects.dto.BewerbungDTO;
import org.hardcore.model.objects.dto.StellenanzeigeDTO;
import org.hardcore.model.objects.dto.StudentDTO;
import org.hardcore.process.exceptions.DatabaseException;
import org.hardcore.process.proxy.BewerbungControlProxy;
import org.hardcore.process.proxy.StellenanzeigeControlProxy;
import org.hardcore.services.util.BuildGrid;

import java.sql.SQLException;
import java.util.List;

public class BewerbungView extends VerticalLayout implements View {

    private StellenanzeigeDTO selektiert;
    private List<StellenanzeigeDTO> list;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        StudentDTO studentDTO = new StudentDTO( ( (MyUI) UI.getCurrent() ).getUserDTO() );

        this.setUp(studentDTO);
    }

    private void setUp(StudentDTO studentDTO) {

        //Top Layer
        this.addComponent( new TopPanel() );
        Label line = new Label("<hr>", ContentMode.HTML);
        this.addComponent(line);
        line.setSizeFull();
        setStyleName("schrift-profil");
        //Tabelle
        final Grid<StellenanzeigeDTO> grid = new Grid<>("Ihre Bewerbungen");
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);
        SingleSelect<StellenanzeigeDTO> selection = grid.asSingleSelect();
        grid.setStyleName("schrift-tabelle");
        //Tabelle füllen
        try {
            list = StellenanzeigeControlProxy.getInstance().getAnzeigenForStudent(studentDTO);
        } catch (SQLException e) {
            Notification.show("Es ist ein SQL-Fehler aufgetreten. Bitte informieren Sie einen Administrator!");
        }
        BuildGrid.buildGrid(grid);
        grid.setItems(list);

        //DeleteButton
        Button deleteButton = new Button("Löschen");
        deleteButton.setEnabled(false);

        //Tabellen Select Config
        grid.addSelectionListener(new SelectionListener<StellenanzeigeDTO>() {
            @Override
            public void selectionChange(SelectionEvent<StellenanzeigeDTO> event) {
                if (selection.getValue() == null) {
                    deleteButton.setEnabled(false);
                }
                else {
                    selektiert = selection.getValue();
                    deleteButton.setEnabled(true);
                }
            }
        });

        //deleteButton Config
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                BewerbungDTO bewerbungDTO = null;
                try {
                    bewerbungDTO = BewerbungControlProxy.getInstance().getBewerbungForStellenanzeige(selektiert, studentDTO);
                } catch (SQLException e) {
                    Notification.show("Es ist ein SQL-Fehler aufgetreten. Bitte kontaktieren Sie den Administrator!", Notification.Type.ERROR_MESSAGE);
                } catch (DatabaseException e) {
                    Notification.show("Es ist ein Datenbankfehler aufgetreten. Bitte versuchen Sie es erneut!", Notification.Type.ERROR_MESSAGE);
                }
                DeleteBewerbungWindow deleteBewerbungWindow = new DeleteBewerbungWindow(bewerbungDTO);
                UI.getCurrent().addWindow( new DeleteWindow(deleteBewerbungWindow) );
            }
        });

        //HorizontalLayout
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(deleteButton);

        //Darstellung
        addComponent(grid);
        addComponent(horizontalLayout);
        setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
    }
}
