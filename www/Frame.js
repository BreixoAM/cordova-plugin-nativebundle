var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');

function Frame() {
};

Frame.prototype.getHeader = function() {
	return this.header;
};

Frame.prototype.getSlider = function() {
	return this.slider;
};

Frame.prototype.getContent = function() {
	return this.content;
};

Frame.prototype.getMainColor = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "frameGetMainColor", []);
};

Frame.prototype.setMainColor = function (mainColor, callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "frameSetMainColor", [mainColor]);
};

Frame.prototype.getMainColorDark = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "frameGetMainColorDark", []);
};

Frame.prototype.setMainColorDark = function(mainColorDark, callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "frameSetMainColorDark", [mainColorDark]);
};

Frame.prototype.showSpinner = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "frameShowSpinner", []);
};

Frame.prototype.hideSpinner = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "frameHideSpinner", []);
};

module.exports = Frame;
