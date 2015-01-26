Ext.define('Demo.model.Task', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'task',
        type: 'string'
    }, {
        name: 'user',
        type: 'string'
    }, {
        name: 'duration',
        type: 'float'
    }, {
        name: 'done',
        type: 'boolean'
    },{
        name: 'status',
        type: 'string'
    }]
}); 