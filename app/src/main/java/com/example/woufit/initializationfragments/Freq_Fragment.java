package com.example.woufit.initializationfragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.woufit.R;
import com.example.woufit.model.Preferences;
import com.example.woufit.model.Users;

public class Freq_Fragment extends Fragment {

    //private NumberPicker freqFragmentNumberPicker;
    private RadioGroup freqFragmentRadioGroup;
    private RadioButton freqFirstRadioButton;
    private RadioButton freqSecondRadioButton;
    private RadioButton freqThirdRadioButton;
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

        /*
        //setting min and max values for the Number Picker
        freqFragmentNumberPicker.setMinValue(1);
        freqFragmentNumberPicker.setMaxValue(3);
        //freqFragmentNumberPicker.setWrapSelectorWheel(false); - doesn't work?
         */

        freqFragmentNextButton.setOnClickListener(v -> {

            //preferences.setFreqPerWeek(freqFragmentNumberPicker.getValue());

            if (freqFragmentRadioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getContext(), "Please select one of the options" ,
                        Toast.LENGTH_SHORT).show();
            }
            else {
                if (freqFirstRadioButton.isChecked()) {
                    preferences.setFreqPerWeek(1);
                } else if (freqSecondRadioButton.isChecked()) {
                    preferences.setFreqPerWeek(2);
                } else if (freqThirdRadioButton.isChecked()) {
                    preferences.setFreqPerWeek(3);
                }

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
            }
        });


    }

    private void findViews(View view) {
        //freqFragmentNumberPicker = view.findViewById(R.id.freq_fragment_number_picker);
        freqFragmentRadioGroup = view.findViewById(R.id.freq_fragment_radio_group);
        freqFirstRadioButton = view.findViewById(R.id.freq_first_radio_button);
        freqSecondRadioButton = view.findViewById(R.id.freq_second_radio_button);
        freqThirdRadioButton = view.findViewById(R.id.freq_third_radio_button);
        freqFragmentNextButton = view.findViewById(R.id.freq_fragment_next_button);
    }
}
