package csc214.project3.Model;


public class City {

    private String CityName;
    private int CityImageResource;

    public City(String CityName, int CityImageResource){
        this.CityName = CityName;
        this.CityImageResource = CityImageResource;
    }

    public int getCityImageResource(){
        return this.CityImageResource;
    }

    public String getCityName(){
        return this.CityName;
    }
}
