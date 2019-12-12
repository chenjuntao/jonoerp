//在JsonRest的源码上修改，因为JsonRest所有对数据的操作都会向服务器发送请求
//主要是两个修改，一个是getData从本地获取数据，另外一个是query用POST的方式请求数据

define(["dojo/_base/xhr", "dojo/_base/lang", "dojo/json", "dojo/_base/declare", "dojo/store/util/QueryResults" /*=====, "./api/Store" =====*/
], function(xhr, lang, JSON, declare, QueryResults /*=====, Store =====*/){

// No base class, but for purposes of documentation, the base class is dojo/store/api/Store
var base = null;
/*===== base = Store; =====*/

/*=====
var __HeaderOptions = {
		// headers: Object?
		//		Additional headers to send along with the request.
	},
	__PutDirectives = declare(Store.PutDirectives, __HeaderOptions),
	__QueryOptions = declare(Store.QueryOptions, __HeaderOptions);
=====*/

return declare("custom.store.Server", base, {
	// summary:
	//		This is a basic store for RESTful communicating with a server through JSON
	//		formatted data. It implements dojo/store/api/Store.

	constructor: function(options){
		// summary:
		//		This is a basic store for RESTful communicating with a server through JSON
		//		formatted data.
		// options: dojo/store/JsonRest
		//		This provides any configuration information that will be mixed into the store
		this.headers = {};
		declare.safeMixin(this, options);
	},
	
	set: function(options){
		// summary:
		//		Change option like target, idProperty dynamically.
		// options: dojo/store/JsonRest
		//		This provides any configuration information that will be mixed into the store
		declare.safeMixin(this, options);
	},

	// headers: Object
	//		Additional headers to pass in all requests to the server. These can be overridden
	//		by passing additional headers to calls to the store.
	headers: {},

	// target: String
	//		The target base URL to use for all requests to the server. This string will be
	//		prepended to the id to generate the URL (relative or absolute) for requests
	//		sent to the server
	target: "",
	
	// data: Array
	//		The array of all the objects in the memory store
	data: null,

	// idProperty: String
	//		Indicates the property to use as the identity property. The values of this
	//		property should be unique.
	idProperty: "id",

	// index: Object
	//		An index of data indices into the data array by id
	index: null,

	// rangeParam: String
	//		Use a query parameter for the requested range. If this is omitted, than the
	//		Range header will be used. Independent of this, the X-Range header is always set.

	// sortParam: String
	//		The query parameter to used for holding sort information. If this is omitted, than
	//		the sort information is included in a functional query token to avoid colliding
	//		with the set of name/value pairs.

	// ascendingPrefix: String
	//		The prefix to apply to sort attribute names that are ascending
	ascendingPrefix: "+",

	// descendingPrefix: String
	//		The prefix to apply to sort attribute names that are ascending
	descendingPrefix: "-",
	 
	_getTarget: function(id){
		// summary:
		//		If the target has no trailing '/', then append it.
		// id: Number
		//		The identity of the requested target
		var target = this.target;
		if(typeof id != "undefined"){
			if(target.charAt(target.length-1) == '/'){
				target += id;
			}else{
				target += '/' + id;
			}
		}
		return target;
	},
					
	get: function(id){
		// summary:
		//		Retrieves an object by its identity
		// id: Number
		//		The identity to use to lookup the object
		// returns: Object
		//		The object in the store that matches the given id.
		return this.data[this.index[id]];
	},
	
	//从本地获取数据
	getData: function(){
		// summary:
		//		Retrieves all the store data array
		// returns: Array
		return this.data;
	},

	// accepts: String
	//		Defines the Accept header to use on HTTP requests
	accepts: "application/javascript, application/json",

	getIdentity: function(object){
		// summary:
		//		Returns an object's identity
		// object: Object
		//		The object to get the identity from
		// returns: Number
		return object[this.idProperty];
	},

	put: function(object, options){
		// summary:
		//		Stores an object. This will trigger a PUT request to the server
		//		if the object has an id, otherwise it will trigger a POST request.
		// object: Object
		//		The object to store.
		// options: __PutDirectives?
		//		Additional metadata for storing the data.  Includes an "id"
		//		property if a specific id is to be used.
		// returns: dojo/_base/Deferred
		options = options || {};
		var id = ("id" in options) ? options.id : this.getIdentity(object);
		var hasId = typeof id != "undefined";
		return xhr(hasId && !options.incremental ? "PUT" : "POST", {
				url: this._getTarget(id),
				postData: JSON.stringify(object),
				handleAs: "json",
				headers: lang.mixin({
					"Content-Type": "application/json",
					Accept: this.accepts,
					"If-Match": options.overwrite === true ? "*" : null,
					"If-None-Match": options.overwrite === false ? "*" : null
				}, this.headers, options.headers)
			});
	},

	add: function(object, options){
		// summary:
		//		Adds an object. This will trigger a PUT request to the server
		//		if the object has an id, otherwise it will trigger a POST request.
		// object: Object
		//		The object to store.
		// options: __PutDirectives?
		//		Additional metadata for storing the data.  Includes an "id"
		//		property if a specific id is to be used.
		options = options || {};
		options.overwrite = false;
		return this.put(object, options);
	},

	remove: function(id, options){
		// summary:
		//		Deletes an object by its identity. This will trigger a DELETE request to the server.
		// id: Number
		//		The identity to use to delete the object
		// options: __HeaderOptions?
		//		HTTP headers.
		options = options || {};
		return xhr("DELETE", {
			url: this._getTarget(id),
			headers: lang.mixin({}, this.headers, options.headers)
		});
	},

	query: function(query, options){
		// summary:
		//		Queries the store for objects. This will trigger a GET request to the server, with the
		//		query added as a query string.
		// query: Object
		//		The query to use for retrieving objects from the store.
		// options: __QueryOptions?
		//		The optional arguments to apply to the resultset.
		// returns: dojo/store/api/Store.QueryResults
		//		The results of the query, extended with iterative methods.
		
		// 数据排列改变时会自动发送请求，将 sort 对象字符串化，以便服务器解析
		options = options || {};
		if (options.sort != undefined) {
			options.sort = JSON.stringify(options.sort);
		}
		
		var results = xhr("POST", {
//			url: this.target + (query || ""),
			url: this.target,
			handleAs: "json",
			postData: lang.mixin(query, options)
		});
		var setData = this.setData;
		var instance = this;
		results.total = results.then(function(data){
			setData(instance, data || []);
			var total = results.ioArgs.xhr.getResponseHeader("Content-Total");
			return total;
		});
		return QueryResults(results);
	},
	setData: function(instance, data){
		// summary:
		//		Sets the given data as the source for this store, and indexes it
		// data: Object[]
		//		An array of objects to use as the source of data.
		if(data.items){
			// just for convenience with the data format IFRS expects
			instance.idProperty = data.identifier || instance.idProperty;
			data = instance.data = data.items;
		} else {
			instance.data = data;
		}
		instance.index = {};
		for(var i = 0, l = data.length; i < l; i++){
			instance.index[data[i][instance.idProperty]] = i;
		}
	}
});

});