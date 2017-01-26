var currentUserId = 1;

(function($) {

	var Stock = Backbone.Model.extend();

	var StockView = Backbone.View.extend({
		tagName : "div",
		className : "content",
		template : $("#stockTemplate").html(),

		render : function() {
			var tmpl = _.template(this.template);
			this.$el.html(tmpl(this.model.toJSON()));

			return this;
		},

		events : {
			"click .lookupStock" : "addStock",
			"click .delete" : "deleteStock"
		},

		addStock : function(e) {
			console.log("Inside Add Stock.");
			e.preventDefault();

			var formData = {};

			$("#content-right div").children("input").each(function(i, el) {
				if ($(el).val() !== "") {
					formData[el.id] = $(el).val();
					console.log("EL:" + $(el).val());
				}
			});

			stocks.push(formData);

			this.collection.add(new Stock(formData));
		},

		deleteStock : function() {
			// Delete model
			this.model.destroy();

			// Delete view
			this.remove();
		}
	});

	var StockCollection = Backbone.Collection.extend({
		model : Stock,
		url : 'getStocks'
	});

	var StockCollectionView = Backbone.View.extend({
		el : $("#stocks"),

		initialize : function() {
			this.collection = new StockCollection();
	        this.collection.fetch();
	        this.render();
	 
	        this.collection.on("add", this.renderStock, this);
	        this.collection.on("remove", this.removeStock, this);
	        this.collection.on("reset", this.render, this);
		},

		render : function() {
			var that = this;
			_.each(this.collection.models, function(item) {
				that.renderStock(item);
			}, this);
		},

		renderStock : function(item) {
			var stockView = new StockView({
				model : item
			});
			this.$el.append(stockView.render().el);
		}
	});

	var stocks = [ {
		companyName : "",
		purchasePrice : "0.0",
		currentPrice : "0.0",
		netProfit : "0.0"
	} ]

	var stockCollectionView = new StockCollectionView();
})(jQuery);

// This function looks up a stock by calling the Yahoo API, and then
// populates the form with the data.
// The next step is to call the web application and save the stock to
// the database.

function a() {
	var stockSymbol = $('#stockSymbol').val();
	var currentStockName = "";
	var currentStockCurrentPrice = "";
	var currentStockNetProfit = "";
	var currentStockPurchasePrice = "";

	$
			.get(
					{
						url : 'https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22'
								+ stockSymbol
								+ '%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback='
					})
			.then(
					function(data) {
						$('#companyName').val(data.query.results.quote.Name);
						$('#currentPrice')
								.val(
										"$"
												+ data.query.results.quote.LastTradePriceOnly);
						$('#purchasePrice').val(
								"$" + data.query.results.quote.Open);
						$('#netProfit').val(
								"$" + data.query.results.quote.EBITDA);

						currentStockName = data.query.results.quote.Name;
						currentStockCurrentPrice = data.query.results.quote.LastTradePriceOnly;
						currentStockPurchasePrice = data.query.results.quote.Open;
						currentStockNetProfit = data.query.results.quote.EBITDA;
						$.post("saveStock", {
							userId : "1",
							companyName : currentStockName,
							currentPrice : currentStockCurrentPrice,
							purchasePrice : currentStockPurchasePrice,
							netProfit : currentStockNetProfit
						}, function(data) {
							alert("Data Loaded: " + data);
						});
					});

}

function deletestock(stockname) {
	$.get('deleteStock?companyName='+stockname);
}
