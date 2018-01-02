package schweika.chatapplication.ViewModels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schweika.chatapplication.Models.API.Participant;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Repositories.ParticipantRepository;
import schweika.chatapplication.Repositories.RoomRepository;
import schweika.chatapplication.TokenSingleton;

public class CreateRoomViewModel
{
    public Room room = new Room();
    private RoomRepository repository = new RoomRepository(TokenSingleton.getInstance().getToken());
    private ParticipantRepository participantRepository = new ParticipantRepository(TokenSingleton.getInstance().getToken());


    ViewModelListener listener;

    public CreateRoomViewModel(ViewModelListener listener)
    {
        room.idOwner = TokenSingleton.getInstance().getUser().id;
        this.listener = listener;
    }

    public void create()
    {
        repository.create(room, new Callback<Room>()
        {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response)
            {
                if (response.isSuccessful())
                {
                    Participant participant = new Participant();

                    participant.idRoom = response.body().id;
                    participant.idUser = TokenSingleton.getInstance().getUser().id;

                    participantRepository.addParticipant(participant, new Callback<Void>()
                    {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response)
                        {
                            if (response.isSuccessful())
                            {
                                listener.onActionSuccess();
                            }
                            else
                            {
                                listener.onActionFailure();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t)
                        {
                            listener.onActionFailure();
                        }
                    });

                }
                else
                {
                    listener.onActionFailure();
                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t)
            {
                listener.onActionFailure();
            }
        });


    }
}
