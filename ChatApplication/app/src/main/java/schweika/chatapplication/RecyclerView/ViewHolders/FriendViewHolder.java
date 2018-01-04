package schweika.chatapplication.RecyclerView.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.RecyclerView.ViewModels.FriendViewModel;
import schweika.chatapplication.databinding.RecyclerViewFriendBinding;
import schweika.chatapplication.databinding.RecyclerViewRoomBinding;

public class FriendViewHolder extends RecyclerView.ViewHolder
{
    private RecyclerViewFriendBinding binding;

    public FriendViewHolder(RecyclerViewFriendBinding binding)
    {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(FriendViewModel model)
    {
        this.binding.setViewModel(model);
    }

    public RecyclerViewFriendBinding getDataBinding()
    {
        return binding;
    }
}
