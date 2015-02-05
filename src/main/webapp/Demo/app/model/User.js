Ext.define('Demo.model.User', {
    extend: 'Ext.data.Model',
    
    proxy: {
        type: 'memory'
    },
    
    fields: [
        {name: 'username', type: 'string'},
        {name: 'email', type: 'string'},
        {name: 'type', type: 'string'},
        {name: 'status', type: 'string'},
        {name: 'email', type: 'string'},
        {name: 'telephone', type: 'string'},
        {name: 'createTime', type: 'Date',format:'time'},
        {name: 'updateTime', type: 'Date',format:'time'},
		{name: 'id', type: 'string'}
    ]
});