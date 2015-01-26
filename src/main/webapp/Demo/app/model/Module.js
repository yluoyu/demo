Ext.define('Demo.model.Module', {
    extend: 'Ext.data.Model',
    
    proxy: {
        type: 'memory'
    },
    
    fields: [
        {name: 'name', type: 'string'},
		{name: 'title', type: 'string'},
        {name: 'url',  type: 'string'},
		{name: 'iconCls', type: 'string'}
    ]
});