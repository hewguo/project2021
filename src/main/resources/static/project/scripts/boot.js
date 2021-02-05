__CreateJSPath = function (js) {
    var scripts = document.getElementsByTagName("script");
    var path = "";
    for (var i = 0, l = scripts.length; i < l; i++) {
        var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            var ss = src.split(js);
            path = ss[0];
            break;
        }
    }
    var href = location.href;
    href = href.split("#")[0];
    href = href.split("?")[0];
    var ss = href.split("/");
    ss.length = ss.length - 1;
    href = ss.join("/");

    if (path.indexOf("http:") == -1 && path.indexOf("file:") == -1) {
        path = href + "/" + path;
    }
    return path;
}

var bootPATH = __CreateJSPath("boot.js");

document.write('<script src="' + bootPATH + 'jquery.js" type="text/javascript"></script>');

document.write('<link href="' + bootPATH + 'miniui/themes/icons.css" rel="stylesheet" type="text/css" />');

document.write('<link href="' + bootPATH + 'miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />');

document.write('<script src="' + bootPATH + 'miniui/miniui.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'miniui/locale/zh_CN.js" type="text/javascript"></script>');

//project
document.write('<script src="' + bootPATH + 'plusproject/js/CalendarWindow.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'plusproject/js/ProjectMenu.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'plusproject/js/StatusColumn.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'plusproject/js/TaskWindow.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'plusproject/js/ResourcesWindow.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'plusproject/js/ProjectServices.js" type="text/javascript"></script>');

document.write('<script src="' + bootPATH + 'ThirdLibs/swfobject/swfobject.js" type="text/javascript"></script>');

//gantt
document.write('<script src="' + bootPATH + 'plusgantt/GanttService.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'plusgantt/GanttMenu.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'plusgantt/GanttSchedule.js" type="text/javascript"></script>');

//rgantt
document.write('<script src="' + bootPATH + 'rgantt/RGanttMenu.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'rgantt/RGanttSchedule.js" type="text/javascript"></script>');