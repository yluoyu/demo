Ext.define('Demo.view.DocumentPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.documentpanel',

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
								fieldLabel : '选择文件',
								xtype : 'filefield',
								name : 'file'
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
							},{
								text : '上传',
								handler : function() {
						            var form = this.up('form').getForm();
						            if(form.isValid()){
						                form.submit({
						                    url: Demo.app.contextRoot + '/document/upload',
						                    waitMsg: 'Uploading your photo...',
						                    success: function(fp, o) {
						                        Ext.Msg.alert('Success', 'has been uploaded.');
						                    }
						                });
						            }
								}
							}]
				});
		return this.filterPanel;
	},
	createGrid : function() {
		var thisPanel = this;
		this.store = Ext.create('Ext.data.Store', {
					pageSize : 10,
					model : 'Demo.model.Document',
					remoteSort : true,
					proxy : {
						type : 'ajax',
						url : Demo.app.contextRoot + '/document/list',
						reader : {
							type : 'json',
							root : 'data.data',
							totalProperty : 'data.recordTotal'
						},
						extraParams : {
							name : ""
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
		
		this.documentGrid = Ext.create('Ext.grid.Panel', {
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
								text : '文件名',
								dataIndex : 'name',
								width : 100,
								name : 'name'
							}, {
								text : '地址',
								dataIndex : 'path',
								flex : 1,
								name : 'path'
							}, {
								text : '点击下载',
								dataIndex : 'id',
								flex : 1,
								name : 'path',
								renderer : function(value){
									 return '<a href="'+Demo.app.contextRoot+'/document/download?id='+value+'"" >下载</a>'
								}
							}],
					bbar : Ext.create('Ext.PagingToolbar', {
								store : this.store,
								displayInfo : true,
								displayMsg : '正显示{2}条记录中的第{0}-{1}条',
								emtpyMsg : '无数据'
							}),
					listeners : {
						scope : this,
						celldblclick : function(){}
					}
				});
		return this.documentGrid;
	},

	onPaneActive : function() {
		
	}
});
