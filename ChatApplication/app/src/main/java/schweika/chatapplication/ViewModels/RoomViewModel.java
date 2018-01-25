package schweika.chatapplication.ViewModels;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Repositories.RXRoomRepository;
import schweika.chatapplication.DataContext;
import schweika.chatapplication.ViewModels.Interfaces.RoomViewModelListener;

public class RoomViewModel
{
    public Room room;
    private RoomViewModelListener listener;
    private RXRoomRepository rxRoomRepository = new RXRoomRepository(DataContext.getInstance().getToken());

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
                },error ->
                {

                });
    }

    public boolean isOwner()
    {
        if (DataContext.getInstance().getUser().id == room.idOwner)
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

    public void leave()
    {
        rxRoomRepository.leaveRoom(room.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() ->
                {
                    listener.onRemoveRoom(this);
                },error ->
                {

                });
    }

    public boolean getDescriptionDisplay()
    {
        if (room.description == null)
            return false;
        else if (room.description.equals(""))
            return false;
        else
            return true;
    }
}
