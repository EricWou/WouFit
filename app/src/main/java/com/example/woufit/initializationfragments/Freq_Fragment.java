package com.example.woufit.initializationfragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.woufit.R;
import com.example.woufit.model.Preferences;
import com.example.woufit.model.Users;

public class Freq_Fragment extends Fragment {

    private NumberPicker freqFragmentNumberPicker;
    private Button freqFragmentNextButton;
    private Users currentUser;
    private String userID;
    private Preferences preferences;

    public Freq_Fragment() {
        super(R.layout.init_freq_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //retrieving bundle data from previous fragment
        currentUser = requireArguments().getParcelable("user");
        userID = requireArguments().getString("userID");
        preferences = new Preferences();

        findViews(view);

        //setting min and max values for the Number Picker
        freqFragmentNumberPicker.setMinValue(1);
        freqFragmentNumberPicker.setMaxValue(3);
        //freqFragmentNumberPicker.setWrapSelectorWheel(false); - doesn't work?

        freqFragmentNextButton.setOnClickListener(v -> {

            preferences.setFreqPerWeek(freqFragmentNumberPicker.getValue());

            //sending along both user details and updated preferences
            Bundle bundle = new Bundle();
            bundle.putParcelable("user", currentUser);
            bundle.putString("userID", userID);
            bundle.putParcelable("preferences", preferences);

            getParentFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack("Duration")
                    .setCustomAnimations(
                            R.anim.slide_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.slide_out  // popExit
                    )
                    .replace(R.id.initialization_fragment_container, Duration_Fragment.class, bundle)
                    .commit();
        });

    }

    private void findViews(View view) {
        freqFragmentNumberPicker = view.findViewById(R.id.freq_fragment_number_picker);
        freqFragmentNextButton = view.findViewById(R.id.freq_fragment_next_button);
    }
}
