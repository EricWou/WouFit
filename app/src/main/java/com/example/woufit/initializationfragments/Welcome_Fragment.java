package com.example.woufit.initializationfragments;

import android.os.Bundle;
import android.view.View;
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
        super(R.layout.init_welcome_fragment);
    }

    //onViewCreated is called immediately after onCreateView()
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //retrieving data from Bundle object containing key "user"
        user = requireArguments().getParcelable("user");

        Bundle bundle = new Bundle();
        bundle.putParcelable("user", user);

        //retrieving view from welcome_fragment
        welcomeFragmentTitleTextView = getView().findViewById(R.id.welcome_fragment_title_text_view);

        String greeting = "Welcome "+user.getUserFName();
        welcomeFragmentTitleTextView.setText(greeting);

        getStartedButton = getView().findViewById(R.id.get_started_button);
        getStartedButton.setOnClickListener(v -> {
            //getParentFragmentManager() retrieves the FragmentManager from the parent
            //the parent in this case is account_initialization.java
            getParentFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack("Goal")
                    .setCustomAnimations(
                            R.anim.slide_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.slide_out  // popExit
                    )
                    .replace(R.id.initialization_fragment_container, Goal_Fragment.class, bundle)
                    .commit();

            //implement stack to allow going back and forth between fragments
        });
    }

}
