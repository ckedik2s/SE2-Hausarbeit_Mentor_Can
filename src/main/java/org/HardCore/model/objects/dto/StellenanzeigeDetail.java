package org.HardCore.model.objects.dto;

import java.time.LocalDate;

public class StellenanzeigeDetail {
    private int id ;
    private int id_anzeige;
    private String beschreibung = "";
    private String art = "";
    private String name = "";
    private LocalDate zeitraum = LocalDate.now();
    private String branche = "";
    private String studiengang = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_anzeige() {
        return id_anzeige;
    }

    public void setId_anzeige(int id_anzeige) {
        this.id_anzeige = id_anzeige;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getZeitraum() {
        return zeitraum;
    }

    public void setZeitraum(LocalDate zeitraum) {
        this.zeitraum = zeitraum;
    }

    public String getBranche() {
        return branche;
    }

    public void setBranche(String branche) {
        this.branche = branche;
    }

    public String getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }

    public String toString(){
        return "ID: " + this.getId() + "\n" +
                "ID Anzeige: " + this.getId_anzeige() + "\n" +
                "Beschreibung: " + this.getBeschreibung() + "\n" +
                "Art: " + this.getArt() + "\n" +
                "Name: " + this.getName() + "\n" +
                "Zeitraum: " + this.getZeitraum().toString() + "\n" +
                "Branche: "+ this.getBranche() + "\n" +
                "Studiengang: "+ this.getStudiengang() + "\n";
    }
}
