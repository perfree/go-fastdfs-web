getParentFile()
/*初始化layui*/
var element;
layui.use(['element'], function(){
    element = layui.element;
});
/*监听上传按钮点击*/
$('#fileUpload').click(function () {
    window.parent.document.getElementById("fileUpload").click();
})
/*获取所有一级目录及文件*/
function getParentFile() {
    $.post('/file/getParentFile', function(result){
        if(result.state==200){
            var data = result;
            template.helper('iconHandler', function (name,isDir) {
                var icon;
                if(isDir == true){
                    icon = "file-b-2";
                }else{
                    var index = name.lastIndexOf(".");
                    var length = name.length;
                    var suffix=name.substring(index+1,length).toLowerCase();
                    icon = kit.getIconName(suffix);
                }
                return icon;
            });
            var html = template('file-list',data);
            $("#file-result").html(html);
            $("#path-side").html('<a class="path-side-btn" data-path=""><cite>全部文件</cite></a>');
        }else{
            layer.msg("系统异常");
        }
    });
}

/*文件夹点击事件*/
$("#file-result").on("click",".resultDir",function(){
    var dirName = $(this).data("name");
    var dirPath = $(this).data("path");
    openDir(dirPath+"/"+dirName)
});

/*监听文件导航*/
$("#path-side").on("click",".path-side-btn",function(){
    var dir = $(this).data("path");
    openDir(dir)
})
//打开文件夹
function openDir(dir) {
    $.post('/file/getDirFile',{"dir":dir}, function(result){
        if(result.state==200){
            var data = result;
            template.helper('iconHandler', function (name,isDir) {
                var icon;
                if(isDir == true){
                    icon = "file-b-2";
                }else{
                    var index = name.lastIndexOf(".");
                    var length = name.length;
                    var suffix=name.substring(index+1,length).toLowerCase();
                    icon = kit.getIconName(suffix);
                }
                return icon;
            });
            var html = template('file-list',data);
            $("#file-result").html(html);
            setPathSide(dir);
        }else{
            layer.msg("系统异常");
        }
    });
}
//设置文件导航
function setPathSide(dir) {
    var arr = dir.split('/');
    var html;
    var path;
    for(var i=0;i<arr.length;i++){
        if (arr[i]==""){
            html += '<a class="path-side-btn" data-path="">全部文件</a>';
            path = "";
        }else{
            html += '<a class="path-side-btn" data-path="'+(path+"/"+arr[i])+'">'+arr[i]+'</a>';
            path += "/"+arr[i];
        }
    }
    $("#path-side").html(html);
    element.init();
}

/*监听MD5查看按钮*/
$("#file-result").on("click",".show-md5-btn",function(){
    var md5 = $(this).data("md5");
    layer.alert(md5, {title: "MD5值"});
})