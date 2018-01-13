package schweika.chatapplication.Views.Home.Fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import schweika.chatapplication.GenericRecyclerViewAdapter;
import schweika.chatapplication.Models.API.Message;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.R;
import schweika.chatapplication.ViewModels.ChatViewModel;
import schweika.chatapplication.ViewModels.HomeViewModel;
import schweika.chatapplication.ViewModels.MessageViewModel;
import schweika.chatapplication.databinding.FragmentRoomChatBinding;

public class RoomChatFragment extends Fragment
{
    RecyclerView recyclerView;
    GenericRecyclerViewAdapter<MessageViewModel> adapter;
    HomeViewModel homeViewModel;
    Room room;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        FragmentRoomChatBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_room_chat, container, false);

        View view = binding.getRoot();

        if (homeViewModel == null)
        {
            homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        }

        if (room == null)
        {
            Long id = getArguments().getLong("id");

            for (Room item : homeViewModel.rooms.getValue())
            {
                if (item.id == id)
                {
                    room = item;
                    break;
                }
            }
        }

        if (adapter == null)
            adapter = new GenericRecyclerViewAdapter<>(new ArrayList<>(),R.layout.recycler_view_message);

        recyclerView = view.findViewById(R.id.recyclerView_message);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        binding.setViewModel(new ChatViewModel(room,adapter));

        return view;
    }

}
