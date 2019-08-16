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
    }

    stages {
        stage('Prepare Jenkins Slave') {
            steps {
                sh "sudo yum install wget git unzip epel-release ansible java-1.8.0-openjdk maven awscli -y"
                sh "curl https://releases.hashicorp.com/terraform/0.11.13/terraform_0.11.13_linux_amd64.zip -o /usr/local/bin/terraform.zip"
                sh "unzip -u /usr/local/bin/terraform.zip -d /usr/local/bin/"
            }
        }
        stage('Unit Test & Package') {
            steps {
                withCredentials([file(credentialsId: 'MAVEN_SETTINGS', variable: 'MAVEN_SETTINGS')]) {
                    sh "mvn test -f pom.xml -s $MAVEN_SETTINGS"
                    sh "mvn package -f pom.xml -s $MAVEN_SETTINGS"
                }
            }
        }
        stage('Clean & Generate Snapshot') {
            steps {
                withCredentials([file(credentialsId: 'MAVEN_SETTINGS', variable: 'MAVEN_SETTINGS')]) {
                    sh "mvn -B clean deploy -f pom.xml  -s $MAVEN_SETTINGS"
                }
            }
        }
        stage('Release & Deploy Image to Nexus'){
            steps{
                withCredentials([file(credentialsId: 'MAVEN_SETTINGS', variable: 'MAVEN_SETTINGS')]) {
                    sh "mvn -B release:prepare release:perform -DscmCommentPrefix=\"[skip-ci]\" -f pom.xml  -s $MAVEN_SETTINGS"
                }
            }
        }
        stage('Configure Docker Image on Docker Host') {
            steps {
                withCredentials([file(credentialsId: 'ANSIBLE_JSON_ALL', variable: 'ANSIBLE_JSON')]) {
                    sh "ansible-playbook --extra-vars @$ANSIBLE_JSON ansible/create-docker-image.yml -i ansible/java-docker.txt"            
                }
            }
        }
        stage('Publish Image to DTR') {
            steps {
                withCredentials([file(credentialsId: 'ANSIBLE_JSON_ALL', variable: 'ANSIBLE_JSON')]) {
                    sh "ansible-playbook --extra-vars @$ANSIBLE_JSON ansible/push-docker-image.yml -i ansible/java-docker.txt"            
                }
            }
        }
    }
}
