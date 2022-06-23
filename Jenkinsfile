pipeline {
    agent any

    stages {
        stage ('Serenity BDD Tests') {
        echo "Starting to execute BDD Tests"
            steps {
                sh './gradlew clean bddTest -Dcucumber.filter.tags="@AddEmployee"'
            }
        echo "BDD Test execution completed"
        }

        stage ('Serenity BDD Tests') {
                echo "Starting Gatling simulation"
                    steps {
                        sh './gradlew clean gatlingRun -Dduration=$Duration -Dusers=$TPS -Denvironment=$Environment'
                    }
                echo "Gatling simulation ended"
        }
    }
}