var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');

function Content() {
};

Content.prototype.enableBounce = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "contentEnableBounce", []);
};

Content.prototype.disableBounce = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "contentDisableBounce", []);
};

Content.prototype.showScrollbar = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "contentShowScrollbar", []);
};

Content.prototype.hideScrollbar = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "contentHideScrollbar", []);
};

Content.prototype.fadeIn = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "contentFadeIn", []);
};

Content.prototype.fateOut = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "contentFadeOut", []);
};

Content.prototype.show = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "contentShow", []);
};

Content.prototype.hide = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "contentHide", []);
};

Content.prototype.clearHistory = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "contentClearHistory", []);
};

module.exports = Content;
