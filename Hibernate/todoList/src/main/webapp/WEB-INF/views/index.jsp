<!DOCTYPE html>
<html>
<head>
    <title>TODO List</title>
    <meta http-equiv="Content-Type" content="text/html/js; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <%--<script type="text/javascript" src="../../jsScripts/retriveJsonData.js"></script>--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

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
        <table id = "taskTable" class="table table-striped table-bordered" border="1"
               cellpadding="1"
               cellspacing="1">
            <thead>
            <tr>
                <td>ID</td>
                <td>Description</td>
                <td>Create Date</td>
                <td>Done</td>
            </tr>
            </thead>
            <tbody id="tbodyTodoList"></tbody>
        </table>
    </div>
</div>
<script><%@ include file="../../jsScripts/retriveJsonData.js"%></script>
</body>
</html>