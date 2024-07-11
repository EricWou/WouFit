package com.example.woufit.initializationfragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.woufit.R;
import com.example.woufit.model.Preferences;
import com.example.woufit.model.Users;

public class Goal_Fragment extends Fragment {

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
    }

    //implement onViewCreated()


}
