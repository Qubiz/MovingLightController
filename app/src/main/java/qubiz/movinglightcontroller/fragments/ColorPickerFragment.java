package qubiz.movinglightcontroller.fragments;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import qubiz.movinglightcontroller.R;
import qubiz.movinglightcontroller.tabs.SlidingTabLayout;
import qubiz.movinglightcontroller.tcp.Constants;
import qubiz.movinglightcontroller.tcp.TCPClient;

public class ColorPickerFragment extends Fragment {
    public static final String ARG_POSITION = "COLOR PICKER FRAGMENT POSITION";

    private ColorPicker colorPicker;
    private SaturationBar saturationBar;
    private ValueBar valueBar;

    private SlidingTabLayout tabs;
    private TCPClient tcpClient;
    private SwitchCompat switchCompat;

    private boolean lampOn = false;

    public static ColorPickerFragment newInstance(int position) {
        ColorPickerFragment fragment = new ColorPickerFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_POSITION, position);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color_picker, container, false);

        Bundle bundle = getArguments();

        if(bundle != null) {
            // SET ARGUMENTS
        }

        colorPicker = (ColorPicker) view.findViewById(R.id.color_picker);
        saturationBar = (SaturationBar) view.findViewById(R.id.saturation_bar);
        valueBar = (ValueBar) view.findViewById(R.id.value_bar);

        colorPicker.setShowOldCenterColor(false);
        colorPicker.addSaturationBar(saturationBar);
        colorPicker.addValueBar(valueBar);

        tabs.setSelectedIndicatorColors(colorPicker.getColor());

        new ConnectToServerTask().execute("");

        colorPicker.setOnColorChangedListener(new ColorPicker.OnColorChangedListener() {
            @Override
            public void onColorChanged(int i) {
                if(lampOn) {
                    tabs.setSelectedIndicatorColors(colorPicker.getColor());
                    int red = (i >> 16) & 0xff;
                    int green = (i >> 8) & 0xff;
                    int blue = (i) & 0xff;

                    Log.e("COLOR", "(" + red + ", " + green + ", " + blue + ")");

                    String message = "R=" + red + " G=" + green + " B=" + blue;

                    if (tcpClient != null) {
                        tcpClient.sendMessage(message);
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        tcpClient.stopClient();
        tcpClient = null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        tabs = (SlidingTabLayout) activity.findViewById(R.id.sliding_tabs);
    }

    @Override
    public void onStop() {
        super.onStop();
        tcpClient.stopClient();
        tcpClient = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        switchCompat = (SwitchCompat) menu
                .findItem(R.id.menu_item_main_on_off_switch)
                .getActionView()
                .findViewById(R.id.main_on_off_switch);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                lampOn = isChecked;
                if(lampOn) {
                    int color = colorPicker.getColor();

                    int red = (color >> 16) & 0xff;
                    int green = (color >> 8) & 0xff;
                    int blue = (color) & 0xff;

                    String message = "R=" + red + " G=" + green + " B=" + blue;

                    if (tcpClient != null) {
                        tcpClient.sendMessage(message);
                    }
                } else {
                    if (tcpClient != null) {
                        tcpClient.sendMessage("R=0 G=0 B=0");
                    }
                }
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    public class ConnectToServerTask extends AsyncTask<String, String,TCPClient> {

        @Override
        protected TCPClient doInBackground(String... params) {
            tcpClient = new TCPClient(new TCPClient.MessageReceivedListener() {
                @Override
                public void messageReceived(String message) {
                    publishProgress(message);
                }
            });
            tcpClient.run();
            return null;
        }
    }
}


