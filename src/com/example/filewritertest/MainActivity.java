package com.example.filewritertest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

/**
 * アクセス方法
 * MainAc
 * @author 1223066
 *
 */
public class MainActivity extends Activity {
	
	Handler handler = new Handler();
	Context context = this;
	String text = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new Thread(new Runnable() {
			
		@Override
			public void run() {
				// TODO 自動生成されたメソッド・スタブ

				text = "";
				text += "書き込み結果：" + FileWriter.writePrivateFile(context, "test.txt", "テキストテキストテキスト") + "\n";
				text += "読み込み結果：" + FileWriter.readPrivateFile(context, "test.txt") + "\n";
				
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO 自動生成されたメソッド・スタブ
						TextView textview1 = (TextView)findViewById(R.id.textview1);
						textview1.setText(text);							
					}
				});
			}
		}).start();

	}
}
