from sense_hat import SenseHat
from subprocess import check_output
from re import findall
import time
import json
import requests

sense = SenseHat()
sense.clear()

def get_temp():
    temp = check_output(["vcgencmd","measure_temp"]).decode("UTF-8")
    return(findall("\d+\.\d+",temp)[0])
while True: 
        humidite = sense.get_humidity()
        humidite = format(humidite, '.2f')
        print(humidite)
        time.sleep(1)

        headers = {'Content-Type': 'application/json', 'authentification' : 'paul'}

        epoch = int(time.time())

        jsonFile = json.loads('{\"humidite\":[{\"valeur\":'+str(humidite)+',\"date\":\"'+str(epoch)+'\"}]}')
        print(jsonFile)


        requests.post('http://54.39.145.59:8082/humidite', headers=headers, json=jsonFile)
        sense.show_message(humidite)
       # sense.clear()



