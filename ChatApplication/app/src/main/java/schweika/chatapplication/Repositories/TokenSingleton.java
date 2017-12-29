package schweika.chatapplication.Repositories;

import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.User;

public class TokenSingleton
{
    private Token token;
    private User user;

    private TokenSingleton()
    {
    }

    private static TokenSingleton instance;

    public static TokenSingleton getInstance()
    {
        if (instance == null)
            instance = new TokenSingleton();

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