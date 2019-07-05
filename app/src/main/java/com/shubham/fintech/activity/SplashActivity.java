package com.shubham.fintech.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.shubham.fintech.R;
import com.ypyproductions.dialog.utils.AlertDialogUtils;
import com.ypyproductions.task.IDBCallback;
import com.ypyproductions.utils.ApplicationUtils;

public class SplashActivity extends Activity{

	public static final String TAG = SplashActivity.class.getSimpleName();

	private ProgressBar mProgressBar;
	private boolean isPressBack;

	private Handler mHandler = new Handler();
	private TextView mTvCopyright;

	private TextView mTvVersion;
	private boolean isLoading;
	private TextView mTvAppName;
	private boolean isStartAnimation;
	private ImageView mImgLogo;
	protected boolean isShowingDialog;
	Animation animation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.splash);
		this.mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		mImgLogo = (ImageView) findViewById(R.id.img_logo);


		mProgressBar.setVisibility(View.INVISIBLE);

	}

	@Override
	protected void onResume() {
		super.onResume();
		if(!ApplicationUtils.isOnline(this)){
			if(!isShowingDialog){
				isShowingDialog=true;
				showDialogTurnOnInternetConnection();
			}
		}
		else{
			if (!isLoading) {
				isLoading = true;

				startAnimationLogo(new IDBCallback() {
					@Override
					public void onAction() {
						mProgressBar.setVisibility(View.VISIBLE);


						mHandler.postDelayed(new Runnable() {
							@Override
							public void run() {
								mProgressBar.setVisibility(View.INVISIBLE);
								Intent mIntent = new Intent(SplashActivity.this, MainActivity.class);
								startActivity(mIntent);
								finish();
							}
						}, 3000);
					}
				});
			}
		}

	}

	private void startAnimationLogo(final IDBCallback mCallback) {
		if (!isStartAnimation) {
			isStartAnimation = true;
			mProgressBar.setVisibility(View.INVISIBLE);

			animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
			mImgLogo.setAnimation(animation);

			if (mCallback != null) {
				mCallback.onAction();

			} else {
				if (mCallback != null) {
					mCallback.onAction();
				}
			}
		}}

	private void showDialogTurnOnInternetConnection() {
		Dialog mDialog = AlertDialogUtils.createFullDialog(this, 0, R.string.title_warning, R.string.title_settings, R.string.title_cancel,
				R.string.info_lose_internet, new AlertDialogUtils.IOnDialogListener() {
					@Override
					public void onClickButtonPositive() {
						isShowingDialog = false;
						startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
					}

					@Override
					public void onClickButtonNegative() {
						isShowingDialog = false;
						finish();
					}
				});
		mDialog.show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mHandler.removeCallbacksAndMessages(null);
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (isPressBack) {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
/**
 *
 *
 * @author:Awais sakhi
 * @Email: wasigggg@gmail.com
 * @Project:ActiveAudioTube
 * @Date:3/8/2017
 *
 */