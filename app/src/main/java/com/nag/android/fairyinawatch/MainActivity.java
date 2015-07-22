package com.nag.android.fairyinawatch;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

import com.nag.android.util.FlipView;

public class MainActivity extends Activity implements ShakeManager.OnShakeListener,
		FlipView.FlipViewListener,
		TapManager.OnTapListener{
	private FlipView fairy;
	private WatchHandView hourhand;
	private WatchHandView minutehand;
	private ShakeManager shakemanager;
	private TapManager tapmanager;

	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		shakemanager = new ShakeManager(MainActivity.this);
		final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
		stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
			@Override
			public void onLayoutInflated(WatchViewStub stub) {
				fairy = (FlipView)findViewById(R.id.animationViewFairy);
				fairy.setOnFlipViewListener(MainActivity.this);
				hourhand = (WatchHandView)findViewById(R.id.imageViewHourHand);
				minutehand = (WatchHandView)findViewById(R.id.imageViewMinuteHand);
				findViewById(R.id.watch_view_stub).setOnTouchListener(new TapManager(MainActivity.this));
			}
		});
	}
		@Override
		protected void onResume() {
		super.onResume();
		shakemanager.resume(this);
	}

		@Override
		protected void onStop() {
		super.onStop();
		shakemanager.pause();
	}

		private static final int DURATION=3000;
		private static final int INTERVAL = 124;

		@Override
		public void onShake() {
		fairy.reset();
		hourhand.random(DURATION);
		minutehand.random(DURATION);
	}

		@Override
		public void onFinish() {
		hourhand.adjust(DURATION);
		minutehand.adjust(DURATION);
	}

		@Override
		public void onTap() {
		fairy.setSource(R.array.fairy_enter, INTERVAL, false);
	}
}
