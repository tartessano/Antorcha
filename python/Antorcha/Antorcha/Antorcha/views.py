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
                     user="root",        
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
    cur.execute('''SELECT * FROM Producto WHERE stock >= 1 ''')
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
    strin = "INSERT INTO Producto ( Name, Stock, Cantidad, Precio, Imagen) VALUES (%s, %s, %s, %s, %s)"
    values = (res["Name"],res["Stock"],res["Cantidad"],res["Precio"],'a')
    cur.execute(strin,values)
    db.commit()
    return json.dumps(res)

@app.route('/nuevoCliente', methods = ['POST'])
def nuevoCliente():
    res = request.get_json()
    cur = db.cursor()    
    strin = "INSERT INTO Cliente (User, Domicilio, Correo, Telefono, Admin) VALUES (%s, %s, %s, %s, %s)"
    values = (res["User"]," ",res["Correo"],res["Telefono"], 0)
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
    print(json.dumps(json_data))
    return json.dumps(json_data)

@app.route('/addtsv',methods =['POST'])
def añadirtsv():
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

@app.route('')
@app.route('/test', methods = ['POST'])
def Test():
    
    return "aaaaaa"

if __name__ == '__main__':
    app.run(host='0.0.0.0')




