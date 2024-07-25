package com.example.woufit.initializationfragments;

import android.content.Intent;
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
import com.example.woufit.StartActivity;
import com.example.woufit.account_initialization;
import com.example.woufit.model.Preferences;
import com.example.woufit.model.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Duration_Fragment extends Fragment {

    private RadioGroup durationFragmentRadioGroup;
    private RadioButton firstRadioButton;
    private RadioButton secondRadioButton;
    private RadioButton thirdRadioButton;
    private Button durationFragmentNextButton;
    private Users currentUser;
    private String userID;
    private Preferences preferences;
    DatabaseReference databaseRef;

    public Duration_Fragment() {
        super(R.layout.init_duration_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //retrieving bundle data from previous fragment
        currentUser = requireArguments().getParcelable("user");
        userID = requireArguments().getString("userID");
        preferences = requireArguments().getParcelable("preferences");

        findViews(view);

        durationFragmentNextButton.setOnClickListener(v -> {
            //checks to see if there are no checked Radio Buttons belonging to the Radio Group
            if (durationFragmentRadioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getContext(), "Please select one of the options" ,
                        Toast.LENGTH_SHORT).show();
            }
            else {
                if (firstRadioButton.isChecked()) {
                    preferences.setDurationWorkout(20);
                }
                else if (secondRadioButton.isChecked()) {
                    preferences.setDurationWorkout(30);
                }
                else if (thirdRadioButton.isChecked()) {
                    preferences.setDurationWorkout(45);
                }

                //saves preferences under user object
                databaseRef = FirebaseDatabase.getInstance().getReference();
                databaseRef.child("Users")
                            .child(userID)
                            .child("Preferences")
                            .setValue(preferences);

                //to update, needs to be in the form of a map so that there is key-value pairs
                currentUser.setFirstLogin(false);
                Map<String, Object> updateUser = new HashMap<>();
                updateUser.put("firstLogin", currentUser.getFirstLogin());

                databaseRef.child("Users")
                            .child(userID)
                            .updateChildren(updateUser);

                //sending along just userID to MainActivity (similar to StartActivity flow)
                Bundle bundle = new Bundle();
                bundle.putString("userID", userID);

                Intent nextActivity = new Intent(getActivity(), MainActivity.class);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);

                /*
                //change to intent
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

                 */
            }
        });

    }

    private void findViews(View view) {
        durationFragmentRadioGroup = view.findViewById(R.id.duration_fragment_radio_group);
        firstRadioButton = view.findViewById(R.id.first_radio_button);
        secondRadioButton = view.findViewById(R.id.second_radio_button);
        thirdRadioButton = view.findViewById(R.id.third_radio_button);
        durationFragmentNextButton = view.findViewById(R.id.duration_fragment_next_button);
    }
}
