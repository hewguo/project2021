<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>WEB 甘特图显示编辑</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <link href="../project/css/core.css" rel="stylesheet" type="text/css" />
    <script src="../project/scripts/core.js" type="text/javascript"></script>
    <script src="../project/scripts/boot.js" type="text/javascript"></script>
    <link href="../project/scripts/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
    <link href="../project/js/ProgressLine.css" rel="stylesheet" type="text/css" />
    <script src="../project/js/ProgressLine.js" type="text/javascript"></script>
    <script src="../project/js/dom-to-image.js" type="text/javascript"></script>
    <script src="../project/js/export.js" type="text/javascript"></script>
</head>
<body>
<#--<#if mppUrl?contains("http://") || mppUrl?contains("https://")>-->
<#--    <#assign finalUrl="${mppUrl}">-->
<#--<#else>-->
<#--    <#assign finalUrl="${baseUrl}${mppUrl}">-->
<#--</#if>-->

        <div style="padding-bottom:5px;">
            顶层时间刻度：
            <select style="margin-right:20px;" onchange="changeTopTimeScale(this.value)">
                <option value="year">年</option>
                <option value="halfyear">半年</option>
                <option value="quarter">季度</option>
                <option value="month">月</option>
                <option value="week" selected>周</option>
                <option value="day">日</option>
                <option value="hour">时</option>
            </select>
            底层时间刻度：
            <select onchange="changeBottomTimeScale(this.value)" style="margin-right:20px;" >
                <option value="year">年</option>
                <option value="halfyear">半年</option>
                <option value="quarter">季度</option>
                <option value="month">月</option>
                <option value="week">周</option>
                <option value="day" selected>日</option>
                <option value="hour">时</option>
            </select>
            <label><input type="checkbox" onclick="toggleProgressLine(this.checked)" style="position:relative;top:2px;" />显示进度线</label>
        </div>

        <div class="mini-toolbar" style="border-bottom:0;">
            <a class="mini-button" plain="true" iconCls="icon-reload"  onclick="load()">加载</a><a class="mini-button" plain="true" iconCls="icon-save" onclick="save()">保存</a><a class="mini-button" plain="true" iconCls="icon-print" onclick="printGantt()">打印</a><span class="separator"></span><a class="mini-button" plain="true" iconCls="icon-add" onclick="addTask()">增加</a><a class="mini-button" plain="true" iconCls="icon-edit" onclick="updateTask()">修改</a><a class="mini-button" plain="true" iconCls="icon-remove" onclick="removeTask()">删除</a><span class="separator"></span><a class="mini-button" plain="true" iconCls="icon-upgrade" onclick="upgradeTask()">升级</a><a class="mini-button" plain="true" iconCls="icon-downgrade" onclick="downgradeTask()">降级</a><span class="separator"></span><a class="mini-button" plain="true" iconCls="icon-lock" onclick="onLockClick" checkOnClick="true">锁定列</a><span class="separator"></span><a class="mini-button" plain="true" iconCls="icon-zoomin" onclick="zoomIn()">放大</a><a class="mini-button" plain="true" iconCls="icon-zoomout" onclick="zoomOut()">缩小</a><span class="separator"></span><a class="mini-button" iconCls="icon-node" onclick="editTaskByTaskWindow()">任务</a><a class="mini-button" iconCls="icon-date" onclick="showCalendars()">日历</a><a class="mini-button" iconCls="icon-node" onclick="showResources()">资源</a>
        </div>

        <div id="viewCt">

        </div>
</body>
</html>

<script type="text/javascript">
    mini.parse();
    /* 创建项目甘特图对象，设置列配置，创建右键菜单和任务面板
    -----------------------------------------------------------------------------*/
    //alert(window.screen.height);
    //alert(document.body.offsetHeight);
    var project = new PlusProject();
    project.setStyle("width:100%;height:"+(window.innerHeight-63)+"px");
    project.setColumns([
        new PlusProject.IDColumn(),
        new PlusProject.StatusColumn(),
        new PlusProject.ManualColumn(),
        new PlusProject.NameColumn(),
        new PlusProject.PredecessorLinkColumn(),
        new PlusProject.PercentCompleteColumn(),
        new PlusProject.DurationColumn(),
        new PlusProject.StartColumn(),
        new PlusProject.FinishColumn(),
        new PlusProject.WorkColumn(),
        //new PlusProject.DepartmentColumn(), //部门、负责人暂时没有
        //new PlusProject.PrincipalColumn(),
        new PlusProject.AssignmentsColumn()
    ]);
    project.render(document.getElementById("viewCt"));

    //project.setAllowDragDrop(true);

    project.enableManualSchedule = true;        //启用手动模式

    project.on('taskdblclick',onTaskDblClick);

    //根据浏览器窗口大小调整project主界面大小
    window.onresize=function(){
        // alert(document.body.clientHeight);
        // alert(window.screen.availHeight);
        // alert(window.innerHeight);
        //project.setStyle("width:100%;height:400px");
        project.setStyle("width:100%;height:"+(window.innerHeight-63)+"px");
    }

    //创建右键菜单
    var menu = new ProjectMenu();
    project.setContextMenu(menu);
    menu.edit.on("click", function (e) {
        ShowTaskWindow(project);
    });

    //进度线
    var progressLine = new ProgressLine(project, {
        visible: false,
        getProjectDate: function () {
            return new Date(2007, 0, 10);
        },
        getTaskDate: function (task) {
            if (task.Start && task.Finish) {
                var time = task.Finish - task.Start;
                time = time * task.PercentComplete / 100;
                var date = new Date(task.Start.getTime() + time);
                return date;
            }

            //return task.Start;
        }
    });

    function toggleProgressLine(checked) {
        progressLine.setVisible(checked);
    }



    function load() {
        LoadJSONProject("/projectfilereader", project);
    }
    load();


    /* 业务代码：加载、保存、调试项目，增、删、改、升级、降级任务
    -----------------------------------------------------------------------------*/

    function track() {
        TrackProject(project);
    }
    function save() {
        alert("网站示例不提供保存，产品开发包内有此功能！");
        //SaveProject(project);
        SaveJSONProject("/projectfilewriter",project);
    }

    function addTask() {
        var newTask = project.newTask();
        newTask.Name = '<新增任务>';    //初始化任务属性

        var selectedTask = project.getSelected();
        if (selectedTask) {
            project.addTask(newTask, "before", selectedTask);   //插入到到选中任务之前
            //project.addTask(newTask, "add", selectedTask);       //加入到选中任务之内
        } else {
            project.addTask(newTask);
        }
    }
    function removeTask() {
        var task = project.getSelected();
        if (task) {
            if (confirm("确定删除任务 \"" + task.Name + "\" ？")) {
                project.removeTask(task);
            }
        } else {
            alert("请选中任务");
        }
    }
    function updateTask() {
        ShowTaskWindow(project);
    }
    function upgradeTask() {
        var task = project.getSelected();
        if (task) {
            project.upgradeTask(task);
        } else {
            alert("请选选中任务");
        }
    }
    function downgradeTask() {
        var task = project.getSelected();
        if (task) {
            project.downgradeTask(task);
        } else {
            alert("请选选中任务");
        }
    }


    function changeTopTimeScale(value) {
        project.setTopTimeScale(value)
    }
    function changeBottomTimeScale(value) {
        project.setBottomTimeScale(value)
    }
    function zoomIn() {
        project.zoomIn();
    }
    function zoomOut() {
        project.zoomOut();
    }
    function showResources() {
        ShowResourcesWindow(project);
    }
    function showCalendars() {
        ShowCalendarWindow(project);
    }
    function editTaskByTaskWindow() {
        ShowTaskWindow(project);
    }

    function frozenColumn() {
        project.frozenColumn(0, 2);
    }
    function unfrozenColumn() {
        project.unfrozenColumn();
    }
    function onLockClick(e) {
        var checked = this.getChecked();
        if (checked) {
            project.frozenColumn(0, 2);
        } else {
            project.unfrozenColumn();
        }
    }

    function printGantt() {
        exportImage(project);
    }

    //任务双击打开编辑窗口
    function onTaskDblClick(e) {
        //var project=e.source;
        var task = e.task;
        if(task){
            ShowTaskDubleWindow(task,project);
            //project.upgradeTask(task);
        }
    }

</script>