package schweika.chatapplication.Repositories;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
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

    public Observable<List<Participant>> getByRoomId(long id)
    {
        return client.getParticipantsByRoomID(getTokenHeader(),id);
    }
}
