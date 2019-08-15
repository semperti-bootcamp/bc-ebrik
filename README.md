### Subir Imagen de docker a Nexus

Al haber problemas de ports en Nexus, utilizaremos DockerHub para subir nuestra imagen

#### Los pasos son los siguiente:

1) docker login docker.io
Login with your Docker ID to push and pull images from Docker Hub. If you don't have a Docker ID, head over to https://hub.docker.com to create one.
Username (ebrik):
Password:
Login Succeeded

2)docker tag name-imagen:tag name-repository/name-imagen:tag

3)docker push name-repository / name-imagen:tag

4)docker rmi name-repository / name-imagen:tag --force

5)docker pull name-repository / name-imagen:tag

6)docker run -d -p 8080:8080 --net=host name-imagen:tag

7)docker ps -a

