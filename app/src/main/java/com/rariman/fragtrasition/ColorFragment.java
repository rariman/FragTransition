package com.rariman.fragtrasition;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

public class ColorFragment extends Fragment {

    private ColorFragmentListener listener;
    private SeekBar alphaSeekBar, redSeekBar, greenSeekBar, blueSeekBar;
    private View previewcolor;
    private Button okButton;
    private int color;

    public interface ColorFragmentListener {
        void onsetColorView(int color);
    }

    public ColorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle colorInfoBundle = getArguments();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_color, container, false);
        alphaSeekBar = (SeekBar) view.findViewById(R.id.alphaSeekBar);
        redSeekBar = (SeekBar) view.findViewById(R.id.redSeekBar);
        greenSeekBar = (SeekBar) view.findViewById(R.id.greenSeekBar);
        blueSeekBar = (SeekBar) view.findViewById(R.id.blueSeekBar);
        previewcolor = view.findViewById(R.id.colorview);
        okButton = (Button) view.findViewById(R.id.okButton);

        alphaSeekBar.setOnSeekBarChangeListener(onColorChanged);
        redSeekBar.setOnSeekBarChangeListener(onColorChanged);
        greenSeekBar.setOnSeekBarChangeListener(onColorChanged);
        blueSeekBar.setOnSeekBarChangeListener(onColorChanged);

        okButton.setOnClickListener(onOkClick);

        color = colorInfoBundle.getInt(MainActivity.COLOR_INFO);
        alphaSeekBar.setProgress(Color.alpha(color));
        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));

        return view;
    }

    SeekBar.OnSeekBarChangeListener onColorChanged = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser)
                color = Color.argb(alphaSeekBar.getProgress(), redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress());

            previewcolor.setBackgroundColor(color);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    View.OnClickListener onOkClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
               listener.onsetColorView(color);
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (ColorFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ColorFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
