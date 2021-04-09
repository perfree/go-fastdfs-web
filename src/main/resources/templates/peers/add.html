<!DOCTYPE html>
<html lang="zh_CN" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>添加集群</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" th:href="@{/static/lib/layui-v2.5.6/layui/css/layui.css}">
</head>
<body>
<div style="padding: 15px;">
	<form class="layui-form" action="">
		<div class="layui-form-item">
			<label class="layui-form-label">集群名称</label>
			<div class="layui-input-block">
				<input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入集群名称" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">所属组名</label>
			<div class="layui-input-inline">
				<input type="text" name="groupName" placeholder="请输入组名" autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">如未在GoFastDfs开启按组管理,请不要填写</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">管理地址</label>
			<div class="layui-input-block">
				<input type="text" name="serverAddress" lay-verify="required" autocomplete="off" placeholder="请输入集群管理地址" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">访问域名</label>
			<div class="layui-input-block">
				<input type="text" name="showAddress" autocomplete="off" placeholder="请输入访问域名" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button type="reset" class="layui-btn layui-btn-primary" style="height: 33px;line-height: 33px;float: right;">重置</button>
				<button class="layui-btn" lay-submit="" lay-filter="addPeers" style="height: 33px;line-height: 33px;background-color: #627aad;float: right;margin-right: 10px;">立即提交</button>
			</div>
		</div>
	</form>
</div>
<script th:src="@{/static/lib/jquery/jquery-3.5.1.min.js}"></script>
<script th:src="@{/static/lib/layui-v2.5.6/layui/layui.js}"></script>
<script>
	layui.use(['form'], function() {
		var form = layui.form;
		form.on('submit(addPeers)', function(data){
			let index = layer.load();
			$.post("/peers/doAdd",data.field,function (data) {
				if(data.code === 200){
					layer.msg("添加成功", {icon: 6});
					setTimeout(function(){
						window.parent.location.reload();
					}, 1000);
				}else{
					layer.msg(data.msg);
				}
				layer.close(index);
			})
			return false;
		});
	})
</script>
</body>
</html>