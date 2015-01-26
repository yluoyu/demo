Ext.define('Demo.view.HistoryPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.historypanel',

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
/*		this.store = Ext.create('Ext.data.Store', {
			pageSize : 20,
			//model : '',
			remoteSort : true,
			proxy : {
				type : 'ajax',
				//url :Demo.app.contextRoot+'/#',
				reader : {
					type : 'json',
					root : 'data.data',
					totalProperty : 'data.recordTotal'
				},
				extraParams : {
					name : ''
				},
				simpleSortMode : true
			},
			listeners : {
				
			}
		});*/
		
		this.store = Ext.create('Ext.data.Store', {
			fields: [{name: 'name', type: 'string'},
			         {name: 'type',  type: 'string'},
			         {name: 'identityId',  type: 'string'},
			         {name: 'score',  type: 'string'}],
		     data : [
		         {name: 'test1',    type: '身份证',identityId: '12345678',score:'100'},
		         {name: 'test2',    type: '身份证',identityId: '12345678',score:'100'},
		         {name: 'test3',    type: '身份证',identityId: '12345678',score:'100'},
		         {name: 'test4',    type: '身份证',identityId: '12345678',score:'100'}
		     ]
		 });
		
		this.reportGrid = Ext.create('Ext.grid.Panel', {
			region : 'center',
			store : this.store ,
			loadMask : true,
			showHeaderCheckbox:true,
			selModel:this.checkSM,
			viewConfig : {
				stripeRows : false
			},
			columns : [{
				text : '查询姓名',
				dataIndex : 'name',
				width : 200
			}, {
				text : '证件号',
				dataIndex : 'identityId',
				flex : 2
			},{
				text : '证件类型',
				dataIndex : 'type',
				flex : 2
			}, {
				text : '操作时间',
				dataIndex : 'queryTime',
				xtype : 'datecolumn',
				format : 'Y-m-d H:i:s',
				flex : 1
			}, {
				text : '查询结果',
				dataIndex : 'score',
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : this.store ,
				displayInfo : true,
				displayMsg : '正显示{2}条记录中的第{0}-{1}条',
				emtpyMsg : '无数据'
					}),
			listeners : {
				scope : this,
				celldblclick : this.onSelect
			}
		});
		
		return this.reportGrid;
	},

	onSelect : function(){
		var thisPanel = this;
		var panel = Ext.create(
				'Demo.view.QueryPanel', {
					closable:true,
					title : '我的查询-新增'
				});
		thisPanel.up('viewport').addPanel(panel);
		thisPanel.up('tabpanel').setActiveTab(panel);
	},
	
	onPaneActive : function() {
		//this.store.load();
		
	}
});
