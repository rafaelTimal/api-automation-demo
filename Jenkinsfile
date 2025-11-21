pipeline {
    agent any

    tools {
        jdk 'jdk25'
        maven 'maven3.9.11'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rafaelTimal/api-automation-demo.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finalizado — limpiando workspace.'
            cleanWs()
        }
    }
}
