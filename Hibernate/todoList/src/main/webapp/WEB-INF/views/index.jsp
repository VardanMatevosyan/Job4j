<!DOCTYPE html>
<html>
<head>
    <title>TODO List</title>
    <meta http-equiv="Content-Type" content="text/html/js; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<nav class="nav navbar-default">
    <div class="container">
        <div class="navbar-text">
            <h3>TODO List</h3>
        </div>
    </div>
</nav>
<div class="container">
    <div>
        <div id="add_item_form_div_wrapper">
            <form accept-charset="UTF-8" class="add_item_form">
                <label for="add_desc">Description</label>
                <textarea id="add_desc" class="form-control" rows="3" name="description"
                          placeholder="Description" required></textarea>
            </form>
            <button id="add_task_button" class="btn btn-success">Add task</button>
        </div>
    </div>
    <hr>
    <div>
        <div id="check">
            <label class="checkbox-inline">
                <input id="show-done" type="checkbox" >Show done</label>
        </div>
        <table id = "taskTable" class="table table-bordered" border="1"
               cellpadding="1"
               cellspacing="1">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Description</th>
                <th scope="col">Create Date</th>
                <th scope="col">Done</th>
            </tr>
            </thead>
            <tbody id="tbodyTodoList"></tbody>
        </table>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script><%@ include file="../../jsScripts/retriveJsonData.js"%></script>
</body>
</html>