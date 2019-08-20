#!groovy

pipeline {
    options {
        ansiColor('xterm')
    }   

    agent {
        node (){
            label 'bc-ebrik'
        } 
    }
    environment {
        ANSIBLE_HOST_KEY_CHECKING = 'false'
	registry = "ebrik/journal"
           registryCredential = 'ebrik'
		   AppVersion = "5.3"
    }

    stages {   
        stage('Unit Test & Package') {
            steps {
                    sh "mvn test -f Code/pom.xml"   
    //                sh "mvn package -f /home/ebrik/bc-ebrik/Code/"   
                
            }
        }
        
          stage('Clean & Generate Snapshot') {
            steps {
                    sh "mvn versions:set -DnewVersion=$env.AppVersion-SNAPSHOT -f Code/pom.xml"
                    sh "mvn -B clean deploy -DnewVersion=$env.AppVersion-SNAPSHOT -f Code/pom.xml -DskipTests"   
                
            }
        }
        stage('Release & Deploy Image to Nexus'){
            steps{  
                  
          //         #sh "mvn -B release:clean release:prepare release:perform -f Code/pom.xml -DcheckModificationExcludeList=**  -DskipTests"   
                   sh "mvn -B clean deploy -f Code/pom.xml -DcheckModificationExcludeList=**  -DskipTests"   
              
            }
        }
  
    stage('Building image') {
      steps{
      //  script {
      //    dockerImage = docker.build registry + ":$env.AppVersion"
       		sh "sudo docker build -t docker.io/ebrik/journal:latest ."
	// }
      }
    }
    stage('Stage 6 - Docker pull & run') {
            steps {
		dir("${env.WORKSPACE}/ansible"){
			sh "pwd"
                	sh "ansible-playbook --extra-vars @vars/ansible-vars.json docker-run.yml"
		}
		timeout(300) {
		    waitUntil {
		       script {
			 def r = sh script: 'curl http://localhost:8080', returnStatus: true
			 return (r == 0);
		       }
		    }
		} 
            }
        }
        stage('Stage 7 - Check Application RUN') {
            steps {
		sh "curl http://localhost:8080"
            }
        }
    }
}
