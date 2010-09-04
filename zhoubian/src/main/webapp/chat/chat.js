
Ext.onReady(function() {
	dwr.engine.setActiveReverseAjax(true);

	Ext.QuickTips.init();

	// Login window
	var login = new Ext.Window({
		title : '登录聊天室',
		width : 340,
		height : 140,
		resizable : false,
		hideBorders : true,
		layout:'fit',
		items : {
			xtype : 'form',
			monitorValid : true,
			bodyStyle : 'padding:10px 0 5px 5px',
			labelWidth : 70,
			baseCls : 'x-plain',
			items : [{
				id : 'loginName',
				xtype : 'textfield',
				name : 'loginName',
				anchor : '78%',
				maxLength : 15,
				fieldLabel : '用户名',
				validationEvent : false,
				validateOnBlur : false,
				allowBlank : false
			},{
				id : 'password',
				xtype : 'textfield',
				inputType:"password",
				name : 'password',
				anchor : '78%',
				maxLength : 15,
				fieldLabel : '密&nbsp;&nbsp;码',
				validationEvent : false,
				validateOnBlur : false,
				allowBlank : false
			}],
			buttons : [{
				id : 'signin',
				text : '登录',
				disabled : true,
				formBind : true,
				handler : doLogin
			}],
			keys : [{
				key : 13,
				fn : doLogin
			}]
		}
	});
	

	function doLogin() {
		var form = login.get(0).getForm();
		if (form.isValid()) {
			var o = form.getFieldValues();
			Ext.Ajax.request({
				url : 'addUser.do',
				params : {
					'loginName' : o.loginName,
					'password' : o.password
				},
				success : function(result) {
//					alert(result.responseText);
					var flag = Ext.decode(result.responseText).success;
					if(flag){
						form.ownerCt.close();
						showChatWindow(o.userName);
					}else{
						alert(Ext.decode(result.responseText).errorMessage);
					}
				}
			});
		}
	}
	
	if(loginName == ""){
		login.show();
	}else{
		showChatWindow(loginName);
	}

	// Chat window
	function showChatWindow(userName) {
		
		/******************************表情窗口********************************************/

		qqimagepanel=new Ext.extend(Ext.Panel,{
		       constructor:function(){
		          qqimagepanel.superclass.constructor.call(this,{
		            layout:'table',//声明布局类型为table
				  	autoScroll:true,
				  	layoutConfig : {
		              columns: 8 //设置表格布局默认列数为4列
		             },
		            defaults:{//设置默认属性
		                 bodyStyle:'padding:20px',
		                 width:50
		                 },
			        items:[]
		          });
		          this.initpanel();
		          this.addEvents("onImageButtons");
		      },
		      initpanel:function(){
		          this.items.clear();
			      for(var i =1; i<= 40;i++){
			          var btn = new Ext.Button({
			                cls : "x-btn-icon",
				            icon : "images/chat/qq/"+i+".gif",
				            handler : this.onImageButton,
		                    scope : this
			          });
			          this.items.add(""+(i-1)+"",btn);
			      }
		        
		      },onImageButton:function(imgbutton){
		         this.fireEvent("onImageButtons",imgbutton);
		         
		      }
		});
		
		/******************************发消息显示窗口****************************************************/
		htmleditors= Ext.extend(Ext.form.HtmlEditor,{
		    qqimagepanel:null,
		    win:null,
			constructor:function(){
			   qqimagepanel=new qqimagepanel();
			   qqimagepanel.on("onImageButtons",this.onImageButton);
			   htmleditors.superclass.constructor.call(this,{
			       enableFormat :false,
			       id:"html",
			       enableFontSize : true,
			       enableLinks :true,     //这是把链接的按钮去掉. 
		           enableLists : true,     // 这是把list 排序给去掉, 
		           enableColors : true,
		           height:140,
		           width:240,
		           enableAlignments:false,
		           enableSourceEdit : false,
		           enableFont : true,
			       frame:true,
//			       layout:"fit"
			      region:'center'
			   });
			  
			   this.on("render",this.addimage);
			   this.addEvents("onImages");
			   //this.initImageWin();
			},
			addimage:function(editor){
				      var tb =editor.getToolbar();
				      tb.addText("  ");
				      tb.insertButton(3,{
				          	text:"",
				          	cls : "x-btn-icon",
				            icon : "images/chat/qq/05.gif",
				            handler : this.insertImage,
		                    scope : this
				       });
			},
			openImageWin:function(){
				if(win == null)this.initImageWin();
				win.show();
			},
			closeImageWin:function(){
			   win.hide();
			   
			},
			initImageWin:function(){
			  win = new Ext.Window({
			       title:"插入表情",
			       layout:"fit",
			        width:210,
			       //modal :true,
			       height:150,
			       items:qqimagepanel,
			       close:this.closeImageWin
		      });
			},
			insertImage:function(){
				  var images = new Ext.Panel({
				  	layout:'table',//声明布局类型为table
				  	autoScroll:true,
				  	layoutConfig : {
		              columns: 8 //设置表格布局默认列数为4列
		             },
		            defaults:{//设置默认属性
		                 bodyStyle:'padding:20px',
		                 width:50
		                 },
			        items:[]
			      });
			      images.items.clear();
			      for(var i =1; i<= 40;i++){
			          var btn = new Ext.Button({
			                cls : "x-btn-icon",
			                tooltip :"表情",
				            icon : "images/chat/qq/"+i+".gif",
				            handler : this.onImageButton,
		                    scope : this
			          });
			          images.items.add(""+(i-1)+"",btn);
			      }
			     win = new Ext.Window({
			       title:"插入表情",
			       layout:"fit",
			       width:210,
			       //modal :true,
			       height:150,
			       items:images,
			       close:this.closeImageWin
			    });
			    win.show();
			},
			onImageButton:function(img){
			    this.fireEvent("onImages",img.icon);
			}
		});
		Ext.ComponentMgr.registerType('htmleditors', htmleditors);
		UserSendPanel=Ext.extend(Ext.Panel,{
			html:null,
		    constructor:function(){
		    	
		    	html=new htmleditors();
		    	html.on("onImages",this.imagesInsert);
			    UserSendPanel.superclass.constructor.call(this,{
			    	//title:"发送消息",
				      height:70,
				      layout:"fit",
				      items:html,
				      region:'center'
				      
			    });
			  },
			  imagesInsert:function(imgicon){
			  	 
			  	 this.setValue(this.getValue()+"<img src='"+imgicon+"'/>");
			     html.closeImageWin();
			  }
		});
//		var usersendPanel=new UserSendPanel();
//		alert("a");
		Ext.ComponentMgr.registerType('usersendPanel', UserSendPanel);
//		alert("b");

		var myPageSize = 20;

		var tpl = new Ext.XTemplate(
				'<tpl for=".">',
				'<font color="{[xindex % 2 === 0 ? "green" : "red"]}"><p>{data:this.parseSender()}: {data:this.parseDate} </p></font>',
				'<p style="padding:1 1 10 5">{data:this.parseText}</p>', '</tpl></p>', {
					compiled : true,
					parseSender : function(json) {
						return json.sender;
					},
					parseDate : function(json) {
						return Ext.util.Format.date(json.date, 'Y-m-d H:i:s');
					},
					parseText : function(json) {
						return json.text
					}
				});

		var store = new Ext.data.Store({
			url : 'findChatHistory.do',
			reader : new Ext.data.JsonReader({
				totalProperty : 'totalProperty',
				root : 'root'
			}, ['mid', 'id', 'sender', 'text', {
				name : 'date',
				dateFormat : "Y-m-dTH:i:s",
				type : 'date'
			}]),
			listeners : {
				'load' : function(sd, records, options) {
					tpl.overwrite(Ext.fly('history'), records);
				},
				'beforeload' : function(sd, options) {
					var date = Ext.getCmp('chatdate').getValue();
					var date2 = date.clone();
					date2.setDate(date2.getDate() + 1);
					date2.setSeconds(date2.getSeconds() - 1);

					sd.setBaseParam('page.strCondition', date.format('Y-m-d H:i:s'));
					sd.setBaseParam('page.strCondition2', date2.format('Y-m-d H:i:s'));
					sd.setBaseParam('page.start', 0);
					sd.setBaseParam('page.limit', myPageSize);
				}
			}
		});

		var page = new Ext.PagingToolbar({
			store : store,
			displayInfo : true,
			pageSize : myPageSize,
			paramNames : {
				start : 'page.start',
				limit : 'page.limit'
			}
		});
		page.insert(0, '-');
		page.insert(0, {
			xtype : 'datefield',
			id : 'chatdate',
			format : 'Y-m-d',
			value : new Date(),
			listeners : {
				'select' : function() {
					store.load();
				}
			}
		});

		var win = new Ext.Window({
			id : 'chatwin',
			title : userName,
			layout : 'fit',
			width : 500,
			height : 400,
			resizable : false,
			items : [{
				border : false,
				xtype : 'tabpanel',
				activeTab : 0,
				items : [{
					title : 'Group',
					layout : 'border',
					iconCls : 'icon-chat-comments',
					items : [{
						region : 'center',
						layout : 'border',
						margins : '-1 -1 0 -1',
						items : [new Ext.Panel({
							region : 'center',
							margins : '-1 0 -2 -2',
							layout : 'fit',
							items : [{
								xtype : 'panel',
								id : 'chatlog',
								autoScroll : true,
								border : false,
								style : 'padding:5px'
							}]
						}), {
							region : 'east',
							title : 'Online users',
							collapsible : true,
							split : true,
							margins : '-1 -1 -1 0',
							tools : [{
								id : 'refresh',
								handler : function(event, toolEl, panel) {
									//alert("hello" + panel);
									panel.items.itemAt(0).getRootNode().reload();
								}
							}],
							minWidth : 150,
							maxWidth : 200,
							width : 150,
							items : {
								id: 'usertree',
								xtype : 'treepanel',
								border : false,
								dataUrl : 'getOnlineUsers.do',
								rootVisible : false,
								root : {
									nodeType : 'async',
									id : 'online'
								}
							}
						}]
					}, {
						region : 'south',
						layout : 'border',
						minHeight : 50,
						maxHeight : 150,
						height : 70,
						items : [{
							region : 'center',
							xtype : 'usersendPanel',
							id:'msg'
						}, {
							xtype : 'button',
							region : 'east',
							width : 70,
							margins : '0 4 4 4',
							text : 'Send',
							tooltip : 'CTRL + ENTER',
							handler : function() {
								sendChat(this, userName);
							}
						}]
					}]
				}, {
					title : 'Message History',
					iconCls : 'icon-chat-record',
					autoScroll : true,
					items : [{
						xtype : 'displayfield',
						id : 'history',
						style : 'padding:5px;overflow-x:hidden'
					}],
					bbar : page
				}]
			}],
			onEsc : function() {
				this.hide();
			},
			listeners : {
				'minimize' : function() {
					this.hide();
				}
			}
		});

		win.show();
	}
});

// 发送聊天信息
function sendChat(cmp, userName) {
	var textarea = cmp.ownerCt.items.first().items.first();
//	alert(textarea);
	var value = textarea.getValue();
	if (value.length > 0) {
		var el = cmp.ownerCt.el;
		el.mask('Sending...', 'x-mask-loading');
		ChatAction.addChat(value, userName, function() {
			el.unmask();
			textarea.setValue('');
		});
	}
	textarea.focus();
};

// 接受消息
function receiveChats(chat) {
//	alert(chat);
	var chatlog = Ext.getCmp('chatlog');
	var tpl = new Ext.XTemplate(
			'<p><font color="green">{sender}: </font>{date:this.parseDate} </p><p style="padding:1 1 10 5">{text:this.parseText}</p>',
			{
				compiled : true,
				parseDate : function(date) {
					return Ext.util.Format.date(date, 'H:i:s');
				},
				parseText : function(text) {
//					alert(text);
					return Ext.util.Format.htmlDecode(text);
					//return Ext.util.Format.nl2br(Ext.util.Format.htmlEncode(text));
				}
			});
	tpl.append(chatlog.body, chat);
	chatlog.body.scroll('b', 100000, true);
}

//添加新进入聊天室用户
function addUser(user) {
	var usertree = Ext.getCmp('usertree');
	var node=new Ext.tree.TreeNode(Ext.decode(user));
	usertree.getRootNode().removeChild(node,true);
	usertree.getRootNode().appendChild(node);
}

//删除退出聊天室用户
function removeUser(id) {
	var usertree = Ext.getCmp('usertree');
	var node=usertree.getNodeById(Ext.decode(id))
	usertree.getRootNode().removeChild(node,true);
}

window.onbeforeunload = function checkLeave(e){
//	if (event.clientY<0 && event.clientX>document.body.clientWidth-20 
//		   || event.clientY<0 && event.clientX<20 || event.altKey 
//		   || event.clientY>document.body.clientHeight) {
        
//	}
//		var evt = e ? e : (window.event ? window.event : null);        //此方法为了在firefox中的兼容
//		window.event.returnValue="确认结束当前会话？"; 
		Ext.Ajax.request({url : 'chatLogout.do'});
}