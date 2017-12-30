package schweika.chatapplication.Repositories.Services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import schweika.chatapplication.Models.API.User;

public interface UserService
{
    @GET("Users/GetCurrentUser")
    Call<User> getCurrentUser(@Header("Authorization") String token);

    @GET("Users/")
    Call<User> getUser(@Header("Authorization") String token);
}
