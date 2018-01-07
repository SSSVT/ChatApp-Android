package schweika.chatapplication.ViewModels;

import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.ViewModels.Abstract.SelectableViewModel;
import schweika.chatapplication.ViewModels.Interfaces.SelectableViewModelListener;

public class RoomFriendViewModel extends SelectableViewModel
{
    public User friend;
    private SelectableViewModelListener<RoomFriendViewModel> listener;

    public RoomFriendViewModel(User friend, SelectableViewModelListener<RoomFriendViewModel> listener)
    {
        this.friend = friend;
        this.listener = listener;
    }

    @Override
    public void toggleSelected()
    {
        super.toggleSelected();
        listener.onSelectedChange(this.selected,this);
    }

    @Override
    public void setSelected(boolean value)
    {
        if (this.selected != value)
            this.listener.onSelectedChange(value, this);

        super.setSelected(value);


    }
}
