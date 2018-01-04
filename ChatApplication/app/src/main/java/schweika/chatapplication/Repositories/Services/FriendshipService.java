package schweika.chatapplication.Repositories.Services;

import java.util.List;
import java.util.Observable;
import java.util.UUID;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import schweika.chatapplication.Models.API.Friendship;

public interface FriendshipService
{
    @POST("Friendships/PostFriendship")
    Call<Friendship> createFriendship(@Header("Authorization") String token, @Body Friendship friendship);

    @PUT("Friendships/PutFriendship/{id}")
    Call<Friendship> editFriendship(@Header("Authorization") String token,@Path("id") UUID uuid, @Body Friendship friendship);

    @DELETE("Friendships/DeleteFriendship/{id}")
    Call<Friendship> deleteFriendship(@Header("Authorization") String token,@Path("id") UUID uuid);

    @GET("Friendships/GetByUserID/{id}")
    Call<List<Friendship>> findByUserID(@Header("Authorization") String token,@Path("id") long id);

    //@GET("Friendships/GetByUserID/{id}")
    //io.reactivex.Observable<List<Friendship>> findByUserID(@Header("Authorization") String token, @Path("id") long id);
}
