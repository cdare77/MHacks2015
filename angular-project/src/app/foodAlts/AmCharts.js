var chart = AmCharts.makeChart( "chartdiv", {
  "type": "funnel",
  "theme": "default",
  "dataProvider": [ {
    "title": "Low Risk",
    "value": 10,
  }, {
    "title": "Medium Risk",
    "value": 20,
  }, {
    "title": "High Risk",
    "value": 30

  }, {
    "title": "Extreme Risk",
    "value": 40,
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
      "url": "suggestions.html",
      "size": 40,
      "x": 475,
      "y": 590,
      "color": "#000000"
    },
    {
      "text": "High Risk",
      "size": 30,
      "url": "suggestions.html",
      "bold": true,
      "x": 540,
      "y": 510,
      "color": "#000000"
    },
    {
      "text": "Medium Risk",
      "size": 22,
      "url": "suggestions.html",
      "bold": true,
      "x": 530,
      "y": 390,
      "color": "#000000",
    },
    {
      "text": "Low Risk",
      "size": 16,
      "url": "suggestions.html",
      "bold": true,
      "x": 578,
      "y": 200,
      "color": "#000000"
    },
    {
      "text": "Red meats and salty foods should be avoided",
      "size": 14,
      "x": 810,
      "y": 600,
      "color": "#000000"
    },
    {
      "text": "Exercise regularly; primarily cardiovascular",
      "size": 14,
      "x": 760,
      "y": 530,
      "color": "#000000"
    },
    {
      "text": "Fish and poultry contain low cholesterol and high protein",
      "size": 14,
      "x": 240,
      "y": 550,
      "color": "#000000"
    },
    {
      "text": "Stick to tree nuts with low saturated fat",
      "size": 14,
      "x": 290,
      "y": 430,
      "color": "#000000"
    },
    {
      "text": "Pick low sugar juices not made from concentrate",
      "size": 14,
      "x": 440,
      "y": 330,
      "color": "#000000"
    },
    {
      "text": "Avoid empty calories such as lipids and beer",
      "size": 14,
      "x": 630,
      "y": 430,
      "color": "#000000"
    },
    {
      "text": "Given the option, choose no salt",
      "size": 14,
      "x": 500,
      "y": 250,
      "color": "#000000"
    },
    {
      "text": "Never underestimate",
      "size": 14,
      "x": 530,
      "y": 150,
      "color": "#000000"
    },
    {
      "text": "the value of stretching",
      "size": 14,
      "x": 550,
      "y": 170,
      "color": "#000000"
    },
    {
      "text": "Practice aerobics on a daily basis",
      "size": 14,
      "x": 140,
      "y": 600,
      "color": "#000000"
    }
  ],
  "titles": [{
    "text": "Obesity Risk Factor",
    "size": 25,
    "color": "#FFCC00"
  }],
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