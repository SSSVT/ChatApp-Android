package schweika.chatapplication.Views.Register;

import schweika.chatapplication.Models.User;

/**
 * Created by patri on 17.12.2017.
 */

public class RegisterPresenter implements RegisterContract.Presenter
{
    private RegisterContract.View view;

    public RegisterPresenter(RegisterContract.View view)
    {
        this.view = view;
    }

    @Override
    public void register(User user)
    {

    }
}
