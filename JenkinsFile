pipeline {
    agent any

    stages {
        stage ('Run Serenity BDD Tests') {
            steps {
                sh './gradlew clean bddTest'
            }
        }
    }
}