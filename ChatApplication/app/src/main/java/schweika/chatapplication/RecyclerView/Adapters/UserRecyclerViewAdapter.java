package schweika.chatapplication.RecyclerView.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import schweika.chatapplication.RecyclerView.ViewHolders.UserViewHolder;
import schweika.chatapplication.RecyclerView.ViewModels.UserViewModel;
import schweika.chatapplication.databinding.RecyclerViewUserBinding;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserViewHolder>
{
    private Context context;
    private ArrayList<UserViewModel> models;
    private LayoutInflater inflater;

    public UserRecyclerViewAdapter(Context context, ArrayList<UserViewModel> models)
    {
        this.context = context;
        this.models = models;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (inflater == null)
        {
            inflater = LayoutInflater.from(parent.getContext());
        }

        RecyclerViewUserBinding binding = RecyclerViewUserBinding.inflate(inflater,parent,false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position)
    {
        UserViewModel model = models.get(position);
        holder.bind(model);

        //final RecyclerViewUserBinding binding = holder.getDataBinding();
    }

    @Override
    public int getItemCount()
    {
        return models.size();
    }
}
