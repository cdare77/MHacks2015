/* global $ */

"use strict";

(function() {
  'use strict';

  angular
    .module('angularProject')
    .controller('SuggestionsController', ['$location', '$routeParams', SuggestionsController]);

  /** @ngInject */
  function SuggestionsController($location, $routeParams) {
    var vm = this;

    vm.risk = $routeParams.risk;

    console.log(vm.risk);

    function food(name, price) {
        this.name = name;
        this.price = price;
    }

    var tier4 = [new food("Vegetables", 0.0), new food("Whole Grain Bread", 0.0), 
        new food("Skim Milk", 0.0), new food("Fruit", 0.0), new food("Lean Cuisine", 0.0)];

    var tier3 = [new food("Fish", 0.0), new food("Low Fat Milk", 0.0), new food("Fruit", 0.0)];

    var tier2 = [new food("Poultry", 0.0), new food("Nuts", 0.0), new food("White Bread", 0.0)];

    var tier1 = [new food("Red Meat", 0.0), new food("Whole Milk", 0.0)];

    var ajaxFinished = false;

    function findPrice(food) {
        jQuery.ajax( {
            url: "http://api.walmartlabs.com/v1/search?query=" + food.name.replace(" ", "+") + "&format=json&apiKey=f9e8tfetpv3r64c6ewzjtkrt",
            type: 'GET',
            data: { content: 'testing test' },
            beforeSend : function(xhr) {
                xhr.setRequestHeader( "X-Originating-Ip", "35.0.122.37");
            },
            success: function( response ) {
                ajaxFinished = true;
                if (response.hasOwnProperty('items')) {
                    console.log(food.name);
                    console.log(response.items[0].name)
                    console.log(response.items[0].salePrice);
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
            findPrice(array[i]);
            //setTimeout(200);
        }
    }

    switch(vm.risk) {
        case "Low Risk":
            findPrices(tier1);
            vm.items = tier1;
            break;
        case "Medium Risk":
            findPrices(tier2);
            vm.items = tier2;
            break;
        case "High Risk":
            findPrices(tier3);
            vm.items = tier3;
            break;
        case "Extreme Risk":
            findPrices(tier4);
            vm.items = tier4;
            break;
        default:
            findPrices(tier2);
            vm.items = tier2;
    }

    console.log(vm.items);
   
  }
})();
