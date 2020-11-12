;(function($, window, document,undefined) {

		var bodyClick=function (e){
			console.log("绑定body事件")

			if($(e.target).parents('.calculator').length > 0  || $(e.target).hasClass('calculator') || $(e.target).hasClass('mykeyboard')){
				//点击事件在输入框或者在键盘上
			}else{
                $('div.calculator').remove();
				$("body").unbind("click",bodyClick)
			}
		}

        var Keyboard = function(ele,opt){
            this.$element = ele;
            this.options = opt;
        };
        Keyboard.prototype = {
            //初始化生成界面
            init:function(){


                if(!$(this.$element).hasClass('mykeyboard')){
                    throw new Error('检测到该元素缺少mykeyboard属性,请添加');
                }
                if($('div.calculator').length >= 1){
                    $('div.calculator').remove();
                }
                var mykeyboard = $('<div class="calculator">\n' +
                    '            <div class="row_line">\n' +
                    '                <span class="num_key" the_val="7">7</span><span class="num_key" the_val="8">8</span><span class="num_key" the_val="9">9</span><span class="btn_key my_exit">退出</span>\n' +
                    '            </div>\n' +
                    '            <div class="row_line">\n' +
                    '                <span class="num_key" the_val="4">4</span><span class="num_key" the_val="5">5</span><span class="num_key" the_val="6">6</span><span class="btn_key my_clear">清除</span>\n' +
                    '            </div>\n' +
                    '            <div class="row_line">\n' +
                    '                <span class="num_key" the_val="1">1</span><span class="num_key" the_val="2">2</span><span class="num_key" the_val="3">3</span><span class="btn_key my_backspace">退格</span>\n' +
                    '            </div>\n' +
                    '            <div class="row_line">\n' +
                    '                <span class="num_key zero" the_val="0">0</span><span class="num_key" the_val=".">.</span><span class="btn_key ok">确定</span>\n' +
                    '            </div>\n' +
                    '        </div>');

                mykeyboard.appendTo('body');

                if( this.options ){

                    $('div.calculator').css( this.options);

                }else{
                    var margin_top = $(this.$element).offset().top;
                    var margin_left = $(this.$element).offset().left;
                    var dom_height = $(this.$element).height();
                    $('div.calculator').css({'position':'absolute','left':margin_left+'px','top':(margin_top+dom_height+20)+'px'});
                    var window_height = $(window).height();
                    var current_dom_bottom = window_height - $(this.$element).height() - margin_top;
                    if( current_dom_bottom <= $('div.calculator').height()){
                        $('div.calculator').css({'position':'absolute','left':margin_left+'px','top':(margin_top-$('div.calculator').height()-20)+'px'});
                    }
                }
				console.log("绑定事件")
                this.my_exit();
                this.my_clear();
                this.done();
                this.my_ok();
                this.my_backspace();


				console.log("绑定结束事件")

				$("body").unbind("click",bodyClick).bind("click",bodyClick)

            },
            //确定
            my_ok:function(){
                var that = this;
                $('.btn_key.ok').click(function(){
                    $('div.calculator').remove();
                });
            },
            //退格
            my_backspace:function(){
                var that = this;
                $('.btn_key.my_backspace').click(function(){
                    var input_val = $(that.$element).val();
                    input_val = input_val.substring(0, input_val.length - 1);
                    $(that.$element).val( input_val );
                });
            },
			exit:function(){
               $('div.calculator').remove();
			   $("body").unbind("click",bodyClick)
            },

            //退出
            my_exit:function(){
				var that = this;
                $('.btn_key.my_exit').on('click',function(){
                   that.exit();
                });
            },
            //清除
            my_clear:function(){
                var that = this;
                $('.btn_key.my_clear').click(function(){
                    $(that.$element).val('');
                });
            },
            //赋值
            done: function() {
                var that = this;
                $('span.num_key').on('click',function(){
                   var num = $(this).attr('the_val');
                   var input_val = $(that.$element).val()?$(that.$element).val():'';
                   var val = input_val + num;
                   $(that.$element).val( val );
                });
            }
        };
        $.fn.mykeyboard = function(options) {
			this.on('click',function(){
				var my = new Keyboard(this, options);
				my.init();
			});
        };
})(jQuery, window, document);
