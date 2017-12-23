package schweika.chatapplication.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

import schweika.chatapplication.BR;
import schweika.chatapplication.Models.User;
import schweika.chatapplication.Models.UserValidator;
import schweika.chatapplication.Repositories.RetrofitCallback;
import schweika.chatapplication.Repositories.UserRepository;

public class RegistrationViewModel extends BaseObservable
{
    private User user;
    private UserValidator validator;
    private UserRepository repository = new UserRepository();

    public RegistrationViewModel()
    {
        this.user = new User();
        user.setBirthDate(new Date());

        validator = new UserValidator(user);
    }

    public void setFirstName(String firstName)
    {
        this.user.setFirstName(firstName);
        notifyPropertyChanged(BR.firstName);
        notifyPropertyChanged(BR.firstNameError);
    }

    @Bindable
    public String getFirstName()
    {
        return user.getFirstName();
    }

    @Bindable
    public String getFirstNameError()
    {
        return validator.getFirstNameError();
    }

    public void setMiddleName(String middleName)
    {
        user.setMiddleName(middleName);
        notifyPropertyChanged(BR.middleName);
        notifyPropertyChanged(BR.middleNameError);
    }



    @Bindable
    public String getMiddleName()
    {
        return user.getMiddleName();
    }

    @Bindable
    public String getMiddleNameError()
    {
        return validator.getMiddleNameError();
    }


    public void setLastName(String lastName)
    {
        user.setLastName(lastName);
        notifyPropertyChanged(BR.lastName);
        notifyPropertyChanged(BR.lastNameError);
    }

    @Bindable
    public String getLastName()
    {
        return user.getLastName();
    }

    @Bindable
    public String getLastNameError()
    {
        return validator.getLastNameError();
    }


    public void setBirthDate(Long date)
    {
        user.setBirthDate(new Date(date));
        notifyPropertyChanged(BR.birthDate);
        notifyPropertyChanged(BR.birthDateError);
    }

    @Bindable
    public Long getBirthDate()
    {
        return user.getBirthDate().getTime();
    }

    @Bindable
    public String getBirthDateError()
    {
        return validator.getBirthDateError();
    }


    public void setEmail(String email)
    {
        user.setEmail(email);
        notifyPropertyChanged(BR.email);
        notifyPropertyChanged(BR.emailError);
    }

    @Bindable
    public String getEmail()
    {
        return user.getEmail();
    }

    @Bindable
    public String getEmailError()
    {
        return validator.getEmailError();
    }

    public void setUsername(String username)
    {
        user.setUsername(username);
        notifyPropertyChanged(BR.username);
        notifyPropertyChanged(BR.usernameError);
    }

    @Bindable
    public String getUsername()
    {
        return user.getUsername();
    }

    @Bindable
    public String getUsernameError()
    {
        return validator.getUsernameError();
    }

    public void setPassword(String password)
    {
        user.setPassword(password);
        notifyPropertyChanged(BR.password);
        notifyPropertyChanged(BR.passwordError);
    }

    @Bindable
    public String getPassword()
    {
        return user.getPassword();
    }

    @Bindable
    public String getPasswordError()
    {
        return validator.getPasswordError();
    }

    public View.OnClickListener onRegisterClicked()
    {
        //TODO: REGISTER

        return new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                if (validator.isUserValid())
                {
                    repository.add(user, new RetrofitCallback()
                    {
                        @Override
                        public void onSuccess()
                        {
                            Toast.makeText(view.getContext(), user.getUsername() + " Registered", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure()
                        {
                            Toast.makeText(view.getContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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
