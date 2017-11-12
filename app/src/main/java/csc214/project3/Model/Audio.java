package csc214.project3.Model;

public class Audio {
    private String name;
    private int ID;

    public Audio(String name){

        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setID(int ID){
        this.ID = ID;
    }
    public int getID(){
        return this.ID;
    }
}
