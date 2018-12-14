# CourseListExample

To do: 
•	need to implement a nice error dialogs for error handling and showing
•	add shared elements animations to get more materialized look
•	optimize layouts (I haven’t removed values, they need to define in the dimen file, flattened view hierarchy)
•	may be remove databinding views and replace with Anko layouts (light weight and can be used efficiently with kotlin – I’m also experimenting them)
•	More material components could be added in order to make the app look materialistic 
•	I added Grid layout to make it more attractive, you could change the span to one, if it’s required
Then we have a very long list.  
•	More test cases could be added, I have added few testing classes, and with provided architecture, it could be very easy to add more cases. 


Concerns: 
1.	At the moment,  as indicated in the document, app does show the latest content by requesting each course details(better if we can have a separate Rest end point to get specific details rather getting all the results), I used the same api to make the details request.
2.	I have added a logic (but commented out), where we can check the fetched data is very latest or expired by setting a given time period (probably 5 min or 10min), so the user won’t be needing to request unless this time has passed, I suggest this, because we can have a smoother transition between list and details, currently, I had shown a loading icon. 
3.	Even, we can load content from the background, it won’t be a good measure, while user reading the content, it might be very confusing to see some updated data after sometime, that’s why I have shown loading icon, while fetching the data.


