package fc.shortcuts;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.util.Base64;

import androidx.core.content.pm.ShortcutManagerCompat;
//Change appname to your config app name
import nl.dewiOnline.app.MainActivity;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AndroidShortcutsPlugin extends CordovaPlugin {

    private static final String TAG = "AndroidShortcutsPlugin";
    private Context context = null;
    public AndroidShortcutsPlugin(){}

    @TargetApi(26)
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) {
        context = this.cordova.getActivity().getApplicationContext();
        if("supportsPinned".equals(action)) {
            boolean supported = this.supportsPinned();
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,supported));

        } else if("createDynamicShortcut".equals(action)) {
            ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
            Intent intent = new Intent(context, MainActivity.class);

            try {
                JSONObject jsonObject = new JSONObject(args.getString(0));
                intent.setAction(jsonObject.getString("action"));
                String icon = jsonObject.getString("icon");
                ShortcutInfo shortcutInfo = null;

                    byte[] decodedString = Base64.decode(icon, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    shortcutInfo = new ShortcutInfo.Builder(context, jsonObject.getString("id"))
                            .setShortLabel(jsonObject.getString("shortLabel"))
                            .setLongLabel(jsonObject.getString("longLabel"))
                            .setIcon(Icon.createWithBitmap(decodedByte))
                            .setIntent(intent)
                            .build();
                if(shortcutManager.getPinnedShortcuts().size() >= 1) {
                    ArrayList <ShortcutInfo> list = new ArrayList<ShortcutInfo>();
                    list.add(shortcutInfo);
                    shortcutManager.updateShortcuts(list);
                    callbackContext.success();
                    return true;
                }
                Intent pinShortcutCallback = shortcutManager.createShortcutResultIntent(shortcutInfo);
                PendingIntent successCallback = PendingIntent.getBroadcast(context, 0, pinShortcutCallback,0);
                shortcutManager.requestPinShortcut(shortcutInfo, successCallback.getIntentSender());


                callbackContext.success();
            } catch (JSONException e) {
                e.printStackTrace();
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
            }

        } else {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
        }
        return true;
    }

    private boolean supportsPinned(){
        Context context = this.cordova.getActivity().getApplicationContext();
        return ShortcutManagerCompat.isRequestPinShortcutSupported(context);
    }
}
