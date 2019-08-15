# Week 01 - Assignments 5
Dockerizar aplicacion Java

1. Se debe proveer el Dockerfile y los archivos necesarios para generar la imagen
2. Debe quedar corriendo el container
3. Debe proveerse un link para probar el funcionamiento del contenedor

	Link: http://10.252.7.166:8080

## Paso 1. Instalar docker y levantar servicio.

yum -y install docker
systemctl start docker
docker version

## Paso 2. Crear Dockerfile con network namespace default

Creo Docker file en ./docker/journal5.0/Dockerfile y corro el siguiente comando.

docker build --rm=true --no-cache --force-rm --tag journal:5.0 .

Esto crea una imagen
# docker image ls

```
#docker images | grep journal
REPOSITORY                TAG                 IMAGE ID            CREATED             SIZE
journal                   5.0                 b2c029f9f516        23 hours ago        155 MB


```

```
docker run --rm -p 8080:8080 --net=host --name=journal journal:5.0
```

```
docker run --rm -d -p 8080:8080 journal:5.0-SNAPSHOT
```
