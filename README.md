# cordova-plugin-nativebundle
This plugin provides a native header and slider menu, and allows you to controll them from JavaScript (appearance and behavior).

## Supported Platforms
* Android

## Installation

# New project:
```
cordova platform add https://github.com/BreixoAM/cordova-android-compat
cordova plugin add https://github.com/BreixoAM/cordova-plugin-nativebundle
```

# Existing project:
```
cordova platform update https://github.com/BreixoAM/cordova-android-compat
cordova plugin add https://github.com/BreixoAM/cordova-plugin-nativebundle
```

## Quick Example
```
	var options = {
	    'mainColor': '#F44336',
	    'mainColorDark': '#C62828',
	    'title': 'Native Bundle Example'
	};
	var callback = function(frame) {
	    frame.header.setTitle('New title');
	};
	tgw.init(callback, options);
```

#API

## Tgw
- [.init()](#tgw-init)

## Frame

- [.header](#frame-header)
- [.slider](#frame-slider)
- [.content](#frame-content)
- [.getMainColor()](#frame-getMainColor)
- [.setMainColor()](#frame-setMainColor)
- [.getMainColorDark()](#frame-getMainColorDark)
- [.setMainColorDark()](#frame-setMainColorDark)
- [.showSpinner()](#frame-showSpinner)
- [.hideSpinner()](#frame-hideSpinner)

## Header

- [.getTitle()](#header-getTitle)
- [.setTitle()](#frame-setTitle)
- [.setItemsColor()](#frame-setItemsColor)
- [.showBackButton()](#frame-showBackButton)
- [.showSliderButton()](#frame-showSliderButton)
- [.clearOptions()](#frame-clearOptions)
- [.setOptions()](#frame-setOptions)
- [.clearActions()](#frame-clearActions)
- [.setActions()](#frame-setActions)

## Slider

- [.setColor()](#header-setColor)
- [.getTitle1()](#frame-getTitle1)
- [.setTitle1()](#frame-setTitle1)
- [.setTitle2()](#frame-setTitle2)
- [.setIcon()](#frame-setIcon)
- [.setOptions()](#frame-setOptions)

