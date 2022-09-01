import json
import random
import time
import requests
import socket
import sys

name = socket.gethostbyname(socket.gethostname())
ip = 'http://localhost:8080'

for arg in sys.argv[1:]:
    if 'name' in arg and arg.index('name=') == 0:
        name = arg.split('=')[1]

    if 'ip' in arg and arg.index('ip=') == 0:
        ip = int(arg.split('=')[1])

while True:
    body = {
        'name': name,
        'gpu': random.randrange(0, 100),
        'cpu': random.randrange(0, 100),
        'ram': random.randrange(0, 100),
    }

    url = ip + '/health/send'
    bodyJson = json.dumps(body)
    print(url + ': ' + bodyJson + ': ' + str(requests.post(url, data=bodyJson)))

    time.sleep(5)
