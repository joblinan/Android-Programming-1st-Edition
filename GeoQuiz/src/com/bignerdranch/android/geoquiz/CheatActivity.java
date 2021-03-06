package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	
	public static final String TAG = "CheatActivity";
	
	public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOW = "com.bignerdranch.android.geoquiz.answer_show";
	public static final String KEY_IS_ANSWER_SHOW = "is_answer_show";
	
	private boolean mAnswerIsTrue;
	private boolean mIsAnswerShow = false;
	
	private TextView mAnswerTextView;
	private Button mShowAnswerButton;
	private TextView mAPILevelTextView;
	
	
	private void setAnswerShowResult() {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOW, mIsAnswerShow);
		setResult(RESULT_OK, data);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_cheat);
		
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
		mAPILevelTextView = (TextView)findViewById(R.id.apiLevelTextView);
		
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		if (savedInstanceState != null) {
			Log.d(TAG, "savedInstanceState != null");
			mIsAnswerShow = savedInstanceState.getBoolean(KEY_IS_ANSWER_SHOW);
			
			if (mIsAnswerShow) {
				showAnswerText();
			}
		}		
		setAnswerShowResult();
		
		mShowAnswerButton = (Button)findViewById(R.id.showAnswerButton);
		mShowAnswerButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				showAnswerText();	
				
				mIsAnswerShow = true;
				setAnswerShowResult();
			}
		});
		
		mAPILevelTextView.setText(getString(R.string.API_level) + " " + Build.VERSION.SDK_INT);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState() called.");
		
		outState.putBoolean(KEY_IS_ANSWER_SHOW, mIsAnswerShow);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy() called.");
	}

	private void showAnswerText() {
		if(mAnswerIsTrue) {
			mAnswerTextView.setText(R.string.true_button);
		} else {
			mAnswerTextView.setText(R.string.false_button);
		}
	}	

}
