package schweika.chatapplication.Views.Register;

import schweika.chatapplication.Models.User;

/**
 * Created by patri on 17.12.2017.
 */

public interface RegisterContract
{
    interface View
    {
        void registerFailed();

        void registerSuccess();
    }

    interface Presenter
    {
        void register(User user);
    }
}
