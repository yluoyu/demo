Ext.define('Demo.view.QueryPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.querypanel',

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
		this.store = Ext.create('Ext.data.Store', {
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
				load : function(thisStore, records, successful) {
					if (successful) {
						for (var idx = 0; idx < records.length; idx++) {
							var record = records[idx];
							if (thisPanel.selectedMolecule[record.data.checked] == true) {
								thisPanel.selectionModel.select(record, true);
							}
						}
					}
				}
			}
		});
		
		this.reportGrid = Ext.create('Demo.view.TreeGrid',{
			region : 'center',
           // store: this.store,
            viewConfig : {
            	loadMask : true
            },
            listeners : {

            }
		});
		
		return this.reportGrid;
	},

	onPaneActive : function() {
		var test =  Ext.create('Ext.data.TreeStore', { 
             model: Demo.model.Task,
             rootVisible : true,
             proxy: {
                 type: 'ajax',
                 url: 'resources/treejson2.json'
             }
		    });
		
	}
});
