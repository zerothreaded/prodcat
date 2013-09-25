
var express = require('express')
  , routes = require('./routes')
  , user = require('./routes/user')
  , signin = require('./routes/signin')
  , memberhome = require('./routes/memberhome')
  , ddschema = require('./daos/ddschema')
  , test = require('./routes/test')
  , http = require('http')
  , path = require('path');

var app = express();

app.use(express.cookieParser());
app.use(express.session({secret: '1234567890QWERTY'}));

// all environments
app.set('port', process.env.PORT || 3000);

app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(express.cookieParser('your secret here'));
app.use(express.session());

app.use(app.router);

// Using the .html extension instead of having to name the views as *.ejs
app.engine('.html', require('ejs').__express);
// Set the folder where the pages are kept
app.set('views', __dirname + '/views');
// This avoids having to provide the extension to res.render()
app.set('view engine', 'html');

app.use(express.static(path.join(__dirname, '/')));

// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}

app.get('/', routes.index);
app.get('/test', test.getTest);
app.get('/signin', signin.displayform);
app.get('/signout', signin.handlesignout);
app.get('/memberhome', memberhome.get);
app.post('/registermember', signin.registermember);
app.post('/handlesignin', signin.handlesignin);

http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});
