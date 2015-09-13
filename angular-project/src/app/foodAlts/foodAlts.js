function food(name, price) {
    this.name = name;
    this.price = price;
}

var tier4 = [new food("Vegetables", 0.0), new food("Whole Grain Bread", 0.0), 
    new food("Skim Milk", 0.0), new food("Fruit", 0.0), new food("Lean Cuisine", 0.0)];

var tier3 = [new food("Fish", 0.0), new food("Low Fat Milk", 0.0), new food("Fruit", 0.0)];

var tier2 = [new food("Poultry", 0.0), new food("Nuts", 0.0), new food("White Bread", 0.0)];

var tier1 = [new food("Red Meat", 0.0), new food("Whole Milk", 0.0)];

function findPrice(food) {
    jQuery.ajax( {
        url: "http://api.walmartlabs.com/v1/search?query=" + food.name.replace(" ", "+") + "&format=json&apiKey=f9e8tfetpv3r64c6ewzjtkrt",
        type: 'GET',
        data: { content: 'testing test' },
        beforeSend : function(xhr) {
            xhr.setRequestHeader( "X-Originating-Ip", "35.0.122.37");
        },
        success: function( response ) {
            if (response.hasOwnProperty('items')) {
                food.price = response.items[0].salePrice;
            }
            else {
                food.price = -1;
            }
        }
    });
}

function findPrices(array){
    for (var i = 0; i < array.length; i ++) {
        findPrice(array[0]);
        setTimeout(200);
    }
}

findPrices(tier4);
findPrices(tier3);
findPrices(tier2);
findPrices(tier1);

tier3 = tier3.concat(tier4);
tier2 = tier2.concat(tier3);
tier1 = tier1.concat(tier2);

console.log(tier1.length);

$( "div.demo-container" ).html(function(){
    return "hi";

});
//tier 1
//red meat
//whole milk

//tier 2
//poultry
//nuts
//white bread

//tier 3
//Fish
//Low fat milk
//Juice
//Fruits

//Tier 4
//Vegetables
//Whole grain bread
//Fat-free milk
//Bottled wadwater
//Fruits
//Lean cuisine
