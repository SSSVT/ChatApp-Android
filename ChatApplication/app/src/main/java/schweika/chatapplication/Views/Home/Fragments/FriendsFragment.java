package schweika.chatapplication.Views.Home.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import schweika.chatapplication.Models.API.Friendship;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.R;
import schweika.chatapplication.RecyclerView.Adapters.GenericRecyclerViewAdapter;
import schweika.chatapplication.RecyclerView.ViewModels.FriendViewModel;
import schweika.chatapplication.ViewModels.HomeViewModel;

public class FriendsFragment extends Fragment
{
    private RecyclerView recyclerView;
    private GenericRecyclerViewAdapter<FriendViewModel> adapter;
    private HomeViewModel viewModelWrapper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModelWrapper = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);

        adapter = new GenericRecyclerViewAdapter<>(new ArrayList<FriendViewModel>(),R.layout.recycler_view_friend);

        adapter.setList(getFriendViewModels(viewModelWrapper.friends.getValue()));

        viewModelWrapper.friends.observe(this, new Observer<HashMap<Friendship, User>>()
        {
            @Override
            public void onChanged(@Nullable HashMap<Friendship, User> friends)
            {
                adapter.setList(getFriendViewModels(friends));
            }
        });
    }

    private ArrayList<FriendViewModel> getFriendViewModels(HashMap<Friendship, User> friends)
    {
        ArrayList<FriendViewModel> friendViewModels = new ArrayList<>();

        for (Map.Entry<Friendship, User> map : friends.entrySet())
        {
            FriendViewModel friendViewModel = new FriendViewModel(map.getKey(),map.getValue(),adapter);
            friendViewModels.add(friendViewModel);
        }

        return  friendViewModels;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        FloatingActionButton button = view.findViewById(R.id.floatingActionButton_addFriend);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openAddFriendFragment();
            }
        });

        viewModelWrapper.updateFriends();

        if (recyclerView == null)
            recyclerView = view.findViewById(R.id.recyclerView_friend);


        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void openAddFriendFragment()
    {
        FragmentManager manager = getActivity().getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.fragment_content, new AddFriendFragment(),"friends").addToBackStack("friends").commit();
    }
}
