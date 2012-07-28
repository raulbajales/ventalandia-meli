Ventalandia
===========

* Si deployas el branch "master" lo ves en:

    * http://www.ventalandia.com <- Este es nuestro entorno de produccion
    * http://master.ventalandia-meli.appspot.com
    
* Si deployas el branch "development" lo ves en:

    http://development.ventalandia-meli.appspot.com


Para bajarte el repo:

    git clone ...

Para pasar a un branch:

    git checkout <branch>

Para pushear cambios:

    git add .
    git commit -m "mensaje"
    git push origin <branch>
    
Para deployar estoy usando el plugin de Appengine para Eclipse.