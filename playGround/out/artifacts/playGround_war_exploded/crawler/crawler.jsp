<html>
<head>
    <title>Web Crawler</title>
    <link rel="stylesheet" type="text/css" href="../easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyUI/themes/icon.css">
    <script type="text/javascript" src="../easyUI/jquery.min.js"></script>
    <script type="text/javascript" src="../easyUI/jquery.easyui.min.js"></script>
    <script>
        var newfunc = function () {
            var seed = document.getElementById('seedURL').value;
            var tnum = document.getElementById('tNum').value;
            console.log(">>>>>",seed);
            $.ajax("/setupCraw", {
                type: "POST"
                , cache: false
                , dataType: "json"
                , data: {
                    seed: seed,
                    tnum: tnum
                }
                , success: function(ret) {
                }
            });
        }
    </script>
</head>
<body>
   <p>Web Crawler Control</p>
   <div style="padding:5px 0;">
       <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="newfunc()">New</a>
   </div>
   <div>
       <p>Seed</p><input id="seedURL" class="easyui-textbox" iconWidth="28" style="width:300px;height:34px;padding:10px;">
       <p>Thread Number</p><input id="tNum" class="easyui-textbox" iconWidth="28" style="width:300px;height:34px;padding:10px;">
   </div>
</body>
</html>
