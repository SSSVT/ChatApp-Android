package schweika.chatapplication.RecyclerView.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.RecyclerView.ViewHolders.RoomViewHolder;
import schweika.chatapplication.RecyclerView.ViewHolders.UserViewHolder;
import schweika.chatapplication.RecyclerView.ViewModels.UserViewModel;
import schweika.chatapplication.databinding.RecyclerViewRoomBinding;
import schweika.chatapplication.databinding.RecyclerViewUserBinding;

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RoomViewHolder>
{
    private ArrayList<Room> models;
    private LayoutInflater inflater;

    public RoomRecyclerViewAdapter(ArrayList<Room> models)
    {
        this.models = models;
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (inflater == null)
        {
            inflater = LayoutInflater.from(parent.getContext());
        }

        RecyclerViewRoomBinding binding = RecyclerViewRoomBinding.inflate(inflater,parent,false);
        return new RoomViewHolder(binding);
    }

    public void setModels(ArrayList<Room> models)
    {
        this.models = models;
    }

    @Override
    public void onBindViewHolder(RoomViewHolder holder, int position)
    {
        Room model = models.get(position);
        holder.bind(model);

        //final RecyclerViewUserBinding binding = holder.getDataBinding();
    }

    @Override
    public int getItemCount()
    {
        return models.size();
    }
}
