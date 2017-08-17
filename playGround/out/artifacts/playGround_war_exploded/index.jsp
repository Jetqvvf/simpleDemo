<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
   <head>
       <title>learn use easyUI</title>
       <script type="text/javascript" src="${pageContext.request.contextPath}/easyUI/jquery.min.js"></script>
       <script type="text/javascript" src="${pageContext.request.contextPath}/easyUI/jquery.easyui.min.js"></script>
       <script type="text/javascript" src="${pageContext.request.contextPath}/easyUI/locale/easyui-lang-zh_CN.js"></script>
       <link rel="stylesheet" href="${pageContext.request.contextPath}/easyUI/themes/default/easyui.css" type="text/css"/>
       <link rel="stylesheet" href="${pageContext.request.contextPath}/easyUI/themes/icon.css" type="text/css"/>
       <script src="${pageContext.request.contextPath}/easyUI/jquery.form.js" type="text/javascript"></script>
   </head>
   <body style="margin-top: 100px;text-align:center">
   <p>Expert System Demo</p>
      <div class="easyui-panel" style="width:400px;padding:50px 60px">
         <form id="loginForm" action="login" method="post">
         <div style="margin-bottom:20px">
            <input name="Username" class="easyui-textbox" prompt="Username" iconWidth="28" iconCls="icon-man" style="width:100%;height:34px;padding:10px;">
         </div>
         <div style="margin-bottom:20px">
            <input name="Password" class="easyui-passwordbox" prompt="Password" iconWidth="28" style="width:100%;height:34px;padding:10px">
         </div>
             <input id="login" style="width:100%" type="submit" value="Login" />
         </form>
      </div>
   </body>
</html>