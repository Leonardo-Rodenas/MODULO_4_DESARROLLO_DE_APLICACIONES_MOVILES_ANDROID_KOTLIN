<center>

# Evidencia dia 03 - Semana 16
#### Leonardo Rodenas Escobar

</center>

---

## **Reflexión:** 
La clase no avanzó mucho el día de hoy, el tratado fue MockWebServer, una libreria que permite probar llamadas a API creando un servidor de pruebas y configurando los cuerpos de las respuesta y errores que se podrian recibir. La idea de buscar nosotros la info y el cómo implementarlo es buena (creo?, no mucho) a primera vista, pero cuando despues de 3 horas de trabajo a nadie le resultó lo que teniamos que hacer y el avance fue nulo, deja más la sensación de perder la mañana que de aprender algo, y creo que si es un curso donde el objetivo es aprender, se pierde precisamente eso, lo más importante.

Como no logramos en mi grupo el poder hacer funcionar lo requerido en el tiempo de clases, entrego acá como evidencia, más que el código (que no funciona), el resultado de la búsqueda de información y la idea de que es un MockWebServer, para así al menos cubrir lo teórico básico sobre el tema.

<center>

## **Pruebas con MockWebServer**

</center>

**¿Qué es un MockWebServer?** -->  MockWebServer actúa como un servidor web, al cual intercepta la petición realizada y devuelve una respuesta con un body previamente establecida, fue creada por Square, creadores de Retrofit y OkHttp. 

Este servidor web simulado se burla del comportamiento de un servidor remoto real, pero no realiza llamadas a través de Internet, lo cual sirve para probar de manera más fácil diferentes escenarios sin acceso a Internet y sin tener que realizar cambios en su servidor remoto.

**¿Por qué utilizar un servidor simulado para probar llamadas a la API?**

- Probar adaptadores es fácil.
- Prueba de estados vacíos (nulls) y de error.
- MockWebServer ejercita el stack HTTP completo (de manera manual es tedioso).
- No es necesario realizar cambios en el lado de la aplicación cuando se utiliza un MockWebServer.
- Es fácil recrear error raros o situaciones adversas controladas en la app.

<center>

![mockwebserver](https://i.imgur.com/YYQZRrd.png)

</center>

## **Sitios consultados:**

- [Using mockwebserver on android](https://engineering.monstar-lab.com/en/post/2020/07/24/using-mockwebserver-on-android)
- [Android mock server for unittest](https://medium.com/mobile-app-development-publication/android-mock-server-for-unittest-82f5bbbf0362)
- [Android unit testing api calls with mockwebserver](https://www.ericthecoder.com/2020/02/03/android-unit-testing-api-calls-with-mockwebserver/)
- [The recommended way of testing http calls mockwebserver by OKHTTP](https://medium.com/xebia-engineering/the-recommended-way-of-testing-http-calls-mockwebserver-by-okhttp-e716f87d6122)
- [Testing rest apis using mockwebserver](https://www.raywenderlich.com/10091980-testing-rest-apis-using-mockwebserver)
- **[Sitio Oficial de Square-OKHTTP3](https://square.github.io/okhttp/#mockwebserver)**
- **[Github de OKHHTP - MockWebServer](https://github.com/square/okhttp)**


Hasta acá mi avance por hoy, en clases  fue más una breve recopilación del material consultado sin código, para así reforzar los conocimientos de la clase de hoy y dejarlos listos para la continuación mañana. Muchas gracias.

---

Leonardo Rodenas Escobar  
  