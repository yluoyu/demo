Ext.define('Demo.view.ModulePanel', {
	extend:	'Ext.panel.Panel',
	alias: 'widget.modulepanel',

	animCollapse: true,
	layout:	'fit',

	initComponent: function(){
		Ext.apply(this,	{
			items: this.createView()
		});
		this.addEvents(
			'moduleselect'
		);
		this.on('collapse',function(){
			this.view.getSelectionModel().deselectAll();
		})

		this.callParent(arguments);
	},

	createView:	function(){
		this.view =	Ext.create('widget.dataview', {
			autoScroll:	true,
			store: Ext.create('Ext.data.Store',	{
				model: 'Demo.model.Module',
				data: this.modules
			}),
			selModel: {
				mode: 'SINGLE',
				listeners: {
					scope: this,
					selectionchange: this.onSelectionChange
				}
			},
			listeners: {
				scope: this,
				viewready: this.onViewReady
			},
			trackOver: true,
			cls: 'module-list',
			itemSelector: '.module-list-item',
			overItemCls: 'module-list-item-hover',
			tpl: '<tpl for="."><div	class="module-list-item {iconCls}">{title}</div></tpl>'
		});
		return this.view;
	},

	onViewReady: function(){
		//this.view.getSelectionModel().select(this.view.store.first());
	},

	createActions: function(){
	},

	onSelectionChange: function(){
		var	selected = this.getSelectedItem();
		if (selected) {
			this.loadModule(selected);
		}
	},

	loadModule: function(rec){
		if (rec) {
			this.fireEvent('moduleselect', this, rec, rec.get('name'), rec.get('title'), rec.get('url'));
		}
	},

	getSelectedItem: function(){
		return this.view.getSelectionModel().getSelection()[0] || false;
	},

	onModuleValid: function(win, title, url){
		var	view = this.view,
			store =	view.store,
			rec;

		rec	= store.add({
			url: url,
			title: title
		})[0];
		this.animateNode(view.getNode(rec),	0, 1);
	},

	animateNode: function(el, start, end, listeners){
		Ext.create('Ext.fx.Anim', {
			target:	Ext.get(el),
			duration: 500,
			from: {
				opacity: start
			},
			to:	{
				opacity: end
			},
			listeners: listeners
		 });
	},
	deselectAll:function(){
		this.view.getSelectionModel().deselectAll();
	},
	onDestroy: function(){
		this.callParent(arguments);
	}

});