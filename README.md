# Cordova Android Pinned Shortcuts Plugin

This Cordova Plugin allows you to create and update Android Static Shortcuts saving in memory the selected one and get this action to do something in the base application. Tested on ionic 1, 2 and 3 based projects.

## Installing plugin

Install the plugin with the following command:

````
cordova plugin add https://github.com/MathieuMaas/container-app-android.git
````

To improve the app launch performance you should set the following code into config.xml file:

````
... <platform name="android">
        <preference name="AndroidLaunchMode" value="singleInstance" />
    ...
````

## Pinned Shortcuts

### Create a pinned shortcut

This plugin provides you the following method to create shortcuts dynamically (on runtime):

````
AndroidShortcutsPlugin.createPinnedShortcut(
    {
        id: 'someID',
        action: 'someAction',
        shortLabel: 'ShortLabel', //String. This is the shortcut shown when user long press over the icon when it is located in the home screen.
        longLabel: 'LongLabel', //String. This is the shortcut shown when user long press over the icon when it is located in the app drawer.
        icon: 'BASE64_String_icon', //String, should be the base64 icon or the name of this without extension. 
    },
    successCallback,
    errorCallback
);
````

### Check if android supports

Use the following method to remove all dynamic shortcuts. The static shortcuts will not be affected:

````
AndroidShortcutsPlugin.removeAllDynamicShortcuts(
    successCallback,
    errorCallback
);````

