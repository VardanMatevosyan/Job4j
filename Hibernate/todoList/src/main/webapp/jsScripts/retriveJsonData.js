$(document).ready( function () {
    $.ajax({
        scriptCharset: 'UTF-8',
        method: "GET",
        url: "./getTodoList",
        mimeType: 'application/json',
        dataType: "text json",
        contentType: 'application/json',
        complete : function (data) {
            var array = data.responseJSON;
            var tableBody = $('#tbodyTodoList');
            for (var i = 0; i < array.length; i++) {
                var id = array[i].id;
                var done = array[i].done;
                var tr = "<tr  class='bg-danger'> ";
                var input = "<input type='button' "+ " class='btn btn-block  btn-danger doneButton' " + " value='" + done + "'/>";
                if (array[i].done === true) {
                    tr = "<tr class='bg-success'>";
                    input = "<input type='button' "+ " class='btn btn-block  btn-success doneButton' " + " value='" + done + "'/>";
                }
                var trWithTd = tr
                    + "<th class='row'>" + id + "</th> "
                    + "<td>" + array[i].description + "</td> "
                    + "<td>" + array[i].createDate + "</td> "
                    + "<td>" + input + "</td>";
                tableBody.append(trWithTd);
            }
            getLastAddedTask();
            change();
        },

        error: function (message) {
            console.log(message);
        }
    });

	function isValid(description) {
		var valid = description !== "";
		if (valid) {
			$("label [for='" + description.attr('id') + "']").css("color", '');
		} else {
			$("label [for='"+ description.attr('id') +"']").css("color", 'red');
		}
		return valid;
	}

     function getLastAddedTask() {
         var description = $("#add_desc");
         var firstRow = $('tbody#tbodyTodoList > tr:first-child');
         $("#add_task_button").on("click", function() {
             if (Boolean(isValid(description))) {
                 $.ajax({
                     scriptCharset: 'UTF-8',
                     method: "POST",
                     url: "./addTask",
                     mimeType: 'application/json',
                     dataType: "text json",
                     data: JSON.stringify({description:description.val()}),
                     error: function (message) {
                         console.log(message);
                     },
                     complete: function(taskData) {

                         var task = taskData.responseJSON;
                         var tableBody = $("#tbodyTodoList");
                         var input = "<input type='button' "+ " class='btn btn-block btn-danger doneButton' " + " value='" + task.done + "'/>";
                             var trWithTd = "<tr class='bg-danger'> "
                                 + "<th class='row'>" + task.id + "</th> "
                                 + "<td>" + task.description + "</td> "
                                 + "<td>" + task.createDate + "</td> "
                                 + "<td>" + input + "</td>";
                         $('tbody#tbodyTodoList > tr:first-child').before(trWithTd);
                         addEventListenerToNewAddedTask();
                     }
                 });
             }
             description.val('');
         });
    }

    function addEventListenerToNewAddedTask() {
	    console.log("ADD listener ");
        $("tbody#tbodyTodoList > tr:first-child").find('td').eq(2).find('input').on("click", change());
    }


	$("#show-done").on("click", function() {
		filteringTasks();
	});

    function filteringTasks() {
		var checked = $('#show-done').is(':checked');
		var tbodyTodoList = $("tbody#tbodyTodoList");
		var rows = tbodyTodoList.find('tr');
        var filterRows = rows.filter(function () {
            return $(this).find('td').eq(2).find('input').val() === 'false';
        });

        if (checked) {
            filterRows.hide();
        } else {
            filterRows.show();
        }
	}

    function change() {
        $("input.doneButton").on("click", function () {
            var tr = $(this).parent().parent();
            var value = false;
            var buttonValue = $(this).val();
            var next = $(this).parent().parent().find('th.row');
            var taskIdValue = next.html();

            if (buttonValue === 'false') {
                value = true;
                $(this).attr({class: 'btn btn btn-block btn-success buttonSellState', value: 'true'});
                tr.attr({class: 'bg-success'});
            } else {
                $(this).attr({class: 'btn btn btn-block btn-danger buttonSellState', value: 'false'});
                tr.attr({class: 'bg-danger'});
            }

            $.ajax({
                url: './taskState',
                scriptCharset: 'UTF-8',
                method: 'POST',
                dataType: 'json',
                mimeType: 'application/json',
                contentType: 'application/json',
                data: JSON.stringify({doneState: value, taskId: taskIdValue})
            });
        });
    }

});