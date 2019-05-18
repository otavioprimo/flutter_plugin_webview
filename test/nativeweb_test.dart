import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:nativeweb/nativeweb.dart';

void main() {
  const MethodChannel channel = MethodChannel('nativeweb');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await Nativeweb.platformVersion, '42');
  });
}
