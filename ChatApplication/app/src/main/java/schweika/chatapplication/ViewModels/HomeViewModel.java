package schweika.chatapplication.ViewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schweika.chatapplication.Models.API.Friendship;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Repositories.RoomRepository;
import schweika.chatapplication.TokenSingleton;
import schweika.chatapplication.Repositories.UserRepository;

public class HomeViewModel extends ViewModel
{

    public MutableLiveData<ArrayList<Room>> rooms = new MutableLiveData<>();
    public ArrayList<User> friends = new ArrayList<>();
    public ArrayList<Friendship> friendRequests = new ArrayList<>();

    private Token token = TokenSingleton.getInstance().getToken();
    public User currentUser = TokenSingleton.getInstance().getUser();

    private RoomRepository roomRepository = new RoomRepository(token);
    private UserRepository userRepository = new UserRepository(token);

    public HomeViewModel()
    {
        rooms.setValue(new ArrayList<>());

        updateRooms();
    }

    public void updateRooms()
    {
        roomRepository.findByUserID(currentUser.id,new Callback<List<Room>>()
        {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response)
            {
                if (response.isSuccessful())
                {
                    rooms.setValue(new ArrayList<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t)
            {

            }
        });
    }
}
