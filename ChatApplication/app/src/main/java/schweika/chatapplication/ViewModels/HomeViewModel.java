package schweika.chatapplication.ViewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schweika.chatapplication.Models.API.Friendship;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.RecyclerView.Adapters.FriendRecyclerViewAdapter;
import schweika.chatapplication.RecyclerView.ViewModels.FriendViewModel;
import schweika.chatapplication.Repositories.FriendshipRepository;
import schweika.chatapplication.Repositories.RXFriendshipRepository;
import schweika.chatapplication.Repositories.RXUserRepository;
import schweika.chatapplication.Repositories.RoomRepository;
import schweika.chatapplication.TokenSingleton;
import schweika.chatapplication.Repositories.UserRepository;

public class HomeViewModel extends ViewModel
{

    public MutableLiveData<ArrayList<Room>> rooms = new MutableLiveData<>();
    //public MutableLiveData<ArrayList<FriendViewModel>> friends = new MutableLiveData<>();
    public MutableLiveData<HashMap<Friendship,User>> friends = new MutableLiveData<>();

    private Token token = TokenSingleton.getInstance().getToken();
    public User currentUser = TokenSingleton.getInstance().getUser();

    private RoomRepository roomRepository = new RoomRepository(token);
    //private UserRepository userRepository = new UserRepository(token);
    //private FriendshipRepository friendshipRepository = new FriendshipRepository(token);
    private RXFriendshipRepository rxFriendshipRepository = new RXFriendshipRepository(token);
    private RXUserRepository rxUserRepository = new RXUserRepository(token);

    public HomeViewModel()
    {
        rooms.setValue(new ArrayList<>());
        friends.setValue(new HashMap<>());

        updateRooms();
        updateFriends();
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

    private long getOtherUserId(Friendship friendship)
    {
        if (friendship.idSender != currentUser.id)
        {
            return friendship.idSender;
        }
        else
        {
            return friendship.idRecipient;
        }
    }

    public void updateFriends()
    {
        HashMap<Friendship, User> friendshipUserHashMap = new HashMap<>();

        rxFriendshipRepository.findByUserID(currentUser.id)
                .flatMapIterable(friendships -> friendships)
                .flatMap(friendship -> rxUserRepository.findById(getOtherUserId(friendship)).toObservable(),(friendship,user) -> new Pair<Friendship,User>(friendship,user))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pairs -> {

                    for (Pair<Friendship,User> pair : pairs)
                    {
                        friendshipUserHashMap.put(pair.first,pair.second);
                    }

                    friends.setValue(friendshipUserHashMap);
                });

        /*rxFriendshipRepository.findByUserID(currentUser.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> friends.setValue(friendshipUserHashMap))
                .subscribe(friendships ->
                {
                    for (Friendship friendship : friendships)
                    {
                        rxUserRepository.findById(getOtherUserId(friendship))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe( user ->
                                {
                                    friendshipUserHashMap.put(friendship,user);
                                });
                    }
                });*/




        /*rxFriendshipRepository.findByUserID(currentUser.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable(friendships -> friendships)
                .flatMap(friendship -> rxUserRepository.findById(getOtherUserId(friendship))
                        .doAfterSuccess(user -> friendshipUserHashMap.put(friendship,user))

                );*/

        /*rxFriendshipRepository.findByUserID(currentUser.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable(friendships -> friendships)
                .flatMapSingle(
                    friendship -> rxUserRepository.findById(getOtherUserId(friendship))
                        .doAfterSuccess(user ->
                        {
                            friendshipUserHashMap.put(friendship,user);
                        })
                )
                .doOnComplete(() ->
                {
                    friends.setValue(friendshipUserHashMap);
                });*/

        /*rxFriendshipRepository.findByUserID(currentUser.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable(friendships -> friendships)
                .flatMap(friendship -> rxUserRepository.findById(getOtherUserId(friendship)).toObservable()
                        .doOnNext(
                                user -> { friendshipUserHashMap.put(friendship,user);}),
                        ((friendship, user) -> {return new Pair<Friendship,User>(friendship,user);}))
                .doOnTerminate(() -> friends.setValue(friendshipUserHashMap))
                .subscribe(new DisposableObserver<Pair<Friendship, User>>()
                {
                    @Override
                    public void onNext(Pair<Friendship, User> friendshipUserPair)
                    {
                        friendshipUserHashMap.put(friendshipUserPair.first,friendshipUserPair.second);
                    }

                    @Override
                    public void onError(Throwable e)
                    {

                    }

                    @Override
                    public void onComplete()
                    {
                        friends.setValue(friendshipUserHashMap);
                    }
                });*/





        /*friendshipRepository.findByUserID(currentUser.id, new Callback<List<Friendship>>()
        {
            @Override
            public void onResponse(Call<List<Friendship>> call, Response<List<Friendship>> response)
            {
                if (response.isSuccessful())
                {
                    friends.setValue(new HashMap<>());

                    for (Friendship friendship : response.body())
                    {
                        long otherUserId;

                        if (friendship.idSender != currentUser.id)
                        {
                            otherUserId = friendship.idSender;
                        }
                        else
                        {
                            otherUserId = friendship.idRecipient;
                        }



                        userRepository.findByID(otherUserId, new Callback<User>()
                        {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response)
                            {
                                if (response.isSuccessful())
                                {
                                    User user = response.body();
                                    //FriendViewModel viewModel = new FriendViewModel(friendship, response.body());
                                    //friends.getValue().add(viewModel);
                                    friends.getValue().put(friendship,user);
                                    //friendViewModels.add(viewModel);
                                }
                            }

                            @Override public void onFailure(Call<User> call, Throwable t) {}
                        });
                    }

                    //friends.setValue(friendsUsers);
                }
            }

            @Override public void onFailure(Call<List<Friendship>> call, Throwable t) {}
        });*/
    }
}
