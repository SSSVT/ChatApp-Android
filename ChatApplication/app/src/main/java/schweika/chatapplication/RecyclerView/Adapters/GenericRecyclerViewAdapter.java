package schweika.chatapplication.RecyclerView.Adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;

public class GenericRecyclerViewAdapter<T> extends RecyclerView.Adapter<GenericRecyclerViewAdapter.ViewHolder> {

    private int layoutId;
    private ArrayList<T> list;

    public GenericRecyclerViewAdapter(ArrayList<T> list, int layoutId)
    {
        this.list = list;
        this.layoutId = layoutId;
    }

    public static class ViewHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {
        private V binding;

        public ViewHolder(V v)
        {
            super(v.getRoot());
            this.binding = v;
        }

        public V getBinding() {
            return binding;
        }
    }

    public void setList(ArrayList<T> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }

    public void removeItem(T item)
    {
        this.list.remove(item);
        notifyDataSetChanged();
    }

    public void addItem(T item)
    {
        this.list.add(item);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ViewDataBinding bind = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
        return new ViewHolder<>(bind);
    }

    @Override
    public int getItemViewType(int position)
    {
        return layoutId;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        T model = list.get(position);

        holder.getBinding().setVariable(BR.viewModel, model);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}