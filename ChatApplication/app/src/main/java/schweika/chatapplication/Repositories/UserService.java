package schweika.chatapplication.Repositories;

import retrofit2.Call;
import retrofit2.http.GET;
import schweika.chatapplication.Models.User;

public interface UserService
{
    String ENDPOINT = "http://192.168.1.201:60141/api/v1";

    @GET("users")
    Call<User> add(User user);
}
