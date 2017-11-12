package csc214.project3.Model;


public class Favorite {
    private City favCity;

    public Favorite(City city){
        this.favCity = city;
    }

    public City getFavCity(){
        return this.favCity;
    }
}
