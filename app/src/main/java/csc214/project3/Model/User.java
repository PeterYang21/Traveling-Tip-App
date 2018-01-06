package csc214.project3.Model;

public class User {
    private Account mAccount;
    private Profile mProfile;
    private String mUserName; // primary key
    private Favorite mFavorite;

    public User(){}

    public void setAccount(Account account){
        this.mAccount = account;
        this.mUserName = account.getUserName();
    }

    public void setUserName(String userName){
        this.mUserName = userName;
    }

    public void setProfile(Profile profile){
        this.mProfile = profile;
    }

    public void setFavorite(Favorite favorite){
        this.mFavorite = favorite;
    }

    public Account getAccount(){
        return this.mAccount;
    }

    public Profile getProfile(){
        return this.mProfile;
    }

    public String getUserName(){
        return this.mUserName;
    }

    public Favorite getFavorite(){
        return this.mFavorite;
    }
}
