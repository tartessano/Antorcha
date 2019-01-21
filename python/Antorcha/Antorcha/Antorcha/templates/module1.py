from datetime import datetime
import pprint
from flask import request, jsonify
from flask import *
import csv
import json
import MySQLdb
import string

from io import StringIO

app = Flask(__name__)

db = MySQLdb.connect(host="localhost",    
                     user="root",        
                     passwd="patata123",  
                     db="Antorcha")      


cur = db.cursor()

@app.route('/test', methods = ['POST'])
def Test():
    stra = request.data.decode()
    print(stra)
    stre = stra.splitlines()
    print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
    print(stre[1])
    stri =[stre[i].split('''\t''') for i in range(len(stre))]
    strin = "INSERT INTO Producto (Name, Stock, Cantidad, Precio, Categoria) VALUES (%s, %s, %s, %s, %s)"
    for i in range(len(stri)):
        values = (str(stri[i][0]),10,10,str(stri[i][1]), "verdura")
        cur.execute(strin,values)
    db.commit()
    cur.execute('''SELECT * FROM Producto''')
    rv = cur.fetchall()
    json_data=[]
    row_headers=[x[0] for x in cur.description] #this will extract row headers
    for result in rv:
        json_data.append(dict(zip(row_headers,result)))
    return json.dumps(json_data) 

if __name__ == '__main__':
    app.run(host='0.0.0.0')



