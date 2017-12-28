package schweika.chatapplication;

import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.User;

public class AppData
{
    private User currentUser;
    private Token token;

    public User getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }

    public Token getToken()
    {
        return token;
    }

    public void setToken(Token token)
    {
        this.token = token;
    }

    private AppData()
    {

    }

    private static AppData instance;

    public static AppData getInstance()
    {
        if (instance == null)
            instance = new AppData();

        return instance;
    }
}
