package org.hardcore.model.objects.dto;

public class BewerbungDTO extends AbstractDTO {
    private String text;
    private int id;

    public void setFreitext(String text){

        this.text = text;
    }
    public String getFreitext(){
        return this.text;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}