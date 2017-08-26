<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <title>HTML5分片上传</title>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/spark-md5/3.0.0/spark-md5.min.js"></script>
</head>
<body>

<div>
    <input type="file" id="file">
    <button id="upload">上传</button>
    <span id="message" style="font-size:12px">等待</span>
    <span id="time" style="font-size:12px;margin-left:20px;">用时</span>
    <span id="md5" style="font-size:12px;margin-left:20px;">md5</span>
</div>

<script>
    var start, end;
    $("#upload").click(function () {
        start = new Date();
        var file = $("#file")[0].files[0];  //文件对象
        if (file === null || file === undefined) {
            alert("请添加文件!");
            return;
        }

        var blobSlice = File.prototype.slice || File.prototype.mozSlice || File.prototype.webkitSlice,
            chunkSize = 2097152 * 2,                             //分片大小为4MB
            chunks = Math.ceil(file.size / chunkSize),
            currentChunk = 0,
            spark = new SparkMD5.ArrayBuffer(),
            fileReader = new FileReader();

        fileReader.onload = function (e) {
            console.log('计算MD5: 读取第', currentChunk + 1, '个分片, 一共:', chunks + '片');
            spark.append(e.target.result);
            currentChunk++;
            if (currentChunk < chunks) {
                loadNext();
            } else {
                var hash = spark.end();
                console.info("读取完毕, hash: ", hash);

                isUpload(hash);
            }
        };

        fileReader.onerror = function () {
            console.warn("读取文件中出现错误!");
        };

        function loadNext() {
            var start = currentChunk * chunkSize,
                end = ((start + chunkSize) >= file.size) ? file.size : start + chunkSize;
            fileReader.readAsArrayBuffer(blobSlice.call(file, start, end));
        }
        loadNext();

        function isUpload(hash) {
            var form = new FormData();
            form.append("fileMd5", hash);
            $.ajax({
                url: '${ctx}/file/validate',
                type: "POST",
                data: form,
//                async: true,        //异步
                processData: false,  //很重要，告诉jquery不要对form进行处理
                contentType: false,  //很重要，指定为false才能形成正确的Content-Type
                success: function (data) {
                    var code = data.code;
                    if (code === 0) {
                        console.log("已经上传过...");
                    } else {
                        console.log("尚未上传过...");
                        //TODO 分片上传
                    }
                }, error: function () {
                    alert("服务器出错!");
                }
            });
        }

        function upload() {

        }

    });

</script>

</body>
</html>