package schweika.chatapplication.ViewModels.Interfaces;

public interface SelectableViewModelListener<T>
{
    public void onSelectedChange(boolean value, T item);
}
