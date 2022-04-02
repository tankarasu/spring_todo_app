# Spring Todo App

## purpose of the repo

this is a simple todo app api handled with Java Spring Boot. This repo 
handles for now only the back-end. 

## Running Test

## API endpoints

### GET
Fetch all task.
``` 
http://localhost:8000/tasks  

Method : GET  
ResponseBody : List-Task
```

Fetch a specific task.
``` 
http://localhost:8000/tasks/id  

Method : GET  
ResponseBody : Task
```

### POST

Create a new task.
``` 
http://localhost:8000/tasks 

Method : POST
RequestBody : Task  
ResponseBody : Task
```

### PUT

Modify an existing task.
``` 
http://localhost:8000/tasks 

Method : PUT
RequestBody : Task  
ResponseBody : Task
```

Modify an existing task's finished state.
``` 
http://localhost:8000/tasks/finished

Method : PUT
RequestParam : id  
```

### DELETE

Delete an existing task.
``` 
http://localhost:8000/tasks 

Method : PUT
RequestParam : id  
```

---

## Further Help

Developer can find more help by reading the test case in UT (unit testing) 
and FT (functional testing) folders.

### TaskService

Globally, a developer will never use directly the Service. This part is only 
for information purpose. 

### TaskService.setTasks( )

This method is used for Testing purpose.

### TaskService.fetchAllTasks( )

This method is used to fetch all Tasks inside a List of Tasks.

### TaskService.fetchSpecificTask( )

This method is used to fetch a specific Task.

### TaskService.createOneTask( )

This method is used when we want to create a new Task.

### TaskService.updateSpecificTask( )

This method is used when we want to update a specific Task.

### TaskService.deleteSpecificTask( )

This method is used when we want to delete a specific Task. 