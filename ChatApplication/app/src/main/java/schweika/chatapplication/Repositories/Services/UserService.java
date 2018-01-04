package schweika.chatapplication.Repositories.Services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import schweika.chatapplication.Models.API.User;

public interface UserService
{
    @GET("Users/GetCurrentUser")
    Call<User> getCurrentUser(@Header("Authorization") String token);

    @GET("Users/FindByUsername")
    Call<User> findByUsername(@Header("Authorization") String token,@Query("id") String username);

    @GET("Users/Detail")
    Call<User> findByID(@Header("Authorization") String token,@Query("id") long id);
}
