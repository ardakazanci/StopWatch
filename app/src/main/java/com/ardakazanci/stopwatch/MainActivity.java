package com.ardakazanci.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import java.util.Timer;
import java.util.TimerTask;

import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;

public class MainActivity extends AppCompatActivity {

	CircleProgressView circleProgressView;
	Button startButton, stopButton;
	Chronometer chronometer;
	long time = 10;
	StopWatch stopWatch = new StopWatch();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		circleProgressView = findViewById(R.id.circle_progress_view);
		startButton = findViewById(R.id.startButton);
		stopButton = findViewById(R.id.stopButton);
		chronometer = findViewById(R.id.chronometer);
		circleProgressView.setTextMode(TextMode.VALUE);

		startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {



				chronometer.setBase(SystemClock.elapsedRealtime() + time);
				chronometer.start();
				stopWatch.startCount();


			}
		});


		stopButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				time = chronometer.getBase() - SystemClock.elapsedRealtime();
				chronometer.stop();
				stopWatch.stopCount();


			}
		});


	}

	class StopWatch {

		private Timer timer;
		private int progressValue = -1;

		public void startCount() {

			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					progressValue++;
					circleProgressView.setValue(progressValue);
					if (progressValue == 59) {

						progressValue = -1;

					}
				}

			}, 0, 1000);


		}

		public void stopCount() {

			timer.cancel();
			timer = null;

		}

	}
}
