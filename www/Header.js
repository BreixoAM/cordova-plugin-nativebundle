var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');

function Header() {
};

Header.prototype.getTitle = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "headerGetTitle", []);
};

Header.prototype.setTitle = function(title, callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "headerSetTitle", [title]);
};

Header.prototype.setItemsColor = function(color, callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "headerSetItemsColor", [color]);
};

Header.prototype.showBackButton = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "headerShowBackButton", []);
};

Header.prototype.showSliderButton = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "headerShowSliderButton", []);
};

Header.prototype.clearOptions = function() {

	exec(function(){}, function(){}, "Tgw", "headerClearOptions", []);
	exec(function(){}, function(){}, "Tgw", "headerUpdate", []);
};

Header.prototype.setOptions = function(options) {

	exec(function(){}, function(){}, "Tgw", "headerClearOptions", []);

	for (i = 0; i < options.length; i++) {
		option = options[i];
		exec(option.callback, function(){}, "Tgw", "headerAddOption", [option.title]);
	}

	exec(option.callback, function(){}, "Tgw", "headerUpdate", []);
};

Header.prototype.clearActions = function() {

	exec(function(){}, function(){}, "Tgw", "headerClearActions", []);
	exec(option.callback, function(){}, "Tgw", "headerUpdate", []);
};

Header.prototype.setActions = function(actions) {

	exec(function(){}, function(){}, "Tgw", "headerClearActions", []);

	for (i = 0; i < actions.length; i++) {
		action = actions[i];
		exec(action.callback, function(){}, "Tgw", "headerAddAction", [action.title, action.icon]);
	}

	exec(action.callback, function(){}, "Tgw", "headerUpdate", []);
};

module.exports = Header;
