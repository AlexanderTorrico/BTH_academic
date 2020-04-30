$(document).ready(function () {
    if (!localStorage.getItem('objUsuario')) {
        $(location).attr('href', 'index.html');
    }
});

