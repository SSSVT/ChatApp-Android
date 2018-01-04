package schweika.chatapplication.Repositories;

import schweika.chatapplication.Models.Token;

public abstract class RXSecuredRepository extends RXRepository
{
    protected Token token;

    public RXSecuredRepository(Token token)
    {
        this.token = token;
    }

    protected String getTokenHeader()
    {
        return token.type + " " + token.token;
    }
}
