function AndroidShortcutsPlugin() {
}

AndroidShortcutsPlugin.prototype.createDynamicShortcut = function (params, successCallback, errorCallback) {
  cordova.exec(
    successCallback,
    errorCallback,
    "AndroidShortcutsPlugin",
    "createDynamicShortcut",
    [params]
  );
};
AndroidShortcutsPlugin.prototype.supportsPinned = function (params, successCallback, errorCallback) {
  cordova.exec(
    successCallback,
    errorCallback,
    "AndroidShortcutsPlugin",
    "supportsPinned",
    []
  );
};
var AndroidShortcutsPlugin = new AndroidShortcutsPlugin();
module.exports = AndroidShortcutsPlugin;
