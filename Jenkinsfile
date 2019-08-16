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
                    sh "mvn test -f /home/ebrik/bc-ebrik/Code/"   
    //                sh "mvn package -f /home/ebrik/bc-ebrik/Code/"   
                
            }
        }
        
          stage('Clean & Generate Snapshot') {
            steps {
                    sh "mvn versions:set -DnewVersion=$env.AppVersion-SNAPSHOT -f /home/ebrik/bc-ebrik/Code/pom.xml"
                    sh "mvn -B clean deploy -DnewVersion=$env.AppVersion-SNAPSHOT -f /home/ebrik/bc-ebrik/Code/ -DskipTests"   
                
            }
        }
        stage('Release & Deploy Image to Nexus'){
            steps{  
                  
                   sh "mvn -B release:clean release:prepare release:perform    -f /home/ebrik/bc-ebrik/Code/ -DcheckModificationExcludeList=**  -DskipTests"   
                 
              
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
