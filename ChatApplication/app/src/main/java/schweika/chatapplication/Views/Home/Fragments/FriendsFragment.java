package schweika.chatapplication.Views.Home.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.R;
import schweika.chatapplication.GenericRecyclerViewAdapter;
import schweika.chatapplication.ViewModels.FriendViewModel;
import schweika.chatapplication.ViewModels.HomeViewModel;
import schweika.chatapplication.ViewModels.Interfaces.GenericViewModelListener;

public class FriendsFragment extends Fragment implements GenericViewModelListener<FriendViewModel>
{
    private RecyclerView recyclerView;
    private GenericRecyclerViewAdapter<FriendViewModel> adapter;
    private HomeViewModel viewModelWrapper;

    private ArrayList<FriendViewModel> getFriendViewModels(HashMap<Friendship, User> friends)
    {
        ArrayList<FriendViewModel> friendViewModels = new ArrayList<>();

        for (Map.Entry<Friendship, User> map : friends.entrySet())
        {
            FriendViewModel friendViewModel = new FriendViewModel(map.getKey(),map.getValue(),this);
            friendViewModels.add(friendViewModel);
        }

        return  friendViewModels;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        initialize(view);

        viewModelWrapper.updateFriends();

        return view;
    }

    private void openAddFriendFragment()
    {
        FragmentManager manager = getActivity().getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.fragment_content, new AddFriendFragment(),"friends").addToBackStack("friends").commit();
    }

    private void initialize(View view)
    {
        FloatingActionButton button = view.findViewById(R.id.floatingActionButton_addFriend);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openAddFriendFragment();
            }
        });

        if (viewModelWrapper == null)
        {
            viewModelWrapper = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
            viewModelWrapper.friends.observe(this, new Observer<HashMap<Friendship, User>>()
            {
                @Override
                public void onChanged(@Nullable HashMap<Friendship, User> friendshipUserHashMap)
                {
                    adapter.setItems(getFriendViewModels(friendshipUserHashMap));
                }
            });
        }


        if (adapter == null)
        {
            adapter = new GenericRecyclerViewAdapter<>(new ArrayList<FriendViewModel>(),R.layout.recycler_view_friend);
            adapter.setItems(getFriendViewModels(viewModelWrapper.friends.getValue()));

        }

        recyclerView = view.findViewById(R.id.recyclerView_friend);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActionFailure(String message)
    {


    }

    @Override
    public void onActionSuccess(FriendViewModel item)
    {
        adapter.removeItem(item);
        viewModelWrapper.friends.getValue().remove(item.friendship);
    }
}
