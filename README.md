Ventalandia
===========

El flujo o circuito que sigue MELI es acotado, sólo se encarga de publicar, comprar/vender, notificar, calificar.

* Este proyecto pretende:

    * Facilitar el seguimiento de una transacción desde que se genera hasta que se cierra.
    * Ser un servicio para administrar un punto de venta en Mercadolibre
    * Hacerle la vida mas facil a los vendedores de MELI.

* El proyecto tiene 2 branches: "master" y "develompent"

* Para bajarte el repo:

> git clone https://github.com/raulbajales/napkin.git

* Para pasar a un branch:

> git checkout <branch>

* Para pushear cambios:

> git add .
> git commit -m "mensaje"
> git push origin <branch>
    
* Para deployar estoy usando el plugin de Appengine para Eclipse.

* Si deployas el branch "master" lo ves en:

    * http://www.ventalandia.com <- Este es nuestro entorno de produccion
    * http://master.ventalandia-meli.appspot.com
    
* Si deployas el branch "development" lo ves en:

    * http://development.ventalandia-meli.appspot.com
