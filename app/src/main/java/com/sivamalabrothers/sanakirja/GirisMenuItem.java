package com.sivamalabrothers.sanakirja;


import java.util.ArrayList;

public class GirisMenuItem {

    private String adi;
    private int imgId;

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public static ArrayList<GirisMenuItem> getGirisMenuItems(){

        ArrayList<GirisMenuItem> girisMenuItemArrayList = new ArrayList<GirisMenuItem>();
        int[] resimler={
                R.mipmap.save,
                R.mipmap.list,
                R.mipmap.search,
                R.mipmap.delete,
                R.mipmap.update


        };


        String [] menuItems = {

                "KAYDET",
                "SÖZLÜK",
                "ARA",
                "SİL",
                "GÜNCELLE"


        };

        for(int i=0; i< resimler.length; i++){
            GirisMenuItem girisMenuItem = new GirisMenuItem();
            girisMenuItem.setAdi(menuItems[i]);
            girisMenuItem.setImgId(resimler[i]);

            girisMenuItemArrayList.add(girisMenuItem);
        }

        return girisMenuItemArrayList;
    }

    public static String splitData(String satir){


        String str[];
        String data = "";

        str = satir.split(",");
        data =  str[0];

        char [] x = data.toCharArray();
        boolean ikinoktailk = false;
        boolean ikinoktason = false;
        for(int i = 0;i<x.length; i++){

            if(x[i] =='<' || x[i] =='>' || x[i] =='/'){
                x[i]= ' ';
            }
            if(!ikinoktailk && x[i] == 'b')
                x[i] = ' ';

            if(!ikinoktason && x[i] == 'b')
                x[i] = ' ';

            if(x[i] == ':'){
                if(ikinoktailk == false)
                    ikinoktailk = true;
                else
                    ikinoktason = true;
            }
        }
        String s = "";
        for(int j = 0; j<x.length;j++)
            if(x[j] != ' ' )
                s += x[j];

        data = s;



        return data;
    }



}
