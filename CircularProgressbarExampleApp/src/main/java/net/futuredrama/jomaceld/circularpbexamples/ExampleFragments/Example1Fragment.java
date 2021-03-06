/*
* Copyright 2016 Jose Antonio Maestre Celdran
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package net.futuredrama.jomaceld.circularpbexamples.ExampleFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import net.futuredrama.jomaceld.circularpbexamples.R;
import net.futuredrama.jomaceld.circularpblib.CircularProgressBarView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Example1Fragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    CircularProgressBarView pBar;
    SeekBar sb_progress;
    SeekBar sb_barThickness;
    SeekBar sb_backgroundThickness;

    public Example1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_example1, container, false);

        Button buttonRandomProgress = (Button) rootView.findViewById(R.id.button_randomprogress);
        assert buttonRandomProgress != null;
        buttonRandomProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRandomProgress();
            }
        });

        sb_progress = (SeekBar) rootView.findViewById(R.id.progressSeekBar);
        sb_progress.setOnSeekBarChangeListener(this);

        sb_barThickness = (SeekBar) rootView.findViewById(R.id.barThicknessSeekBar);
        sb_barThickness.setOnSeekBarChangeListener(this);

        sb_backgroundThickness = (SeekBar) rootView.findViewById(R.id.backgroundThicknessSeekBar);
        sb_backgroundThickness.setOnSeekBarChangeListener(this);

        pBar = (CircularProgressBarView) rootView.findViewById(R.id.pbar);

        if(pBar != null) {
            setupProgressbar();
        }
        sb_barThickness.setProgress(20);
        sb_backgroundThickness.setProgress(16);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
//        Log.d("James==pBarLocate==",String.valueOf(pBar.getX())+"  "+String.valueOf(pBar.getY()));

    }

    private void setupProgressbar() {
        // Set the numbers of bars
        pBar.setNumberOfBars(1);
        // Set bars colors
        pBar.setBarsColors(new int[]{ getResources().getColor(R.color.bar1Color) });
        //Set a Random progress
        setRandomProgress();
    }

    public void setRandomProgress() {
        Random r = new Random();
        sb_progress.setProgress((int)(r.nextFloat()*100));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        switch (seekBar.getId()){
            case R.id.barThicknessSeekBar:
                pBar.setBarsThickness(progress,TypedValue.COMPLEX_UNIT_DIP);
                // force redraw of the view
                pBar.invalidate();
                break;
            case R.id.progressSeekBar:
                pBar.setProgressWithAnimation(progress/100f);
                break;
            case R.id.backgroundThicknessSeekBar:
                pBar.setProgressbarBackgroundThickness(progress,TypedValue.COMPLEX_UNIT_DIP);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
