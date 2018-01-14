package schweika.chatapplication.ViewModels;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import schweika.chatapplication.Models.API.Participant;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Repositories.RXParticipantRepository;
import schweika.chatapplication.Repositories.RXRoomRepository;
import schweika.chatapplication.DataContext;
import schweika.chatapplication.ViewModels.Interfaces.SelectableViewModelListener;
import schweika.chatapplication.ViewModels.Interfaces.ViewModelListener;

public class CreateRoomViewModel implements SelectableViewModelListener<RoomFriendViewModel>
{
    public Room room;
    private RXRoomRepository repository = new RXRoomRepository(DataContext.getInstance().getToken());
    RXParticipantRepository rxParticipantRepository = new RXParticipantRepository(DataContext.getInstance().getToken());
    private ArrayList<User> selectedUsers = new ArrayList<>();

    ViewModelListener listener;

    public CreateRoomViewModel(ViewModelListener listener)
    {
        room = new Room();
        room.idOwner = DataContext.getInstance().getUser().id;
        this.listener = listener;
    }

    public CreateRoomViewModel(Room room, ViewModelListener listener)
    {
        this.room = room;
        this.listener = listener;
        room.idOwner = DataContext.getInstance().getUser().id;
    }

    public void create()
    {
        createRoom();
    }

    private void createRoom()
    {
        repository.create(room)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::createParticipants, throwable ->
                {

                });
    }

    private void createParticipants(Room room1)
    {
        if (selectedUsers.size() > 0)
        {
            Observable.just(selectedUsers)
                    .flatMapIterable(users -> users)
                    .flatMapCompletable(user ->
                    {
                        Participant participant = new Participant();
                        participant.idUser = user.id;
                        participant.idRoom = room1.id;

                        return rxParticipantRepository.add(participant);
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() ->
                    {
                        listener.onActionSuccess();
                    }, throwable ->
                    {

                    });


        }
    }

    @Override
    public void onSelectedChange(boolean value, RoomFriendViewModel item)
    {
        if (value)
            selectedUsers.add(item.friend);
        else
            selectedUsers.remove(item.friend);
    }
}
