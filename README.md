# cordova-plugin-nativebundle
This plugin provides a native header and slider menu, and allows you to controll them from Javascript (appearance and behavior).

## Supported Platforms
* Android 4.1 or higher.

## Installation

**New project:**
```
cordova platform add https://github.com/BreixoAM/cordova-android-compat
cordova plugin add https://github.com/BreixoAM/cordova-plugin-nativebundle
```

**Existing project:**
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

## Complex example

App on [google play](https://play.google.com/store/apps/details?id=com.breixoam.nativebundleexample)

Source code on [github](https://github.com/BreixoAM/nativebundle-example)

#API

## Tgw
- [.init()](#tgwinitcallback-options)

## Frame

- [.header](#frameheader)
- [.slider](#frameslider)
- [.content](#framecontent)
- [.getMainColor()](#framegetmaincolor)
- [.setMainColor()](#framesetmaincolormaincolor)
- [.getMainColorDark()](#framegetmaincolordark)
- [.setMainColorDark()](#framesetmaincolordarkmaincolordark)
- [.showSpinner()](#frameshowspinner)
- [.hideSpinner()](#framehidespinner)

## Header

- [.getTitle()](#headergettitle)
- [.setTitle()](#headersettitletitle)
- [.setItemsColor()](#headersetitemscolor)
- [.showBackButton()](#headershowbackbutton)
- [.showSliderButton()](#headershowsliderbutton)
- [.clearOptions()](#headerclearoptions)
- [.setOptions()](#headersetoptionsoptions)
- [.clearActions()](#headerclearactions)
- [.setActions()](#headersetactionsactions)

## Slider

- [.setColor(color)](#slidersetcolorcolor)
- [.getTitle1()](#slidergettitle1)
- [.setTitle1(title)](#slidersettitle1title)
- [.getTitle2()](#slidergettitle2)
- [.setTitle2(title)](#slidersettitle2title)
- [.setIcon()](#sliderseticoncharcolor1color2)
- [.setOptions()](#slidersetoptionsoptions)

## tgw.init(callBack, options)

Initializes the plugin on the native side.

**callBack:** will be called with frame object to manipulate the native elements.

**options:** init object.

- options.mainColor: header color.
- options.mainColorDark: status bar color.
- options.title: header title.

## frame.header

Header object.

## frame.slider

Slider object.

## frame.content

Content object

## frame.getMainColor()

Return header color.

## frame.setMainColor(mainColor)

Update header color.

**mainColor:** String. Example: '#F44336'

## frame.getMainColorDark()

Return statusbar color.

## frame.setMainColorDark(mainColorDark)

Update statusbar color.

**mainColorDark:** String. Example: '#C62828'

## frame.showSpinner()

Show native dialog spinner.

## frame.hideSpinner()

Hide native dialog spinner.

## header.getTitle()

Return header title.

## header.setTitle(title)

Update header title.

**title:** String. Example: 'New title'

## header.setItemsColor(color)

Update color of title and buttons.

**color:** String. Example: '#FFFFFF'

## header.showBackButton()

Display back button and hide slider button.

## header.showSliderButton()

Display slidder button and hide back button.

## header.setOptions(options)

Display options on menu header.

**options:** array of objects.

- options.title: Option title.
- options.callback: function executed when the option is pressed.

Example:
```
[
	{
	    'title': 'Deatils option',
	    'callback': function() {
	        setTimeout(function(){ alert('Details option'); }, 250);
	    }
	}
]
```

## header.clearOptions()

Empty options.

## header.setActions(actions)

Display actions on header.

**actions:** array of objects.

- actions.title: Option title.
- actions.icon: Icon from icons list.
- actions.callback: function executed when the action is pressed.

Example:
```
[
	{
	    'title': 'Search',
	    'icon': 'search',
	    'callback': function() {
	        setTimeout(function(){ alert('Search'); }, 250);
	    }
	}
]
```

## header.clearActions()

Empty actions.

## slider.setColor(color)

Update slider menu header color.

**color:** String. Example: '#F44336'

## slider.getTitle1()

Return header menu title1.

## slider.setTitle1(title)

Update header menu title1.

**title:** String. Example: 'New title 1'

## slider.getTitle2()

Return header menu title2.

## slider.setTitle2(title)

Update header menu title2.

**title:** String. Example: 'New title 2'

## slider.setIcon(char, color1, color2)

Update circle icon on menu header.

**char:** String. Example: 'A'

**color1:** String. String clor. Example: '#F44336'

**color2:** String. Circle color. Example: '#F44336'

## slider.setOptions(options)

Display options on slider.

**options:** array of objects.

- option.title: Option title.
- option.icon: Option icon.
- option.check: Init item as checked.
- option.callback: function executed when the option is pressed.

Example:
```
[
	{
		'icon': 'dashboard',
		'title': 'Dashboard',
		'checked': 0,
		'callback': function(data) {
			alert('Dashboard');
		}
	},
	{
		'icon': 'build',
		'title': 'Build',
		'checked': 0,
		'callback': function(data) {
			alert('Build');
		}
	}
]
```
