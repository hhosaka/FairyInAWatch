package com.nag.android.fairyinawatch;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

import com.nag.android.fairyviewerengine.Fairy;
import com.nag.android.fairyviewerengine.ShakeManager;
import com.nag.android.fairyviewerengine.TapManager;
import com.nag.android.fairyviewerengine.Watch;
import com.nag.android.fairyviewerengine.WatchHandView;
import com.nag.android.util.FlipView;

public class MainActivity extends Activity implements ShakeManager.OnShakeListener,
		TapManager.OnTapListener
		,Watch {

	private ShakeManager shakemanager;
	private TapManager tapmanager;
	private Fairy fairy = new FairyShadowGuy();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		shakemanager = new ShakeManager(MainActivity.this);
		final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
		stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
			@Override
			public void onLayoutInflated(WatchViewStub stub) {
				shakemanager = new ShakeManager(MainActivity.this);
				findViewById(R.id.watch_view_stub).setOnTouchListener(new TapManager(MainActivity.this));
				((FlipView)findViewById(R.id.animationViewFairy)).setAngleMeter(shakemanager);
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

	@Override
	public void onShake()
	{
		fairy.onShake(this, this);
	}

	@Override
	public void onRolling()
	{
		//do nothing
	}

	@Override
	public void onTap() {
		fairy.onTap(this, this);
	}

	@Override
	public WatchHandView getHourHandView() {
		return (WatchHandView)findViewById(R.id.imageViewHourHand);
	}

	@Override
	public WatchHandView getMinuteHandView() {
		return (WatchHandView)findViewById(R.id.imageViewMinuteHand);
	}

	@Override
	public FlipView getFairyHandView() {
		return (FlipView)findViewById(R.id.animationViewFairy);
	}

}
