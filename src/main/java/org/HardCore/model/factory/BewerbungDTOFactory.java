package org.HardCore.model.factory;

import org.HardCore.model.objects.dto.BewerbungDTO;

public class BewerbungDTOFactory {

    public static BewerbungDTO createBewerbungDTO(int id, String text) {
        BewerbungDTO bewerbungDTO = new BewerbungDTO();

        bewerbungDTO.setId(id);
        bewerbungDTO.setFreitext(text);
        return bewerbungDTO;
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Dieses Object kann nicht geclont werden!");
    }
}
