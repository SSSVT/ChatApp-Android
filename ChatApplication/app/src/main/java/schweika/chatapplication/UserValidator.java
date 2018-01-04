package schweika.chatapplication;

import java.util.regex.Pattern;

import schweika.chatapplication.Models.API.User;

public class UserValidator
{
    private final Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private final Pattern namePattern = Pattern.compile("^[\\p{L}\\s'.-]+$");

    private User user;

    public UserValidator(User user)
    {
        this.user = user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    private boolean isUsernameAvailable;

    public boolean isUsernameAvailable()
    {
        return isUsernameAvailable;
    }

    public void setUsernameAvailable(boolean usernameAvailable)
    {
        isUsernameAvailable = usernameAvailable;
    }

    public boolean isUsernameValid()
    {
        if (user.username == null)
            return false;
        else if (user.username.equals(""))
            return  false;
        else if (user.username.length() < 4)
            return false;
        else if (!isUsernameAvailable)
            return false;

        return true;
    }

    public String getUsernameError()
    {
        if (user.username == null)
            return null;
        else if (user.username.equals(""))
            return "You must enter a username.";
        else if (user.username.length() < 4)
            return "You must enter a username with at least 4 characters";
        else if (!isUsernameAvailable)
            return "Username is not available.";

        return null;
    }

    public boolean isEmailValid()
    {


        if (user.email == null)
            return false;
        else if (user.email.equals(""))
            return  false;
        else if (!emailPattern.matcher(user.email).matches())
            return false;



        return true;
    }

    public String getEmailError()
    {
        if (user.email == null)
            return null;
        else if (user.email.equals(""))
            return "You must enter an email.";
        else if (!emailPattern.matcher(user.email).matches())
            return "You must enter a valid email.";

        return null;
    }

    public boolean isPasswordValid()
    {
        if (user.password == null)
            return false;
        else if (user.password.equals(""))
            return  false;
        else if (user.password.length() < 8)
            return false;

        return true;
    }

    public String getPasswordError()
    {
        if (user.password == null)
            return null;
        else if (user.password.equals(""))
            return "You must enter a password.";
        else if (user.password.length() < 8)
            return "Password must have at least 8 characters.";

        return null;
    }

    public boolean isFirstNameValid()
    {
        if (user.firstName == null)
            return false;
        else if (user.firstName.equals(""))
            return  false;
        else if (!namePattern.matcher(user.firstName).matches())
            return false;

        return true;
    }

    public String getFirstNameError()
    {
        if (user.firstName == null)
            return null;
        else if (user.firstName.equals(""))
            return "You must enter a name.";
        else if (!namePattern.matcher(user.firstName).matches())
            return "You must enter a valid name.";

        return null;
    }

    public boolean isMiddleNameValid()
    {
        if (user.middleName == null)
            return true;
        else if (user.middleName.equals(""))
            return true;
        else if (!namePattern.matcher(user.middleName).matches())
            return false;

        return true;
    }

    public String getMiddleNameError()
    {
        if (user.middleName == null)
            return null;
        else if (user.middleName.equals(""))
            return null;
        else if (!namePattern.matcher(user.middleName).matches())
            return "You must enter a valid name.";

        return null;
    }

    public boolean isLastNameValid()
    {
        if (user.lastName == null)
            return false;
        else if (user.lastName.equals(""))
            return  false;
        else if (!namePattern.matcher(user.lastName).matches())
            return false;

        return true;
    }

    public String getLastNameError()
    {
        if (user.lastName == null)
            return null;
        else if (user.lastName.equals(""))
            return "You must enter a name.";
        else if (!namePattern.matcher(user.lastName).matches())
            return "You must enter a valid name.";

        return null;
    }

    public boolean isBirthDateValid()
    {
        if (user.birthDate == null)
            return false;

        return true;
    }

    public String getBirthDateError()
    {
        //TODO: Validation

        return null;
    }

    public boolean isGenderValid()
    {
        if (user.gender == 'M' || user.gender == 'F')
            return true;

        return false;
    }

    public String getGenderError()
    {
        if (user.gender != 'M' && user.gender != 'F')
            return "You must select a gender.";

        return null;
    }

    public boolean isUserValid()
    {
        if (!isUsernameValid())
            return false;
        if (!isEmailValid())
            return false;
        if (!isPasswordValid())
            return false;
        if (!isFirstNameValid())
            return false;
        if (!isMiddleNameValid())
            return false;
        if (!isLastNameValid())
            return false;
        if (!isGenderValid())
            return false;
        if (!isBirthDateValid())
            return false;

        return true;
    }
}
