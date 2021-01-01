var spans = document.getElementsByClassName("close");

var dialogs = document.getElementsByClassName('modal');

var columns = document.getElementsByClassName('column');
window.onload = function () {
    for (let i = 0; i < columns.length; i++) {
        columns[i].addEventListener('click', function () {
            dialogs[i].style.display = 'block';
        });
    }
}

// When the user clicks on <span> (x), close the modal
console.log(spans.length);
for (let i = 0; i < spans.length; i++) {
    spans[i].addEventListener('click', function () {
        dialogs[i].style.display = "none";
    });
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    for (let i = 0; i < columns.length; i++) {
        if (event.target == dialogs[i])
            dialogs[i].style.display = "none";
    }
}