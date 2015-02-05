Ext.define('Demo.view.UserPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.userpanel',
	requires : ['Ext.form.FormPanel', 'Ext.grid.Panel',
			'Ext.form.FieldContainer', 'Ext.form.field.Checkbox'
			],
	animCollapse : true,
	layout : 'border',

	initComponent : function() {

		Ext.apply(this, {
					items : [this.createFilterPanel(), this.createGrid()]
				});
				
				Ext.apply(Ext.form.VTypes, {
					//用户姓名,
					name : function(val, field) {
						var reg = /^[\u4e00-\u9fa5a-zA-Z][\u4e00-\u9fa5a-zA-Z]{1,15}$/;
						return reg.test(val);
					},
					nameText : '请输入正确的姓名',
					//用户账号
					username : function(val, field) {
						var reg = /^[a-zA-Z][a-zA-Z0-9_]{3,15}$/;
						return reg.test(val);
					},
					usernameText : '用户账号以字母开头,长度4-16',
					
					password : function (val, field){
						if(field.confirmTo){
							var pwd = Ext.getCmp(field.confirmTo);
							if (val == pwd.getValue()){
								return true;
							}
							return false;
						}
					},
					passwordText : '确认密码不一致',
					
					telephone : function (val, field){
						var reg = /^1[3|5|7|8][0-9]{9}$/;
						if(!reg.test(val)){
							return false;
						}
						return true;
					},
					telephoneText : '请输入正确的手机号码',
					
					zipcode : function (val, field){
						var reg = /^[1-9]\d{5}$/;
						return reg.test(val);
					},
					zipcodeText : '请输入正确的邮政编码'
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
		userCreateForm : null;
		var thisCreatePanel = this;
		this.userCreatePanel = Ext.create('Ext.form.FormPanel', {
					layout : 'anchor',
					defaults : {
						margin : '5 0 5 5',
						labelWidth : 60
					},
					defaultType : 'textfield',
					items : [{
								fieldLabel : '用户名',
								name : 'name',
								allowBlank : false,
								blankText : '用户名不能为空',
								vtype : 'name'
							}, {
								fieldLabel : '用户账号',
								name : 'username',
								allowBlank : false,
								blankText : '用户账号不能为空',
								vtype : 'username'
							}, {
								fieldLabel : '密码',
								name : 'password',
								id : 'inner_Password',
								inputType : 'password',
								allowBlank : false,
								blankText : '密码不能为空',
								minLength : 6,
								minLengthText : '密码长度不能少于6位',
								maxLength : 18,
								maxLengthText : '密码长度不能大于18位'
							}, {
								fieldLabel : '确认密码',
								name : 'qrpassword',
								inputType : 'password',
								allowBlank : false,
								blankText : '确认密码不能为空',
								minLength : 6,
								minLengthText : '确认密码长度不能少于6位',
								maxLength : 18,
								maxLengthText : '确认密码长度不能大于18位',
								confirmTo : 'inner_Password',
								vtype : 'password'
							}, {
								fieldLabel : '联系电话',
								name : 'telephone',
								allowBlank : true,
								vtype : 'telephone'
							}, {
								fieldLabel : '电子邮件',
								name : 'email',
								allowBlank : false,
								blankText : "电子邮件不能为空",
								width : 300,
								vtype : 'email',
								vtypeText : '请输入正确的电子邮件'
							}, {
								fieldLabel : '部门',
								name : 'department',
								allowBlank : true,
								maxLength : 32,
								maxLengthText : '请输入正确的部门'
							},{
								fieldLabel : '角色',
								xtype : 'combo',
								editable : false,
								store : role,
								queryMode : 'local',
								displayField : 'value',
								valueField : 'key',
								name : 'role',
								allowBlank : false
							},
							{
							    name: 'smallType',
							    fieldLabel: '权限',
							    xtype: 'treecombobox',
							    valueField: 'duration',
							    displayField: 'task',
							    store: Ext.create('Ext.data.TreeStore', { 
					                model: Demo.model.Task,
					                rootVisible : true,
					                proxy: {
					                    type: 'ajax',
					                    url: 'resources/treejson2.json'
					                }
							    })
							}],

					buttons : [{
						text : '保存',
						formBind : true,
						handler : function() {
							var roles = Ext.getCmp('create_roles').items;
							var role = "";
							for (var i = 0; i < roles.length; i++) {
								if (roles.get(i).checked) {
									role += roles.get(i).inputValue + ",";
								}
							}
							//获取表单的值. name 是key , value 是值
							var values = this.up('form').form.getValues();
							values.role = role;
							Ext.Ajax.request({
										url : demo.app.contextRoot + '/user/save',
										params : values,
										method : 'post',
										success : function(response) {

											var textObj = Ext.JSON.decode(response.responseText);
											if (textObj.success == 1) {
												//thisCreatePanel.loadUser();
												thisCreatePanel.userCreatePanel.getForm().reset();
												thisCreatePanel.userCreateForm.hide();
											} else {
												Ext.MessageBox.alert("错误消息", textObj.message);
											}
										},
										failure : function(response) {
											Ext.MessageBox.alert("错误消息", "服务器发生错误！");
										}
									});
						}
					}, {
						text : '关闭',
						handler : function() {
							thisCreatePanel.userCreateForm.hide();
						}
					}]
				});
				
		var thisDetailPanel = this;
		this.userDetailPanel = Ext.create('Ext.form.FormPanel', {
			layout : 'anchor',
			defaults : {
				margin : '5 0 5 5',
				labelWidth : 60
			},
			defaultType : 'textfield',
			items : [{
						fieldLabel : '用户编号',
						name : 'id',
						hidden : true
					}, {
						fieldLabel : '用户名',
						name : 'username',
						allowBlank : false,
						blanText : '请输入用户名',
						vtype : 'name'
					}, {
						fieldLabel : '联系电话',
						name : 'telephone',
						allowBlank : true,
						vtype : 'telephone'
					}, {
						fieldLabel : '电子邮件',
						name : 'email',
						allowBlank : false,
						blankText : "电子邮件不能为空",
						width : 300,
						vtype : 'email',
						vtypeText : '请输入正确的电子邮件'
					}, {
						fieldLabel : '部门',
						name : 'department',
						allowBlank : true,
						maxLength : 32,
						maxLengthText : '请输入正确的部门'
					}, {
						fieldLabel : '状态',
						xtype : 'combo',
						editable : false,
						store : status,
						queryMode : 'local',
						name : 'status',
						allowBlank : false
					},{
						fieldLabel : '角色',
						xtype : 'multicombobox',
						editable : false,
						store : role,
						queryMode : 'local',
						displayField : 'value',
						valueField : 'key',
						name : 'role',
						allowBlank : false
					}],
			buttons : [{
				text : '修改',
				formBind : true,
				handler : function() {
					Ext.MessageBox.confirm('用户修改确认框', '确认要修改用户信息?', function(but) {
						if (but == 'yes') {
							thisDetailPanel.userDetailPanel.form.submit({
								 url:  Demo.app.contextRoot + '/test/save',
			                     method:'POST',  
			                     success:function(){  
			                        Ext.Msg.alert('提示',"提交数据");  
			                    }  
							})
/*							var roles = Ext.getCmp('update_roles').items;
							var role = "";
							for (var i = 0; i < roles.length; i++) {
								if (roles.get(i).checked) {
									role += roles.get(i).inputValue + ",";
								}
							}
							var values = thisDetailPanel.userDetailPanel.form.getValues();
							values.role = role;

							Ext.Ajax.request({
										url : credit.app.contextRoot + '/user/account',
										params : values,
										method : 'post',
										success : function(response) {
											var textObj = Ext.JSON.decode(response.responseText);
											if (textObj.success == 1) {
												thisDetailPanel.loadUser();
												//thisDetailPanel.userDetailForm.hide();
											} else {
												Ext.MessageBox.alert("错误消息", textObj.message);
											}
										},
										failure : function(response) {
											Ext.MessageBox.alert("错误消息", "服务器发生错误！");
										}
									});*/
						}
					});
				}

			}, {
				text : '关闭',
				handler : function() {
					thisDetailPanel.userDetailForm.hide();
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
								fieldLabel : '用户账号',
								name : 'username',
								allowBlank : true
							}, {
								fieldLabel : '用户名',
								name : 'name',
								allowBlank : true
							}, {
								fieldLabel : '角色',
								xtype : 'combo',
								editable : false,
								autoSelect : true,
								store : role,
								queryMode : 'local',
								displayField : 'value',
								valueField : 'key',
								name : 'role',
								value : '管理员用户',
								allowBlank : true
							}, {
								fieldLabel : '状态',
								xtype : 'multicombobox',
								editable : false,
								autoSelect : true,
								store : status,
								value : '有效',
								queryMode : 'local',
								name : 'status',
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
									thisPanel.loadUser();
								}
							}, {
								text : '新建',
								formBind : true,
								handler : function() {
									
									if (thisPanel.userCreateForm == null) {
										thisPanel.userCreateForm = Ext.create(
												'widget.window', {
													title : '创建用户',
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
													items : thisPanel.userCreatePanel
												});
									}
									thisPanel.userCreateForm.show(this);
								}
							}, {
								text : '编辑',
								formBind : true,
								handler : function() {
									if (thisPanel.userCreateForm == null) {
										thisPanel.userCreateForm = Ext.create(
												'widget.window', {
													title : '修改用户',
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
													items : thisPanel.userCreatePanel
												});
									}
									thisPanel.userCre
									thisPanel.userCreateForm.show(this);
								}
							}]
				});
		return this.filterPanel;
	},

	createGrid : function() {
		var thisCreateGrid = this;
		this.store = Ext.create('Ext.data.Store', {
					pageSize : 10,
					model : 'Demo.model.User',
					remoteSort : true,
					proxy : {
						type : 'ajax',
						url : Demo.app.contextRoot + '/test/list',
						reader : {
							type : 'json',
							root : 'data.data',
							totalProperty : 'data.recordTotal'
						},
						extraParams : {
							name : "",
							username : "",
							status : ""
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
								text : '用户编号',
								hidden : true,
								hideable : false,
								dataIndex : 'id',
								width : 150,
								name : 'id'
							},  {
								text : '用户名',
								dataIndex : 'username',
								width : 100,
								name : 'name'
							}, {
								text : '电子邮件',
								dataIndex : 'email',
								flex : 1,
								name : 'email'
							}, {
								text : '联系电话',
								dataIndex : 'telephone',
								width : 120,
								name : 'telephone'
							}, {
								text : '状态',
								dataIndex : 'status',
								width : 90,
								name : 'status'
							}],
					bbar : Ext.create('Ext.PagingToolbar', {
								store : this.store,
								displayInfo : true,
								displayMsg : '正显示{2}条记录中的第{0}-{1}条',
								emtpyMsg : '无数据'
							}),
					listeners : {
						scope : this,
						celldblclick : this.onSelectUser
					}
				});
		return this.userGrid;
	},

	userDetailForm : null,
	onSelectUser : function(grid, td, cellIndex, record, tr,
			rowIndex, e, eOpts) {
		var userid = record.data.id;
		var thisPanel = this;
		Ext.Ajax.request({
			url : Demo.app.contextRoot + '/test/getUserInfo',
			method : 'get',
			params : {
				userid : userid
			},
			success : function(response) {
				var object = Ext.JSON
						.decode(response.responseText);
				if (object.success == 1) {
					var ob = object.data;
					if (ob != null) {
						// 给表单set值
						thisPanel.userDetailPanel.form
								.setValues({
									id : ob.id,
									name : ob.name,
									email : ob.email,
									telephone : ob.telephone,
									department : ob.department,
									status : ob.status,
									user : ob.userRole,
									compond : ob.compoundRole,
									project : ob.projectRole,
									content : ob.contentRole,
									system : ob.systemRole
								});
					}
				} else {
					Ext.MessageBox
							.alert("错误提示", object.message);
				}
			}
		});
		if (this.userDetailForm == null) {
			this.userDetailForm = Ext.create('widget.window', {
				title : '修改内部用户',
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
				items : this.userDetailPanel
			});
		}
		this.userDetailPanel.load({
			url : Demo.app.contextRoot + '/test/getUserInfo',
			method : 'get',
			params : {
				userid : userid
			}
		})
		this.userDetailForm.show(grid);
	},

	onPaneActive : function() {
		this.store.removeAll();
		this.filterPanel.getForm().reset();
		this.userCreatePanel.getForm().reset();
		this.store.load();
	},



	loadUser : function() {
		var thisPanel = this;
		var values = this.filterPanel.form.getValues();
		
		var role = "";

		this.store.getProxy().extraParams.name = encodeURI(values.name);
		this.store.getProxy().extraParams.username = encodeURI(values.username);
		this.store.getProxy().extraParams.role = role;
		this.store.getProxy().extraParams.status = values.status;
		
		thisPanel.store.loadPage(1, {
					params : {
						name : encodeURI(values.name),
						username : encodeURI(values.username),
						role : role,
						status : values.status
					}
				});
	}

});
