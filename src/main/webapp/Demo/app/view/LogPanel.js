Ext.define('Demo.view.LogPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.logpanel',

	requires : ['Ext.form.FormPanel',
				'Ext.grid.Panel',
				'Ext.form.field.ComboBox',
				'Ext.grid.column.Date'],

	animCollapse : true,
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
					items : [this.createFilterPanel(), this.createGrid()]
				});
		var thisPanel = this;
		
		Ext.apply(Ext.form.VTypes, {
			dateRange : function(val, field) {
				var date = field.parseDate(val);
				if (!date) {
					return;
				}
				if (field.startDateField
						&& (!this.dateRangeMax || (date.getTime() != this.dateRangeMax
								.getTime()))) {
					var start = Ext.getCmp(field.startDateField);
					start.setMaxValue(date);
					//start.validate();
					this.dateRangeMax = date;
				} else if (field.endDateField
						&& (!this.dateRangeMin || (date.getTime() != this.dateRangeMin
								.getTime()))) {
					var end = Ext.getCmp(field.endDateField);
					end.setMinValue(date);
					//end.validate();
					this.dateRangeMin = date;
				}
				return true;
			}
		});
		
		this.callParent(arguments);
	},

	createFilterPanel : function() {
		var thisPanel = this;
		var startTime = Ext.Date.add(new Date(),Ext.Date.MONTH,-2);
		this.filterPanel = Ext.create('Ext.form.FormPanel', {
					region : 'north',
					layout : 'column',
					border : 0,
					defaults : {
						margin : '5 0 5 5',
						labelWidth : 80,
						labelAlign : 'right'
					},
					defaultType : 'textfield',
					items : [{
								fieldLabel : '用户名称',
								name : 'name',
								columnWidth : 0.27,
								allowBlank : true
								
							},{
								id : 'startDate',
								fieldLabel : '起始日期',
								format : 'Y-m-d',
								xtype : 'datefield',
								name : 'startTime',
								value : startTime,
								columnWidth : 0.2,
								vtype : 'dateRange',//daterange类型为上代码定义的类型  
					            endDateField : 'endDate',//必须跟endDate的id名相同
								allowBlank : true
								
							},{
								id : 'endDate',
								fieldLabel : '结束日期',
								format : 'Y-m-d',
								xtype : 'datefield',
								name : 'endTime',
								value : new Date(),
								columnWidth : 0.2,
								vtype : 'dateRange',//daterange类型为上代码定义的类型  
					            startDateField : 'startDate', //必须跟startDate的id名相同
								allowBlank : true
								
							}],
					buttonAlign : 'left',
					buttons : [{
								text : '重置',
								handler : function() {
									this.up('form').getForm().reset();
								}
							}, {
								id : 'searchList',
								text : '搜索',
								formBind : true,
								handler : function(btn) {
									btn.setDisabled(true);
									var name = encodeURI(this.up('form').getForm().findField("name").getValue());
									var startTime = Ext.util.Format.date(this.up('form').getForm().findField("startTime").getValue(),'Y-m-d');
									var endTime = Ext.util.Format.date(this.up('form').getForm().findField("endTime").getValue(),'Y-m-d');
									var projectName = encodeURI(this.up('form').getForm().findField("projectName").getValue());
									var startDate = Ext.Date.add(this.up('form').getForm().findField("startTime").getValue(),Ext.Date.MONTH,3);
									var endDate = Ext.Date.add(this.up('form').getForm().findField("endTime").getValue(),Ext.Date.MONTH,0);
									if(startDate != null && endDate != null && startDate.getTime() < endDate.getTime()){
										Ext.Msg.alert("提示","日期相差超过3个月");
										btn.setDisabled(false);
									} else {
										thisPanel.store.load({
											params : {
												name : name,
												startTime : startTime,
												endTime : endTime
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
		this.reportList = Ext.create('Ext.data.Store', {
			storeId : 'report',
			//model : 'Inforstack.model.Report',
			pageSize : 20,
			border : false,
			bodyBorder : false,
			remoteSort : true,
			autoLoad: false,
			proxy : {
				type : 'ajax',
				method : 'post',
				url : Demo.app.contextRoot+'/user/report/listAll',
				reader : {
					type : 'json',
					root : 'data.data',
					totalProperty : 'data.recordTotal'
				},
				extraParams : {
					startTime : '',
					endTime : '',
				},
				simpleSortMode : true
			},
			listeners : {
				load : function(thisStore, records, successful){
					if(successful){
						
					}else{
						Ext.Msg.alert("提示","加载报表列表失败");
					}
				}
			}
		});
		
		var thisPanel = this;

		this.reportGrid = Ext.create('Ext.grid.Panel', {
			region : 'center',
			store : this.store ,
			loadMask : true,
			showHeaderCheckbox:true,
			//selModel:this.checkSM,
			viewConfig : {
				stripeRows : false
			},
			columns : [{
				text : '用户名称',
				dataIndex : 'id',
				width : 200,
			}, {
				text : '操作类型',
				dataIndex : 'name',
				
				flex : 2
			}, {
				text : '操作时间',
				dataIndex : 'Ascore',
				flex : 1
			}, {
				text : '操作结果',
				dataIndex : 'Bscore',
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : this.store ,
				displayInfo : true,
				displayMsg : '正显示{2}条记录中的第{0}-{1}条',
				emtpyMsg : '无数据'
					}),
			listeners : {
				scope : this
				//celldblclick : this.onSelectReport
			}
		});
		return this.reportGrid;
	},

	onPaneActive : function() {
/*		var th = this.filterPanel.getForm();
		this.filterPanel.getForm().reset();
		var name = encodeURI(th.findField("name").getValue());
		var startTime = Ext.util.Format.date(th.findField("startTime").getValue(),'Y-m-d');
		var endTime = Ext.util.Format.date(th.findField("endTime").getValue(),'Y-m-d');
		this.store.load({
			params : {
				name : name,
				startTime : startTime,
				endTime : endTime
			}
		});*/
		
	}
});
