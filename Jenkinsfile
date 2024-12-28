pipeline {
    agent any
    tools {
        maven 'sonarmaven' // Ensure this matches the Maven configuration in Jenkins
    }
    environment {
        SONAR_TOKEN = credentials('Sonarqube-token') // Keep the SonarQube token as defined originally
        PATH = 'C:\Program Files\Java\jdk-17' // Set JAVA_HOME path for JDK
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean package' // Build the Maven project
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') { // Ensure this matches your SonarQube configuration
                    bat """
                        sonar-scanner ^
                        -Dsonar.projectKey=maven ^
                        -Dsonar.sources=. ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.token=${SONAR_TOKEN}
                    """
                }
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
