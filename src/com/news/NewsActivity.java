package com.news;

 

 


 
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Looper;
import android.util.ArrayMap;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.os.Handler;

import java.util.HashMap;
import java.util.UUID;


@SuppressLint("NewApi")
public class NewsActivity extends Activity {
	WebView mWebView;
		static int finishedtimes=0;
		static int nowthread=0;
		static int finishweb=0;
	NewsActivity theActivity=null;


	public void clickonAndroid(final String uuid, final String mypara){
		//	msgOK=true;
		System.out.println(uuid+"*********************");
		String [] newsstr=mypara.split(";zm;");
		finishweb++;
		// taskresult.put(uuid,order);
		final String callBack=mapCallBack.get(uuid);
		 //mypara.replaceAll("'","\\\'");
		// mypara.replaceAll("\"", "\\\'");
		final String newmypara = mypara.replaceAll("\\s*|\t|\r|\n", "");

		System.out.println("new="+mypara);
		mWebView.post(new Runnable() {
			public void run() {
				mWebView.loadUrl("javascript:" + callBack + "('" + newmypara + "')");
			}
		});

		    /* if(finishweb>=Integer.parseInt(total)){
		    	 System.out.println("call order"+order);
		    	 mWebView.loadUrl("javascript:addTitles('" + order + "')");
		     }*/

				//  System.out.println("*********************11111"+order);
			}

			@JavascriptInterface
			public void jsInvoke(final String url, final String jscode, final String callBack) {
				//	msgOK=true;
				String para = "";
				// System.out.println(uuid+"*********************");

				// taskresult.put(uuid,order);
				mWebView.post(new Runnable() {
					public void run() {
						getMyContent(url, jscode, callBack);
					}
				});
				// getMyContent("http://www.bdwm.net/bbs/bbstop.php?board=Job","");

				// mWebView.loadUrl("javascript:"+callBack+"('" + para + "')");

			}

			static public HashMap<String, String> mapCallBack = new HashMap<String, String>();

			public String getMyContent(String url, final String js, final String callBack) {
				String myuuid = UUID.randomUUID().toString();
				mapCallBack.put(myuuid, callBack);

				System.out.println("Mythread =" + myuuid + "," + url);


			 	final MyWebView web = new MyWebView(this, myuuid, 1);//(WebView) findViewById(R.id.webView1);
			//	final MyWebView web = (MyWebView) findViewById(R.id.webView1);

				//	web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
				web.getSettings().setJavaScriptEnabled(true);
				web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
				web.getSettings().setAllowFileAccess(true);// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷募锟斤拷锟斤拷锟�				web.getSettings().setSupportZoom(true);
				web.getSettings().setBuiltInZoomControls(true);
				web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
				//web.getSettings().setCacheMode(web.getSettings().LOAD_CACHE_ELSE_NETWORK);
				web.getSettings().setDomStorageEnabled(true);
				web.getSettings().setDatabaseEnabled(true);
				web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
				web.loadUrl(url);
				web.addJavascriptInterface(this, "mymain");
			 	web.setWebChromeClient(new WebChromeClient());

				web.setWebViewClient(new WebViewClient() {

					public void onPageFinished(final WebView view, String url) {
						super.onPageFinished(view,url);
						finishedtimes++;


						System.out.println("finished1=" + finishedtimes+","+url+","+view.getUrl());


							//view.loadUrl("javascript:" + "var mytxt=\"\";for(var i=document.getElementsByTagName(\"TBODY\")[1].getElementsByTagName(\"TR\").length-1;i>8;i--){ if(document.getElementsByTagName(\"TBODY\")[1].childNodes[i]!=null && document.getElementsByTagName(\"TBODY\")[1].getElementsByTagName(\"TR\")[i].getElementsByTagName(\"TD\")[3]!=null ){mytxt=mytxt+\";zm;\"+document.getElementsByTagName(\"TBODY\")[1].getElementsByTagName(\"TR\")[i].getElementsByTagName(\"TD\")[3].getElementsByTagName(\"A\")[0].innerHTML+\";my;\"+document.getElementsByTagName(\"TBODY\")[1].getElementsByTagName(\"TR\")[i].getElementsByTagName(\"TD\")[3].getElementsByTagName(\"A\")[0].getAttribute(\"href\");} }");
							String myjs = js + "window.mymain.clickonAndroid(\'" + ((MyWebView) view).getUuid() + "\',mytxt);";
							System.out.println("myjs=" + myjs);
							  view.loadUrl(myjs);




					}
				});


				//	System.out.println(myuuid+":result="+taskresult.get(myuuid));


				return "";
			}

			public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				theActivity = this;
				setContentView(R.layout.main);

				mWebView = (WebView) findViewById(R.id.webview);

				mWebView.getSettings().setJavaScriptEnabled(true);
				//    mWebView.getSettings().setDomStorageEnabled(true);
				mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

				mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
				mWebView.getSettings().setAllowFileAccess(true);// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷募锟斤拷锟斤拷锟�				mWebView.getSettings().setSupportZoom(true);
				mWebView.getSettings().setBuiltInZoomControls(true);
				mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
				//web.getSettings().setCacheMode(web.getSettings().LOAD_CACHE_ELSE_NETWORK);
				mWebView.getSettings().setDomStorageEnabled(true);
				mWebView.getSettings().setDatabaseEnabled(true);
				//  mWebView.setWebChromeClient(new  WebChromeClient());
				mWebView.setWebChromeClient(new NewsClient());
				mWebView.loadUrl("file:///android_asset/www/index.html");
				mWebView.addJavascriptInterface(this, "mymain");
				finishweb = 0;


			}

			public boolean onKeyDown(int keyCode, KeyEvent event) {
				if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
					mWebView.goBack();
					return true;
				}
				return super.onKeyDown(keyCode, event);
			}

			private class NewsClient extends WebChromeClient {

				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					System.out.println("URL: " + url);
					//	view.loadUrl("javascript:changeLocation('" + url + "')");
					return true;
				}
			}
		}