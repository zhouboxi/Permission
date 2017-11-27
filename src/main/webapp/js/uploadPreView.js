/*
*����ʱ��:2014��12��12��
*�������:ͼƬ�ϴ�����Ԥ����� ���������(IE �ȸ� ���) ��֧��safari ��Ȼ�����ʹ����Щ�ں˵����������������
*ʹ�÷���:
*���湹��(IMG��ǩ�����ӵ��DIV ���ұ������DIV�ؼ�ID)
* <div id="imgdiv"><img id="imgShow" width="120" height="120" /></div>
* <input type="file" id="up_img" />
*���ô���:
* new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
*����˵��:
*UpBtn:ѡ���ļ��ؼ�ID;
*DivShow:DIV�ؼ�ID;
*ImgShow:ͼƬ�ؼ�ID;
*Width:Ԥ�����;
*Height:Ԥ���߶�;
*ImgType:֧���ļ����� ��ʽ:["jpg","png"];
*callback:ѡ���ļ���ص�����;
*�汾:v1.4
������������:
1.�޸��ص�.
*�汾:v1.3
������������:
1.�޸���㼶��ܻ�ȡ·��BUG.
2.ȥ����jquery���������.
*/
/*
*work:ͼƬԤ�����
*/
var uploadPreview = function(setting) {
  /*
  *work:this(��ǰ����)
  */
  var _self = this;
  /*
  *work:�ж�Ϊnull���߿�ֵ
  */
  _self.IsNull = function(value) {
    if (typeof (value) == "function") { return false; }
    if (value == undefined || value == null || value == "" || value.length == 0) {
      return true;
    }
    return false;
  }
  /*
  *work:Ĭ������
  */
  _self.DefautlSetting = {
    UpBtn: "",
    DivShow: "",
    ImgShow: "",
    Width: 100,
    Height: 100,
    ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
    ErrMsg: "ѡ���ļ�����,ͼƬ���ͱ�����(gif,jpeg,jpg,bmp,png)�е�һ��",
    callback: function() { }
  };
  /*
  *work:��ȡ����
  */
  _self.Setting = {
    UpBtn: _self.IsNull(setting.UpBtn) ? _self.DefautlSetting.UpBtn : setting.UpBtn,
    DivShow: _self.IsNull(setting.DivShow) ? _self.DefautlSetting.DivShow : setting.DivShow,
    ImgShow: _self.IsNull(setting.ImgShow) ? _self.DefautlSetting.ImgShow : setting.ImgShow,
    Width: _self.IsNull(setting.Width) ? _self.DefautlSetting.Width : setting.Width,
    Height: _self.IsNull(setting.Height) ? _self.DefautlSetting.Height : setting.Height,
    ImgType: _self.IsNull(setting.ImgType) ? _self.DefautlSetting.ImgType : setting.ImgType,
    ErrMsg: _self.IsNull(setting.ErrMsg) ? _self.DefautlSetting.ErrMsg : setting.ErrMsg,
    callback: _self.IsNull(setting.callback) ? _self.DefautlSetting.callback : setting.callback
  };
  /*
  *work:��ȡ�ı��ؼ�URL
  */
  _self.getObjectURL = function(file) {
    var url = null;
    if (window.createObjectURL != undefined) {
      url = window.createObjectURL(file);
    } else if (window.URL != undefined) {
      url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) {
      url = window.webkitURL.createObjectURL(file);
    }
    return url;
  }
  /*
  *work:���¼�
  */
  _self.Bind = function() {
    document.getElementById(_self.Setting.UpBtn).onchange = function() {
      if (this.value) {
        if (!RegExp("\.(" + _self.Setting.ImgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
          alert(_self.Setting.ErrMsg);
          this.value = "";
          return false;
        }
        if (navigator.userAgent.indexOf("MSIE") > -1) {
          try {
            document.getElementById(_self.Setting.ImgShow).src = _self.getObjectURL(this.files[0]);
          } catch (e) {
            var div = document.getElementById(_self.Setting.DivShow);
            this.select();
            top.parent.document.body.focus();
            var src = document.selection.createRange().text;
            document.selection.empty();
            document.getElementById(_self.Setting.ImgShow).style.display = "none";
            div.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
            div.style.width = _self.Setting.Width + "px";
            div.style.height = _self.Setting.Height + "px";
            div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src;
          }
        } else {
          document.getElementById(_self.Setting.ImgShow).src = _self.getObjectURL(this.files[0]);
        }
        _self.Setting.callback();
      }
    }
  }
  /*
  *work:ִ�а��¼�
  */
  _self.Bind();
}