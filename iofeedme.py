import urllib2
# If you are using Python 3+, import urllib instead of urllib2

import json 


data =  {

        "Inputs": {

                "input1":
                {
                    "ColumnNames": ["CHSI_County_Name", "CHSI_State_Name", "Obesity_Category"],
                    "Values": [ [ "Fairfax", "Virginia", "" ], [ "Fairfax", "Virginia", "" ], ]
                },        },
            "GlobalParameters": {
}
    }

body = str.encode(json.dumps(data))

url = 'https://ussouthcentral.services.azureml.net/workspaces/a3c008d843864fe49aff19d6ea145acd/services/665f5432cf6449dd9c15500147e23915/execute?api-version=2.0&details=true'
api_key = 'PdxWQRJCWFJn7rb0ujg1tHdOVHaY6YwEhqryytl9F3Cb7pCiiWysicXsiDLHmHa3Md3bgZqd9rfgnMY4/GqjKg==' # Replace this with the API key for the web service
headers = {'Content-Type':'application/json', 'Authorization':('Bearer '+ api_key)}

req = urllib2.Request(url, body, headers) 

try:
    response = urllib2.urlopen(req)

    # If you are using Python 3+, replace urllib2 with urllib.request in the above code:
    # req = urllib.request.Request(url, body, headers) 
    # response = urllib.request.urlopen(req)

    result = response.read()
    print(result) 
except urllib2.HTTPError, error:
    print("The request failed with status code: " + str(error.code))

    # Print the headers - they include the requert ID and the timestamp, which are useful for debugging the failure
    print(error.info())

    print(json.loads(error.read()))                 