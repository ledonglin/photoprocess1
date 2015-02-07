<!DOCTYPE html>
<html lang="zh-CN">
<%@ page pageEncoding="utf-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String _path = request.getContextPath();
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--meta name="viewport" content="width=device-width, initial-scale=1.0"-->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>管理</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=_path%>/css/bootstrap/bootstrap.css" rel="stylesheet">
	 <link href="<%=_path%>/css/imgareaselect/imgareaselect-default.css" rel="stylesheet">
    <link href="<%=_path%>/css/style.css" rel="stylesheet">
	<meta name="robots" content="noindex,follow" />
	</head>

<body>
	<header class="navbar navbar-static-top bs-docs-nav" id="top" role="banner">
	  <div class="container">
		<div class="navbar-header">
		  <a href="#" class="navbar-brand">段子</a>
		</div>
		  <ul class="nav navbar-nav navbar-right">
			<li>user</li>
		  </ul>
		</nav>
	  </div>
	</header>
	<div class="container">
		<h1>发布</h1>
		<div class="frame" style="width: 400px; height: 400px;float:left;margin-right:50px">
			<img id="photo" src="<%=_path%>/img/1.jpg">
		</div>
		<div id="preview" style="width: 100px; height: 100px; overflow: hidden;">
			<img id="view_photo" src="<%=_path%>/img/1.jpg" style="width: 100px; height: 100px;">
		</div><br/><br/>
		<input id="upload" type="file" name="file" onchange="change(this)"/>  
	</div>
	<script src="<%=_path%>/js/jquery-1.7.2.min.js"></script>
	<script src="<%=_path%>/js/imgareaselect/jquery.imgareaselect.pack.js"></script>
	<script src="<%=_path%>/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript">
		selectrate=1;
		rate=1;
		$(function() {
			init();
		});
		function init()
		{
			$('#photo').imgAreaSelect({
				aspectRatio : '1:1',
				handles : true,
				fadeSpeed : 200,
				onSelectChange : preview,
				x1: 0, y1: 0, x2: 240, y2: 240
			});
		}
		function preview(img, selection)
		{
				
			if (!selection.width || !selection.height)
				return;
			var scaleX = 100 / selection.width;
			var scaleY = 100 / selection.height;

			$('#preview img').css({
				width : Math.round(scaleX * 300),
				height : Math.round(scaleY * 300),
				marginLeft : -Math.round(scaleX * selection.x1),
				marginTop : -Math.round(scaleY * selection.y1)
			});
		}
		
		function change(file)
		{
		   // Get a reference to the fileList
		   var files = !!file.files ? file.files : [];
		   // If no files were selected, or no FileReader support, return
		   if (!files.length || !window.FileReader) return;

	        // Create a new instance of the FileReader
	        var reader = new FileReader();
		  
	        // Read the local file as a DataURL
	        reader.readAsDataURL(files[0]);
		  
	        // When loaded, set image data as background of div
	        reader.onloadend = function()
			{
				var img=$('#photo');
				img.attr("src",this.result);
				$("#view_photo").attr("src",this.result);
				img.load(function(){
				  // 加载完成 
				  var img=$('#photo');
				  img.width('100%');
				  img.height('100%'); 

				  init();
				});
			}
		}
	</script>
</body>

</html>