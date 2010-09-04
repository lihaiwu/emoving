/**
 * 简单Form例子
 */

/*
Ext.onReady(function(){
    var form = new Ext.form.FormPanel({
        title:"用户注册",
        width:600,
        autoHeight:true,
        labelAlign:"left",
        labelWidth:60,
        defaults:{xtype:"textfield"},
        items:[{
            fieldLabel:"用户名"
        },{
            fieldLabel:"密码"
        },{
            fieldLabel:"重复密码"
        },{
            fieldLabel:"Email"
        }],
        renderTo:Ext.getBody()
    });
});
*/

/**
 * 分栏表单(运用了之前的布局)
 */
 
/*
Ext.onReady(function(){
 
 var fset1 = new Ext.form.FieldSet({
     title:"基本信息",
     width:450,
     //是否可折叠
     collapsible:true,
     autoHeight:true,
     labelAlign:"left",
     labelWidth:80,
     defaults:{xtype:"textfield"},
     items:[{
         fieldLabel:"用户名",
         name:"username"
     },{
         fieldLabel:"密码",
         name:"password"
     },{
         fieldLabel:"重复密码",
         name:"repassword"
     },{
      xtype:"panel",
      layout:"column",
      border:false,
      isFormField:true,
      fieldLabel:"性别",
      defaults:{xtype:"radio",name:"sex"},
      items:[{
          boxLabel:"男",
          value:"0"
      },{
          boxLabel:"女",
          value:"1"
      }]
     },{
         xtype:"panel",
         layout:"column",
         border:false,
         isFormField:true,
         fieldLabel:"爱好",
         defaults:{xtype:"checkbox",name:"lover"},
         items:[{
             boxLabel:"音乐",
             value:"1"
         },{
             boxLabel:"电影",
             value:"2"
         },{
             boxLabel:"编程",
             value:"3"
         }]
     },{
      xtype:"datefield",
         fieldLabel:"出生日期",
         name:"birthday",
         format:"Y-m-d"
     }]
 });
 
 var fset2 = new Ext.form.FieldSet({
     title:"更多信息",
     width:450,
     //是否可折叠
     collapsible:true,
     collapsed:true,
     autoHeight:true,
     labelAlign:"left",
     labelWidth:80,
     defaults:{xtype:"textfield"},
     items:[{
         fieldLabel:"家庭地址",
         name:"address"
     },{
         fieldLabel:"联系电话",
         name:"phone"
     },{
         fieldLabel:"Email",
         name:"email"
     }]
 });
 
    var form = new Ext.form.FormPanel({
        title:"用户注册",
        width:500,
        items:[fset1,fset2],
        buttons:[{
            text:"提交",
            handler:function(){
             Ext.Msg.alert("提示","您确定注册吗?");
            }
        },{
            text:"重置",
            handler:function(){
             
            }
        }],
        renderTo:Ext.getBody()
    });
});
*/

/**
 * 表单验证[采用上面的分栏表单]
 */

//初始化快速提示 
Ext.QuickTips.init();

//插件类：用以在控件末尾提示输入信息
FormPlugin = function(msg){
 //构造函数中完成
 this.init = function(cmp){
  //控件渲染时触发
  cmp.on("render",function(){
      cmp.el.insertHtml("afterEnd","<font color='red'>*</font><font color='blue'>"+msg+"</font>");
  });
 }
}

//自定义vtype
Ext.apply(Ext.form.VTypes,{
 //自定义名称为password的vtype
    "password":function(value,field){
     //如果被比较的组件存在
     if(field.compareTo){
      //获取此组件
      var cmp = Ext.get(field.compareTo);
      return (value == cmp.getValue());
     }
    }
});

Ext.onReady(function(){
 
 var fset1 = new Ext.form.FieldSet({
     title:"基本信息",
     width:450,
     //是否可折叠
     collapsible:true,
     autoHeight:true,
     labelAlign:"left",
     labelWidth:80,
     defaults:{xtype:"textfield"},
     items:[{
         fieldLabel:"用户名",
         name:"username",
         emptyText:"---请输入用户名---",
         allowBlank:false,
         blankText:"用户名不能为空",
         plugins:new FormPlugin("英文，数字或下划线组成，5-12位")
     },{
         fieldLabel:"密码",
         id:"password1",
         name:"password",
         inputType:"password",
         allowBlank:false,
         blankText:"密码不能为空",
         plugins:new FormPlugin("英文，数字或下划线组成，5-12位")
     },{
         fieldLabel:"重复密码",
         name:"repassword",
         inputType:"password",
         allowBlank:false,
         blankText:"重复密码不能为空",
         compareTo:"password1",
         vtype:"password",
         vtypeText:"两次输入的密码不一致",
         plugins:new FormPlugin("再次输入密码")
     },{
      xtype:"panel",
      layout:"column",
      border:false,
      isFormField:true,
      fieldLabel:"性别",
      defaults:{xtype:"radio",name:"sex"},
      items:[{
          boxLabel:"男",
          value:"0"
      },{
          boxLabel:"女",
          value:"1"
      }]
     },{
         xtype:"panel",
         layout:"column",
         border:false,
         isFormField:true,
         fieldLabel:"爱好",
         defaults:{xtype:"checkbox",name:"lover"},
         items:[{
             boxLabel:"音乐",
             value:"1"
         },{
             boxLabel:"电影",
             value:"2"
         },{
             boxLabel:"编程",
             value:"3"
         }]
     },{
      xtype:"datefield",
         fieldLabel:"出生日期",
         name:"birthday",
         format:"Y-m-d"
     }]
 });
 
 var fset2 = new Ext.form.FieldSet({
     title:"更多信息",
     width:450,
     //是否可折叠
     collapsible:true,
     collapsed:false,
     autoHeight:true,
     labelAlign:"left",
     labelWidth:80,
     defaults:{xtype:"textfield"},
     items:[{
         fieldLabel:"家庭地址",
         name:"address"
     },{
         fieldLabel:"联系电话",
         name:"phone",
         regex:/^d{6,12}$/,
         regexText:"请正确输入"
     },{
         fieldLabel:"Email",
         name:"email",
         vtype:"email",
         vtypeText:"邮箱格式不正确",
         plugins:new FormPlugin("")
     }]
 });
 
    var fp = new Ext.form.FormPanel({
        title:"用户注册",
        width:500,
        items:[fset1,fset2],
        buttons:[{
            text:"注册",
            handler:function(){
             if(fp.getForm().isValid()){
              fp.getForm().submit({
                  url:"http://***",
                  method:"post",
                  success:function(form,action){
                   Ext.Msg.alert("友情提示","注册成功");
                  },
                  failure:function(form,action){
                   Ext.Msg.alert("友情提示","Sorry!注册失败");
                  }
              });
             }else{
              Ext.Msg.alert("提示","请正确填写完毕后再提交");
             }
            }
        },{
            text:"重置",
            handler:function(){
             fp.getForm().reset();
            }
        }],
        renderTo:Ext.getBody()
    });
});
