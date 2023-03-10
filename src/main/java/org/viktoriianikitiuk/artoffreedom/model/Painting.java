package org.viktoriianikitiuk.artoffreedom.model;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table
public class Painting {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column
    private String paintingName;
    @Column
    private String artistName;

    @Column
    private String paintingImage;
    private int price;

    public Painting() {
    }

    public Painting(String pN, String aN, String pI, int price) {
        this.paintingName = pN;
        this.artistName = aN;
        this.paintingImage = pI;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getArtistName(){
        return this.artistName;
    }

    public String getPaintingName(){
        return this.paintingName;
    }

    public String getPaintingImage() {
        return paintingImage;
    }

    public int getPrice(){
        return price;
    }

    public void setPaintingName(String paintingName) {
        this.paintingName = paintingName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setPaintingImage(String paintingImage) {
        this.paintingImage = paintingImage;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
