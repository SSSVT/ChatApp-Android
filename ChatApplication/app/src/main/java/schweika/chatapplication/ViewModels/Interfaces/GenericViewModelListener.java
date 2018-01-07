package schweika.chatapplication.ViewModels.Interfaces;

public interface GenericViewModelListener<T>
{
    public void onActionFailure(String message);
    public void onActionSuccess(T item);
}
