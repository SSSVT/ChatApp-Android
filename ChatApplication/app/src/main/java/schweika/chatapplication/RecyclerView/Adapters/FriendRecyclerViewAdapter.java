package schweika.chatapplication.RecyclerView.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.RecyclerView.ViewHolders.FriendViewHolder;
import schweika.chatapplication.RecyclerView.ViewHolders.RoomViewHolder;
import schweika.chatapplication.RecyclerView.ViewModels.FriendViewModel;
import schweika.chatapplication.databinding.RecyclerViewFriendBinding;
import schweika.chatapplication.databinding.RecyclerViewRoomBinding;


public class FriendRecyclerViewAdapter extends RecyclerView.Adapter<FriendViewHolder>
{
    private ArrayList<FriendViewModel> models;
    private LayoutInflater inflater;

    public FriendRecyclerViewAdapter(ArrayList<FriendViewModel> models)
    {
        this.models = models;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (inflater == null)
        {
            inflater = LayoutInflater.from(parent.getContext());
        }

        RecyclerViewFriendBinding binding = RecyclerViewFriendBinding.inflate(inflater,parent,false);
        return new FriendViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position)
    {
        FriendViewModel model = models.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount()
    {
        return models.size();
    }
}
