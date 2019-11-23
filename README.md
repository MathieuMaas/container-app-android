# Cordova Android Pinned Shortcuts Plugin

This Cordova Plugin allows you to create and update Android Pinned shortcuts with a custom icon and name. 

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
## Important
The Mainactivity location changes each project, so make sure you target the right instance
## Pinned Shortcuts

### Create a pinned shortcut

This plugin provides you the following method to create pinned shortcuts (on runtime):

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

Use the following method check if android supports pinned shortcuts

````
AndroidShortcutsPlugin.supportsPinned(
	function (supported) {
		if (supported) {
		//create the pinned shortcut
		}
	});
});
````

