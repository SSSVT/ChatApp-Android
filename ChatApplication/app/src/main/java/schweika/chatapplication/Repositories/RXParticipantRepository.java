package schweika.chatapplication.Repositories;

import io.reactivex.Completable;
import schweika.chatapplication.Models.API.Participant;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Repositories.Services.RXParticipantService;

public class RXParticipantRepository extends RXSecuredRepository
{
    RXParticipantService client = retrofit.create(RXParticipantService.class);

    public RXParticipantRepository(Token token)
    {
        super(token);
    }

    public Completable add(Participant participant)
    {
        return client.addParticipant(getTokenHeader(),participant);
    }
}
