/** 修改密码 */
Ext.define('Demo.view.AccountPwdPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.accountpwdpanel',
	animCollapse : true,
	layout : 'border',
	initComponent : function() {
		Ext.apply(this, {
					items : [this.updateUserPassword()]
				});

		this.callParent(arguments);
	},

	updateUserPassword : function() {
		Ext.apply(Ext.form.VTypes, {
					account_password : function(val, field) {
						if (field.confirmTo) {
							var pwd = Ext.getCmp(field.confirmTo);
							if (val == pwd.getValue()) {
								return true;
							}
							return false;
						}
					},
					account_passwordText : '新确认密码不一致'
				});
				
		this.filterPanel = Ext.create('Ext.form.FormPanel', {
					region : 'center',
					layout : 'anchor',
					defaults : {
						margin : '5 0 5 5',
						labelWidth : 70,
						allowBlank : false,
						minLength : 6,
						maxLength : 18,
						inputType : 'password'
					},
					defaultType : 'textfield',
					items : [{
								fieldLabel : '旧密码',
								name : 'password',
								blankText : '旧密码不能为空',
								minLengthText : '旧密码不能小于6位',
								maxLengthText : '旧密码不能大于18位'
							}, {
								fieldLabel : '新密码',
								name : 'newPassword',
								id : 'newPassword',
								blankText : '新密码不能为空',
								minLengthText : '新密码不能小于6位',
								maxLengthText : '新密码不能大于18位'
							}, {
								fieldLabel : '新密码确认',
								name : 'newPasswordConde',
								blankText : '新密码确认不能为空',
								minLengthText : '新密码确认不能小于6位',
								maxLengthText : '新密码确认不能大于18位',
								vtype : 'account_password',
								confirmTo : 'newPassword',
								passowrdText : '新密码确认不一致'
							}],
					buttonAlign : 'left',
					buttons : [{
						text : '修改密码',
						formBind : true,
						disabled : true,
						handler : function() {
							var passwordVal = this.up('form').getForm()
									.findField("password").getValue();
							var newPasswordVal = this.up('form').getForm()
									.findField("newPassword").getValue();
							Ext.Ajax.request({
										url : Demo.app.contextRoot + '/test/changePwd',
										params : {
											password : passwordVal,
											newPassword : newPasswordVal
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

					}]
				});
		return this.filterPanel;
	},
	onPaneActive : function() {
		this.filterPanel.getForm().reset();
	}

});
