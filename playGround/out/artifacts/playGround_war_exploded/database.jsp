<html>
<head>
    <title>database</title>
    <link rel="stylesheet" type="text/css" href="../easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyUI/themes/icon.css">
    <script type="text/javascript" src="../easyUI/jquery.min.js"></script>
    <script type="text/javascript" src="../easyUI/jquery.easyui.min.js"></script>
    <script>
        var count = 0;
        $(document).ready(function() {
            $('#myTree').tree({
                // checkbox: true,
                animate: true,
                lines: true,
                url:"/loadTreeJson",  //默认会将节点的id传递到后台
                loadFilter: function(data){
                    return data.treeJson;
                },
                onClick: function (node) {
                    $.ajax("/openFile", {
                        type: "POST"
                        , cache: false
                        , dataType: "json"
                        , data: {
                            node: node.text,
                            path: node.attributes.path
                        }
                        , success: function(ret) {
                                var replyContent = ret.textcontent;
                                var filename = ret.filename;
                                var content = '<textarea style="font-size:20px;height:100%;width:100%;border: 1px solid #D4D4D4;">'+replyContent+' </textarea>'
                                $('#editPanel').tabs('add',{
                                    title:filename,
                                    content:content,
                                    closable:true
                                });
                        }
                    });
                }
            });
        });
        var addfunc = function() {
            var content = '<input class="easyui-textbox" label="Edit text here:" labelPosition="top" multiline="true" style="width:100%;height:100%">';
            $('#editPanel').tabs('add',{
                title:"undefined-"+count,
                content:content,
                closable:true
            });
            count++;
        }
        var savefunc = function () {

        }
    </script>
</head>
<body>
    <p>Knowledge Base Control</p>
    <div style="padding:5px 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addfunc()">Add</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="savefunc()">Save</a>
    </div>
    <div class="easyui-layout" style="width:1300px;height:550px;">
        <div data-options="region:'west',split:true,border:false" title="DataBase" style="width:200px">
            <ul id="myTree" class="easyui-tree"></ul>
        </div>
        <div id="editPanel" class="easyui-tabs" data-options="region:'center'">
        </div>
    </div>
</body>
</html>
