import datetime
import json
import urllib2
import urllib
from bson import json_util
from  collections  import OrderedDict
from pymongo import MongoClient

client =MongoClient("127.0.0.1")
username = "admin"
password = "1105"
url = "https://weighty-utility-768.appspot.com/_ah/api/iotService/v1/updatedataresponse"

client.admin.authenticate(username,password)

db = client.test
print db.collection_names()
pos = db.city_farm

def save():
	measure={'data':'1','time':str(datetime.datetime.now())}
	sensorData={'sensorId':0,'measures':[measure,measure,measure,measure,measure,measure,measure,measure,measure,measure,measure,measure,measure,measure,measure]}
	sensorData['measures'].append(measure);
	smartThingData={'smartThingId':0,'sensorData':[sensorData]}
	data=OrderedDict({'controllerId':'81','smartThingData':[smartThingData]})
	#db_id = pos.insert(data)
	return data
def get():
	return pos.find()

def send(data):
	d=json.dumps(data)
	print d
	req = urllib2.Request(url,d,{'Content-Type': 'application/json'})
	response = urllib2.urlopen(req)
	return response.read()

EC ={"units" : "uS","value" : 0}
DO={"units" : "mg/L","value" : 7.57}	
Temperature ={"units" : "C","value" : 25.9}			
SAL={"units" : "ppt","value" : 0}
TDS={"units" : "mg/L","value" : 0}
PH={"units" : "N/A","value" : 8.411}
SG={"units" : "N/A","value" : 1}
ORP={"units" : "N/A","value" : 157.6}

#req=OrderedDict({"date" : 1413664568.299933,"EC" : EC,"DO":DO,"Temperature":Temperature,"SAL":SAL,"TDS":TDS,"PH":PH,"SG":SG,"ORP":ORP})
#db_id = pos.insert(req)

#print send(save());
#m = get()

#s=send(m[1])

#for t in m:
#	print t


