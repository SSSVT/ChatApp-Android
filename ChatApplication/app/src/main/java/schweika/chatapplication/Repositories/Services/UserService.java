package schweika.chatapplication.Repositories.Services;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.User;

public interface UserService
{
    String ENDPOINT = "http://192.168.1.201:51212/api/v1/";

    @GET("Users/GetCurrentUser")
    Call<User> getCurrentUser(@Header("Authorization") String token);
}
