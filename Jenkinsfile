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
<<<<<<< HEAD
                    sh "mvn test -f Code/pom.xml"   
=======
                    sh "mvn test -f /home/ebrik/bc-ebrik/Code/"   
>>>>>>> ea24ba906c62590eb5e56b8966afcf0d6a233d66
    //                sh "mvn package -f /home/ebrik/bc-ebrik/Code/"   
                
            }
        }
        
          stage('Clean & Generate Snapshot') {
            steps {
<<<<<<< HEAD
                    sh "mvn versions:set -DnewVersion=$env.AppVersion-SNAPSHOT -f Code/pom.xml"
                    sh "mvn -B clean deploy -DnewVersion=$env.AppVersion-SNAPSHOT -f Code/pom.xml -DskipTests"   
=======
                    sh "mvn versions:set -DnewVersion=$env.AppVersion-SNAPSHOT -f /home/ebrik/bc-ebrik/Code/pom.xml"
                    sh "mvn -B clean deploy -DnewVersion=$env.AppVersion-SNAPSHOT -f /home/ebrik/bc-ebrik/Code/pom.xml -DskipTests"   
>>>>>>> ea24ba906c62590eb5e56b8966afcf0d6a233d66
                
            }
        }
        stage('Release & Deploy Image to Nexus'){
            steps{  
                  
<<<<<<< HEAD
                   #sh "mvn -B release:clean release:prepare release:perform -f Code/pom.xml -DcheckModificationExcludeList=**  -DskipTests"   
                   sh "mvn -B clean deploy -f /home/ebrik/bc-ebrik/Code/pom.xml -DcheckModificationExcludeList=**  -DskipTests"   
=======
                   sh "mvn -B release:clean release:prepare release:perform -f /home/ebrik/bc-ebrik/Code/pom.xml -DcheckModificationExcludeList=**  -DskipTests"   
                 
>>>>>>> ea24ba906c62590eb5e56b8966afcf0d6a233d66
              
            }
        }
  
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry + ":$env.AppVersion"
        }
      }
    }
    stage('Deploy Image') {
      steps{
        script {
          docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
          }
        }
      }
    }
    stage('Remove Unused docker image') {
      steps{
        sh "docker rmi $registry:$env.AppVersion"
      }
    }
        
        
        
  }

}
