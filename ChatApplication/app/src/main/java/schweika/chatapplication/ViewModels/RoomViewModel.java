package schweika.chatapplication.ViewModels;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Repositories.RXRoomRepository;
import schweika.chatapplication.TokenSingleton;
import schweika.chatapplication.ViewModels.Interfaces.RoomViewModelListener;

public class RoomViewModel
{
    public Room room;
    private RoomViewModelListener listener;
    private RXRoomRepository rxRoomRepository = new RXRoomRepository(TokenSingleton.getInstance().getToken());

    public RoomViewModel(Room room, RoomViewModelListener listener)
    {
        this.room = room;
        this.listener = listener;
    }

    public void remove()
    {
        rxRoomRepository.delete(room.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() ->
                {
                    listener.onRemoveRoom(this);
                });
    }

    public boolean isOwner()
    {
        if (TokenSingleton.getInstance().getUser().id == room.idOwner)
            return true;
        else
            return false;
    }

    public void edit()
    {
        listener.onEditRoom(this);
    }

    public void enter()
    {
        listener.onEnterRoom(this);
    }
}
