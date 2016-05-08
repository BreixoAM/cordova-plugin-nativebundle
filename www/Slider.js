var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');

function Slider() {
};

Slider.prototype.setColor = function(color, callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "sliderSetColor", [color]);
};

Slider.prototype.getTitle1 = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "sliderGetTitle1", []);
};

Slider.prototype.setTitle1 = function(title, callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "sliderSetTitle1", [title]);
};

Slider.prototype.getTitle2 = function(callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "sliderGetTitle2", []);
};

Slider.prototype.setTitle2 = function(title, callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "sliderSetTitle2", [title]);
};

Slider.prototype.setIcon = function(char, color1, color2, callback) {
	if (!callback) callback = function() {};
	exec(callback, function(){}, "Tgw", "sliderSetIcon", [char, color1, color2]);
};

Slider.prototype.setOptions = function(options) {

	exec(function(){}, function(){}, "Tgw", "sliderClearOptions", []);

	for (i = 0; i < options.length; i++) {
		option = options[i];
		exec(option.callback, function(){}, "Tgw", "sliderAddOption", [option.icon, option.title, option.checked]);
	}

	exec(option.callback, function(){}, "Tgw", "sliderUpdate", []);
};


module.exports = Slider;
