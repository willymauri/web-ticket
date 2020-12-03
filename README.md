# web-ticket
Aplicación ejemplo de un crud

Descargar el proyecto, compilar con maven

<web-ticket> mvn install

En el directorio "PATH_HOME/web-ticket/target" se encuentra el compilado web-ticket.war

Descargar el servidor de aplicaciones WildFly, verificar y configurar el datasource por defecto.
  
  <datasources>
    <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true" statistics-     enabled="${wildfly.datasources.statistics-enabled:${wildfly.statistics-enabled:false}}">
      <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
      <driver>h2</driver>
                    <security>
                        <user-name>sa</user-name>
                        <password>sa</password>
                    </security>
                </datasource>
                <drivers>
                    <driver name="h2" module="com.h2database.h2">
                        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                    </driver>
                </drivers>
            </datasources>
  
  Levantar el servidor de aplicaciones, en el directorio "PATH_HOME/WildFly/standalone/deployments/" colocar el compilado web-ticket.war
  
  Ingresar a la aplicación "http://localhost:8080/web-ticket".
