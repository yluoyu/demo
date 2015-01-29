Ext.define('Demo.view.ResourcePanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.resourcepanel',
	requires : ['Ext.form.FormPanel', 'Ext.grid.Panel',
			'Ext.form.FieldContainer', 'Ext.form.field.Checkbox'
			],
	animCollapse : true,
	layout : 'border',

	initComponent : function() {
        var thisPanel = this;
		Ext.apply(this, {
					items : [this.createFilterPanel(), this.createGrid()]
				});
				
				
		var status = Ext.create('Ext.data.Store', {
			fields: [{name: 'key', type: 'string'},
			         {name: 'value',  type: 'string'}],
		     data : [
		         {key: '0',    value: '无效'},
		         {key: '1', value: '有效'}
		     ]
		 });
		
		var role = Ext.create('Ext.data.Store', {
			fields: [{name: 'key', type: 'string'},
			         {name: 'value',  type: 'string'}],
		     data : [
		         {key: '0',    value: '普通用户'},
		         {key: '1', value: '管理员用户'}
		     ]
		 });


		this.resourcePanel = Ext.create('Ext.form.FormPanel', {
			layout : 'anchor',
			defaults : {
				margin : '5 0 5 5',
				labelWidth : 60
			},
			defaultType : 'textfield',
			items : [{
				        fieldLabel : 'id',
						name : 'id',
						allowBlank : false
					}, {
						fieldLabel : 'URL',
						name : 'url',
						allowBlank : false
					}, {
						fieldLabel : '权限',
						name : 'authority',
						allowBlank : true
					}],
			buttons : [{
				text : '保存',
				formBind : true,
				handler : function() {
					Ext.MessageBox.confirm('用户修改确认框', '确认要修改用户信息?', function(but) {
						if (but == 'yes') {
							thisPanel.resourcePanel.form.submit({
								 url:  Demo.app.contextRoot + '/sysresource/save',
			                     method:'POST',  
			                     success:function(){  
			                        Ext.Msg.alert('提示',"提交数据");  
			                    }  
							})
						}
					});
					thisPanel.store.load();
				}

			}, {
				text : '关闭',
				handler : function() {
					thisPanel.resourceForm.hide();
				}
			}]
		});
		this.callParent(arguments);
	},

	createFilterPanel : function() {

		var status = Ext.create('Ext.data.Store', {
		fields: [ {name: 'value'}],
	     data : [
	         {value: '无效'},
	         {value: '有效'}
	     ]
	 });
		var role = Ext.create('Ext.data.Store', {
			fields: [{name: 'key', type: 'string'},
			         {name: 'value',  type: 'string'}],
		     data : [
		         {key: '0',    value: '普通用户'},
		         {key: '1', value: '管理员用户'}
		     ]
		 });
		var thisPanel = this;
		this.filterPanel = Ext.create('Ext.form.FormPanel', {
					region : 'north',
					layout : 'anchor',
					collapsible : true,
					defaults : {
						margin : '5 0 5 5',
						labelWidth : 60
					},
					defaultType : 'textfield',
					items : [{
								fieldLabel : 'url',
								name : 'url',
								allowBlank : true
							}, {
								fieldLabel : '权限',
								name : 'authority',
								allowBlank : true
							}],
					buttonAlign : 'left',
					buttons : [{
								text : '重置',
								handler : function() {
									this.up('form').getForm().reset();
									//thisPanel.loadUser();
								}
							}, {
								text : '搜索',
								formBind : true,
								handler : function() {
									thisPanel.loadResource();
								}
							}, {
								text : '新建',
								formBind : true,
								handler : function() {
									
									if (thisPanel.resourceForm == null) {
										thisPanel.resourceForm = Ext.create(
												'widget.window', {
													title : '创建资源',
													closable : true,
													modal : true,
													resizable : false,
													closeAction : 'hide',
													width : 600,
													minWidth : 350,
													height : 350,
													layout : {
														type : 'fit',
														padding : 5
													},
													items : thisPanel.resourcePanel
												});
									}
									thisPanel.resourcePanel.form.reset();
									thisPanel.resourceForm.show(this);
								}
							}, {
								text : '编辑',
								formBind : true,
								handler : function() {
									if (thisPanel.resourceForm == null) {
										thisPanel.resourceForm = Ext.create(
												'widget.window', {
													title : '修改资源',
													closable : true,
													modal : true,
													resizable : false,
													closeAction : 'hide',
													width : 600,
													minWidth : 350,
													height : 350,
													layout : {
														type : 'fit',
														padding : 5
													},
													items : thisPanel.resourcePanel
												});
									}
									thisPanel.resourceForm.show(this);
								}
							}, {
								text : '删除',
								handler : function() {
									var rec  = thisPanel.checkSM.getLastSelected();
									if(rec){
										
										Ext.MessageBox.confirm('提示', '确认要删除?', function(but) {
											if (but == 'yes') {
												Ext.Ajax.request({
													url : Demo.app.contextRoot + '/sysresource/delete',
													params : {
														id : rec.get('id')
													},
													method : 'post',
													success : function(response) {
														var textObj = Ext.JSON.decode(response.responseText);
														if (textObj.success == 1) {
															Ext.MessageBox.alert("消息提示", textObj.message);
														} else {
															Ext.MessageBox.alert("错误提示", textObj.message);
														}

													},
													failure : function(response) {
														Ext.MessageBox.alert("错误提示", "服务器发生错误！");
													}
												});
											}
										});
									}else{
										Ext.MessageBox.alert("提示","请选择一条记录");
									}
									
								}
							}]
				});
		return this.filterPanel;
	},

	createGrid : function() {
		var thisCreateGrid = this;
		this.store = Ext.create('Ext.data.Store', {
					pageSize : 10,
					model : 'Demo.model.Resource',
					remoteSort : true,
					proxy : {
						type : 'ajax',
						url : Demo.app.contextRoot + '/sysresource/list',
						reader : {
							type : 'json',
							root : 'data.data',
							totalProperty : 'data.recordTotal'
						},
						extraParams : {
							authority : "",
							url : ""
						},
						simpleSortMode : true
					},
					autoLoad:true
				});
		
		this.checkSM = Ext.create('Ext.selection.CheckboxModel',{
		     singleSelect: false,
		     listeners : {
				select : function(thisModel, record, index, eOpts) {
				},
				deselect : function(thisModel, record, index, eOpts) {
				},
				selectionchange: function(thisModel,record,eOpts){

				}
			}
		});
		
		this.userGrid = Ext.create('Ext.grid.Panel', {
					store : this.store,
					region : 'center',
					loadMask : true,
					selModel:this.checkSM,
					viewConfig : {
						stripeRows : false
					},
					columns : [{
								text : '编号',
								hidden : true,
								hideable : false,
								dataIndex : 'id',
								width : 150,
								name : 'id'
							},  {
								text : 'URL',
								dataIndex : 'url',
								width : 100,
								name : 'url'
							}, {
								text : '权限',
								dataIndex : 'authority',
								flex : 1,
								name : 'authority'
							}],
					bbar : Ext.create('Ext.PagingToolbar', {
								store : this.store,
								displayInfo : true,
								displayMsg : '正显示{2}条记录中的第{0}-{1}条',
								emtpyMsg : '无数据'
							}),
					listeners : {
						scope : this,
						celldblclick : this.onSelect
					}
				});
		return this.userGrid;
	},

	onSelect : function(grid, td, cellIndex, record, tr,
			rowIndex, e, eOpts) {
		var id = record.data.id;
		var thisPanel = this;
		if (this.resourceForm == null) {
			this.resourceForm = Ext.create('widget.window', {
				title : '修改资源',
				closable : true,
				closeAction : 'hide',
				modal : true,
				width : 600,
				resizable : false,
				minWidth : 350,
				height : 350,
				layout : {
					type : 'fit',
					padding : 5
				},
				items : this.resourcePanel
			});
		}
		this.resourcePanel.load({
			url : Demo.app.contextRoot + '/sysresource/get',
			method : 'get',
			params : {
				id : id
			}
		})
		this.resourceForm.show(grid);
	},

	onPaneActive : function() {
		this.store.removeAll();
		this.filterPanel.getForm().reset();
		this.store.load();
	},



	loadResource : function() {
		var thisPanel = this;
		var values = this.filterPanel.form.getValues();
		this.store.getProxy().extraParams.url = encodeURI(values.url);
		this.store.getProxy().extraParams.authority = encodeURI(values.authority);
		
		thisPanel.store.loadPage(1, {
					params : {
						url : encodeURI(values.url),
						authority : encodeURI(values.authority)
					}
				});
	}

});
