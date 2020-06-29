package org.HardCore.gui.windows;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.objects.dto.BewerbungDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.exceptions.BewerbungException;
import org.HardCore.process.proxy.BewerbungControlProxy;
import org.HardCore.process.proxy.LoginControlProxy;
import org.HardCore.process.proxy.RegistrationControlProxy;
import org.HardCore.services.util.Views;

public class DeleteBewerbungWindow extends Window {
    //Window zum Löschen von Bewerbungen auf Stellenanzeigen

    public DeleteBewerbungWindow(BewerbungDTO bewerbungDTO) {
        center();

        VerticalLayout verticalLayout = new VerticalLayout();
        Panel panel = new Panel();
        panel.setWidth("700");
        panel.setContent(new Label( "Sind Sie sicher, dass Sie Ihre Bewerbung auf diese Stellenanzeige löschen wollen? <br>" +
                "Dieser Vorgang ist unumkehrbar!", ContentMode.HTML));
        verticalLayout.addComponent(panel);

        //OK Button
        Button okButton = new Button("Ok");
        okButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    BewerbungControlProxy.getInstance().deleteBewerbung(bewerbungDTO);
                } catch (BewerbungException e) {
                    Notification.show("DB-Fehler", "Löschen war nicht erfolgreich!", Notification.Type.ERROR_MESSAGE);
                }
                Notification.show( "Bewerbung gelöscht!", Notification.Type.HUMANIZED_MESSAGE);
                UI.getCurrent().getNavigator().navigateTo(Views.BEWERBUNG);
                close();
            }
        });

        //Abbruch Button
        Button abortButton = new Button("Abbrechen");
        abortButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                close();
            }
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(okButton);
        horizontalLayout.addComponent(abortButton);
        verticalLayout.addComponent(horizontalLayout);
        verticalLayout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
        setContent(verticalLayout);
    }
}

