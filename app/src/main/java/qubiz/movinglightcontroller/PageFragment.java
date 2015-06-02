package qubiz.movinglightcontroller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;

public class PageFragment extends Fragment {
    public static final String ARG_PAGE =  "ARG_PAGE";

    private ColorPicker colorPicker;
    private SaturationBar saturationBar;
    private ValueBar valueBar;

    public int page;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        colorPicker = (ColorPicker) view.findViewById(R.id.color_picker);
        saturationBar = (SaturationBar) view.findViewById(R.id.saturation_bar);
        valueBar = (ValueBar) view.findViewById(R.id.value_bar);

        colorPicker.setShowOldCenterColor(false);
        colorPicker.addSaturationBar(saturationBar);
        colorPicker.addValueBar(valueBar);

        return view;
    }

    private void initializeVariables(View view) {

    }

}
