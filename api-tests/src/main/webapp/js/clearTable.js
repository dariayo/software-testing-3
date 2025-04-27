function clearTable() {
    $.ajax({
        type: "DELETE",
        url: "controllerServlet",
        async: false,
        success: function () {
            clear()
            $('.point').remove();
        }
    })
}

const table = document.getElementById("res");

function clear() {
    for (let i = table.rows.length - 1; i > 0; i--) {
        table.deleteRow(i);
    }
}