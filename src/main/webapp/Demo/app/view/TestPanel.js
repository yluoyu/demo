Ext.define('Demo.view.TestPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.testpanel',

	requires : ['Ext.form.FormPanel',
				'Ext.grid.Panel',
				'Ext.form.field.ComboBox',
				'Ext.grid.column.Date'],

	animCollapse : true,
	layout : 'border',
    border : 0,
	initComponent : function() {
		Ext.apply(this, {
					items : [this.createFilterPanel(), this.createGrid()]
				});
		
		var thisPanel = this;
		this.callParent(arguments);
	},

	createFilterPanel : function() {
		var thisPanel = this;
		this.filterPanel = Ext.create('Ext.form.FormPanel', {
					region : 'north',
					layout : 'column',
					bordor : 0,
					//collapsible : true,
					defaults : {
						margin : '5 0 5 5',
						labelWidth : 60
					},
					defaultType : 'textfield',
					items : [{
								fieldLabel : '姓名',
								name : 'name',
								columnWidth : 0.3,
								allowBlank : true
							},{
								fieldLabel : '身份证号',
								name : 'identityId',
								columnWidth : 0.3,
								allowBlank : true
							}],
					buttonAlign : 'left',
					buttons : [{
								text : '重置',
								handler : function() {
									this.up('form').getForm().reset();
								}
							}, {
								text : '搜索',
								formBind : true,
								disabled : true,
								handler : function() {
									thisPanel.store.getProxy().extraParams.name = encodeURI(this.up('form').getForm().findField("name").getValue()); 
									var name = encodeURI(this.up('form').getForm().findField("name").getValue());
								}
							}]
				});
		return this.filterPanel;
	},
	createGrid : function() {
		var thisPanel = this;		
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
					}, {
						fieldLabel : '身份证号',
						name : 'department',
					}, {
						fieldLabel : '事故情况',
						name : 'telephone',
					}, {
						fieldLabel : '违法情况',
						name : 'email',
						width : 300,
					}, {
						fieldLabel : '业务办理情况',
						name : 'email',
						width : 300,
					}, {
						fieldLabel : '总分',
						name : 'email',
						width : 300,
					}]
		})
		return this.accountForm;
	},

	onPaneActive : function() {
		//this.store.load();
		
	}
});
