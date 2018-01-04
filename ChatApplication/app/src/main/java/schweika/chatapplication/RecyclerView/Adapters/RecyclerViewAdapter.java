package schweika.chatapplication.RecyclerView.Adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.RecyclerView.ViewHolders.DataBindingViewHolder;
import schweika.chatapplication.RecyclerView.ViewHolders.RoomViewHolder;
import schweika.chatapplication.databinding.RecyclerViewRoomBinding;

public abstract class RecyclerViewAdapter<T,V extends ViewDataBinding> extends RecyclerView.Adapter<DataBindingViewHolder>
{
    /*private ArrayList<T> models;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(ArrayList<T> models)
    {
        this.models = models;
    }

    @Override
    public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (inflater == null)
        {
            inflater = LayoutInflater.from(parent.getContext());
        }
    }

    public void setModels(ArrayList<T> models)
    {
        this.models = models;
    }

    @Override
    public void onBindViewHolder(DataBindingViewHolder holder, int position)
    {
        T model = models.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount()
    {
        return models.size();
    }*/
}
