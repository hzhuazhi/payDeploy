<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>拼多多</title>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
<div class="box">
    <div class="icon"></div>
    <div class="txt">拼多多</div>
    <div class="txtBox">
        <span>拼</span>
        <span>着</span>
        <span>买</span>
        <span style="margin-left: 30px">更</span>
        <span>便</span>
        <span>宜</span>
    </div>
</div>
<!--  <video width="320" height="210" controls  id="videoid">
      <source src="video1.MP4" type="video/mp4">
      您的浏览器不支持 HTML5 video 标签。
  </video>-->
<input type="button" value="播放" onclick="play();" hidden/>
${dto}
</body>

</html>


<!--<script type="text/javascript">
function play() {
document.getElementById("videoid").play();
}
</script>-->
<style>
    *{
        margin: 0;padding: 0;list-style: none;
    }
    body{
        position: relative;
    }
    .box{
        width: 1217px;height: 688px;background-color: #d4352f;
        position: fixed;
        left: 0;right: 0;top: 0;bottom: 0;
        margin: auto;
    }
    .icon{
        width:300px;height: 300px;
        position: absolute;
        left: 0;right: 0;
        margin: auto;top: 0;bottom: 0;
        background-image: url("https://zqtemp.oss-cn-hangzhou.aliyuncs.com/tvtemp/pdd.png");
        background-size: cover;
        animation: animat1 1s linear forwards;
        -webkit-animation: animat1 1s linear forwards;
        animation-delay: 0.5s;
        -webkit-animation-delay: 0.5s;opacity: 0;
    }
    .txt{
        margin-left:550px;display: none;
        width:32%;height: 120px;margin-top: 206px;
        color: white;
        font-size: 74px;font-weight: bold;line-height: 120px;
        letter-spacing:6px
    }
    .txtBox{
        width:100%;height: 80px;
        position: absolute;
        left: 0px; right: 0;margin: auto;
        bottom: 230px;box-sizing: border-box;
        text-align: center;
    }
    .txtBox span{
        display: inline-block;
        color: white;font-size:52px;font-weight: bold;margin-left: 6px;margin-right: 6px;
        line-height: 80px;
        display: none;
    }
    .txtBox span.txtscale{
        display: inline-block;
        animation: txtscale 0.3s linear forwards;
        -webkit-animation: txtscale 0.3s linear forwards;
    }
    .icon.scale1{
        animation: scale1 0.2s linear forwards;
        -webkit-animation: scale1 0.2s linear forwards;
        animation-delay: 0.5s;
        -webkit-animation-delay:0.5s;
        opacity: 1;
    }
    .box.on{
        animation: box_show 0.5s ease forwards;
        -webkit-animation: box_show 0.5s ease forwards;
    }

    @-webkit-keyframes box_show{
        0%{
            transform: scale(0);
            -webkit-transform: scale(0);
        }
        100%{
            transform: scale(1);
            -webkit-transform: scale(1);
        }
    }
    @-webkit-keyframes animat1 {
        0%{
            opacity: 1;
            transform: scale(0);
            -webkit-transform: scale(0);
        }
        30%{
            opacity: 1;
            transform: scale(1);
            -webkit-transform: scale(1);
        }
        60%{
            opacity: 1;
            transform: scale(0.7);
            -webkit-transform: scale(0.7);
        }
        80%{
            opacity: 1;
            transform: scale(0.9);
            -webkit-transform: scale(0.9);
        }
        100%{
            opacity: 1;
            transform: scale(1);
            -webkit-transform: scale(1);
        }
    }
    @-webkit-keyframes scale1 {
        100%{
            opacity: 1;
            transform:scale(0.4);
            -webkit-transform: scale(0.4);
            top: -150px;
        }
    }
    @-webkit-keyframes txtscale {
        0%{
            -webkit-transform: scale(0);
        }
        45%{
            -webkit-transform: scale(1.2);
        }
        80%{
            -webkit-transform: scale(0.8);
        }

        100%{
            -webkit-transform: scale(1);
        }
    }
</style>

<script>
    $(function(){
        $(".box").addClass("on");

        setTimeout(function(){
            $(".icon").addClass("scale1")
        },1500);
        setTimeout(function(){
            $(".icon").animate({
                "right":"254px"
            });
            $(".txt").css("display","block");
            $(".txt").animate({
                "margin-left":"570px"
            })
        },2250)

        var txt  = $(".txtBox span");
        var s =2500;
        for(var i = 0;i<txt.length;i++){
            (function () {
                var temp = i;
                setTimeout(function(){
                    txt.eq(temp).addClass("txtscale");
                },s)
                s+=140
            })()

        }
    })
</script>
