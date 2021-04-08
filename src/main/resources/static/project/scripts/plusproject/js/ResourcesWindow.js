

var ResourcesWindow = function () {
    ResourcesWindow.superclass.constructor.call(this);
    this.initControls();
    this.initEvents();
}
mini.extend(ResourcesWindow, mini.Window, {
    title: "项目资源",
    width: 580,
    height: 320,
    bodyStyle: "padding:0;",
    allowResize: true,
    showModal: true,
    showToolbar: true,
    showFooter: true,
    initControls: function () {
        var toolbarEl = this.getToolbarEl();
        var footerEl = this.getFooterEl();
        var bodyEl = this.getBodyEl();

        //toolbar
        var labelId = this.id + "$label";
        var topHtml =
            '<div style="padding:5px;">'
                + '<a name="add" class="mini-button">增加</a> '
                + '<a name="remove" class="mini-button">删除</a> '
            + '</div>';
        jQuery(toolbarEl).append(topHtml);

        //footer
        var footerHtml =
            '<div style="padding:8px;text-align:center;">'
                + '<a name="okBtn" class="mini-button" style="width:60px;">确定</a>       '
                + '<span style="display:inline-block;width:25px;"></span>'
                + '<a name="cancelBtn" class="mini-button" style="width:60px;">取消</a>       '
            + '</div>';
        jQuery(footerEl).append(footerHtml);

        /////////////////////////////////////////////////////
        //body        


        this.grid = new mini.DataGrid();
        this.grid.set({
            
            multiSelect: true,
            style: "width: 100%;height: 100%;",
            borderStyle: "border:0",
            allowCellSelect: true,
            allowCellEdit: true,
            showPager: false,
            columns: [
                { type: "checkcolumn" },
                { header: "资源名称", field: "Name", width: 200,
                    editor: { type: "textbox" }

                },
                { header: "资源类型", field: "Type", type: "comboboxcolumn",
                    editor: {
                        type: "combobox",
                        valueField: "value", textField: "text",
                        data: [{ value: 0, text: "材料" }, { value: 1, text: "工时" }, { value: 2, text: "成本"}]
                    }
                },
                { header: "资源成本", field: "Cost",
                    editor: { type: "spinner", minValue: 0, maxValue: 999999999 }
                }
            ]
        });

        this.grid.render(bodyEl);

        var grid = this.grid;

        grid.on("drawcell", function (e) {
            if (e.record.Type == 2 && e.field == "Cost") {
                e.cellHtml = "";
            }
        });
        grid.on("cellbeginedit", function (e) {

            if (e.record.Type == 2 && e.field == "Cost") {
                e.cancel = true;
            }
        });

        /////////////////////////////////////////////////////

        //组件对象
        mini.parse(this.el);
        this._okBtn = mini.getbyName("okBtn", this);
        this._cancelBtn = mini.getbyName("cancelBtn", this);
        this._addBtn = mini.getbyName("add", this);
        this._removeBtn = mini.getbyName("remove", this);
    },
    initEvents: function () {
        this._addBtn.on("click", function (e) {
            var o = { UID: UUID(), Type: 1, Cost: 0, Name: "" };
            this.grid.addRow(o);
        }, this);
        this._removeBtn.on("click", function (e) {

            var records = this.grid.getSelecteds();

            if (confirm("确定删除选中的资源吗？")) {
                this.grid.removeRows(records);
            }

        }, this);

        /////////////////////////////////////
        this._okBtn.on("click", function (e) {
            var ret = true;
            if (this._Callback) ret = this._Callback('ok');
            if (ret !== false) {
                this.hide();
            }
        }, this);
        this._cancelBtn.on("click", function (e) {
            var ret = true;
            if (this._Callback) ret = this._Callback('cancel');
            if (ret !== false) {
                this.hide();
            }
        }, this);
        //        this.on("beforebuttonclick", function (e) {
        //            if (e.name == "close") {                
        //                e.cancel = true;
        //                var ret = true;
        //                if (this._Callback) ret = this._Callback('close');
        //                if (ret !== false) {
        //                    this.hide();
        //                }
        //            }
        //        }, this);
    },

    setData: function (data, project, callback) {
        this._Callback = callback;
        //this.grid.selects(data);
        var json = mini.encode(data);
        //alert(json);

        $.each(data, function (i, o) {
            if (o.Type == null) o.Type = 1;
            if (o.Cost == null) o.Cost = 0;
        });

        this.grid.setData(data);
        this.project = project;

    },
    getData: function () {
        var data = this.grid.getData();

        var list = [];
        $.each(data, function (i, o) {
            var r = { UID: o.UID, Name: o.Name, Type: o.Type, Cost: o.Cost };
            list.add(r);

            //if (!r.UID) r.UID = UUID();
        });

        //alert(mini.encode(list));

        return list;
    }
});
