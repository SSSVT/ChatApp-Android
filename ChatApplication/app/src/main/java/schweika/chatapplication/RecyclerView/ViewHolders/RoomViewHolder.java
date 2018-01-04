package schweika.chatapplication.RecyclerView.ViewHolders;

import android.support.v7.widget.RecyclerView;

import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.databinding.RecyclerViewRoomBinding;

/**
 * Created by patri on 01.01.2018.
 */

public class RoomViewHolder extends RecyclerView.ViewHolder
{
    private RecyclerViewRoomBinding binding;

    public RoomViewHolder(RecyclerViewRoomBinding binding)
    {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Room model)
    {
        this.binding.setViewModel(model);
    }

    public RecyclerViewRoomBinding getDataBinding()
    {
        return binding;
    }
}
