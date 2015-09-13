"use strict";

(function() {
  'use strict';

  angular
    .module('angularProject')
    .controller('ResultsController', ['$routeParams', '$http', ResultsController]);

  /** @ngInject */
  function ResultsController($routeParams, $http) {
    var vm = this;

    vm.zip = $routeParams.zip;

    $http.get('http://api.zipasaur.us/zip/' + vm.zip).
      success(function(response) {
        vm.locData = [response["state_full"], response["county"]];
      }).
      then(function() {
        var locArr = [vm.locData[1], vm.locData[0], ""]
        vm.data = {
          "Inputs": {
            "input1": {
              "ColumnNames": ["CHSI_County_Name", "CHSI_State_Name", "Obesity_Category"],
              "Values": [ locArr, locArr]
            }
          },
          "GlobalParameters": {}
        }
        vm.url = 'https://ussouthcentral.services.azureml.net/workspaces/a3c008d843864fe49aff19d6ea145acd/services/665f5432cf6449dd9c15500147e23915/execute?api-version=2.0&details=true';
        vm.api_key = 'PdxWQRJCWFJn7rb0ujg1tHdOVHaY6YwEhqryytl9F3Cb7pCiiWysicXsiDLHmHa3Md3bgZqd9rfgnMY4/GqjKg==';
        vm.post_headers = {
          'Content-Type':'application/json',
          'Authorization':'Bearer '+ vm.api_key,
          'Access-Control-Allow-Origin': 'http://localhost:3000'
        };

        vm.req = {
          method: 'POST',
          url: vm.url,
          headers: vm.post_headers,
          data: vm.data
        }

        console.log(vm.post_headers);
        
        // $.ajax({
        //     method: "POST",
        //     url: vm.url,
        //     data: vm.data,
        //     headers: vm.post_headers,
        //   beforeSend: function(request) {
        //     request.setRequestHeader("Access-Control-Allow-Origin", "http://localhost:3000")
        //   }   
        // }).done(function (data) {
        //     console.log(data);
        // });
        //
        $http(vm.req).then(function(response) {
          vm.response = response.data.Results.output1.value.Values[1][7];
          switch(vm.response) {
            case "Low Risk":
              vm.resclass = "low";
              vm.res_caption = "You're Chris Dare quality fam";
              break;
            case "Medium Risk":
              vm.resclass = "med";
              vm.res_caption = "You could watch your eating a little";
              break;
            case "High Risk":
              vm.resclass = "high";
              vm.res_caption = "You should watch your eating and exercise";
              break;
            case "Extreme Risk":
              vm.resclass = "extreme";
              vm.res_caption = "You should see a doctor";
              break;
            default:
              vm.resclass = "low";
              vm.res_caption = "You're Chris Dare quality fam";
              break;
          }
        })
  
      });
  }
})();
