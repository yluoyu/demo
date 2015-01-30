Ext.define('Demo.model.Document', {
    extend: 'Ext.data.Model',
    
    proxy: {
        type: 'memory'
    },
    
    fields: [
        {name: 'name', type: 'string'},
        {name: 'path', type: 'string'},
		{name: 'id', type: 'string'}
    ]
});