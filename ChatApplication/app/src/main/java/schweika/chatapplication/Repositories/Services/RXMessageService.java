package schweika.chatapplication.Repositories.Services;

import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import schweika.chatapplication.Models.API.Message;

public interface RXMessageService
{
    @POST("Messages/Create")
    public Completable createMessage(@Header("Authorization") String token, @Body Message message);

    @POST("Messages/GetByRoomID/{id}")
    public Observable<List<Message>> getMessagesByRoomID(@Header("Authorization") String token,@Path("id") long id,@Body Date lastMessageTime);
}
