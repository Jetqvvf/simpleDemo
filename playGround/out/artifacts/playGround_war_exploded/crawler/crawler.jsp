<html>
<head>
    <title>Web Crawler</title>
    <link rel="stylesheet" type="text/css" href="../easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyUI/themes/icon.css">
    <script type="text/javascript" src="../easyUI/jquery.min.js"></script>
    <script type="text/javascript" src="../easyUI/jquery.easyui.min.js"></script>
    <script>
        var seconds = 0;
        var flag = false;

        function showtime() {
            document.getElementById('time').innerHTML = seconds + " seconds";
            seconds = seconds + 1;

            if(!flag) {
                setTimeout("showtime();", 1000)
                $.ajax("/getCrawlstat", {
                    type: "POST"
                    , cache: false
                    , dataType: "json"
                    , async: true
                    , data: {
                    }
                    , success: function(ret) {
                        document.getElementById('totalProcessedPages').innerHTML =  ret.totalProcessedPages;
                        document.getElementById('totalLinks').innerHTML =  ret.totalLinks;
                        document.getElementById('totalTextSize').innerHTML =  ret.totalTextSize;
                        document.getElementById('totalDownloadedPages').innerHTML =  ret.totalDownloadedPages;
                    }
                });
            }
        }

        var newfunc = function () {
            var seed = document.getElementById('seedURL').value;
            var tnum = document.getElementById('tNum').value;
            var pre = document.getElementById('preProcess').value;
            var maxpage = document.getElementById('maxPage').value;
            var maxdepth = document.getElementById('maxDepth').value;
            document.getElementById('status').innerText = "Processing";
            showtime();

            $.ajax("/setupCraw", {
                type: "POST"
                , cache: false
                , dataType: "json"
                , data: {
                    seed: seed,
                    tnum: tnum,
                    maxpage: maxpage,
                    maxdepth: maxdepth,
                    pre: pre
                }
                , success: function(ret) {
                    console.log("sssssssssssss");
                    flag = true;
                    document.getElementById('status').innerText = "SUCCESS!";
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
   <hr />
   <div>
       <table>
           <tr>
               <td>Seed URL</td>
               <td><input id="seedURL" class="easyui-textbox" iconWidth="28" style="width:300px;height:34px;padding:10px;" value="https://bbs.hupu.com/20033332.html"></td>
           </tr>
           <tr>
               <td>Max Page Number</td>
               <td><input id="maxPage" class="easyui-textbox" iconWidth="28" style="width:300px;height:34px;padding:10px;" value="100"></td>
           </tr>
           <tr>
               <td>Max Depth</td>
               <td><input id="maxDepth" class="easyui-textbox" iconWidth="28" style="width:300px;height:34px;padding:10px;" value="4"></td>
           </tr>
           <tr>
               <td>Thread Number</td>
               <td><input id="tNum" class="easyui-textbox" iconWidth="28" style="width:300px;height:34px;padding:10px;" value="1"></td>
           </tr>
           <tr>
               <td>Pre-process</td>
               <td><input id="preProcess" class="easyui-switchbutton" checked></td>
           </tr>
       </table>
   </div>
   <div id="status"></div>
   <div id="p" class="easyui-panel" title="Result Panel" style="width:100%;height:200px;padding:10px">
       <table>
           <tr>
               <td>Time</td>
               <td><span id="time"></span></td>
           </tr>
           <tr>
               <td>Number of ProcessedPages</td>
               <td><span id="totalProcessedPages"></span></td>
           </tr>
           <tr>
               <td>Number of Links</td>
               <td><span id="totalLinks"></span></td>
           </tr>
           <tr>
               <td>Number of TextSize</td>
               <td><span id="totalTextSize"></span></td>
           </tr>
           <tr>
               <td>Number of DownloadedPages</td>
               <td><span id="totalDownloadedPages"></span></td>
           </tr>
       </table>
   </div>
</body>
</html>
