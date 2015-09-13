var chart = AmCharts.makeChart( "chartdiv", {
  "type": "funnel",
  "theme": "default",
  "dataProvider": [ {
    "title": "Low Risk",
    "value": 100
  }, {
    "title": "Medium Risk",
    "value": 75
  }, {
    "title": "High Risk",
    "value": 50
  }, {
    "title": "Extreme Risk",
    "value": 25
  } ],
  "balloon": {
    "fixedPosition": true
  },
  "valueField": "value",
  "titleField": "Obesity Risk Level",
  "marginRight": 240,
  "marginLeft": 50,
  "startX": -500,
  "allLabels": [
    {
      "text": "Extreme Risk",
      "bold": true,
      "size": 40,
      "x": 475,
      "y": 500,
      "color": "#000000"
    },
    {
      "text": "High Risk",
      "size": 30,
<<<<<<< HEAD
      "url": "",
=======
>>>>>>> a76bd7de4411fcf0fe5b2713acecd8366678509e
      "bold": true,
      "x": 540,
      "y": 275,
      "color": "#000000"
    },
    {
      "text": "Medium Risk",
      "size": 25,
<<<<<<< HEAD
      "url": "",
=======
>>>>>>> a76bd7de4411fcf0fe5b2713acecd8366678509e
      "bold": true,
      "x": 530,
      "y": 140,
      "color": "#000000",
    },
    {
      "text": "Low Risk",
      "size": 18,
<<<<<<< HEAD
      "url": "",
=======
>>>>>>> a76bd7de4411fcf0fe5b2713acecd8366678509e
      "bold": true,
      "x": 580,
      "y": 45,
      "color": "#000000"
    }
  ],
  "rotate": true,
  "labelPosition": "right",
  "balloonText": "[[title]]: [[value]][[description]]",
  "export": {
    "enabled": true
  }
} );

$( "#chartdiv" ).click(function() {
  console.log( "Works!" );
});