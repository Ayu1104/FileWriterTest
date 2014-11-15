package com.example.filewritertest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	Handler handler = new Handler();
	String text = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new Thread(new Runnable() {
			
		@Override
			public void run() {
				// TODO 自動生成されたメソッド・スタブ

				File file = new File(getExternalFilesDir(null), "test.txt");

				try{
					//	/mnt/sdcard/Android/data/com.example.filewritertest/files/test.txt（全ユーザ読み書き可） に書き込む
//					FileOutputStream output = new FileOutputStream(file);
					//	/data/data/com.example.filewritertest/files/test.txt（アプリのみ読み書き可） に書き込む
					FileOutputStream output = openFileOutput(file.getName(), MODE_PRIVATE);
					BufferedOutputStream stream = new BufferedOutputStream(output);
					stream.write("テキストテキストテキスト".getBytes("UTF-8"));
					stream.close();
					
				}catch(Exception exception){
					Log.e("", exception.toString() + "on writing");
				}
				
				try{					
//					FileInputStream in = new FileInputStream(file);
					FileInputStream in = openFileInput(file.getName());
					BufferedInputStream stream = new BufferedInputStream(in);
					text = "";
					byte[] temp = new byte[stream.available()];
					while(stream.read(temp) != -1){
						text += new String(temp, "UTF-8");
					}
					stream.close();
				}catch(Exception exception){
					Log.e("", exception.toString() + " on reading");
				}
				
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