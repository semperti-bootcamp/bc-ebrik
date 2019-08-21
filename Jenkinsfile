#!groovy

def manifest
def environment

pipeline {

    agent {
        node (){
            label 'bc-ebrik'
        }
    }

    stages {
        stage('Deploy to Stage') {
	    when{ 
		branch 'w1a9-gitops-stage'
	    }
            steps {
		sh "echo ${env.BRANCH_NAME}"
		sh 'git branch'
		script {
            	   manifest = readJSON file: 'manifest.json'
                   environment = readJSON file: 'stage-env.json'
		   echo "Deploying the manifest ${manifest.version} for ${manifest.artifacts.web} to Staging"
		   echo "URL: ${environment.app.healthcheck_url}"
		}
		dir("${env.WORKSPACE}/ansible"){
                	sh "ansible-playbook gitops-deploy.yml -e appname=${environment.app.name} -e repo=${manifest.repo} -e appport=${environment.app.port} -e version=${manifest.version}"
		}
            }
        }

        stage('Deploy to Production') {
	    when{ 
		branch 'w1a9-gitops-prd'
	    }
            steps {
		sh 'git branch'
		script {
            	   manifest = readJSON file: 'manifest.json'
                   environment = readJSON file: 'prd-env.json'
		   echo "Deploying the manifest ${manifest.version} for ${manifest.artifacts.web} to Production"
		   echo "URL: ${environment.app.healthcheck_url}"
		}
		dir("${env.WORKSPACE}/ansible"){
                	sh "ansible-playbook gitops-deploy.yml -e appname=${environment.app.name} -e repo=${manifest.repo} -e appport=${environment.app.port} -e version=${manifest.version}"
		}
            }
    	}
    }
}
