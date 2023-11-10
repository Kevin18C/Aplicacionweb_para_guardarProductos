function enviarFormulario() {
    let request = new XMLHttpRequest();
    request.open('POST', 'NewServlet', true);
    request.setRequestHeader('Content-Type', 'text/plain; charset=UTF-8');

    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            // Peticion terminada.
            if (request.status === 200) {
                // Todo salio bien
                console.log(request.response);
                
            } else {
                console.log("Error en el envío " + request.response);
            }
        }
    };

    var formData = new FormData(document.getElementById('form'));
    request.send(formData);
}

function enviarFormularioOpcion2() {
    const XHR = new XMLHttpRequest();
    var formData = new URLSearchParams(new FormData(document.getElementById('form'))).toString();

    // Define what happens in case of error
    XHR.addEventListener('error', (event) => {
        alert('Oops! Something went wrong.');
    });

    // Set up our requests
    XHR.open('POST', 'NewServlet', true);
    XHR.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    XHR.onload = () => {
        if (XHR.readyState === XHR.DONE && XHR.status === 200) {
            console.log("response => " + XHR.response);
            //console.log("response xml=> " + XHR.responseXML);
            
            document.getElementById('tabla').innerHTML = XHR.response;
            limpiarFormulario();
        }
    };
    XHR.send(formData);
}

function limpiarFormulario() {
    document.getElementById("codigo_producto").value = '';
    document.getElementById("nombre_producto").value = '';
    document.getElementById("precio").value = '';
    document.getElementById("existencia").value = '';
    document.getElementById("fecha_vencimiento").value = '';
    document.getElementById("id_marca").value = '';
    document.getElementById("id_categoria").value = '';
}

function eliminarProducto(codigo_producto) {
    const XHR = new XMLHttpRequest();
    var formData = new URLSearchParams(new FormData());

    // Define what happens in case of error
    XHR.addEventListener('error', (event) => {
        alert('Oops! Something went wrong.');
    });

    // Set up our request
    XHR.open('POST', 'NewServlet', true);
    XHR.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    XHR.onload = () => {
        if (XHR.readyState === XHR.DONE && XHR.status === 200) {
            console.log("response => " + XHR.response);
            setTimeout(function () {
            window.location.reload();
            }, 2000);
        }
    };
    formData.append('codigo_producto', codigo_producto);
    formData.append('control', 'ELIMINAR');
    XHR.send(formData);

}

function mostrarProductos() {
    var control = document.getElementById("control");
    control.value = "Mostrar";
    const XHR = new XMLHttpRequest();
    var formData = new URLSearchParams(new FormData(document.getElementById('form'))).toString();
    // Define what happens in case of error
    XHR.addEventListener('error', (event) => {
        alert('Oops! Something went wrong.');
    });
    // Set up our request
    XHR.open('POST', 'NewServlet', true);
    XHR.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    XHR.onload = () => {
        if (XHR.readyState === XHR.DONE && XHR.status === 200) {
            console.log("response => " + XHR.response);
            document.getElementById('tabla').innerHTML = XHR.response;
            limpiarFormulario();
        }
    };
    XHR.send(formData);
}


function limpiarTabla() {
    var tabla = document.getElementById('tabla1');
    var totalFilas = tabla.rows.length;
    for (var i = totalFilas - 1; i > 0; i--) {
        tabla.deleteRow(i);
    }
}
