
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
import android.content.pm.PackageManager;

import android.content.Intent;

/** DeviceInfoPlugin */
public class BlueInfoPlugin implements MethodCallHandler   {

  private final BluetoothManager mBluetoothManager;
  private BluetoothAdapter mBluetoothAdapter;
  private final MethodChannel channel;
  private final Registrar registrar;
  private static final int REQUEST_COARSE_LOCATION_PERMISSIONS = 1452;



  public static void registerWith(Registrar registrar) {
    final BlueInfoPlugin instance = new BlueInfoPlugin(registrar);
  }

  BlueInfoPlugin(Registrar r){
    this.registrar = r;
    this.channel = new MethodChannel(registrar.messenger(), "blue.gds/blue_info");
    this.mBluetoothManager = (BluetoothManager) r.activity().getSystemService(Context.BLUETOOTH_SERVICE);
    this.mBluetoothAdapter = mBluetoothManager.getAdapter();
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {

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
      if (call.method.equals("EnableBluetooth")) {
        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
        }
      }
      if (call.method.equals("CloseBluetooth")) {
        mBluetoothAdapter.disable();
      }
      else {
          result.notImplemented();
      }
  }

}
