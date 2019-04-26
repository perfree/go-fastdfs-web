/** 打开tab选项卡 */
function openTab(icon,menuName,url,tabId) {
	var element = layui.element;
	var eachcount=0;
	var flag= false;
	$(".content-tab-title").find('li').each(function() {
		eachcount++;
		var layId = $(this).attr("lay-id");
		if(tabId == layId){
			flag = true;
		}
		if(eachcount >= $(".content-tab-title").find('li').length){
			if(flag){
				element.tabChange('tabNav', tabId);
				return;
			}else{
				//添加tab
				element.tabAdd('tabNav', {
					title: "<i class='fa fa-"+icon+"' style='font-size: 16px;'></i>&nbsp;"+menuName,
					content: "<iframe src='"+url+"' scrolling='auto' width='100%' height='100%' frameborder='0' allowfullscreen='true' webkitallowfullscreen='true' mozallowfullscreen='true' class='layui-anim layui-anim-upbit perfree-ifram'></iframe>", //支持传入html
					id: tabId
				});
				//切换至新添加tab
				element.tabChange('tabNav', tabId);
				util.iframeAuto();
				if(util.getWidth() < 640){
					hideNav();
					flag = 0;
				}
			}
		}
	});
}
/** 收起侧边栏 */
var flag = 0;
$(".menuBtn").on("click",function(){
	if(util.getWidth() < 640){
		if(flag == 1){
			hideNav();
			flag = 0;
		}else{
			showNav();
			flag = 1;
		}
	}
	if(util.getWidth() >= 640){
		if(flag == 0){
			hideNav();
			flag = 1;
		}else{
			showNav();
			flag = 0;
		}
	}
});
/** 菜单导航点击事件 */
$('.left-menu').delegate('.nav-menu-a','click',function () {
	if(flag == 1){
		showNav();
		flag = 0;
	}
});
/** 隐藏导航栏文字,显示图标 */
function hideNav(){
	if(util.getWidth() < 640){
		$(".nav-menu-a > .menu-text,.nav-menu-a > .layui-nav-more").fadeOut(100);
		$(".left-navbar-menu,.layui-logo").animate({width:0}, 200);
		$(".logo-text > .logo-text-value").fadeOut(0);
		$(".layui-body,.layui-footer,.navbar-header").animate({left:0}, 200);
		$(".nav-header-right").animate({right:"0px"}, 200);
		$(".logo-text> i").css("display","inherit");
		$(".child-menu").css("display","none");
	}
	if(util.getWidth() >= 640){
		$(".nav-menu-a > .menu-text,.nav-menu-a > .layui-nav-more").fadeOut(100);
		$(".left-navbar-menu,.layui-logo").animate({width:60}, 200);
		$(".logo-text > .logo-text-value").fadeOut(0);
		$(".layui-body,.layui-footer,.navbar-header").animate({left:60}, 200);
		$(".nav-header-right").animate({right:"70px"}, 200);
		$(".logo-text> i").css("display","inherit");
		$(".child-menu").css("display","none");
	}
}
/** 显示完全菜单导航 */
function showNav(){
	if(util.getWidth() < 640){
		layer.closeAll('tips');
		$(".layui-body,.layui-footer,.navbar-header").animate({left:"200px"}, 200 );
		$(".nav-header-right").animate({right:"170px"}, 200 );
		$(".logo-text > .logo-text-value,.nav-menu-a > .menu-text").delay(200).fadeIn(200);
		$(".layui-logo,.left-navbar-menu").animate({width:200}, 200 );
		$(".nav-menu-a > .layui-nav-more").delay(200).fadeIn(0);
		$(".logo-text> i").css("display","none");
		$(".child-menu").css("display","block");
	}
	if(util.getWidth() >= 640){
		layer.closeAll('tips');
		$(".layui-body,.layui-footer,.navbar-header").animate({left:"200px"}, 200 );
		$(".nav-header-right").animate({right:"220px"}, 200 );
		$(".logo-text > .logo-text-value,.nav-menu-a > .menu-text").delay(200).fadeIn(200);
		$(".layui-logo,.left-navbar-menu").animate({width:200}, 200 );
		$(".nav-menu-a > .layui-nav-more").delay(200).fadeIn(0);
		$(".logo-text> i").css("display","none");
		$(".child-menu").css("display","block");
	}
}
/** 菜单缩小时,鼠标移出隐藏tips */
$('.left-menu').on('mouseleave', '.layui-nav-item', function(){
	layer.closeAll('tips');
});
/** 菜单缩小时,鼠标悬浮显示tips */
$('.left-menu').on('mouseenter', '.layui-nav-item', function(){
	if(flag == 1){
		var tip = $(this).children(".nav-menu-a").children(".menu-text").text();
		var that = this;
		layer.closeAll('tips');
		layer.open({type: 4,shade: 0,closeBtn: 0,content: [tip, that]});
	}
});
/** 顶部导航鼠标悬浮显示tips */
$(".nav-top-icon").hover(
	function(){
		var tip = $(this).children("a").attr("lay-tips");
		var that = this;
		layer.closeAll('tips');
		layer.open({type: 4,tips: 3,shade: 0,closeBtn: 0,content: [tip, that]});
	},
	function(){
		layer.closeAll('tips');
	}
)
/**更换主题*/
 $('.choose-theme-btn').on('click', function () {
	 $(".theme").animate({right:0}, 200 );
	 util.coverShade();
});
/** 点击关闭遮罩及右侧主题页 */
function offShade() {
	$(".theme").animate({right:"-288px"}, 200 );
	 util.hideShade();
 }
/** 刷新 */
$('.refreshBtn').on('click', function () {
	var content = $(".layui-show >iframe");
	content.attr('src', content.attr('src'));
});
/**全屏*/
 $('.full-scann').on('click', function () {
	 var docElm = document.documentElement;
	if (docElm.requestFullscreen) {docElm.requestFullscreen();}
	else if (docElm.mozRequestFullScreen) {docElm.mozRequestFullScreen();}
	else if (docElm.webkitRequestFullScreen) {docElm.webkitRequestFullScreen();}
	else if (elem.msRequestFullscreen) {elem.msRequestFullscreen();}
	layer.msg('按Esc即可退出全屏');
});
var cssArr="";
$('.theme-box-body').delegate('.theme-box','click',function () {
	if(cssArr == ""){
		var url = $(this).attr("css-url");
		cssArr = url;
		util.dynamicLoadCss(url);
	}else{
		util.removejscssfile(cssArr,"css");
		var url = $(this).attr("css-url");
		cssArr = url;
		util.dynamicLoadCss(url);
	}
});

$('#userCenter').click(function () {
	window.document.getElementById("settingsUser").click();
})