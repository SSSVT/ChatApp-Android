package schweika.chatapplication.Repositories;

import schweika.chatapplication.Models.API.Token;

public abstract class SecuredRepository extends Repository
{
    protected Token token;

    public SecuredRepository(Token token)
    {
        this.token = token;
    }

    protected String getTokenHeader()
    {
        return token.type + " " + token.token;
    }
}
