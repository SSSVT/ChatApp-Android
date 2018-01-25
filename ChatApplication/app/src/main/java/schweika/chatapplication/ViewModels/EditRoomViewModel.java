package schweika.chatapplication.ViewModels;

import android.provider.ContactsContract;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import schweika.chatapplication.DataContext;
import schweika.chatapplication.Models.API.Participant;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Repositories.RXParticipantRepository;
import schweika.chatapplication.Repositories.RXRoomRepository;
import schweika.chatapplication.SelectableRecyclerViewAdapter;
import schweika.chatapplication.ViewModels.Interfaces.ViewModelListener;

public class EditRoomViewModel
{
    public SelectableRecyclerViewAdapter<RoomFriendViewModel> adapter;
    public Room room;

    private List<Participant> participants;

    RXParticipantRepository rxParticipantRepository = new RXParticipantRepository(DataContext.getInstance().getToken());
    RXRoomRepository rxRoomRepository = new RXRoomRepository(DataContext.getInstance().getToken());

    public ViewModelListener listener;

    public EditRoomViewModel(SelectableRecyclerViewAdapter<RoomFriendViewModel> adapter, Room room, ViewModelListener listener)
    {
        this.adapter = adapter;
        this.room = room;
        this.listener = listener;
    }

    public void initialize()
    {
        rxParticipantRepository.getByRoomId(room.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(participants1 ->
                {
                    this.participants = participants1;
                }, throwable ->
                {

                });
    }
}
