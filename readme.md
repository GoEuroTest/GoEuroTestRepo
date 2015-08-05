The application is Java command-line, that query the API Location endpoint with a user input and create a CSV file from it.
The CSV file have the form: _id, name, type, latitude, longitude

Run the application from the command-line by issue the following command, for example:

java -jar GoEuroTest.jar "STRING"

The query request looks like this:

http://api.goeuro.com/api/v2/position/suggest/en/STRING

Where STRING is the string that the user has entered as a parameter, when executes the application.
