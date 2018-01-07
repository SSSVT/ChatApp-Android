package schweika.chatapplication.Repositories;

import schweika.chatapplication.Models.API.Token;

public class RXMessageRepository extends RXSecuredRepository
{
    public RXMessageRepository(Token token)
    {
        super(token);
    }
}
