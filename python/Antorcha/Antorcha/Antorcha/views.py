"""
Routes and views for the flask application.
"""

from datetime import datetime

from flask import *


db = MySQLdb.connect(host="localhost",    
                     user="apiRest",        
                     passwd="patata123",  
                     db="Antorcha")      

cur = db.cursor()


@app.route('/')
@app.route('/home')
def home():
    """Renders the home page, with a list of all polls."""
    return render_template(
        'index.html',
        title='Polls',
        year=datetime.now().year,
        polls=repository.get_polls(),
    )

@app.route('/cliente', methods = ['POST'])
def cliente():
    cur.execute("SELECT * FROM Cliente")
    return json.load()

@app.route('/about')
def about():
    """Renders the about page."""
    return render_template(
        'about.html',
        title='About',
        year=datetime.now().year,
        repository_name=repository.name,
    )

@app.route('/seed', methods=['POST'])
def seed():
    """Seeds the database with sample polls."""
    repository.add_sample_polls()
    return redirect('/')

@app.route('/results/<key>')
def results(key):
    """Renders the results page."""
    poll = repository.get_poll(key)
    poll.calculate_stats()
    return render_template(
        'results.html',
        title='Results',
        year=datetime.now().year,
        poll=poll,
    )

@app.route('/poll/<key>', methods=['GET', 'POST'])
def details(key):
    """Renders the poll details page."""
    error_message = ''
    if request.method == 'POST':
        try:
            choice_key = request.form['choice']
            repository.increment_vote(key, choice_key)
            return redirect('/results/{0}'.format(key))
        except KeyError:
            error_message = 'Please make a selection.'

    return render_template(
        'details.html',
        title='Poll',
        year=datetime.now().year,
        poll=repository.get_poll(key),
        error_message=error_message,
    )

@app.errorhandler(PollNotFound)
def page_not_found(error):
    """Renders error page."""
    return 'Poll does not exist.', 404



import MySQLdb

db = MySQLdb.connect(host="localhost",    
                     user="apiRest",        
                     passwd="patata123",  
                     db="Antorcha")      

# you must create a Cursor object. It will let
#  you execute all the queries you need
cur = db.cursor()

# Use all the SQL you like
cur.execute("SELECT * FROM Cliente")

# print all the first cell of all the rows
for row in cur.fetchall():
    print row[0]

db.close()