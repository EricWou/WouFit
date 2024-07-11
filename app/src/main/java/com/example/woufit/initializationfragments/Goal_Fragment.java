package com.example.woufit.initializationfragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.woufit.MainActivity;
import com.example.woufit.R;
import com.example.woufit.model.Preferences;
import com.example.woufit.model.Users;

public class Goal_Fragment extends Fragment {

    private RadioGroup goalFragmentRadioGroup;
    private RadioButton strengthRadioButton;
    private RadioButton enduranceRadioButton;
    private RadioButton flexibilityRadioButton;
    private Button goalFragmentNextButton;
    private Users user;
    private Preferences preferences;

    public Goal_Fragment() {
        super(R.layout.init_goal_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        goalFragmentNextButton.setOnClickListener(v -> {
            //checks to see if there are no checked Radio Buttons belonging to the Radio Group
            if (goalFragmentRadioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getContext(), "Please select one of the options" ,
                        Toast.LENGTH_SHORT).show();
            }
            else {
                preferences = new Preferences();

                if (strengthRadioButton.isChecked()) {
                    preferences.setFitnessGoal("strength");
                }
                else if (enduranceRadioButton.isChecked()) {
                    preferences.setFitnessGoal("endurance");
                }
                else if (flexibilityRadioButton.isChecked()) {
                    preferences.setFitnessGoal("flexibility");
                }

                //retrieving bundle data from previous fragment
                user = requireArguments().getParcelable("user");

                //sending along both user details and preferences
                Bundle bundle = new Bundle();
                bundle.putParcelable("user", user);
                bundle.putParcelable("preferences", preferences);

                getParentFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack("Frequency")
                        .setCustomAnimations(
                                R.anim.slide_in,  // enter
                                R.anim.fade_out,  // exit
                                R.anim.fade_in,   // popEnter
                                R.anim.slide_out  // popExit
                        )
                        .replace(R.id.initialization_fragment_container, Freq_Fragment.class, bundle)
                        .commit();
            }
        });

    }

    private void findViews(View view) {
        goalFragmentRadioGroup = view.findViewById(R.id.goal_fragment_radio_group);
        strengthRadioButton = view.findViewById(R.id.strength_radio_button);
        enduranceRadioButton = view.findViewById(R.id.endurance_radio_button);
        flexibilityRadioButton = view.findViewById(R.id.flexibility_radio_button);
        goalFragmentNextButton = view.findViewById(R.id.goal_fragment_next_button);
    }


}
