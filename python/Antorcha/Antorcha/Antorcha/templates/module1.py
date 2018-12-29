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
    stra = request.data
    print(stra)
    stre = stra.splitlines()
    print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
    print(stre[1])
    stri =[str(stre[i].split('''\t''')) for i in range(len(stre))]    
    strin = "INSERT INTO Producto (Name, Stock, Cantidad, Precio, Categoria) VALUES (%s, %s, %s, %s, %s)"
    for i in range(len(stri)):
        values = (str(stri[i][1]),10,10,str(stri[i][2]), "verdura")
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



#cntx='''ACELGAS VERDES\t1,2\tSEVILLA\r\nACELGAS ROJAS\t1,4\tSEVILLA\r\nAJO MORADO\t4,5\tCORDOBA\r\nBATATAS\t1,75\tSEVILLA\r\nBONIATO VIOLETA\t1,9\tMALAGA\r\nBROCOLI\t1,95\tSEVILLA\r\nCALABAZA CACAHUTE\t1\tSEVILLA\r\nCAQUI PERSIMON\t2,6\tSEVILLA\r\nCEBOLLA ROJA\t1,4\tSEVILLA\r\nCEBOLLA SECA BLANCA\t1,2\tMALAGA\r\nCURCUMA \t11,9\tIMPORTACION \r\nGOGOLLOS VIVOS DE TUDELA\t6\tSEVILLA\r\nHINOJOS \t1,6\tSEVILLA\r\nJENGIBRE \t6,5\tIMPORTACION \r\nKALE CRESPADA\t1,5\tSEVILLA\r\nKALE DINOSAURIO\t1,5\tSEVILLA\r\nKIWI\t2,7\tGALICIA\r\nLECHUGAS VIVAS BATAVIA \t5,5\tSEVILLA\r\nLECHUGAS VIVAS CARMEN \t5,5\tSEVILLA\r\nLECHUGAS VIVAS LOLLO ROSA \t5,5\tSEVILLA\r\nLECHUGAS VIVAS ROBLE VERDE \t5,5\tSEVILLA\r\nLIMON OFERTA\t1,2\tSEVILLA\r\nMANDARINAS\t1,6\tSEVILLA\r\nMANOJO DE RUCULA \t1,3\tSEVILLA\r\nMANZANA FUJI \t1,95\tGRANADA\r\nMANZANA GOLDEN \t2,2\tZARAGOZA\r\nMELON NEGRO DE INVIERNO\t1,3\tSEVILLA\r\nNARANJAS\t0,75\tSEVILLA\r\nNARANJAS\t3,5\tSEVILLA\r\nPATATAS BLANCAS\t1,2\tSEVILLA\r\nPERA CONFERENCIA\t2,3\tZARAGOZA\r\nTOMATES CHERRY PERA\t0,95\tMALAGA\r\nTOMATES ENSALADA\t1,75\tSEVILLA\r\nZANAHORIA\t1,3\tCADIZ'''
