package br.com.codebrain.nativeweb;

import br.com.codebrain.nativeweb.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.plugin.platform.PlatformView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;

import static io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class FlutterWeb implements PlatformView, MethodCallHandler {
  Context context;
  Registrar registrar;
  String url = "";
  MethodChannel channel;
  View view;
  Button button;

  FlutterWeb(Context context, Registrar registrar, int id) {
    this.context = context;
    this.registrar = registrar;
    view = getViewFromFile(registrar);

    channel = new MethodChannel(registrar.messenger(), "nativeweb_" + id);

    channel.setMethodCallHandler(this);

    button = getView().findViewById(R.id.myButton);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View arg0) {
        Log.d("debug","foiiiii");
      }
    });
  }

  @Override
  public View getView() {
    return view;
  }

  @Override
  public void dispose() {
  }

  @Override
  public void onMethodCall(MethodCall call, MethodChannel.Result result) {
    switch (call.method) {
    case "loadUrl":
      String url = call.arguments.toString();
      webView.loadUrl(url);
      break;
    default:
      result.notImplemented();
    }
  }

  private View getViewFromFile(Registrar registrar) {
    View view = LayoutInflater.from(registrar.activity()).inflate(R.layout.teste, null);
    return view;
  }
}