Ext.define('Demo.view.Viewport', {
    extend: 'Ext.container.Viewport',
    requires:[
        'Ext.layout.container.Fit',
        'Ext.tab.Panel','Ext.view.View',
        'Ext.layout.container.Column',
        'Ext.form.field.Date',
        'Ext.layout.container.Border',
        'Ext.layout.container.Accordion',
        'Demo.view.ModulePanel',
        'Demo.model.Module',
        'Demo.model.User',
        'Demo.model.Resource',
        'Demo.model.Task',
        'Demo.model.Document',
        'Demo.view.TreeComboBox',
        'Demo.view.TreeGrid',
        'Demo.view.MultiComboBox',
        'Demo.view.QueryPanel',
        'Demo.view.TestPanel',
        'Demo.view.LogPanel',
        'Demo.view.UserPanel',
        'Demo.view.DocumentPanel',
        'Demo.view.ResourcePanel',
        'Demo.view.AccountPwdPanel',
        'Demo.view.AccountInfoPanel'
        
    ],
	initComponent : function() {				
		Ext.apply(this, {
					layout : {
						type : 'border',
						padding : 1
					},
					items : [this.createBanner(),
							this.createModuleList(),
							this.createMainContainer()]
				});
		this.callParent(arguments);

	},
	createBanner : function() {
		this.banner = Ext.create('Ext.container.Container', {
			region : 'north',
			layout : 'hbox',
			height : 80,
			items : [ {
				xtype : 'container',
				width : 359,
				height : 80
				//cls : 'logo'
			}, {
				xtype : 'container',
				height : 80,
				cls : 'vertical-align:bottom',
				html :'<div style="height : 100%;text-align:right;padding-top:60px;padding-right:10px;">欢迎 : <font color="white">' + '用户' + '&nbsp;|&nbsp;<a href = "'+Demo.app.contextRoot+'/user/dologout" style="text-decoration:none;color:#FFF">退出</a> </font></div>',
				flex : 1
				//cls : 'banner1'
			} ]
		});
		return this.banner;
	},
	createModuleList : function() {
		var items = new Array();
			var queryModule = Ext.create('Demo.view.ModulePanel', {
				title : '我的查询',
				iconCls : 'user',
				modules : [{
							name : 'data_query',
							title : 'TreeGrid',
							url : '#'
						},{
							name : 'resource_manager',
							title : '资源管理',
							url : '#'
						}],
				listeners : {
					scope : this,
					moduleselect : this.onModuleSelect
				}
			});
			items.push(queryModule);
			

			
			var userModule = Ext.create('Demo.view.ModulePanel', {
				title : '用户管理',
				iconCls : 'info',
				modules : [{
							name : 'user_manager',
							title : '用户管理',
							url : '#'
						},{
							name : 'document_manager',
							title : '文件管理',
							url : '#'
						}],
				listeners : {
					scope : this,
					moduleselect : this.onModuleSelect
				}
			});
			items.push(userModule);

		var infoModule = Ext.create('Demo.view.ModulePanel', {
			title : '账户',
			iconCls : 'info',
			modules : [{
						name : 'account_info',
						title : '用户信息',
						url : '#'
					}, {
						name : 'account_pwd',
						title : '修改密码',
						url : '#'
					}],
			listeners : {
				scope : this,
				moduleselect : this.onModuleSelect
			}
		});
		items.push(infoModule);
				
		var exitBtn = Ext.create('Ext.panel.Panel', {
			title : '退出',
			iconCls : 'logout',
			collapsible : false,
			hideCollapseTool : true,
			header : {
				listeners : {
					scope : this,
					click : function() {
						Ext.MessageBox.confirm("退出", "是否确认退出?", function(buttonId) {
							if (buttonId == 'yes') {
								//window.location = Inforstack.staff.app.contextRoot + '/staff/dologout';
							}
						});
					}
				}
			}
		});
		items.push(exitBtn);

		this.moduleList = Ext.create('Ext.panel.Panel', {
					region : 'west',
					title : '导航',
					collapsible : true,
					animCollapse : true,
					margins : '0 0 0 5',
					layout : 'accordion',
					width : 200,
					minWidth : 175,
					maxWidth : 400,
					split : true,
					items : items
				});
		return this.moduleList;
	},
	createMainContainer : function() {
		this.mainPanel = Ext.create('Ext.panel.Panel', {
					title : '首页',
					layout : 'card',
					items : [{
								itemId : 'resource_manager',
								xtype : 'resourcepanel'
					        },{
								itemId : 'document_manager',
								xtype : 'documentpanel'
							},{
								itemId : 'user_manager',
								xtype : 'userpanel'
							}, {
								itemId : 'account_info',
								xtype : 'accountinfopanel'
							}, {
								itemId : 'account_pwd',
								xtype : 'accountpwdpanel'
							}]
				});
		this.mainPanel.getLayout().setActiveItem('document_manager');
		var pane = this.mainPanel.getLayout().getActiveItem();
		if (pane != null && pane.onPaneActive) {
			pane.onPaneActive();
		}
		this.mainContainer = Ext.create('Ext.tab.Panel', {
					region : 'center',
					items : [this.mainPanel]
				});
		return this.mainContainer;
	},
	onModuleSelect : function(moduleList, module, name, title, url) {
		if (name == 'data_query') {
			var panel = Ext.create(
					'Demo.view.QueryPanel', {
						closable:true,
						title : title
					})
			this.mainContainer.add(panel);
			this.mainContainer.setActiveTab(panel);
			if (panel != null && panel.onPaneActive) {
				panel.onPaneActive();
			}
			moduleList.deselectAll();
		} else {
			if (this.mainPanel != this.mainContainer.activeTab) {
				this.mainContainer.setActiveTab(this.mainPanel);
			}
			this.mainPanel.setTitle(moduleList.title + ' - '
					+ title);
			this.mainPanel.getLayout().setActiveItem(name);
			var pane = this.mainPanel.getLayout()
					.getActiveItem();
			if (pane != null && pane.onPaneActive) {
				pane.onPaneActive();
			}
		}
	},
	addPanel: function (panel){
		this.mainContainer.add(panel);
	}

});
