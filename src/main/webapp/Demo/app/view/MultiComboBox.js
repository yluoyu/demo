Ext.define('Demo.view.MultiComboBox', {
    extend: 'Ext.form.ComboBox',
    alias: 'widget.multicombobox',
    xtype: 'multicombobox',
    initComponent: function(){
        this.listConfig = {
              itemTpl : Ext.create('Ext.XTemplate',
                    '<input type=checkbox>{value}'),
              onItemSelect: function(record) {    
                  var node = this.getNode(record);
                  if (node) {
                     Ext.fly(node).addCls(this.selectedItemCls);
                                                                           
                     var checkboxs = node.getElementsByTagName("input");
                     if(checkboxs!=null)
                     {
                         var checkbox = checkboxs[0];
                         checkbox.checked = true;
                     }
                  }
              },
              listeners:{
                  itemclick:function(view, record, item, index, e, eOpts ){
                      var isSelected = view.isSelected(item);
                      var checkboxs = item.getElementsByTagName("input");
                      if(checkboxs!=null)
                      {
                          var checkbox = checkboxs[0];
                          if(!isSelected)
                          {
                              checkbox.checked = true;
                          }else{
                              checkbox.checked = false;
                          }
                      }
                  }
              }       
        }       
        this.callParent();
    },
    /**
     * @private
     * If the autoSelect config is true, and the picker is open, highlights the first item.
     */
    doAutoSelect: function() {
        var me = this,
            picker = me.picker,
            lastSelected, itemNode;
        if (picker && me.autoSelect && me.store.getCount() > 0) {
            // Highlight the last selected item and scroll it into view
            lastSelected = picker.getSelectionModel().lastSelected;
            itemNode = picker.getNode(lastSelected || 1);
            if (itemNode) {
                picker.highlightItem(itemNode);
                Ext.fly(itemNode).addCls(this.selectedItemCls);
                
                var checkboxs = itemNode.getElementsByTagName("input");
                if(checkboxs!=null)
                {
                    var checkbox = checkboxs[0];
                    checkbox.checked = true;
                }
                picker.listEl.scrollChildIntoView(itemNode, false);
            }
        }
    },
    /**
     * @private
     * Enables the key nav for the BoundList when it is expanded.
     */
    onExpand: function() {
        var me = this,
            keyNav = me.listKeyNav,
            selectOnTab = me.selectOnTab,
            picker = me.getPicker();

        // Handle BoundList navigation from the input field. Insert a tab listener specially to enable selectOnTab.
        if (keyNav) {
            keyNav.enable();
        } else {
            keyNav = me.listKeyNav = new Ext.view.BoundListKeyNav(this.inputEl, {
                boundList: picker,
                forceKeyDown: true,
                tab: function(e) {
                    if (selectOnTab) {
                        this.selectHighlighted(e);
                        me.triggerBlur();
                    }
                    // Tab key event is allowed to propagate to field
                    return true;
                },
                enter: function(e){
                    var selModel = picker.getSelectionModel(),
                        count = selModel.getCount();
                        
                    this.selectHighlighted(e);
                    
                    // Handle the case where the highlighted item is already selected
                    // In this case, the change event won't fire, so just collapse
                    if (!me.multiSelect && count === selModel.getCount()) {
                        me.collapse();
                    }
                }
            });
        }

        // While list is expanded, stop tab monitoring from Ext.form.field.Trigger so it doesn't short-circuit selectOnTab
        if (selectOnTab) {
            me.ignoreMonitorTab = true;
        }

        Ext.defer(keyNav.enable, 1, keyNav); //wait a bit so it doesn't react to the down arrow opening the picker
        me.inputEl.focus();
        me.doAutoSelect();
    },
});