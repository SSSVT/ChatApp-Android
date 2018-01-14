package schweika.chatapplication;

import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Models.API.User;

public class DataContext
{
    private Token token;
    private User user;

    private DataContext()
    {
    }

    private static DataContext instance;

    public static DataContext getInstance()
    {
        if (instance == null)
            instance = new DataContext();

        return instance;
    }

    public void setToken(Token token)
    {
        this.token = token;
    }

    public Token getToken()
    {
        return this.token;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
