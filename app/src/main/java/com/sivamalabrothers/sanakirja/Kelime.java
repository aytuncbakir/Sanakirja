package com.sivamalabrothers.sanakirja;



public class Kelime {

    private String id;
    private String kelime;
    private String anlam;
    private String cumle;


    public Kelime(){

    }

    public Kelime(String id, String kategori){


    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKelime() {
        return kelime;
    }

    public void setKelime(String kelime) {
        this.kelime = kelime;
    }

    public String getAnlam() {
        return anlam;
    }

    public void setAnlam(String anlam) {
        this.anlam = anlam;
    }

    public String getCumle() {
        return cumle;
    }

    public void setCumle(String cumle) {
        this.cumle = cumle;
    }
}
