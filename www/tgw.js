var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');

function Tgw() {
}

Tgw.prototype.init = function(successCallback, options) {
	var arguments = [
		options.colorPrimary,
		options.colorPrimaryDark,
		options.title
	];
	var callback = function() {
		var frame = new tgw.Frame();
		frame.header = new tgw.Header();
		frame.slider = new tgw.Slider();
		frame.content = new tgw.Content();
		successCallback(frame);
	}
    exec(callback, function(){}, "Tgw", "init", arguments);
};

module.exports = new Tgw;
