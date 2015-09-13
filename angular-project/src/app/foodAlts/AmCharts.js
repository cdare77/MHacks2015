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
      "x": 580,
      "y": 500,
      "color": "#000000"
    },
    {
      "text": "High Risk",
      "bold": true,
      "x": 580,
      "y": 275,
      "color": "#000000"
    },
    {
      "text": "Medium Risk",
      "bold": true,
      "x": 580,
      "y": 140,
      "color": "#000000"
    },
    {
      "text": "Low Risk",
      "bold": true,
      "x": 590,
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