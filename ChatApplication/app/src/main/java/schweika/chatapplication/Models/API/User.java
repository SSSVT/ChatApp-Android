package schweika.chatapplication.Models.API;
//import com.google.gson.annotations.SerializedName;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class User
{
    @SerializedName("id")
    private long id;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("middleName")
    private String middleName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("birthdate")
    //private Calendar birthDate;
    private Date birthDate;

    @SerializedName("gender")
    private char gender;
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /*public Calendar getBirthDate()
    {
        return birthDate;
    }/*

    /*public void setBirthDate(Calendar birthDate)
    {
        this.birthDate = birthDate;
    }*/

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public char getGender()
    {
        return gender;
    }

    public void setGender(char gender)
    {
        if (gender == 'M' || gender == 'F')
            this.gender = gender;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
