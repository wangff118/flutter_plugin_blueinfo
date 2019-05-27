
package gds.blue;

import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothAdapter;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

import io.flutter.plugin.common.PluginRegistry.Registrar;
import android.content.Context;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry.RequestPermissionsResultListener;
import io.flutter.plugin.common.MethodChannel.Result;

/** DeviceInfoPlugin */
public class BlueInfoPlugin implements MethodCallHandler , RequestPermissionsResultListener  {
  private final Context context;
  private final BluetoothManager mBluetoothManager;
  private BluetoothAdapter mBluetoothAdapter;



  public static void registerWith(Registrar registrar) {
    final BlueInfoPlugin instance = new BlueInfoPlugin(registrar);
    registrar.addRequestPermissionsResultListener(instance);
  }

  BlueInfoPlugin(Registrar r){
    this.channel = new MethodChannel(registrar.messenger(), "blue.gds/blue_info");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {

    mBluetoothAdapter = mBluetoothManager.getAdapter();

    if(mBluetoothAdapter == null && !"isAvailable".equals(call.method)) {
      result.error("bluetooth_unavailable", "the device does not have bluetooth", null);
      return;
    }
      String resultState ="";
      if (mBluetoothAdapter.isEnabled()) {
          resultState="isOpen";
      } else {
          resultState="isNotOpen";
      }

      if (call.method.equals("getBlueStateInfo")) {
          result.success(resultState);
      }
      if (call.method.equals("getPlatformVersion")) {
          result.success("Android ${android.os.Build.VERSION.RELEASE}");
      }
      else {
          result.notImplemented();
      }
  }

}
