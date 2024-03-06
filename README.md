# Proyecto-BadIceCream
En este repositorio almacenamos el proyecto desarrollado por el grupo N°7 del curso de Programación II de la Facultad de Sistemas de La Escuela Politécnica Nacional Del Ecuador.

Antes de Ejecutar el programa tener en cuenta lo siguiente:

Cambiar las rutas de la ubicación de las fuentes en las siguientes clases y métodos:

- presentación/interfazDeUsuario/Configuración: guardarConfig() y cargarConfig()
- datos/GuardarCargar: en los métodos de guardar() y cargar() cambiar la ruta de la siguiente forma:
  "*tuRuta*\\src\\datos\\fuentes\\datosDeJuego\\" + nivel.getNivel() + ".dat"

Para cargar un nuevo nivel se debe enviarlo en la clase BadIceCream en empezarJuego(Nivel); 
y enviar un nuevo nivel de los disponibles en Negocio/niveles. 