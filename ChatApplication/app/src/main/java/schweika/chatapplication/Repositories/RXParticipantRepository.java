package schweika.chatapplication.Repositories;

import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Repositories.Services.RXParticipantService;

public class RXParticipantRepository extends RXSecuredRepository
{
    RXParticipantService client = retrofit.create(RXParticipantService.class);

    public RXParticipantRepository(Token token)
    {
        super(token);
    }
}
