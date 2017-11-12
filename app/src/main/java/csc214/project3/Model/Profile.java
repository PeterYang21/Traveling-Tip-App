package csc214.project3.Model;


public class Profile {
    private String userName;
    private String pathToPhoto;
    private String fullName;
    private String homeTown;
    private String bio;

    public Profile(String userName, String pathToPhoto, String fullName, String homeTown,String bio){
        this.userName = userName;
        this.pathToPhoto = pathToPhoto;
        this.fullName = fullName;
        this.homeTown = homeTown;
        this.bio = bio;
    }


    public String getPathToPhoto(){
        return this.pathToPhoto;
    }

    public String getFullName(){
        return this.fullName;
    }

    public String getHomeTown(){
        return this.homeTown;
    }

    public String getBio(){
        return this.bio;
    }

}
