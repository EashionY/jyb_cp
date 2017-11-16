/**
 * Created by dell on 2017/10/12.
 */
/* *参数说明:
imgObj:图片jquery对象;
type:支持文件类型;
callback:选择文件显示图片后回调方法;
 *使用方法:
 <div><img id="temp"/></div>
 <input type="file" id="up" />
 把需要进行预览的IMG标签外 套一个DIV 然后给上传控件ID给予uploadPreview事件
 $("#up").uploadPreview({
    imgObj: $("#temp"),
    type: ["gif", "jpeg", "jpg", "bmp", "png"],
    callback: function () { }
 });
 */
jQuery.fn.extend({
    uploadPreview: function (opts) {
        var _self = this,
            _this = $(this);
        opts = jQuery.extend({
            imgObj: null,
            type: ["gif", "jpeg", "jpg", "bmp", "png"],
            callback: function () {}
        }, opts || {});
        _self.getObjectURL = function (file) {
            var url = null;
            if (window.createObjectURL != undefined) {
                url = window.createObjectURL(file)
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(file)
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(file)
            }
            return url
        };
        _this.change(function () {
            if (this.value) {
                if(!opts.imgObj){
                    return;
                }
                if (!RegExp("\.(" + opts.type.join("|") + ")$", "i").test(this.value.toLowerCase())) {
                    alert("文件格式错误,必须是" + opts.type.join("，") + "中的一种");
                    this.value = "";
                    return false
                }
                if (document.selection) {
                    try {
                        opts.imgObj.attr('src', _self.getObjectURL(this.files[0]))
                    } catch (e) {
                        var src = "";
                        var obj = opts.imgObj;
                        var div = obj.parent("div")[0];
                        _self.select();
                        if (top != self) {
                            window.parent.document.body.focus()
                        } else {
                            _self.blur()
                        }
                        src = document.selection.createRange().text;
                        document.selection.empty();
                        obj.hide();
                        obj.parent("div").css({
                            'filter': 'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)'
                        });
                        div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src;
                    }
                } else {
                    opts.imgObj.attr('src', _self.getObjectURL(this.files[0]))
                }
                opts.callback()
            }else{
                opts.imgObj.attr('src', "")
            }
        })
    }
});