package com.chinaCEB.cebActivity;

import android.app.Activity;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import coma.github.lzyzsd.jsbridge.BridgeHandler;
import coma.github.lzyzsd.jsbridge.BridgeWebView;
import coma.github.lzyzsd.jsbridge.CallBackFunction;
import coma.github.lzyzsd.jsbridge.DefaultHandler;

public class YaoYaoActivity extends Activity {
	private final String TAG = "YaoYaoActivity";
	static BridgeWebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Uri uri=getIntent().getData();
		Toast.makeText(YaoYaoActivity.this, uri.toString(), Toast.LENGTH_SHORT).show();

		webView = (BridgeWebView) findViewById(R.id.webView);
		webView.setDefaultHandler(new DefaultHandler());
		SslCertificate s=webView.getCertificate();
//		webView.setWebViewClient(new WebViewClient(){
//			@Override
//			public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
////				super.onReceivedSslError(view, handler, error);
//				handler.proceed();
////				handleMessage(Message msg); 其他处理
//			}
//		});

		webView.setWebChromeClient(new WebChromeClient() {

			@SuppressWarnings("unused")
			public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
				this.openFileChooser(uploadMsg);
			}

			@SuppressWarnings("unused")
			public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
				this.openFileChooser(uploadMsg);
			}

			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
			}

			public void openFileChooser(ValueCallback<Uri> uploadMsg) {

			}

		});



		webView.loadUrl(uri.toString());
		//关闭
		webView.registerHandler("close", new BridgeHandler() {
			@Override
			public void handler(String data, CallBackFunction function) {
				finish();
			}

		});



		webView.registerHandler("wxpay", new BridgeHandler() {
			@Override
			public void handler(String data, CallBackFunction function) {
				Gson gson = new GsonBuilder()
						.setDateFormat("yyyy-MM-dd HH:mm:ss")
						.create();
//				OrderModel ordermodel=gson.fromJson(data,OrderModel.class);
//				requestWXPay(YaoYaoActivity.this,ordermodel);
//				Log.i(TAG, "handler: "+ordermodel.getAppid());
				Log.i(TAG, "handler = submitFromWeb, data from web = " + data);
//                function.onCallBack("submitFromWeb exe, response data 中文 from Java");
			}

		});

	}
	public static void payResult(String state){
		webView.send("hellow");
		webView.callHandler("payResult", state, new CallBackFunction() {

			@Override
			public void onCallBack(String data) {
				// TODO Auto-generated method stub
//				Log.i(TAG, "reponse data from js " + data);
			}

		});
	}

}
