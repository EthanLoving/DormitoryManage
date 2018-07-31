$(function(){
	
	 //自定义手机号验证
    $.extend($.fn.validatebox.defaults.rules, {
        phoneRex: {
            validator: function(value){
                var pattern_tel=/(^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$)|(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
                if(pattern_tel.test(value)) {
                    return true;
                }else {
                    return false;
                }
            },
            message: '请输入正确的手机号格式'
        }
    });
	//确认密码验证
    $.extend($.fn.validatebox.defaults.rules, {
        equals: {
            validator: function(value,param){
                return value == $(param[0]).val();
            },
            message: '密码不一致'
        }
    });
	
});