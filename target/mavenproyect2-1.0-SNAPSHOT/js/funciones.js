$(document).ready(function () {
    $("tr #btnDelete").click(function () {
        var pos = $(this).parent().find("#pos").val();
        swal({
            title: "Eliminar producto",
            text: "Se eliminara el producto del carrito.",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
                .then((willDelete) => {
                    if (willDelete) {
                        eliminar(pos);
                        swal("El producto ha sido eliminado!", {
                            icon: "success",
                        }).then((willDelete) => {
                            if (willDelete) {
                                location.reload();
                            }
                        });
                    } else {
                        swal("Producto no eliminado!");
                    }
                });

    });

    $("tr #btnBorrar").click(function () {
        var pos = $(this).parent().find("#pos").val();
        swal({
            title: "Eliminar",
            text: "Se eliminara un empleado de la base de datos.",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
                .then((willDelete) => {
                    if (willDelete) {
                        eliminarEmpleado(pos);
                        swal("El empleado ha sido eliminado!", {
                            icon: "success",
                        }).then((willDelete) => {
                            if (willDelete) {
                                location.reload();
                            }
                        });
                    } else {
                        swal("Empleado no eliminado!");
                    }
                });

    });
     $("tr #btnBorrarCliente").click(function () {
        var pos = $(this).parent().find("#pos").val();
        swal({
            title: "Eliminar Cliente",
            text: "Se eliminara un cliente de la base de datos.",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
                .then((willDelete) => {
                    if (willDelete) {
                        eliminarCliente(pos);
                        swal("El cliente ha sido eliminado!", {
                            icon: "success",
                        }).then((willDelete) => {
                            if (willDelete) {
                               location.reload();
                            }
                        });
                    } else {
                        swal("Cliente no eliminado!");
                    }
                });

    });
     $("tr #btnBorrarProducto").click(function () {
        var pos = $(this).parent().find("#pos").val();
        swal({
            title: "Eliminar Producto",
            text: "Se eliminara un producto de la base de datos.",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
                .then((willDelete) => {
                    if (willDelete) {
                        eliminarProducto(pos);
                        swal("El productio ha sido eliminado!", {
                            icon: "success",
                        }).then((willDelete) => {
                            if (willDelete) {
                               location.reload();
                            }
                        });
                    } else {
                        swal("Producto no eliminado!");
                    }
                });

    });
    $("form #btnRegistrar").click(function () {
        var dni = $(this).parent().find("#textdni").val();
        var nom = $(this).parent().find("#textnom").val();
        var direccion = $(this).parent().find("#textdireccion").val();
        var telefono = $(this).parent().find("#texttelefono").val();
        var email = $(this).parent().find("#textcorreo").val();
        var password= $(this).parent().find("#textpass").val();
        console.log(dni+" "+nom+" "+direccion+" "+telefono+" "+email+" "+password);
        swal({
            title: "Registrarse",
            text: "Click en el boton para continuar",
            icon: "info",
            button: "Continuar",
        })
                .then((willDelete) => {
                    if (willDelete) {
                        registrar(dni,nom,direccion,telefono,email,password);
                        swal("Se ha registrado correctamente!", {
                            icon: "success",
                        }).then((willDelete) => {
                            if (willDelete) {
                                parent.location.href = "./validarCliente.jsp";
                            }
                        });
                    } else {
                        swal("No se ha registrado correctamente.");
                    }
                });

    });
     $("tr #btnGenerarVenta").click(function () {
        var pos = $(this).parent().find("#pos").val();
        swal({
            title: "Generar Venta",
            text: "Se va a guardar la compra en la base de datos.",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
                .then((willDelete) => {
                    if (willDelete) {
                        GenerarVenta();
                        swal("Se ha guardado la venta correctamente.", {
                            icon: "success",
                        }).then((willDelete) => {
                            if (willDelete) {
                               location.reload();
                            }
                        });
                    } else {
                        swal("Venta no registrada.");
                    }
                });

    });
    function registrar(dni, nom, direccion, telefono, email, password) {
        var url = "../Validar?menu=Registrar";
        $.ajax({
            type: 'POST',
            data: {dni: dni, nombre: nom, direccion: direccion, telefono: telefono, email: email, password: password},
            url: url,
            success: function (data, textStatus, jqXHR) {



            }
        });
    }
    function eliminarEmpleado(pos) {
        var url = "Controlador1?menu=Empleado&accion=Delete";
        $.ajax({
            type: 'POST',
            url: url,
            data: "pos=" + pos,
            success: function (data, textStatus, jqXHR) {



            }
        });
    }
    function eliminarCliente(pos) {
        var url = "Controlador1?menu=Cliente&accion=Delete";
        $.ajax({
            type: 'POST',
            url: url,
            data: "pos=" + pos,
            success: function (data, textStatus, jqXHR) {



            }
        });
    }
     function eliminarProducto(pos) {
        var url = "Controlador1?menu=Producto&accion=Delete";
        $.ajax({
            type: 'POST',
            url: url,
            data: "pos=" + pos,
            success: function (data, textStatus, jqXHR) {



            }
        });
    }
    function GenerarVenta() {
        var url = "Controlador1?menu=NuevaVenta&accion=GenerarVenta";
        $.ajax({
            type: 'POST',
            url: url,
            success: function (data, textStatus, jqXHR) {



            }
        });
    }


    $("tr #Cantidad").click(function () {
        var idp = $(this).parent().find("#idpro").val();
        var id = $(this).parent().find("#id").val();
        var cantidad = $(this).parent().find("#Cantidad").val();
        var url = "Controlador?accion=ActulizarCantidad";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp=" + idp + "&Cantidad=" + cantidad,
            success: function (data, textStatus, jqXHR) {
                location.href="Controlador?accion=Carrito&id="+id;
            }
        });
    });
});



