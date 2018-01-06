package schweika.chatapplication.Views.Home.Fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import schweika.chatapplication.R;
import schweika.chatapplication.ViewModels.AddFriendViewModel;
import schweika.chatapplication.ViewModels.Interfaces.ViewModelListener;
import schweika.chatapplication.databinding.FragmentAddFriendBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFriendFragment extends Fragment implements ViewModelListener
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        FragmentAddFriendBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_friend,container,false);

        View view = binding.getRoot();

        binding.setViewModel(new AddFriendViewModel(this));

        return view;
    }

    @Override
    public void onActionSuccess()
    {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onActionFailure(String message)
    {

    }
}
