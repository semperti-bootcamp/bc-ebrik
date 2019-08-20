Crear Pipeline de CD :
8.0	Debe encontrarse dentro de un folder con el nombre bc-username
8.1	Debe ejecutarse el build cada vez que se realice un PR
8.2	Debe contener al menos las etapas de descarga de imagen, ejecución de contenedor y prueba de acceso a la aplicación mediante un curl y su output


#### Output del curl al localhost

+ curl http://localhost:8080
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed

  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0
100   782  100   782    0     0  63572      0 --:--:-- --:--:-- --:--:-- 65166
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Semperti - Journal System</title>
    <script src="js/angular-min.js"></script>
    <script src="js/app.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body ng-app="JournalApp" ng-controller="CategoryController">
<h2>Bienvenido al sistema de jornales de Semperti</h2>




<div ng-controller="getCategories">
    <table>
        <thead>
        <td>Categoría</td>
        <td>Subscribirse</td>
        </thead>
        <tbody>

        <tr ng-repeat="category in categories">
            <td>{{category.name}}</td>
            <td>
                <a href="/login">Login</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>

[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // ansiColor
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS
