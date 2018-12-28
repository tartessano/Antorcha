"""
Routes and views for the flask application.
"""

from datetime import datetime

from flask import request
from flask import *

import MySQLdb
app = Flask(__name__)
db = MySQLdb.connect(host="localhost",    
                     user="apiRest",        
                     passwd="patata123",  
                     db="Antorcha")      

cur = db.cursor()

@app.route('/cliente', methods = ['GET'])
def cliente():
    cur = db.cursor()
    cur.execute('''SELECT * FROM Cliente ''')
    row_headers=[x[0] for x in cur.description] #this will extract row headers
    rv = cur.fetchall()
    json_data=[]
    for result in rv:
        json_data.append(dict(zip(row_headers,result)))
    return json.dumps(json_data)

if __name__ == '__main__':
    app.run(host='0.0.0.0')




