package schweika.chatapplication.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import javax.xml.validation.Validator;

import schweika.chatapplication.Models.User;

public class RegistrationViewModel extends BaseObservable
{
    private User user;

    public RegistrationViewModel(User user)
    {
        this.user = user;
    }

    public RegistrationViewModel()
    {
        this.user = new User();
    }

    public User getUser()
    {
        return user;
    }

    @Bindable
    public String getFirstNameError()
    {
        if (user.getFirstName() == "")
        {
            return "First name can't be empty";
        }

        return null;
    }

    public void register()
    {
        Log.i("Success","lul");
    }

    public void setGenderFemale()
    {
        user.setGender('F');
        Log.i("Success","Female");
    }

    public void setGenderMale()
    {
        user.setGender('M');
        Log.i("Success","Male");
    }
}
