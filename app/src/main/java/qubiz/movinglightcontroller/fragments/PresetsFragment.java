package qubiz.movinglightcontroller.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import qubiz.movinglightcontroller.R;

/**
 * Created by QUBiZ on 4-6-2015.
 */
public class PresetsFragment extends Fragment {

    public static final String ARG_POSITION = "PRESETS FRAGMENT POSITION";

    public static PresetsFragment newInstance(int position) {
        PresetsFragment fragment = new PresetsFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_POSITION, position);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_presets, container, false);

        Bundle bundle = getArguments();

        if(bundle != null) {
            // SET ARGUMENTS
        }

        return view;
    }
}
