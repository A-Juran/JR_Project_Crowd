<%--
  Created by IntelliJ IDEA.
  User: Juran
  Date: 2021/12/30
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ssm测试成功</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script type="text/javascript" src="./jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {
            //按钮1
            $("#btn1").click(function () {
                $.ajax({
                    url: "send/array.html",      //请求地址
                    type: "post",                //请求方式
                    data: {"array": [5, 8, 12]},              //发送参数
                    dataType: "text",            //如何对待服务端返回的数据类型。
                    success: function (res) {     //服务器处理成功后的回调函数
                        alert(res);
                    },
                    error: function (res) {       //服务器处理失败后的回调函数
                        alert(res);
                    }
                })
            });
            //按钮2
            $("#btn2").click(function () {
                $.ajax({
                    url: "send/twoarray.html",      //请求地址
                    type: "post",                //请求方式
                    data: {
                        "array[0]": 5,
                        "array[1]": 8,           //发送参数
                        "array[2]": 11,
                    },
                    dataType: "text",            //如何对待服务端返回的数据类型。
                    success: function (res) {     //服务器处理成功后的回调函数
                        alert(res);
                    },
                    error: function (res) {       //服务器处理失败后的回调函数
                        alert(res);
                    }
                })
            });
            //按钮3
            $("#btn3").click(function () {
                //准备需要发送到服务器端得数组
                var array = [1, 2, 3];
                console.log(array.length);
                //将Json数组转换成Json字符串
                var requestBody = JSON.stringify(array);
                $.ajax({
                    url: "send/threearray.html",      //请求地址
                    type: "post",                //请求方式
                    data: requestBody,         //发送参数
                    contentType: "application/json;charset=UTF-8",   //告知服务端传输的数据类型。
                    dataType: "text",            //如何对待服务端返回的数据类型。
                    success: function (res) {     //服务器处理成功后的回调函数
                        alert(res);
                    },
                    error: function (res) {       //服务器处理失败后的回调函数
                        alert(res);
                    }
                })
            });
            //按钮4
            //传输复杂对象
            $("#btn4").click(function () {
                var student = {
                    "stuName": "JuRan",
                    "stuId": 21,
                    "address": {
                        "province": "浙江",
                        "city": "宁波"
                    },
                    "subjectList": [
                        {
                            "subjectName": "Java",
                            "subjectScore": 96
                        },
                        {
                            "subjectName": "Data Struct",
                            "subjectScore": 93
                        }
                    ],
                    "map": {
                        "key1": "value1",
                        "key2": "value2"
                    }
                };   //student end
                var studentStr = JSON.stringify(student);
                $.ajax({
                    url: "send/composearray.json",    //请求目标资源地址
                    type: "post",                       //请求方式
                    data: studentStr,                   //发送的请求参数
                    dataType: "json",                   //表示如何对待服务器返回的数据
                    contentType: "application/json;charset=UTF-8",  //告诉服务器端当前请求的请求体是JSON格式
                    success: function (response) {
                        console.log(response);
                    },
                    error: function (response) {
                        alert(response);
                    }
                });

            });
            //引入layer测试
            $("#btn5").click(function () {
                layer.msg("测试成功");
            })

            })
    </script>
</head>
<body>
<a href="test/ssm.html">测试页面</a>
<br>
<button id="btn1">Send [5, 8, 12] One</button>
<br>
<button id="btn2">Send [5, 8, 11] Two</button>
<br>
<button id="btn3">Send [1,2,3] three</button>
<br>
<button id="btn4">Send [1,2,3] three</button>
<br>
<button id="btn5">测试layer</button>
</body>
</html>
