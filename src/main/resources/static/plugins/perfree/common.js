//工具类示例,调用直接util.initMenu();
(function($){
	var util = function(){}
	util.prototype = {
		/** 加载外部css文件 */
		dynamicLoadCss: function(url) {
			var head = document.getElementsByTagName('head')[0];
			var link = document.createElement('link');
			link.type='text/css';
			link.rel = 'stylesheet';
			link.href = url;
			head.appendChild(link);
		},/** 移除css/js文件 */
		removejscssfile: function(filename,filetype){
			var targetelement=(filetype=="js")? "script" :(filetype=="css")? "link" : "none"
			var targetattr=(filetype=="js")?"src" : (filetype=="css")? "href" :"none"
			var allsuspects=document.getElementsByTagName(targetelement)
			for (var i=allsuspects.length; i>=0;i--){
			if (allsuspects[i] &&allsuspects[i].getAttribute(targetattr)!=null && allsuspects[i].getAttribute(targetattr).indexOf(filename)!=-1)
			 allsuspects[i].parentNode.removeChild(allsuspects[i])
			}
		},/** iframe自适应 */
		iframeAuto: function() {
			$(window).on('resize', function () {
				var $content = $('.layui-tab-content');
				$content.height($(this).height() - 147);
				$content.find('iframe').each(function () {
				$(this).height($content.height());
			});
      }).resize();
		},/** 获取当前屏幕宽度 */
		getWidth: function(){
			return $(window).width();
		},/** 显示遮罩 */
		coverShade: function(){    
				var perfreebg = document.createElement("div");  
				perfreebg.setAttribute("id","perfreeBg");    
				perfreebg.style.background = "#000000";    
				perfreebg.style.width = "100%";    
				perfreebg.style.height = "100%";    
				perfreebg.style.position = "fixed";    
				perfreebg.style.top = "0";    
				perfreebg.style.left = "0";    
				perfreebg.style.zIndex = "1000";    
				perfreebg.style.opacity = "0.2";    
				perfreebg.style.filter = "Alpha(opacity=70)"; 
				perfreebg.setAttribute("onclick","offShade();")
				document.body.appendChild(perfreebg);    
		},/**取消遮罩*/    
		hideShade: function() {    
				var body = document.getElementsByTagName("body");    
				var perfreeBg = document.getElementById("perfreeBg");    
				body[0].removeChild(perfreeBg);    
		},/** 初始化主题 */
		initTheme: function(theme){
			var html = "";
			for(var i = 0;i < theme.length;i++){
				html+='<a class="layui-card theme-box" href="javascript:;" css-url="'+theme[i].url+'">'+
						'<div class="layui-card-header theme-header" style="background-color: '+theme[i].header+';"></div>'+
						'<div class="layui-card-body theme-body">'+
						'<div class="left-menu-theme" style="background-color: '+theme[i].menu+';border-top: 1px solid '+theme[i].border+';"></div>'+
						'<div class="theme-name">'+theme[i].name+'</div></div></a>';
			}
			$(".theme-box-body").append(html);
		}
	}
	window.util = new util();
})(window.jQuery);
