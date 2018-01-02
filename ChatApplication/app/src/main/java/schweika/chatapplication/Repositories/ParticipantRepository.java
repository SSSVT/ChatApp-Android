package schweika.chatapplication.Repositories;

import retrofit2.Callback;
import schweika.chatapplication.Models.API.Participant;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Repositories.Services.ParticipantService;
import schweika.chatapplication.Repositories.Services.RoomService;

public class ParticipantRepository extends SecuredRepository
{
    ParticipantService client = retrofit.create(ParticipantService.class);

    public ParticipantRepository(Token token)
    {
        super(token);
    }

    public void addParticipant(Participant participant, Callback<Void> callback)
    {
        client.addParticipant(getTokenHeader(),participant).enqueue(callback);
    }
}
