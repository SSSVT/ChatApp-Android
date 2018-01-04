package schweika.chatapplication.RecyclerView.ViewHolders;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import schweika.chatapplication.RecyclerView.ViewModels.FriendViewModel;
import schweika.chatapplication.databinding.RecyclerViewFriendBinding;

/**
 * Created by patri on 02.01.2018.
 */

public abstract class DataBindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder
{
    private T binding;

    public DataBindingViewHolder(T binding)
    {
        super(binding.getRoot());
        this.binding = binding;
    }

    public abstract void bind(Object model);

    public T getDataBinding()
    {
        return binding;
    }
}
