package schweika.chatapplication.Repositories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import schweika.chatapplication.Models.User;

public interface UserService
{
    String ENDPOINT = "http://192.168.1.201:60141/api/v1";

    @POST("users")
    Call<User> add(User user);

    @GET("")
    Call<Boolean> isUsernameTaken(String username);

    @GET("")
    Call<User> login(String username, String Password);


}
