/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
                $('.btn1').click(function () {
                    cargarCont(1);
                });
                $('.btn2').click(function () {
                    cargarCont(2);
                });

                function cargarCont( n) {
                    var t1 = "";
                    var t2 = "";
                    var t3 = "";
                    var t4 = "";

                    t1 += "<table>";
                    t1 += "<thead>";
                    t1 += "<th>nR</th>";
                    t1 += "<th>nOMBRE</th>";
                    t1 += "<th>Correo</th>";
                    t1 += "<thead>";
                    t1 += "</thead";
                    t1 += "<tbody>";
                    t1 += "<tr>";
                    t1 += "<td>sededf</td>";
                    t1 += "<td>Lucho</td>";
                    t1 += "<td>lucho@sdask</td>";
                    t1 += "</tr>";
                    t1 += "<tr>";
                    t1 += "<td>1</td>";
                    t1 += "<td>Lucho</td>";
                    t1 += "<td>lucho@sdask</td>";
                    t1 += "</tr>";
                    t1 += "</tbody>";
                    t1 += "<tr></tr></table>";

                    t2 += "<table>";
                    t2 += "<thead>";
                    t2 += "<th>id</th>";
                    t2 += "<th>apellido</th>";
                    t2 += "<th>Correoss</th>";
                    t2 += "<thead>";
                    t2 += "</thead";
                    t2 += "<tbody>";
                    t2 += "<tr>";
                    t2 += "<td>1</td>";
                    t2 += "<td>Lucho</td>";
                    t2 += "<td>lucho@sdask</td>";
                    t2 += "</tr>";
                    t2 += "<tr>";
                    t2 += "<td>1</td>";
                    t2 += "<td>Lucho</td>";
                    t2 += "<td>lucho@sdask</td>";
                    t2 += "</tr>";
                    t2 += "</tbody>";
                    t2 += "<tr></tr></table>";
                    
                    
                    //Eliminar contenido
                    $('.cont').empty();
                    //Cargar la tabla
                    if (n === 1) {
                        $('.cont').append(t1);
                    }
                    if (n === 2) {
                        $('.cont').append(t2);
                    }
                    if (n === 3) {
                        $('.cont').append(t3);
                    }
                    if (n === 4) {
                        $('.cont').append(t4);
                    }


                }
            });