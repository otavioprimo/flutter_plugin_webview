import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

typedef void WebViewCreateCallback(WebController controller);

class Nativeweb {
  static const MethodChannel _channel = const MethodChannel('nativeweb');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}

class NativeWeb extends StatefulWidget {
  final WebViewCreateCallback onWebCreated;
  final double height;

  NativeWeb({Key key, @required this.onWebCreated, this.height});

  @override
  _NativeWebState createState() => _NativeWebState();
}

class _NativeWebState extends State<NativeWeb> {
  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {
      return Column(
        children: <Widget>[
          Text(
            'Webview from Android Code',
            textAlign: TextAlign.center,
          ),
          Container(
            height: widget.height,
            child: AndroidView(
              viewType: 'nativeweb',
              onPlatformViewCreated: onPlatformViewCreated,
              creationParamsCodec: const StandardMessageCodec(),
            ),
          )
        ],
      );
    }

    return new Text(
        '$defaultTargetPlatform is not yet supported by this plugin');
  }

  Future<void> onPlatformViewCreated(id) async {
    if (widget.onWebCreated == null) {
      return;
    }

    widget.onWebCreated(new WebController.init(id));
  }
}

class WebController {
  MethodChannel _channel;

  WebController.init(int id) {
    _channel = new MethodChannel('nativeweb_$id');
  }

  Future<void> loadUrl(String url) async {
    assert(url != null);
    return _channel.invokeMethod('loadUrl', url);
  }
}
