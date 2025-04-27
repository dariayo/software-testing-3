let Y;

function showError() {
    let showErr = document.querySelector('.error');
    showErr.innerHTML = '';
    showErr.classList.remove("show");
}

function addError(message) {
    let showErr = document.querySelector('.error');
    showErr.innerHTML += message + "<br>";
    showErr.classList.add("show");
}

function getX() {

    return parseFloat(document.getElementById("x-value-select").value);
}

function getY() {
    return parseFloat(Y);
}

function getR() {
    return parseFloat(document.getElementById("r_select").value);
}

function chooseButton(element, className) {
    if (className === 'y-button') {
        Y = element.value;
    }
    [...document.getElementsByClassName(className)].forEach(function (btn) {
        btn.style.backgroundColor = "#f8f8ff";
        btn.style.color = "black";
        btn.style.border = '2px solid lightslategray';
        btn.style.borderRadius = '4px';

    });
    element.style.backgroundColor = "#ffc0cb";
    element.style.color = "#ffffff";

}

function checkX(x) {
    showError();
    let err = '';
    let xCorr = false;

    if (x != null && !isNaN(x)) {
        if ((x > -5) && (x < 5)) {
            xCorr = true;
        } else {
            err = "X should be (-5..5)!";

        }
    } else err = "Enter X!";

    addError(err);
    return xCorr;
}

function checkY(y) {
    showError();
    let err = '';
    let yCorr = false;

    if (y !== undefined && !isNaN(y)) {
        yCorr = true;
    } else err = "Choose Y!";

    addError(err);
    return yCorr;
}

function checkR(r) {

    showError();
    let err = '';
    let rCorr = false;

    if (r != null && !isNaN(r)) {
        if ((r > 1) && (r < 5)) {
            rCorr = true;
        } else {
            err = "R should be (2..5)!";
        }
    } else err = "Enter R!";

    addError(err);
    return rCorr;
}

