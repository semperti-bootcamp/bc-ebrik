#!groovy

def man

pipeline {

    agent {
        node () {
            label 'bc-ebrik'
        }
    }

    stages {

	// STAGE1 - Slave Configure & Clean
       	stage('Configure & Clean Slave') {
           steps {
		script {
		   man = readJSON file: 'manifest.json'
		   echo "STAGE1 - Tasks pre Test and build"
		   echo "Version: ${man.stg.ver.maj}.${man.stg.ver.min}"
   		}
		   sh "sudo yum -y install wget nc ansible"
            }
       	}


	// STAGE2 - Unit Test
       	stage('Unit Test') {
            steps {
                sh "mvn test -f Code/pom.xml"
            }
       	} 

	// STAGE3 - Release and Upload to Nexus
       	stage('Release & Upload Nexus') {
            steps {
                sh "mvn versions:set -DnewVersion=${man.stg.ver.maj}.${man.stg.ver.min} -f Code/pom.xml"
                sh "mvn clean deploy -f Code/pom.xml -DskipTests" 
            }
       	}

	// STAGE4 - Snapshot and Upload to Nexus
       	stage('Snapshot & Upload Nexus') {
            steps {
                sh "mvn versions:set -DnewVersion=${man.stg.ver.maj}.${man.stg.ver.min}-SNAPSHOT -f Code/pom.xml"
                sh "mvn clean deploy -f Code/pom.xml -DskipTests" 
            }
       	}

	// STAGE5 - Docker build, tag and push image 
	stage('Docker build, tag & push images ') {
            steps {
	       withCredentials([usernamePassword(credentialsId: 'ebrik', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {	
		  dir("${env.WORKSPACE}/ansible"){
		    	sh "ansible-playbook docker-build.yml --extra-vars @vars/ansible-vars.json -e VERSION=${man.stg.ver.maj}.${man.stg.ver.min} -e USERNAME=$USERNAME -e PASSWORD=$PASSWORD"
		  }
	       } 
            }
	}

	// STAGE6 - Version
        stage('Determine Version') {
            steps {
		script {
		   // Stage versions
		   env.run_stg_ver = sh(returnStdout: true, script: "sudo docker ps -a | grep journal_stage | awk '{ print \$2 }' | cut -d: -f2").trim()
		   env.run_stg_ver_maj = sh(returnStdout: true, script: "echo '${env.run_stg_ver}' | awk -F'[ .]' '{print \$1}'").trim()
		   env.run_stg_ver_min = sh(returnStdout: true, script: "echo '${env.run_stg_ver}' | awk -F'[ .]' '{print \$2}'").trim()

		   // Prod versions
		   env.run_prd_ver = sh(returnStdout: true, script: "sudo docker ps -a | grep journal_latest | awk '{ print \$2 }' | cut -d: -f2").trim()
		   env.run_prd_ver_maj = sh(returnStdout: true, script: "echo '${env.run_prd_ver}' | awk -F'[ .]' '{print \$1}'").trim()
		   env.run_prd_ver_min = sh(returnStdout: true, script: "echo '${env.run_prd_ver}' | awk -F'[ .]' '{print \$2}'").trim()

		   // Print Versions 
		   echo "Stage deployed: ${env.run_stg_ver} -- Stage to deploy: ${man.stg.ver.maj}.${man.stg.ver.min}"
		   echo "Prod deployed: ${env.run_prd_ver} -- Prod to deploy: ${man.prd.ver.maj}.${man.prd.ver.min}"

		   // Deploy stage if stg.ver deployed NOT EQUAL stg.ver in man.json
		   env.deploy_stg = sh(returnStdout: true, script: " '${env.run_stg_ver_min}' -ne '${man.stg.ver.min}' && echo 'YES'").trim()
		
		   // Deploy prod if stg.ver deployed LESS THAN OR EQUAL stage in man.json
		   env.deploy_prd = sh(returnStdout: true, script: " '${man.prd.ver.min}' -le '${man.stg.ver.min}' && echo 'YES'").trim()

		}  
            }
        }
	
	// STAGE7 - Deploy to Staging
        stage('Deploy to Staging') {
	    when { 
		environment name: "deploy_stg", value: "YES"
	    } 
            steps {
		sh "echo 'Deploy Stage Version: ${man.stg.ver.maj}.${man.stg.ver.min}'"
		dir("${env.WORKSPACE}/ansible"){
                	sh "ansible-playbook deploy-app.yml -e appname=${man.stg.app.name} -e repo=${man.stg.docker_repo} -e appport=${man.stg.app.port} -e version=${man.stg.ver.maj}.${man.stg.ver.min}"
		} 
            }
    	}
	
	// STAGE8 - Deploy to Prod
        stage('Deploy to Prod') {
	    when { 
		environment name: "deploy_prd", value: "YES"
	    } 
            steps {
		sh "echo 'Deploy Prod Version: ${man.prd.ver.maj}.${man.prd.ver.min}'"
		dir("${env.WORKSPACE}/ansible"){
                	sh "ansible-playbook deploy-app.yml -e appname=${man.prd.app.name} -e repo=${man.prd.docker_repo} -e appport=${man.prd.app.port} -e version=${man.prd.ver.maj}.${man.prd.ver.min}"
		}
            }
    	}
    }
}
