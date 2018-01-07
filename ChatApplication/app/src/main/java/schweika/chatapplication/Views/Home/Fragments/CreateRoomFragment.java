package schweika.chatapplication.Views.Home.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import schweika.chatapplication.GenericRecyclerViewAdapter;
import schweika.chatapplication.Models.API.Friendship;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.R;
import schweika.chatapplication.SelectableRecyclerViewAdapter;
import schweika.chatapplication.ViewModels.CreateRoomViewModel;
import schweika.chatapplication.ViewModels.FriendViewModel;
import schweika.chatapplication.ViewModels.HomeViewModel;
import schweika.chatapplication.ViewModels.Interfaces.CreateRoomListener;
import schweika.chatapplication.ViewModels.Interfaces.ViewModelListener;
import schweika.chatapplication.ViewModels.RoomFriendViewModel;
import schweika.chatapplication.databinding.FragmentCreateRoomBinding;

public class CreateRoomFragment extends Fragment implements ViewModelListener
{
    RecyclerView recyclerView;
    CreateRoomViewModel viewModel;
    HomeViewModel viewModelWrapper;
    SelectableRecyclerViewAdapter<RoomFriendViewModel> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        FragmentCreateRoomBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_create_room,container,false);

        View view = binding.getRoot();

        if (viewModelWrapper == null)
        {
            viewModelWrapper = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);

            viewModelWrapper.friends.observe(this, new Observer<HashMap<Friendship, User>>()
            {
                @Override
                public void onChanged(@Nullable HashMap<Friendship, User> friendshipUserHashMap)
                {
                    adapter.setItems(getRoomFriendViewModels(friendshipUserHashMap));
                }
            });
        }

        if (viewModel == null)
            viewModel = new CreateRoomViewModel(this);

        if (adapter == null)
            adapter = new SelectableRecyclerViewAdapter<>(getRoomFriendViewModels(viewModelWrapper.friends.getValue()),R.layout.recycler_view_room_friend);

        recyclerView = view.findViewById(R.id.recyclerView_roomFriends);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        binding.setViewModel(viewModel);

        viewModelWrapper.updateFriends();

        return view;
    }

    private ArrayList<RoomFriendViewModel> getRoomFriendViewModels(HashMap<Friendship, User> friendshipUserHashMap)
    {
        ArrayList<RoomFriendViewModel> viewModels = new ArrayList<>();

        for (Map.Entry<Friendship,User> entry : friendshipUserHashMap.entrySet())
        {
            if (entry.getKey().accepted != null)
                viewModels.add(new RoomFriendViewModel(entry.getValue(), viewModel));
        }

        return viewModels;
    }

    @Override
    public void onActionSuccess()
    {
        getActivity().getSupportFragmentManager().popBackStack("rooms", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onActionFailure(String message)
    {

    }

    /*@Override
    public ArrayList<User> getSelectedUsers()
    {
        ArrayList<User> users = new ArrayList<>();

        for (RoomFriendViewModel viewModel : adapter.getSelectedItems())
        {
            users.add(viewModel.friend);
        }

        return users;
    }*/
}
