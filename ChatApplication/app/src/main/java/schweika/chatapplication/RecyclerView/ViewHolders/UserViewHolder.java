package schweika.chatapplication.RecyclerView.ViewHolders;

import android.support.v7.widget.RecyclerView;

import schweika.chatapplication.RecyclerView.ViewModels.UserViewModel;
import schweika.chatapplication.databinding.RecyclerViewUserBinding;

/**
 * Created by patri on 30.12.2017.
 */

public class UserViewHolder extends RecyclerView.ViewHolder
{
private RecyclerViewUserBinding binding;

    public UserViewHolder(RecyclerViewUserBinding binding)
    {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(UserViewModel model)
    {
        this.binding.setViewModel(model);
    }

    public RecyclerViewUserBinding getDataBinding()
    {
        return binding;
    }
}
