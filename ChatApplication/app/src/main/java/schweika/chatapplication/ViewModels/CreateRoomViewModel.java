package schweika.chatapplication.ViewModels;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Repositories.RXRoomRepository;
import schweika.chatapplication.TokenSingleton;
import schweika.chatapplication.ViewModels.Interfaces.ViewModelListener;

public class CreateRoomViewModel
{
    public Room room = new Room();
    private RXRoomRepository repository = new RXRoomRepository(TokenSingleton.getInstance().getToken());

    ViewModelListener listener;

    public CreateRoomViewModel(ViewModelListener listener)
    {
        room.idOwner = TokenSingleton.getInstance().getUser().id;
        this.listener = listener;
    }

    public void create()
    {
        repository.create(room)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( room1 ->
                {
                    listener.onActionSuccess();
                }, throwable ->
                {
                    listener.onActionFailure("");
                });
    }
}
