package schweika.chatapplication.ViewModels.Interfaces;

public interface ViewModelListener
{
    public void onActionFailure(String message);
    public void onActionSuccess();
}
