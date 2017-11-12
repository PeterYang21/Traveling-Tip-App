package csc214.project3.Model;


public class Attraction {
    private String name;
    private int previewImage;
    private int image01;
    private int image02;
    private int image03;
    private int info; // string resource file
    private String city;

    public Attraction(String name, String city, int previewImage, int image01, int image02, int image03, int info){
        this.name = name;
        this.city = city;
        this.previewImage = previewImage;
        this.image01 = image01;
        this.image02 = image02;
        this.image03 = image03;
        this.info = info;
    }

    public String getName(){
        return this.name;
    }

    public int getInfo(){
        return this.info;
    }

    public int getImage01(){
        return this.image01;
    }

    public int getImage02(){
        return this.image02;
    }

    public int getImage03(){
        return this.image03;
    }

    public int getPreviewImage(){
        return this.previewImage;
    }

    public String getCity(){
        return this.city;
    }
}
