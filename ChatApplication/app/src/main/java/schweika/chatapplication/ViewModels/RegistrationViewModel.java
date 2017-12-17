package schweika.chatapplication.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

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

    public View.OnClickListener onRegisterClicked()
    {
        return new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Toast.makeText(view.getContext(), "Registered", Toast.LENGTH_SHORT).show();
            }
        };

    }

    public void setGenderFemale()
    {
        user.setGender('F');
    }

    public void setGenderMale()
    {
        user.setGender('M');
    }
}
