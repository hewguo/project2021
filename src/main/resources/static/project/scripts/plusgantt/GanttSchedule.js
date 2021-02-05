﻿/*
    GanttSchedule：甘特图功能逻辑模块
    描述：监听处理单元格编辑，以及条形图拖拽事件。
*/

function clearTime (date) {
    if (!date) return null;
    return new Date(date.getFullYear(), date.getMonth(), date.getDate());
}
function maxTime(date) {
    if (!date) return null;
    return new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59);
}

GanttSchedule = function (gantt) {
    this.gantt = gantt;
    //处理单元格编辑，和条形图拖拽事件
    gantt.on("cellbeginedit", this.__OnCellBeginEdit, this);
    gantt.on("aftercellcommitedit", this.__OnCellCommitEdit, this);
    gantt.on("itemdragstart", this.__OnItemDragStart, this);
    gantt.on("itemdragcomplete", this.__OnItemDragComplete, this);
    gantt.on("taskdragdrop", this.__OnTaskDragDrop, this);

    gantt.on("tasksync", function (e) {
        this._syncTask(e.task);
    }, this);
}
GanttSchedule.prototype = {
    __OnTaskDragDrop: function (e) {
        e.cancel = true;
        var dragRecords = e.tasks, targetRecord = e.targetTask, action = e.action;

        this.gantt.moveTasks(dragRecords, targetRecord, action);
    },
    __OnCellBeginEdit: function (e) {
        var task = e.record, field = e.field;
        if (task.Summary) {
            if (field == 'Start' || field == 'Finish' || field == 'Duration') {
                e.cancel = true;
            }
        }
    },
    __OnCellCommitEdit: function (e) {
        e.cancel = true;
        var task = e.record, field = e.field, value = e.value, oldValue = task[field];

        if (mini.isEquals(oldValue, value)) return;

        try {

            //!!!处理单元格编辑提交
            var gantt = this.gantt;

            //标记任务的属性被修改
            gantt.setTaskModified(task, field);

            if (e.column.displayField) {
                task[e.column.displayField] = e.text;
            }

            switch (field) {
                case "Duration":
                    task.Duration = value;

                    if (task.Start) {
                        task.Finish = maxTime(task.Start);
                        task.Finish.setDate(task.Finish.getDate() + task.Duration - 1);

                        gantt.setTaskModified(task, "Finish");
                    }

                    break;
                case "Start":
                    task.Start = clearTime(value);

                    if (task.Start) {
                        task.Finish = maxTime(task.Start);
                        task.Finish.setDate(task.Start.getDate() + task.Duration - 1);

                        gantt.setTaskModified(task, "Finish");
                    }

                    break;
                case "Finish":
                    task.Finish = maxTime(value);

                    if (task.Finish && task.Start) {
                        var days = parseInt((task.Finish - task.Start) / (3600 * 24 * 1000));
                        task.Duration = days + 1;

                        gantt.setTaskModified(task, "Duration");
                    }

                    break;
                case "PredecessorLink":
                    gantt.setLinks(task, value);
                    return;
                    break;
                default:
                    task[field] = value;

                    break;
            }

            this._syncTask(task);

            gantt.refresh();

        } catch (ex) {
            alert(ex.message);
        }
    },
    __OnItemDragStart: function (e) {
        if (e.action == "start") {
            e.cancel = true;
        }
    },
    __OnItemDragComplete: function (e) {
        var action = e.action, value = e.value, task = e.item;

        //!!!处理条形图拖拽完成
        var gantt = this.gantt;


        if (action == "finish") {
            gantt.setTaskModified(task, "Finish");

            task.Finish = maxTime(value);
            if (task.Finish && task.Start) {
                var days = parseInt((task.Finish - task.Start) / (3600 * 24 * 1000));
                task.Duration = days + 1;

                gantt.setTaskModified(task, "Duration");
            }
        }
        if (action == "percentcomplete") {
            gantt.setTaskModified(task, "PercentComplete");

            task.PercentComplete = value;

        }
        if (action == "move") {
            gantt.setTaskModified(task, "Start");

            task.Start = clearTime(value);

            if (task.Start) {
                task.Finish = maxTime(task.Start);
                task.Finish.setDate(task.Start.getDate() + task.Duration - 1);

                gantt.setTaskModified(task, "Finish");
            }
        }

        this._syncTask(task);

        gantt.refresh();
    },

    _syncTask: function (task) {
        if (!task) return;
        var gantt = this.gantt;

        //同步任务的父任务工期
        function getDuration(pnode) {
            var nodes = gantt.getChildTasks(pnode);
            var duration = 0;
            if (nodes && nodes.length > 0) {
                for (var i = 0, l = nodes.length; i < l; i++) {
                    var node = nodes[i];
                    var d = parseInt(node.Duration);
                    if (!isNaN(d)) {
                        duration += d;
                    }
                }
            }
            return duration;
        }

        //1)获取所有父级任务
        var ans = gantt.getAncestorTasks(task);
        ans.reverse();
        for (var i = 0, l = ans.length; i < l; i++) {
            var t = ans[i];
            //2)获取父任务下子任务的Duration之和
            var duration = getDuration(t);
            if (t.Duration != duration) {
                t.Duration = duration;
                gantt.setTaskModified(t, "Duration");
            }
        }

    }
};