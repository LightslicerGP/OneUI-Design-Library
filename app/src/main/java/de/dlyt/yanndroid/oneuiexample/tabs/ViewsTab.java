package de.dlyt.yanndroid.oneuiexample.tabs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SeslSpinner;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import de.dlyt.yanndroid.oneui.sesl.picker.widget.SeslDatePicker;
import de.dlyt.yanndroid.oneui.sesl.picker.widget.SeslDatePickerSpinnerLayout;
import de.dlyt.yanndroid.oneui.sesl.picker.widget.SeslNumberPicker;
import de.dlyt.yanndroid.oneui.sesl.picker.widget.SeslSpinningDatePickerSpinner;
import de.dlyt.yanndroid.oneui.sesl.picker.widget.SeslTimePicker;
import de.dlyt.yanndroid.oneui.widget.ProgressBar;
import de.dlyt.yanndroid.oneui.widget.SeekBar;
import de.dlyt.yanndroid.oneui.widget.SwitchBar;
import de.dlyt.yanndroid.oneuiexample.MainActivity;
import de.dlyt.yanndroid.oneuiexample.R;

public class ViewsTab extends Fragment {

    private View mRootView;
    private MainActivity mActivity;

    public ViewsTab() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_views_tab, container, false);
        return mRootView;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProgressBar pgb1 = mRootView.findViewById(R.id.pgb1);
        ProgressBar pgb2 = mRootView.findViewById(R.id.pgb2);
        ProgressBar pgb3 = mRootView.findViewById(R.id.pgb3);
        pgb3.setMode(ProgressBar.MODE_CIRCLE);

        //SeekBar
        SeekBar seekBar1 = mRootView.findViewById(R.id.seekbar1);
        SeekBar seekBar2 = mRootView.findViewById(R.id.seekbar2);
        SeekBar seekBar3 = mRootView.findViewById(R.id.seekbar3);
        seekBar3.setSeamless(true);
        seekBar3.showTickMarkDots(true);

        seekBar1.setOverlapPointForDualColor(70);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seslSeekBar, int i, boolean z) {
                seekBar1.setSecondaryProgress(i);
                pgb1.setProgress(i);
                pgb2.setProgress(i);
                pgb3.setProgress(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seslSeekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seslSeekBar) {

            }
        });

        //SwitchBar
        SwitchBar switchbar = mRootView.findViewById(R.id.switchBar);
        switchbar.addOnSwitchChangeListener((switchCompat, z) -> {
            switchbar.setEnabled(false);
            switchbar.setProgressBarVisible(true);

            new Handler().postDelayed(() -> {
                switchbar.setEnabled(true);
                switchbar.setProgressBarVisible(false);
            }, 700);
        });

        //Spinner
        SeslSpinner spinner = mRootView.findViewById(R.id.spinner);
        List<String> categories = new ArrayList<>();
        for (int i = 1; i < 16; i++) categories.add("Spinner Item " + i);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(R.layout.sesl_simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        //Pickers //todo: selected number not centered on each scroll picker
        SeslSpinningDatePickerSpinner ssdps = mRootView.findViewById(R.id.ssdps);

        SeslNumberPicker snp = mRootView.findViewById(R.id.snp); //todo: need to find out how dismiss edit-mode when "done" is clicked in keyboard (dismissing keyboard with back key works | on all the other pickers it's working)
        snp.setMaxValue(100);
        snp.setMinValue(0);
        snp.setValue(50);

        SeslDatePicker sdp = mRootView.findViewById(R.id.sdp);
        sdp.setFirstDayOfWeek(2);
        sdp.setMinDate(0);
        sdp.setMaxDate(4133966209349L);

        SeslTimePicker stp = mRootView.findViewById(R.id.stp);
        stp.setIs24HourView(true);

        SeslDatePickerSpinnerLayout sdpsl = mRootView.findViewById(R.id.sdpsl);
        sdpsl.setMinDate(0);
        sdpsl.setMaxDate(4133966209349L);

    }

}