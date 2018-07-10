$(document).ready( function () {
    // addTask();
	$.when(addTask()).done(getTodoList());

	function addTask() {
	$("#add_task_button").on("click", function() {
        var description = $("#add_desc");
        console.log(description.val());
		if (Boolean(isValid(description))) {
            console.log("inside " + description.val());
				$.ajax({
				scriptCharset: 'UTF-8',
				url: "addTask",
				method: "POST",
				mimeType: 'application/json',
				dataType: "json",
				error: function (message) {
						console.log(message);
				},
				data: {"description" :  description.val()}
					// data: JSON.stringify({description:description.val()})
			});
		}
		description.val('');
	});
	}

	function isValid(description) {
		var valid = description !== "";
		if (valid) {
			$("label [for='" + description.attr('id') + "']").css("color", '');
		} else {
			$("label [for='"+ description.attr('id') +"']").css("color", 'red');
		}
		return valid;
	}

	function getTodoList() {
		$.ajax({
			scriptCharset: 'UTF-8',
			url: "getTodoList",
			method: "GET",
			mimeType: 'application/json',
			dataType: "json",
			error: function (message) {
					console.log(message);
			},
			complete: function(data) {
				$.each(data.items, function(index, element) {
					createEachRow(element);
				} );
			}
		});
	}

	function createEachRow(element) {
		var tableBody = $("#tbodyTodoList");
		var tr = $("<tr></tr>");
		tr.append("<td></td>").text(element.id);
		tr.append("<td></td>").text(element.description);
		tr.append("<td></td>").text(element.create_date);
		tr.append("<td></td>").text(element.done);
		tableBody .append(tr);
	}

	$("#show-done").on("click", function() {
		filteringTasks();
	});


    function filteringTasks() {
		var checked = $('#show-done').is(':checked');
		var row = $(this).find("#taskTable tbody  tr");
		$(row).each(function() {
			var done = $(this).find("#taskTable > tbody  > tr > td ").eq(3).html();
			if ((checked) && (done === false)) {
				row.hide();
			} else {
				row.show();
			}
		});
	}
});