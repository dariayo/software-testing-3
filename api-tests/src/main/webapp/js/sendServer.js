const checkInput = function () {
    if (checkX(getX()) && checkY(getY()) && checkR(getR())) {
        let x = getX();
        let y = getY();
        let r = getR();
        sendServer(x, y, r);
    }
}

function sendServer(x, y, r) {
    $.ajax({
        type: "POST",
        url: "controllerServlet",
        dataType: "json",
        async: false,
        data: {
            "x-value": x.toString().trim(), "y-value": y.toString().trim(), "r-value": r.toString().trim(),
            "timezone": new Date().getTimezoneOffset()
        },

    });

}
