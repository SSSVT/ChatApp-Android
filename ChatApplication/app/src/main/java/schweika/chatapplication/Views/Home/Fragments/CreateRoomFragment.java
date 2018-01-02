package schweika.chatapplication.Views.Home.Fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.MediaBrowserCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.R;
import schweika.chatapplication.ViewModels.CreateRoomViewModel;
import schweika.chatapplication.ViewModels.ViewModelListener;
import schweika.chatapplication.Views.Login.LoginViewModelListener;
import schweika.chatapplication.databinding.FragmentCreateRoomBinding;

public class CreateRoomFragment extends Fragment implements ViewModelListener
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        FragmentCreateRoomBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_create_room,container,false);

        View view = binding.getRoot();

        binding.setViewModel(new CreateRoomViewModel(this));

        return view;
    }

    @Override
    public void onActionSuccess()
    {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onActionFailure()
    {

    }
}
