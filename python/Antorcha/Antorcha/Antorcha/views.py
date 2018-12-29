"""
Routes and views for the flask application.
"""

from datetime import datetime
import pprint
from flask import request, jsonify
from flask import *
import csv
import json
import MySQLdb
from io import StringIO

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


@app.route('/productos', methods = ['GET'])  #proporciona los productos disponibles para el pedido
def pruductos():
    cur = db.cursor()
    cur.execute('''SELECT * FROM PRODUCTOS WHERE stock >= 1 ''')
    row_headers=[x[0] for x in cur.description] #this will extract row headers
    rv = cur.fetchall()
    json_data=[]
    for result in rv:
        json_data.append(dict(zip(row_headers,result)))
    return json.dumps(json_data)

@app.route('/nuevoProducto', methods = ['POST'])
def nuevoProducto():
    res = request.get_json()
    cur = db.cursor()
    print(res["Name"])
    strin = "INSERT INTO PRODUCTOS ( Name, Stock, Cantidad, Precio, Imagen) VALUES (%s, %s, %s, %s, %s)"
    values = (res["Name"],res["Stock"],res["Cantidad"],res["Precio"],'a')
    cur.execute(strin,values)
    db.commit()
    return json.dumps(res)

@app.route('/nuevoCliente', methods = ['POST'])
def nuevoCliente():
    res = request.get_json()
    cur = db.cursor()
    print(res["Name"])
    
    strin = "INSERT INTO Cliente (ID_cliente, User,  Pass, Domicilio, Correo, Telefono) VALUES (%s, %s, %s, %s, %s, %s)"
    values = (res["ID_cliente"],res["User"],res["Pass"],res["Domicilio"],res["Correo"],res["Telefono"])
    cur.execute(strin,values)
    db.commit()
    return json.dumps(res)

@app.route('/login', methods = ['POST'])
def login():
    ret = request.get_json()
    cur = db.cursor()
    strin = ("SELECT ID_cliente, User, Domicilio, Correo, Telefono FROM Cliente WHERE User = '"+ ret["User"]+"'AND Pass = '"+ret["Pass"]+"'")
    cur.execute(strin)
    row_headers=[x[0] for x in cur.description] #this will extract row headers
    rv = cur.fetchall()
    json_data=[]
    for result in rv:
        json_data.append(dict(zip(row_headers,result)))
    return json.dumps(json_data)

@app.route('/addcsv',methods =['POST'])
def añadircsv():
    ret = StringIO(request.data)
    jsonfile = open('file.json', 'w')
    fieldnames = ("Name","Precio","Origen")
    reader = csv.DictReader(ret, fieldnames)
    out = json.dumps( [ row for row in reader ] )
    strin = "INSERT INTO PRODUCTOS ( Name, Stock, Cantidad, Precio, Imagen) VALUES (%s, %s, %s, %s, %s)"
    values = (res["Name"],res["Stock"],res["Cantidad"],res["Precio"],'a')
    cur.execute(strin,values)
    return out

@app.route('/test', methods = ['POST'])
def Test():
    print(str(request.data))
    ret = StringIO(str(request.data))
    jsonfile = open('file.json', 'w')
    fieldnames = ("Name","Precio","Origen")
    reader = csv.DictReader(ret, fieldnames)
    rett = json.dumps( [ row for row in reader ] )
    strin = "INSERT INTO PRODUCTOS ( Name, Stock, Cantidad, Precio, Imagen) VALUES (%s, %s, %s, %s, %s)"
    res= json.loads(rett)
    for i in range(len(res)):
        values = (res[i]["Name"],10,10,res[i]["Precio"],'a')
        cur.execute(strin,values)
    cur.execute('''SELECT * FROM PRODUCTOS''')
    rv = cur.fetchall()
    json_data=[]
    for result in rv:
        json_data.append(dict(zip(row_headers,result)))
    return json.dumps(json_data) 

if __name__ == '__main__':
    app.run(host='0.0.0.0')




