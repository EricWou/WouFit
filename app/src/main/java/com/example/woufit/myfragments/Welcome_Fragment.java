package com.example.woufit.myfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.woufit.R;
import com.example.woufit.model.Users;

public class Welcome_Fragment extends Fragment {

    private TextView welcomeFragmentTitleTextView;
    private Button getStartedButton;
    private Users user;

    public Welcome_Fragment() {
        super(R.layout.welcome_fragment);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        //onViewCreated is called immediately after onCreateView()

        //retrieving data from Bundle object containing key "user"
        user = requireArguments().getParcelable("user");
        //retrieving view from welcome_fragment
        welcomeFragmentTitleTextView = getView().findViewById(R.id.welcome_fragment_title_text_view);
        
        String greeting = "Welcome "+user.getUserFName();
        welcomeFragmentTitleTextView.setText(greeting);
    }

}
