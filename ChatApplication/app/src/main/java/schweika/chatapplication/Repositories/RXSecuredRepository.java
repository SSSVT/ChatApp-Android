package schweika.chatapplication.Repositories;

import schweika.chatapplication.Models.API.Token;

public abstract class RXSecuredRepository extends RXRepository
{
    protected Token token;
    protected String tokenHeader;

    public RXSecuredRepository(Token token)
    {
        this.token = token;
        this.tokenHeader = token.type + " " + token.token;
    }

    protected String getTokenHeader()
    {
        return this.tokenHeader;
    }
}
