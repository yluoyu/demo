Ext.define('Demo.view.TreeGrid', {
    extend: 'Ext.tree.Panel',
    alias : 'widget.treegrid',
    requires: [
        'Ext.data.*',
        'Ext.grid.*',
        'Ext.tree.*',
        'Ext.ux.CheckColumn',
        'Demo.model.Task'
    ],    
    exampleTitle: 'TreeGrid',
    themes: { 
        classic: {
            width: 500,
            colWidth: 40
        },
        neptune: {
            width: 600,
            colWidth: 55
        }
    },
    //</example>
    
    header: false,
    height: 300,
    width : 600,
    useArrows: true,
    rootVisible: false,
    multiSelect: true,
    //singleExpand: true,
    
    initComponent: function() {
  
        
        Ext.apply(this, {
            store: new Ext.data.TreeStore({
                model: Demo.model.Task,
                proxy: {
                    type: 'ajax',
                    url: 'resources/treejson.json'
                },
    			listeners : {
    				load : function(thisStore, records, successful) {
    					if (successful) {
    						console.log("successful");
    							if (records.firstChild.data.status == '1') {
    							//	Ext.Msg.alert("警告！");
    							}
    					
    					}
    				}
    			},
                folderSort: true
            }),
            columns: [{
                xtype: 'treecolumn', //this is so we know which column will show the tree
                text: '分类',
                flex: 2,
                sortable: true,
                dataIndex: 'task'
            },{
				text : '数量情况',
				dataIndex : 'duration',
				flex : 1
			},{
                text: '日期',
                xtype : 'datecolumn',
				format : 'Y-m-d H:i:s',
                dataIndex: 'createTime',
                flex: 1,
                sortable: true
            },{
                text: '其他',
               // hidden:true,
                flex: 1,
                dataIndex: 'user'
            }]


        });
        this.callParent();
    }
});
