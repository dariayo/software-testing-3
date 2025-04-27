function svgPoint(event) {
    document.querySelector('svg');
    showError();
    let r = getR();
    if (checkR(r)) {
        let x = (r * (event.pageX - 274) / 150).toFixed(2);
        let y = (r * (event.pageY - 320) / -150).toFixed(2);
        sendServer(x, y, r);
    }
}


