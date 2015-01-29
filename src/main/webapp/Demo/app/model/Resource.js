Ext.define('Demo.model.Resource', {
    extend: 'Ext.data.Model',
    
    proxy: {
        type: 'memory'
    },
    
    fields: [
        {name: 'url', type: 'string'},
        {name: 'authority', type: 'string'},
		{name: 'id', type: 'string'}
    ]
});