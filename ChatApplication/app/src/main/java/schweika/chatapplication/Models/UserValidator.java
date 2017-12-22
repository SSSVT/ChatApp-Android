package schweika.chatapplication.Models;

public class UserValidator
{
    private User user;

    public UserValidator(User user)
    {
        this.user = user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public boolean isUsernameValid()
    {
        if (user.getUsername() == null)
            return false;
        else if (user.getUsername().equals(""))
            return  false;

        return true;
    }

    public String getUsernameError()
    {
        if (user.getUsername() == null)
            return null;
        else if (user.getUsername().equals(""))
            return "You must enter a username.";

        return null;
    }

    public boolean isEmailValid()
    {
        if (user.getEmail() == null)
            return false;
        else if (user.getEmail().equals(""))
            return  false;

        return true;
    }

    public String getEmailError()
    {
        if (user.getEmail() == null)
            return null;
        else if (user.getEmail().equals(""))
            return "You must enter an email.";

        return null;
    }

    public boolean isPasswordValid()
    {
        if (user.getPassword() == null)
            return false;
        else if (user.getPassword().equals(""))
            return  false;

        return true;
    }

    public String getPasswordError()
    {
        if (user.getPassword() == null)
            return null;
        else if (user.getPassword().equals(""))
            return "You must enter a password.";

        return null;
    }

    public boolean isFirstNameValid()
    {
        if (user.getFirstName() == null)
            return false;
        else if (user.getFirstName().equals(""))
            return  false;

        return true;
    }

    public String getFirstNameError()
    {
        if (user.getFirstName() == null)
            return null;
        else if (user.getFirstName().equals(""))
            return "You must enter a first name.";

        return null;
    }

    public boolean isMiddleNameValid()
    {
        //TODO: Validation

        return true;
    }

    public String getMiddleNameError()
    {
        //TODO: Validation

        return null;
    }

    public boolean isLastNameValid()
    {
        if (user.getLastName() == null)
            return false;
        else if (user.getLastName().equals(""))
            return  false;

        return true;
    }

    public String getLastNameError()
    {
        if (user.getLastName() == null)
            return null;
        else if (user.getLastName().equals(""))
            return "You must enter a first name.";

        return null;
    }

    public boolean isBirthDateValid()
    {
        if (user.getBirthDate() == null)
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
        if (user.getGender() == 'M' || user.getGender() == 'F')
            return true;

        return false;
    }

    public String getGenderError()
    {
        if (user.getGender() != 'M' || user.getGender() != 'F')
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
