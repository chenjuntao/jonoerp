define("custom/FilteringSelect", [
    "dojo/_base/declare", // declare,
    "dojo/dom-attr", // domAttr.get
    "dijit/form/FilteringSelect"
], function(declare, domAttr ,FilteringSelect){
 
    return declare("custom.FilteringSelect", [FilteringSelect], {
            
             displayValueAttr:null, 
         
            // summary:
            _announceOption: function(/*Node*/ node){
 
                if(!node){
                    return;
                }
                // pull the text value from the item attached to the DOM node
                var newValue;
                if(node == this.dropDown.nextButton ||
                    node == this.dropDown.previousButton){
                    newValue = node.innerHTML;
                    this.item = undefined;
                    this.value = '';
                }else{
                    var item = this.dropDown.items[node.getAttribute("item")];
                    var displayAttr = this.displayValueAttr!=null?this.displayValueAttr:this.searchAttr;
                     
                    newValue = (this.store._oldAPI ?    // remove getValue() for 2.0 (old dojo.data API)
                        this.store.getValue(item, displayAttr) : item[displayAttr]).toString();
 
                    this.set('item', item, false, newValue);
                }
                // get the text that the user manually entered (cut off autocompleted text)
                this.focusNode.value = this.focusNode.value.substring(0, this._lastInput.length);
                // set up ARIA activedescendant
                this.focusNode.setAttribute("aria-activedescendant", domAttr.get(node, "id"));
                // autocomplete the rest of the option to announce change
                this._autoCompleteText(newValue);
            }
    });
});
