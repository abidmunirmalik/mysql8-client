pipeline {
    agent any
    environment {
        DOCKERHUB_CREDS   = "dockerhub-creds"
        ARTIFACTORY_CREDS = "artifactory-creds"
    }
    options {
        buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '1')
    }
    stages {
        stage('version check') {
            steps {
                sh "git --version"
                sh "docker --version"
            }//steps
         }// stage
        stage('Build & Push to Dockerhub') {
            steps {
             script {
                docker.withRegistry('', "$DOCKERHUB_CREDS") {
                  docker.build("abidmunirmalik/mysql8-client", '.').push('latest') 
               }
             } // script
            }//steps
         }// stage
        stage('Build & Push to Artifactory') {
            steps {
             script {
                docker.withRegistry('https://docker-artifactory.webassign.net', "$ARTIFACTORY_CREDS") {
                  docker.build("docker-artifactory.webassign.net/waops/mysql8-client", '.').push('latest') 
               }
             } // script
            }//steps
         }// stage

    } //stages
}
