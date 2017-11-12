package csc214.project3.Model;


public class Account {
    private String userName;
    private String password;

    public Account(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getPassword(){
        return this.password;
    }
}
