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
    var index = layer.load();
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
            layer.close(index);
        }else{
            layer.close(index);
            layer.msg("系统异常");
        }
    });
}

/*文件夹点击事件*/
$("#file-result").on("click",".resultDir",function(){
    var dirName = $(this).data("name");
    var dirPath = $(this).data("path");
    openDir(dirPath+"/"+dirName);
});

/*监听文件导航*/
$("#path-side").on("click",".path-side-btn",function(){
    var dir = $(this).data("path");
    openDir(dir);
})
//打开文件夹
function openDir(dir) {
    var index = layer.load();
    var suff = dir.substring(0,1);
    if(suff == "/"){
        dir = dir.substring(1);
    }
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
            setPathSide("/"+dir);
            layer.close(index);
        }else{
            layer.close(index);
            layer.msg("系统异常");
        }
    });
}
//设置文件导航
function setPathSide(dir) {
    var arr = dir.split('/');
    var html = '<a class="path-side-btn" data-path="">全部文件</a>';
    var path = "";
    for(var i=0;i<arr.length;i++){
        if(arr[i] != ""){
            html += '<a class="path-side-btn" data-path="'+(path+"/"+arr[i])+'">'+arr[i]+'</a>';
            path += "/"+arr[i];
        }
    }
    $("#path-side").html(html);
    element.init();
}

/*监听下载按钮*/
$("#file-result").on("click",".download-btn",function(){
    var name = $(this).data("name");
    var path = $(this).data("path");
    var url = "/file/downloadFile";
    var form = $("<form></form>").attr("action", url).attr("method", "post");
    form.append($("<input></input>").attr("type", "hidden").attr("name", "path").attr("value", path));
    form.append($("<input></input>").attr("type", "hidden").attr("name", "name").attr("value", name));
    form.appendTo('body').submit().remove();
})

/*监听删除按钮*/
$("#file-result").on("click",".delete-file-btn",function(){
    var name = $(this).data("name");
    var md5 = $(this).data("md5");
    var $this = $(this);
    layer.confirm('确定要删除'+name+'吗?',{icon: 3, title:'提示'}, function(index){
        $.post('/file/deleteFile',{"md5":md5}, function(result){
            if(result.state==200){
                $this.parent().parent().remove();
                var len = $(".file-list-file-box").length;
                if(len == 0){
                    $("#file-result").html('<div class="file-list-file-box"><div class="no-file-tip">暂无文件</div></div>');
                }
                layer.msg("删除成功");
            }else{
                layer.msg(result.msg);
            }
        })
        layer.close(index);
    });
})