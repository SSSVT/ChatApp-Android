package schweika.chatapplication.ViewModels.Interfaces;

import schweika.chatapplication.ViewModels.RoomViewModel;

public interface RoomViewModelListener
{
    public void onEnterRoom(RoomViewModel roomViewModel);

    public void onRemoveRoom(RoomViewModel roomViewModel);

    public void onEditRoom(RoomViewModel roomViewModel);
}
