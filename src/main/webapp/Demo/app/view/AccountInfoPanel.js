/* AccountInfo */
Ext.define('Demo.view.AccountInfoPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.accountinfopanel',

	requires : ['Ext.form.FormPanel'],

	animCollapse : true,
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {items : [this.createAccountForm()]
				});
		var thisPanel = this;
		this.callParent(arguments);
	},
	
	createAccountForm : function() {
		this.accountForm = Ext.create('Ext.form.FormPanel', {
			region : 'center',
			layout : 'anchor',
			defaults : {
				margin : '5 0 5 5',
				labelWidth : 60
			},
			defaultType : 'textfield',
			items : [{
						fieldLabel : '姓名',
						name : 'name',
						allowBlank : false,
						blankText : '不允许为空'
					}, {
						fieldLabel : '部门',
						name : 'department',
						allowBlank : false,
						blankText : '不允许为空'
					}, {
						fieldLabel : '电话',
						name : 'telephone',
						allowBlank : false,
						blankText : '不允许为空'
					}, {
						fieldLabel : '邮箱',
						name : 'email',
						width : 300,
						vtype:'email', 
						allowBlank : false,
						blankText : '不允许为空'
					}, {
						fieldLabel : '创建时间',
						name : 'createTime',
						disabled:true,
       					hidden:false,
       					blankText : '不允许为空'
					}, {
						fieldLabel : '更新时间',
						name : 'updateTime',
						disabled:true,
       					hidden:false
					}],
			buttonAlign : 'center',
			buttons : [{
						text : '更改',
						formBind : true,
						disabled : true,
						handler : function() {
							if(this.up('form').getForm().isValid() == true){
								var name = this.up('form').getForm().findField("name").getValue();
								var department = this.up('form').getForm().findField("department").getValue();
								var telephone = this.up('form').getForm().findField("telephone").getValue();
								var email = this.up('form').getForm().findField("email").getValue();
								//var createtime = this.up('form').getForm().findField("createTime").getValue();
								//var updatetime = this.up('form').getForm().findField("updateTime").getValue();
								Ext.Ajax.request({
									//url : Demo.app.contextRoot+'/account',
									method : 'post',
									params : {
										name : name,
										department : department,
										telephone : telephone,
										email : email
										//createTime : createtime,
										//updateTime : updatetime
									},
									success : function(response) {
										var textObj = Ext.JSON.decode(response.responseText);
										if (textObj.success == 1) {
											Ext.Msg.alert("信息", "更改成功");
										} else {
											Ext.Msg.alert("错误", textObj.message);
										}
									}
								})
							}
						}
						}]
		})
		return this.accountForm;
	},

	onPaneActive : function() {
/*		var thisAccountPanel = this.accountForm;
		Ext.Ajax.request({
			//url : Demo.app.contextRoot+'/account',
			method : 'get',
			success : function(response) {
				var text = response.responseText;
				var obj = Ext.JSON.decode(response.responseText);
				var object = obj.data;
				if (obj.success != 1) {
					Ext.Msg.alert("错误", obj.message);
				} else {
					thisAccountPanel.items.getAt(0).setValue(object.name);
					thisAccountPanel.items.getAt(1).setValue(object.department);
					thisAccountPanel.items.getAt(2).setValue(object.telephone);
					thisAccountPanel.items.getAt(3).setValue(object.email);
					thisAccountPanel.items.getAt(4).setValue(object.createTime);
					thisAccountPanel.items.getAt(5).setValue(object.updateTime);
				}
			}
		});*/
		
	}
});
