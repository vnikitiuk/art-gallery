package org.viktoriianikitiuk.artoffreedom;

public class Painting {
    private String paintingName;
    private String artistName;
//    private int price;

    public Painting() {
        this.paintingName = paintingName;
        this.artistName = artistName;
//        this.price = price;
    }


    public String getArtistName(){
        return this.artistName;
    }

    public String getPaintingName(){
        return this.paintingName;
    }

//    public int getPrice(){
//        return price;
//    }

    public void setPaintingName(String paintingName) {
        this.paintingName = paintingName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

//    public void setPrice(int price) {
//        this.price = price;
//    }

}
