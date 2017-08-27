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
    <div>
        <input type="file" id="file">
        <button id="upload">上传</button>
    </div>
    <div id="message">

    </div>
    <div id="log">

    </div>
    <span id="md5" style="font-size:12px;margin-left:20px;">md5</span>
</div>

<script>
    $("#upload").click(function () {
        var file = $("#file")[0].files[0];  //文件对象
        if (file === null || file === undefined) {
            alert("请添加文件!");
            return;
        }

        //计算hash
        get_hash(file, isUpload);

        function isUpload(hash) {
            $("#md5").text(hash);
            var form = new FormData();
            form.append("fileMd5", hash);
            $.ajax({
                url: '${ctx}/file/validate',
                type: "POST",
                data: form,
                async: true,        //异步
                processData: false,  //很重要，告诉jquery不要对form进行处理
                contentType: false,  //很重要，指定为false才能形成正确的Content-Type
                success: function (data) {
                    var code = data.code;
                    if (code === 0) {
                        console.log("已经上传过...");
                    } else {
                        console.log("尚未上传过...");
                        upload(hash);
                    }
                }, error: function () {
                    alert("服务器出错!");
                }
            });
        }

        var i = 0, action = false;

        function upload(hash) {
            var name = file.name,
                size = file.size,
                shardSize = 2 * 1024 * 1024,                //5M分片
                shardCount = Math.ceil(size / shardSize);   //总分片树

            if (i < shardCount) {
                if (!action) {
                    i++;
                }
            } else {
                i = shardCount;
            }

            var start = (i - 1) * shardSize,
                end = Math.min(size, start + shardSize);

            var form = new FormData();
            if (!action) {
                form.append("action", "c");
            } else {
                form.append("action", "u");
                form.append("file", file.slice(start, end));
            }

            form.append("fileName", name);
            form.append("size", size);
            form.append("total", shardCount);
            form.append("index", i);
            form.append("fileMd5", hash);

            get_hash(file.slice(start, end), done);

            function done(shardingMd5) {
                form.append("shardingMd5", shardingMd5);
                $.ajax({
                    url: '${ctx}/file/upload',
                    type: "POST",
                    data: form,
                    async: true,        //异步
                    processData: false,  //很重要，告诉jquery不要对form进行处理
                    contentType: false,  //很重要，指定为false才能形成正确的Content-Type
                    success: function (data) {
                        if (!action) {  //验证请求
                            if (data.code === 0) {  //分片已经上传
                                $("#log").append("<p>第" + i + "块#" + shardingMd5 + ": 后台检测到分片, 本次分片上传跳过!</p>");
                                if (i === shardCount) {
                                    console.log("上传完毕...");
                                } else {
                                    action = false;
                                    upload(hash);
                                }
                            } else {    //未上传
                                action = true;
                                upload(hash);
                            }
                        } else {    //上传请求
                            $("#log").append("<p>第" + i + "块#" + shardingMd5 + ": 分片上传成功!</p>");
                            if (shardCount === i) {   //上传完毕
                                console.log("上传完毕...");
                            } else {    //部分上传
                                action = false;
                                upload(hash);
                            }
                        }
                    },
                    error: function () {
                        alert("服务器出错!");
                    }
                });
            }

        }

    });

    /**
     * 计算文件hash
     * @param file     需要hash计算的文件
     * @param callback 成功回调
     */
    function get_hash(file, callback) {
        var blobSlice = File.prototype.slice || File.prototype.mozSlice || File.prototype.webkitSlice,
            chunkSize = 5 * 1024 * 1024,    //5MB大小分片计算md5
            chunks = Math.ceil(file.size / chunkSize),
            currentChunk = 0,
            spark = new SparkMD5.ArrayBuffer(),
            fileReader = new FileReader();

        fileReader.onload = function (e) {
            console.log('[MD5计算]读取第', currentChunk + 1, '个分片, 一共:', chunks + '片');
            spark.append(e.target.result);
            currentChunk++;
            if (currentChunk < chunks) {
                loadNext();
            } else {
                var hash = spark.end();
                console.info("[MD5计算]读取完毕, hash: ", hash);
                callback(hash);
            }
        };

        fileReader.onerror = function () {
            console.warn("[MD5计算]读取文件中出现错误!");
        };

        function loadNext() {
            var start = currentChunk * chunkSize,
                end = ((start + chunkSize) >= file.size) ? file.size : start + chunkSize;
            fileReader.readAsArrayBuffer(blobSlice.call(file, start, end));
        }

        loadNext();
    }

</script>

</body>
</html>