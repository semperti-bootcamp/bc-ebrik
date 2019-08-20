# Week 01 - Assigments 9 

GitOps

	9.0	Se debe realizar la configuración de un Manifest en GitHub
	9.1	La modificación del Manifest, sólo deberá afectar el ambiente elegido [tiene que haber, al menos, dos ambientes distintos (staging/prod)]
	9.2	Debe ejecutarse automáticamente, tras únicamente, la modificación del Manifest y SOLO del ambiente elegido

## Pasos

Utilizare esta rama como la rama master del Assigment 9. Los archivos son los que definen debajo.

	master -> gitops
		- minifest.json
		- Jenkinsfile

	staging -> gitops-stage
		- manifest.json
		- stage-env.json

	prod ->  gitops-prd
		- manifest.json
		- prd-env.json

