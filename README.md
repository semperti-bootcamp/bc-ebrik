# Week 01 - Assignments 1
Crear VM con Terraform

##  Detalle
	1.0. La VM no debe tener mas de 2 cores y 2 GB de RAM
	1.1. La VM debe tener CentOS7
	1.2. La VM debe ser accesible mediante VPN
	1.3. La VM debe poder conectarse a internet

## Pasos

### 1. Instalo go y dep de go y compilo el provider de oVirt
```
Git: https://github.com/ovirt/terraform-provider-ovirt
```

### 2. Instalo terraform en centos | macosx
```
wget https://releases.hashicorp.com/terraform/0.12.6/terraform_0.12.6_linux_amd64.zip
unzip terraform_0.12.6_linux_amd64.zip -d /usr/bin/
terraform -v
```

### 3. Copio binario de provider a directorio de plugins local de terraform probado en centos

```
mkdir ~/.terraform.d/plugins
cp $GOPATH/bin/terraform-provider-ovirt ~/.terraform.d/plugins
```

### 4. Creo los script de terraform bajo el directorio terraform/bc-ebrik
```
- main.tf
- variables.tf
- output.tf
```

### 5. Aplico los cambios con terraform apply ingresando user y pass
```
terraform apply
```

El output de la salida se encuentra en terraform_apply.out

### 6. Inconvenientes.

	1. Al ejecutar el provider lanza el siguiente error colocando las credenciales
	de dominio. 

```
* ovirt_vm.my_vm_1: 1 error(s) occurred:
* ovirt_vm.my_vm_1: Failed to parse non-array sso with response
