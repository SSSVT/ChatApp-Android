package schweika.chatapplication.Views.Home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import schweika.chatapplication.R;
import schweika.chatapplication.ViewModels.HomeViewModel;
import schweika.chatapplication.Views.Home.Fragments.FriendsFragment;
import schweika.chatapplication.Views.Home.Fragments.RoomsFragment;
import schweika.chatapplication.Views.Login.LoginActivity;
import schweika.chatapplication.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity
{
    ActivityHomeBinding binding;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        switch (item.getItemId())
        {
            case R.id.navigation_rooms:
                switchToRoomsFragment();
                return true;
            case R.id.navigation_friends:
                switchToFriendsFragment();
                return true;
        }
        return false;
    };

    private void switchToRoomsFragment()
    {
        FragmentManager manager = getSupportFragmentManager();

        Fragment fragment = manager.findFragmentByTag("rooms");

        if (fragment == null)
            fragment = new RoomsFragment();

        manager.beginTransaction().replace(R.id.fragment_content, fragment, "rooms").commit();
    }

    private void switchToFriendsFragment()
    {
        FragmentManager manager = getSupportFragmentManager();

        Fragment fragment = manager.findFragmentByTag("friends");

        if (fragment == null)
            fragment = new FriendsFragment();

        manager.beginTransaction().replace(R.id.fragment_content, fragment, "friends").commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        HomeViewModel viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        binding.setViewModel(viewModel);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        switchToRoomsFragment();
    }

    public void showUserMenu(View view)
    {
        PopupMenu popupMenu = new PopupMenu(this,view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.user,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.user_logout:
                        logOut();
                        return true;
                }
                return false;
            }
        });

        popupMenu.show();
    }


    private void logOut()
    {
        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("JWT");
        editor.apply();

        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);

        finish();
    }

    @Override
    public void onBackPressed()
    {
        FragmentManager manager = getSupportFragmentManager();

        if (manager.getBackStackEntryCount() > 0)
        {
            manager.popBackStack();
        }
        else
        {
            finishAffinity();
        }
    }
}
