<%@ page contentType="text/html; charset=UTF-8"%>
 <html>
 <head>
 <title>Create Car</title>
 </head>
 <body>
   <h1>Choose type of car for create:</h1>
   <form method="post" action="CarServlet">
   		<input type="radio" name="type" value="CAR">Passenger Car<br>
   		<input type="radio" name="type" value="TRUCK">Truck<br>
   		<input type="submit" value="Create a new car"><br>
   	</form>
 </body>
 </html>