import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_plugin_blueinfo/flutter_plugin_blueinfo.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  String _blueState = 'Unknown';

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    String blueState;
    // Platform messages may fail, so we use a try/catch PlatformException. blueStae
    try {
      platformVersion = await FlutterPluginBlueinfo.platformVersion;
      blueState= await FlutterPluginBlueinfo.blueStae;
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
      blueState="Failed";
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
      _blueState = blueState;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Row(
            children: [
               new Text('Running on: $_platformVersion\n'),
               new Text('Running on: $_blueState\n'),
            ],

        ),
      ),
    );
  }
}
