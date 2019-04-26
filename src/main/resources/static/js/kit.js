(function($){
    var kit = function(){}
    kit.prototype = {
        getIconName: function(suffix) {
            var iconName;
            switch(suffix) {
                //图片
                case "jpg":
                    iconName = "file-b-6";break;
                case "png":
                    iconName = "file-b-6";break;
                case "jpeg":
                    iconName = "file-b-6";break;
                case "gif":
                    iconName = "file-b-6";break;
                case "psd":
                    iconName = "file-b-6";break;
                //压缩包
                case "rar":
                    iconName = "file-b-4";break;
                case "zip":
                    iconName = "file-b-4";break;
                case "7z":
                    iconName = "file-b-4";break;
                case "tar":
                    iconName = "file-b-4";break;
                case "gz":
                    iconName = "file-b-4";break;
                //ppt
                case "ppt":
                    iconName = "file-b-";break;
                case "pptx":
                    iconName = "file-b-";break;
                //pdf
                case "pdf":
                    iconName = "file-s-6";break;
                //word
                case "doc":
                    iconName = "file-b-1";break;
                case "docx":
                    iconName = "file-b-1";break;
                //excel
                case "xls":
                    iconName = "file-b-5";break;
                case "xlsx":
                    iconName = "file-b-5";break;
                //歌曲
                case "wave":
                    iconName = "file-b-3";break;
                case "mp3":
                    iconName = "file-b-3";break;
                case "mpeg-4":
                    iconName = "file-b-3";break;
                case "aac":
                    iconName = "file-b-3";break;
                case "mpeg":
                    iconName = "file-b-3";break;
                //文本
                case "txt":
                    iconName = "file-b-3";break;
                //视频
                case "avi":
                    iconName = "file-b-9";break;
                case "mp4":
                    iconName = "file-b-9";break;
                case "3gp":
                    iconName = "file-b-9";break;
                case "rmvb":
                    iconName = "file-b-9";break;
                case "flv":
                    iconName = "file-b-9";break;
                //exe
                case "exe":
                    iconName = "Exe";break;
                //脚本文件
                case "sh":
                    iconName = "jiaobenguanli";break;
                case "bat":
                    iconName = "jiaobenguanli";break;
                //java
                case "java":
                    iconName = "java";break;
                //go
                case "go":
                    iconName = "gopher";break;
                //css
                case "css":
                    iconName = "css";break;
                //html
                case "html":
                    iconName = "file_html";break;
                //js
                case "js":
                    iconName = "JS";break;
                //python
                case "py":
                    iconName = "python";break;
                //其他
                default:
                    iconName = "file-b-8";break;
            }
            return iconName;
        },
        getFileType: function(suffix) {
            var fileType;
            if(suffix == "jpg" ||suffix == "png" ||suffix == "jpeg" ||suffix == "gif" ||suffix == "psd"){
                fileType = "image";
            }else if(suffix == "rar" ||suffix == "zip" ||suffix == "7z" ||suffix == "tar" ||suffix == "gz"){
                fileType = "zip";
            }else if(suffix == "ppt" ||suffix == "pptx"){
                fileType = "ppt";
            }else if(suffix == "doc" ||suffix == "docx"){
                fileType = "word";
            }else if(suffix == "xls" ||suffix == "xlsx"){
                fileType = "excel";
            }else if(suffix == "wave" ||suffix == "mp3" ||suffix == "mpeg-4" ||suffix == "aac" ||suffix == "mpeg"){
                fileType = "song";
            }else if(suffix == "txt"){
                fileType = "txt";
            }else if(suffix == "avi" ||suffix == "mp4" ||suffix == "3gp" ||suffix == "rmvb" ||suffix == "flv"){
                fileType = "video";
            }else{
                fileType = "other";
            }
            return fileType;
        }
    }
    window.kit = new kit();
})(window.jQuery);