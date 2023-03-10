package edu.escuelaing.app.services;

/**
 * Cliente de busqueda de archivos dentro del disco duro
 * @author Luis Felipe Giraldo Rodriguez
 * @version 1.0
 */
public class SearchService implements Service{

    
    public String getBody(){
        return "<!DOCTYPE html>"+
        "<html lang=\"es\">"+
        "  <head>"+
        "    <autor>Luis Felipe Giraldo</autor>"+
        "    <meta charset=\"utf-8\" />"+
        "    <title>BUSQUEDA ARCHIVOS</title>"+
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />"+
        "    <style>"+
        "      * {"+
        "        box-sizing: border-box;"+
        "      }"+
        "      body {"+
        "        font-family: Arial, Helvetica, sans-serif;"+
        "        background-color: cornsilk;"+
        "      }"+
        "      .header {"+
        "        background-color: rgb(34, 129, 158);"+
        "        padding: 5px;"+
        "        text-align: center;"+
        "        font-size: 20px;"+
        "        color: white;"+
        "        border: 2px solid black;"+
        "        border-radius: 5px;"+
        "      }"+
        "      .container {"+
        "        margin-top: 15px;"+
        "      }"+
        "      input {"+
        "        padding: 12px 20px;"+
        "      }"+
        ""+
        "      button {"+
        "        background-color: rgb(34, 129, 158);"+
        "        border: none;"+
        "        color: white;"+
        "        padding: 15px 32px;"+
        "        text-align: center;"+
        "        text-decoration: none;"+
        "        display: inline-block;"+
        "        border: 2px solid black;"+
        "        border-radius: 5px;"+
        "        cursor: pointer;"+
        "      }"+
        ""+
        "      #pelicula {"+
        "        border: 2px solid black;"+
        "        border-radius: 5px;"+
        "        width: 75%;"+
        "        visibility: hidden;"+
        "      }"+
        "    </style>"+
        "    <script>"+
        "      let connected;"+
        "      let ws;"+
        "      let flag = false;"+
        ""+
        "      /**"+
        "       * Funcion que nos permite realizar una consulta a un ws, enviar un titulo y recibir la informaci????n de una p????licula."+
        "       */"+
        "      function sendJSON() {"+
        "        let path = document.querySelector(\"#path\").value;"+
        "        console.log(path);"+
        "        function makeHttpObject() {"+
        "          if (\"XMLHttpRequest\" in window) return new XMLHttpRequest();"+
        "          else if (\"ActiveXObject\" in window)"+
        "            return new ActiveXObject(\"Msxml2.XMLHTTP\");"+
        "        }"+
        "        function _arrayBufferToBase64(buffer) {"+
        "          var binary = \"\";"+
        "          var bytes = new Uint8Array(buffer);"+
        "          var len = bytes.byteLength;"+
        "          for (var i = 0; i < len; i++) {"+
        "            binary += String.fromCharCode(bytes[i]);"+
        "          }"+
        "          return window.btoa(binary);"+
        "        }"+
        "        var request = makeHttpObject();"+
        "        request.withCredentials = false;"+
        "        request.open(\"GET\", \"http://localhost:35000/file/\" + path, true);"+
        "        request.responseType = \"arraybuffer\";"+
        "        request.send(null);"+
        "        request.onreadystatechange = function () {"+
        "          if (request.readyState == 4) {"+
        "            let responseDec = \"\";"+
        "            if (request.getResponseHeader(\"content-type\").includes(\"image\")) {"+
        "              document.getElementById(\"archivo\").innerHTML ="+
        "                \"No se pueden mostrar imagenes\";"+
        "              document.getElementById(\"archivo\").style.visibility = \"visible\";"+
        "            } else {"+
        "              var enc = new TextDecoder(\"utf-8\");"+
        "              responseDec = enc.decode(request.response);"+
        "              document.getElementById(\"archivo\").innerHTML = responseDec;"+
        "              document.getElementById(\"archivo\").style.visibility = \"visible\";"+
        "              console.log(responseDec);"+
        "            }"+
        "          }"+
        "        };"+
        "        flag = true;"+
        "      }"+
        "    </script>"+
        "  </head>"+
        ""+
        "  <body>"+
        "    <div class=\"header\">"+
        "      <h3>BUSQUEDA DE ARCHIVOS</h3>"+
        "    </div>"+
        "    <div class=\"container\">"+
        "      <input"+
        "        id=\"path\""+
        "        type=\"text\""+
        "        placeholder=\"Escriba el path que desea buscar\""+
        "      />"+
        "      <button type=\"button\" onclick=\"sendJSON()\">Buscar!</button>"+
        "      <p id=\"archivo\" style=\"color: darkblue\"></p>"+
        "      <img id=\"photo\" src=\"\" />"+
        "    </div>"+
        "  </body>"+
        "</html>";        
    }

    public String getHeader(){
        return "HTTP/1.1 200 OK\r\n"
        + "Access-Control-Allow-Origin: *\r\n"
        + "Content-Type:text/html \r\n"
        + "\r\n";
    }

    
}
