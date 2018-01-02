package schweika.chatapplication.Repositories.Services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import schweika.chatapplication.Models.API.Friendship;

public interface FriendshipService
{
    @POST("Friendships/PostFriendship")
    Call<Friendship> createFriendship(@Header("Authorization") String token, @Body Friendship friendship);
}
