package org.HardCore.model.objects.dto;

public class Bewerbung {
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