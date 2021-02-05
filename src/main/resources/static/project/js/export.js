
function exportImage(gantt) {

    $('<div class="printing" style="background:gray;opacity:0.35;position:fixed;left:0;top:0;width:100%;height:100%;cursor:wait;z-index:99999;"></div>').appendTo("body");


    var options = options || {},
        oldWidth = gantt.el.style.width,
        oldHeight = gantt.el.style.height,
    //oldTableWidth = 
            topTimeScale = gantt.ganttView.topTimeScale,
            bottomTimeScale = gantt.ganttView.bottomTimeScale;

    var headerHeight = gantt.tableView.getHeaderHeight(),
    //rowsHeight = gantt.rowHeight * gantt.getTaskList().length,          //所有任务高度
            rowsHeight = gantt.rowHeight * gantt.tasks.getVisibleRows().length,        //视图任务高度
            height = headerHeight + rowsHeight + 25,

            scrollTop = gantt.tableView.getScrollTop(),
            _tableWidth = gantt.pane1.size,
            tableWidth = gantt.getPaneEl(1).offsetWidth,
            width = tableWidth + gantt.ganttView.scrollWidth + 50;

    if (!gantt.ganttViewExpanded || !gantt.showGanttView) width = width - gantt.ganttView.scrollWidth + 50;
    if (!gantt.tableViewExpanded || !gantt.showTableView) {
        width = width - tableWidth;
        tableWidth = 0;
    }

    gantt.setTableViewWidth(tableWidth);
    //gantt.setGanttViewWidth('100%');
    gantt.setWidth(width);
    gantt.setHeight(height);
    gantt.refresh();

    setTimeout(function () {

        doPrint(function () {

            //setTimeout(function () {
                gantt.setWidth(oldWidth);
                gantt.setHeight(oldHeight);
                gantt.setTableViewWidth(_tableWidth);

                gantt.setScrollTop(scrollTop, true);
            //}, 200);

        });

    }, 500);

    function doPrint(callback) {
        var node = gantt.el;
        domtoimage.toPng(node)
            .then(function (dataUrl) {
                var img = new Image();
                img.src = dataUrl;
                //document.body.appendChild(img);

                //window.open(img.src);
               // tempwindow.location = img.src;
                tempwindow.document.body.appendChild(img);
                $(".printing").remove();
                callback();
            });

        var tempwindow = window.open('');
    }
}
