package schweika.chatapplication.ViewModels;

import android.arch.lifecycle.ViewModel;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.Calendar;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schweika.chatapplication.BR;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.UserValidator;
import schweika.chatapplication.Repositories.AuthenticationRepository;
import schweika.chatapplication.Views.Register.RegisterListener;

public class RegisterViewModel extends BaseObservable
{
    private User user;
    private UserValidator validator;
    private AuthenticationRepository repository = new AuthenticationRepository();
    //private FutureTokenRepository futureTokenRepository = new FutureTokenRepository();
    private Boolean processingState = false;
    private RegisterListener listener;

    //private Timer usernameTimer = new Timer();

    private Calendar userBirthDate = Calendar.getInstance();

    public RegisterViewModel(RegisterListener listener)
    {
        this.listener = listener;
        this.user = new User();

        validator = new UserValidator(user);

        userBirthDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        userBirthDate.set(Calendar.HOUR,0);
        userBirthDate.set(Calendar.MINUTE,0);
        userBirthDate.set(Calendar.SECOND,0);
        userBirthDate.set(Calendar.MILLISECOND,0);
    }

    public RegisterViewModel(RegisterListener listener, User user)
    {
        this.listener = listener;
        this.user = user;

        validator = new UserValidator(user);
    }

    public User getUser()
    {
        return user;
    }

    @Bindable
    public boolean getProcessingState()
    {
        //return processingState ? View.VISIBLE : View.GONE;
        return this.processingState;
    }

    private void setProcessingState(boolean value)
    {
        this.processingState = value;
        notifyPropertyChanged(BR.processingState);
    }

    public void setEmail(String email)
    {
        user.email = email;
        notifyPropertyChanged(BR.email);
        notifyPropertyChanged(BR.emailError);
    }

    @Bindable
    public String getEmail()
    {
        return user.email;
    }

    @Bindable
    public String getEmailError()
    {
        return validator.getEmailError();
    }

    public void setUsername(String username)
    {
        user.username = username;

        if (validator.isUsernameValid())
        {
            getUsernameAvailability();
        }

        validator.setUsernameAvailable(true);
        notifyPropertyChanged(BR.username);
        notifyPropertyChanged(BR.usernameError);
    }

    @Bindable
    public String getUsername()
    {
        return user.username;
    }

    @Bindable
    public String getUsernameError()
    {
        return validator.getUsernameError();
    }

    private void getUsernameAvailability()
    {
        /*CompletableFuture<Response<Boolean>> responseFuture = CompletableFuture.supplyAsync(() -> futureTokenRepository.isUsernameAvailable(user.getUsername()));

        responseFuture.thenAccept(this::updateUsernameAvailability);*/

        /*responseFuture.thenAccept(response ->
        {
            if (response.body())
            {
                validator.setUsernameAvailable(true);
            }
            else
            {
                validator.setUsernameAvailable(false);
            }

            notifyPropertyChanged(BR.usernameError);
        });*/

        /*Future<Response<Boolean>> responseFuture =  futureTokenRepository.isUsernameAvailableAsync(user.getUsername());

        try
        {
            Response<Boolean> response = responseFuture.get();

            if (response.body())
            {
                validator.setUsernameAvailable(true);
            }
            else
            {
                validator.setUsernameAvailable(false);
            }

            notifyPropertyChanged(BR.usernameError);
        }
        catch (Exception e)
        {
        }*/

        repository.isUsernameAvailable(user.username, new Callback<Boolean>()
        {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response)
            {
                if (response.body())
                {
                    validator.setUsernameAvailable(true);
                }
                else
                {
                    validator.setUsernameAvailable(false);
                }

                notifyPropertyChanged(BR.usernameError);
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t)
            {

            }

        });
    }

    private void updateUsernameAvailability(Response<Boolean> response)
    {
        if (response.body())
        {
            validator.setUsernameAvailable(true);
        }
        else
        {
            validator.setUsernameAvailable(false);
        }

        notifyPropertyChanged(BR.usernameError);
    }

    public void setPassword(String password)
    {
        user.password = password;
        notifyPropertyChanged(BR.password);
        notifyPropertyChanged(BR.passwordError);
    }

    @Bindable
    public String getPassword()
    {
        return user.password;
    }

    @Bindable
    public String getPasswordError()
    {
        return validator.getPasswordError();
    }

    public void setFirstName(String firstName)
    {
        this.user.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
        notifyPropertyChanged(BR.firstNameError);
    }


    @Bindable
    public String getFirstName()
    {
        return user.firstName;
    }

    @Bindable
    public String getFirstNameError()
    {
        return validator.getFirstNameError();
    }

    public void setMiddleName(String middleName)
    {
        user.middleName = middleName;
        notifyPropertyChanged(BR.middleName);
        notifyPropertyChanged(BR.middleNameError);
    }

    @Bindable
    public String getMiddleName()
    {
        return user.middleName;
    }

    @Bindable
    public String getMiddleNameError()
    {
        return validator.getMiddleNameError();
    }


    public void setLastName(String lastName)
    {
        user.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
        notifyPropertyChanged(BR.lastNameError);
    }

    @Bindable
    public String getLastName()
    {
        return user.lastName;
    }

    @Bindable
    public String getLastNameError()
    {
        return validator.getLastNameError();
    }

    public void setGenderFemale()
    {
        user.gender = 'F';
    }

    public void setGenderMale()
    {
        user.gender = 'M';
    }

    public void setBirthDay(int day)
    {
        userBirthDate.set(Calendar.DAY_OF_MONTH, day);
        notifyPropertyChanged(BR.birthDay);
    }

    @Bindable
    public int getBirthDay()
    {
        return userBirthDate.get(Calendar.DAY_OF_MONTH);
    }

    public void setBirthMonth(int month)
    {
        userBirthDate.set(Calendar.MONTH, month);
        notifyPropertyChanged(BR.birthMonth);
    }

    @Bindable
    public int getBirthMonth()
    {
        return userBirthDate.get(Calendar.MONTH);
    }

    public void setBirthYear(int year)
    {
        userBirthDate.set(Calendar.YEAR, year);
        notifyPropertyChanged(BR.birthYear);
    }

    @Bindable
    public int getBirthYear()
    {
        return userBirthDate.get(Calendar.YEAR);
    }

    public void onRegisterClicked()
    {
        user.birthDate = userBirthDate.getTime();

        if (validator.isUserValid())
        {
            setProcessingState(true);

            /*CompletableFuture<Response<Void>> future = CompletableFuture.supplyAsync(() -> futureTokenRepository.register(user));

            future.thenAccept(this::successfullyRegistered);*/

            repository.register(user, new Callback<Void>()
            {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response)
                {
                    if (response.isSuccessful())
                    {
                        listener.registered();
                    }

                    setProcessingState(false);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t)
                {
                    setProcessingState(false);
                }
            });
        }
    }

    private void successfullyRegistered(Response<Void> response)
    {
        setProcessingState(false);

        if (response.isSuccessful())
        {
            listener.registered();
        }
    }
}
