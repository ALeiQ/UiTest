/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */

// 右侧导航栏效果
$(document).ready(function () {
    var menuRight = document.getElementById('cbp-spmenu-s2'),
        showRightPush = document.getElementById('showRightPush'),
        body = document.body;

    showRightPush.onclick = function () {
        classie.toggle(this, 'active');
        classie.toggle(body, 'cbp-spmenu-push-toleft');
        classie.toggle(menuRight, 'cbp-spmenu-open');
        disableOther('showRightPush');
    };


    function disableOther(button) {
        if (button !== 'showRightPush') {
            classie.toggle(showRightPush, 'disabled');
        }
    }
});


// 共用函数
$(document).ready(function () {

    // 删除行（隐藏）
    delRow = function (term) {
        $(term).parents("tr").fadeToggle(400);

        return false;
    };

    // form转换json
    $.fn.serializeObject = function()
    {
       var o = {};
       var a = this.serializeArray();
       $.each(a, function() {
           if (typeof(o[this.name]) !== "undefined") {
               if (!o[this.name].push) {
                   o[this.name] = [o[this.name]];
               }
               o[this.name].push(this.value || "");
           } else {
               o[this.name] = this.value || "";
           }
       });
       return o;
    };

    // 3s提示框
    showPopover = function (target, msg) {
        target.attr("data-original-title", msg);
        $('[data-toggle="tooltip"]').tooltip();
        target.tooltip('show');
        target.focus();

        //2秒后消失提示框
        var id = setTimeout(
            function () {
                target.attr("data-original-title", "");
                target.tooltip('hide');
            }, 3000
        );
    };

    setDateYYMMDD = function (inp) {
        inp.datetimepicker({
            format: 'yyyy-mm-dd',//显示格式
            todayHighlight: 1,//今天高亮
            minView: "month",//设置只显示到月份
            startView:2,
            forceParse: 0,
            showMeridian: 1,
            autoclose: 1//选择后自动关闭
        }).on('hide', function (e) {
            // 关闭后手动触发校验（否则不自动校验）
            form.data('bootstrapValidator')
                .updateStatus(inp, 'NOT_VALIDATED',null)
                .validateField(inp);
        });
    }

});

// 工具方法
$(function () {
    // 将value保留两位小数（截尾）并补全小数点后两个零
    returnFloat = function (value){
        var value=parseInt(parseFloat(value)*100)/100;
        var xsd=value.toString().split(".");
        if(xsd.length==1){
            value=value.toString()+".00";
            return value;
        }
        if(xsd.length>1){
            if(xsd[1].length<2){
                value=value.toString()+"0";
            }
            return value;
        }
    }
});

